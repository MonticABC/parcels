package by.bntu.fitr.povt.dao.imlements;

import by.bntu.fitr.povt.dao.entities.Tarif;
import by.bntu.fitr.povt.dao.entities.User;
import by.bntu.fitr.povt.dao.interfaces.UserDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }


    @Override
    public void add(User user) {
        Session session =sessionFactory.getCurrentSession();
        session.save(user);
    }


    @Override
    public void remove(int id) {
        Session session =sessionFactory.getCurrentSession();
        User p = (User) session.load(User.class, new Integer(id));
        if(null != p){
            session.delete(p);
        }
    }


    @Override
    public void update(User user) {

        Session session =sessionFactory.getCurrentSession();
        session.update(user);
    }


    @Override
    public List<User> getList() {
        Session session = sessionFactory.getCurrentSession();
        List<User> list = session.createQuery("from User where role =1").list();
        return list;
    }


    @Override
    public User getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User)session.get(User.class,id);
        return user;
    }

    @Override
    public User getByUsername(String username) {
        Query query= sessionFactory.getCurrentSession().
                createQuery("from User where username=:username");
        query.setParameter("username", username);
       User user = (User) query.uniqueResult();
       return user;
    }

    @Override
    public User getByEmail(String email) {
        Query query= sessionFactory.getCurrentSession().
                createQuery("from User where email=:email");
        query.setParameter("email", email);
        User user = (User) query.uniqueResult();
        return user;
    }
}
