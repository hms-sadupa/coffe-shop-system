package com.coffee.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class CoffeeOrderItemId implements Serializable {
    @ManyToOne
    @JsonIgnore
    private CoffeeOrder coffeeOrder;
    @ManyToOne
    private Item item;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoffeeOrderItemId that = (CoffeeOrderItemId) o;
        if (coffeeOrder != null ? !coffeeOrder.equals(that.coffeeOrder) : that.getCoffeeOrder() != null) return false;
        if (item != null ? !item.equals(that.item) : that.item != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = (coffeeOrder != null ? coffeeOrder.hashCode() : 0);
        result = 32 * result + (item != null ? item.hashCode() : 0);
        return result;
    }

    public CoffeeOrder getCoffeeOrder() {
        return coffeeOrder;
    }

    public void setCoffeeOrder(CoffeeOrder coffeeOrder) {
        this.coffeeOrder = coffeeOrder;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
