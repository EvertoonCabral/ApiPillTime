package br.unipar.api.ApiPillTime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Foto")
@ApiModel(description = "Tabela para armazenamento das fotos dos alarmes")
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "foto", nullable = false, columnDefinition = "bytea")
    private byte [] arquivo;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "remedioId")
    private Remedio remedio;


}
