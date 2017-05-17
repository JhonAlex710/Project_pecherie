package donne;

import java.util.ArrayList;
/**
 * Une liste de 12 mois
 * @author jeanpaul
 * */
public final class Annee {
	private ArrayList<Mois> listeDeMois=new ArrayList<>(12);
	private final int numeroAnne;
	public Annee(int numeroAnne) {
		// TODO Auto-generated constructor stub
		this.numeroAnne=numeroAnne;
	}
	/**
	 * Renvoie l'ensemble de la liste
	 * */
	public ArrayList<Mois> getListeDeMois() {
		return listeDeMois;
	}
	/**Renvoie le mois à la postion X*/
	public Mois getListeDeMois(int position) {
		return listeDeMois.get(position);
	}
	/**Renvoie la position du mois Y */
	public int getListeDeMois(Mois mois) {
		int compteur=0;
		for (Mois mois2 : listeDeMois) {
			if (mois.getNumeroMois()==mois2.getNumeroMois()) {
				break;
			} else {
				compteur=compteur+1;
			}
		}
		return compteur;
	}
	/**
	 * Renvoie le numéro de l'année
	 * */
	public int getNumeroAnne() {
		return numeroAnne;
	}
	/**
	 * Remplacement de liste intégrale du mois*/
	public void setListeDeMois(ArrayList<Mois> listeDeMois) {
		this.listeDeMois = listeDeMois;
	}
	/**Ajout d'un élément dans la liste*/
	public void setListeDeMois(Mois listeDeMois) {
		this.listeDeMois.add(listeDeMois);
	}
	/**Remplacement d'un élément dans la liste*/
	public void setListeDeMois(int position, Mois listeDeMois) {
		this.listeDeMois.set(position, listeDeMois);
	}
	
}
