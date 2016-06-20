package Model.DAO.Interfaces;

import Model.Oferta;
import java.util.List;

public interface IOfertaDAO {
    
    public Oferta getOfertaPorId(int id);
    public List<Oferta> lista();
    public int CountOfertasUsuario(int userId);
    public List<Oferta> listaOfertasUsuario(int userId,int pagina, int tamanho);
    public boolean registrarCompra(int idUsuario, int idPersonagem, int quantidade, float valorUnitario);
}
