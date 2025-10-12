package org.srestrepo.webapp.ejb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.srestrepo.webapp.ejb.model.Product;
import org.srestrepo.webapp.ejb.service.ProductServiceLocalEJB;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ProductServiceLocalEJB productServiceLocal;
            InitialContext context = new InitialContext();
            productServiceLocal = (ProductServiceLocalEJB) context.lookup("java:global/webapp-ejb/ProductServiceLocalEJBImpl!org.srestrepo.webapp.ejb.service.ProductServiceLocalEJB");

            Product p = productServiceLocal.save(new Product("Uvas"));
            System.out.println("New product added: " + p);

            req.setAttribute("products", productServiceLocal.findAll());

            getServletContext().getRequestDispatcher("/products.jsp").forward(req, resp);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
