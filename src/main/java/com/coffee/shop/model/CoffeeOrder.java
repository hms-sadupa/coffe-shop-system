package com.coffee.shop.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "coffee_order")
public class CoffeeOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "status")
    private String status;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;
    @OneToMany(mappedBy = "coffeeOrder")
    private Set<CoffeeOrderItem> coffeeOrderItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Set<CoffeeOrderItem> getCoffeeOrderItems() {
        return coffeeOrderItems;
    }

    public void setCoffeeOrderItems(Set<CoffeeOrderItem> coffeeOrderItems) {
        this.coffeeOrderItems = coffeeOrderItems;
    }
}
