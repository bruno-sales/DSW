package Model.DAO.Interfaces;

import Model.LancamentosDinheiro;
import java.util.List;

public interface ILancamentosDinheiroDAO {
    
        public LancamentosDinheiro getlancamentoDinheiroPorId(int id);
        public List<LancamentosDinheiro> lista();
}
