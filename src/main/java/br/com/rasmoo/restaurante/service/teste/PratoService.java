package br.com.rasmoo.restaurante.service.teste;

import br.com.rasmoo.restaurante.entity.Prato;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class PratoService {
    public static void main(String[] args) {

        // criando Prato
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

        // "padrão de projeto" factory - persistindo entidade, passando como parametro persistence.xml >>> <persistence-unit name="rasFood"> - pegamos apenas o name
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rasFood");

        // criando o EntityManager a partir do Factory
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // iniciando transações - caminhando para TRANSIENT
        entityManager.getTransaction().begin();
        // persistindo a entidade - MANAGED
        entityManager.persist(risoto);
        // sincronizando com o banco de dados - MANAGED
        entityManager.getTransaction().commit();
        // fechando transação - DETACHED
        entityManager.close();
    }
}
