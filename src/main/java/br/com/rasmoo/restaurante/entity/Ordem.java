package br.com.rasmoo.restaurante.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    /*
    ALL = realiza todas as operações em cascata
    DETACH = executa no pai e no filho
    MERGE = salva pai e filho, podendo já haver entidade gerenciada
    PERSIST = cria pai e filho
    REFRESH = atualiza entidade com operações do banco
    REMOVE = propaga remoção entre pai e filho
    */

    @OneToMany(mappedBy = "ordem", cascade = CascadeType.ALL)                                                      // quando se trata de relacionamento bidirecional, o mappedBy vai para o ToMany
    private List<OrdensCardapio> ordensCardapioList = new ArrayList<>();                // neste caso OrdensCardapio precisa ter visão de Ordem // lista de itens do cardápio associados à essa ordem.
    // quando se trata de um List ou Set, é uma boa prátrica já instânciá-la na declaração de atributos -> evita erros

    public Ordem() {
    }

    public Ordem(Cliente cliente) {
        this.cliente = cliente;
    }

    //
    public void addOrdensCardapio(OrdensCardapio ordensCardapio){
        ordensCardapio.setOrdem(this);                          // Define a Ordem a qual este item de cardápio pertence
        this.ordensCardapioList.add(ordensCardapio);            // Adiciona o item de cardápio à lista interna da Ordem
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

    public List<OrdensCardapio> getOrdensCardapioList() {
        return ordensCardapioList;
    }

    public void setOrdensCardapioList(List<OrdensCardapio> ordensCardapioList) {
        this.ordensCardapioList = ordensCardapioList;
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
