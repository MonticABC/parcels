package by.bntu.fitr.povt.dao.interfaces;

import by.bntu.fitr.povt.dao.entities.Parcel;

import java.util.List;

public interface ParcelDao  {
    void add(Parcel parcel);
    void remove(int id);
    void update(Parcel parcel);
    List<Parcel> getList();
    Parcel getById(int id);
    List<Parcel> getAllParcelsByUserId(int id);

    List<Parcel> getParcelsByOrderId(int id);


    double getTotalPrice(int userId);
    double getTotalWeight( int userId);
}
