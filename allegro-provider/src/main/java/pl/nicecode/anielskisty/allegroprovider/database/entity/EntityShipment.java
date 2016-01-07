package pl.nicecode.anielskisty.allegroprovider.database.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by jhojczak on 1/5/16.
 */
@Entity
@Table(name = "shipment", schema = "public", catalog = "as")
public class EntityShipment implements Serializable {
    private UUID id;
    private String shipmentType;
    private BigDecimal shipmentValue;

    @Id
    @Column(name = "id")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Basic
    @Column(name = "shipment_type")
    public String getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(String shipmentType) {
        this.shipmentType = shipmentType;
    }

    @Basic
    @Column(name = "shipment_value")
    public BigDecimal getShipmentValue() {
        return shipmentValue;
    }

    public void setShipmentValue(BigDecimal shipmentValue) {
        this.shipmentValue = shipmentValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityShipment that = (EntityShipment) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (shipmentType != null ? !shipmentType.equals(that.shipmentType) : that.shipmentType != null) return false;
        if (shipmentValue != null ? !shipmentValue.equals(that.shipmentValue) : that.shipmentValue != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (shipmentType != null ? shipmentType.hashCode() : 0);
        result = 31 * result + (shipmentValue != null ? shipmentValue.hashCode() : 0);
        return result;
    }
}
