package by.bntu.fitr.povt.dao.interfaces;


import by.bntu.fitr.povt.dao.entities.Country;

import java.util.List;

public interface CountryDao  {
    List<Country> getList();
    Country getCountryById(int id);

    void save(Country country);

    int getCount();

    Country getByName(String name);
}
