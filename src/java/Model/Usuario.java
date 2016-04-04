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

    
    public Usuario() 
    {
    }
    
    public Usuario(int id, String nome, String telefone, String cpf, String email, String senha, String foto) 
    {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.foto = foto;
    }
    
    
}
