import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Films extends Baza{

    //dziedziczony konstruktor
    public Films(String gatunek, String ocena, String nazwa, String rok) {
        super(gatunek,ocena,nazwa,rok);
    }

/*
    public static void main(String args[])throws IOException
    {
        int n=20;
        BufferedReader nazwa = new BufferedReader(new FileReader("src\\nazwa_filmy.txt"));
        BufferedReader gatunek = new BufferedReader(new FileReader("src\\gatunek_filmy.txt"));
        BufferedReader rok = new BufferedReader(new FileReader("src\\rok_filmy.txt"));
        BufferedReader ocena = new BufferedReader(new FileReader("src\\ocena_filmy.txt"));

        String nazwa_baza[] = new String[n];
        String gatunek_baza[] = new String[n];
        String rok_baza[] = new String[n];
        String ocena_baza[] = new String[n];

        for (int i = 0; i < n; i++) {

            nazwa_baza[i] = nazwa.readLine();
            gatunek_baza[i] = gatunek.readLine();
            rok_baza[i] = rok.readLine();
            ocena_baza[i] = ocena.readLine();

        }

        //wszystkie filmy
        Films film[]=new Films[n];

        for(int i=0;i<20;i++)
        {
           film[i]=new Films(gatunek_baza[i],ocena_baza[i],nazwa_baza[i],rok_baza[i]);
        }
        //dla przykladu nazwy filmow - pokazuje ze konstrukroy dzialaja xd
        for(int i=0;i<20;i++)
        {
            System.out.println(film[i].getNazwa());
        }
    }
    */
    public void wyszukaj_film()throws IOException
    {
        int n=20;
        BufferedReader nazwa = new BufferedReader(new FileReader("src\\nazwa_filmy.txt"));
        BufferedReader gatunek = new BufferedReader(new FileReader("src\\gatunek_filmy.txt"));
        BufferedReader rok = new BufferedReader(new FileReader("src\\rok_filmy.txt"));
        BufferedReader ocena = new BufferedReader(new FileReader("src\\ocena_filmy.txt"));

        String nazwa_baza[] = new String[n];
        String gatunek_baza[] = new String[n];
        String rok_baza[] = new String[n];
        String ocena_baza[] = new String[n];

        for (int i = 0; i < n; i++) {

            nazwa_baza[i] = nazwa.readLine();
            gatunek_baza[i] = gatunek.readLine();
            rok_baza[i] = rok.readLine();
            ocena_baza[i] = ocena.readLine();

        }

        //wszystkie filmy
        Films films[]=new Films[n];

        for(int i=0;i<20;i++)
        {
            films[i]=new Films(gatunek_baza[i],ocena_baza[i],nazwa_baza[i],rok_baza[i]);
        }
        //dla przykladu nazwy filmow - pokazuje ze konstrukroy dzialaja xd
        for(int i=0;i<20;i++)
        {
            System.out.println(films[i].getNazwa());
        }

        String film; //w nim zapiszemy swoje imie
        Scanner odczyt = new Scanner(System.in); //obiekt do odebrania danych od użytkownika
        film= odczyt.nextLine();

        for(int i = 0; i<20; i++){
            if (film.equals(films[i].getGatunek()))
                System.out.println("film ktory szukales jest na liscie. Czeka na obejrzenie :D");
            else
                System.out.println("nie ma serialu ktorego szukasz");}

    }

}
