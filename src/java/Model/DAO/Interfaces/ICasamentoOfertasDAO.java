/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO.Interfaces;

import Model.CasamentoOfertas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author Hazël § Rebecca
 */
public interface ICasamentoOfertasDAO {
    
    public CasamentoOfertas getCasamentosOfertaPorId(int id);
    public List<CasamentoOfertas> lista();
    public boolean ExecutaOrdens(DateTime vAgora, int vIdPersonagem, /*INOUT*/ int vIdCompra, int vIdUsuarioComprador, /*INOUT*/ int vQuantidadeCompra, int vIdVenda, int vIdUsuarioVendedor, /*INOUT*/ int vQuantidadeVenda, float vPrecoVenda, float vPrecoCompra, /*OUT*/int vIdRestante);
}
