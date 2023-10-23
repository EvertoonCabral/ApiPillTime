package br.unipar.api.ApiPillTime.model;

public enum TipoUsuario {

C("C"), I("I");

private String descricao;

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
