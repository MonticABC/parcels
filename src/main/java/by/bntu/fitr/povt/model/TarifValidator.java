package by.bntu.fitr.povt.model;

import by.bntu.fitr.povt.dao.entities.Tarif;
import by.bntu.fitr.povt.services.TarifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class TarifValidator {
    TarifService tarifService;

    @Autowired
    @Qualifier(value = "tarifService")
    public void setTarifService(TarifService tarifService){
        this.tarifService= tarifService;
    }

    public Tarif validate(String tarifId,String price)
    {
        int id;
        double newPrice;
        Tarif tarif;
        try{
            id= Integer.parseInt(tarifId);
            newPrice = Double.parseDouble(price);
            if(newPrice<=Constans.ZERO || newPrice> Constans.MAX_TARIF_PRICE) {
                return null;
            }
            tarif =tarifService.getById(id);
            tarif.setPrice(newPrice);
        }
        catch (Exception e) {
            return null;
        }
        return tarif;
    }
}
