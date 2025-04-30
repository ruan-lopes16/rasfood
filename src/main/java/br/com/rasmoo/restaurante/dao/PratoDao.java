package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Prato;

import javax.persistence.EntityManager;

public class PratoDao {
    // atributo
    // Cada instância do PratoDao vai usar esse EntityManager para executar operações
    private EntityManager entityManager;


    // Quando você cria um PratoDao, você obriga a passar um EntityManager (injeção manual de dependência)
    public PratoDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void cadastrar(Prato prato){
        this.entityManager.persist(prato);
        // log
        System.out.println("Entidade cadastrada: " + prato);
    }
}
