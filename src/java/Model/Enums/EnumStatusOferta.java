package Model.Enums;

public enum EnumStatusOferta {
    ABERTA(0), LIQUIDADA(1), CANCELADA(2);
    
    private final int valor; 
    EnumStatusOferta(int valorOpcao)
    { 
        valor = valorOpcao; 
    } 
    
    public int getValor()
    { 
        return valor; 
    }
}
