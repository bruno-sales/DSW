package Model;
import lombok.Data;

//jodatime

public @Data class Token {
    private int IdToken;
    private int IdUsuario; //nao associaçoes explicitas. usar bd
    private String token;
   //private Datetime dataValidade;
}


/*
Entrega segunda classes da descriçao do trabalho
Entregar arq no moodle so com o link pro repositorio no git onde o codigo estiver
*/