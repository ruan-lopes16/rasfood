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

    // criando categoria
    private Categoria categoria;

    // renomeando uma tabela ex.: dataDeRegistro para data_de_registro
    @Column(name = "data_de_registro")
    private LocalDateTime dataDeRegistro = LocalDateTime.now();     // deixando data já registrada

    // JPA obriga ter um construtor vazio
    public Cardapio() {
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

    // toString - exibição
    @Override
    public String toString() {
        return "Prato{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", disponivel=" + disponivel +
                ", valor=R$" + valor +
                ", dataDeRegistro=" + dataDeRegistro +
                '}';
    }
}
