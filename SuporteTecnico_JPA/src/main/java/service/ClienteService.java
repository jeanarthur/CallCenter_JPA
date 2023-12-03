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
        this.open();
        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
        this.close();
    }

    public Cliente consultar(Long cpf){
        this.open();
        entityManager.getTransaction().begin();
        Cliente cliente = entityManager.find(Cliente.class, cpf);
        this.close();
        return cliente;
    }

    public void open(){
        entityManagerFactory = Persistence.createEntityManagerFactory("callcenter-jpa");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void atualizar(Cliente cliente){
        this.open();
        entityManager.getTransaction().begin();
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();
        this.close();
    }

    public void deletar(Cliente cliente){
        this.open();
        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();
        this.close();
    }

    public void close(){
        entityManager.close();
        entityManagerFactory.close();
    }
    
}
