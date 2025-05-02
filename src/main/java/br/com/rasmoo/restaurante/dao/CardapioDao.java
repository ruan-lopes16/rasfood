package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Cardapio;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CardapioDao {

    private EntityManager entityManager;

    public CardapioDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /*
     * CRUD
     * C = CREATE
     * R = READ
     * U = UPDATE
     * D = DELETE
     * */

    public void cadastrar(final Cardapio cardapio) {
        this.entityManager.persist(cardapio);
    }

    public Cardapio consultarPorId(final Integer id) {
        return this.entityManager.find(Cardapio.class, id);
    }

    public Cardapio consultarPorNome(final String filtro) {
        try {
            String jpql = "SELECT c FROM Cardapio c WHERE LOWER(c.nome) = LOWER(:nome)";
            return this.entityManager
                    .createQuery(jpql, Cardapio.class)
                    .setParameter("nome", filtro)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    public List<Cardapio> consultarPorValor(final BigDecimal filtro){
        try {
            String jpql = "SELECT c FROM Cardapio c WHERE c.valor = : valor";
            return this.entityManager.createQuery(jpql, Cardapio.class).setParameter("valor", filtro).getResultList();
        } catch (Exception e) {
            return null;
        }

    }

    public List<Cardapio> consultarTodos(){
        try {
            String jpql = "SELECT c FROM Cardapio c";
            return this.entityManager.createQuery(jpql, Cardapio.class).getResultList();
        } catch (Exception e) {
            return null;
        }

    }

    public void atualizar(final Cardapio cardapio){
        this.entityManager.merge(cardapio);
    }

    public void excluir(final Cardapio cardapio){
        this.entityManager.remove(cardapio);
    }

}