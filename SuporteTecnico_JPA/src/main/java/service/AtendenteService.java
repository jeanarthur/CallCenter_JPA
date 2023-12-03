package service;

import model.Atendente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AtendenteService {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public AtendenteService(){
        if (entityManagerFactory == null || entityManager == null){
            entityManagerFactory = Persistence.createEntityManagerFactory("callcenter-jpa");
            entityManager = entityManagerFactory.createEntityManager();
        }
    }

    public void open(){
        entityManagerFactory = Persistence.createEntityManagerFactory("callcenter-jpa");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void persistir(Atendente atendente){
        this.open();
        entityManager.getTransaction().begin();
        entityManager.persist(atendente);
        entityManager.getTransaction().commit();
        this.close();
    }

    public Atendente consultar(Long matricula){
        this.open();
        entityManager.getTransaction().begin();
        Atendente atendente = entityManager.find(Atendente.class, matricula);
        this.close();
        return atendente;
    }

    public void atualizar(Atendente atendente){
        this.open();
        entityManager.getTransaction().begin();
        entityManager.merge(atendente);
        entityManager.getTransaction().commit();
        this.close();
    }

    public void deletar(Atendente atendente){
        this.open();
        entityManager.getTransaction().begin();
        entityManager.remove(atendente);
        entityManager.getTransaction().commit();
        this.close();
    }

    public void close(){
        entityManager.close();
        entityManagerFactory.close();
    }
    
}
