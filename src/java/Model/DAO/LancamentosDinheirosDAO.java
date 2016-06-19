package Model.DAO;

import Util.Configurador;
import Model.Enums.EOperacao;
import Model.LancamentosDinheiros;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.DAO.Interfaces.ILancamentosDinheirosDAO;
import org.joda.time.DateTime;

public class LancamentosDinheirosDAO implements ILancamentosDinheirosDAO {

    private final Configurador config;

    public LancamentosDinheirosDAO() {
        config = new Configurador();
    }

    private LancamentosDinheiros carrega(ResultSet rs) throws SQLException {
        LancamentosDinheiros ld = new LancamentosDinheiros();
        ld.setId(rs.getInt("id"));
        ld.setIdUsuario(rs.getInt("idUsuario"));
        ld.setHistorico(rs.getString("historico"));
        ld.setValor(rs.getFloat("valor"));

        DateTime data = new DateTime(rs.getTimestamp("data"));
        ld.setData(data);
        
        //Recuperando enumerador da operacao
        int codigoOperacao = rs.getInt("operacao");
        EOperacao tipoOperacao = EOperacao.get(codigoOperacao);
        ld.setOperacao(tipoOperacao);

        return ld;
    }

    @Override
    public LancamentosDinheiros getlancamentoDinheiroPorId(int id) {
        Connection c = config.getConnection();

        if (c == null) {
            return null;
        }

        LancamentosDinheiros ld = null;

        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM LancamentosDinheiro WHERE id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ld = carrega(rs);
            }

            c.close();

        } catch (SQLException e) {
            Configurador.log(e.getMessage());
        }

        return ld;
    }

    @Override
    public List<LancamentosDinheiros> lista() {
        Connection c = config.getConnection();

        if (c == null) {
            return null;
        }

        List<LancamentosDinheiros> lista = new ArrayList<>();

        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM LancamentosDinheiro");
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
    public List<LancamentosDinheiros> getlancamentosDinheiroPorIdUsuario(int idUsuario, int pagina, int tamanho) {
        Connection c = config.getConnection();

        if (c == null) {
            return null;
        }

        List<LancamentosDinheiros> lista = new ArrayList<>();

        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM LancamentosDinheiro where idUsuario = ? "
                    + "order by data desc LIMIT ? OFFSET ?");
            ps.setInt(1, idUsuario);
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
    public int countLancamentoDinheiro(int userId) {
        Connection c = config.getConnection();
        if (c == null) {
            return 0;
        }

        int qtd = 0;

        try {
            PreparedStatement ps = c.prepareStatement("SELECT count(*) AS qtd FROM lancamentosDinheiro where "
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
}
