package by.bntu.fitr.povt.services;

import by.bntu.fitr.povt.dao.entities.Discount;

import java.util.List;

public interface DiscountService {
    double getDiscount(String name, int month);
    List<Discount> getList();
    void remove(int id);

    void add(Discount discount);
}
