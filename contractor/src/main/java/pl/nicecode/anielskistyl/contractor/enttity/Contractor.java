package pl.nicecode.anielskistyl.contractor.enttity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by jhojczak on 4/14/15.
 */
@Entity
@Table(name = "contractor")
public class Contractor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String login;

    @Version
    private long version;

    @ManyToOne
    private List<Address> deliveryAddresses;

    @OneToOne
    private Address userAddress;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Address> getDeliveryAddresses() {
        return deliveryAddresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contractor that = (Contractor) o;

        if (id != that.id) return false;
        if (version != that.version) return false;
        if (!login.equals(that.login)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + login.hashCode();
        result = 31 * result + (int) (version ^ (version >>> 32));
        return result;
    }
}
