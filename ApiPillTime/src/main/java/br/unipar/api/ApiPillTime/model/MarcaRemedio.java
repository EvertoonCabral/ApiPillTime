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
@Table(name = "MarcaRemedio")
@ApiModel(description = "Modelo de marcaRemedio")
public class MarcaRemedio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Id gerado automaticamente pelo sistema")
    private Long id;
    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 255)
    private String nome;
    @CreationTimestamp
    @Column(updatable = false)
    private Date dataCadastro;

    private boolean stAtivo;

    public MarcaRemedio(Long id, String nome, Date dataCadastro, boolean stAtivo) {
        this.id = id;
        this.nome = nome;
        this.dataCadastro = dataCadastro;
        this.stAtivo = stAtivo;
    }

    public MarcaRemedio( ) {
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

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public boolean isStAtivo() {
        return stAtivo;
    }

    public void setStAtivo(boolean stAtivo) {
        this.stAtivo = stAtivo;
    }
}
