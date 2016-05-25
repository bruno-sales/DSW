package Model.Enums;

import lombok.Getter;

public enum ETipoOferta {
    COMPRA(0), VENDA(1);
    
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
