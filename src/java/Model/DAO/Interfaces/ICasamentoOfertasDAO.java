package Model.DAO.Interfaces;

import Model.CasamentoOfertas;
import java.util.List;

public interface ICasamentoOfertasDAO {
    
    public CasamentoOfertas getCasamentosOfertaPorId(int id);
    public List<CasamentoOfertas> lista();
}
