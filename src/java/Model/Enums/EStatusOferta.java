package Model.Enums;

import lombok.Getter;

public enum EStatusOferta {
    ABERTA(0), LIQUIDADA(1), CANCELADA(2);
    
    private final @Getter int codigo;
    EStatusOferta(int codigo)
    { 
        this.codigo = codigo; 
    } 
    
    public static EStatusOferta get(int codigo)
    { 
        for (EStatusOferta tipo : values())
            if (tipo.getCodigo() == codigo)
                return tipo;
        
        return null;
    }
}