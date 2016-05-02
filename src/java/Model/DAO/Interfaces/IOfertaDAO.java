package Model.DAO.Interfaces;

import Model.Oferta;
import java.util.List;

public interface IOfertaDAO {
    
    public Oferta getOfertaPorId(int id);
    public List<Oferta> lista();
    
}
