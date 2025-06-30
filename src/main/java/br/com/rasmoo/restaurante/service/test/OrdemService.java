package br.com.rasmoo.restaurante.service.test;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.dao.ClienteDao;
import br.com.rasmoo.restaurante.dao.OrdemDao;
import br.com.rasmoo.restaurante.entity.Cliente;
import br.com.rasmoo.restaurante.entity.Ordem;
import br.com.rasmoo.restaurante.entity.OrdensCardapio;
import br.com.rasmoo.restaurante.util.CargaDeDadosUtil;
import br.com.rasmoo.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;

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
        ClienteDao clienteDao = new ClienteDao(entityManager);
        OrdemDao ordemDao = new OrdemDao(entityManager);

        Cliente ruan = new Cliente("12345678900", "Ruan", "99988877");
        Ordem ordem = new Ordem(ruan);
        // Adiciona item ao pedido: produto com ID 1 e quantidade 2
        ordem.addOrdensCardapio(new OrdensCardapio(ordem, cardapioDao.consultarPorId(1), 2));

        clienteDao.cadastrar(ruan);
        ordemDao.cadastrar(ordem);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
