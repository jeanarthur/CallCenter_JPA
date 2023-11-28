package model;

import javax.persistence.*;
import java.util.List;
@Entity
public class Cliente {
    @Id
    long cpf;
    @Column(name = "nome")
    String nome;
    @Column(name = "email")
    String email;
    @Column(name = "senhacliente")
    String senha;
    @Column(name = "telefone")
    Integer telefone;
    @Column(name = "endereco")
    String endereco;
    @OneToMany
    @JoinTable(name = "ticket_cliente",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_id"))
    List<Ticket> tickets;

    public Cliente() {
    }

    public Cliente(Long cpf, String nome, String email, String senha, Integer telefone, String endereco) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Integer cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "cpf=" + cpf +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", telefone=" + telefone +
                ", endereco='" + endereco + '\'' +
                ", tickets=" + tickets +
                '}';
    }
}
