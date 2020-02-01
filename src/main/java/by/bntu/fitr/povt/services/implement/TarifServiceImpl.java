package by.bntu.fitr.povt.services.implement;

import by.bntu.fitr.povt.services.TarifService;
import by.bntu.fitr.povt.dao.entities.Tarif;
import by.bntu.fitr.povt.dao.interfaces.TarifDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TarifServiceImpl implements TarifService {

    TarifDao tarifDao;



    public void setTarifDao(TarifDao tarifDao) {
        this.tarifDao = tarifDao;
    }

    @Override
    @Transactional
    public Tarif getTarifByName(String sender,String recipient) {
        return tarifDao.getTarifByName(sender,recipient);
    }

    @Override
    @Transactional
    public List<Tarif> getList() {
        return tarifDao.getList();
    }

    @Override
    @Transactional
    public Tarif getById(int id) {
        return tarifDao.getById(id);
    }

    @Override
    @Transactional
    public void update(Tarif tarif) {
        tarifDao.update(tarif);
    }
}
