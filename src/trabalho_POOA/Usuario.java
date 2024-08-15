package trabalho_POOA;

public class Usuario {
    private String user;
    private String senha;

    public Usuario(String user, String senha) {
        this.user = user;
        this.senha = senha;
    }

    public boolean autenticar(String user, String senha) {
        return this.user.equals(user) && this.senha.equals(senha);
    }
}