package Model;
import lombok.Data;
/* TODO
Criar historico de transação de dinheiro e de personagem:
Transferencia 
- id
- Valor
- Data
- IdUsuario
- Banco Origem
- Agencia de origem
- Conta de origem


Ativo
- id
- Nome
- Negociavel (true = personagem / false = moeda)

Lançamento
- id
- Data
- Operacao (Debito/Credito/Bloqueio/Liberacao)
- Historico
- Quantidade 
- Preço Unitario
- IdAtivo
- IdUsuario

Enumerable para TipoOferta
Enumerable para StatusOferta



Ao invés de referenciar objetos nas classes [Usuario user, por exemplo], referenciar apenas Id_____ 


*/

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
