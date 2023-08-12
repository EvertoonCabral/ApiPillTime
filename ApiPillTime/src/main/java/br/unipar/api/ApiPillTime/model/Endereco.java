package br.unipar.api.ApiPillTime.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "Endereco")
@ApiModel(description = "Modelo de Endereço")
public class Endereco {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@ApiModelProperty(notes = "Id gerada automaticamente pelo sistema")
    private Long id;
    @ApiModelProperty(notes = "Nome do Estado", required = true)
    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 255)
private String estado;
    @ApiModelProperty(notes = "Nome da Cidade", required = true)
    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 255)
    private String cidade;
    private String bairro;
    private String numeroResidencia;
    private String complemento;
    @CreationTimestamp
    @Column(updatable = false)
    private Date dataCadastro;

    public Endereco(Long id, String estado, String cidade, String bairro, String numeroResidencia, String complemento, Date dataCadastro) {
        this.id = id;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.numeroResidencia = numeroResidencia;
        this.complemento = complemento;
        this.dataCadastro = dataCadastro;

    }

    public Endereco() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumeroResidencia() {
        return numeroResidencia;
    }

    public void setNumeroResidencia(String numeroResidencia) {
        this.numeroResidencia = numeroResidencia;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", estado='" + estado + '\'' +
                ", cidade='" + cidade + '\'' +
                ", bairro='" + bairro + '\'' +
                ", numeroResidencia='" + numeroResidencia + '\'' +
                ", complemento='" + complemento + '\'' +
                ", dataCadastro=" + dataCadastro +
                '}';
    }
}
