package by.bntu.fitr.povt.dao.imlements;

import by.bntu.fitr.povt.dao.entities.Role;
import by.bntu.fitr.povt.dao.interfaces.RoleDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class RoleDaoImpl implements RoleDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public Role getRoleById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Role) session.load(Role.class, id);
    }



    @Override
    public List<Role> getList() {
        Session session = sessionFactory.getCurrentSession();
        List<Role> list = session.createQuery("from role ").list();
        return list;
    }
}
