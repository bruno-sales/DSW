package Model.DAO.Interfaces;

import java.util.List;
import Model.Enums.EOperacao;
import Model.LancamentosPersonagens;

public interface ILancamentosPersonagensDAO
{
    
    public LancamentosPersonagens getlancamentosPersonagemPorId(int id);
    public List<LancamentosPersonagens> lista();
    public boolean adicionarPersonagens(int idUsuario, int idPersonagem, int quantidade);
    public boolean removerPersonagem(int idUsuario, int idPersonagem, int quantidade, String historico, double precoUnitario, EOperacao operacao);
    public boolean calculaSaldoDisponivelPersonagem(int IdUsuario, int idPersonagem, /*OUT*/ int saldo);
    public boolean CancelaOrdemCompra(int idCompra);
    public List<LancamentosPersonagens> getLancamentosPersonagensPorIdUsuario(int idUsuario);
    public int countLancamentosPersonagens(int userId);
}
