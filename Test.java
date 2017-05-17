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
		//name.testCalculMaree();
	}
	/**
	 * Test De l'importation du fichier générale
	 * */
	public void testImportBarage() {
		resultat=new ParserFichierNiveauMarin().barrage();
		System.out.println("Test.testImportBarage resultat.size() "+resultat.size());
	}
	/**
	 * Test de l'importation du contenu de marée
	 * */
	public void testImportReleveveDateHeure(){
		resiltat2=new AnalyserUnFichierDonnes().importerReleveDateHeureEauLieu();
		System.out.println("Test.testImportReleveveDateHeure resultat2.size() "+resiltat2.size());
	}
}
