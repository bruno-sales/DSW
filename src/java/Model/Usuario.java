package Model;

import br.unirio.simplemvc.servlets.IUser;
import lombok.Data;
import org.joda.time.DateTime;

public @Data
class Usuario implements IUser {

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

    @Override
    public String getName() {
        return nome;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public boolean mustChangePassword() {
        //TODO
        return false;
    }

    @Override
    public boolean checkLevel(String nivel) {
        return true;
    }
}
