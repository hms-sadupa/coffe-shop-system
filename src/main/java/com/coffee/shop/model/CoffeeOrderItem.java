package com.coffee.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "coffee_order_item")
@AssociationOverrides({
        @AssociationOverride(name = "pk.coffeeOrder", joinColumns = @JoinColumn(name = "coffee_order_id")),
        @AssociationOverride(name = "pk.item", joinColumns = @JoinColumn(name = "item_id"))
})
public class CoffeeOrderItem implements Serializable {
    @EmbeddedId
    @JsonIgnore
    private CoffeeOrderItemId pk = new CoffeeOrderItemId();

    @Column(name = "quantity")
    private int quantity;

    public CoffeeOrderItemId getPk() {
        return pk;
    }

    public void setPk(CoffeeOrderItemId pk) {
        this.pk = pk;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Transient
    @JsonIgnore
    public CoffeeOrder getCoffeeOrder() {
        return pk.getCoffeeOrder();
    }

    public void setCoffeeOrder(CoffeeOrder coffeeOrder) {
        pk.setCoffeeOrder(coffeeOrder);
    }

    @Transient
    public Item getItem() {
        return pk.getItem();
    }

    public void setItem(Item item) {
        pk.setItem(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoffeeOrderItem that = (CoffeeOrderItem) o;
        if (getPk() != null ? !getPk().equals(that.pk) : that.getPk() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk);
    }
}
