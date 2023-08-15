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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @OneToMany
   private List<Remedio> listaRemedio;

    @OneToMany
    private List<Idosos> listaResposabilidade;

//    @ManyToMany
//    private List<Alarme> listaAlarmes;


    public Cuidador(Long id, String nome, Endereco endereco, Date dataNascimento, Date dataCadastro, String cpf, br.unipar.api.ApiPillTime.model.Telefone telefone, boolean stAtivo, List<Idosos> listaResposabilidade) {
        super(id, nome, endereco, dataNascimento, dataCadastro, cpf, telefone, stAtivo);
        this.listaResposabilidade = listaResposabilidade;
    }

    public Cuidador() {
        super();
    }
    public List<Idosos> getListaResposabilidade() {
        return listaResposabilidade;
    }

    public void setListaResposabilidade(List<Idosos> listaResposabilidade) {
        this.listaResposabilidade = listaResposabilidade;
    }
}
