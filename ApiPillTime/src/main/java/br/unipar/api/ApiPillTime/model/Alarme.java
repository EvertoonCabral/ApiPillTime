package br.unipar.api.ApiPillTime.model;

import br.unipar.api.ApiPillTime.repository.PessoaRepository;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "Alarme")
@ApiModel(description = "Modelo de Alarme")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Alarme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Identificador gerado automaticamente pelo Sistema")
    private Long id;
    private String titulo;
    private String Descricao;
    private Date dtCadastrado;
    @ManyToOne
    @JoinColumn(name = "IdosoId")
    private Idosos idoso;
    @OneToMany
    private List<Remedio> RemediosIdosos;
    private LocalDateTime alarme;
    private boolean statusAlarme;



}
