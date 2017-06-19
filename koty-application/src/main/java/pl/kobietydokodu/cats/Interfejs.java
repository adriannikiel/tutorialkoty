package pl.kobietydokodu.cats;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Pattern;

import pl.kobietydokodu.cats.dao.OldCatDAO;
import pl.kobietydokodu.cats.domain.Cat;

/**
 * Very simple console application that uses standard input to create cats and standard output to show them on the screen.  
 */
public class Interfejs {

    static Scanner sc = new Scanner(System.in);
    
    static OldCatDAO catDao = new OldCatDAO(); //static CatDAO catDao = new CatDAO();

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
		
		Cat cat;
		for (int i=0; i<catDao.getCats().size(); i++) {
			cat = catDao.getCats().get(i);
			System.out.println(i + ": " + cat.getName());
		}
		System.out.println();
		Pattern wzorzecNumeru = Pattern.compile("[0-9]+");
		String numerWczytany;
		do {
			System.out.print("Którego kota chcesz poznaæ bli¿ej? ");
			numerWczytany = getUserInput();
		} while (!wzorzecNumeru.matcher(numerWczytany).matches());
		
		Integer numerKota = Integer.parseInt(numerWczytany);
		if (catDao.getCats().size()>numerKota) {
			Cat wybranyKot = catDao.getCats().get(numerKota);
			System.out.println("Wybrany kot ma na imiê "+wybranyKot.getName()+", wa¿y "+wybranyKot.getWeight()+", urodzi³‚ siê ™ "+wybranyKot.getBirthday().toString()+", a opiekuje siê nim "+wybranyKot.getGuardianName());
		} else {
			System.out.println("Niestety, nie znalaz³em kota o wybranym numerze :( Sprobój ponownie lub go dodaj!");
		}
	}

	private static void dodajKota() {
		System.out.println();
		System.out.println("#########################################################");
		System.out.println("######                 DODAJ  KOTA                 ######");
		System.out.println("#########################################################");
		Cat cat = new Cat();
		System.out.print("Podaj imiê kota: ");
        cat.setName(getUserInput());

        Pattern wzorzecDaty = Pattern.compile("[0-9]{4}.[0-1]?[0-9].[0-3]?[0-9]");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String dataUrodzeniaWczytana;
        do {
            System.out.print("Podaj datê urodzenia kota w formacie RRRR.MM.DD: ");
            dataUrodzeniaWczytana = getUserInput();
            if (wzorzecDaty.matcher(dataUrodzeniaWczytana).matches()) {
            	try {
            		cat.setBirthday(sdf.parse(dataUrodzeniaWczytana));
            	} catch (ParseException pe) {
            		System.out.println("Coœ jest nie tak z dat¹! Przyk³adowa data: 2014.01.05");
            	}
            }
        } while (cat.getBirthday()==null);
        
        Pattern wzorzecWagi = Pattern.compile("[0-9]+(\\.[0-9]+)?");
        String wagaWczytana;
        do {
            System.out.print("Podaj wagê kota: ");
            wagaWczytana = getUserInput();
            
            if (wzorzecWagi.matcher(wagaWczytana).matches()) {
                cat.setWeight(Float.valueOf(wagaWczytana));
            }
        } while (cat.getWeight() == null);

        System.out.print("Podaj kto jest opiekunem kota: ");
        cat.setGuardianName(getUserInput());

        catDao.addCat(cat);
        
        System.out.println("Dziêkujê, teraz wiem o kocie naprawdê wszystko! Doda³em go do naszego zbioru.");
	}

    public static String getUserInput() {
        return sc.nextLine().trim();
    }

}
