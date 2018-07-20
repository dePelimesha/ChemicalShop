package com.chemicalshop.controller;

import com.chemicalshop.entities.OrderpositionsEntity;
import com.chemicalshop.services.serviceinterface.CartService;
import com.chemicalshop.services.serviceinterface.CategoryService;
import com.chemicalshop.services.serviceinterface.OrderPositionsService;
import com.chemicalshop.services.serviceinterface.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CartController {

    private CartService cartService;
    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public CartController(CartService cartService, ProductService productService, CategoryService categoryService) {
        this.cartService = cartService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @ModelAttribute
    public void addCommonElements(Model model) {
        model.addAttribute("productInCart", cartService.getPositionsCount());
        model.addAttribute("category", categoryService.getAll());
        model.addAttribute("user", getPrincipal());
    }

    @RequestMapping(value="cart/add", method = RequestMethod.POST)
    public ModelAndView addProductToCart(@RequestParam("id") final int productId, @RequestParam("url") final String url) {
        OrderpositionsEntity orderPosition = new OrderpositionsEntity(productService.getProductById(productId));
        cartService.addPosition(orderPosition);
        return new ModelAndView("redirect:" + url);
    }

    @RequestMapping(value="cart/delete", method = RequestMethod.POST)
    public ModelAndView deleteProductFromCart(@RequestParam("id") final int productId, @RequestParam("url") final String url) {
        OrderpositionsEntity orderPosition = new OrderpositionsEntity(productService.getProductById(productId));
        cartService.deletePosition(orderPosition);
        return new ModelAndView("redirect:" + url);
    }

    @RequestMapping(value="cart/delete/{productId}", method = RequestMethod.GET)
    public ModelAndView deleteProductFromCart(@PathVariable("productId") int productId) {
        OrderpositionsEntity orderPosition = new OrderpositionsEntity(productService.getProductById(productId));
        cartService.deletePosition(orderPosition);
        return new ModelAndView("redirect:/cart/view");
    }

    @RequestMapping(value="cart/view", method = RequestMethod.GET)
    public ModelAndView viewCart() {
        ModelAndView modelAndView = new ModelAndView("cart");
        modelAndView.addObject("orderPositions", cartService.getPositions());
        modelAndView.addObject("price", cartService.getPrice());
        for (OrderpositionsEntity orderPosition: cartService.getPositions()) {
            System.out.print(orderPosition.getPositionId());
            System.out.print(orderPosition.getProduct().getProductName());
            System.out.println(orderPosition.getProductCount());
        }
        return modelAndView;
    }

    @RequestMapping(value="cart/increment/{productId}", method = RequestMethod.GET)
    public ModelAndView incrementCount(@PathVariable("productId") final int productId) {
        OrderpositionsEntity orderPosition = new OrderpositionsEntity(productService.getProductById(productId));
        cartService.incrementCount(orderPosition);
        return new ModelAndView("redirect:/cart/view");
    }

    @RequestMapping(value="cart/decrement/{productId}", method = RequestMethod.GET)
    public ModelAndView decrementCount(@PathVariable("productId") final int productId) {
        OrderpositionsEntity orderPosition = new OrderpositionsEntity(productService.getProductById(productId));
        cartService.decrementCount(orderPosition);
        return new ModelAndView("redirect:/cart/view");
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
