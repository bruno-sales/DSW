package Model.DAO.Interfaces;

import Model.LancamentosDinheiros;
import java.util.List;

public interface ILancamentosDinheirosDAO {
    
        public LancamentosDinheiros getlancamentoDinheiroPorId(int id);
        public List<LancamentosDinheiros> getlancamentosDinheiroPorIdUsuario(int idUsuario,int pagina, int tamanho);
        public List<LancamentosDinheiros> lista();
        public int countLancamentoDinheiro(int userId);
        public float obterSaldoUsuario(int idUsuario);
}
