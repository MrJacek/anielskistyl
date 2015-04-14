package pl.nicecode.anielskistyl.contractor.enttity;

import javax.persistence.*;

/**
 * Created by jhojczak on 4/14/15.
 */
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Version
    private long version;

    private String city;

    private String street;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (id != address.id) return false;
        if (version != address.version) return false;
        if (!city.equals(address.city)) return false;
        if (!postCode.equals(address.postCode)) return false;
        if (!street.equals(address.street)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (version ^ (version >>> 32));
        result = 31 * result + city.hashCode();
        result = 31 * result + street.hashCode();
        result = 31 * result + postCode.hashCode();
        return result;
    }

    public String getStreet() {
        return street;
    }


    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    private String postCode;
}
