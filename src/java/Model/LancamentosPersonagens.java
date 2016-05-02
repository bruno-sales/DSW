package Model;

import Model.Enums.EnumOperacao;
import lombok.Data;
import org.joda.time.DateTime;

public @Data class LancamentosPersonagens {

    private int id;
    private DateTime data;
    private EnumOperacao operacao; 
    private String historico;
    private int quantidade;
    private float precoUnitario;
    private int idUsuario;
    private int idPersonagem;
}
