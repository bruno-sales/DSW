package Model.DAO.Interfaces;


import java.util.List;
import Model.Personagem;

public interface IPersonagemDAO {
    
    public Personagem getPersonagemPorId(int id);
    public List<Personagem> lista(int pagina, int tamanho);
    public int countItens();
}
