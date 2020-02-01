package by.bntu.fitr.povt.model;

import by.bntu.fitr.povt.dao.entities.Country;
import by.bntu.fitr.povt.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.Date;

public class CountryValidator {
    private CountryService countryService;

    @Autowired
    @Qualifier(value = "countryService")
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }


    public String validateName(String name){
        if(!name.matches("^[a-zA-Z0-9-]+$"))
        {
            return Constans.ERROR_PATTERN;
        }

        if(name.length()<4 || name.length()>48)
        {
            return Constans.ERROR_LEN_COUNTRY;
        }

        if(countryService.getByName(name)!=null)
        {
            return Constans.DUPLICATE_COUTRY;
        }
        return  "";
    }
/*
* ALTER TABLE `parcels`.`country`
CHANGE COLUMN `country` `country` VARCHAR(50) NOT NULL ;*/
    public String validate(String countryId)
    {
        String st ="";
        Integer id;
        try {
             id= Integer.parseInt(countryId);
        }
        catch (Exception e) {
            return  Constans.INVALID;
        }
        if(id<1 || id> countryService.getCount()){
            return  Constans.INVALID;
        }
        return st;
    }

    public boolean regionValidate(String region)
    {
        try {
            region = region.replace(' ','_');
            Regions.valueOf(region.toUpperCase());

        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }

}
