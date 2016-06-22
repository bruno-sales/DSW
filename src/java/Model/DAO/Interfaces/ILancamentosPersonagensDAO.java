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
    public List<LancamentosPersonagens> getLancamentosPersonagensPorIdUsuario(int idUsuario,int pagina, int tamanho);
    public int countLancamentosPersonagens(int userId);
    public float obterSaldoPersonagem(int idUsuario, int idPersonagem);
}
