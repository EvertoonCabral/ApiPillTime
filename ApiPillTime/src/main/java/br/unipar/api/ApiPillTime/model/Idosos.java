package br.unipar.api.ApiPillTime.model;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Idoso")
@ApiModel(description = "Modelo de representação de um idoso")
public class Idosos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Alarme> alarmesIdoso;


    @ManyToOne
    @JoinColumn(name = "CuidadorId")
    private Cuidador cuidador;
    private String observacao;
    private boolean stAtivo;

    public Idosos(Long id, List<Alarme> alarmesIdoso, Cuidador cuidador, String observacao, boolean stAtivo) {
        this.id = id;
        this.alarmesIdoso = alarmesIdoso;
        this.cuidador = cuidador;
        this.observacao = observacao;
        this.stAtivo = stAtivo;
    }

    public Idosos() {

    }

    public boolean isStAtivo() {
        return stAtivo;
    }

    public void setStAtivo(boolean stAtivo) {
        this.stAtivo = stAtivo;
    }

    public List<Alarme> getAlarmesIdoso() {
        return alarmesIdoso;
    }

    public void setAlarmesIdoso(List<Alarme> alarmesIdoso) {
        this.alarmesIdoso = alarmesIdoso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cuidador getCuidador() {
        return cuidador;
    }

    public void setCuidador(Cuidador responsavel) {
        this.cuidador = responsavel;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "Idosos{" +
                "id=" + id +
                ", alarmesIdoso=" + alarmesIdoso +
                ", cuidador=" + cuidador +
                ", observacao='" + observacao + '\'' +
                ", stAtivo=" + stAtivo +
                '}';
    }
}
