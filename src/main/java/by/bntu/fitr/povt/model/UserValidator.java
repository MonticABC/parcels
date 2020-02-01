package by.bntu.fitr.povt.model;

import by.bntu.fitr.povt.dao.entities.User;
import by.bntu.fitr.povt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UserValidator  {

    private UserService userService;

    @Autowired
    @Qualifier(value="userService")
    public void setPersonService(UserService ps){
        this.userService = ps;
    }

    public String validateAdd(Object o) {
        User user = (User) o;

        User userHelp;

        if (user.getUsername().length() < 8 || user.getUsername().length() > 32) {
            return Constans.ERROR_LEN_USERNAME;
        }

        if(!user.getUsername().matches("^[a-zA-Z0-9_]+$"))
        {
            return Constans.ERROR_PATTERN;
        }

        if(!user.getName().matches("^[a-zA-Z0-9-]+$"))
        {
            return Constans.ERROR_PATTERN;
        }

        if(!user.getSurname().matches("^[a-zA-Z0-9-]+$"))
        {
            return Constans.ERROR_PATTERN;
        }


        userHelp = userService.getByUsername(user.getUsername());


        if(userHelp!=null){
            if(userHelp.getId()!=user.getId()) {
                return Constans.DUPLICATE_USERNAME;
            }
        }

        userHelp = userService.getByEmail(user.getEmail());



        if(userHelp!=null ) {
            if(userHelp.getId()!=user.getId()) {
            return Constans.DUPLICATE_EMAIL;
            }
        }

        if(!user.getPhone().matches("^\\+375 \\((17|29|33|44)\\) [0-9]{7}$")) {
            return Constans.ERROR_PATTERN;
        }

        if(user.getName().length()<2 || user.getName().length()>90 ) {
            return Constans.ERROR_NAME;
        }

        if(user.getSurname().length()<2 || user.getSurname().length()>90 ) {
            return Constans.ERROR_SURNAME;
        }

        if(!user.getEmail().contains("@") || user.getEmail().indexOf("@")== (user.getEmail().length()-1)) {
            return Constans.ERROR_EMAIL;
        }
        /*if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            st+="Error len password. ";
            return st;
        }
        if (!user.getConfirmPassword().equals(user.getPassword())) {
                st += "confirmPassword. ";
                return st;
        }*/
        return "";
    }

    public String validatePassword(String password)
    {
        if(password.length()<8 || password.length()>32)
        {
            return Constans.ERROR_LEN_PASSWORD;
        }

        if(!password.matches("^[a-zA-Z0-9_]+$"))
        {
            return Constans.ERROR_PATTERN;
        }
        return "";
    }

    public User validateCommand(String command)
    {
        int id ;
        try {
            id=Integer.parseInt(command);
        }
        catch (Exception e)
        {
            return null;
        }
        return userService.getById(id);
    }
}
