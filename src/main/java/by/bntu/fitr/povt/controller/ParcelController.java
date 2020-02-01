package by.bntu.fitr.povt.controller;


import by.bntu.fitr.povt.dao.entities.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import by.bntu.fitr.povt.model.*;
import by.bntu.fitr.povt.services.*;

import java.util.*;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;

@Controller
public class ParcelController {
    private static final Logger LOG = Logger.getLogger(ParcelController.class);
    private CountryService countryService;
    private TarifService tarifService;
    private ParcelService parcelService;
    private OrderService orderService;
    private CountryValidator countryValidator;
    private ParcelValidator parcelValidator;
    private UserService userService;
    private DiscountService discountService;


    @Autowired
    @Qualifier(value ="discountService")
    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }


    @Autowired
    @Qualifier(value="userService")
    public void setPersonService(UserService userService){
        this.userService = userService;
    }


    @Autowired
    @Qualifier(value="countryService")
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    @Autowired
    @Qualifier(value = "tarifService")
    public void setTarifService(TarifService tarifService){
        this.tarifService= tarifService;
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

    @Autowired
    @Qualifier(value = "countryValidator")
    public void setCountryValidator(CountryValidator countryValidator) {
        this.countryValidator = countryValidator;
    }

    @Autowired
    @Qualifier(value = "parcelValidator")
    public void setParcelValidator(ParcelValidator parcelValidator) {
        this.parcelValidator = parcelValidator;
    }

    @RequestMapping(value = {"/parcel"}, method = RequestMethod.GET)
    public String index(@ModelAttribute("message") String message, @ModelAttribute("message1") String message1, ModelMap model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("countries",countryService.getList());

        if(user!=null) {
            model.addAttribute("weight", Math.round(parcelService.getTotalWeight(user.getId()) * Constans.ROUND) / Constans.ROUND);
            model.addAttribute("price", Math.round(parcelService.getTotalPrice(user.getId()) * Constans.ROUND) / Constans.ROUND);
            List<Parcel> list = parcelService.getAllParcelsByUserId(user.getId());
            model.addAttribute("parcels",list);
        }
        httpSession.setAttribute("parcelId",Constans.ZERO);
        if (message1.length()!= Constans.ZERO) {
            Parcel parcel = parcelValidator.validateId(message1);
            if(parcel!=null) {
                if(parcel.getUserId()!=user.getId()){
                    model.addAttribute("message",Constans.MISSING);
                } else{
                httpSession.setAttribute("parcelId",parcel.getId());
                    httpSession.setAttribute("parcelUp",parcel);
                }
            }
            else {
                model.addAttribute("message",Constans.SELECTED);
            }
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        model.addAttribute("discounts",discountService.getList());
        model.addAttribute("currentDate",dateFormat.format(date));
        return "welcome";
    }

    @RequestMapping(value = "parcel/Parcels", method = RequestMethod.POST)
    public synchronized String addParcel(String countrySender, String countryRecipient, String weightS, String date, String express,String button, Model model,HttpSession httpSession) throws ParseException {
        User user = (User) httpSession.getAttribute("user");
        double weight=0;
        int delivery_time;
        SimpleDateFormat sdf1;
        java.sql.Date sqlDate;
        try{
            weight=Double.parseDouble(weightS);
             sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date1 = sdf1.parse(date);
            sqlDate = new java.sql.Date(date1.getTime());
        }
        catch (Exception e)
        {
            model.addAttribute("message",Constans.INVALID);
            return "redirect:/parcel";
        }
        if( weight<=Constans.ONE ||   weight>=Constans.MAX_WEIGH) {
            model.addAttribute("message",Constans.INVALID);
        return "redirect:/parcel";
        }
        if(date==null || date.length()==Constans.ZERO) {
            model.addAttribute("message",Constans.INVALID);
            return "redirect:/parcel";
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        java.util.Date dateCurrent = new java.util.Date();
        java.util.Date date2 = sdf1.parse(dateFormat.format(dateCurrent));
        if(sqlDate.before(date2)) {
            LOG.info("user try to add parcel with past date");
            model.addAttribute("message",Constans.INVALID);
            return "redirect:/parcel";
        }
        if(countryValidator.validate(countryRecipient).length()!=Constans.ZERO || countryValidator.validate(countrySender).length()!=Constans.ZERO){
            LOG.info("user try to add parcel  bad country");
            model.addAttribute("message",Constans.INVALID);
            return "redirect:/parcel";
        }
        Integer senderId = Integer.parseInt(countrySender);
        Integer recipientId =Integer.parseInt(countryRecipient);
        Country countrySender1 = countryService.getCountryById(senderId);
        Country countryRecipient1 = countryService.getCountryById(recipientId);
        Tarif tarif = tarifService.getTarifByName(countrySender1.getRegion(),countryRecipient1.getRegion());
        double discount = discountService.getDiscount(tarif.getName(),sqlDate.getMonth()+1);
        double total= Math.round(weight*Constans.ROUND*tarif.getPrice()*Constans.ROUND)/Constans.ROUND;
        total-=total*discount;
        delivery_time = tarif.getDeliveryTime();
        Parcel parcel = new Parcel();
        boolean flag = false;
        if(express!=null){
            total*=Constans.EXPRESS_COEFFICIENT;
            flag = true;
            delivery_time=(int)delivery_time*7/10;
        }
        if(user==null)
        {
            button = "price";
        }
        else if(user.getRole()== RoleEnum.ADMIN.ordinal())
        {
            button = "price";
        }

        if(button.equals("price")) {
            Parcel parcelUp = new Parcel();
            parcelUp.setCountrySender(countrySender1.getId());
            parcelUp.setCountryRecipient(countryRecipient1.getId());
            parcelUp.setWeight(weight);
            parcel.setDeliveryTime(delivery_time);
            parcelUp.setDateOfSend(sqlDate);
            parcelUp.setExpress(flag);
            httpSession.setAttribute("parcelUp",parcelUp);
            LOG.info("user check price of parcel");
            model.addAttribute("message","price of the parcel "+Math.round(total*Constans.ROUND)/ Constans.ROUND);
            return "redirect:/parcel";
        }
        model.addAttribute("price","");
        parcel.setCountrySender(senderId);
        parcel.setDateOfSend(sqlDate);
        parcel.setUserId(user.getId());
        parcel.setWeight(weight);
        parcel.setDeliveryTime(delivery_time);
        parcel.setCountryRecipient(recipientId);
        parcel.setTarifId(tarif.getId());
        parcel.setTotalPrice(Math.round(total*Constans.ROUND)/Constans.ROUND);
        parcel.setOrderId(Constans.ONE);
        parcel.setCountryByCountrySender(countrySender1);
        parcel.setCountryByCountryRecipient(countryRecipient1);
        parcel.setTarifByTarifId(tarif);
        parcel.setExpress(flag);
        if ((Integer)httpSession.getAttribute("parcelId")==Constans.ZERO) {
            parcelService.add(parcel);
            LOG.info("user("+user.getId()+") add new parcel" +parcel.toString());
        } else {
            parcel.setId((Integer) httpSession.getAttribute("parcelId"));
            LOG.info("user("+user.getId()+") add new parcel" +parcel.toString());
            parcelService.update(parcel);
        }
        return "redirect:/parcel";
    }

    @RequestMapping(value = "parcel/listParcels",method = RequestMethod.POST,params = "button")
    public synchronized String order(String button,Model model,HttpSession httpSession,String ... checkbox) {
        User user = (User) httpSession.getAttribute("user");
        if(checkbox==null) {
            model.addAttribute("message",Constans.SELECTED);
            return "redirect:/parcel";
        }
        Parcel parcel;
        List<Integer> list = parcelValidator.validation(checkbox);
        if (list.size()!=checkbox.length) {
            model.addAttribute("message",Constans.INVALID);
            return "redirect:/parcel";
        }
        try {
            if(button.equals("Delete")) {
                for(int che:list) {
                    parcel = parcelService.getById(che);
                    if (parcel.getUserId() != user.getId()){
                        LOG.info("user("+user.getId()+") try to delete not his parcel");
                        model.addAttribute("message",Constans.MISSING);
                        return "redirect:/parcel";
                    }
                    LOG.info("user("+user.getId()+") delete parcel with id "+che );
                    parcelService.remove(che);
                }
                return "redirect:/parcel";
            }
        }
        catch (Exception e)
        {
            LOG.info("user("+user.getId()+") delete parcel with with bad input ");
            return "redirect:/parcel";
        }
        double price = 0;

        try {
            for (int che : list) {
                parcel = parcelService.getById(che);
                if (parcel.getUserId() != user.getId()) {
                    LOG.info("user("+user.getId()+") try to add in order not his parcel");
                    model.addAttribute("message",Constans.MISSING);
                    return "redirect:/parcel";
            }
                price += parcel.getTotalPrice();
            }
        }
        catch (Exception e)
        {
            LOG.info("user("+user.getId()+") add in order parcel with with bad input ");
            return "redirect:/parcel";
        }
        Order order = new Order();
        order.setTotalpPrice(price);
        order.setIdUser(user.getId());
        int orderId = (orderService.add(order));
        StringBuilder stringBuilder = new StringBuilder();
        for(int che:list) {
            parcel= parcelService.getById(che);
            parcel.setOrderId(orderId);
            stringBuilder.append(parcel.toString()+". \n");
            LOG.info("user("+user.getId()+") add parcel with id "+che+" in order with id" +orderId);
            parcelService.update(parcel);
        }
        MailSender.send(userService.getById(Constans.ONE).getEmail(),"User  = "+ user.getUsername()+" create new order. Total price: "+price+". \nParcels: "+stringBuilder.toString(),"Create order" );
        MailSender.send(user.getEmail(),"Create new order. Total price: "+price+".Parcels: "+stringBuilder.toString(),"Create order");
        return "redirect:/parcel";
    }
    @RequestMapping(value = "parcel/listParcels",method = RequestMethod.POST,params ="listOrders")
    public String listOrder(Model model)
    {
        return "redirect:/orders";
    }

    @RequestMapping(value = "parcel/listParcels",method = RequestMethod.POST)
    public String defaultMethod(Model model)
    {
        return "redirect:/parcel";
    }

    @RequestMapping(value = "parcel/listParcels",method = RequestMethod.POST,params ="update")
    public String update(Model model,String ... checkbox)
    {
        if(checkbox==null) {
            model.addAttribute("message",Constans.SELECTED);
            return "redirect:/parcel";
        }
        if(checkbox.length>Constans.ONE) {
            model.addAttribute("message",Constans.SELECT_ONE);
        }
        else {
            model.addAttribute("message1",checkbox[0]);
        }
        return "redirect:/parcel";

    }
}

