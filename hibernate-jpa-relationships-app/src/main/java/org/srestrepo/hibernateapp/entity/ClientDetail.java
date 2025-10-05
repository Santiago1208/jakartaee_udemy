package org.srestrepo.hibernateapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "client_details")
public class ClientDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean prime;
    @Column(name = "score_total")
    private Long scoreTotal;
    @OneToOne
    @JoinColumn(name = "id_client")
    private Client client;

    public ClientDetail() {
    }

    public ClientDetail(Boolean prime, Long scoreTotal) {
        this.prime = prime;
        this.scoreTotal = scoreTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPrime() {
        return prime;
    }

    public void setPrime(Boolean prime) {
        this.prime = prime;
    }

    public Long getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(Long scoreTotal) {
        this.scoreTotal = scoreTotal;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "{ " +
                "id=" + id +
                ", prime=" + prime +
                ", scoreTotal=" + scoreTotal +
                " }";
    }
}
