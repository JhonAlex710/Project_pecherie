package test;

import java.util.ArrayList;

import analyseCSV.AnalyserUnFichierDonnes;
import analyseCSV.ParserFichierNiveauMarin;
import donne.NiveauMarin;

public class Test {

	private ArrayList<NiveauMarin> resultat;
	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test name = new Test();
		name.testImportBarage();
		name.testImportReleveveDateHeure();
	}
	public void testImportBarage() {
		resultat=new ParserFichierNiveauMarin().barrage();
		System.out.println("Test.testImportBarage resultat.size() "+resultat.size());
	}
	public void testImportReleveveDateHeure(){
		new AnalyserUnFichierDonnes().importerReleveDateHeureEauLieu();
	}
}
