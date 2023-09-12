package br.unipar.api.ApiPillTime.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "RemedioIdoso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RemedioIdoso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Remedio remedio;

    @ManyToOne
    private Idoso idoso;

    private boolean stAtivo;

}
