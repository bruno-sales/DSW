package Model.Enums;

public enum EnumTipoOferta {
    COMPRA(0), VENDA(1);
    
    private final int valor; 
    EnumTipoOferta(int valorOpcao)
    { 
        valor = valorOpcao; 
    } 
    
    public int getValor()
    { 
        return valor; 
    }
}
