package by.bntu.fitr.povt.dao.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name= "parcel")
public class Parcel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "date_of_send")
    private Date dateOfSend;
    @Column(name = "country_sender")
    private int countrySender;
    @Column(name = "country_recipient")
    private int countryRecipient;
    private double weight;



    @Column(name = "tarif_id")
    private int tarifId;
    @Column(name = "order_id")
    private int orderId;
    private boolean express;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "total_price")
    private double totalPrice;

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
    @Column(name = "delivery_time")
    private int deliveryTime;

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }



    @ManyToOne
    @JoinColumn(name = "country_sender", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    private Country countryByCountrySender;
    @ManyToOne
    @JoinColumn(name = "country_recipient", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    private Country countryByCountryRecipient;

    @ManyToOne
    @JoinColumn(name = "tarif_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    private Tarif tarifByTarifId;

    /*
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order orderByOrderId;
*/
/*
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User userByUserId;
*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Date getDateOfSend() {
        return dateOfSend;
    }

    public void setDateOfSend(Date dateOfSend) {
        this.dateOfSend = dateOfSend;
    }


    public int getCountrySender() {
        return countrySender;
    }

    public void setCountrySender(int countrySender) {
        this.countrySender = countrySender;
    }


    public int getCountryRecipient() {
        return countryRecipient;
    }

    public void setCountryRecipient(int countryRecipient) {
        this.countryRecipient = countryRecipient;
    }


    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getTarifId() {
        return tarifId;
    }

    public void setTarifId(int tarifId) {
        this.tarifId = tarifId;
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


    public boolean getExpress() {
        return express;
    }

    public void setExpress(boolean express) {
        this.express = express;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parcel parcel = (Parcel) o;

        if (id != parcel.id) return false;
        if (countrySender != parcel.countrySender) return false;
        if (countryRecipient != parcel.countryRecipient) return false;
        if (Double.compare(parcel.weight, weight) != 0) return false;
        if (tarifId != parcel.tarifId) return false;
        if (orderId != parcel.orderId) return false;
        if (express != parcel.express) return false;
        if (userId != parcel.userId) return false;
        if (dateOfSend != null ? !dateOfSend.equals(parcel.dateOfSend) : parcel.dateOfSend != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (dateOfSend != null ? dateOfSend.hashCode() : 0);
        result = 31 * result + countrySender;
        result = 31 * result + countryRecipient;
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + tarifId;
        result = 31 * result + orderId;

        result = 31 * result + userId;
        return result;
    }


    public Country getCountryByCountrySender() {
        return countryByCountrySender;
    }

    public void setCountryByCountrySender(Country countryByCountrySender) {
        this.countryByCountrySender = countryByCountrySender;
    }


    public Country getCountryByCountryRecipient() {
        return countryByCountryRecipient;
    }

    public void setCountryByCountryRecipient(Country countryByCountryRecipient) {
        this.countryByCountryRecipient = countryByCountryRecipient;
    }


    public Tarif getTarifByTarifId() {
        return tarifByTarifId;
    }

    public void setTarifByTarifId(Tarif tarifByTarifId) {
        this.tarifByTarifId = tarifByTarifId;
    }

/*
    public Order getOrderByOrderId() {
        return orderByOrderId;
    }

    public void setOrderByOrderId(Order orderByOrderId) {
        this.orderByOrderId = orderByOrderId;
    }
    */


    @Override
    public String toString() {
        return "Parcel{" +
                "dateOfSend=" + dateOfSend +
                ", countrySender=" + countryByCountrySender.getCountry() +
                ", countryRecipient=" + countryByCountryRecipient.getCountry() +
                ", weight=" + weight +
                ", tarif=" + tarifByTarifId.getName() +
                ", express=" + express +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
