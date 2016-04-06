package Model;

public enum StatusOferta {
    ABERTA(1), EXECUTADA(2);
    
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
