package br.com.rasmoo.restaurante.service.test;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.util.CargaDeDadosUtil;
import br.com.rasmoo.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class OrdemService {
    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManagerRasfood();
        entityManager.getTransaction().begin();
        // 1. Primeiro, cadastramos as categorias
        CargaDeDadosUtil.cadastarCategorias(entityManager);
        // 2. Depois, cadastramos os produtos que dependem das categorias
        CargaDeDadosUtil.cadastrarProdutosCardapio(entityManager);

        // 3. Depois, criamos o cliente, ordem e itens da ordem
        CardapioDao cardapioDao = new CardapioDao(entityManager);
        System.out.println("Lista de produtos por valor: " + cardapioDao.consultarPorValor(BigDecimal.valueOf(59.00)));
        entityManager.close();
    }
}
