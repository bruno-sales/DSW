package Model;
import lombok.Data;

public @Data class Token {
    private int IdToken;
    private int IdUsuario; 
    private String token;
   //private Datetime dataValidade; 

    public Token() {
    }

    public Token(int IdToken, int IdUsuario, String token) {
        this.IdToken = IdToken;
        this.IdUsuario = IdUsuario;
        this.token = token;
    }
    
    
}