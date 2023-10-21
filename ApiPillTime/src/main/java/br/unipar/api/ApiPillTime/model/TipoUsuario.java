package br.unipar.api.ApiPillTime.model;

public enum TipoUsuario {

C(1), I(2);

private int descricao;

    TipoUsuario(int descricao) {
        this.descricao = descricao;
    }

    public int getDescricao() {
        return descricao;
    }
}
