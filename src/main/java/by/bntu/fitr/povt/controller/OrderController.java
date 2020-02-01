package by.bntu.fitr.povt.controller;

import by.bntu.fitr.povt.dao.entities.Order;
import by.bntu.fitr.povt.dao.entities.Parcel;
import by.bntu.fitr.povt.dao.entities.User;
import by.bntu.fitr.povt.model.Constans;
import by.bntu.fitr.povt.services.OrderService;
import by.bntu.fitr.povt.services.ParcelService;
import java.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class OrderController {
    private static final Logger LOG = Logger.getLogger(OrderController.class);
    private ParcelService parcelService;
    private OrderService orderService;

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

    @RequestMapping(value ="/orders",method = RequestMethod.GET)
    public String listOrders(@ModelAttribute("message")String message,Model model, HttpSession httpSession) {
        double total=0 ;
        User user = (User) httpSession.getAttribute("user");
        LOG.info("user(id:"+ user.getId()+") check all parcel");
        List<Order> orderList = orderService.getListByUserId(user.getId());
        Map<Integer,Double> weights = new HashMap<>();
        for(Order order:orderList) {
            order.setParcelsById(parcelService.getParcelsByOrderId(order.getId()));
            total+=order.getTotalpPrice();
            weights.put(order.getId(),orderService.getWeightByOrderId(order.getId()));
        }

        model.addAttribute("orders", orderList);
        model.addAttribute("weghts",weights);
        model.addAttribute("total", total);
        model.addAttribute("user", user);
        return "orders";
    }

    @RequestMapping(value ="/order/deleteOrder",method = RequestMethod.POST)
    public String deleteOrder(String command,Model model,HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (command==null) {
            return "redirect:/orders";
        }
        Integer id;
        Order order;
        try {
            id= Integer.parseInt(command);
        } catch (Exception e)
        {
            return "redirect:/orders";
        }
        order= orderService.getById(id);
        if(order == null ||order.getIdUser()!=user.getId()) {
            LOG.info("user(id:"+ user.getId()+") try to delete not his order(null order) ");
            return "redirect:/orders";
        }
        List <Parcel> list =parcelService.getParcelsByOrderId(id);
        for(Parcel parcel: list) {
            parcel.setOrderId(Constans.ONE);
            parcelService.update(parcel);
        }
        LOG.info("user(id:"+ user.getId()+") delete parcel with id "  +id );
        orderService.remove(id);
        return "redirect:/orders";
    }
}
