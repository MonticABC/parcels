package by.bntu.fitr.povt.dao.imlements;

import by.bntu.fitr.povt.dao.entities.Country;
import by.bntu.fitr.povt.dao.interfaces.CountryDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class CountryDaoImpl implements CountryDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    @Override
    public List<Country> getList() {
        Session session = sessionFactory.getCurrentSession();
        List<Country> list = session.createQuery("from Country ").list();
        return list;
    }

    @Override
    public Country getCountryById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Country tarif = (Country) session.get(Country.class,id);
        return tarif;
    }

    @Override
    public void save(Country country) {
        Session session =sessionFactory.getCurrentSession();
        session.save(country);
    }

    @Override
    public int getCount() {
        Session session =sessionFactory.getCurrentSession();
        Query query =session.createSQLQuery("select count(*) from country");
        Integer count = Integer.parseInt( query.uniqueResult().toString());
        return count;
    }

    @Override
    public Country getByName(String name) {
        Query query= sessionFactory.getCurrentSession().
                createQuery("from Country where country=:name");
        query.setParameter("name", name);

        Country country = (Country) query.uniqueResult();
        return country;
    }

}
