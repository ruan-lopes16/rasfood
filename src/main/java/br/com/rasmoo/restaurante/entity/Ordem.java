package br.com.rasmoo.restaurante.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ordens")
public class Ordem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    @Column(name = "data_de_criacao")
    private LocalDateTime dataDeCriacao = LocalDateTime.now();

    @ManyToOne // muitos pedidos para um Cliente
    private Cliente cliente;

//    @ManyToMany     // muitos cardapio(itens) para muitas Ordens - todo toMany precisa estar vinculado à uma lista(List<> ou Set<>)
//    @JoinTable(     // tabela de junção entre ordem e cardapio (tabela intermediária) -> "casamento" entre Ordem e Cardapio
//            name = "ordens_cardapio",   // nome dessa tabela -> irá conter IDs de ordem e cardapio
//            joinColumns = @JoinColumn(name = "ordens_id"),   // coluna que representa a chave estrangeira da entidade Ordem -> faz referencia a entidade atual // nome do id que está vindo da tabela(@Table seguido de id) -> coluna da tabela que está sendo referenciada // chave estrangeira (foreign key) que aponta para o id da tabela Ordem.
//            inverseJoinColumns = @JoinColumn(name = "cardapio_id")    // coluna que representa a chave estrangeira da entidade Cardapio // tabela target - tabela que estamos linkando // foreign key que aponta para o id da tabela Cardapio.
//    )

    /* parte comentada acima:

    entidade foi criada com mais atributos
    obs.: um relacionamento Many to Many, pode ser representado(diagrama) como One to Many e Many to One
     */

    @OneToMany
    private List<OrdensCardapio> ordensCardapioList;    // neste caso OrdensCardapio precisa ter visão de Ordem // lista de itens do cardápio associados à essa ordem.

    public Ordem() {
    }

    public Ordem(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(LocalDateTime dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Ordem{" +
                "id=" + id +
                ", valorTotal=" + valorTotal +
                ", dataDeCriacao=" + dataDeCriacao +
                ", cliente=" + cliente +
                '}';
    }
}
