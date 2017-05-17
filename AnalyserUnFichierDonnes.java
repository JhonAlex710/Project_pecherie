package analyseCSV;

import java.io.File;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import donne.Calendrier;
import donne.ReleveDateHeureEau;
import test.Test;

public class AnalyserUnFichierDonnes extends PartagerAnalyserPlusieursAnalyseCSV {

	private ArrayList<String[]> ensembleDeFichier;
	public AnalyserUnFichierDonnes() {
		// TODO Auto-generated constructor stub
		ensembleDeFichier = new ArrayList<>();
		String chemin="/home/jeanpaul/Bureau/ter/Maree/Brest/201501.CSV";
		listerFichier(new File(chemin));
	}
	

	/**
	 * SertA Repertorier l'ensemble des dossiers et Fichiers
	 * Elle est récursive dans les dossiers
	 * @param fichier servant àsavoir si c'est dossier ou pas
	 * */
	private void listerFichier(File fichier){
		if (fichier.isFile()) {
			String[] tableau = {fichier.getName(),fichier.getAbsolutePath()};
			ensembleDeFichier.add(tableau);
		} else {
			File[] listedefichiers = fichier.listFiles();
			for (File file : listedefichiers) {
				listerFichier(file);
			}
		}
	}
	
	private void conversion(String cheminEtNomFichier) {
		String nomLieu= new File(new File(cheminEtNomFichier).getParent()).getName();
		double hauteurDEau;
		String[] date,heure;
		boolean test = true;
		Calendrier temps;
		List<String[]> reultat = readCsvFile(cheminEtNomFichier, ';');
		for (String[] strings : reultat) {
			test = true;
			if (!strings[0].contains("Site")&& !strings[0].contains("horaire")&& !strings[0].contains("Jour")) {
				//System.out.println(strings[1]);
				date = strings[0].split("-");
				heure = strings[1].split(":");
				try {
					hauteurDEau = Double.parseDouble(strings[2]);
					temps = new Calendrier(Integer.parseInt(date[0]), 
							Integer.parseInt(date[1]), Integer.parseInt(date[2]), 
							Integer.parseInt(heure[0]), Integer.parseInt(heure[1]), 0);
				} catch (NullPointerException e) {
					// TODO: handle exception
					System.err.println("Erreur dans cas 1 : AnalyserUnFichierDonnes.conversion");
					//e.printStackTrace();
					hauteurDEau=Double.NaN;
					test = false;
					temps= new Calendrier();
				} catch (NumberFormatException e) {
					// TODO: handle exception
					System.err.println("Erreur dans cas 2 : AnalyserUnFichierDonnes.conversion");
					//e.printStackTrace();
					hauteurDEau=Double.NaN;
					temps= new Calendrier();
					test = false;
				}
				if (test==true) {
					new ReleveDateHeureEau(hauteurDEau, nomLieu, temps).afficher();
				}
				
			}
			/*GregorianCalendar dateDuReleve = 
					new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute);*/
		//	new ReleveDateHeureEau(HauteurEau, lieu, dateDuReleve);
		}
		
	}
	public void importerReleveDateHeureEauLieu() {
		for (String[] strings : ensembleDeFichier) {
			//System.out.println(strings[1]+strings[0]);
			conversion(strings[1]/*+strings[0]*/);
		}
	}
	/*public ArrayList<ReleveDateHeureEau> analyserUnFichierDonnesHauteurEau() {
		String fileName="/home/jeanpaul/Bureau/ter/Donnees_Niveau Marin_BZH.csv";
		List<String[]> reultat = readCsvFile(fileName, ';');
		ArrayList<NiveauMarin> listeMarin=new ArrayList<NiveauMarin>(reultat.size());
		return null;
	}*/
}
