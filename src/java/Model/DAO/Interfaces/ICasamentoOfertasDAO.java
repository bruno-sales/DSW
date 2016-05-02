package Model.DAO.Interfaces;

import Model.CasamentoOfertas;
import java.util.List;
import org.joda.time.DateTime;

public interface ICasamentoOfertasDAO {
    
    public CasamentoOfertas getCasamentosOfertaPorId(int id);
    public List<CasamentoOfertas> lista();
    public boolean ExecutaOrdens(DateTime vAgora, int vIdPersonagem, /*INOUT*/ int vIdCompra, int vIdUsuarioComprador, /*INOUT*/ int vQuantidadeCompra, int vIdVenda, int vIdUsuarioVendedor, /*INOUT*/ int vQuantidadeVenda, float vPrecoVenda, float vPrecoCompra, /*OUT*/int vIdRestante);
}
