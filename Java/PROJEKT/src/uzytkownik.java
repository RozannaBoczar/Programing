public class uzytkownik  {

    private String login;
    private String haslo;
    private int ilosc_wypozyczonych_filmow;
    private int ilosc_polecanych_filmow;
    private Films wypozyczone_filmy;
    private Seriale wypozyczone_seriale;

    //dzie konstruktor 2.0 XD!
    public uzytkownik(String login,String haslo, int ilosc_wypozyczonych_filmow,int ilosc_polecanych_filmow)
    {
        this.login=login;
        this.haslo=haslo;
        this.ilosc_polecanych_filmow=ilosc_polecanych_filmow;
        this.ilosc_wypozyczonych_filmow=ilosc_wypozyczonych_filmow;
    }

    public String getLogin(){
        return this.login;
    }

    public String getHaslo(){
        return this.haslo;
    }

    public int getIlosc_wypozyczonych_filmow(){
        return this.ilosc_wypozyczonych_filmow;
    }

    public int getIlosc_polecanych_filmow(){
        return this.ilosc_polecanych_filmow;
    }

    public void wypozycz_film(){
        if(this.ilosc_wypozyczonych_filmow<10)
        this.ilosc_wypozyczonych_filmow=this.ilosc_wypozyczonych_filmow+1;
        else
            System.out.println("nie mozesz wypozyczyc wiecej filmow");
    }

    public void oddaj_film(){
        if(this.ilosc_wypozyczonych_filmow>0)
            this.ilosc_wypozyczonych_filmow=this.ilosc_wypozyczonych_filmow-1;
        else
            System.out.println("nie posiadasz aktualnie zadnych filmow do oddania");
    }



}
