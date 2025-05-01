package br.com.rasmoo.restaurante.service.teste;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.entity.Cardapio;
import br.com.rasmoo.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;
// import javax.persistence.EntityManagerFactory;
// import javax.persistence.Persistence;
import java.math.BigDecimal;

public class CardapioService {
    public static void main(String[] args) {

        // 1. Criando um novo prato
        Cardapio risoto = new Cardapio();

        // informações/atributos de risoto
        risoto.setNome("Risoto de frutos do mar");
        risoto.setDescricao("Risoto acompanhado de lula, polvo e mariscos");
        risoto.isDisponivel(true);
        risoto.setValor(BigDecimal.valueOf(88.50));
        // risoto.setDataDeRegistro(); -> nesse caso não é preciso, já fica automático

        Cardapio salmao = new Cardapio();
        salmao.setNome("Salmão ao molho de maracujá");
        salmao.setDescricao("Salmão grelhado ao molho de maracujá");
        salmao.isDisponivel(true);
        salmao.setValor(BigDecimal.valueOf(60.00));


        // EntityManager -> interface JPA que faze o gerenciamento de entidade
        // EntityManagerFactory -> interface JPA que implementa padrão de projeto

        // cria gerenciador de entity
        // Usamos o utilitário que criamos (JPAUtil) para obter um EntityManager.
        // Agora estamos prontos para conversar com o banco
        EntityManager entityManager = JPAUtil.getEntityManagerRasFood();

        // Criamos um DAO para gerenciar a persistência de Prato.
        // Passamos o EntityManager para ele usar nos métodos (como cadastrar()).
        CardapioDao cardapioDao = new CardapioDao(entityManager);

        // 4. Iniciando a transação - caminhando para TRANSIENT
        entityManager.getTransaction().begin();

        // 5. Salvando (persistindo) o objeto no banco - MANAGED
        cardapioDao.cadastrar(risoto); // id 1
        entityManager.flush();
        cardapioDao.cadastrar(salmao); // id 2
        entityManager.flush();

        // consultando prato
        System.out.println("O prato consultado foi: " + cardapioDao.consultar(1));

        // excluindo prato + tentativa de consulta
        cardapioDao.excluir(salmao);
        System.out.println("O prato consultado foi: " + cardapioDao.consultar(2)); // null, por conta da exclusão

        entityManager.clear(); // removendo objetos gerenciados pelo EntityManager

        // atualizando risoto
        risoto.setValor(BigDecimal.valueOf(75.50));
        cardapioDao.atualizar(risoto);
        System.out.println("O prato consultado foi: " + cardapioDao.consultar(1));


        /*
        6. Confirmando (commit) a transação - sincronizando com o banco de dados - MANAGED
        Agora será o método flush a cada interação como o banco de dados

        entityManager.getTransaction().commit();
         */


        // 7. Encerrando o gerenciador de entidades - DETACHED
        entityManager.close();
    }
}
