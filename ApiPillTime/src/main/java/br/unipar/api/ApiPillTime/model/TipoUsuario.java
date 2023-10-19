package br.unipar.api.ApiPillTime.model;

public enum TipoUsuario {

C("cuidador"), I("Idoso");

private String descricao;

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
