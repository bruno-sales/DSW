package Model;

import java.sql.Blob;
import lombok.Data;
import org.joda.time.DateTime;

public @Data class Usuario {

    private int idUsuario;
    private String nome;
    private String telefone;
    private String cpf;
    private String email;
    private String senha;
    private Blob foto;
    private boolean administrator;
    private int numeroLogins;
    private DateTime ultimoLogin;

    public boolean isAdministrator() {
        return this.administrator;
    }

}
