package by.bntu.fitr.povt.controller;

import by.bntu.fitr.povt.dao.entities.*;
import by.bntu.fitr.povt.model.Constans;
import by.bntu.fitr.povt.model.CountryValidator;
import by.bntu.fitr.povt.model.TarifValidator;
import by.bntu.fitr.povt.model.UserValidator;
import by.bntu.fitr.povt.services.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
    private static final Logger LOG = Logger.getLogger(AdminController.class);
    private TarifValidator tarifValidator;
    private CountryValidator countryValidator;
    private UserValidator userValidator;
    private TarifService tarifService;
    private UserService userService;
    private CountryService countryService;
    private OrderService orderService;
    private ParcelService parcelService;
    private DiscountService discountService;
    private BCryptPasswordEncoder encoder;

    @Autowired
    @Qualifier(value = "encoder")
    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Autowired
    @Qualifier(value ="discountService")
    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }

    @Autowired
    @Qualifier(value = "tarifValidator")
    public void setTarifValidator(TarifValidator tarifValidator) {
        this.tarifValidator = tarifValidator;
    }

    @Autowired
    @Qualifier(value = "countryValidator")
    public void setCountryValidator(CountryValidator countryValidator) {
        this.countryValidator = countryValidator;
    }

    @Autowired()
    @Qualifier(value="userValidator")
    public void setPersonService(UserValidator ps){
        this.userValidator = ps;
    }

    @Autowired
    @Qualifier(value = "tarifService")
    public void setTarifService(TarifService tarifService){
        this.tarifService= tarifService;
    }

    @Autowired()
    @Qualifier(value="userService")
    public void setPersonService(UserService userService){
        this.userService = userService;
    }

    @Autowired
    @Qualifier(value = "countryService")
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    @Autowired
    @Qualifier(value = "parcelService")
    public void setParcelService(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    @Autowired
    @Qualifier(value = "orderService")
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }


    @RequestMapping(value = "/administratorDiscount",method = RequestMethod.GET)
    public String adminDiscount(@ModelAttribute("message")String message, Model model){
        List<Discount> discounts = discountService.getList();
        model.addAttribute("discounts", discounts);
        return "adminDiscount";
    }


    @RequestMapping(value ="/adminDeleteDiscount",method = RequestMethod.POST)
    public String adminDeleteDiscount(String hid, Model model)
    {
        int id;
        try {
            id = Integer.parseInt(hid);
            discountService.remove(id);
        }
        catch (Exception e)
        {
            model.addAttribute("message","Error deleting");
            return "redirect:/administratorDiscount";
        }


        return "redirect:/administratorDiscount";
    }


    @RequestMapping(value ="/administratorAddDiscount",method = RequestMethod.POST)
    public synchronized String adminAddDiscount(String regionS,String regionR,String month,String discount, Model model)
    {
       double discountI;
        int monthI;
        try {
            monthI= Integer.parseInt(month);
            discountI =Double.parseDouble(discount);
        } catch (Exception e) {
            model.addAttribute("message",Constans.INVALID);
            return "redirect:/administratorDiscount";
        }
        if (monthI<Constans.ONE || monthI> Constans.MONTH_COUNT || discountI>Constans.MAX_DISCOUNT || discountI<Constans.ONE) {
            model.addAttribute("message",Constans.INVALID);
            return "redirect:/administratorDiscount";
        }

        String name = " "+regionS+"-"+regionR+" ";
        double dis = discountService.getDiscount(name,monthI);
        if(dis!=Constans.ZERO) {
            model.addAttribute("message",Constans.DUPLICATE_DISCOUNT);
            return "redirect:/administratorDiscount";
        }
        Discount discountO = new Discount();
        discountO.setDis(discountI/Constans.HUNDRED);
        discountO.setMonth(monthI);
        discountO.setNameT(name);
        discountService.add(discountO);
        return "redirect:/administratorDiscount";
    }

    @RequestMapping(value = "/administratorCountry",method = RequestMethod.GET)
    public String adminCountry( @ModelAttribute("message")String message,Model model){
        List<Country> list = countryService.getList();
        LOG.info("administrator check all countries");
        model.addAttribute("countries",list);

        model.addAttribute("countryO",new Country());
        return "adminCountry";
    }

    @RequestMapping(value = "/administratorUpdateCountry",method = RequestMethod.POST)
    public synchronized String adminAddCountry(@ModelAttribute("countryO")Country country,Model model){
        LOG.info("administrator add new Country:"+country.getCountry()+","+country.getRegion());
        String errorText= countryValidator.validateName(country.getCountry());
        if(errorText.length()!=Constans.ZERO) {
            model.addAttribute("message", errorText);
            return "redirect:/administratorCountry";
        }
        if (countryValidator.regionValidate(country.getRegion())) {
            try{
                countryService.save(country);
            }
            catch (Exception e)
            {
            }
        } else {
            model.addAttribute("message", Constans.ERROR_REGION);
        }
        return "redirect:/administratorCountry";
    }


    @RequestMapping(value = "/administratorTarif",method = RequestMethod.GET)
    public String adminTarif(@ModelAttribute("message")String message, Model model,HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        List<Tarif> list =tarifService.getList();
        LOG.info("administrator check all tarifs");
        model.addAttribute("tarifs",list);
        model.addAttribute("user",user);
        return "adminTarif";
    }

    @RequestMapping(value ="/administratorTarif",method = RequestMethod.POST)
    public synchronized String UpdateTarif(String tarif,String tarifId,Model model)
    {
        Tarif tarifO = tarifValidator.validate(tarifId,tarif);
        if(tarifO!=null) {
            tarifService.update(tarifO);
            LOG.info("administrator update tarif("+tarifO.getId()+"), new price is "+tarifO.getPrice());
        }
        else {
            model.addAttribute("message", Constans.INVALID);
        }
        return "redirect:/administratorTarif";
    }

    @RequestMapping(value ="/administratorUser",method = RequestMethod.GET)
    public String adminUser(@ModelAttribute("password") String password,
                            @ModelAttribute("userIdOrder") String userIdOrder,
                            @ModelAttribute("userIdParcel") String userIdParcel,
                            @ModelAttribute("message") String message,
                                    Model model) {
     if(userIdParcel.length()!=Constans.ZERO) {
         List<Parcel> parcels = parcelService.getAllParcelsByUserId(Integer.parseInt(userIdParcel));
         if(parcels.size()==Constans.ZERO) {
             model.addAttribute("message",Constans.NO_PARCELS);
         }
        model.addAttribute("parcels", parcels);
    }
    if(userIdOrder.length()!=Constans.ZERO) {
         List<Order> orderList = orderService.getListByUserId(Integer.parseInt(userIdOrder));


        if(orderList.size()==Constans.ZERO)
        {
            model.addAttribute("message",Constans.NO_ORDERS);

        }
         for(Order order:orderList) {
             order.setParcelsById(parcelService.getParcelsByOrderId(order.getId()));
         }
         model.addAttribute("orders",orderList);
        }
        List<User> list =userService.getList();
        LOG.info("administrator check all users");
        model.addAttribute("users",list);
        model.addAttribute("user",new User());

        model.addAttribute("countries",countryService.getList());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        model.addAttribute("currentDate",dateFormat.format(date));
        return "adminUser";
    }

    @RequestMapping(value ="/administratorUser",method = RequestMethod.POST,params ="delete")
    public String adminAction(String command,Model model)
    {
        LOG.info("administrator delete user with id "+command);
        try {
            userService.remove(Integer.parseInt(command));
        } catch (Exception e)
        {
            model.addAttribute("message",Constans.DELETE_USER);
        }
        return "redirect:/administratorUser";
    }



    @RequestMapping(value ="/administratorUser",method = RequestMethod.POST,params ="update")
    public synchronized String updatePassword(String command,Model model)
    {
        User user=userValidator.validateCommand(command);
        if(user == null) {
            model.addAttribute("message",Constans.SELECTED);
            return"redirect:/administratorUser";
        }

        user.setPassword(encoder.encode("qwertyuiop"));
        userService.update(user);
        model.addAttribute("message",Constans.UPDTE_PASSWORD);
        return "redirect:/administratorUser";
    }

    @RequestMapping(value ="/administratorUser",method = RequestMethod.POST,params ="parcels")
    public String checkParcels(String command,Model model)
    {
        LOG.info("administrator check all parcels of user with user id:"+ command);
        User user=userValidator.validateCommand(command);
        if(user == null) {
            model.addAttribute("message",Constans.SELECTED);
            return"redirect:/administratorUser";
        }
        model.addAttribute("userIdParcel",user.getId());
        return "redirect:/administratorUser";
    }

    @RequestMapping(value ="/administratorUser",method = RequestMethod.POST,params ="orders")
    public String checkOrders(String command,Model model)
    {
        LOG.info("administrator check all orders of user with user id:"+ command);
        User user=userValidator.validateCommand(command);
        if(user == null){
            model.addAttribute("message",Constans.SELECTED);
            return"redirect:/administratorUser";
        }
        model.addAttribute("userIdOrder",user.getId());
        return "redirect:/administratorUser";
    }

    @RequestMapping(value ="/administratorUser",method = RequestMethod.POST)
    public String defaultMethod()
    {
        return "redirect:/administratorUser";
    }


    @RequestMapping(value ="/logout",method = RequestMethod.POST,params = "logout")
    public String logout(HttpSession httpSession)
    {
        User user = (User) httpSession.getAttribute("user");
        LOG.info("logout user id is "+user.getId());
        httpSession.setAttribute("user",null);
        return "redirect:/index";
    }

    @RequestMapping(value ="/logout",method = RequestMethod.POST)
    public String defaultLogout(HttpSession httpSession, Model model)
    {
        httpSession.setAttribute("user", null);
        return "redirect:/index";
    }


    @RequestMapping(value ="/logout",method = RequestMethod.POST,params = "login")
    public String login()
    {
        return "redirect:/index";
    }


    @RequestMapping(value = "/administratorUpdateParcel", method = RequestMethod.POST)
    public synchronized String addParcel(Model model,String countrySender, String countryRecipient, String weightS, String date, String express,String id, String userId) throws ParseException {
        double weight ;
        SimpleDateFormat sdf1;
        java.sql.Date sqlDate;
        int idU;
        int idUser,deliveryTime;
        try {
            weight = Double.parseDouble(weightS);
            sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date1 = sdf1.parse(date);
            sqlDate = new java.sql.Date(date1.getTime());
            idU=Integer.parseInt(id);
            idUser=Integer.parseInt(userId);
        } catch (Exception e) {
            model.addAttribute("message",Constans.INVALID);
            return "redirect:/administratorUser";
        }
        if (weight <= Constans.ZERO || date == null || date.length() == Constans.ZERO || weight > Constans.MAX_WEIGH) {
            model.addAttribute("message",Constans.INVALID);
            return "redirect:/administratorUser";
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        java.util.Date dateCurrent = new java.util.Date();
        java.util.Date date2 = sdf1.parse(dateFormat.format(dateCurrent));
        if (sqlDate.before(date2)) {
            model.addAttribute("message",Constans.DATE_BEFORE);
            return "redirect:/administratorUser";
        }
        if (countryValidator.validate(countryRecipient).length() != Constans.ZERO || countryValidator.validate(countrySender).length() != Constans.ZERO) {
            model.addAttribute("message",Constans.INVALID);
            return "redirect:/administratorUser";
        }
        Integer senderId = Integer.parseInt(countrySender);
        Integer recipientId = Integer.parseInt(countryRecipient);
        Country countrySender1 = countryService.getCountryById(senderId);
        Country countryRecipient1 = countryService.getCountryById(recipientId);
        Tarif tarif = tarifService.getTarifByName(countrySender1.getRegion(), countryRecipient1.getRegion());
        double total = Math.round(weight * Constans.ROUND * tarif.getPrice() * Constans.ROUND) / Constans.ROUND;
        deliveryTime = tarif.getDeliveryTime();
        Parcel parcel = new Parcel();
        boolean flag = false;
        if (express != null) {
            total *= Constans.EXPRESS_COEFFICIENT;
            flag = true;
            deliveryTime= (int)deliveryTime*7/10;
        }
        parcel.setId(idU);
        parcel.setCountrySender(senderId);
        parcel.setDateOfSend(sqlDate);
        parcel.setUserId(idUser);
        parcel.setWeight(weight);
        parcel.setDeliveryTime(deliveryTime);
        parcel.setCountryRecipient(recipientId);
        parcel.setTarifId(tarif.getId());
        parcel.setTotalPrice(total);
        parcel.setOrderId(Constans.ONE);
        parcel.setCountryByCountrySender(countrySender1);
        parcel.setCountryByCountryRecipient(countryRecipient1);
        parcel.setTarifByTarifId(tarif);
        parcel.setExpress(flag);
        parcelService.update(parcel);
        return "redirect:/administratorUser";
    }
}
