package Model.Enums;

public enum EnumOperacao {
    CREDITO(0), DEBITO(1), BLOQUEIO(2), DESBLOQUEIO(3);
  
    private final int valor; 
    EnumOperacao(int valorOpcao)
    { 
        valor = valorOpcao; 
    } 
    
    public int getValor()
    { 
        return valor; 
    }
}
