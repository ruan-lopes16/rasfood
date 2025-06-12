package br.com.rasmoo.restaurante.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity                     // dizendo que está classe é uma entidade
@Table(name = "cardapio")     // "renomeando" a tabela
public class Cardapio {

    @Id                                                         // dizendo ao JPA que o atributo id, é uma primary-key do tipo Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)         // estratégia de geração de Id -> auto-incremento a cada registro
    private Integer id;

    private String nome;
    private String descricao;
    private boolean disponivel;
    private BigDecimal valor;

    @Column(name = "data_de_registro")                                  // renomeando a tabela
    private LocalDateTime dataDeRegistro = LocalDateTime.now();         // dizendo que a data será ao instanciar


    public Cardapio(Integer id, String nome, String descricao, boolean disponivel, BigDecimal valor, LocalDateTime dataDeRegistro) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.disponivel = disponivel;
        this.valor = valor;
        this.dataDeRegistro = dataDeRegistro;
    }

    public Cardapio() {
        // JPA obriga ter um construtor vazio
    }


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

    public boolean isDisponivel() {
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

    @Override
    public String toString() {
        return "Prato{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", disponivel=" + disponivel +
                ", valor=" + valor +
                ", dataDeRegistro=" + dataDeRegistro +
                '}';
    }
}
