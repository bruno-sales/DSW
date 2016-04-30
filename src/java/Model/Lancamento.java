package Model;

import lombok.Data;
import org.joda.time.DateTime;

public @Data
class Lancamento {

    private int id;
    private DateTime data;
    private String operacao; //(Debito/Credito/Bloqueio/Liberacao);
    private String historico;
    private int quantidade;
    private double precoUnitario;
    private int idAtivo;
    private int idUsuario;
}
