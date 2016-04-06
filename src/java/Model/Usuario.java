package Model;
import lombok.Data;
//import java.util.ArrayList;
import java.util.List;

public @Data class Usuario 
{
    private int id;
    private String nome;
    private String telefone;
    private String cpf;
    private String email;
    private String senha;
    private String foto;
    
    List<Gogo> gogos; //Isso não será mapeado no banco
        
    
}
