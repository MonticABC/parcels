package by.bntu.fitr.povt.services.implement;

import by.bntu.fitr.povt.services.CountryService;
import by.bntu.fitr.povt.dao.entities.Country;
import by.bntu.fitr.povt.dao.interfaces.CountryDao;

import javax.transaction.Transactional;
import java.util.List;


public class CountryServiceImpl implements CountryService {


    private CountryDao countryDao;

    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }


    @Override
    @Transactional
    public List<Country> getList() {
        return countryDao.getList();
    }


    @Override
    @Transactional
    public Country getCountryById(int id) {
        return this.countryDao.getCountryById(id);
    }

    @Override
    @Transactional
    public void save(Country country) {
        countryDao.save(country);
    }

    @Override
    @Transactional
    public int getCount() {
        return countryDao.getCount();
    }

    @Transactional
    @Override
    public Country getByName(String name) {
        return countryDao.getByName(name);
    }


}
