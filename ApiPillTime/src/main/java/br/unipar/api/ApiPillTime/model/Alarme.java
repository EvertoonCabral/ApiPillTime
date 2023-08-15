package br.unipar.api.ApiPillTime.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

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
    @ManyToOne
    @JoinColumn(name = "IdosoId")
    private Idosos idoso;
    @OneToMany
    private List<Remedio> RemediosIdosos;
    private LocalDateTime alarme;
    private boolean statusAlarme;

    public Alarme(Long id, String titulo, String descricao, Date dtCadastrado, Idosos idoso, LocalDateTime alarme, boolean statusAlarme) {
        this.id = id;
        this.titulo = titulo;
        Descricao = descricao;
        this.dtCadastrado = dtCadastrado;
        this.idoso = idoso;
        this.alarme = alarme;
        this.statusAlarme = statusAlarme;
    }

    public Idosos getIdoso() {
        return idoso;
    }

    public void setIdoso(Idosos idoso) {
        this.idoso = idoso;
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

}
