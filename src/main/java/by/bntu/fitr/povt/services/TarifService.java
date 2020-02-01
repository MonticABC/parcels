package by.bntu.fitr.povt.services;

import by.bntu.fitr.povt.dao.entities.Tarif;

import java.util.*;

public interface TarifService {

    Tarif getTarifByName(String sender,String recipient);

    List<Tarif> getList();

    Tarif getById(int id);

    void update(Tarif tarif);

}
