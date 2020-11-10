package com.coffee.shop.service.impl;

import com.coffee.shop.dto.OrderApiRequest;
import com.coffee.shop.dto.OrderItem;
import com.coffee.shop.model.*;
import com.coffee.shop.repository.CustomerRepository;
import com.coffee.shop.repository.ItemRepository;
import com.coffee.shop.repository.OrderRepository;
import com.coffee.shop.repository.ShopRepository;
import com.coffee.shop.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public CoffeeOrder getCoffeeOrder(Long orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("Missing order id");
        }
        Optional<CoffeeOrder> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new IllegalArgumentException("Invalid order id");
        }
        return order.get();
    }

    @Override
    public CoffeeOrder createOrder(OrderApiRequest orderApiRequest) {

        if (orderApiRequest.getShopId() == null || orderApiRequest.getCustomerId() == null || orderApiRequest.getOrderItems() == null || orderApiRequest.getOrderItems().isEmpty()) {
            logger.info("Missing required parameter");
            throw new IllegalArgumentException("Missing required parameters");
        }
        Shop shop = getShop(orderApiRequest.getShopId());
        Customer customer = getCustomer(orderApiRequest.getCustomerId());

        CoffeeOrder coffeeOrder = new CoffeeOrder();
        coffeeOrder.setStatus(CoffeeOrder.Status.PENDING);
        coffeeOrder.setShop(shop);
        coffeeOrder.setCustomer(customer);
        Set<CoffeeOrderItem> orderItems = getCoffeeOrderItems(orderApiRequest.getOrderItems(), coffeeOrder);

        coffeeOrder.setCoffeeOrderItems(orderItems);
        orderRepository.save(coffeeOrder);

        return coffeeOrder;
    }

    @Override
    public void cancelOrder(Long orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("Missing order id");
        }
        Optional<CoffeeOrder> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new IllegalArgumentException("Invalid order id");
        }
        CoffeeOrder coffeeOrder = order.get();
        coffeeOrder.setStatus(CoffeeOrder.Status.CANCELED);
        orderRepository.save(coffeeOrder);
    }

    private Shop getShop(Long shopId) {
        if (shopId == null) {
            logger.info("Missing shop id");
            throw new IllegalArgumentException("Missing shop id");
        }
        Optional<Shop> shop = shopRepository.findById(shopId);
        if (shop.isEmpty()) {
            logger.info("Invalid shop id");
            throw new IllegalArgumentException("Invalid shop id");
        }
        return shop.get();
    }

    private Customer getCustomer(Long customerId) {
        if (customerId == null) {
            logger.info("Missing customer id");
            throw new IllegalArgumentException("Missing customer id");
        }
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) {
            logger.info("Invalid customer id");
            throw new IllegalArgumentException("Invalid customer id");
        }
        return customer.get();
    }

    private Set<CoffeeOrderItem> getCoffeeOrderItems(Set<OrderItem> orderItems, CoffeeOrder coffeeOrder) {
        Set<CoffeeOrderItem> coffeeOrderItems = new HashSet<>();
        for (OrderItem orderItem : orderItems) {
            Optional<Item> item = itemRepository.findById(orderItem.getItemId());
            if (item.isEmpty()) {
                throw new IllegalArgumentException("Invalid item id:" + orderItem.getItemId());
            }
            CoffeeOrderItem coffeeOrderItem = new CoffeeOrderItem();
            coffeeOrderItem.setItem(item.get());
            coffeeOrderItem.setQuantity(orderItem.getQuantity());
            coffeeOrderItems.add(coffeeOrderItem);
            coffeeOrderItem.setCoffeeOrder(coffeeOrder);
        }
        return coffeeOrderItems;
    }
}
