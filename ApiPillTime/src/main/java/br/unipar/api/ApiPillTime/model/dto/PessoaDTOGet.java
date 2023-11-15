package br.unipar.api.ApiPillTime.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class PessoaDTOGet {


        private Long id;
        private String nome;
        private String email;
        @JsonFormat(pattern = "dd/MM/yyyy")
        private java.util.Date dataNascimento;
        private String cpf;
        private String Telefone;


    }


