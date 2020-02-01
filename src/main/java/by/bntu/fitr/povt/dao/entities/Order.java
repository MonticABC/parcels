package by.bntu.fitr.povt.dao.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public double getTotalpPrice() {
        return totalpPrice;
    }

    public void setTotalpPrice(double totalpPrice) {
        this.totalpPrice = totalpPrice;
    }

    @Column(name = "id_user")
    private int idUser;

    @Column(name = "totalp_price")
    private double totalpPrice;

    @Transient
    private Collection<Parcel> parcelsById;
   /*@ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id",nullable = true)
    private User userByIdUser;
    @OneToMany(mappedBy = "orderByOrderId")
    private Collection<Parcel> parcelsById;
*/



   /* public User getUserByIdUser() {
        return userByIdUser;
    }

    public void setUserByIdUser(User userByIdUser) {
        this.userByIdUser = userByIdUser;
    }


    public Collection<Parcel> getParcelsById() {
        return parcelsById;
    }

    public void setParcelsById(Collection<Parcel> parcelsById) {
        this.parcelsById = parcelsById;
    }*/

    public Collection<Parcel> getParcelsById() {
        return parcelsById;
    }

    public void setParcelsById(Collection<Parcel> parcelsById) {
        this.parcelsById = parcelsById;}
}
