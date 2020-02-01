package by.bntu.fitr.povt.services;

import by.bntu.fitr.povt.dao.entities.Country;

import java.util.*;

public interface CountryService {

    List<Country> getList();
    Country getCountryById(int id);

    void save(Country country);
    int getCount();

    Country getByName(String name);

}
