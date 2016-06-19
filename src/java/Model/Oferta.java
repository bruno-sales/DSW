package Model;
import Model.Enums.ETipoOferta;
import Model.Enums.EStatusOferta;
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
    private ETipoOferta tipoOferta;
    private EStatusOferta status;
            
    public String getNomeTipoOferta()
    {
        return this.tipoOferta.name();
    }
    
}
