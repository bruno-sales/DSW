package Model.DAO;

import Model.Enums.EnumOperacao;
import Model.LancamentosDinheiro;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LancamentosDinheiroDAO {
    private final Configurador config;
 
    public LancamentosDinheiroDAO() {
         config = new Configurador();
     }
    
    private LancamentosDinheiro carrega(ResultSet rs) throws SQLException
	{
		LancamentosDinheiro ld = new LancamentosDinheiro();
		ld.setId(rs.getInt("id"));
		ld.setIdUsuario(rs.getInt("idUsuario"));
                ld.setHistorico(rs.getString("historico"));
                ld.setValor(rs.getFloat("valor"));
                
                switch(rs.getInt("operacao"))
                {
                    case 0:
                    ld.setOperacao(EnumOperacao.CREDITO);
                        break;
                    case 1:
                    ld.setOperacao(EnumOperacao.DEBITO);
                        break;
                    case 2:
                    ld.setOperacao(EnumOperacao.BLOQUEIO);
                        break;
                    case 3:
                    ld.setOperacao(EnumOperacao.DESBLOQUEIO);
                        break;
                }
                
		return ld;
	}

	public LancamentosDinheiro getlancamentoDinheiroDAO(int id)
	{
		Connection c = config.getConnection();
		
		if (c == null)
			return null;
		
		LancamentosDinheiro ld = null;
		
		try
		{
			PreparedStatement ps = c.prepareStatement("SELECT * FROM lancamentosDinheiro WHERE id = ?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next())
				ld = carrega(rs);

			c.close();

		} catch (SQLException e)
		{
			Configurador.log(e.getMessage());
		}
		    
		return ld;
	}

	//@Override
	public List<LancamentosDinheiro> lista()
	{
		Connection c = config.getConnection();
		
		if (c == null)
			return null;
		
		List<LancamentosDinheiro> lista = new ArrayList<>();
		
		try
		{
			PreparedStatement ps = c.prepareStatement("SELECT * FROM lancamentosDinheiro");
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

	//@Override
	public boolean insere(LancamentosDinheiro ld)
	{
		Connection c = config.getConnection();
		
		if (c == null)
			return false;
		
		try
		{
			CallableStatement cs = c.prepareCall("{call InsereToken(?, ?, ?, ?)}");
			cs.setInt(1, ld.getId());
			cs.setInt(2, ld.getIdUsuario());
			cs.setString(3, ld.getHistorico());
                        cs.setDouble(4, ld.getValor());
                        cs.setInt(5, ld.getOperacao().getValor());
//                        cs.setDateTime(4, ld.getData());
                        
                        cs.execute();
			
			int id = cs.getInt(1);
			ld.setId(id);
			
			c.close();
			return true;

		} catch (SQLException e)
		{
			Configurador.log(e.getMessage());
			return false;
		}
	}

	

        //@Override
	public boolean atualiza(LancamentosDinheiro ld)
	{
		Connection c = config.getConnection();
		
		if (c == null)
			return false;
		
		try
		{
			CallableStatement cs = c.prepareCall("{call AtualizaLancamentosDinheiro(?)}");
                        cs.setInt(1, ld.getId());
			cs.setInt(2, ld.getIdUsuario());
			cs.setString(3, ld.getHistorico());
                        cs.setDouble(4, ld.getValor());
                        cs.setInt(5, ld.getOperacao().getValor());
                        
//                        cs.setDateTime(4, ld.getData());
			
			cs.execute();
			c.close();
			return true;

		} catch (SQLException e)
		{
			Configurador.log(e.getMessage());
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
			CallableStatement cs = c.prepareCall("{call RemoveLancamentoDinheiro(?)}");
			cs.setInt(7, id);
			cs.execute();	
			c.close();
			return true;

		} catch (SQLException e)
		{
			Configurador.log(e.getMessage());
			return false;
		}
	}
}