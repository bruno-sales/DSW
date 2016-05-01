package Model.DAO;

import Model.Token;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import org.joda.time.DateTime;

public class TokenDAO {

    private final Configurador config;

    public TokenDAO() {
        config = new Configurador();
    }

    /**
     * Metodo para inserir um novo token no banco. 
     * Chama a procedure responsavel por isso.
     * @param tk Objeto Token
     * @return Booleano informando se inseriu ou não 
     */
    public boolean insere(Token tk) {
        Connection c = config.getConnection();

        if (c == null) {
            return false;
        }

        try {            
            DateTime validade = DateTime.now().plusDays(1);
            
            CallableStatement cs = c.prepareCall("{call InserirToken(?, ?, ?)}");
            cs.setInt(1, tk.getIdUsuario());
            cs.setString(2, tk.getToken());
            cs.setString(3, validade.toString()); //Isso vai dar merda. data na procedure é Date, não String. VERIFICAR
            cs.execute();

            c.close();
            return true;

        } catch (SQLException e) {
            Configurador.log(e.getMessage());
            return false;
        }
    }
}
