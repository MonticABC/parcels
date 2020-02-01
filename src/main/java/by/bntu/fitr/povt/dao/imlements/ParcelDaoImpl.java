package by.bntu.fitr.povt.dao.imlements;


import by.bntu.fitr.povt.dao.entities.Parcel;
import by.bntu.fitr.povt.dao.interfaces.ParcelDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ParcelDaoImpl implements ParcelDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public void add(Parcel parcel) {
        Session session =sessionFactory.getCurrentSession();
        session.save(parcel);

    }


    @Override
    public void remove(int id) {
        Session session =sessionFactory.getCurrentSession();
        Parcel p = (Parcel) session.load(Parcel.class, new Integer(id));
        if(null != p){
            session.delete(p);
            }
    }


    @Override
    public void update(Parcel parcel) {

        Session session =sessionFactory.getCurrentSession();
        session.update(parcel);
    }


    @Override
    public List<Parcel> getList() {
        Session session = sessionFactory.getCurrentSession();
        List<Parcel> list = session.createQuery("from Parcel ").list();
        return list;
    }


    @Override
    public Parcel getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Parcel parcel = (Parcel) session.get(Parcel.class,id);
        return parcel;
    }

    @Override
    public List<Parcel> getAllParcelsByUserId(int id) {
        Session session = sessionFactory.getCurrentSession();
        List<Parcel> list = session.createQuery("from Parcel where order_id=1 and user_id = :id").setInteger("id",id).list();
        return list;
    }

    @Override
    public List<Parcel> getParcelsByOrderId(int id) {
        Session session = sessionFactory.getCurrentSession();
        List<Parcel> list = session.createQuery("from Parcel where order_id=:id").setInteger("id",id).list();

        return list;
    }

    @Override
    public double getTotalPrice(int userId) {

        Session session = sessionFactory.getCurrentSession();
        Query query =session.createSQLQuery("select sum(total_price) from parcel where user_id=:id and order_id=1").setInteger("id",userId);
        Double count;

        try{
            count = Double.parseDouble( query.uniqueResult().toString());
        } catch (Exception e)
        {
            return 0;
        }
        return count;
    }

    @Override
    public double getTotalWeight(int userId) {
        Session session = sessionFactory.getCurrentSession();
        Query query =session.createSQLQuery("select sum(weight) from parcel where user_id=:id and order_id=1").setInteger("id",userId);
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
