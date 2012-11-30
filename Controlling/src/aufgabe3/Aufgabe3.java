package aufgabe3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Aufgabe3 {

	public class ergebnisObjekt{
		double wert;
		int haeufigkeit;
		public ergebnisObjekt(double start){
			wert = start;
		}
		public double getWert(){
			return this.wert;
		}
	}
	
	static List<ergebnisObjekt> umsatz = new ArrayList<ergebnisObjekt>();
	static List<ergebnisObjekt> kosten = new ArrayList<ergebnisObjekt>();
	
	public static void main(String[] para) {

		BufferedReader userIn = new BufferedReader(new InputStreamReader(
				System.in));
		double p, d;
		int startUmsatz = 100;
		int startKosten = 50;
		int amount = 0;
		
		try {
			System.out.println("Wahrscheinlichkeit:\t");
			p = Double.parseDouble(userIn.readLine());
			System.out.println("Sprunghöhe:\t");
			d = Double.parseDouble(userIn.readLine());
			System.out.println("Häufigkeit des Durchlaufs:\t");
			amount = Integer.parseInt(userIn.readLine());
			berechnung(p,d,startUmsatz,amount,umsatz);
			System.out.println("\n---------------------\n");
			berechnung(p,d,startKosten,amount,kosten);
			
		} catch (Exception e) {
			System.out.println("da ging was schief");
		}
	}

	static void berechnung(double p, double d, double start, int durchläufe, List<ergebnisObjekt> liste) {
		double richtung;
		
		for(int j = 0; j < durchläufe; j++){						//x Durchläufe
			double ergebnis = start;
			
			for(int i = 0; i < 10; i++){							//10 Jahre pro Durchlauf berechnen
				richtung = Math.random();							//Sprung hoch vs. Sprung runter
				if(richtung >= p)
					richtung = -1;
				else
					richtung = 1;
				
				if(richtung == 1){									//Addieren bzw. subtrahieren
					ergebnis += d;
				}else{
					ergebnis -= d;
				}
			}
			ergebnisObjekt myObject = new Aufgabe3().new ergebnisObjekt(ergebnis);
			pruefeObEnthalten(myObject,liste);
		}
	}
	
	static void pruefeObEnthalten(ergebnisObjekt ob, List<ergebnisObjekt> liste){
		for (ergebnisObjekt ob2: liste){								//Liste iterieren
			if(ob.getWert() == ob2.getWert()){							//Wenn enthalten, häufigkeit hochzählen
				ob2.haeufigkeit++;
				return;
			}
		}
		liste.add(ob);													//Wenn nicht, Objekt hinzufügen
		return;
	}
}
