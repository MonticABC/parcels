package by.bntu.fitr.povt.dao.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String country;
    private String region;




    /*@OneToMany(mappedBy = "countryByCountrySender",fetch = FetchType.LAZY)
    private Collection<Parcel> parcelsById;
    @OneToMany(mappedBy = "countryByCountryRecipient",fetch = FetchType.LAZY)
    private Collection<Parcel> parcelsById_0;*/


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country1 = (Country) o;

        if (id != country1.id) return false;
        if (country != null ? !country.equals(country1.country) : country1.country != null) return false;
        if (region != null ? !region.equals(country1.region) : country1.region != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        return result;
    }


   /* public Collection<Parcel> getParcelsById() {
        return parcelsById;
    }

    public void setParcelsById(Collection<Parcel> parcelsById) {
        this.parcelsById = parcelsById;
    }


    public Collection<Parcel> getParcelsById_0() {
        return parcelsById_0;
    }

    public void setParcelsById_0(Collection<Parcel> parcelsById_0) {
        this.parcelsById_0 = parcelsById_0;
    }*/
}
