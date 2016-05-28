package Model.DAO.Interfaces;

import Model.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IUsuarioDAO {
    
    public Usuario getUsuarioPorId(int id);
    public List<Usuario> lista();
    public boolean inserir(Usuario usuario);
    public boolean atualizar(Usuario usuario);
    public boolean indicarLoginFalha(int idUsuario);
    public boolean indicarLoginSucesso(int idUsuario);
    public boolean TrocarSenha(int idUsuario, String senha);
    public Usuario verificaLogin(String email, String senha);
}
