/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO.Interfaces;

import Model.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Hazël § Rebecca
 */
public interface IUsuarioDAO {
    
    public Usuario getUsuarioPorId(int id);
    public Usuario carrega(ResultSet rs) throws SQLException; //muda no UsuarioDAO tbm?
    public List<Usuario> lista();
    public boolean inserir(Usuario usuario);
    public boolean atualizar(Usuario usuario);
    public boolean indicarLoginFalha(int idUsuario);
    public boolean indicarLoginSucesso(int idUsuario);
    public boolean TrocarSenha(int idUsuario, String senha);
    
}
