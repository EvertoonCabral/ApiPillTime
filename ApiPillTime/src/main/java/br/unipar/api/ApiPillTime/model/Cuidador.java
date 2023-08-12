package br.unipar.api.ApiPillTime.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Cuidador")
@ApiModel(description = "Modelo para a representação de um cuidador")
public class Cuidador extends Pessoa {


    private List<Remedio> listaRemedio;
    private List<Idosos> listaResposabilidade;

    private List<Alarme> listaAlarmes;


    public Cuidador(Long id, String nome, Endereco endereco, Date dataNascimento, Date dataCadastro, String cpf, br.unipar.api.ApiPillTime.model.Telefone telefone, boolean stAtivo, List<Remedio> listaRemedio, List<Idosos> listaResposabilidade, List<Alarme> listaAlarmes) {
        super(id, nome, endereco, dataNascimento, dataCadastro, cpf, telefone, stAtivo);
        this.listaRemedio = listaRemedio;
        this.listaResposabilidade = listaResposabilidade;
        this.listaAlarmes = listaAlarmes;
    }

    public List<Remedio> getListaRemedio() {
        return listaRemedio;
    }

    public void setListaRemedio(List<Remedio> listaRemedio) {
        this.listaRemedio = listaRemedio;
    }

    public List<Idosos> getListaResposabilidade() {
        return listaResposabilidade;
    }

    public void setListaResposabilidade(List<Idosos> listaResposabilidade) {
        this.listaResposabilidade = listaResposabilidade;
    }

    public List<Alarme> getListaAlarmes() {
        return listaAlarmes;
    }

    public void setListaAlarmes(List<Alarme> listaAlarmes) {
        this.listaAlarmes = listaAlarmes;
    }

    @Override
    public String toString() {
        return "Cuidador{" +
                "listaRemedio=" + listaRemedio +
                ", listaResposabilidade=" + listaResposabilidade +
                ", listaAlarmes=" + listaAlarmes +
                '}';
    }
}
