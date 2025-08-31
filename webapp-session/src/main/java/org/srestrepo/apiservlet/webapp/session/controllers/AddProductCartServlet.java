package org.srestrepo.apiservlet.webapp.session.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.srestrepo.apiservlet.webapp.session.models.Cart;
import org.srestrepo.apiservlet.webapp.session.models.CartItem;
import org.srestrepo.apiservlet.webapp.session.models.Product;
import org.srestrepo.apiservlet.webapp.session.services.ProductService;
import org.srestrepo.apiservlet.webapp.session.services.ProductServiceImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/cart/add")
public class AddProductCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long productId = Long.parseLong(request.getParameter("id"));
        ProductService productService = new ProductServiceImpl();
        Optional<Product> product = productService.findById(productId);
        if (product.isPresent()) {
            CartItem cartItem = new CartItem(1, product.get());
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            cart.addItem(cartItem);
        }
        response.sendRedirect(request.getContextPath() + "/cart/view");
    }
}
