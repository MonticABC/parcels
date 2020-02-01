package by.bntu.fitr.povt.model;

import by.bntu.fitr.povt.dao.entities.Parcel;
import by.bntu.fitr.povt.services.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;

import java.util.ArrayList;

public class ParcelValidator {

    private ParcelService parcelService;

    @Autowired
    @Qualifier(value = "parcelService")
    public void setParcelService(ParcelService parcelService) {
        this.parcelService = parcelService;
    }


    public List<Integer> validation(String ... arr)
    {
        List<Integer> list = new ArrayList<>();
        for(String string :arr) {
            try {
                list.add(Integer.parseInt(string));
            }
            catch (Exception e)
            {
            }
        }
        return list;

    }


    public Parcel validateId(String id)
    {
        Parcel parcel;
        try{
            Integer idI = Integer.parseInt(id);
            parcel = parcelService.getById(idI);
        }
        catch (Exception e)
        {
            return null;
        }
        return parcel;
    }
}
