package br.com.rasmoo.restaurante.service.test;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.dao.CategoriaDao;
import br.com.rasmoo.restaurante.util.CargaDeDadosUtil;
import br.com.rasmoo.restaurante.entity.Cardapio;
import br.com.rasmoo.restaurante.entity.Categoria;
import br.com.rasmoo.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CardapioService {
    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManagerRasfood();
        entityManager.getTransaction().begin();
        CargaDeDadosUtil.cadastarCategorias(entityManager);
        CargaDeDadosUtil.cadastrarProdutosCardapio(entityManager);
        CardapioDao cardapioDao = new CardapioDao(entityManager);
        System.out.println("Lista de produtos por valor: "+ cardapioDao.consultarPorValor(BigDecimal.valueOf(59.00)));
        entityManager.close();
    }

    // isolando cadastro de categorias
//    private static Categoria cadastrarCategoria(EntityManager entityManager){
//        CategoriaDao categoriaDao = new CategoriaDao(entityManager);
//        Categoria pratoPrincipal = new Categoria("Prato principal");
//
//        entityManager.getTransaction().begin();     // início a transação
//
//        categoriaDao.cadastrar(pratoPrincipal);     // cadastrando categoria
//
//        entityManager.getTransaction().commit();
//        entityManager.clear();
//
//        return pratoPrincipal; // retornando a lista com as duas categorias
//
//        // descobrir como fazer várias categorias
//    }
//
//    // isolando cadastro de produtos -> entitymanager para gerenciar a classe + a categoria que irá ser passada
//    private static void cadastrarProdutoCardapio(EntityManager entityManager, Categoria categoria){      // EntityManager entityManager = JPAUtil.getEntityManagerRasfood();
//        // persistindo entidade
//        Cardapio risoto = new Cardapio();
//        risoto.setNome("Risoto de frutos do mar");
//        risoto.setDescricao("Risoto acompanhado de lula, polvo e mariscos");
//        risoto.setDisponivel(true);
//        risoto.setValor(BigDecimal.valueOf(88.50));
//        risoto.setCategoria(categoria);
//
//        Cardapio salmao = new Cardapio();
//        salmao.setNome("Salmão ao molho de maracujá");
//        salmao.setDescricao("Salmão grelhado ao molho de maracujá");
//        salmao.setDisponivel(true);
//        salmao.setCategoria(categoria);
//        salmao.setValor(BigDecimal.valueOf(60.00));
//
//
//        Cardapio petitGateau = new Cardapio();
//        petitGateau.setNome("Petit Gâteau");
//        petitGateau.setDescricao("Sobremesa individual de chocolate, com casca crocante por fora e recheio cremoso e derretido por dentro, geralmente servida quente com sorvete");
//        petitGateau.setDisponivel(true);
//        petitGateau.setValor(BigDecimal.valueOf(24.50));
//        // descobrir como fazer várias categorias
//
//        // com os Managers isolados(evitar repetição), fazemos:
//        // EntityManager entityManager = JPAUtil.getEntityManagerRasfood();     // não usa mais neste ponto, pois precisa ser usado na Main para fazer o gerenciamento
//
//        // iniciando transações -> "RESOURCE_LOCAL", diz que eu mesmo quem irá cuidar das transações/gerenciamento. "JTA" -> servidor de aplicação que irá gerenciar
//        CardapioDao cardapioDao = new CardapioDao(entityManager);
//        entityManager.getTransaction().begin();     // pega a transação e da um início à ela
//
//        cardapioDao.cadastrar(risoto);
//        entityManager.flush();      // sincronizando com o banco de dados a cada cadastro -> sem commit, assim permitindo rollback
//        cardapioDao.cadastrar(salmao);
//        entityManager.flush();
//
//        // System.out.println("O prato consultado foi: " + cardapioDao.consultarPorId(1));      // consultando prato cadastrado
//        // System.out.println("O prato consultado foi: " + cardapioDao.consultarPorId(2));      // consultando prato cadastrado
//        cardapioDao.consultarTodos().forEach(prato -> System.out.println("O prato consultado foi: " + prato));
//
//        // fechando transação -> MANAGED to DETACHED
//        entityManager.close();      // troca de close para clear, para que funcione o merge
//
//    }
}
