package Model.DAO;

import Model.LancamentosPersonagem;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LancamentosPersonagemDAO {
    private final Configurador config;
 
    public LancamentosPersonagemDAO() {
         config = new Configurador();
     }
    
    private LancamentosPersonagem carrega(ResultSet rs) throws SQLException
	{
		LancamentosPersonagem lp = new LancamentosPersonagem();
		lp.setOperacao(rs.getInt("operacao"));
                //lp.setData(rs.getDateTime("data"));
                lp.setHistorico(rs.getString("historico"));
                lp.setQuantidade(rs.getInt("quantidade"));
		lp.setPrecoUnitario(rs.getDouble("precoUnitario"));
                lp.setIdPersonagem(rs.getInt("idPersonagem"));
                lp.setIdUsuario(rs.getInt("idUsuario"));
                
                return lp;
	}

	//@Override
	public LancamentosPersonagem getlancamentosPersonagemDAO(int id)
	{
		Connection c = config.getConnection();
		
		if (c == null)
			return null;
		
		LancamentosPersonagem lp = null;
		
		try
		{
			PreparedStatement ps = c.prepareStatement("SELECT * FROM lancamentosPersonagem WHERE idUsuario = ?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next())
				lp = carrega(rs);

			c.close();

		} catch (SQLException e)
		{
			config.log(e.getMessage());
		}
		    
		return lp;
	}

	//@Override
	public List<LancamentosPersonagem> lista()
	{
		Connection c = config.getConnection();
		
		if (c == null)
			return null;
		
		List<LancamentosPersonagem> lista = new ArrayList<LancamentosPersonagem>();
		
		try
		{
			PreparedStatement ps = c.prepareStatement("SELECT * FROM lancamentosPersonagem");
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
	public boolean insere(LancamentosPersonagem lp)
	{
		Connection c = config.getConnection();
		
		if (c == null)
			return false;
		
		try
		{
			CallableStatement cs = c.prepareCall("{call InsereToken(?, ?, ?, ?)}");
			cs.setInt(1, lp.getOperacao());
                        //cs.setInt(2, lp.getData());
                        cs.setString(3, lp.getHistorico());
			cs.setInt(4, lp.getQuantidade());
                        cs.setDouble(5, lp.getPrecoUnitario());
                        cs.setInt(6, lp.getIdUsuario());
			cs.setInt(7, lp.getIdPersonagem());
                        
                        cs.execute();
			
			int id = cs.getInt(7);
			lp.setIdUsuario(id);
			
			c.close();
			return true;

		} catch (SQLException e)
		{
			config.log(e.getMessage());
			return false;
		}
	}

	

        //@Override
	public boolean atualiza(LancamentosPersonagem lp)
	{
		Connection c = config.getConnection();
		
		if (c == null)
			return false;
		
		try
		{
//			CallableStatement cs = c.prepareCall("{call AtualizaLancamentosPersonagem(?,?,?,?,?,?,)}");
                        
                        cs.setInt(1, lp.getOperacao());
                        //cs.setInt(2, lp.getData());
                        cs.setString(3, lp.getHistorico());
			cs.setInt(4, lp.getQuantidade());
                        cs.setDouble(5, lp.getPrecoUnitario());
                        cs.setInt(6, lp.getIdUsuario());
			cs.setInt(7, lp.getIdPersonagem());
                        

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
			CallableStatement cs = c.prepareCall("{call RemoveLancamentoPersonagem(?)}");
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