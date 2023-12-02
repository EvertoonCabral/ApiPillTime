package br.unipar.api.ApiPillTime.model;

import br.unipar.api.ApiPillTime.model.dto.AlarmeDTOInsert;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Table(name = "Foto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "foto", nullable = false, columnDefinition = "bytea")
    private byte [] arquivo;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "alarmeId")
    private Alarme alarme;

}
