package Model.DAO;
import Model.Enums.EnumStatusOferta;
import Model.Enums.EnumTipoOferta;
import Model.Oferta;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfertaDAO {
    
    private final Configurador config;
 
    public OfertaDAO() {
         config = new Configurador();
     }
    
    private Oferta carrega(ResultSet rs) throws SQLException
	{
		Oferta oft = new Oferta();
		oft.setId(rs.getInt("id"));
		oft.setIdUsuario(rs.getInt("idUsuario"));
                oft.setQuantidade(rs.getInt("quantidade"));
                oft.setQuantidadeOriginal(rs.getInt("quantidadeOriginal"));
                oft.setIdPersonagem(rs.getInt("idPersonagem"));
                
                switch(rs.getInt("tipo"))
                {
                    case 0:
                    oft.setTipoOferta(EnumTipoOferta.COMPRA);
                        break;
                    case 1:
                    oft.setTipoOferta(EnumTipoOferta.VENDA);
                        break;
                }
                switch(rs.getInt("status"))
                {
                    case 0:
                    oft.setStatus(EnumStatusOferta.ABERTA);
                        break;
                    case 1:
                    oft.setStatus(EnumStatusOferta.LIQUIDADA);
                        break;
                    case 2:
                    oft.setStatus(EnumStatusOferta.CANCELADA);
                        break;
                }
                //tk.setData(rs.getDateTime("data"));
                                 
		return oft;
	}

	//@Override
	public Oferta getOfertaPorId(int id)
	{
		Connection c = config.getConnection();
		
		if (c == null)
			return null;
		
		Oferta oft = null;
		
		try
		{
			PreparedStatement ps = c.prepareStatement("SELECT * FROM Ofertas WHERE id = ?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next())
				oft = carrega(rs);

			c.close();

		} catch (SQLException e)
		{
			Configurador.log(e.getMessage());
		}
		    
		return oft;
	}

	//@Override
	public List<Oferta> lista()
	{
		Connection c = config.getConnection();
		
		if (c == null)
			return null;
		
		List<Oferta> lista = new ArrayList<>();
		
		try
		{
			PreparedStatement ps = c.prepareStatement("SELECT * FROM Ofertas");
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

        
	public boolean insere(Oferta oft)
	{
		Connection c = config.getConnection();
		
		if (c == null)
			return false;
		
		try
		{
                    /*
			CallableStatement cs = c.prepareCall("{call InsereToken(?, ?, ?, ?)}");
			
                        cs.setInt(1, oft.getId());
                        cs.setInt(2, oft.getTipo());
			cs.setInt(3, oft.getIdUsuario());
			cs.setInt(4, oft.getStatus());
                        cs.setDouble(5, oft.getQuantidade());
                        cs.setDouble(6, oft.getQuantidadeOriginal());
                        cs.setInt(7, oft.getIdPersonagem());
                        cs.setDouble(8, oft.getPrecoUnitario());
                        cs.setInt(9, oft.getIdOrdemOriginal());
//                        cs.setDateTime(10, tk.getData());
                        
                        cs.execute();
			int id = cs.getInt(1);
			oft.setId(id);
			
			*/
			c.close();
			return true;

		} catch (SQLException e)
		{
			Configurador.log(e.getMessage());
			return false;
		}
	}
		
	public boolean remove(int id)
	{
		Connection c = config.getConnection();
		
		if (c == null)
			return false;
		
		try
		{
			CallableStatement cs = c.prepareCall("{call RemoveOferta(?)}");
			cs.setInt(1, id);
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
