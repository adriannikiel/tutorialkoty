package pl.kobietydokodu.koty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Pattern;

import pl.kobietydokodu.koty.domain.Kot;

public class Interfejs {

    static Scanner sc = new Scanner(System.in);
    
    static KotDAO kotDao = new KotDAO();

    public static void main(String[] args) {
    	String wyborUzytkownika;
    	do {
    		System.out.println();
    		System.out.println("Wybierz, co chcesz zrobiæ, a nastêpnie zatwierdŸ enterem:");
	    	System.out.println("[1] Dodaj nowego kota");
	    	System.out.println("[2] Poka¿ wszystkie koty");
	    	System.out.println("[x] Zakoñcz");
			wyborUzytkownika = getUserInput();
	    	if (wyborUzytkownika.equals("1")) {
	    		dodajKota();
	    	} else if (wyborUzytkownika.equals("2")) {
	    		pokazKoty();
	    	}
    	} while (!wyborUzytkownika.equalsIgnoreCase("x"));
        
    }

	private static void pokazKoty() {
		System.out.println();
		System.out.println("#########################################################");
		System.out.println("######                 LISTA KOTÓW                 ######");
		System.out.println("#########################################################");
		
		Kot kot;
		for (int i=0; i<kotDao.getKoty().size(); i++) {
			kot = kotDao.getKoty().get(i);
			System.out.println(i + ": " + kot.getImie());
		}
		System.out.println();
		Pattern wzorzecNumeru = Pattern.compile("[0-9]+");
		String numerWczytany;
		do {
			System.out.print("Którego kota chcesz poznaæ bli¿ej? ");
			numerWczytany = getUserInput();
		} while (!wzorzecNumeru.matcher(numerWczytany).matches());
		
		Integer numerKota = Integer.parseInt(numerWczytany);
		if (kotDao.getKoty().size()>numerKota) {
			Kot wybranyKot = kotDao.getKoty().get(numerKota);
			System.out.println("Wybrany kot ma na imiê "+wybranyKot.getImie()+", wa¿y "+wybranyKot.getWaga()+", urodzi³‚ siê™ "+wybranyKot.getDataUrodzenia().toString()+", a opiekuje siê nim "+wybranyKot.getImieOpiekuna());
		} else {
			System.out.println("Niestety, nie znalaz³em kota o wybranym numerze :( Sprobój ponownie lub go dodaj!");
		}
	}

	private static void dodajKota() {
		System.out.println();
		System.out.println("#########################################################");
		System.out.println("######                 DODAJ  KOTA                 ######");
		System.out.println("#########################################################");
		Kot kot = new Kot();
		System.out.print("Podaj imiê kota: ");
        kot.setImie(getUserInput());

        Pattern wzorzecDaty = Pattern.compile("[0-9]{4}.[0-1]?[0-9].[0-3]?[0-9]");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String dataUrodzeniaWczytana;
        do {
            System.out.print("Podaj datê urodzenia kota w formacie RRRR.MM.DD: ");
            dataUrodzeniaWczytana = getUserInput();
            if (wzorzecDaty.matcher(dataUrodzeniaWczytana).matches()) {
            	try {
            		kot.setDataUrodzenia(sdf.parse(dataUrodzeniaWczytana));
            	} catch (ParseException pe) {
            		System.out.println("Coœ jest nie tak z dat¹! Przyk³adowa data: 2014.01.05");
            	}
            }
        } while (kot.getDataUrodzenia()==null);
        
        Pattern wzorzecWagi = Pattern.compile("[0-9]+(\\.[0-9]+)?");
        String wagaWczytana;
        do {
            System.out.print("Podaj wagê kota: ");
            wagaWczytana = getUserInput();
            
            if (wzorzecWagi.matcher(wagaWczytana).matches()) {
                kot.setWaga(Float.valueOf(wagaWczytana));
            }
        } while (kot.getWaga() == null);

        System.out.print("Podaj kto jest opiekunem kota: ");
        kot.setImieOpiekuna(getUserInput());

        kotDao.dodajKota(kot);
        
        System.out.println("Dziêkujê, teraz wiem o kocie naprawdê wszystko! Doda³em go do naszego zbioru.");
	}

    public static String getUserInput() {
        return sc.nextLine().trim();
    }

}
