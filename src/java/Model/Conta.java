package Model;
import lombok.Data;

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
