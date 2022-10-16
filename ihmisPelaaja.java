import java.util.Scanner;

public class ihmisPelaaja extends Pelaaja{
    private boolean syoteSallittu;
    private Scanner peliRuutuSyote = new Scanner(System.in);

    /**
     * Tulostetaan pelilauta.
     * Tarkistetaan ihmispelaajien määrä, kysytään meneillään olevan vuoron perusteella oikealta pelaajalta ruudun numeroa. 
     * Varmistetaan, että numero vastaa ruutua, että ruutu ei ole täytetty, ja että ylipäätään syötetään numero. 
     * Asetetaan pelaajan merkki ja päivitetään pelitilanne.
     */
    public void Pelaa(Pelilauta pelilauta) {
        syoteSallittu = false;
        pelilauta.tulostaLauta();
        while (!syoteSallittu) {
            try {
                if (pelilauta.annapelaajaMaara() == 2) {
                    if (pelilauta.annaKierros() %2 != 0) {
                        System.out.println("Pelaaja 1 (X): Aseta merkkisi vapaaseen ruutuun syöttämällä ruudun numero:");   
                    }
                    else {
                        System.out.println("Pelaaja 2 (O): Aseta merkkisi vapaaseen ruutuun syöttämällä ruudun numero:");
                    }
                }
                else {
                    System.out.println("Aseta merkkisi vapaaseen ruutuun syöttämällä ruudun numero:");
                }
                seuraavaRuutu = Integer.parseInt(peliRuutuSyote.nextLine());
                if (seuraavaRuutu < 1 || seuraavaRuutu > 9) {
                    System.out.println("Numero ei vastaa ruutua. Syötä uusi numero:");
                    continue;
                }
                if (pelilauta.annaPelatutRuudut().contains(seuraavaRuutu) == true) {
                    System.out.println("Ruutu on jo täytetty. Valitse tyhjä ruutu:");
                    continue;
                }
                syoteSallittu = true;
                pelilauta.asetaMerkki(seuraavaRuutu);
                pelilauta.paivitaPelitilanne();
            } catch (NumberFormatException e) {
            System.out.println("Syötteesi ei ole sopiva numero. Syötä numero:");
            }
        }
    }

    public void suljeScanner() {
        peliRuutuSyote.close();
    }
}