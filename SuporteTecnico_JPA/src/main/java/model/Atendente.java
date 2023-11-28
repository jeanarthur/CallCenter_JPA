package model;

import javax.persistence.*;
import java.util.List;
@Entity
public class Atendente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long matricula;
    @Column
    String nome;
    @Column
    String email;
    @Column
    String senha;
    @OneToMany
    @JoinTable(name = "ticket_atendente",
               joinColumns = @JoinColumn(name = "atendente_id"),
               inverseJoinColumns = @JoinColumn(name = "ticket_id"))
    List<Ticket> tickets;

    public Atendente() {
    }

    public Atendente( String nome, String email, String senha) {;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Atendente{" +
                "matricula=" + matricula +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", tickets=" + tickets +
                '}';
    }
}
