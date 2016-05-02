package Model.DAO.Interfaces;

import java.util.List;

public interface ILancamentosPersonagem {
    
    public LancamentosPersonagem getlancamentosPersonagemPorId(int id);
    public List<LancamentosPersonagem> lista();
    public boolean adicionarPersonagens(int idUsuario, int idPersonagem, int quantidade);
    public boolean removerPersonagem(int idUsuario, int idPersonagem, int quantidade, String historico, double precoUnitario, enum operacao) throws SQLException;
    public boolean calculaSaldoDisponivelPersonagem(int IdUsuario, int idPersonagem, /*OUT*/ int saldo);
    public boolean CancelaOrdemCompra(int idCompra);
    
}
