package Model;
import Model.Enums.EOperacao;
import lombok.Data;
import org.joda.time.DateTime;

public @Data class LancamentosDinheiros {
    private int id;
    private int idUsuario;
    private DateTime data;
    private String historico;
    private float valor;
    private EOperacao operacao;
    
    
}
