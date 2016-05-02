package Model.DAO.Interfaces;

import java.util.List;
import Model.Enums.EnumOperacao;
import Model.LancamentosPersonagem;

public interface ILancamentosPersonagemDAO
{
    
    public LancamentosPersonagem getlancamentosPersonagemPorId(int id);
    public List<LancamentosPersonagem> lista();
    public boolean adicionarPersonagens(int idUsuario, int idPersonagem, int quantidade);
    public boolean removerPersonagem(int idUsuario, int idPersonagem, int quantidade, String historico, double precoUnitario, EnumOperacao operacao);
    public boolean calculaSaldoDisponivelPersonagem(int IdUsuario, int idPersonagem, /*OUT*/ int saldo);
    public boolean CancelaOrdemCompra(int idCompra);
    
}
