package by.bntu.fitr.povt.services.implement;

import by.bntu.fitr.povt.services.ParcelService;
import by.bntu.fitr.povt.dao.entities.Parcel;
import by.bntu.fitr.povt.dao.interfaces.ParcelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ParcelServiceImpl implements ParcelService {

    ParcelDao parcelDao;



    public void setParcelDao(ParcelDao parcelDao) {
        this.parcelDao = parcelDao;
    }

    @Transactional
    @Override
    public void add(Parcel parcel) {


        parcelDao.add(parcel);
    }

    @Transactional
    @Override
    public void remove(int id) {
        parcelDao.remove(id);
    }

    @Transactional
    @Override
    public void update(Parcel parcel) {
    parcelDao.update(parcel);
    }

    @Transactional
    @Override
    public List<Parcel> getList() {
        return parcelDao.getList();
    }

    @Transactional
    @Override
    public Parcel getById(int id) {
        return parcelDao.getById(id);
    }

    @Transactional
    @Override
    public List<Parcel> getAllParcelsByUserId(int id) {
        return parcelDao.getAllParcelsByUserId(id);
    }

    @Override
    @Transactional
    public List<Parcel> getParcelsByOrderId(int id) {
        return parcelDao.getParcelsByOrderId(id);
    }

    @Override
    @Transactional
    public double getTotalPrice(int userId) {
        return parcelDao.getTotalPrice(userId);
    }

    @Override
    @Transactional
    public double getTotalWeight(int userId) {
        return parcelDao.getTotalWeight(userId);
    }
}
