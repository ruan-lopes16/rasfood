package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Prato;

import javax.persistence.EntityManager;

// classe DAO para fazendo o nosso CRUD
public class PratoDao {
    private EntityManager entityManager;

    public PratoDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /* CRUD
     C - create
     R - read
     U - update
     D - delete
     */
    public void cadastrar(final Prato prato){
        this.entityManager.persist(prato);
        System.out.println("Entidade cadastrada: " + prato);
    }

    public Prato consultar(final Integer id){
        /*
        Prato prato = this.entityManager.find(Prato.class, id);
        return prato;
         */
        // ou
        return this.entityManager.find(Prato.class, id);
    }

    public void atualizar(final Prato prato){
        this.entityManager.merge(prato);
        System.out.println("Entidade " + prato + "foi atualizada");
    }

    public void excluir(final Prato prato){
        this.entityManager.remove(prato);
        System.out.println("Entidade " + prato + "foi excluída");
    }
}
