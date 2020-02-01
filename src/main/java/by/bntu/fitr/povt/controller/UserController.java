package by.bntu.fitr.povt.controller;

import by.bntu.fitr.povt.dao.entities.Tarif;
import by.bntu.fitr.povt.dao.entities.User;
import by.bntu.fitr.povt.model.*;
import by.bntu.fitr.povt.services.TarifService;
import by.bntu.fitr.povt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.util.Properties;

import static java.lang.Thread.sleep;

@Controller
public class UserController {
    private static final Logger LOG = Logger.getLogger(UserController.class);
    private UserValidator userValidator;
    private UserService userService;
    BCryptPasswordEncoder encoder;

    private TarifService tarifService;
    @Autowired
    @Qualifier(value = "tarifService")
    public void setTarifService(TarifService tarifService){
        this.tarifService= tarifService;
    }

    @Autowired
    @Qualifier(value = "encoder")
    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Autowired
    @Qualifier(value="userValidator")
    public void setPersonService(UserValidator ps){
        this.userValidator = ps;
    }

    @Autowired
    @Qualifier(value="userService")
    public void setPersonService(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
    public String index(@ModelAttribute("message") String message,Model model) {
        model.addAttribute("user",new User());
        return "index";
    }

    @RequestMapping(value = "parcelupdatePassword", method = RequestMethod.POST)
    public synchronized String updatePassword(String password,String confirmPassword,Model model,HttpSession httpSession) {
        User user = (User)httpSession.getAttribute("user");
        String string = "redirect:/administratorCountry";
        if(user.getRole()==RoleEnum.USER.ordinal()) {
            string="redirect:/parcel";
        }
        if(!password.equals(confirmPassword)) {
            model.addAttribute("message","Confirm password");
            return string ;
        }

        String message = userValidator.validatePassword(password);
        if(message.length()!= Constans.ZERO) {
            model.addAttribute("message",message);
            return string;
        }
        user.setPassword(encoder.encode(password));
        userService.update(user);
        model.addAttribute("message","password successfully updated");
        return string;
    }

    @RequestMapping(value = {"/Login", "/"}, method = RequestMethod.POST)
    public String login(@ModelAttribute("user")User user, HttpSession httpSession, Model model ) {
        User userByUsername = userService.getByUsername(user.getUsername());
        if(userByUsername!=null) {
            if(encoder.matches(user.getPassword(),userByUsername.getPassword())) {
                userByUsername.setConfirmPassword(user.getPassword());
                httpSession.setAttribute("user",userByUsername);
               LOG.info("authorization user:" +user.getUsername() +"," +user.getPassword());
                if(userByUsername.getRole()==RoleEnum.USER.ordinal()) {
                    return "redirect:/parcel";
                }
                else {
                    return "redirect:/administratorCountry";
                }
            }
            else {
                model.addAttribute("message","incorrect password");
            }
        } else {
            model.addAttribute("message","account is missing");
        }
        return "redirect:/index";
    }

    @RequestMapping(value ="/registration", method = RequestMethod.GET)
    public String registration(Model model) {
       model.addAttribute("user",new User());


        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public synchronized String registration(@ModelAttribute("user") User user, Model model) {
        String st = userValidator.validateAdd(user);
        if (st.length()!=Constans.ZERO) {
            model.addAttribute("message",st);
            return "registration";
        }
        String password = PasswordGenerator.generate(Constans.PASSWORD_COUNT);
        user.setPassword(password);
        user.setRole(RoleEnum.USER.ordinal());
        try{
            userService.add(user);
            MailSender.send(user.getEmail(),"your password = '"+password+"'.","Create password");
        }
        catch (Exception e)
        {

        }
        LOG.info("registration user:" +user.getUsername() +"," +user.getPassword());
        return "redirect:/index";
    }

    @RequestMapping(value ="/ordersinformation", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") User user,HttpSession httpSession,Model model) {
        User userHttp = (User) httpSession.getAttribute("user");
        User userById = userService.getById(userHttp.getId());
        user.setId(userHttp.getId());
        user.setRole(userHttp.getRole());
        user.setPassword(userById.getPassword());
        String st = userValidator.validateAdd(user);
        if(st.length()==Constans.ZERO) {
            httpSession.setAttribute("user",user);
            userService.update(user);
        }
        else {
            model.addAttribute("message",st);
        }
        if(user.getRole()==RoleEnum.ADMIN.ordinal()) {
            return "redirect:/administratorTarif";
        }
        return "redirect:/orders";
    }

    @RequestMapping(value ="/administratorUpdateUser", method = RequestMethod.POST)
    public synchronized String updateUserByAdmin(@ModelAttribute("user") User user,String radio,Model model,HttpSession httpSession) {
        String string = userValidator.validateAdd(user);
        if(string.length()!=Constans.ZERO) {
            model.addAttribute("message",string);
            return "redirect:/administratorUser";
        }

        if(user.getId()==Constans.ZERO) {
            String password = PasswordGenerator.generate(Constans.PASSWORD_COUNT);
            user.setPassword(password);
            if(radio!=null) {
                user.setRole(RoleEnum.ADMIN.ordinal());
            } else {
                user.setRole(RoleEnum.USER.ordinal());
            }


            try{
                userService.add(user);
                MailSender.send(user.getEmail(),"your password = '"+password+"'.","Password");
            }
            catch (Exception e)
            {
            }
            LOG.info("registration user:" +user.getUsername() +"," +user.getPassword());
        } else {

          User userHelp = userService.getById(user.getId());
          user.setRole(userHelp.getRole());
          user.setPassword(userHelp.getPassword());
          userService.update(user);
        }
        return "redirect:/administratorUser";
    }


    @RequestMapping(value ="/ordersMessage", method = RequestMethod.POST)
    public String message(String comment,HttpSession httpSession,Model model)
    {
        System.out.println(comment);
        if(comment.length()==0 || !comment.matches("^[a-zA-Z0-9\\s.!?;:]+$") || comment.length()>500)
        {
            model.addAttribute("message",Constans.INVALID);
            return "redirect:/orders";
        }


        User user = (User)httpSession.getAttribute("user");
        MailSender.send(userService.getById(1).getEmail(), user.getUsername()+"("+user.getEmail()+")" +" write - "+comment,"support");

        return "redirect:/orders";
    }
}
