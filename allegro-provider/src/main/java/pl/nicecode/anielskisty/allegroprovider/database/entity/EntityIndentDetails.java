package pl.nicecode.anielskisty.allegroprovider.database.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by jhojczak on 1/5/16.
 */
@Entity
@Table(name = "indent_details", schema = "public", catalog = "anielskistyl")
public class EntityIndentDetails implements Serializable {
    private UUID id;
    private int count;

    @Id
    @Column(name = "id")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Basic
    @Column(name = "count")
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityIndentDetails that = (EntityIndentDetails) o;

        if (count != that.count) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + count;
        return result;
    }
}
