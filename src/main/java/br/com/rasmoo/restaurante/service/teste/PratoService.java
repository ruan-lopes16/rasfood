package br.com.rasmoo.restaurante.service.teste;

import br.com.rasmoo.restaurante.entity.Prato;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class PratoService {
    public static void main(String[] args) {

        // 1. Criando um novo prato
        Prato risoto = new Prato();

        // informações/atributos de risoto
        risoto.setNome("Risoto de frutos do mar");
        risoto.setDescricao("Risoto acompanhado de lula, polvo e mariscos");
        risoto.isDisponivel(true);
        risoto.setValor(BigDecimal.valueOf(88.50));
        // risoto.setDataDeRegistro(); -> nesse caso não é preciso, já fica automático

        // persistindo informações na tabela
        // EntityManager -> interface JPA que faze o gerenciamento de entidade
        // EntityManagerFactory -> interface JPA que implementa padrão de projeto

        // 2. Criando a fábrica de EntityManager (padrão Factory)
        // "padrão de projeto" factory - persistindo entidade, passando como parametro persistence.xml >>> <persistence-unit name="rasFood"> - pegamos apenas o name
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rasFood");

        // 3. Criando o gerenciador de entidades (EntityManager)
        // criando o EntityManager a partir do Factory
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // 4. Iniciando a transação
        // iniciando transações - caminhando para TRANSIENT
        entityManager.getTransaction().begin();

        // 5. Salvando (persistindo) o objeto no banco
        // persistindo a entidade - MANAGED
        entityManager.persist(risoto);

        // 6. Confirmando (commit) a transação
        // sincronizando com o banco de dados - MANAGED
        entityManager.getTransaction().commit();

        // 7. Encerrando o gerenciador de entidades
        // fechando transação - DETACHED
        entityManager.close();
    }
}
