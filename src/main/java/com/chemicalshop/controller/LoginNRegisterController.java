package com.chemicalshop.controller;

import com.chemicalshop.entities.UsersEntity;
import com.chemicalshop.services.serviceinterface.CartService;
import com.chemicalshop.services.serviceinterface.CategoryService;
import com.chemicalshop.services.serviceinterface.UserService;
import com.chemicalshop.services.serviceinterface.UserStatusService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@Controller
public class LoginNRegisterController {

    private CartService cartService;
    private CategoryService categoryService;
    private UserService userService;
    private UserStatusService userStatusService;

    @Autowired
    @Qualifier("userValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(validator);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ModelAttribute("userReg")
    public UsersEntity createOrderModel() {
        return new UsersEntity();
    }

    @Autowired
    public LoginNRegisterController(CartService cartService, CategoryService categoryService, UserService userService,
                                    UserStatusService userStatusService) {
        this.cartService = cartService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.userStatusService = userStatusService;
    }

    @ModelAttribute
    public void addCommonElements(Model model) {
        model.addAttribute("productInCart", cartService.getPositionsCount());
        model.addAttribute("category", categoryService.getAll());
        model.addAttribute("user", getPrincipal());
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ModelAndView logout (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerPage() {
        return new ModelAndView("register");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute("userReg") @Validated UsersEntity user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ModelAndView("register");
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        String hashPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        user.setUserStatus(userStatusService.getStatusById(2));
        userService.addUser(user);
        modelAndView.addObject("registerMessage", "You are registered");
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
