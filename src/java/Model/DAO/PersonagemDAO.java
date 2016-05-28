package Model.DAO;

import Util.Configurador;
import Model.DAO.Interfaces.IPersonagemDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.Personagem;

public class PersonagemDAO implements IPersonagemDAO{

    private final Configurador config;

    public PersonagemDAO() {
        config = new Configurador();
    }

    /**
     * Carrega os dados do personagem a partir de uma consulta
     */
    private Personagem carrega(ResultSet rs) throws SQLException {

        Personagem persona = new Personagem();
        persona.setId(rs.getInt("id"));
        persona.setNome(rs.getString("nome"));
        return persona;

    }
    /**
     * Carrega os dados do personagem a partir de um ID
     * @param id Idpersonagem
     * @return Objeto Personagem
     */
    
    @Override
    public Personagem getPersonagemPorId(int id) {
        Connection c = config.getConnection();

        if (c == null) {
            return null;
        }
        Personagem item = null;

        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Personagens WHERE id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                item = carrega(rs);
            }

            c.close();

        } catch (SQLException e) {
            Configurador.log(e.getMessage());
        }

        return item;
    }

    /**
     * Retorna a lista de Personagens armazenados no sistema
     * @return Lista de personagens
     */
    @Override
    public List<Personagem> lista() {
        Connection c = config.getConnection();

        if (c == null) {
            return null;
        }

        List<Personagem> lista = new ArrayList<>();

        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Personagens");
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
