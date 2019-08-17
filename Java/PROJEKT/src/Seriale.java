import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Seriale extends Baza
{
    private String dlugosc;


    public Seriale(String gatunek, String ocena, String nazwa, String dlugosc) {
        super(gatunek, ocena, nazwa);
        this.dlugosc=dlugosc;
    }


    public String getDlugosc() {
        return dlugosc;
    }

  /*  public static void main(String args[])throws IOException
    {
        int n=20;
        BufferedReader nazwa = new BufferedReader(new FileReader("src\\seriale.txt"));
        BufferedReader gatunek = new BufferedReader(new FileReader("src\\gatunek_seriale.txt"));
        BufferedReader dlugosc = new BufferedReader(new FileReader("src\\dlugosc_seriale.txt"));
        BufferedReader ocena = new BufferedReader(new FileReader("src\\oceny_seriale.txt"));

        String nazwa_baza[] = new String[n];
        String gatunek_baza[] = new String[n];
        String dlugosc_baza[] = new String[n];
        String oceny_baza[] = new String[n];

        for (int i = 0; i < n; i++) {

            nazwa_baza[i] = nazwa.readLine();
            gatunek_baza[i] = gatunek.readLine();
            dlugosc_baza[i] = dlugosc.readLine();
            oceny_baza[i] = ocena.readLine();

        }
        //wszystkie seriale

        Seriale seriale[]=new Seriale[n];

        for(int i=0;i<20;i++)
        {
            seriale[i]=new Seriale(gatunek_baza[i],oceny_baza[i],nazwa_baza[i],dlugosc_baza[i]);
        }
        //dla przykladu nazwy filmow - pokazuje ze konstrukroy dzialaja xd

        for(int i=0;i<20;i++)
        {
            System.out.println(seriale[i].getNazwa());
        }

    }
    */
    public void wyszukaj_serial()throws IOException
    {

        int n=20;
        BufferedReader nazwa = new BufferedReader(new FileReader("src\\seriale.txt"));
        BufferedReader gatunek = new BufferedReader(new FileReader("src\\gatunek_seriale.txt"));
        BufferedReader dlugosc = new BufferedReader(new FileReader("src\\dlugosc_seriale.txt"));
        BufferedReader ocena = new BufferedReader(new FileReader("src\\oceny_seriale.txt"));

        String nazwa_baza[] = new String[n];
        String gatunek_baza[] = new String[n];
        String dlugosc_baza[] = new String[n];
        String oceny_baza[] = new String[n];

        for (int i = 0; i < n; i++) {

            nazwa_baza[i] = nazwa.readLine();
            gatunek_baza[i] = gatunek.readLine();
            dlugosc_baza[i] = dlugosc.readLine();
            oceny_baza[i] = ocena.readLine();

        }
        //wszystkie seriale

        Seriale seriale[]=new Seriale[n];

        for(int i=0;i<20;i++)
        {
            seriale[i]=new Seriale(gatunek_baza[i],oceny_baza[i],nazwa_baza[i],dlugosc_baza[i]);
        }
        //dla przykladu nazwy filmow - pokazuje ze konstrukroy dzialaja xd

        for(int i=0;i<20;i++)
        {
            System.out.println(seriale[i].getNazwa());
        }


        String serial; //w nim zapiszemy swoje imie
        Scanner odczyt = new Scanner(System.in); //obiekt do odebrania danych od użytkownika
        serial= odczyt.nextLine();
        serial.toLowerCase();


        for(int i = 0; i<20; i++){ //to lower case zeby moglo znalezc a nie sie buntowac bo wielka/duza litera XD
          if (serial.equals(seriale[i].getGatunek().toLowerCase()))
            System.out.println("serial ktory szukales jest na liscie. Czeka na obejrzenie ");
            else
        System.out.println("nie ma serialu ktorego szukasz");}
    }
    public static void main(String args[]){}//o testow xd w sensie czy to ma rece i nogi XD
}