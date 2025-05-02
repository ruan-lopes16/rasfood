package br.com.rasmoo.restaurante.service.teste;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.dao.CategoriaDao;
import br.com.rasmoo.restaurante.entity.Cardapio;
import br.com.rasmoo.restaurante.entity.Categoria;
import br.com.rasmoo.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CardapioService {
    public static void main(String[] args) {

        EntityManager entityManager = JPAUtil.getEntityManagerRasFood();
        cadastrarProdutoCardapio(entityManager, cadastrarCategoria(entityManager));
    }

    public static Categoria cadastrarCategoria(EntityManager entityManager) {
        CategoriaDao categoriaDao = new CategoriaDao(entityManager);
        Categoria pratoPrincipal = new Categoria("Prato principal");
        entityManager.getTransaction().begin();
        categoriaDao.cadastrar(pratoPrincipal);
        entityManager.getTransaction().commit();
        entityManager.clear();

        return pratoPrincipal;
    }

    private static void cadastrarProdutoCardapio(EntityManager entityManager, Categoria categoria) {

        // 1. Criando novo prato
        Cardapio risoto = new Cardapio();
        risoto.setNome("Risoto de frutos do mar");
        risoto.setDescricao("Risoto acompanhado de lula, polvo e mariscos");
        risoto.isDisponivel(true);
        risoto.setCategoria(categoria);
        risoto.setValor(BigDecimal.valueOf(88.50));

        Cardapio salmao = new Cardapio();
        salmao.setNome("Salmão ao molho de maracujá");
        salmao.setDescricao("Salmão grelhado ao molho de maracujá");
        salmao.isDisponivel(true);
        salmao.setCategoria(categoria);
        salmao.setValor(BigDecimal.valueOf(60.00));

        // .setDataDeRegistro(); -> nesse caso não é preciso, já fica automático

        // Criamos um DAO para gerenciar a persistência de Prato.
        // Passamos o EntityManager para ele usar nos métodos (como cadastrar()).
        CardapioDao cardapioDao = new CardapioDao(entityManager);

        // 2. Iniciando a transação - caminhando para TRANSIENT
        entityManager.getTransaction().begin();

        // 3. Salvando (persistindo) o objeto no banco - MANAGED
        cardapioDao.cadastrar(risoto); // id 1
        entityManager.flush();
        cardapioDao.cadastrar(salmao); // id 2
        entityManager.flush();

        // consultando prato
        System.out.println("O prato consultado foi: " + cardapioDao.consultar(1));
        System.out.println("O prato consultado foi: " + cardapioDao.consultar(2));

        // 4. Encerrando o gerenciador de entidades - DETACHED
        entityManager.close();
    }
}
