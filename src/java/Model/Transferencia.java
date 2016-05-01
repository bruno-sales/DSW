package Model;
import lombok.Data;
import org.joda.time.DateTime;

public @Data class Transferencia {

    private int id;
    private double valor;
    private DateTime data;
    private int idUsuario;
    private String numeroBanco;
    private String numeroAgencia;
    private String numeroConta;

}

/*
public @Data class Conta {

    private double saldo;
    private String extrato;
    private Integer numeroDaConta;
    private Usuario Usuario;

    public Conta(double saldo, String extrato, Integer numeroDaConta) {
        this.saldo = saldo;
        this.extrato = extrato;
        this.numeroDaConta = numeroDaConta;
    }
}
*/