package Model;
import Model.Enums.EnumOperacao;
import lombok.Data;
import org.joda.time.DateTime;

public @Data class LancamentosDinheiro {
    private int id;
    private int idUsuario;
    private DateTime data;
    private String historico;
    private float valor;
    private EnumOperacao operacao;
}
