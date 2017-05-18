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
	private ArrayList<ReleveDateHeureEau> conversion(String cheminEtNomFichier) {
		//long debut = System.currentTimeMillis(),fin;
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
		//fin=System.currentTimeMillis()-debut;
		//System.out.println("Temps d'exécution 1 fichier " + fin);
		/*Transformation de Arralist de ReleveDateHeureEau en 1 mois*/
		//fin=System.currentTimeMillis()-debut;
		//System.out.println("Temps d'exécution 1 fichier et fusion dans un mois" + fin);
		return ensembledeSortie;
		//return null;
	}
	private Mois conversionMois(ArrayList<ReleveDateHeureEau> ensembledeSortie) {
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
	}
	/**
	 * fusion des mois en années puis en Lieu
	 * @param resultat liste de mois à fusionner
	 * @return {@link ArrayList}{@link Lieu} liste des différents lieu avec mois, annee 
	 * et les différents relevées
	 * */
	private ArrayList<Lieu> transformation(ArrayList<Mois> resultat) {
		//long debut = System.currentTimeMillis(),fin;
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
		//fin=System.currentTimeMillis()-debut;
		//System.out.println("A ssemblage dans lieu" + fin);
		return listedeLieu;
	}
	/**
	 * Convertits les fichiers CSV en object. Ce ne sont des hauteurs d'eau, en des lieu. 
	 * @return 
	 * @return {@link ArrayList}{@link ReleveDateHeureEau} liste du contenu des fichiers
	 * */
	public ArrayList<Lieu> importerReleveDateHeureEauLieu() {
		ArrayList<Mois> resultat = new ArrayList<>();
		for (String[] strings : ensembleDeFichier) {
			resultat.add(mareHauteBasse(conversionMois(conversion(strings[1]))));
		}
		ArrayList<Lieu> transition= new ArrayList<>();
		for (Lieu lieu : transformation(resultat)) {
			transition.add(mareHauteBasse(lieu));
		}
		return transition;
	}
	private Lieu mareHauteBasse(Lieu lieu) {
		// TODO Auto-generated method stub
		ReleveDateHeureEau marreBasseExceprion=null,marreHauteExceprion=null;
		Lieu sorti = new Lieu(lieu.getNomDuLieu()), transition = new Lieu(lieu.getNomDuLieu());
		for (Annee  annee: lieu.getAnneeDeDonnes()) {
			transition.setAnneeDeDonnes(mareHauteBasse(annee));
		}
		try {
			marreBasseExceprion = transition.getAnneeDeDonnes(0).getMarreBasseExceprion();
			marreHauteExceprion = transition.getAnneeDeDonnes(0).getMarreHauteExceprion();
		} catch (Exception e) {
			// TODO: handle exception
			sorti=transition;
		}
		if (!(marreBasseExceprion==null) && !(marreHauteExceprion==null) ) {
			for (Annee annee : transition.getAnneeDeDonnes()) {
				if (marreBasseExceprion.getHauteurEau()>annee.getMarreBasseExceprion().getHauteurEau()) {
					marreBasseExceprion=annee.getMarreBasseExceprion();
				}
				if (marreHauteExceprion.getHauteurEau()<annee.getMarreHauteExceprion().getHauteurEau()) {
					marreHauteExceprion=annee.getMarreHauteExceprion();
				}
			}
			sorti.addMarreBasseExeceptionnel(marreBasseExceprion);
			sorti.addMarreHauteExeceptionnel(marreHauteExceprion);
			sorti.setAnneeDeDonnes(lieu.getAnneeDeDonnes());
		}
		return sorti;
	}
	/**
	 * Calcul les marreHauteExceprion pour un mois de donnes
	 * idem pour  marreBasseExceprion
	 * @param transition
	 * @return {@link Annee} avec les champs marreBasseExceprion et marreHauteExceprion rempli
	 * */
	private Annee mareHauteBasse(Annee transition) {
		// TODO Auto-generated method stub
		Annee ensembleDeSortie = new Annee(transition.getNumeroAnne());
		ReleveDateHeureEau marreBasseExceprion=null,marreHauteExceprion=null;
		try {
			marreBasseExceprion = transition.getListeDeMois(0).getMarreBasseExceprion();
			marreHauteExceprion = transition.getListeDeMois(0).getMarreHauteExceprion();
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
			ensembleDeSortie=transition;
		}
		if (!(marreHauteExceprion==null) && !(marreBasseExceprion==null)) {
			for (Mois mois : transition.getListeDeMois()) {
				if (marreBasseExceprion.getHauteurEau()>mois.getMarreBasseExceprion().getHauteurEau()) {
					marreBasseExceprion=mois.getMarreBasseExceprion();
				} 
				if (marreHauteExceprion.getHauteurEau()<mois.getMarreHauteExceprion().getHauteurEau()) {
					marreHauteExceprion=mois.getMarreHauteExceprion();
				}
			}
			ensembleDeSortie.addMarreeHaute(marreHauteExceprion);
			ensembleDeSortie.addMarreeBasse(marreBasseExceprion);
			ensembleDeSortie.setListeDeMois(transition.getListeDeMois());
		}
		return ensembleDeSortie;
	}
	/**
	 * Calcul les marreHauteExceprion pour un mois 
	 * idem pour marreBasseExceprion
	 * @param entree un mois pour subir le traitement
	 * @return {@link Mois} renvoie le résultat avec le champs marreHauteExceprion et marreBasseExceprion rempli
	 * */
	private Mois mareHauteBasse(Mois entree) {
		// TODO Auto-generated method stub
		ReleveDateHeureEau marreBasseExceprion=null,marreHauteExceprion=null;
		Mois ensembleDeSortie = new Mois(entree.getNumeroMois(), 
				entree.getNumeroMoisAnnee(), entree.getLieu()),
				transition= new Mois(entree.getNumeroMois(), 
						entree.getNumeroMoisAnnee(), entree.getLieu());
		for (Jours jours : entree.getListedeJours()) {
			transition.setListedeJours(this.mareHauteBasse(jours));
		}
		try {
			marreBasseExceprion = transition.getListedeJours(0).getMarreBasseExceprion();
			marreHauteExceprion = transition.getListedeJours(0).getMarreHauteExceprion();
		} catch (Exception e) {
			// TODO: handle exception
			ensembleDeSortie=transition;
		}
		if (!(marreBasseExceprion==null) && !(marreHauteExceprion==null) ) {
			for (Jours jours : transition.getListedeJours()) {
				if (marreBasseExceprion.getHauteurEau()>jours.getMarreBasseExceprion().getHauteurEau()) {
					marreBasseExceprion=jours.getMarreBasseExceprion();
				}
				if (marreHauteExceprion.getHauteurEau()<jours.getMarreHauteExceprion().getHauteurEau()) {
					marreHauteExceprion=jours.getMarreHauteExceprion();
				}
			}
			ensembleDeSortie.addMarreBasseExeceptionnel(marreBasseExceprion);
			ensembleDeSortie.addMarreHauteExeceptionnel(marreHauteExceprion);
			ensembleDeSortie.setListedeJours(entree.getListedeJours());
		}
		return ensembleDeSortie;
	}
	/**
	 * Calcul les marreHauteExceprion pour un jour de donnes
	 * idem pour  marreBasseExceprion
	 * @param transition un jour quelquonque
	 * @return {@link Jours} renvoi ce jour avec les champs marreHauteExceprion et marreBasseExceprion rempli
	 * */
	private Jours mareHauteBasse(Jours transition){
		// TODO Auto-generated method stub
		Jours joursDesortie = new Jours(transition.getNumeroJours());
		ReleveDateHeureEau marreBasseExceprion=null,marreHauteExceprion=null;
		try {
			marreBasseExceprion = transition.getListedeDonnes(0);
			marreHauteExceprion = transition.getListedeDonnes(0);
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
			joursDesortie=transition;
		}
		if (!(marreHauteExceprion==null) && !(marreBasseExceprion==null)) {
			for (ReleveDateHeureEau releve : transition.getListedeDonnes()) {
				if (marreBasseExceprion.getHauteurEau()>releve.getHauteurEau()) {
					marreBasseExceprion=releve;
				} 
				if (marreHauteExceprion.getHauteurEau()<releve.getHauteurEau()) {
					marreHauteExceprion=releve;
				}
			}
			joursDesortie.addMarreeHaute(marreHauteExceprion);
			joursDesortie.addMarreeBasse(marreBasseExceprion);
			joursDesortie.setListedeDonnes(transition.getListedeDonnes());
		}
		return joursDesortie;
	}
	
}
