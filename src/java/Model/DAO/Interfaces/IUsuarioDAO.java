package Model.DAO.Interfaces;

import Model.Usuario;
import java.util.List;

public interface IUsuarioDAO {
    
    public Usuario getUsuarioPorId(int id);
    public Usuario getUsuarioPorEmail(String email);
    public List<Usuario> lista();
    public boolean inserir(Usuario usuario);
    public boolean atualizar(Usuario usuario);
    public boolean indicarLoginFalha(int idUsuario);
    public boolean indicarLoginSucesso(int idUsuario);
    public boolean TrocarSenha(int idUsuario, String senha);
    public boolean verificaLogin(int idUsuario, String senha);
}
