package br.unipar.api.ApiPillTime.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "Alarme")
@ApiModel(description = "Modelo de Alarme")
public class Alarme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Identificador gerado automaticamente pelo Sistema")
    private Long id;
    private String titulo;
    private String Descricao;
    private Date dtCadastrado;
    private Date dtModificacao;
    private LocalDateTime alarme;
    private boolean statusAlarme;

    public Alarme(Long id, String titulo, String descricao, Date dtCadastrado, Date dtModificacao, LocalDateTime alarme, boolean statusAlarme) {
        this.id = id;
        this.titulo = titulo;
        Descricao = descricao;
        this.dtCadastrado = dtCadastrado;
        this.dtModificacao = dtModificacao;
        this.alarme = alarme;
        this.statusAlarme = statusAlarme;
    }

    public Alarme() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public Date getDtCadastrado() {
        return dtCadastrado;
    }

    public void setDtCadastrado(Date dtCadastrado) {
        this.dtCadastrado = dtCadastrado;
    }

    public Date getDtModificacao() {
        return dtModificacao;
    }

    public void setDtModificacao(Date dtModificacao) {
        this.dtModificacao = dtModificacao;
    }

    public LocalDateTime getAlarme() {
        return alarme;
    }

    public void setAlarme(LocalDateTime alarme) {
        this.alarme = alarme;
    }

    public boolean isStatusAlarme() {
        return statusAlarme;
    }

    public void setStatusAlarme(boolean statusAlarme) {
        this.statusAlarme = statusAlarme;
    }

    @Override
    public String toString() {
        return "Alarme{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", Descricao='" + Descricao + '\'' +
                ", dtCadastrado=" + dtCadastrado +
                ", dtModificacao=" + dtModificacao +
                ", alarme=" + alarme +
                ", statusAlarme=" + statusAlarme +
                '}';
    }
}
