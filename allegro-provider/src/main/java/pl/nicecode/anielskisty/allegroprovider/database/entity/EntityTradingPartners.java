package pl.nicecode.anielskisty.allegroprovider.database.entity;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by jhojczak on 1/5/16.
 */
@Entity
@Table(name = "trading_partners", schema = "public", catalog = "anielskistyl")
public class EntityTradingPartners {
    private UUID id;
    private String name;
    private String surname;
    private String street;
    private String number;
    private String city;
    private String contry;
    private String postalKode;
    private String addressType;
    private String phone;
    private String email;
    private String login;

    @Id
    @Column(name = "id")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "contry")
    public String getContry() {
        return contry;
    }

    public void setContry(String contry) {
        this.contry = contry;
    }

    @Basic
    @Column(name = "postal_kode")
    public String getPostalKode() {
        return postalKode;
    }

    public void setPostalKode(String postalKode) {
        this.postalKode = postalKode;
    }

    @Basic
    @Column(name = "address_type")
    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityTradingPartners that = (EntityTradingPartners) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (contry != null ? !contry.equals(that.contry) : that.contry != null) return false;
        if (postalKode != null ? !postalKode.equals(that.postalKode) : that.postalKode != null) return false;
        if (addressType != null ? !addressType.equals(that.addressType) : that.addressType != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (contry != null ? contry.hashCode() : 0);
        result = 31 * result + (postalKode != null ? postalKode.hashCode() : 0);
        result = 31 * result + (addressType != null ? addressType.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        return result;
    }
}
