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

    public void persistir(Ticket ticket){
        entityManager.getTransaction().begin();
        entityManager.persist(ticket);
        entityManager.getTransaction().commit();
    }

    public Ticket consultar(String protocolo){
        entityManager.getTransaction().begin();
        Ticket ocorrencia = entityManager.find(Ticket.class, protocolo);
        entityManager.getTransaction().rollback();
        return ocorrencia;
    }

    public void atualizar(Ticket ticket){
        entityManager.getTransaction().begin();
        entityManager.merge(ticket);
        entityManager.getTransaction().commit();
    }

    public void deletar(Ticket ticket){
        entityManager.getTransaction().begin();
        entityManager.remove(ticket);
        entityManager.getTransaction().commit();
    }

    public void close(){
        entityManager.close();
        entityManagerFactory.close();
    }

}
