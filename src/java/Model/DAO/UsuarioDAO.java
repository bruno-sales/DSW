package Model.DAO;

import Model.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            //private boolean administrator;
            //private DateTime ultimoLogin;
                
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
			config.log(e.getMessage());
		}
		    
		return usuario;
	}

	
	public List<Usuario> lista()
	{
		Connection c = config.getConnection();
		
		if (c == null)
			return null;
		
		List<Usuario> lista = new ArrayList<Usuario>();
		
		try
		{
			PreparedStatement ps = c.prepareStatement("SELECT * FROM CompactDisc");
			ResultSet rs = ps.executeQuery();

			while (rs.next())
				lista.add(carrega(rs));

			c.close();

		} catch (SQLException e)
		{
			config.log(e.getMessage());
		}
		    
		return lista;
	}

	//@Override
	public boolean insere(Usuario usuario)
	{
		Connection c = config.getConnection();
		
		if (c == null)
			return false;
		
		try
		{
			CallableStatement cs = c.prepareCall("{call InsereU(?, ?, ?, ?)}");
			cs.setInt(1, usuario.getId());
			cs.setString(2, usuario.getNome());
			cs.setString(3, usuario.getTelefone());
                        cs.setString(4, usuario.getCpf());
                        cs.setString(5, usuario.getEmail());
                        cs.setString(6, usuario.getSenha());
                        cs.setString(7, usuario.getFoto());
                       // cs.setDouble(8, usuario.getTelefone());
                      //  cs.setDouble(9, usuario.getTelefone());
			cs.execute();
			
			int id = cs.getInt(1);
			usuario.setId(id);
			
			c.close();
			return true;

		} catch (SQLException e)
		{
			config.log(e.getMessage());
			return false;
		}
	}

	

        //@Override
	public boolean atualiza(Usuario usuario)
	{
		Connection c = config.getConnection();
		
		if (c == null)
			return false;
		
		try
		{
			CallableStatement cs = c.prepareCall("{call AtualizaCompactDisc(?, ?, ?, ?)}");
			cs.setString(2, usuario.getNome());
			cs.setString(3, usuario.getTelefone());
                        cs.setString(4, usuario.getCpf());
                        cs.setString(5, usuario.getEmail());
                        cs.setString(6, usuario.getSenha());
                        cs.setString(7, usuario.getFoto());
                       // cs.setDouble(8, usuario.getTelefone());
                       // cs.setDouble(9, usuario.getTelefone());
			cs.execute();
			c.close();
			return true;

		} catch (SQLException e)
		{
			config.log(e.getMessage());
			return false;
		}
	}

	
	//@Override
	public boolean remove(int id)
	{
		Connection c = config.getConnection();
		
		if (c == null)
			return false;
		
		try
		{
			CallableStatement cs = c.prepareCall("{call RemoveUsuario(?)}");
			cs.setInt(1, id);
			cs.execute();	
			c.close();
			return true;

		} catch (SQLException e)
		{
			config.log(e.getMessage());
			return false;
		}
	}
}