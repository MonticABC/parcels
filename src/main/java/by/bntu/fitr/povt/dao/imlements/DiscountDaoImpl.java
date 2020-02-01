package by.bntu.fitr.povt.dao.imlements;

import by.bntu.fitr.povt.dao.entities.Discount;
import by.bntu.fitr.povt.dao.interfaces.DiscountDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class DiscountDaoImpl implements DiscountDao {
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public double getDiscount(String name, int month) {

        name = name.trim();
        String[] arr= name.split("-");
        String st = arr[1]+"-"+arr[0];

        Session session = sessionFactory.getCurrentSession();
        Double dis ;
      Query query =session.createSQLQuery("select dis from  discount where nameT like ? and month=?");
      query.setString(0,"%"+ name+"%");
      query.setInteger(1,month);
      Query query1 =session.createSQLQuery("select dis from  discount where nameT like ?  and month=?");
query1.setString(0,"%"+ st+"%");
      query1.setInteger(1,month);
      try {
            dis= Double.parseDouble(query.uniqueResult().toString());
       }
       catch (Exception e)
       {
           dis = 0.0;
       }

       if(dis!=0.0)
       {
           return dis;
       }

        try {
            dis= Double.parseDouble(query1.uniqueResult().toString());
        }
       catch (Exception e)
        {
            dis = 0.0;
        }

        if(dis!=0)
        {
            return dis;
        }

       return 0;

    }

    @Override
    public List<Discount> getList() {
        Session session = sessionFactory.getCurrentSession();
        List<Discount> list = session.createQuery("from Discount").list();

        return list;
    }

    @Override
    public void remove(int id) {
        Session session =sessionFactory.getCurrentSession();
        Discount p = (Discount) session.load(Discount.class, new Integer(id));
        if(null != p){
            session.delete(p);
        }

    }

    @Override
    public void add(Discount discount) {
        Session session =sessionFactory.getCurrentSession();
        session.save(discount);
    }


}
