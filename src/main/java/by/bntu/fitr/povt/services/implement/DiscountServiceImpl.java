package by.bntu.fitr.povt.services.implement;

import by.bntu.fitr.povt.dao.entities.Discount;
import by.bntu.fitr.povt.dao.interfaces.DiscountDao;
import by.bntu.fitr.povt.services.DiscountService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DiscountServiceImpl implements DiscountService{
    public void setDiscountDao(DiscountDao discountDao) {
        this.discountDao = discountDao;
    }

    private DiscountDao discountDao;



    @Override
    @Transactional
    public double getDiscount(String name, int month) {
        return discountDao.getDiscount(name,month);
    }

    @Transactional
    @Override
    public List<Discount> getList() {
        return discountDao.getList();
    }

    @Transactional
    @Override
    public void remove(int id) {
        discountDao.remove(id);

    }

    @Transactional
    @Override
    public void add(Discount discount) {
    discountDao.add(discount);
    }


}
