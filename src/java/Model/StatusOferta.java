package Model;

public enum StatusOferta {
    ABERTA(1), CANCELADA(2), EXECUTADA(3);
    
    private final int valor; 
    StatusOferta(int valorOpcao)
    { 
        valor = valorOpcao; 
    } 
    
    public int getValor()
    { 
        return valor; 
    }
}
