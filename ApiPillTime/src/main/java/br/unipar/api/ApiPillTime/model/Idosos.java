package br.unipar.api.ApiPillTime.model;

import io.swagger.annotations.ApiModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Idoso")
@ApiModel(description = "Modelo de representação de um idoso")
public class Idosos {

    private List<Alarme> alarmesIdoso;
    private List<Remedio> RemediosIdosos;
    private Cuidador responsavel;
    private String observacao;

    public Idosos(List<Alarme> alarmesIdoso, List<Remedio> remediosIdosos, Cuidador responsavel, String observacao) {
        this.alarmesIdoso = alarmesIdoso;
        RemediosIdosos = remediosIdosos;
        this.responsavel = responsavel;
        this.observacao = observacao;
    }

    public List<Alarme> getAlarmesIdoso() {
        return alarmesIdoso;
    }

    public void setAlarmesIdoso(List<Alarme> alarmesIdoso) {
        this.alarmesIdoso = alarmesIdoso;
    }

    public List<Remedio> getRemediosIdosos() {
        return RemediosIdosos;
    }

    public void setRemediosIdosos(List<Remedio> remediosIdosos) {
        RemediosIdosos = remediosIdosos;
    }

    public Cuidador getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Cuidador responsavel) {
        this.responsavel = responsavel;
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
                "alarmesIdoso=" + alarmesIdoso +
                ", RemediosIdosos=" + RemediosIdosos +
                ", responsavel=" + responsavel +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}
