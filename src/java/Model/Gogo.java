package Model;
import lombok.Data;

public @Data class Gogo {
    
    private int IdGogo;
    private String NomeGogo;
    private String Foto;

    public Gogo() {
    }

    
    public Gogo(int IdGogo, String NomeGogo, String Foto) {
        this.IdGogo = IdGogo;
        this.NomeGogo = NomeGogo;
        this.Foto = Foto;
    }
    
    
}
