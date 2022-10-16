import java.util.ArrayList;

public class Pelilauta {
    private char[][] lauta; 

    /**
     * Kierros = jokainen pelattava vuoro, max 9.
     */
    private int kierros = 1;
    private int pelaajaMaara;

    /**
     * Esittää pelitilanteen csv-muodossa: Ensin ruudut 1-9 (joko ruudun numero tai asetettu merkki), sitten ihmispelaajien määrä, ja meneillään olevan vuoron numero.
     */
    private StringBuilder pelitilanne;

    /**
     * Pelatut ruudut taulukossa, jotta pelaajilta saataisiin syöte vapaana olevasta ruudusta. 
     */
    private ArrayList<Integer> pelatutRuudut = new ArrayList<>();

    public Pelilauta(char a, char b, char c, char d, char e, char f, char g, char h, char i) {
        lauta = new char[3][3];
        lauta[0][0] = a; 
        lauta[0][1] = b;
        lauta[0][2] = c;
        lauta[1][0] = d;
        lauta[1][1] = e;
        lauta[1][2] = f;
        lauta[2][0] = g;
        lauta[2][1] = h;
        lauta[2][2] = i;
    }

    public int annapelaajaMaara() {
        return pelaajaMaara;
    }

    public void asetaPelaajaMaara(int pelaajaMaara) {
        this.pelaajaMaara = pelaajaMaara;
    }
    public String annaPelitilanne() {
        return pelitilanne.toString();
    }

    /**
     * Palauttaa tämänhetkisen pelitilanteen merkkijonona.
     */
    public String paivitaPelitilanne() {
        pelitilanne = new StringBuilder();
        pelitilanne.append(lauta[0][0]);
        pelitilanne.append(',');
        pelitilanne.append(lauta[0][1]);
        pelitilanne.append(',');
        pelitilanne.append(lauta[0][2]);
        pelitilanne.append(',');
        pelitilanne.append(lauta[1][0]);
        pelitilanne.append(',');
        pelitilanne.append(lauta[1][1]);
        pelitilanne.append(',');
        pelitilanne.append(lauta[1][2]);
        pelitilanne.append(',');
        pelitilanne.append(lauta[2][0]);
        pelitilanne.append(',');
        pelitilanne.append(lauta[2][1]);
        pelitilanne.append(',');
        pelitilanne.append(lauta[2][2]);
        pelitilanne.append(',');
        pelitilanne.append(Integer.toString(pelaajaMaara).charAt(0));
        pelitilanne.append(',');
        pelitilanne.append(Integer.toString(kierros).charAt(0));
        return pelitilanne.toString();
    }

    /**
     * Palauttaa pelitilanteen lähtöasetelmiin, jossa ei asetettua pelaajamäärää ja kierros 1. 
     */
    public void nollaaPelitilanne() {
        pelitilanne = new StringBuilder();
        pelitilanne.append("1,2,3,4,5,6,7,8,9,0,1");
        asetaPelaajaMaara(0);
        asetaKierros(1);
        lauta[0][0] = '1'; 
        lauta[0][1] = '2';
        lauta[0][2] = '3';
        lauta[1][0] = '4';
        lauta[1][1] = '5';
        lauta[1][2] = '6';
        lauta[2][0] = '7';
        lauta[2][1] = '8';
        lauta[2][2] = '9';
        pelatutRuudut.clear();
    }
    public int annaKierros() {
        return kierros;
    }
    public void asetaKierros(int kierros) {
        this.kierros = kierros;
    }

    /**
     * Tarkistaa jokaisen ruudun asetetun merkin varalta, ja lisää tällöin ruudun pelattujen ruutujen taulukkoon.
     */
    public void selvitaPelatutRuudut() {
        if (lauta[0][0] != '1') {
            pelatutRuudut.add(1);
        }
        if (lauta[0][1] != '2') {
            pelatutRuudut.add(2);
        }
        if (lauta[0][2] != '3') {
            pelatutRuudut.add(3);
        }
        if (lauta[1][0] != '4') {
            pelatutRuudut.add(4);
        }
        if (lauta[1][1] != '5') {
            pelatutRuudut.add(5);
        }
        if (lauta[1][2] != '6') {
            pelatutRuudut.add(6);
        }
        if (lauta[2][0] != '7') {
            pelatutRuudut.add(7);
        }
        if (lauta[2][1] != '8') {
            pelatutRuudut.add(8);
        }
        if (lauta[2][2] != '9') {
            pelatutRuudut.add(9);
        }
    }
    public ArrayList<Integer> annaPelatutRuudut() {
        return pelatutRuudut;
    }

    /**
     * Tulostaa yksinkertaisen ristinolla-laudan komentoriville senhetkisen pelitilanteen mukaisesti. 
     */
    public void tulostaLauta() {
        System.out.println("");
        System.out.println(lauta[0][0] + "|" + lauta[0][1] + "|" + lauta[0][2]);
        System.out.println("-" + "+" + "-" + "+" + "-");
        System.out.println(lauta[1][0] + "|" + lauta[1][1] + "|" + lauta[1][2]);
        System.out.println("-" + "+" + "-" + "+" + "-");
        System.out.println(lauta[2][0] + "|" + lauta[2][1] + "|" + lauta[2][2]);
        System.out.println("");
    }

