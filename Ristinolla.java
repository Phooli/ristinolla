import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Ristinolla {
    private Scanner syote = new Scanner(System.in);
    private Pelilauta pelilauta = new Pelilauta('1', '2', '3', '4', '5', '6', '7', '8', '9');
    private Pelaaja Pelaaja1 = new ihmisPelaaja();
    private Pelaaja Pelaaja2;
    private File tallennus = new File("tallennus.txt");
    private String jatketaankoTallennuksesta;
    public void pelaa() {
        if (pelilauta.annaKierros() != 1) {
            System.out.println("Haluatko jatkaa tallennettua peliä? (Syötä K/E):");
            jatketaankoTallennuksesta = syote.nextLine();
            if (jatketaankoTallennuksesta.charAt(0) == 'K' || jatketaankoTallennuksesta.charAt(0) == 'k') {
                System.out.println("Jatketaan tallennettua peliä...");
                try {
                    Thread.sleep(3000);
                }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            else {
                pelilauta.nollaaPelitilanne();
            }
        }
        while (pelilauta.annapelaajaMaara() != 1 && pelilauta.annapelaajaMaara() != 2) {
            try {
                System.out.println("Syötä ihmispelaajien määrä (1/2)");
                pelilauta.asetaPelaajaMaara(Integer.parseInt(syote.nextLine()));
                if (pelilauta.annapelaajaMaara() < 1 || pelilauta.annapelaajaMaara() > 2) {
                    System.out.println("Syötetty pelaajamäärä ei kelpaa");
                    continue;
                }
            } catch (NumberFormatException e) {
            System.out.println("Syötteesi ei ole sopiva numero.");
            }
            if (pelilauta.annapelaajaMaara() == 1) {
                Pelaaja2 = new tietokonePelaaja();
            }
            else {
                Pelaaja2 = new ihmisPelaaja();
            }
        }
        while (pelilauta.peliLoppu() == false) {
            if (pelilauta.annaKierros() %2 == 1) {
                Pelaaja1.Pelaa(pelilauta);
                try { 
                    tallenna();
                } catch (IOException e) {
                    System.out.println("Jokin meni pieleen!");
                }
            } 
            if (pelilauta.peliLoppu() == false) {
                if (pelilauta.annaKierros() %2 != 1) {
                    Pelaaja2.Pelaa(pelilauta);
                    try { 
                        tallenna();
                    } catch (IOException e) {
                        System.out.println("Jokin meni pieleen!");
                    }
                }    
            }
        }
        pelilauta.tulostaLauta();
        pelilauta.julistaLopputulos();
        pelilauta.nollaaPelitilanne();
        try { 
            tallenna();
        } catch (IOException e) {
            System.out.println("Jokin meni pieleen!");
        }
        syote.close();
        Pelaaja1.suljeScanner();
        Pelaaja2.suljeScanner();
    }
    public void tallenna() throws IOException {
        FileWriter tallentaja = new FileWriter(tallennus);
        tallentaja.write(pelilauta.annaPelitilanne());
        tallentaja.close();
    }
    public void lataa() throws IOException {
        Path tallennusPolku = tallennus.toPath();
        List<String> luku = Files.readAllLines(tallennusPolku);
        pelilauta = new Pelilauta(luku.get(0).charAt(0), luku.get(0).charAt(2), luku.get(0).charAt(4), luku.get(0).charAt(6), luku.get(0).charAt(8), luku.get(0).charAt(10), luku.get(0).charAt(12), luku.get(0).charAt(14), luku.get(0).charAt(16)); 
        pelilauta.asetaPelaajaMaara(Integer.parseInt(luku.get(0).substring(18, 19)));
        if (pelilauta.annapelaajaMaara() == 1) {
            Pelaaja2 = new tietokonePelaaja();
        }
        else {
            Pelaaja2 = new ihmisPelaaja();
        }
        pelilauta.asetaKierros(Integer.parseInt(luku.get(0).substring(20)));
        pelilauta.selvitaPelatutRuudut();
    }
}