package Model.DAO;

import Model.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

public class UsuarioDAO {
    
    private final Configurador config;
 
    public UsuarioDAO() {
         config = new Configurador();
    }
    
    private Usuario carrega(ResultSet rs) throws SQLException
	{
		Usuario user = new Usuario();
		user.setId(rs.getInt("id"));
		user.setNome(rs.getString("nome"));
		user.setTelefone(rs.getString("telefone"));
		user.setCpf(rs.getString("cpf"));
                user.setEmail(rs.getString("email"));
                user.setSenha(rs.getString("senha"));
                user.setFoto(rs.getString("foto"));
                user.setAdministrador(rs.getBoolean("administrador"));
                user.setNumeroLogins(rs.getInt("numeroLogins"));
                user.setUltimoLogin(DateTime.parse(rs.getString("ultimoLogin")));
                
		return user;
	}

	public Usuario getUsuarioPorId(int id)
	{
		Connection c = config.getConnection();
		
		if (c == null)
			return null;
		
		Usuario usuario = null;
		
		try
		{
			PreparedStatement ps = c.prepareStatement("SELECT * FROM Usuarios WHERE id = ?");
			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next())
				usuario = carrega(rs);

			c.close();

		} catch (SQLException e)
		{
			Configurador.log(e.getMessage());
		}
		    
		return usuario;
	}

	
	public List<Usuario> lista()
	{
		Connection c = config.getConnection();
		
		if (c == null)
			return null;
		
		List<Usuario> lista = new ArrayList<>();
		
		try
		{
			PreparedStatement ps = c.prepareStatement("SELECT * FROM Usuarios");
			ResultSet rs = ps.executeQuery();

			while (rs.next())
				lista.add(carrega(rs));

			c.close();

		} catch (SQLException e)
		{
			Configurador.log(e.getMessage());
		}
		    
		return lista;
	}

	public boolean inserir(Usuario usuario)
	{
		Connection c = config.getConnection();
		
		if (c == null)
			return false;
		
		try
		{
			CallableStatement cs = c.prepareCall("{call InserirUsuario(?, ?, ?, ?, ?)}");
			cs.setString(1, usuario.getNome());
			cs.setString(2, usuario.getTelefone());
                        cs.setString(3, usuario.getCpf());
                        cs.setString(4, usuario.getEmail());
                        cs.setString(5, usuario.getSenha());
			cs.execute();
			
			c.close();
			return true;

		} catch (SQLException e)
		{
			Configurador.log(e.getMessage());
			return false;
		}
	}
	
	public boolean atualizar(Usuario usuario)
	{
		Connection c = config.getConnection();
		
		if (c == null)
			return false;
		
		try
		{
			CallableStatement cs = c.prepareCall("{call EditarUsuario(?, ?, ?, ?, ?)}");
			cs.setInt(1, usuario.getId());
                        cs.setString(2, usuario.getNome());
			cs.setString(3, usuario.getTelefone());
                        cs.setString(4, usuario.getCpf());
                        cs.setString(5, usuario.getFoto());
			cs.execute();
			c.close();
			return true;

		} catch (SQLException e)
		{
			Configurador.log(e.getMessage());
			return false;
		}
	}
        
        public boolean indicarLoginFalha(int idUsuario)
        {
            Connection c = config.getConnection();
		
		if (c == null)
			return false;
		
                try{
                        CallableStatement cs = c.prepareCall("{call IndicarLoginFalha(?)}");
			cs.setInt(1, idUsuario);
                        
                        return true;
                        
                } catch (SQLException e)
                {
                        Configurador.log(e.getMessage());
			return false;
                }
        }
        
        public boolean indicarLoginSucesso(int idUsuario)
        {
                Connection c = config.getConnection();
		
		if (c == null)
			return false;
		
                try{
                        CallableStatement cs = c.prepareCall("{call IndicarLoginSucesso(?)}");
			cs.setInt(1, idUsuario);
                        
                        return true;
                        
                } catch (SQLException e)
                {
                        Configurador.log(e.getMessage());
			return false;
                }
        }
        
        public boolean TrocarSenha(int idUsuario, String senha)
        {
                Connection c = config.getConnection();
		
		if (c == null)
			return false;
		
                try{
                        CallableStatement cs = c.prepareCall("{call TrocarSenha(?,?)}");
			cs.setInt(1, idUsuario);
                        cs.setString(2, senha);
                        
                        return true;
                        
                } catch (SQLException e)
                {
                        Configurador.log(e.getMessage());
			return false;
                }
        }
}