    /**
     * Asetetaan oikea merkki kierroksen perusteella (pariton = X, parillinen = O) pelaajan antamaa lukua vastaavaan ruutuun.
     * Lisätään pelattu ruutu pelattujen ruutujen taulukkoon.
     * Kasvatetaan kierroksen lukua yhdellä.
     */
    public void asetaMerkki(int numero) {
        char merkki;
        if (kierros %2 == 1) {
            merkki = 'X';
        }
        else {
            merkki = 'O';
        }
        if (numero == 1) {
            lauta[0][0] = merkki;
        }
        if (numero == 2){
            lauta[0][1] = merkki;
        }
        if (numero == 3) {
            lauta[0][2] = merkki;
        }
        if (numero == 4) {
            lauta[1][0] = merkki;
        }
        if (numero == 5) {
            lauta[1][1] = merkki;
        }
        if (numero == 6) {
            lauta[1][2] = merkki;
        }
        if (numero == 7) {
            lauta[2][0] = merkki;
        }
        if (numero == 8) {
            lauta[2][1] = merkki;
        }
        if (numero == 9) {
            lauta[2][2] = merkki;
        }
        pelatutRuudut.add(numero);
        kierros++;
    }

    /**
     * Tulostetaan pelin lopputulos. Voiton tapauksessa voittaja selviää pelaajien määrän ja kierroksen avulla. Jos voittoa ei ole, julistetaan tasapeli.
     */
    public void julistaLopputulos() {
        if (onkoVoitto() == true) {
            if (pelaajaMaara == 2) {
                if (kierros %2 == 0) {
                    System.out.println("Pelaaja 1 (X) voittaa!");
                }
                else {
                    System.out.println("Pelaaja 2 (O) voittaa!");
                }
            }
            else {
                if (kierros %2 == 0) {
                    System.out.println("Voitit pelin!");
                }
                else {
                    System.out.println("Hävisit pelin!");
                }
            }
        }
        else {
            System.out.println("Tasapeli!");
        }
    }

    /**
     * Palauttaa booleanin siitä, onko peli loppu. Voiton puuttuessa ja ruudukossa ollessa tilaa peli ei ole loppu.
     * @return
     */
    public boolean peliLoppu() {
        if (onkoVoitto() == false && onkoTaynna() == false) {
            return false;
        }
        return true;
    }

    /**
     * Palautetaan boolean voitosta brute force -selvityksen perusteella siitä, että onko pelilaudalla sääntöjen mukaisesti kolmen merkin yhdistelmä.
     */
    private boolean onkoVoitto() {
        if (lauta[0][0] == lauta[0][1] && lauta[0][0] == lauta[0][2]) {
            return true;
            }
        if (lauta[1][0] == lauta[1][1] && lauta[1][0] == lauta[1][2]) {
            return true;
        }
        if (lauta[2][0] == lauta[2][1] && lauta[2][0] == lauta[2][2]) {
            return true;
        }
        if (lauta[0][0] == lauta[1][0] && lauta[0][0] == lauta[2][0]) {
            return true;
        }
        if (lauta[0][1] == lauta[1][1] && lauta[0][1] == lauta[2][1]) {
            return true;
        }
        if (lauta[0][2] == lauta[1][2] && lauta[0][2] == lauta[2][2]) {
            return true;
        }
        if (lauta[0][0] == lauta[1][1] && lauta[0][0] == lauta[2][2]) {
            return true;
        }
        if (lauta[0][2] == lauta[1][1] && lauta[0][2] == lauta[2][0]) {
            return true;
        }
        return false;
    }

    /**
     * Palautetaan boolean siitä, onko laudalla tilaa. Tarkistetaan ruutuja, kunnes jossain ruudussa ei ole merkkiä. Jos kaikissa ruuduissa merkki, lauta on täynnä.
     */
    private boolean onkoTaynna() {
        if (lauta[0][0] != 'O' && lauta[0][0] != 'X') {
            return false;
        }
        if (lauta[0][1] != 'O' && lauta[0][1] != 'X') {
            return false;
        }
        if (lauta[0][2] != 'O' && lauta[0][2] != 'X') {
            return false;
        }
        if (lauta[1][0] != 'O' && lauta[1][0] != 'X') {
            return false;
        }
        if (lauta[1][1] != 'O' && lauta[1][1] != 'X') {
            return false;
        }
        if (lauta[1][2] != 'O' && lauta[1][2] != 'X') {
            return false;
        }
        if (lauta[2][0] != 'O' && lauta[2][0] != 'X') {
            return false;
        }
        if (lauta[2][1] != 'O' && lauta[2][1] != 'X') {
            return false;
        }
        if (lauta[2][2] != 'O' && lauta[2][2] != 'X') {
            return false;
        }
        return true;
    }
}