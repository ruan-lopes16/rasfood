package br.com.rasmoo.restaurante.service.test;

import br.com.rasmoo.restaurante.dao.ClienteDao;
import br.com.rasmoo.restaurante.dao.EnderecoDao;
import br.com.rasmoo.restaurante.dao.OrdemDao;
import br.com.rasmoo.restaurante.entity.Ordem;
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

        CargaDeDadosUtil.cadastrarClientes(entityManager);
        CargaDeDadosUtil.cadastrarOrdensClientes(entityManager);

        OrdemDao ordemDao = new OrdemDao(entityManager);
        Ordem ordem = ordemDao.consultarPorId(2);
        //ClienteDao clienteDao = new ClienteDao(entityManager);
        EnderecoDao enderecoDao = new EnderecoDao(entityManager);

        System.out.println(ordemDao.consultarItensMaisVendidos());
        System.out.println(ordem.getValorTotal());  // poderia esse item após o close, pois já foi carregado na linha 24
        //System.out.println(clienteDao.consultarPorNome("COSTA"));
        //System.out.println(enderecoDao.consultarClientes(null, null, "lapa")); // não me retorna nada, ngm mora lá
        System.out.println(enderecoDao.consultarClientesUsandoCriteria(null, null, "lapa"));

        ClienteDao clienteDao = new ClienteDao(entityManager);
        System.out.println(clienteDao.consultarTodos());

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
