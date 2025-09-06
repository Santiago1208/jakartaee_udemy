package org.srestrepo.apiservlet.webapp.bootstrap.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.srestrepo.apiservlet.webapp.bootstrap.models.Cart;
import org.srestrepo.apiservlet.webapp.bootstrap.models.CartItem;
import org.srestrepo.apiservlet.webapp.bootstrap.models.Product;
import org.srestrepo.apiservlet.webapp.bootstrap.services.ProductJdbcServiceImpl;
import org.srestrepo.apiservlet.webapp.bootstrap.services.ProductService;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/cart/add")
public class AddProductCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long productId = Long.parseLong(request.getParameter("id"));
        Connection connection = (Connection) request.getAttribute("jdbcConnection");
        ProductService productService = new ProductJdbcServiceImpl(connection);
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
