package test;

import java.util.ArrayList;

import csv.ParserFichierNiveauMarin;
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
	}
	public void testImportBarage() {
		resultat=new ParserFichierNiveauMarin().barrage();
		System.out.println(resultat.size());
	}
}
