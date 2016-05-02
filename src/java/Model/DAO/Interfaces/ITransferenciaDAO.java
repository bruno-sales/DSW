package Model.DAO.Interfaces;

import Model.Transferencia;
import java.util.List;

public interface ITransferenciaDAO {
    
    public Transferencia getTransferenciaPorId(int id);
    public List<Transferencia> lista();
    
}
