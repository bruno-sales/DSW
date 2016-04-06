package Model;

public enum TipoOferta {
    COMPRA(1), VENDA(2);
    
    private final int valor; 
    TipoOferta(int valorOpcao)
    { 
        valor = valorOpcao; 
    } 
    
    public int getValor()
    { 
        return valor; 
    }

}
