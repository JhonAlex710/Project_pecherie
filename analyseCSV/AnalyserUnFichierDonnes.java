package analyseCSV;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import donne.Annee;
import donne.Calendrier;
import donne.CommunAuxDonne;
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

	//private static final boolean BASSE = true;
	//private static final boolean HAUTE = false;
	private ArrayList<String[]> ensembleDeFichier;
	public AnalyserUnFichierDonnes() {
		// TODO Auto-generated constructor stub
		super();
		ensembleDeFichier = new ArrayList<>();
		String chemin=System.getProperty("user.home")+"/ter/Maree/";
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
				if (test==true) {
					ensembledeSortie.add(new ReleveDateHeureEau(hauteurDEau, nomLieu, temps));
				}
			}
		}
		return ensembledeSortie;
	}
	private Mois conversionMois(ArrayList<ReleveDateHeureEau> ensembledeSortie) {
		Mois mois = new Mois(ensembledeSortie.get(0).getDateDuReleve().getMois(),
				ensembledeSortie.get(0).getDateDuReleve().getAnnee(),
				ensembledeSortie.get(0).getNomDuLieu());
		//System.out.println(ensembledeSortie.get(0).getNomDuLieu());
		//Mois mois = new Mois(ensembledeSortie.get(0).getDateDuReleve().getMois());
		for (ReleveDateHeureEau releveDateHeureEau : ensembledeSortie) {
			if (mois.getListeDeDonne2().size()==0) {
				Jours jour = new Jours(releveDateHeureEau.getDateDuReleve().getJour());
				jour.setListeDeDonne(releveDateHeureEau);
				mois.setListeDeDonne(jour);
			} else {
				try {
					Jours position = (Jours) mois.getListeDeDonne(releveDateHeureEau.getDateDuReleve().getJour());
					int numeroJours = mois.getListeDeDonne(position);
					position.setListeDeDonne(releveDateHeureEau);
					mois.setListeDeDonne(numeroJours,position);
				} catch (IndexOutOfBoundsException e) {
					// TODO: handle exception
					Jours position=new Jours(releveDateHeureEau.getDateDuReleve().getJour());
					position.setListeDeDonne(releveDateHeureEau);
					mois.setListeDeDonne(position);
				}
			}
			
		}
		//mois.afficher();
		/*for (Arbre releveDateHeureEau : mois.getListeDeDonne()) {
			releveDateHeureEau.afficher();
		}*/
		return mois;
	}
	/**
	 * fusion des mois en années puis en Lieu
	 * @param resultat liste de mois à fusionner
	 * @return {@link ArrayList}{@link Lieu} liste des différents lieu avec mois, annee 
	 * et les différents relevées
	 * */
	private ArrayList<Lieu> transformation(ArrayList<Mois> entre) {
		//long debut = System.currentTimeMillis(),fin;
		ArrayList<Lieu> sorti = new ArrayList<>();
		ArrayList<Annee> tps = conversitionMoisAnnee(entre);
		for (Annee annee : tps) {
			int compteur1=0; boolean test=false;
			for (Lieu Lieu : sorti) {
				if (Lieu.getNom().contains(annee.getNomDuLieu())) {
					test=true;
					break;
				}else {
					compteur1= compteur1+1;
				}
			}
			Lieu lieu;
			if (test==true) {
				lieu = sorti.get(compteur1);
				lieu.setListeDeDonne(annee);
				sorti.set(compteur1, lieu);
			} else {
				lieu = new Lieu(annee.getNomDuLieu());
				lieu.setListeDeDonne(annee);
				sorti.add(lieu);
			}
		}
		return sorti;
	}
	private ArrayList<Annee> conversitionMoisAnnee(ArrayList<Mois> entre) {
		// TODO Auto-generated method stub
		ArrayList<Annee> sorti = new ArrayList<>();
		for (Mois mois : entre) {
			int compteur1=0; boolean test=false;
			for (Annee annee : sorti) {
				if (annee.getNumero()==mois.getAnnee() && 
						annee.getNomDuLieu().contains(mois.getNomDuLieu())) {
					test=true;
					break;
				}else {
					compteur1= compteur1+1;
				}
			}
			Annee annee;
			if (test==true) {
				annee = sorti.get(compteur1);
				annee.setListeDeDonne(mois);
				sorti.set(compteur1, annee);
			} else {
				annee = new Annee(mois.getAnnee(), mois.getNomDuLieu());
				annee.setListeDeDonne(mois);
				sorti.add(annee);
			}
		}
		return sorti;
	}
	/**
	 * Convertits les fichiers CSV en object. Ce ne sont des hauteurs d'eau, en des lieu. 
	 * @return 
	 * @return {@link ArrayList}{@link ReleveDateHeureEau} liste du contenu des fichiers
	 * */
	public ArrayList<Lieu> importerReleveDateHeureEauLieu() {
		ArrayList<Mois> resultat = new ArrayList<>();
		ArrayList<Lieu> sorti = new ArrayList<>();
		for (String[] strings : ensembleDeFichier) {
			resultat.add((Mois)mareHauteBasse(conversionMois(conversion(strings[1]))));
		}
		for (Lieu lieu : transformation(resultat)) {
			sorti.add((Lieu) mareHauteBasse(lieu));
		}
		return sorti;
	}
	private CommunAuxDonne mareHauteBasse(CommunAuxDonne entree) {
		// TODO Auto-generated method stub
		CommunAuxDonne sorti=entree;
		if (entree.getClass()==Jours.class) {
			Jours entree2 = (Jours) entree;
			sorti= new Jours(entree.getNumero());
			sorti = traitement2((Jours)sorti, entree2);
		} else if (entree.getClass()==Mois.class) {
			Mois entree2 = (Mois) entree;
			int compteur = 0;
			for (Jours jours : entree2.getListeDeDonne()) {
				entree2.setListeDeDonne(compteur, (Jours) mareHauteBasse(jours));
				compteur=compteur+1;
			}
			sorti= new Mois(entree2.getNumero(), entree2.getAnnee(), entree2.getNomDuLieu());
			sorti = traitement(sorti, entree2);
		} else if (entree.getClass()==Annee.class) {
			Annee entree2 = (Annee) entree;
			sorti = new Annee(entree2.getNumero());
			sorti = traitement(sorti, entree2);
		} else if (entree.getClass()==Lieu .class) {
			Lieu entree2 = (Lieu) entree;
			int compteur = 0;
			for (Annee annee : entree2.getListeDeDonne()) {
				entree2.setListeDeDonne(compteur, (Annee) mareHauteBasse(annee)); 
				compteur=compteur+1;
			}
			sorti = new Lieu(entree2.getNom());
			sorti = traitement(sorti, entree2);
		} 
		return sorti;
	}
	private Jours traitement2(Jours sorti, Jours entree2) {
		// TODO Auto-generated method stub
		ReleveDateHeureEau marreBasseExceprion=entree2.getListeDeDonne(0),
				marreHauteExceprion=entree2.getListeDeDonne(0);
		for (ReleveDateHeureEau releve : entree2.getListeDeDonne()) {
			if (releve.getHauteurEau()>marreHauteExceprion.getHauteurEau()) {
				marreHauteExceprion=releve;
			}
			if (releve.getHauteurEau()<marreBasseExceprion.getHauteurEau()) {
				marreBasseExceprion=releve;
			}
		}
		sorti.setListeDeDonne(entree2.getListeDeDonne());
		sorti.addMarreeBasse(marreBasseExceprion);
		sorti.addMarreeHaute(marreHauteExceprion);
		return sorti;
	}
	private CommunAuxDonne traitement(CommunAuxDonne sorti,CommunAuxDonne entree) {
		ReleveDateHeureEau marrebasse = entree.getListeDeDonne2().get(0).getMarreBasseExceprion(),
				marrehaute = entree.getListeDeDonne2().get(0).getMarreHauteExceprion();
		for (CommunAuxDonne strings : entree.getListeDeDonne2()) {
			if (marrebasse.getHauteurEau()>strings.getMarreBasseExceprion().getHauteurEau()) {
				marrebasse=strings.getMarreBasseExceprion();
			}
			if (marrehaute.getHauteurEau()>strings.getMarreHauteExceprion().getHauteurEau()) {
				marrehaute=strings.getMarreHauteExceprion();
			}
		}
		sorti.setListeDeDonne2(entree.getListeDeDonne2());
		sorti.addMarreeBasse(marrebasse);
		sorti.addMarreeHaute(marrehaute);
		return sorti;
	}
	/**
	 * 
	 * */
