package Model;
import Model.Enums.EnumTipoOferta;
import Model.Enums.EnumStatusOferta;
import lombok.Data;
import org.joda.time.DateTime;

public @Data class Oferta {
    
    private int id;
    private int idUsuario;
    private int idPersonagem;
    private int idOfertaOriginal;
    private int quantidadeOriginal;
    private int quantidade;
    private DateTime data;
    private float valor;
    private EnumTipoOferta tipoOferta;
    private EnumStatusOferta status;
            
    
    
}
