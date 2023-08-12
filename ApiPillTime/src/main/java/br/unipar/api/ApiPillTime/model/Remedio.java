package br.unipar.api.ApiPillTime.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "Remedio")
@ApiModel(description = "Modelo para representação de Remedio")
public class Remedio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Id autogerado pelo sistema")
    private Long id;
    @ApiModelProperty(notes = "Nome do Remedio", required = true)
    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 255)
    private String nome;
    @ManyToOne
    private MarcaRemedio marcaRemedio;
    private String dosagem;
    private String formaFarmaceutico;
    @CreationTimestamp
    @Column(updatable = false)
    private Date dataCadastro;
    @UpdateTimestamp
    private Date dataValidade;
    private String observacoes;

    private boolean stAtivo;


    public Remedio(Long id, String nome, MarcaRemedio marcaRemedio, String dosagem, String formaFarmaceutico, Date dataCadastro, Date dataValidade, String observacoes, boolean stAtivo) {
        this.id = id;
        this.nome = nome;
        this.marcaRemedio = marcaRemedio;
        this.dosagem = dosagem;
        this.formaFarmaceutico = formaFarmaceutico;
        this.dataCadastro = dataCadastro;
        this.dataValidade = dataValidade;
        this.observacoes = observacoes;
        this.stAtivo = stAtivo = true;
    }

    public Remedio() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public MarcaRemedio getMarcaRemedio() {
        return marcaRemedio;
    }

    public void setMarcaRemedio(MarcaRemedio marcaRemedio) {
        this.marcaRemedio = marcaRemedio;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public String getFormaFarmaceutico() {
        return formaFarmaceutico;
    }

    public void setFormaFarmaceutico(String formaFarmaceutico) {
        this.formaFarmaceutico = formaFarmaceutico;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public boolean isStAtivo() {
        return stAtivo;
    }

    public void setStAtivo(boolean stAtivo) {
        this.stAtivo = stAtivo;
    }

    @Override
    public String toString() {
        return "Remedio{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", marcaRemedio=" + marcaRemedio +
                ", dosagem='" + dosagem + '\'' +
                ", formaFarmaceutico='" + formaFarmaceutico + '\'' +
                ", dataCadastro=" + dataCadastro +
                ", dataValidade=" + dataValidade +
                ", observacoes='" + observacoes + '\'' +
                '}';
    }
}
