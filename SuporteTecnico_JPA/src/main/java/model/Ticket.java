package model;

import javax.persistence.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Ticket {
    @Id
    String protocolo;
    @Column(name = "tipoticket")
    String tipo;
    @Column(name = "descticket")
    String descricao;
    @Column(name = "dataticket")
    Date data;
    @Column(name = "statusticket")
    String status;
    @ManyToOne
    @JoinColumn(name = "cpf_cliente")
    Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "matricula_atendente")
    Atendente atendente;

    public Ticket() {
    }

    public Ticket(String protocolo, String tipo, String descricao, Date data, String status, Cliente cliente) {
        this.protocolo = protocolo;
        this.tipo = tipo;
        this.descricao = descricao;
        this.data = data;
        this.status = status;
        this.cliente = cliente;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "protocolo='" + protocolo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                ", status='" + status + '\'' +
                ", cliente=" + cliente +
                ", atendente=" + atendente +
                '}';
    }
}
