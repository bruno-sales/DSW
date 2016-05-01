package Model.DAO;

import Model.CasamentoOfertas;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

public class CasamentoOfertasDAO {

    private final Configurador config;

    public CasamentoOfertasDAO() {
        config = new Configurador();
    }

    private CasamentoOfertas carrega(ResultSet rs) throws SQLException {
        CasamentoOfertas cof = new CasamentoOfertas();

        cof.setIdOfertaVenda(rs.getInt("idOfertaVenda"));
        cof.setIdOfertaCompra(rs.getInt("idOfertaCompra"));
        cof.setDataExecucao(DateTime.parse(rs.getDate("dataExecucao").toString()));

        return cof;
    }

    public CasamentoOfertas getCasamentosOfertaPorId(int id) {
        Connection c = config.getConnection();

        if (c == null) {
            return null;
        }

        CasamentoOfertas cof = null;

        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM casamentosOferta WHERE id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cof = carrega(rs);
            }

            c.close();

        } catch (SQLException e) {
            config.log(e.getMessage());
        }

        return cof;
    }

    //@Override
    public List<CasamentoOfertas> lista() {
        Connection c = config.getConnection();

        if (c == null) {
            return null;
        }

        List<CasamentoOfertas> lista = new ArrayList<>();

        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM casamentosOferta");
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

}
