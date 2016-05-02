package Model.DAO.Interfaces;

import Model.Personagem;

public interface IPersonagemDAO {
    
    public Personagem getPersonagemPorId(int id);
    public List<Personagem> lista();
    
}
