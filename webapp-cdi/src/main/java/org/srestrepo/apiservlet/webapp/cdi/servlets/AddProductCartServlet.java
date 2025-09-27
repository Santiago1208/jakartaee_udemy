package org.srestrepo.apiservlet.webapp.cdi.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.srestrepo.apiservlet.webapp.cdi.config.DefaultProductService;
import org.srestrepo.apiservlet.webapp.cdi.models.Cart;
import org.srestrepo.apiservlet.webapp.cdi.models.CartItem;
import org.srestrepo.apiservlet.webapp.cdi.models.Product;
import org.srestrepo.apiservlet.webapp.cdi.services.ProductService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/cart/add")
public class AddProductCartServlet extends HttpServlet {

    @Inject
    private Cart cart;
    @Inject
    @DefaultProductService
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long productId = Long.parseLong(request.getParameter("id"));
        Optional<Product> product = productService.findById(productId);
        if (product.isPresent()) {
            CartItem cartItem = new CartItem(1, product.get());
            cart.addItem(cartItem);
        }
        response.sendRedirect(request.getContextPath() + "/cart/view");
    }
}
