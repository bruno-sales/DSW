package Model;
import lombok.Data;

public @Data class Oferta {
    
    private int IdOferta;
    private String TipoOferta; //Compra ou Venda
    private double Valor;
    private Usuario Usuario;
    private String Status;
    private int QtdGogos;

    public Oferta() {
    }

    
    public Oferta(int IdOferta, String TipoOferta, double Valor, Usuario Usuario, String Status, int QtdGogos) {
        this.IdOferta = IdOferta;
        this.TipoOferta = TipoOferta;
        this.Valor = Valor;
        this.Usuario = Usuario;
        this.Status = Status;
        this.QtdGogos = QtdGogos;
    }
    
    
}
