package service;

import model.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class OcorrenciaService {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public OcorrenciaService(){
        if (entityManagerFactory == null || entityManager == null){
            entityManagerFactory = Persistence.createEntityManagerFactory("callcenter-jpa");
            entityManager = entityManagerFactory.createEntityManager();
        }
    }

    public void open(){
        entityManagerFactory = Persistence.createEntityManagerFactory("callcenter-jpa");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void persistir(Ticket ticket){
        this.open();
        entityManager.getTransaction().begin();
        entityManager.persist(ticket);
        entityManager.getTransaction().commit();
        this.close();
    }

    public Ticket consultar(String protocolo){
        this.open();
        entityManager.getTransaction().begin();
        Ticket ocorrencia = entityManager.find(Ticket.class, protocolo);
        this.close();
        return ocorrencia;
    }

    public void atualizar(Ticket ticket){
        this.open();
        entityManager.getTransaction().begin();
        entityManager.merge(ticket);
        entityManager.getTransaction().commit();
        this.close();
    }

    public void deletar(Ticket ticket){
        this.open();
        entityManager.getTransaction().begin();
        entityManager.remove(ticket);
        entityManager.getTransaction().commit();
        this.close();
    }

    public void close(){
        entityManager.close();
        entityManagerFactory.close();
    }

}
