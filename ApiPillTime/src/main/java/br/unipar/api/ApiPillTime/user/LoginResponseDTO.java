package br.unipar.api.ApiPillTime.user;

public class LoginResponseDTO {


    private String token;

    private String login;

    private String nomePessoaLogin;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String token) {
        this.token = token;
    }

    public LoginResponseDTO(String token, String login, String nomePessoaLogin) {
        this.token = token;
        this.login = login;
        this.nomePessoaLogin = nomePessoaLogin;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNomePessoaLogin() {
        return nomePessoaLogin;
    }

    public void setNomePessoaLogin(String nomePessoaLogin) {
        this.nomePessoaLogin = nomePessoaLogin;
    }

}
