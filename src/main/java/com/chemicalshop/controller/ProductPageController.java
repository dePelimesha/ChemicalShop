package com.chemicalshop.controller;

import com.chemicalshop.services.serviceinterface.CartService;
import com.chemicalshop.services.serviceinterface.CategoryService;
import com.chemicalshop.services.serviceinterface.OrderInfoService;
import com.chemicalshop.services.serviceinterface.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Controller
public class ProductPageController {
    private ProductService productService;
    private CategoryService categoryService;
    private CartService cartService;
    private OrderInfoService orderInfoService;

    @ModelAttribute
    public void addCommonElements(Model model) {
        model.addAttribute("category", categoryService.getAll());
        model.addAttribute("productInCart", cartService.getPositionsCount());
        model.addAttribute("cart",cartService.getAllId());
        model.addAttribute("user", getPrincipal());
        model.addAttribute("orders", orderInfoService.getAllOrders());
    }

    @Autowired
    public ProductPageController(ProductService productService, CategoryService categoryService,
                          CartService cartService, OrderInfoService orderInfoService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.cartService = cartService;
        this.orderInfoService = orderInfoService;
    }

    @RequestMapping(value="/productDetails/{id}", method = RequestMethod.GET)
    public ModelAndView viewProductInfo(@PathVariable("id") int idOfProduct) {
        ModelAndView modelAndView = new ModelAndView("productpage");
        int pages = productService.getAllProduct().size() / 3;
        System.out.println(pages);
        Random random = new Random();
        int pageNumber = 1 + random.nextInt(pages - 1 + 1);
        modelAndView.addObject("product", productService.getProductById(idOfProduct));
        modelAndView.addObject("recommendedProducts", productService.getAllProduct(pageNumber, 3));
        return modelAndView;
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
