package br.com.rasmoo.restaurante.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {

//    @Id                 // id não precisa ser exclusivamente um Integer, Long, GenerationType, etc
//    private String cpf;
    @EmbeddedId
    private ClienteId clienteId;

    private String nome;

    @Embedded       // tomar cuidado! aqui estou indicando que está classe vai pegar os dados da outra
    private Contato contato;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecoList = new ArrayList<>();

    public Cliente(String cpf, String email, String nome) {
        this.clienteId = new ClienteId(email, cpf);
        this.nome = nome;
    }

    public Cliente() {
    }

    public void addEndereco(Endereco endereco){
        endereco.setCliente(this);
        this.enderecoList.add(endereco);
    }


    public String getCpf() {
        return clienteId.getCpf();
    }

    public void setCpf(String cpf) {
        this.clienteId.setCpf(cpf);
    }

    public String getEmail() {
        return clienteId.getEmail();
    }

    public void setEmail(String email) {
        this.clienteId.setEmail(email);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public List<Endereco> getEnderecoList() {
        return enderecoList;
    }

    public void setEnderecoList(List<Endereco> enderecoList) {
        this.enderecoList = enderecoList;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "cpf='" + clienteId.getCpf() + '\'' +
                "email='" + clienteId.getEmail() + '\'' +
                ", nome='" + nome + '\'' +
                ", contato=" + contato +
                ", enderecoList=" + enderecoList +
                '}';
    }
}