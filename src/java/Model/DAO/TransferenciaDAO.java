package Model.DAO;

import Model.Transferencia;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

public class TransferenciaDAO {

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
        trsf.setValor(rs.getDouble("valor"));
        trsf.setData(DateTime.parse(rs.getDate("data").toString()));

        return trsf;
    }

    //@Override
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

    
    public boolean insere(Transferencia trsf) {
        Connection c = config.getConnection();

        if (c == null) {
            return false;
        }

        try {
            CallableStatement cs = c.prepareCall("{call InsereToken(?, ?, ?, ?)}");

            cs.setInt(1, trsf.getId());
            cs.setInt(2, trsf.getIdUsuario());
            cs.setString(3, trsf.getNumeroBanco());
            cs.setString(4, trsf.getNumeroAgencia());
            cs.setString(5, trsf.getNumeroConta());
            cs.setDouble(6, trsf.getValor());
//                       cs.setDateTime(10, tk.getData());

            cs.execute();

            int id = cs.getInt(1);
            trsf.setId(id);

            c.close();
            return true;

        } catch (SQLException e) {
            config.log(e.getMessage());
            return false;
        }
    }

    public boolean atualiza(Transferencia trsf) {
        Connection c = config.getConnection();

        if (c == null) {
            return false;
        }

        try {
            CallableStatement cs = c.prepareCall("{call AtualizaTranferencia(?, ?, ?, ?, ?, ?, ?)}");
            cs.setInt(1, trsf.getId());
            cs.setInt(2, trsf.getIdUsuario());
            cs.setString(3, trsf.getNumeroBanco());
            cs.setString(4, trsf.getNumeroAgencia());
            cs.setString(5, trsf.getNumeroConta());
            cs.setDouble(6, trsf.getValor());
//                       cs.setDateTime(10, tk.getData());        

            cs.execute();
            c.close();
            return true;

        } catch (SQLException e) {
            Configurador.log(e.getMessage());
            return false;
        }
    }
}
