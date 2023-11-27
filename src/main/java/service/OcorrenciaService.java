package service;

import model.Ocorrencia;

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

    public void persistir(Ocorrencia ocorrencia){
        entityManager.getTransaction().begin();
        entityManager.persist(ocorrencia);
        entityManager.getTransaction().commit();
    }

    public Ocorrencia consultar(String protocolo){
        entityManager.getTransaction().begin();
        Ocorrencia ocorrencia = entityManager.find(Ocorrencia.class, protocolo);
        entityManager.getTransaction().rollback();
        return ocorrencia;
    }

    public void atualizar(Ocorrencia ocorrencia){
        entityManager.getTransaction().begin();
        entityManager.merge(ocorrencia);
        entityManager.getTransaction().commit();
    }

    public void deletar(Ocorrencia ocorrencia){
        entityManager.getTransaction().begin();
        entityManager.remove(ocorrencia);
        entityManager.getTransaction().commit();
    }

    public void close(){
        entityManager.close();
        entityManagerFactory.close();
    }

}
