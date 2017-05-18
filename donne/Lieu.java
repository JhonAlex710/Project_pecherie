package donne;

import java.util.ArrayList;
/**
 * Liste des données en fonction des années
 * @author jeanpaul
 * */
public class Lieu {
	private ArrayList<Annee> anneeDeDonnes=new ArrayList<>();
	private final String nomDuLieu;
	private ReleveDateHeureEau marreHauteExceprion, marreBasseExceprion;
	
	private boolean MarreBasseEx = true, MarreHauteEx = true;
	public Lieu(String nomDuLieu) {
		// TODO Auto-generated constructor stub
		this.nomDuLieu=nomDuLieu;
	}
	/**
	 * Renvoie le nom du lieu
	 * */
	public String getNomDuLieu() {
		return nomDuLieu;
	}
	/**Renvoie l'ensemble de la liste d'annee*/
	public ArrayList<Annee> getAnneeDeDonnes() {
		return anneeDeDonnes;
	}
	/**Renvoie l'élément à la position X*/
	public Annee getAnneeDeDonnes(int postion) throws IndexOutOfBoundsException{
		return anneeDeDonnes.get(postion);
	}
	/**Renvoie la position de l'ément passé en paramètre*/
	public int getAnneeDeDonnes(Annee annee)  throws IndexOutOfBoundsException {
		int compteur=0;
		for (Annee annee2 : anneeDeDonnes) {
			if (annee.getNumeroAnne()==annee2.getNumeroAnne()) {
				break;
			} else {
				compteur=compteur+1;
			}
		}
		return compteur;
	}
	/**
	 * addMarreHauteExeceptionnel(ReleveDateHeureEau)
	 * */
	public ReleveDateHeureEau getMarreHauteExceprion() {
		return marreHauteExceprion;
	}
	/**
	 * Renvoie la marée basse exceptionnel
	 * Equivalent du set marreBasseExceprion
	 * */
	public ReleveDateHeureEau getMarreBasseExceprion() {
		return marreBasseExceprion;
	}
	/**
	 * Remplacement de la liste
	 * */
	public void setAnneeDeDonnes(ArrayList<Annee> moisDeDonnes) {
		anneeDeDonnes = moisDeDonnes;
	}
	/**
	 * Ajout d'un élement dans la liste
	 * */
	public void setAnneeDeDonnes(Annee moisDeDonnes) {
		anneeDeDonnes.add(moisDeDonnes);
	}
	/**
	 * Ajout d'une annee  à la liste
	 * */
	public void setAnneeDeDonnes(int position,Annee moisDeDonnes) throws IndexOutOfBoundsException{
		anneeDeDonnes.set(position,moisDeDonnes);
	}
	/**
	 * Permet l'ajout d'une seul marreHauteExceprion
	 * Ne peux 1 seul fois utilisé
	 * @param marreHauteExceprion 1 releve de la maréee
	 * */
	public void addMarreHauteExeceptionnel(ReleveDateHeureEau marreHauteExceprion) {
		// TODO Auto-generated method stub
		if (MarreHauteEx==true) {
			this.marreHauteExceprion=marreHauteExceprion;
			MarreHauteEx=false;
		}
	}
	/**
	 * Permet l'ajout d'une seul marreBasseExceprion
	 * Ne peux 1 seul fois utilisé
	 * @param marreBasseExceprion 1 releve de la maréee
	 * */
	public void addMarreBasseExeceptionnel(ReleveDateHeureEau marreBasseExceprion) {
		// TODO Auto-generated method stub
		if (MarreBasseEx==true) {
			this.marreBasseExceprion=marreBasseExceprion;
			MarreBasseEx=false;
		}
	}
	
}