/*	public void calculDesMarresMoyennes(ArrayList<Lieu> entree) {
		// TODO Auto-generated method stub
		for (Lieu lieu : entree) {
			marreeBasse = calculmare(lieu,lieu.getHauteurEauMoyenne(),BASSE);
			marrehaute = calculmare(lieu,lieu.getHauteurEauMoyenne(),HAUTE);
		}
		return ;
	}
	private ArrayList<ReleveDateHeureEau> calculmare(CommunAuDonne entre, double moyenne,boolean test) {
		// TODO Auto-generated method stub
		ArrayList<ReleveDateHeureEau> listemaree = new ArrayList<>();
		for (Object integer2 : entre.getListeDeDonne()) {
			CommunAuxDonne integer = (CommunAuxDonne)integer2;
			if (entre.getClass()==Jours.class) {
				Jours tps = (Jours) entre;
				for (Object releve2 : tps.getListeDeDonne()) {
					ReleveDateHeureEau releve = (ReleveDateHeureEau)releve2;
					if ( (releve.getHauteurEau()<moyenne && test==BASSE) || (releve.getHauteurEau()>moyenne && test==HAUTE)){
						listemaree.add(releve);
					} 
				}
			} else {
				listemaree.addAll(calculmare(integer, integer.getHauteurEauMoyenne(), test));
			}
		}
		return listemaree;
	}
	public ArrayList<ReleveDateHeureEau> getMarreeBasse() {
		return marreeBasse;
	}
	public ArrayList<ReleveDateHeureEau> getMarrehaute() {
		return marrehaute;
	}*/
	
}
