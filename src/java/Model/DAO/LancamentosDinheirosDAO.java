package Model.DAO;

import Model.Enums.EnumOperacao;
import Model.LancamentosDinheiros;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.DAO.Interfaces.ILancamentosDinheirosDAO;


public class LancamentosDinheirosDAO implements ILancamentosDinheirosDAO{
    private final Configurador config;
 
    public LancamentosDinheirosDAO() {
         config = new Configurador();
     }
    
    private LancamentosDinheiros carrega(ResultSet rs) throws SQLException
	{
		LancamentosDinheiros ld = new LancamentosDinheiros();
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
        @Override
    public LancamentosDinheiros getlancamentoDinheiroPorId(int id)
	{
		Connection c = config.getConnection();
		
		if (c == null)
			return null;
		
		LancamentosDinheiros ld = null;
		
		try
		{
			PreparedStatement ps = c.prepareStatement("SELECT * FROM LancamentosDinheiro WHERE id = ?");
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

    @Override
    public List<LancamentosDinheiros> lista()
	{
		Connection c = config.getConnection();
		
		if (c == null)
			return null;
		
		List<LancamentosDinheiros> lista = new ArrayList<>();
		
		try
		{
			PreparedStatement ps = c.prepareStatement("SELECT * FROM LancamentosDinheiro");
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
}