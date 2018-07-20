package com.chemicalshop.controller;

import com.chemicalshop.entities.CategoryEntity;
import com.chemicalshop.entities.ProductsEntity;
import com.chemicalshop.entities.UsersEntity;
import com.chemicalshop.services.serviceinterface.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    private CategoryService categoryService;
    private CartService cartService;
    private OrderInfoService orderInfoService;
    private UserStatusService userStatusService;
    private UserService userService;

    @ModelAttribute
    public void addCommonElements(Model model) {
        model.addAttribute("productInCart", cartService.getPositionsCount());
        model.addAttribute("category", categoryService.getAll());
        model.addAttribute("cart",cartService.getAllId());
        model.addAttribute("orders", orderInfoService.getAllOrders());
        model.addAttribute("user", getPrincipal());
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("adminValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(validator);
    }

    @ModelAttribute("adminReg")
    public UsersEntity createUserModel() {
        return new UsersEntity();
    }

    @Autowired
    public AdminController(CategoryService categoryService,
                           CartService cartService, OrderInfoService orderInfoService,
                           UserStatusService userStatusService, UserService userService) {
        this.categoryService = categoryService;
        this.cartService = cartService;
        this.orderInfoService = orderInfoService;
        this.userStatusService = userStatusService;
        this.userService = userService;
    }

    @RequestMapping(value = "/createAdmin", method = RequestMethod.GET)
    public ModelAndView createAdminPage() {
        return new ModelAndView("createadmin");
    }

    @RequestMapping(value = "/createAdmin", method = RequestMethod.POST)
    public ModelAndView createAdmin(@ModelAttribute("adminReg") @Validated UsersEntity admin, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ModelAndView("createadmin");
        }
        ModelAndView modelAndView =  new ModelAndView("redirect:/createAdmin");
        String hashPassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(hashPassword);
        admin.setUserStatus(userStatusService.getStatusById(1));
        admin.setPhoneNumber("+375172228877");
        admin.setEmail(null);
        admin.setName(admin.getLogin());
        admin.setSurname(null);
        userService.addUser(admin);
        return modelAndView;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ModelAndView showOrdersPage() {
        return new ModelAndView("orders");
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
