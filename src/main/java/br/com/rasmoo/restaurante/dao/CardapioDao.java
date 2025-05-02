package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Cardapio;

import javax.persistence.EntityManager;
import java.util.List;

public class CardapioDao {
    // atributo
    // Cada instância do PratoDao vai usar esse EntityManager para executar operações
    private EntityManager entityManager;


    // Quando você cria um PratoDao, você obriga a passar um EntityManager (injeção manual de dependência)
    public CardapioDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public List<Cardapio> consultarTodos() {
        String sql = "SELECT c FROM Cardapio c";
        return this.entityManager.createQuery(sql, Cardapio.class).getResultList();
    }

    /*
    CRUD
    C = CREATE -> cadastrar()
    R = READ -> consultarPorId()
    U = UPDATE -> atualizar()
    D = DELETE -> excluir()
     */

    public void cadastrar(final Cardapio cardapio){
        this.entityManager.persist(cardapio);
        // log
        System.out.println("Entidade cadastrada: " + cardapio);
    }

    // buscando por id da entidade
    public Cardapio consultarPorId(final Integer id){
        return this.entityManager.find(Cardapio.class, id); // procura no banco de dados um objeto da classe Prato com o ID fornecido (entidade + chave primaria)
    }

    // atualizando entidade (merge -> consigo voltar de detached para managed
    public void atualizar(final Cardapio cardapio){
        this.entityManager.merge(cardapio);
        System.out.println("Entidade atualizada: " + cardapio);
    }

    // excluindo entidade
    public void excluir(final Cardapio cardapio){
        this.entityManager.remove(cardapio);
        System.out.println("Entidade removida: " + cardapio);
    }
}
