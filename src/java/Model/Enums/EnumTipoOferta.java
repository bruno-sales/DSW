package Model.Enums;

import lombok.Getter;

public enum EnumTipoOferta {
    COMPRA(0), VENDA(1);
    
    private final @Getter int codigo;
    
    EnumTipoOferta(int codigo)
    { 
        this.codigo = codigo; 
    } 
    
    public static EnumTipoOferta get(int codigo)
    {
        for (EnumTipoOferta tipo : values())
            if (tipo.getCodigo() == codigo)
                return tipo;
        
        return null;
    }
}
