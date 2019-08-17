import java.util.Scanner;
public class Baza {
    private String gatunek;
    private String ocena;
    private String nazwa;
    private String  data_premiery;//narazie string bo sie lepiej wczytuje xd potem mozna by rzutowac na inta




    public Baza(String gatunek, String ocena, String nazwa, String data_premiery) {
        this.gatunek=gatunek;
        this.ocena=ocena;
        this.nazwa=nazwa;
        this.data_premiery=data_premiery;

    }

    public Baza(String gatunek, String ocena, String nazwa) {

        this.gatunek=gatunek;
        this.ocena=ocena;
        this.nazwa=nazwa;
    }

    public String getData_premiery() {

        return this.data_premiery;
    }
    public String getGatunek(){

        return this.gatunek;
    }
    public String getOcena(){

        return this.ocena;
    }

    public String getNazwa()
    {

        return nazwa;
    }


    void obejrzyj(){
        //opening z Pinky i Mozg XD O tak!!! XDD
    }

    public void wyszukaj_film_lub_serial(){
        String film; //w nim zapiszemy swoje imie
        Scanner odczyt = new Scanner(System.in); //obiekt do odebrania danych od użytkownika
        film= odczyt.nextLine();

        String serial;
        Scanner odczyt2 = new Scanner(System.in); //obiekt do odebrania danych od użytkownika
        serial= odczyt2.nextLine();
    }
}

