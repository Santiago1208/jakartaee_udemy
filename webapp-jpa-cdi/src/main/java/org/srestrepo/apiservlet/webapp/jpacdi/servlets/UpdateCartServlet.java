package org.srestrepo.apiservlet.webapp.jpacdi.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.srestrepo.apiservlet.webapp.jpacdi.models.Cart;

import java.io.IOException;

@WebServlet("/cart/update")
public class UpdateCartServlet extends HttpServlet {

    @Inject
    private Cart cart;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] amounts = request.getParameterValues("amount");
        String[] deletes = request.getParameterValues("delete");
        int itemsLength = cart.getCartItems().size();
        for (int i = 0; i < itemsLength; i++) {
            int amountInt = Integer.parseInt(amounts[i]);
            cart.getCartItems().get(i).setAmount(amountInt);
        }
        if (deletes != null && deletes.length > 0) {
            for (int i = 0; i < deletes.length; i++) {
                int productDeleteId =  Integer.parseInt(deletes[i]);
                cart.getCartItems().stream()
                        .filter(item -> item.getProduct().getId() == productDeleteId)
                        .findFirst()
                        .ifPresent(item -> cart.getCartItems().remove(item));
            }
        }
        response.sendRedirect(getServletContext().getContextPath() +  "/cart/view");
    }
}
