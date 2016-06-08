package Model.DAO;

import Util.Configurador;
import Model.DAO.Interfaces.ITokenDAO;
import Model.Token;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenDAO implements ITokenDAO{

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
    @Override
    public boolean insere(Token tk) {
        Connection c = config.getConnection();

        if (c == null) {
            return false;
        }

        try {                        
            java.sql.Date tokenSqlDate = new java.sql.Date(tk.getDataValidade().toDate().getTime());
            
            CallableStatement cs = c.prepareCall("{call InserirToken(?, ?, ?)}");
            cs.setInt(1, tk.getIdUsuario());
            cs.setString(2, tk.getToken());
            cs.setDate(3, tokenSqlDate);
            cs.execute();

            c.close();
            return true;

        } catch (SQLException e) {
            Configurador.log(e.getMessage());
            return false;
        }
    }
    
    /**
     * Metodo que verifica se token é valido
     * @param idUsuario idUsuario
     * @param token string do token
     * @return Valido: true Invalido: false
     */
    @Override
    public boolean verficaValidadeToken(int idUsuario, String token)
    {
        boolean tokenValido = false;
        
        Connection c = config.getConnection();

        if (c == null) {
            return false;
        }
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Token WHERE idUsuario = ? and Token = ");
            ps.setInt(1, idUsuario);
            ps.setString(1, token);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tokenValido = true;
            }            

            c.close();

        } catch (SQLException e) {
            Configurador.log(e.getMessage());
        }

        return tokenValido;
    }
}
