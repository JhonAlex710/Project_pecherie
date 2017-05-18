package test;

import java.util.ArrayList;

import analyseCSV.AnalyserUnFichierDonnes;
import analyseCSV.ParserFichierNiveauMarin;
import donne.Lieu;
import donne.NiveauMarin;
/**
 * @author jeanpaul
 * Classe de teste
 * */
public class Test {

	private ArrayList<NiveauMarin> resultat;
	private ArrayList<Lieu> resiltat2;
	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test name = new Test();
		name.testImportBarage();
		name.testImportReleveveDateHeure();
		name.testCalculDeMaree();
	}
	/**
	 * Test De l'importation du fichier générale
	 * */
	public void testImportBarage() {
		long debut = System.currentTimeMillis();
		resultat=new ParserFichierNiveauMarin().barrage();
		long tempsExecuter = System.currentTimeMillis()-debut;
		System.out.println("Test.testImportBarage resultat.size() "+resultat.size());
		System.out.println("Test.testImportBarage Temps exécution "+tempsExecuter+ " millisecondes");
	}
	/**
	 * Test de l'importation du contenu de marée
	 * */
	public void testImportReleveveDateHeure(){
		long debut = System.currentTimeMillis();
		resiltat2=new AnalyserUnFichierDonnes().importerReleveDateHeureEauLieu();
		long tempsExecuter = System.currentTimeMillis()-debut;
		System.out.println("Test.testImportReleveveDateHeure resultat2.size() "+resiltat2.size());
		System.out.println("Test.testImportReleveveDateHeure Temps exécution "+tempsExecuter+ " millisecondes");
	}
	/**
	 * Test de des calculs de moyenne de marre
	 * */
	public void testCalculDeMaree(){
		long debut = System.currentTimeMillis();
		resiltat2 = new AnalyserUnFichierDonnes().calculDesMarresMoyennes(resiltat2);
		long tempsExecuter = System.currentTimeMillis()-debut;
		System.out.println("Test.testCalculDeMaree resultat2.size() "+resiltat2.size());
		System.out.println("Test.testCalculDeMaree Temps exécution "+tempsExecuter+ " millisecondes");
	}
}
