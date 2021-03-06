package Model.DAO;

import Util.Configurador;
import Model.Enums.EOperacao;
import Model.LancamentosPersonagens;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.DAO.Interfaces.ILancamentosPersonagensDAO;
import org.joda.time.DateTime;

public class LancamentosPersonagensDAO implements ILancamentosPersonagensDAO {

    private final Configurador config;

    public LancamentosPersonagensDAO() {
        config = new Configurador();
    }

    private LancamentosPersonagens carrega(ResultSet rs) throws SQLException {
        LancamentosPersonagens lp = new LancamentosPersonagens();

        lp.setHistorico(rs.getString("historico"));
        lp.setQuantidade(rs.getInt("quantidade"));
        lp.setPrecoUnitario(rs.getFloat("precoUnitario"));
        lp.setIdPersonagem(rs.getInt("idPersonagem"));
        lp.setIdUsuario(rs.getInt("idUsuario"));

        DateTime data = new DateTime(rs.getTimestamp("data"));
        lp.setData(data);

        //Recuperando enumerador da operacao
        int codigoOperacao = rs.getInt("operacao");
        EOperacao tipoOperacao = EOperacao.get(codigoOperacao);
        lp.setOperacao(tipoOperacao);

        return lp;
    }

    @Override
    public LancamentosPersonagens getlancamentosPersonagemPorId(int id) {
        Connection c = config.getConnection();

        if (c == null) {
            return null;
        }

        LancamentosPersonagens lp = null;

        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM lancamentosPersonagem WHERE idUsuario = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                lp = carrega(rs);
            }

            c.close();

        } catch (SQLException e) {
            Configurador.log(e.getMessage());
        }

        return lp;
    }

    @Override
    public List<LancamentosPersonagens> lista() {
        Connection c = config.getConnection();

        if (c == null) {
            return null;
        }

        List<LancamentosPersonagens> lista = new ArrayList<>();

        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM lancamentosPersonagem");
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
    public boolean adicionarPersonagens(int idUsuario, int idPersonagem, int quantidade) {
        Connection c = config.getConnection();

        if (c == null) {
            return false;
        }

        try {
            CallableStatement cs = c.prepareCall("{call AdicionarPersonagem(?, ?, ?)}");
            cs.setInt(1, idUsuario);
            cs.setInt(2, idPersonagem);
            cs.setInt(3, quantidade);

            cs.execute();

            c.close();
            return true;

        } catch (SQLException e) {
            Configurador.log(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean removerPersonagem(int idUsuario, int idPersonagem, int quantidade, String historico, double precoUnitario, EOperacao operacao) {
        Connection c = config.getConnection();

        if (c == null) {
            return false;
        }

        try {
            CallableStatement cs = c.prepareCall("{call RemoverPersonagem(?, ?, ?, ?, ?, ?)}");
            cs.setInt(1, idUsuario);
            cs.setInt(2, idPersonagem);
            //data
            cs.setString(4, historico);
            cs.setDouble(5, precoUnitario);
            cs.setInt(6, operacao.getCodigo());

            cs.execute();

            c.close();
            return true;

        } catch (SQLException e) {
            Configurador.log(e.getMessage());
            return false;
        }

    }

    @Override
    public List<LancamentosPersonagens> getLancamentosPersonagensPorIdUsuario(int idUsuario, int pagina, int tamanho) {
        Connection c = config.getConnection();

        if (c == null) {
            return null;
        }

        List<LancamentosPersonagens> lista = new ArrayList<>();

        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM LancamentosPersonagem where idUsuario = ? "
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
    public int countLancamentosPersonagens(int userId) {
        Connection c = config.getConnection();
        if (c == null) {
            return 0;
        }

        int qtd = 0;

        try {
            PreparedStatement ps = c.prepareStatement("SELECT count(*) AS qtd FROM LancamentosPersonagem where "
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
    public float obterSaldoPersonagem(int idUsuario, int idPersonagem) {
        Connection c = config.getConnection();

        if (c == null) {
            return 0;
        }

        try {
            CallableStatement cs = c.prepareCall("{call CalculaSaldoDisponivelPersonagem(?, ?, ?)}");
            cs.setInt(1, idUsuario);
            cs.setInt(2, idPersonagem);
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.execute();

            float saldo = cs.getInt(3);

            c.close();
            return saldo;

        } catch (SQLException e) {
            Configurador.log(e.getMessage());
            return 0;
        }
    }
}
