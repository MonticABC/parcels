package by.bntu.fitr.povt.dao.imlements;

import by.bntu.fitr.povt.dao.entities.Country;
import by.bntu.fitr.povt.dao.entities.Order;
import by.bntu.fitr.povt.dao.interfaces.OrderDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class OrderDaoImpl implements OrderDao {


    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }


    @Override
    public int add(Order order) {
        Session session =sessionFactory.getCurrentSession();
        session.save(order);

        return order.getId();
    }


    @Override
    public void remove(int id) {
        Session session =sessionFactory.getCurrentSession();
       Order p = (Order) session.load(Order.class, new Integer(id));
        if(null != p){
            session.delete(p);
        }
    }


    @Override
    public void update(Order order) {
        Session session =sessionFactory.getCurrentSession();
        session.update(order);
    }


    @Override
    public List<Order> getList() {
        Session session = sessionFactory.getCurrentSession();
        List<Order> list = session.createQuery("from Order ").list();
        return list;
    }


    @Override
    public Order getById(int id) {

        Session session = sessionFactory.getCurrentSession();
        Order order = (Order) session.get(Order.class,id);
        return order;
    }

    @Override
    public List<Order> getListByUserId(int id) {

        Session session = sessionFactory.getCurrentSession();
        List<Order> list = session.createQuery("from Order where id_user =:id").setInteger("id",id).list();
        return list;
    }

    @Override
    public double getWeightByOrderId(int orderId) {
        Session session = sessionFactory.getCurrentSession();
        Query query =session.createSQLQuery("select sum(weight) from parcel where order_id=:order_id").setInteger("order_id",orderId);
        Double count ;
        try{
            count = Double.parseDouble( query.uniqueResult().toString());
        } catch (Exception e)
        {
            return 0;
        }
        return count;
    }
}
