package com.chemicalshop.controller;

import com.chemicalshop.entities.ProductsEntity;
import com.chemicalshop.entities.UsersEntity;
import com.chemicalshop.services.serviceinterface.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShopController {

    private ProductService productService;
    private CategoryService categoryService;
    private CartService cartService;
    private OrderInfoService orderInfoService;

    @ModelAttribute
    public void addCommonElements(Model model) {
        model.addAttribute("productInCart", cartService.getPositionsCount());
        model.addAttribute("category", categoryService.getAll());
        model.addAttribute("cart",cartService.getAllId());
        model.addAttribute("user", getPrincipal());
        model.addAttribute("orders", orderInfoService.getAllOrders());
    }

    @Autowired
    public ShopController(ProductService productService, CategoryService categoryService,
                          CartService cartService, OrderInfoService orderInfoService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.cartService = cartService;
        this.orderInfoService = orderInfoService;
    }

    @RequestMapping(value = {"", "/", "/home"}, method = RequestMethod.GET)
    public ModelAndView homePage() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public ModelAndView contactPage() {
        return new ModelAndView("contactpage");
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView productsPage() {
        ModelAndView modelAndView = new ModelAndView("products");
        List<ProductsEntity> productList = productService.getAllProduct(1, 6);
        int amountOfProduct = productService.getAllProduct().size();
        modelAndView.addObject("products", productList);
        modelAndView.addObject("amountOfPages", (amountOfProduct % 6 == 0) ? (amountOfProduct / 6) : (amountOfProduct / 6 + 1));
        modelAndView.addObject("url", "/products/");
        modelAndView.addObject("openedPage", 1);
        return modelAndView;
    }

    @RequestMapping(value = "/products/{pageNumber}", method = RequestMethod.GET)
    public ModelAndView specificProductsPage(@PathVariable("pageNumber") int page) {
        ModelAndView modelAndView = new ModelAndView("products");
        List<ProductsEntity> productList = productService.getAllProduct(page, 6);
        int amountOfProduct = productService.getAllProduct().size();
        modelAndView.addObject("products", productList);
        modelAndView.addObject("amountOfPages", (amountOfProduct % 6 == 0) ? (amountOfProduct / 6) : (amountOfProduct / 6 + 1));
        modelAndView.addObject("url", "/products/");
        modelAndView.addObject("openedPage", page);
        return modelAndView;
    }

    @RequestMapping(value = "/products/category/{id}", method = RequestMethod.GET)
    public ModelAndView getProductsByCategory(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("products");
        List<ProductsEntity> productList = productService.getProductsByCategory(id, 1, 6);
        int amountOfProduct = productService.getAllProductsByCategory(id).size();
        modelAndView.addObject("products", productList);
        modelAndView.addObject("amountOfPages", (amountOfProduct % 6 == 0) ? (amountOfProduct / 6) : (amountOfProduct / 6 + 1));
        modelAndView.addObject("url", "/products/category/" + id + "/");
        modelAndView.addObject("openedPage", 1);
        return modelAndView;
    }

    @RequestMapping(value = "products/category/{id}/{pageNumber}", method = RequestMethod.GET)
    public ModelAndView getProductsByCategoryOnSpecificPage(@PathVariable("id") int id, @PathVariable("pageNumber") int page) {
        ModelAndView modelAndView = new ModelAndView("products");
        List<ProductsEntity> productList = productService.getProductsByCategory(id, page, 6);
        int amountOfProduct = productService.getAllProductsByCategory(id).size();
        modelAndView.addObject("products", productList);
        modelAndView.addObject("amountOfPages", (amountOfProduct % 6 == 0) ? (amountOfProduct / 6) : (amountOfProduct / 6 + 1));
        modelAndView.addObject("url", "/products/category/" + id + "/");
        modelAndView.addObject("openedPage", page);
        return modelAndView;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView getProductsBySearch(@RequestParam("searchValue") String searchValue) {
        ModelAndView modelAndView = new ModelAndView("redirect:/search/" + searchValue + "/1");
        modelAndView.addObject("searchValue", searchValue);
        return modelAndView;
    }

    @RequestMapping(value = "search/{searchValue}/{pageNumber}", method = RequestMethod.GET)
    public ModelAndView getProductsBySearchOnSpecificPage(@PathVariable("searchValue") String searchValue, @PathVariable("pageNumber") int page) {
        ModelAndView modelAndView = new ModelAndView("products");
        List<ProductsEntity> productList = productService.getProductsBySearch(searchValue, page, 6);
        int amountOfProduct = productService.getAllProductsBySearch(searchValue).size();
        modelAndView.addObject("products", productList);
        modelAndView.addObject("amountOfPages", (amountOfProduct % 6 == 0) ? (amountOfProduct / 6) : (amountOfProduct / 6 + 1));
        modelAndView.addObject("url", "/search/");
        modelAndView.addObject("openedPage", page);
        return modelAndView;
    }

    @RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
    public void showImage(@PathVariable("id") final int id, HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(productService.getProductById(id).getImage());
        response.getOutputStream().close();
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
