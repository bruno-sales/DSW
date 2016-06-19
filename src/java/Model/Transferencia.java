package Model;
import lombok.Data;
import org.joda.time.DateTime;

public @Data class Transferencia {

    private int id;
    private float valor;
    private DateTime data;
    private int idUsuario;
    private String numeroBanco;
    private String numeroAgencia;
    private String numeroConta;

}