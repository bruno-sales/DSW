package Model.DAO;

import Util.Configurador;
import Model.DAO.Interfaces.ITransferenciaDAO;
import Model.Transferencia;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

public class TransferenciaDAO implements ITransferenciaDAO {

    private final Configurador config;

    public TransferenciaDAO() {
        config = new Configurador();
    }

    private Transferencia carrega(ResultSet rs) throws SQLException {
        Transferencia trsf = new Transferencia();

        trsf.setId(rs.getInt("id"));
        trsf.setIdUsuario(rs.getInt("idUsuario"));
        trsf.setNumeroBanco(rs.getString("numeroBanco"));
        trsf.setNumeroAgencia(rs.getString("numeroAgencia"));
        trsf.setNumeroConta(rs.getString("numeroConta"));
        trsf.setValor(rs.getFloat("valor"));
        trsf.setData(DateTime.parse(rs.getDate("data").toString()));

        return trsf;
    }

    @Override
    public Transferencia getTransferenciaPorId(int id) {
        Connection c = config.getConnection();

        if (c == null) {
            return null;
        }

        Transferencia trsf = null;

        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Tranferencias WHERE id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                trsf = carrega(rs);
            }

            c.close();

        } catch (SQLException e) {
            Configurador.log(e.getMessage());
        }

        return trsf;
    }

    @Override
    public List<Transferencia> lista() {
        Connection c = config.getConnection();

        if (c == null) {
            return null;
        }

        List<Transferencia> lista = new ArrayList<>();

        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Transferencias");
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
    public boolean registrarTransferencia(Transferencia transferencia) {
        Connection c = config.getConnection();

        if (c == null) {
            return false;
        }

        try {
            CallableStatement cs = c.prepareCall("{call RegistrarTransferencia(?,?,?,?,?)}");
            cs.setInt(1, transferencia.getIdUsuario());
            cs.setString(2, transferencia.getNumeroBanco());
            cs.setString(3, transferencia.getNumeroAgencia());
            cs.setString(4, transferencia.getNumeroConta());
            cs.setFloat(5, transferencia.getValor());

            cs.execute();
            c.close();
            return true;

        } catch (SQLException e) {
            Configurador.log(e.getMessage());
            return false;
        }
    }
}
