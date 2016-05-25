package Model.Enums;

import lombok.Getter;

public enum EOperacao {
    CREDITO(0), DEBITO(1), BLOQUEIO(2), DESBLOQUEIO(3);
  
    private final @Getter int codigo;
    
    EOperacao(int codigo)
    { 
        this.codigo = codigo; 
    } 
    
    public static EOperacao get(int codigo)
    { 
        for (EOperacao tipo : values())
            if (tipo.getCodigo() == codigo)
                return tipo;
        
        return null;
    }
}