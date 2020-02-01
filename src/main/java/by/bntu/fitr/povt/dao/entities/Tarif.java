package by.bntu.fitr.povt.dao.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tarif")
public class Tarif {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private double price;

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
    @Column(name = "delivery_time")
    private int deliveryTime;

    /*@OneToMany(mappedBy = "tarifByTarifId",fetch = FetchType.LAZY)
    private Collection<Parcel> parcelsById;
*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tarif tarif = (Tarif) o;

        if (id != tarif.id) return false;
        if (Double.compare(tarif.price, price) != 0) return false;
        if (name != null ? !name.equals(tarif.name) : tarif.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }


   /* public Collection<Parcel> getParcelsById() {
        return parcelsById;
    }

    public void setParcelsById(Collection<Parcel> parcelsById) {
        this.parcelsById = parcelsById;
    }*/
}
