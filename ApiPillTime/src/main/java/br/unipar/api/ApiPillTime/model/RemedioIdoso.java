package br.unipar.api.ApiPillTime.model;

import javax.persistence.*;

@Entity
@Table(name = "RemedioIdoso")
public class RemedioIdoso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Remedio remedio;

    @ManyToOne
    private Idoso idoso;

    // campos adicionais específicos da relação, como dosagem, horários, etc.
}

