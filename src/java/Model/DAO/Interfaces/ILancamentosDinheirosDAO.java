package Model.DAO.Interfaces;

import Model.LancamentosDinheiros;
import java.util.List;

public interface ILancamentosDinheirosDAO {
    
        public LancamentosDinheiros getlancamentoDinheiroPorId(int id);
        public List<LancamentosDinheiros> getlancamentosDinheiroPorIdUsuario(int idUsuario);
        public List<LancamentosDinheiros> lista();
}
