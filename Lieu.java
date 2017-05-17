package donne;

import java.util.ArrayList;
/**
 * Liste des données en fonction des années
 * @author jeanpaul
 * */
public class Lieu {
	private ArrayList<Annee> anneeDeDonnes=new ArrayList<>();
	private final String nomDuLieu;
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
	public Annee getAnneeDeDonnes(int postion) {
		return anneeDeDonnes.get(postion);
	}
	/**Renvoie la position de l'ément passé en paramètre*/
	public int getAnneeDeDonnes(Annee annee) {
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
	 * Remplacement de la liste
	 * */
	public void setAnneeDeDonnes(ArrayList<Annee> moisDeDonnes) {
		anneeDeDonnes = moisDeDonnes;
	}
	/**
	 * Ajout d'une annee  à la liste
	 * */
	public void setAnneeDeDonnes(int position,Annee moisDeDonnes) {
		anneeDeDonnes.set(position,moisDeDonnes);
	}
	
}
