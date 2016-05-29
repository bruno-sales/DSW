package Model;

import lombok.Data;
import org.joda.time.DateTime;

public @Data class Usuario {

    private int id;
    private String nome;
    private String telefone;
    private String cpf;
    private String email;
    private String senha;
    private String foto;
    private boolean administrador;
    private int numeroLogins;
    private DateTime ultimoLogin;

    public boolean isAdministrator() {
        return this.administrador;
    }

}
