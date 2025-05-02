package br.com.rasmoo.restaurante.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cardapio")
public class Cardapio {

    @Id // dizendo que o atributo é um id + definindo primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // estrategia de geração de id -> h2 não aceita SEQUENCE (essa notação faz gerar id's: 1, 2, 3, 4...)
    private Integer id;

    private String nome;
    private String descricao;
    private boolean disponivel;
    private BigDecimal valor;   // BigDecimal -> utilizado para cálculos decimais de precisão

    /* Relacionamentos

    - ManyToOne
    - ManyToMany
    - OneToMany
    - OneToOne
     */

    // criando categoria
    @ManyToOne
    private Categoria categoria;

    // renomeando uma tabela ex.: dataDeRegistro para data_de_registro
    @Column(name = "data_de_registro")
    private LocalDateTime dataDeRegistro = LocalDateTime.now();     // deixando data já registrada

    // JPA obriga ter um construtor vazio
    public Cardapio() {
    }

    public Cardapio(String nome, String descricao, boolean disponivel, BigDecimal valor, Categoria categoria, LocalDateTime dataDeRegistro) {
        this.nome = nome;
        this.descricao = descricao;
        this.disponivel = disponivel;
        this.valor = valor;
        this.categoria = categoria;
        this.dataDeRegistro = dataDeRegistro;
    }

    // getters e setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isDisponivel(boolean b) {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataDeRegistro() {
        return dataDeRegistro;
    }

    public void setDataDeRegistro(LocalDateTime dataDeRegistro) {
        this.dataDeRegistro = dataDeRegistro;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    // toString - exibição

    @Override
    public String toString() {
        return "Cardapio{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", disponivel=" + disponivel +
                ", valor=" + valor +
                ", categoria=" + categoria +
                ", dataDeRegistro=" + dataDeRegistro +
                '}';
    }
}
