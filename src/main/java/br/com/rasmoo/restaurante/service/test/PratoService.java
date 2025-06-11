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

        Prato salmao = new Prato();
        salmao.setNome("Salmão ao molho de maracujá");
        salmao.setDescricao("Salmão grelhado ao molho de maracujá");
        salmao.setDisponivel(true);
        salmao.setValor(BigDecimal.valueOf(60.00));

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
        entityManager.flush(); // sincronizando com o banco de dados a cada cadastro -> sem commit, assim permitindo rollback
        pratoDao.cadastrar(salmao);
        entityManager.flush();

        System.out.println("O prato consultado foi: " + pratoDao.consultar(1));  // consultando prato cadastrado

        pratoDao.excluir(salmao);   // excluindo salmao
        System.out.println("O prato excluido foi: " + pratoDao.consultar(2)); // nesse momento retorna null, pois a exclusão já foi feita e nao está mais no banco

        // atualizando banco de dados / sincronia
        // entityManager.getTransaction().commit(); // commit -> entende que é o momento de encerrar a transação - não permite rollback

        // fechando transação -> MANAGED to DETACHED
        entityManager.clear(); // troca de close para clear, para que funcione o merge

        risoto.setValor(BigDecimal.valueOf(75.50)); // alterando o valor de risoto
        pratoDao.atualizar(risoto);
        System.out.println("O prato consultado foi: " + pratoDao.consultar(1));  // consultando prato cadastrado atualizado
    }
}
