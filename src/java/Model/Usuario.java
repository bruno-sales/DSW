package Model;
import lombok.Data;

public @Data class Usuario 
{
    private int id;
    private String nome;
    private String telefone;
    private String cpf;
    private String email;
    private String senha;
    private String foto;
}
