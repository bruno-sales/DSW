package Model;
import lombok.Data;
import org.joda.time.DateTime;

public @Data class Token {
    private int id;
    private int idUsuario; 
    private String token;
    private DateTime dataValidade; 
    
    
}