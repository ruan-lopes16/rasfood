package br.com.rasmoo.restaurante.service.teste;

import br.com.rasmoo.restaurante.dao.PratoDao;
import br.com.rasmoo.restaurante.entity.Prato;
import br.com.rasmoo.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;
// import javax.persistence.EntityManagerFactory;
// import javax.persistence.Persistence;
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

        // EntityManager -> interface JPA que faze o gerenciamento de entidade
        // EntityManagerFactory -> interface JPA que implementa padrão de projeto

        // cria gerenciador de entity
        // Usamos o utilitário que criamos (JPAUtil) para obter um EntityManager.
        // Agora estamos prontos para conversar com o banco
        EntityManager entityManager = JPAUtil.getEntityManagerRasFood();

        // Criamos um DAO para gerenciar a persistência de Prato.
        // Passamos o EntityManager para ele usar nos métodos (como cadastrar()).
        PratoDao pratoDao = new PratoDao(entityManager);

        // 4. Iniciando a transação - caminhando para TRANSIENT
        entityManager.getTransaction().begin();

        // 5. Salvando (persistindo) o objeto no banco - MANAGED
        pratoDao.cadastrar(risoto);

        // 6. Confirmando (commit) a transação - sincronizando com o banco de dados - MANAGED
        entityManager.getTransaction().commit();

        // 7. Encerrando o gerenciador de entidades - DETACHED
        entityManager.close();
    }
}
