package br.com.rasmoo.restaurante.service.test;

import br.com.rasmoo.restaurante.dao.PratoDao;
import br.com.rasmoo.restaurante.entity.Prato;
import br.com.rasmoo.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class PratoService {
    public static void main(String[] args) {

        // persistindo entidade
        Prato risoto = new Prato();
        risoto.setNome("Risoto de frutos do mar");
        risoto.setDescricao("Risoto acompanhado de lula, polvo e mariscos");
        risoto.setDisponivel(true);
        risoto.setValor(BigDecimal.valueOf(88.50));
        // TRANSIENT

        // gerenciando entidade
        /* ANTES
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rasfood"); // criando interface para dar inicio à gerenciamento de entidades
        EntityManager entityManager = entityManagerFactory.createEntityManager(); // chamando interface JPA para gerenciar entidades
        */
        // com os Managers isolados(evitar repetição), fazemos:
        EntityManager entityManager = JPAUtil.getEntityManagerRasfood();

        // iniciando transações -> "RESOURCE_LOCAL", diz que eu mesmo quem irá cuidar das transações/gerenciamento. "JTA" -> servidor de aplicação que irá gerenciar
        PratoDao pratoDao = new PratoDao(entityManager);
        entityManager.getTransaction().begin(); // pega a transação e da um início à ela

        // ANTES
        // entityManager.persist(risoto); // persistindo entidade -> TRANSIENT to MANAGED - JPA consegue gerenciar nossa entidade
        pratoDao.cadastrar(risoto);

        // atualizando banco de dados / sincronia
        entityManager.getTransaction().commit();

        // fechando transação -> MANAGED to DETACHED
        entityManager.close();
    }
}
