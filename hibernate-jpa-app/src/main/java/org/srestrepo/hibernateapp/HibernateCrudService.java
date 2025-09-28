package org.srestrepo.hibernateapp;

import jakarta.persistence.EntityManager;
import org.srestrepo.hibernateapp.entity.Client;
import org.srestrepo.hibernateapp.service.ClientService;
import org.srestrepo.hibernateapp.service.ClientServiceImpl;
import org.srestrepo.hibernateapp.util.JpaUtil;

import java.util.List;
import java.util.Optional;

public class HibernateCrudService {

    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();
        ClientService clientService = new ClientServiceImpl(em);

        System.out.println("============ Find All ============");
        List<Client> clients = clientService.findAll();
        clients.forEach(System.out::println);

        System.out.println("============ Find By ID ============");
        Optional<Client> client = clientService.findById(1L);
        client.ifPresent(System.out::println);

        System.out.println("============ Save new client ============");
        Client c1 = new Client(null, "Lucy", "Mena", "PayPal");
        clientService.save(c1);
        System.out.println("Client was saved successfully");
        clientService.findAll().forEach(System.out::println);

        System.out.println("============ Update client ============");
        Optional<Client> c2 = clientService.findById(c1.getId());
        c2.ifPresent(c -> {
            c.setPaymentType("Mercado Pago");
            clientService.save(c);
            System.out.println("Client was updated successfully");
            clientService.findAll().forEach(System.out::println);
        });

        System.out.println("============ Delete client ============");
        Optional<Client> c3 = clientService.findById(c1.getId());
        c3.ifPresent(c -> {
            clientService.delete(c.getId());
            System.out.println("Client was deleted successfully");
            clientService.findAll().forEach(System.out::println);
        });

        em.close();
    }
}
