package Model.DAO;

import Model.DAO.Interfaces.IOfertaDAO;
import Model.Enums.EStatusOferta;
import Model.Enums.ETipoOferta;
import Model.Oferta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfertaDAO implements IOfertaDAO{
    
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
                
                int codigo = rs.getInt("tipo");
                ETipoOferta tipo = ETipoOferta.get(codigo);
                oft.setTipoOferta(tipo);


                switch(rs.getInt("tipo"))
                {
                    case 0:
                    oft.setTipoOferta(ETipoOferta.COMPRA);
                        break;
                    case 1:
                    oft.setTipoOferta(ETipoOferta.VENDA);
                        break;
                }
                
                switch(rs.getInt("status"))
                {
                    case 0:
                    oft.setStatus(EStatusOferta.ABERTA);
                        break;
                    case 1:
                    oft.setStatus(EStatusOferta.LIQUIDADA);
                        break;
                    case 2:
                    oft.setStatus(EStatusOferta.CANCELADA);
                        break;
                }
                //tk.setData(rs.getDateTime("data"));
                                 
		return oft;
	}

	@Override
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

	@Override
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
}
