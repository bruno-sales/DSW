package Model;
import lombok.Data;

public @Data class Ativo 
{
    private int id;
    private String nome;
    private boolean negociavel; //(true = personagem / false = moeda)
}