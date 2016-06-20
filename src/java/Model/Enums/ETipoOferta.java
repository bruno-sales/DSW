package Model.Enums;

import lombok.Getter;

public enum ETipoOferta {
    COMPRA(1), VENDA(0);
    
    private final @Getter int codigo;
    
    ETipoOferta(int codigo)
    { 
        this.codigo = codigo; 
    } 
    
    public static ETipoOferta get(int codigo)
    { 
        for (ETipoOferta tipo : values())
            if (tipo.getCodigo() == codigo)
                return tipo;
        
        return null;
    }
}
