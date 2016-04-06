package Model;
import lombok.Data;

public @Data class Oferta {
    
    private int id;
    private TipoOferta tipoOferta; 
    private double valor;
    private int idUsuario;
    private StatusOferta status;
    private int qtdGogos;
    private int idOfertaPar;
    private int quantidadeOriginal;
    private int idOfertaOriginal;
            
    
    
}
