package Model;

import lombok.Data;
import org.joda.time.DateTime;

public @Data class CasamentoOfertas {
    private int idOfertaCompra;
    private int idOfertaVenda;
    private DateTime dataExecucao;
}
