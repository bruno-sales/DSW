package Model.DAO;

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

public class LancamentosPersonagensDAO implements ILancamentosPersonagensDAO{
    private final Configurador config;
    
    public LancamentosPersonagensDAO() {
        config = new Configurador();
    }
    
    private LancamentosPersonagens carrega(ResultSet rs) throws SQLException
	{
                LancamentosPersonagens lp = new LancamentosPersonagens();
                //lp.setData(rs.getDateTime("data"));
                lp.setHistorico(rs.getString("historico"));
                lp.setQuantidade(rs.getInt("quantidade"));
		lp.setPrecoUnitario(rs.getFloat("precoUnitario"));
                lp.setIdPersonagem(rs.getInt("idPersonagem"));
                lp.setIdUsuario(rs.getInt("idUsuario"));
                
                switch(rs.getInt("operacao"))
                {
                    case 0:
                    lp.setOperacao(EOperacao.CREDITO);
                        break;
                    case 1:
                    lp.setOperacao(EOperacao.DEBITO);
                        break;
                    case 2:
                    lp.setOperacao(EOperacao.BLOQUEIO);
                        break;
                    case 3:
                    lp.setOperacao(EOperacao.DESBLOQUEIO);
                        break;
                }
                return lp;
	}

	@Override
	public LancamentosPersonagens getlancamentosPersonagemPorId(int id)
	{
		Connection c = config.getConnection();
		
		if (c == null)
			return null;
		
		LancamentosPersonagens lp = null;
		
		try
		{
			PreparedStatement ps = c.prepareStatement("SELECT * FROM lancamentosPersonagem WHERE idUsuario = ?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next())
				lp = carrega(rs);

			c.close();

		} catch (SQLException e)
		{
			Configurador.log(e.getMessage());
		}
		    
		return lp;
	}
        
        @Override
	public List<LancamentosPersonagens> lista()
	{
		Connection c = config.getConnection();
		
		if (c == null)
			return null;
		
		List<LancamentosPersonagens> lista = new ArrayList<>();
		
		try
		{
			PreparedStatement ps = c.prepareStatement("SELECT * FROM lancamentosPersonagem");
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

        @Override
	public boolean adicionarPersonagens(int idUsuario, int idPersonagem, int quantidade)
	{
		Connection c = config.getConnection();
		
		if (c == null)
			return false;
	       
		try
		{
			CallableStatement cs = c.prepareCall("{call AdicionarPersonagem(?, ?, ?)}");
			cs.setInt(1, idUsuario);
                        cs.setInt(2, idPersonagem);
			cs.setInt(3, quantidade);
                        
                        cs.execute();			
			
			c.close();
			return true;

		} catch (SQLException e)
		{
			Configurador.log(e.getMessage());
			return false;
		}
	}
        
        @Override
        public boolean removerPersonagem(int idUsuario, int idPersonagem, int quantidade, String historico, double precoUnitario, EOperacao operacao)
        {
                //private LancamentosPersonagem LP = new LancamentosPersonagem;
                Connection c = config.getConnection();
		
		if (c == null)
			return false;
         
                try
		{			
                        CallableStatement cs = c.prepareCall("{call RemoverPersonagem(?, ?, ?, ?, ?, ?)}");
			cs.setInt(1, idUsuario);
                        cs.setInt(2, idPersonagem);
                        //data
                        cs.setString(4, historico);
                        cs.setDouble(5, precoUnitario);
 //                       cs.setInt(6, operacao.get());
			                        
                        cs.execute();			
			
			c.close();
			return true;

		} catch (SQLException e)
		{
			Configurador.log(e.getMessage());
			return false;
		}

        }
        
        @Override
        public boolean calculaSaldoDisponivelPersonagem(int IdUsuario, int idPersonagem, /*OUT*/ int saldo)
        {
//            Connection c = config.getConnection();
//		
//		if (c == null)
//			return false;
//         
//                try
//		{
//                        PreparedStatement ps = c.prepareStatement("SELECT SUM");
//                        ResultSet rs = ps.executeQuery();
//			
//                        int opr = operacao.getValor;
///*SELECT SUM(CASE operacao WHEN 0 THEN quantidade WHEN 1 THEN -quantidade WHEN 2 THEN -quantidade WHEN 3 THEN quantidade END)
//
//	INTO vSaldo
//	FROM lancamentosPersonagem
//	WHERE idUsuario = vIdUsuario
//	AND idPersonagem = vIdPersonagem;
//	
//	IF vSaldo IS NULL THEN SET vSaldo = 0.0; END IF;*/
//                        switch(opr.getInt())
//                        {
//                            case 0:
//                                
//                                break;
//                            case 1:
//                                
//                                break;
//                            case 2:
//                                
//                                break;
//                            case 3:
//                                break;
//                        }
//                        
//                        PreparedStatement ps = c.prepareStatement("");
//                        ResultSet rs = ps.executeQuery();
//                        
//                        cs.execute();			
//			
//			c.close();
//			return true;
//
//		} catch (SQLException e)
//		{
//			Configurador.log(e.getMessage());
//			return false;
//		}
            return false;
        }
        
        
        public boolean CancelaOrdemCompra(int idCompra)
        {
//            
//            //DateTime Agora;
//            int idUsuario;
//            int quantidade;
//            float precoUnitario;
//            float valorTotal;
//            
//                PreparedStatement ps = c.prepareStatement
//("SELECT idUsuario, idPersonagem, quantidade, precoUnitario INTO lIdUsuario, lQuantidade, lPrecoUnitario FROM ofertas WHERE id = idCompra"); //ou vIdCompra?
//                ps.setInt(3,quantidade);
//                ps.setInt(4,precoUnitario);
//        
//                valorTotal = quantidade * precoUnitario;
//	
//                ld.idUsuario = idUsuario;
//                ld.quantidade = 3;
//                ld.historico = "Canc Oferta Compra #" + idOfertaCompra;
//                ld.valor = valorTotal;
//            
//                PreparedStatement ps = c.prepareStatement("SELECT status FROM ofertas WHERE id=idCompra"); //ou vIdCompra??
//                
//                //status = 2
            
            return false;
        }

}