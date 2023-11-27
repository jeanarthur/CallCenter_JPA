package service;

import model.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ClienteService {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public ClienteService(){
        if (entityManagerFactory == null || entityManager == null){
            entityManagerFactory = Persistence.createEntityManagerFactory("callcenter-jpa");
            entityManager = entityManagerFactory.createEntityManager();
        }
    }

    public void persistir(Cliente cliente){
        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
    }

    public Cliente consultar(String protocolo){
        entityManager.getTransaction().begin();
        Cliente cliente = entityManager.find(Cliente.class, protocolo);
        entityManager.getTransaction().rollback();
        return cliente;
    }

    public void atualizar(Cliente cliente){
        entityManager.getTransaction().begin();
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();
    }

    public void deletar(Cliente cliente){
        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();
    }

    public void close(){
        entityManager.close();
        entityManagerFactory.close();
    }
    
}
