package Model.DAO.Interfaces;

import Model.Token;

public interface ITokenDAO {
 
    public boolean insere(Token tk);
    public boolean verficaValidadeToken(int idUsuario, String token);
    
}
