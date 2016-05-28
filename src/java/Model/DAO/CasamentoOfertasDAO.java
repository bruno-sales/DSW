package Model.DAO;

import Util.Configurador;
import Model.CasamentoOfertas;
import Model.DAO.Interfaces.ICasamentoOfertasDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

public class CasamentoOfertasDAO implements ICasamentoOfertasDAO{

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

    @Override
    public CasamentoOfertas getCasamentosOfertaPorId(int id) {
        Connection c = config.getConnection();

        if (c == null) {
            return null;
        }

        CasamentoOfertas cof = null;

        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM CasamentoOfertas WHERE id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cof = carrega(rs);
            }

            c.close();

        } catch (SQLException e) {
            Configurador.log(e.getMessage());
        }

        return cof;
    }

    @Override
    public List<CasamentoOfertas> lista() {
        Connection c = config.getConnection();

        if (c == null) {
            return null;
        }

        List<CasamentoOfertas> lista = new ArrayList<>();

        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM CasamentoOfertas");
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
    public boolean ExecutaOrdens(DateTime vAgora, int vIdPersonagem, /*INOUT*/ int vIdCompra, int vIdUsuarioComprador, /*INOUT*/ int vQuantidadeCompra, int vIdVenda, int vIdUsuarioVendedor, /*INOUT*/ int vQuantidadeVenda, float vPrecoVenda, float vPrecoCompra, /*OUT*/int vIdRestante)
    {
//        Connection c = config.getConnection();
//		
//		if (c == null)
//			return false;
//		
//                try{
//                        if(vQuantidadeCompra == vQuantidadeVenda)
//                        {
//                            CallableStatement cs = c.prepareCall("{call ExecutaCompraIgualVenda(?,?,?,?,?,?,?,?,?,?)}"); //isso que é CALL?
////                            cs.setInt(1, idUsuario);
//                            vQuantidadeVenda = 0;
//                            vQuantidadeCompra = 0;
//                            vIdRestante = 0;
///*                            
//(vAgora, vIdPersonagem, vIdCompra, vIdUsuarioComprador, vQuantidadeCompra, vIdVenda, vIdUsuarioVendedor, vQuantidadeVenda, vPrecoVenda, vPrecoCompra);
//*/                            
//                        }else
//                        if(vQuantidadeCompra > vQuantidadeVenda)
//                        {
//                            CallableStatement cs = c.prepareCall("{call ExecutaCompraMaiorVenda(?,?,?,?,?,?,?,?,?,?,?)}");
///*(vAgora, vIdPersonagem, vIdCompra, vIdUsuarioComprador, vQuantidadeCompra, vIdVenda, vIdUsuarioVendedor, vQuantidadeVenda, vPrecoVenda, vPrecoCompra, vIdRestante)*/
//                            vQuantidadeCompra = vQuantidadeCompra - vQuantidadeVenda;
//                            vQuantidadeVenda = 0;
//                            vIdCompra = vIdRestante;
//                            vIdRestante = 0;
//                        }else
//                        {
//                            CallableStatement cs = c.prepareCall("{call ExecutaVendaMaiorCompra(?,?,?,?,?,?,?,?,?,?,?)}");
///*(vAgora, vIdPersonagem, vIdCompra, vIdUsuarioComprador, vQuantidadeCompra, vIdVenda, vIdUsuarioVendedor, vQuantidadeVenda, vPrecoVenda, vPrecoCompra, vIdRestante);*/
//                            vQuantidadeVenda = vQuantidadeVenda - vQuantidadeCompra; 
//                            vQuantidadeCompra = 0;
//                        
//                        }
//                        return true;
//                        
//                } catch (SQLException e)
//                {
//                        Configurador.log(e.getMessage());
//			return false;
//                }      
        return false;
    }

        /*
        CREATE PROCEDURE ExecutaCompraIgualVenda(vAgora DATETIME, vIdPersonagem INT, vIdCompra INT, vIdUsuarioComprador INT, vQuantidadeCompra INT, vIdVenda INT, vIdUsuarioVendedor INT, vQuantidadeVenda INT, vPrecoVenda FLOAT, vPrecoCompra FLOAT)
BEGIN
	-- executa a oferta de compra integralmente
	UPDATE ofertas
	SET status = 1
	WHERE id = vIdCompra;

	-- Executa a liquidacao
	CALL ExecutaLiquidacao(vAgora, vIdPersonagem, vIdCompra, vIdUsuarioComprador, vQuantidadeCompra, vPrecoCompra, vIdVenda, vIdUsuarioVendedor, vQuantidadeVenda, vPrecoVenda, vQuantidadeVenda);
	
	-- registra o casamento de ordens
	INSERT INTO casamentosOferta (idOfertaCompra, idOfertaVenda, dataExecucao)
	VALUES (vIdCompra, vIdVenda, vAgora);

	-- executa a oferta de venda integralmente
	UPDATE ofertas
	SET status = 1
	WHERE id = vIdVenda;*/
}
