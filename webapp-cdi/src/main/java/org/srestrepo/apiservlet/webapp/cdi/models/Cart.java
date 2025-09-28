package org.srestrepo.apiservlet.webapp.cdi.models;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import org.srestrepo.apiservlet.webapp.cdi.config.SessionCart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@SessionCart
public class Cart implements Serializable {

    @Inject
    private transient Logger log;
    private List<CartItem> cartItems;

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    @PostConstruct
    public void initialize() {
        log.info("Cart initializing");
        cartItems = new ArrayList<>();
    }

    public void addItem(CartItem item) {
        if (cartItems.contains(item)) {
            cartItems.stream()
                    .filter(i -> i.equals(item))
                    .findFirst()
                    .ifPresent(cartItem -> {
                        cartItem.setAmount(cartItem.getAmount() + 1);
                    });
        } else {
            cartItems.add(item);
        }
    }

    public int getTotal() {
        return cartItems.stream()
                .mapToInt(CartItem::getLineTotal)
                .sum();
    }

    @PreDestroy
    public void destroy() {
        log.info("Cart destroying");
    }
}
