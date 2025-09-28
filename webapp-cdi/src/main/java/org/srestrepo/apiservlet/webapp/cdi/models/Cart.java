package org.srestrepo.apiservlet.webapp.cdi.models;

import org.srestrepo.apiservlet.webapp.cdi.config.SessionCart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionCart
public class Cart implements Serializable {
    private final List<CartItem> cartItems;

    public Cart() {
        cartItems = new ArrayList<>();
    }

    public List<CartItem> getCartItems() {
        return cartItems;
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
}
