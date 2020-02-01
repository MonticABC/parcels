package by.bntu.fitr.povt.dao.imlements;

import by.bntu.fitr.povt.dao.entities.Tarif;
import by.bntu.fitr.povt.dao.entities.User;
import by.bntu.fitr.povt.dao.interfaces.TarifDao;
import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class TarifDaoImpl implements TarifDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }


    @Override
    public Tarif getTarifByName(String sender,String recipient) {
        Session session = sessionFactory.getCurrentSession();

        List<Tarif> list = session.createQuery("from Tarif where name like ? and name like ?").setString(0,"%"+sender+"%").setString(1,"%"+recipient+"%").list();
        return list.get(0);
    }

    @Override
    public List<Tarif> getList() {
        Session session = sessionFactory.getCurrentSession();
        List<Tarif> list = session.createQuery("from Tarif ").list();
        return list;
    }

    @Override
    public Tarif getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Tarif tarif = (Tarif) session.get(Tarif.class,id);
        return tarif;
    }

    @Override
    public void update(Tarif tarif) {
        Session session =sessionFactory.getCurrentSession();
        session.update(tarif);
    }


}
