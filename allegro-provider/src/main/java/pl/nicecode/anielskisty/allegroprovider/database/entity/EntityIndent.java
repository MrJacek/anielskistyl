package pl.nicecode.anielskisty.allegroprovider.database.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by jhojczak on 1/5/16.
 */
@Entity
@Table(name = "indent", schema = "public", catalog = "as")
public class EntityIndent implements Serializable{
    private UUID id;
    private Timestamp createDate;
    private BigDecimal indentValue;
    private String notice;

    @Id
    @Column(name = "id")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "indent_value")
    public BigDecimal getIndentValue() {
        return indentValue;
    }

    public void setIndentValue(BigDecimal indentValue) {
        this.indentValue = indentValue;
    }

    @Basic
    @Column(name = "notice")
    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityIndent that = (EntityIndent) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (indentValue != null ? !indentValue.equals(that.indentValue) : that.indentValue != null) return false;
        if (notice != null ? !notice.equals(that.notice) : that.notice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (indentValue != null ? indentValue.hashCode() : 0);
        result = 31 * result + (notice != null ? notice.hashCode() : 0);
        return result;
    }
}
