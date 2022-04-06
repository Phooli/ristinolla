import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Ristinolla Ristinolla = new Ristinolla();
        try {
            Ristinolla.lataa();
        } catch (IOException e) {
           System.out.println("Jokin meni pieleen!"); 
        }
        Ristinolla.pelaa();
    }
}