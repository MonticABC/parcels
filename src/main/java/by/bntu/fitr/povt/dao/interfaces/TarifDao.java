package by.bntu.fitr.povt.dao.interfaces;

import by.bntu.fitr.povt.dao.entities.Tarif;


import java.util.List;

public interface TarifDao{

    Tarif getTarifByName(String sender,String recipient);

    List<Tarif> getList();

    Tarif getById(int id);

    void update(Tarif tarif);

}
