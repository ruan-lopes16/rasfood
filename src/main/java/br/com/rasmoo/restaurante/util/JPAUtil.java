package br.com.rasmoo.restaurante.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    // atributo
    private static final EntityManagerFactory RASFOOD = Persistence.createEntityManagerFactory("rasFood");
    // EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rasFood"); //

    // getter
    public static EntityManager getEntityManagerRasFood(){
        return RASFOOD.createEntityManager();
        // EntityManager entityManager = entityManagerFactory.createEntityManager(); //
    }
}
