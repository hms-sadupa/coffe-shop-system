package com.coffee.shop.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "coffee_order")
public class CoffeeOrder implements Serializable {

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToMany(mappedBy = "pk.coffeeOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonProperty("items")
    private Set<CoffeeOrderItem> coffeeOrderItems;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    public Status getStatus() {
        return status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        PENDING, PROCESSED, CANCELED
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
