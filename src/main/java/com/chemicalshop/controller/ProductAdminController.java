package com.chemicalshop.controller;

import com.chemicalshop.entities.CategoryEntity;
import com.chemicalshop.entities.ProductsEntity;
import com.chemicalshop.entities.Views;
import com.chemicalshop.services.serviceinterface.*;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductAdminController {
    private ProductService productService;
    private CategoryService categoryService;
    private CartService cartService;
    private OrderInfoService orderInfoService;

    @Autowired
    public ProductAdminController(CategoryService categoryService, CartService cartService,
                                  OrderInfoService orderInfoService, ProductService productService) {
        this.categoryService = categoryService;
        this.cartService = cartService;
        this.orderInfoService = orderInfoService;
        this.productService = productService;
    }

    @ModelAttribute
    public void addCommonElements(Model model) {
        List<String> categories = new ArrayList<>();
        for(CategoryEntity category: categoryService.getAll()) {
            categories.add(category.getCategoryName());
        }
        model.addAttribute("productInCart", cartService.getPositionsCount());
        model.addAttribute("category", categoryService.getAll());
        model.addAttribute("categories", categories);
        model.addAttribute("cart",cartService.getAllId());
        model.addAttribute("orders", orderInfoService.getAllOrders());
        model.addAttribute("user", getPrincipal());
    }

    @ModelAttribute("newProduct")
    public ProductsEntity createProductModel() {
        return new ProductsEntity();
    }

    @Autowired
    @Qualifier("productValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(validator);
    }

    @RequestMapping(value = "/createProduct", method = RequestMethod.GET)
    public ModelAndView createProductPage() {
        return new ModelAndView("createproduct");
    }

    @RequestMapping(value = "/createProduct", method = RequestMethod.POST)
    public ModelAndView createProduct(@ModelAttribute("newProduct") @Validated ProductsEntity product, BindingResult bindingResult, @RequestParam("chooseCategory") String categoryName
    ) throws IOException{
        if (bindingResult.hasErrors()) {
            return new ModelAndView("createproduct");
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/createProduct");
        File image = new File("E:\\6\\BiBD_p2\\untitled\\src\\main\\resources\\image\\default.png");
        FileInputStream fis = new FileInputStream("E:\\6\\BiBD_p2\\untitled\\src\\main\\resources\\image\\default.png");
        byte[] bytes = new byte[((int) image.length())];
        product.setCategory(categoryService.getByCategoryName(categoryName));
        fis.read(bytes);
        product.setImage(bytes);
        fis.close();
        productService.addProduct(product);
        return modelAndView;
    }

    @RequestMapping(value = "/changeImage", method = RequestMethod.POST)
    public ModelAndView changeImage(@RequestParam("image") MultipartFile imageFile, @RequestParam("productsName") String name) throws IOException{
        ProductsEntity product = productService.getByName(name);
        if (product != null) {
            if (!imageFile.isEmpty() && (imageFile.getContentType().equals("image/png") || imageFile.getContentType().equals("image/jpg"))) {
                ModelAndView modelAndView = new ModelAndView("redirect:/createProduct");
                System.out.println("Change file in " + product.getProductName());
                product.setImage(null);
                product.setImage(imageFile.getBytes());
                productService.updateProduct(product);
                return modelAndView;
            } else {
                ModelAndView modelAndView = new ModelAndView("createproduct");
                modelAndView.addObject("imageErr", "Wrong file");
                return modelAndView;
            }
        } else {
            ModelAndView modelAndView = new ModelAndView("createproduct");
            modelAndView.addObject("noUserErr", "No such product");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/addFromJSON", method = RequestMethod.POST)
    public ModelAndView addFromJson(@RequestParam("image") MultipartFile jsonFile) throws Exception {
        if (!jsonFile.isEmpty() && jsonFile.getContentType().equals("application/json")) {
            ModelAndView modelAndView = new ModelAndView("redirect:/createProduct");
            File file = new File(jsonFile.getOriginalFilename());
            jsonFile.transferTo(file);
            JSONParser parser = new JSONParser();
            JSONArray products = (JSONArray) parser.parse(new FileReader(file.getName()));
            for(Object o: products) {
                JSONObject productValue = (JSONObject) o;
                ProductsEntity product = new ProductsEntity();
                product.setProductName((String) productValue.get("productName"));
                product.setPrice((double) productValue.get("price"));
                Long inStock = (long) productValue.get("inStock");
                product.setInStock(inStock.intValue());
                JSONObject categories = (JSONObject) productValue.get("category");
                Integer categoryID = ((Long) categories.get("categoryId")).intValue();
                product.setCategory(categoryService.getById(categoryID));
                product.setPhysicalProperties((String) productValue.get("physicalProperties"));
                product.setMolarMass((double) productValue.get("molarMass"));
                product.setChemicalFormula((String) productValue.get("chemicalFormula"));
                File image = new File("E:\\6\\BiBD_p2\\untitled\\src\\main\\resources\\image\\default.png");
                FileInputStream fis = new FileInputStream("E:\\6\\BiBD_p2\\untitled\\src\\main\\resources\\image\\default.png");
                byte[] bytes = new byte[((int) image.length())];
                fis.read(bytes);
                fis.close();
                product.setImage(bytes);
                productService.addProduct(product);
            }
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("createproduct");
            modelAndView.addObject("err", "Wrong file");
            return modelAndView;
        }
    }

    @RequestMapping(value = { "/downloadJSON" }, method = RequestMethod.GET)
    public String downloadDocument(HttpServletResponse response) throws Exception {
        File document = new File("E:\\6\\BiBD_p2\\untitled\\src\\main\\resources\\json\\generated.json");
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        JsonFactory factory = new JsonFactory();
        JsonGenerator gen = factory.createGenerator(writer);
        gen.writeStartArray();
        for (ProductsEntity product: productService.getAllProduct()) {
            mapper.writerWithView(Views.Public.class).writeValue(gen, product);
        }
        gen.writeEndArray();
        gen.close();
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(writer.toString());
        mapper.writeValue(document, jsonArray);
        FileInputStream fis = new FileInputStream("E:\\6\\BiBD_p2\\untitled\\src\\main\\resources\\json\\generated.json");
        byte[] bytes = new byte[((int) document.length())];
        fis.read(bytes);
        fis.close();
        response.setContentType(document.getName());
        response.setContentLength(((int) document.length()));
        response.setHeader("Content-Disposition","attachment; filename=\"" + document.getName() +"\"");

        FileCopyUtils.copy(bytes, response.getOutputStream());

        return "redirect:/createProduct";
    }

    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
