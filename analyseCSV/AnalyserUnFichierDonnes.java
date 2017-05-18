package analyseCSV;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import donne.Annee;
import donne.Calendrier;
import donne.CommunAuDonne;
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

	private static final boolean BASSE = true;
	private static final boolean HAUTE = false;
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
		return ensembledeSortie;
	}
	private Mois conversionMois(ArrayList<ReleveDateHeureEau> ensembledeSortie) {
		Mois mois = new Mois(ensembledeSortie.get(0).getDateDuReleve().getMois(),
				ensembledeSortie.get(0).getDateDuReleve().getAnnee(),
				ensembledeSortie.get(0).getNomDuLieu());
		for (ReleveDateHeureEau releveDateHeureEau : ensembledeSortie) {
			if (mois.getListeDeDonne().size()==0) {
				Jours jour = new Jours(releveDateHeureEau.getDateDuReleve().getJour());
				jour.setListeDeDonne2(releveDateHeureEau);
				//mois.getListeDeDonne(0).setListeDeDonne(releveDateHeureEau);
				mois.setListeDeDonne(jour);
			} else {
				try {
					Jours position = (Jours) mois.getListeDeDonne(releveDateHeureEau.getDateDuReleve().getJour());
					int numeroJours = mois.getListeDeDonne(position);
					position.setListeDeDonne2(releveDateHeureEau);
					mois.setListeDeDonne(numeroJours,position);
				} catch (IndexOutOfBoundsException e) {
					// TODO: handle exception
					Jours position=new Jours(releveDateHeureEau.getDateDuReleve().getJour());
					position.setListeDeDonne2(releveDateHeureEau);
					mois.setListeDeDonne(position);
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
				if (lieu.getNom().contains(mois.getNomDuLieu())) {
					testLieu=true;
					for (CommunAuDonne annee : lieu.getListeDeDonne()) {
						if (mois.getAnnee()==annee.getNumero()) {
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
					listedeLieu.get(positionLieu).getListeDeDonne(positionAnnee).setListeDeDonne(mois);
				} else {
					Annee annee = new Annee(mois.getAnnee());
					annee.setListeDeDonne(mois);
					listedeLieu.get(positionLieu).setListeDeDonne(annee);
				}
			} else {
				Lieu lieu = new Lieu(mois.getNomDuLieu());
				lieu.setListeDeDonne(new Annee(mois.getNumero()));
				lieu.getListeDeDonne(0).setListeDeDonne(mois);
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
			resultat.add((Mois)mareHauteBasse(conversionMois(conversion(strings[1]))));
		}
		ArrayList<Lieu> transition= new ArrayList<>();
		for (Lieu lieu : transformation(resultat)) {
			transition.add((Lieu)mareHauteBasse(lieu));
		}
		return transition;
	}
	private CommunAuDonne mareHauteBasse(CommunAuDonne entree) {
		// TODO Auto-generated method stub
		ReleveDateHeureEau marreBasseExceprion=null,marreHauteExceprion=null;
		CommunAuDonne sorti;
		if (entree.getClass()==Lieu.class || entree.getClass()==Mois.class) {
			CommunAuDonne transition,lieu;
			if (entree.getClass()==Lieu.class) {
				lieu =(Lieu)entree;Lieu test=(Lieu)entree;
				sorti= new Lieu(test.getNom());
				transition = new Lieu(test.getNom());
				for (CommunAuDonne  annee: lieu.getListeDeDonne()) {
					transition.setListeDeDonne(mareHauteBasse((Annee)annee));
				}
			} else {
				lieu =(Mois)entree;Mois test=(Mois)entree;
				sorti = new Mois(test.getNumero(), lieu.getNumero(), test.getNomDuLieu());
				transition= new Mois(test.getNumero(), test.getAnnee(), test.getNomDuLieu());
				for (CommunAuDonne  annee: lieu.getListeDeDonne()) {
					transition.setListeDeDonne(mareHauteBasse((Jours)annee));
				}
			}
			entree=transition;
			
		} else if (entree.getClass()==Annee.class) {
			sorti = new Annee(entree.getNumero());
		} else {
			sorti = new Jours(entree.getNumero());
		}
		try {
			marreBasseExceprion = entree.getListeDeDonne(0).getMarreBasseExceprion();
			marreHauteExceprion = entree.getListeDeDonne(0).getMarreHauteExceprion();
		} catch (Exception e) {
			// TODO: handle exception
			sorti=entree;
		}
		if (!(marreBasseExceprion==null) && !(marreHauteExceprion==null) ) {
			int compteur=0;double somme=0;
			if (entree.getClass()==Jours.class) {
				Jours tps = (Jours) entree;
				for (ReleveDateHeureEau releve : tps.getListeDeDonne2()) {
					if (marreBasseExceprion.getHauteurEau()>releve.getHauteurEau()) {
						marreBasseExceprion=releve;
					}
					if (marreHauteExceprion.getHauteurEau()<releve.getHauteurEau()) {
						marreHauteExceprion=releve;
					}
					somme=somme+releve.getHauteurEau();
					compteur=compteur+1;
				}
			} else {
				for (CommunAuDonne annee : entree.getListeDeDonne()) {
					if (marreBasseExceprion.getHauteurEau()>annee.getMarreBasseExceprion().getHauteurEau()) {
						marreBasseExceprion=annee.getMarreBasseExceprion();
					}
					if (marreHauteExceprion.getHauteurEau()<annee.getMarreHauteExceprion().getHauteurEau()) {
						marreHauteExceprion=annee.getMarreHauteExceprion();
					}
					somme=somme+annee.getHauteurEauMoyenne();
					compteur=compteur+1;
				}
			}
			sorti.setHauteurEauMoyenne(somme/compteur);
			sorti.addMarreeBasse(marreBasseExceprion);
			sorti.addMarreeHaute(marreHauteExceprion);
			sorti.setListeDeDonne(entree.getListeDeDonne());
		}
		return sorti;
	}
	/**
	 * 
	 * */
	public ArrayList<Lieu> calculDesMarresMoyennes(ArrayList<Lieu> entree) {
		// TODO Auto-generated method stub
		ArrayList<Lieu> resultat = new ArrayList<>();
		for (Lieu lieu : entree) {
			lieu=calculmare(lieu,BASSE);
			lieu=calculmare(lieu,HAUTE);
		}
		return resultat;
	}
	private Lieu calculmare(CommunAuDonne entre, boolean test) {
		// TODO Auto-generated method stub
		/*if () {
			
		}*/
		
		return null;
	}
	
}
