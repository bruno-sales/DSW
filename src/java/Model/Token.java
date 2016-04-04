package Model;
import lombok.Data;
import org.joda.time.DateTime;

public @Data class Token {
    private int IdToken;
    private int IdUsuario; 
    private String token;
    private DateTime dataValidade; 

    public Token() {
    }

    public Token(int IdToken, int IdUsuario, String token) {
        this.IdToken = IdToken;
        this.IdUsuario = IdUsuario;
        this.token = token;
    }
    
    
}