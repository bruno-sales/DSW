package Model.DAO;

import Util.Configurador;
import Model.DAO.Interfaces.IOfertaDAO;
import Model.Enums.EStatusOferta;
import Model.Enums.ETipoOferta;
import Model.Oferta;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

public class OfertaDAO implements IOfertaDAO {

    private final Configurador config;

    public OfertaDAO() {
        config = new Configurador();
    }

    private Oferta carrega(ResultSet rs) throws SQLException {
        Oferta oferta = new Oferta();

        oferta.setId(rs.getInt("id"));
        oferta.setIdUsuario(rs.getInt("idUsuario"));
        oferta.setQuantidade(rs.getInt("quantidade"));
        oferta.setValor(rs.getFloat("precoUnitario"));
        oferta.setQuantidadeOriginal(rs.getInt("quantidadeOriginal"));
        oferta.setIdPersonagem(rs.getInt("idPersonagem"));

        //Recuperando enumerador do tipo
        int codigoTipo = rs.getInt("tipo");
        ETipoOferta tipoOferta = ETipoOferta.get(codigoTipo);
        oferta.setTipoOferta(tipoOferta);

        //Recuperando enumerador do status
        int codigoStatus = rs.getInt("status");
        EStatusOferta statusOferta = EStatusOferta.get(codigoStatus);
        oferta.setStatus(statusOferta);

        DateTime dataOferta = new DateTime(rs.getTimestamp("data"));
        oferta.setData(dataOferta);

        return oferta;
    }

    @Override
    public Oferta getOfertaPorId(int id) {
        Connection c = config.getConnection();

        if (c == null) {
            return null;
        }

        Oferta oft = null;

        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Ofertas WHERE id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                oft = carrega(rs);
            }

            c.close();

        } catch (SQLException e) {
            Configurador.log(e.getMessage());
        }

        return oft;
    }

    @Override
    public List<Oferta> lista() {
        Connection c = config.getConnection();

        if (c == null) {
            return null;
        }

        List<Oferta> lista = new ArrayList<>();

        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Ofertas");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(carrega(rs));
            }

            c.close();

        } catch (SQLException e) {
            Configurador.log(e.getMessage());
        }

        return lista;
    }  

    @Override
    public int CountOfertasUsuario(int userId) {
        Connection c = config.getConnection();
        if (c == null) {
            return 0;
        }

        int qtd = 0;

        try {
            PreparedStatement ps = c.prepareStatement("SELECT count(*) AS qtd FROM Ofertas where "
                    + "idUsuario = ?");
                    
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                qtd = rs.getInt("qtd");
            }
            c.close();

        } catch (SQLException e) {
            Configurador.log(e.getMessage());
        }

        return qtd;
    }

    @Override
    public List<Oferta> listaOfertasUsuario(int userId, int pagina, int tamanho) {
        Connection c = config.getConnection();

        if (c == null) {
            return null;
        }

        List<Oferta> lista = new ArrayList<>();

        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Ofertas where "
                    + "idUsuario = ? ORDER BY data desc LIMIT ? OFFSET ?");

            ps.setInt(1, userId);
            ps.setInt(2, tamanho);
            ps.setInt(3, pagina * tamanho);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(carrega(rs));
            }

            c.close();

        } catch (SQLException e) {
            Configurador.log(e.getMessage());
        }

        return lista;
    }
    
    @Override
    public boolean registrarCompra(int idUsuario, int idPersonagem, int quantidade, float valorUnitario) {
        Connection c = config.getConnection();

        if (c == null) {
            return false;
        }

        try {
            CallableStatement cs = c.prepareCall("{call RegistraOrdemCompra(?, ?, ?, ?, ?)}");
            cs.setInt(1,idUsuario);
            cs.setInt(2, idPersonagem);
            cs.setInt(3, quantidade);
            cs.setFloat(4, valorUnitario);
            cs.registerOutParameter(5, java.sql.Types.INTEGER);
            cs.execute();

            int retorno = cs.getInt(5);
            
            c.close();
            return retorno == 0;

        } catch (SQLException e) {
            Configurador.log(e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean registrarVenda(int idUsuario, int idPersonagem, int quantidade, float valorUnitario) {
        Connection c = config.getConnection();

        if (c == null) {
            return false;
        }

        try {
            CallableStatement cs = c.prepareCall("{call RegistraOrdemVenda(?, ?, ?, ?, ?)}");
            cs.setInt(1,idUsuario);
            cs.setInt(2, idPersonagem);
            cs.setInt(3, quantidade);
            cs.setFloat(4, valorUnitario);
            cs.registerOutParameter(5, java.sql.Types.INTEGER);
            cs.execute();

            int retorno = cs.getInt(5);
            
            c.close();
            return retorno == 0;

        } catch (SQLException e) {
            Configurador.log(e.getMessage());
            return false;
        }
    }
}
