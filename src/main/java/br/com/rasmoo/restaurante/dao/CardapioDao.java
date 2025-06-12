package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Cardapio;

import javax.persistence.EntityManager;

// classe DAO para fazendo o nosso CRUD
public class CardapioDao {
    private EntityManager entityManager;

    public CardapioDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /* CRUD
     C - create
     R - read
     U - update
     D - delete
     */
    public void cadastrar(final Cardapio prato){
        this.entityManager.persist(prato);
        System.out.println("Entidade cadastrada: " + prato);
    }

    public Cardapio consultar(final Integer id){
        /*
        Prato prato = this.entityManager.find(Prato.class, id);
        return prato;
         */
        // ou
        return this.entityManager.find(Cardapio.class, id);
    }

    public void atualizar(final Cardapio cardapio){
        this.entityManager.merge(cardapio);    // pratica não necessariamente para atualizar e sim "voltar" para o estado de detached para managed
        System.out.println("Entidade " + cardapio + " foi atualizada");
    }

    public void excluir(final Cardapio cardapio){
        this.entityManager.remove(cardapio);
        System.out.println("Entidade " + cardapio + " foi excluída");
    }
}
