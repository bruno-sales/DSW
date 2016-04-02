package Model;
import lombok.Data;

public @Data class Usuario 
{
    private int IdUsuario;
    private String Nome;
    private String Telefone;
    private String Cpf;
    private String Email;
    private String Senha;
    private boolean Logado;
    private String Foto; //mudar depois

    
    public Usuario() 
    {
    }
    
    public Usuario(int IdUsuario, String Nome, String Telefone, String Cpf, String Email, String Senha, boolean Logado, String Foto) 
    {
        this.IdUsuario = IdUsuario;
        this.Nome = Nome;
        this.Telefone = Telefone;
        this.Cpf = Cpf;
        this.Email = Email;
        this.Senha = Senha;
        this.Logado = Logado;
        this.Foto = Foto;
    }
    
    /*private void CadastraUsuario() ///
    {
    }*/
    
}
