package pl.nicecode.anielskisty.allegroprovider.database.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by jhojczak on 1/5/16.
 */
@Entity
@Table(name = "product_version", schema = "public", catalog = "as")
public class EntityProductVersion implements Serializable {
    private UUID id;
    private String type;

    @Id
    @Column(name = "id")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityProductVersion that = (EntityProductVersion) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
