package com.chemicalshop.controller;

import com.chemicalshop.entities.OrderinfoEntity;
import com.chemicalshop.entities.OrderpositionsEntity;
import com.chemicalshop.entities.UsersEntity;
import com.chemicalshop.services.serviceinterface.*;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;

@Controller
public class CreateOrderController {
    private CartService cartService;
    private OrderPositionsService orderPositionsService;
    private CategoryService categoryService;
    private UserService userService;
    private OrderInfoService orderInfoService;
    private ProductService productService;

    @Autowired
    @Qualifier("orderValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(validator);
    }

    @Autowired
    public CreateOrderController(CartService cartService, OrderPositionsService orderPositionsService, CategoryService categoryService,
                                 UserService userService, OrderInfoService orderInfoService, ProductService productService) {
        this.cartService = cartService;
        this.orderPositionsService = orderPositionsService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.orderInfoService = orderInfoService;
        this.productService = productService;
    }

    @ModelAttribute
    public void addCommonElements(Model model) {
        model.addAttribute("productInCart", cartService.getPositionsCount());
        model.addAttribute("category", categoryService.getAll());
        model.addAttribute("loggedUser", userService.getByUserName(getPrincipal()));
        model.addAttribute("user", getPrincipal());
        model.addAttribute("price", cartService.getPrice());
    }

    @ModelAttribute("order")
    public OrderinfoEntity createOrderModel() {
        return new OrderinfoEntity();
    }

    @RequestMapping(value = "/createorder", method = RequestMethod.GET)
    public ModelAndView orderCreatePage() {
        return new ModelAndView("createorder");
    }

    @RequestMapping(value = "/createorder/guest", method = RequestMethod.POST)
    public ModelAndView makeOrderGuest(@ModelAttribute("order") @Validated OrderinfoEntity order,
                                       BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ModelAndView("createorder");
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/createorder/complete");
        if (order.getSurname().equals(""))
            order.setSurname(null);
        if (order.getEmail().equals(""))
            order.setEmail(null);
        order.setOrderDate(new LocalDate());
        order.setOrderPrice(cartService.getPrice());
        int orderId = orderInfoService.addOrder(order);
        for (OrderpositionsEntity cartOrderPosition : cartService.getPositions()) {
            OrderpositionsEntity orderPosition = new OrderpositionsEntity();
            orderPosition.setOrder(orderInfoService.getById(orderId));
            orderPosition.setProduct(cartOrderPosition.getProduct());
            cartOrderPosition.getProduct().setInStock(cartOrderPosition.getProduct().getInStock() - cartOrderPosition.getProductCount());
            productService.updateProduct(cartOrderPosition.getProduct());
            orderPosition.setProductCount(cartOrderPosition.getProductCount());
            orderPositionsService.addOrderPosition(orderPosition);
        }
        cartService.deleteAllPositions();
        return modelAndView;
    }

    @RequestMapping(value="/createorder/loggedOrder", method = RequestMethod.POST)
    public ModelAndView makeOrderLogged() {
        ModelAndView modelAndView = new ModelAndView("redirect:/createorder/complete");
        OrderinfoEntity order = new OrderinfoEntity();
        order.setOrderDate(new LocalDate());
        order.setOrderPrice(cartService.getPrice());
        order.setUser(userService.getByUserName(getPrincipal()));
        order.setName(order.getUser().getName());
        order.setSurname(order.getUser().getSurname());
        order.setPhoneNumber(order.getUser().getPhoneNumber());
        order.setEmail(order.getUser().getEmail());
        int orderId = orderInfoService.addOrder(order);
        for (OrderpositionsEntity cartOrderPosition : cartService.getPositions()) {
            OrderpositionsEntity orderPosition = new OrderpositionsEntity();
            orderPosition.setProduct(cartOrderPosition.getProduct());
            orderPosition.setOrder(orderInfoService.getById(orderId));
            cartOrderPosition.getProduct().setInStock(cartOrderPosition.getProduct().getInStock() - cartOrderPosition.getProductCount());
            productService.updateProduct(cartOrderPosition.getProduct());
            orderPosition.setProductCount(cartOrderPosition.getProductCount());
            orderPositionsService.addOrderPosition(orderPosition);
        }
        cartService.deleteAllPositions();
        return modelAndView;
    }

    @RequestMapping(value = "/createorder/complete", method = RequestMethod.GET)
    public ModelAndView completeOrder() {
        return new ModelAndView("ordercomplete");
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
