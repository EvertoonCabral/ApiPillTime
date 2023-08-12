package br.unipar.api.ApiPillTime.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "Telefone")
@ApiModel(description = "Modelo de Telefone")
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "ID gerado automaticamente pelo sistema")
    private Long id;
    private String dd;
    private String telefone;

    public Telefone(Long id, String dd, String telefone) {

        this.id = id;
        this.dd = dd;
        this.telefone = telefone;
    }

    public Telefone() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Telefone{" +
                "id=" + id +
                ", dd='" + dd + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
