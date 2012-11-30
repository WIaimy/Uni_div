package aufgabe3;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Aufgabe3 {
	
	static List<ErgebnisObjekt> umsatz = new ArrayList<ErgebnisObjekt>();
	static List<ErgebnisObjekt> kosten = new ArrayList<ErgebnisObjekt>();
	static int counter=0;
	static FileWriter out = null;
	
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

	static void berechnung(double p, double d, double start, int durchläufe, List<ErgebnisObjekt> liste) {
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
					ergebnis += richtung*d;
				}else{
					ergebnis -= richtung*d;
				}
			}
			System.out.println("Ergebnis:\t" + ergebnis);
			ErgebnisObjekt myObject = new ErgebnisObjekt(ergebnis);
			pruefeObEnthalten(myObject,liste);
		}
		
		try {
			out = new FileWriter("werte"+(counter++)+".csv");
			for(ErgebnisObjekt ob : liste){
				out.append(ob.wert+" ");
				out.append(ob.haeufigkeit+" ");
				out.append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	static void pruefeObEnthalten(ErgebnisObjekt ob, List<ErgebnisObjekt> liste){
		for (ErgebnisObjekt ob2: liste){								//Liste iterieren
			if(ob.wert == ob2.wert){									//Wenn enthalten, häufigkeit hochzählen
				ob2.haeufigkeit++;
				return;
			}
		}
		liste.add(ob);													//Wenn nicht, Objekt hinzufügen
		return;
	}
}
