package Model.DAO;

import Model.Enums.EnumOperacao;
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
                //lp.setData(rs.getDateTime("data"));
                lp.setHistorico(rs.getString("historico"));
                lp.setQuantidade(rs.getInt("quantidade"));
		lp.setPrecoUnitario(rs.getFloat("precoUnitario"));
                lp.setIdPersonagem(rs.getInt("idPersonagem"));
                lp.setIdUsuario(rs.getInt("idUsuario"));
                
                switch(rs.getInt("operacao"))
                {
                    case 0:
                    lp.setOperacao(EnumOperacao.CREDITO);
                        break;
                    case 1:
                    lp.setOperacao(EnumOperacao.DEBITO);
                        break;
                    case 2:
                    lp.setOperacao(EnumOperacao.BLOQUEIO);
                        break;
                    case 3:
                    lp.setOperacao(EnumOperacao.DESBLOQUEIO);
                        break;
                }
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
			Configurador.log(e.getMessage());
		}
		    
		return lp;
	}

	public List<LancamentosPersonagem> lista()
	{
		Connection c = config.getConnection();
		
		if (c == null)
			return null;
		
		List<LancamentosPersonagem> lista = new ArrayList<>();
		
		try
		{
			PreparedStatement ps = c.prepareStatement("SELECT * FROM lancamentosPersonagem");
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

        
	public boolean adicionarPersonagens(int idUsuario, int idPersonagem, int quantidade)
	{
		Connection c = config.getConnection();
		
		if (c == null)
			return false;
		
		try
		{
			CallableStatement cs = c.prepareCall("{call AdicionarPersonagem(?, ?, ?)}");
			cs.setInt(1, idUsuario);
                        cs.setInt(2, idPersonagem);
			cs.setInt(3, quantidade);
                        
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