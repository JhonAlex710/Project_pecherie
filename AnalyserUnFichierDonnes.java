package analyseCSV;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import donne.Annee;
import donne.Calendrier;
import donne.Jours;
import donne.Lieu;
import donne.Mois;
import donne.ReleveDateHeureEau;
/**
 * Sert à analyser un ensemble de donnée de port breton
 * @author jeanpaul
 * Il reste des modification à faire
 * */
public class AnalyserUnFichierDonnes extends PartagerAnalyserPlusieursAnalyseCSV {

	private ArrayList<String[]> ensembleDeFichier;
	public AnalyserUnFichierDonnes() {
		// TODO Auto-generated constructor stub
		super();
		ensembleDeFichier = new ArrayList<>();
		String chemin="/home/jeanpaul/Bureau/ter/Maree/";
		listerFichier(new File(chemin));
	}
	/**
	 * Sert a Repertorier l'ensemble des dossiers et Fichiers
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
	/**
	 * Converti un fichier en un série d'object
	 * @param cheminEtNomFichier adresse du fichier
	 * @return {@link ArrayList}{@link ReleveDateHeureEau} ensemble de sorti sous forme de liste
	 * */
	private Mois conversion(String cheminEtNomFichier) {
		String nomLieu= new File(new File(cheminEtNomFichier).getParent()).getName();
		double hauteurDEau;
		String[] date={"0","0","0"},heure;
		boolean test = true;
		Calendrier temps;
		List<String[]> reultat = readCsvFile(cheminEtNomFichier, ';');
		ArrayList<ReleveDateHeureEau> ensembledeSortie= new ArrayList<>();
		for (String[] strings : reultat) {
			test = true;
			if (!strings[0].contains("Site")&& !strings[0].contains("horaire")&& !strings[0].contains("Jour")) {
				//System.out.println(strings[1]);
				date = strings[0].split("-");
				heure = strings[1].split(":");
				try {
					hauteurDEau = Double.parseDouble(strings[2]);
					temps = new Calendrier(Integer.parseInt(date[2]), 
							Integer.parseInt(date[1]), Integer.parseInt(date[0]),
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
				}catch (Exception e) {
					// TODO: handle exception
					System.err.println(cheminEtNomFichier);
					e.printStackTrace();
					hauteurDEau=Double.NaN;
					temps= new Calendrier();
					test = false;
				}
				if (test==true) { ensembledeSortie.add(new ReleveDateHeureEau(hauteurDEau, nomLieu, temps));}
			}
		}
		/*Transformation de Arralist de ReleveDateHeureEau en 1 mois*/
		Mois mois = new Mois(ensembledeSortie.get(0).getDateDuReleve().getMois(),
				ensembledeSortie.get(0).getDateDuReleve().getAnnee(),
				ensembledeSortie.get(0).getNomDuLieu());
		for (ReleveDateHeureEau releveDateHeureEau : ensembledeSortie) {
			if (mois.getListedeJours().size()==0) {
				mois.setListedeJours(new Jours(releveDateHeureEau.getDateDuReleve()
						.getJour()));
				mois.getListedeJours(0).setListedeDonnes(releveDateHeureEau);
			} else {
				try {
					Jours position = mois.getListedeJours(releveDateHeureEau.getDateDuReleve().getJour());
					int numeroJours = mois.getListedeJours(position);
					position.setListedeDonnes(releveDateHeureEau);
					mois.setListedeJours(numeroJours,position);
				} catch (IndexOutOfBoundsException e) {
					// TODO: handle exception
					Jours position=new Jours(releveDateHeureEau.getDateDuReleve().getJour());
					position.setListedeDonnes(releveDateHeureEau);
					mois.setListedeJours(position);
				}
			}
			
		}
		return mois;
		//return null;
	}
	/**
	 * fusion des mois en années puis en Lieu
	 * */
	private ArrayList<Lieu> transformation(ArrayList<Mois> resultat) {
		ArrayList<Lieu> listedeLieu = new ArrayList<>();
		for (Mois mois : resultat) {
			boolean testAnnne=false,testLieu=false;
			int positionLieu=0, positionAnnee=0;
			for (Lieu lieu : listedeLieu) {
				if (lieu.getNomDuLieu().contains(mois.getLieu())) {
					testLieu=true;
					for (Annee annee : lieu.getAnneeDeDonnes()) {
						if (mois.getNumeroMoisAnnee()==annee.getNumeroAnne()) {
							testAnnne=true;
							break;
						} else {
							positionAnnee=positionAnnee+1;
						}
					}
					break;
				} else {
					positionLieu=positionLieu+1;
				}
			}
			if (testLieu) {
				if (testAnnne) {
					listedeLieu.get(positionLieu).getAnneeDeDonnes(positionAnnee).setListeDeMois(mois);
				} else {
					Annee annee = new Annee(mois.getNumeroMoisAnnee());
					annee.setListeDeMois(mois);
					listedeLieu.get(positionLieu).setAnneeDeDonnes(annee);
				}
			} else {
				Lieu lieu = new Lieu(mois.getLieu());
				lieu.setAnneeDeDonnes(new Annee(mois.getNumeroMois()));
				lieu.getAnneeDeDonnes(0).setListeDeMois(mois);
				listedeLieu.add(lieu);
			}
		}
		return listedeLieu;
	}
	/**
	 * Convertits les fichiers CSV en object. Ce ne sont des hauteurs d'eau, en des lieu. 
	 * @return 
	 * @return {@link ArrayList}{@link ReleveDateHeureEau} liste du contenu des fichiers
	 * */
	//private ArrayList<Lieu> importerReleveDateHeureEauLieu() {
	public ArrayList<Lieu> importerReleveDateHeureEauLieu() {
		ArrayList<Mois> resultat = new ArrayList<>();
		for (String[] strings : ensembleDeFichier) {
			//System.out.println(strings[1]);
			resultat.add(conversion(strings[1]/*+strings[0]*/));
		}
		return transformation(resultat);
	}
}
