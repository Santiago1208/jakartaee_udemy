package org.srestrepo.client.ejb;

import org.srestrepo.appejb.remote.model.Product;
import org.srestrepo.appejb.remote.service.ProductServiceRemoteEJB;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Main {

    public static void main(String[] args) {

        ProductServiceRemoteEJB productService;
        ProductServiceRemoteEJB statefulProductService;

        try {
            InitialContext remoteContext = new InitialContext();
            productService = (ProductServiceRemoteEJB) remoteContext.lookup("ejb:/appejb-remote/ProductServiceRemoteEJBImpl!org.srestrepo.appejb.remote.service.ProductServiceRemoteEJB");
            statefulProductService = (ProductServiceRemoteEJB) remoteContext.lookup("ejb:/appejb-remote/ProductStatefulServiceRemoteEJBImpl!org.srestrepo.appejb.remote.service.ProductServiceRemoteEJB?stateful");

            Product p = productService.save(new Product("Sandia"));
            System.out.println("New product saved: " + p);

            productService.findAll().forEach(System.out::println);

            System.out.println("Stateful product service call: " +  statefulProductService.findAll());
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
