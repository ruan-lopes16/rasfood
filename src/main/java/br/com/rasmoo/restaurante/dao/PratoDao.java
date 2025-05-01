package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Prato;

import javax.persistence.EntityManager;
import javax.persistence.Id;

public class PratoDao {
    // atributo
    // Cada instância do PratoDao vai usar esse EntityManager para executar operações
    private EntityManager entityManager;


    // Quando você cria um PratoDao, você obriga a passar um EntityManager (injeção manual de dependência)
    public PratoDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    /*
    CRUD
    C = CREATE -> cadastrar()
    R = READ -> consultar()
    U = UPDATE -> atualizar()
    D = DELETE -> excluir()
     */

    public void cadastrar(final Prato prato){
        this.entityManager.persist(prato);
        // log
        // System.out.println("Entidade cadastrada: " + prato);
    }

    // buscando por id da entidade
    public Prato consultar(final Integer id){
        return this.entityManager.find(Prato.class, id); // procura no banco de dados um objeto da classe Prato com o ID fornecido (entidade + chave primaria)
    }

    // atualizando entidade (merge -> consigo voltar de detached para managed
    public void atualizar(final Prato prato){
        this.entityManager.merge(prato);
        System.out.println("Entidade atualizada: " + prato);
    }

    // excluindo entidade
    public void excluir(final Prato prato){
        this.entityManager.remove(prato);
        System.out.println("Entidade removida: " + prato);
    }
}
