package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {

    @Autowired 
    private UserService userService;

    @RequestMapping("/home")
    public String home(Model model){
        System.out.println("This is the homepage");
        
        //Sending dynamic data to returned view page
        model.addAttribute("name", "Techgeering");
        model.addAttribute("website", "https://www.techgeering.com");
        return "home";
    }

    @RequestMapping("/about")
    public String aboutPage() {
        System.out.println("About Page Loading....");
        return "about";
    }

    @RequestMapping("/services")
    public String servicesPage() {
        System.out.println("Services Page Loading....");
        return "services";
    }

    @RequestMapping("/contactus")
    public String contactPage() {
        System.out.println("Contactus Page Loading....");
        return "contactus";
    }

    @RequestMapping("/register")
    public String regsiterPage(Model model) {
        UserForm userForm = new UserForm();
        // default data bhi daal sakte hai
        // userForm.setName("Durgesh");
        // userForm.setAbout("This is about : Write something about yourself");
        model.addAttribute("userForm", userForm);
        System.out.println("SignUp Page Loading....");
        return "register";
    }

    @RequestMapping("/login")
    public String loginPage() {
        System.out.println("SignIn Page Loading....");
        return "login";
    }
    
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session) {
        System.out.println("Processing request for registration...");
        System.out.println(userForm);
        
        // validate form data
        if (rBindingResult.hasErrors()) {
            return "register";
        }
        
        //UserForm--> User
        // User user = User.builder()
        // .userId(UUID.randomUUID().toString())  // Generate UUID here
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic("https://w7.pngwing.com/pngs/81/570/png-transparent-profile-logo-computer-icons-user-user-blue-heroes-logo.png")
        // .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setEnabled(false);
        user.setProfilePic("https://w7.pngwing.com/pngs/81/570/png-transparent-profile-logo-computer-icons-user-user-blue-heroes-logo.png");
        User savedUser = userService.saveUser(user);

        System.out.println("user saved successfully-------------------------------------------------------------------------------------------------------:" + savedUser);
        // add the message:

        // Message message = Message.builder().content("Registration Successful").type(MessageType.blue).build();
        // session.setAttribute("message", message);
        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
        session.setAttribute("message", message);
        return "redirect:/register";
    }

}
