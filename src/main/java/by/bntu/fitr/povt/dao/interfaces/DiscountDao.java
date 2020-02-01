package by.bntu.fitr.povt.dao.interfaces;

import by.bntu.fitr.povt.dao.entities.Discount;
import java.util.*;

public interface DiscountDao {
     double getDiscount(String name,int month);

     List<Discount> getList();

     void remove(int id);

     void add(Discount discount);
}
