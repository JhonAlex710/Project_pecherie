package donne;

import java.util.ArrayList;
/**
 * Un ensemble de 24 relevés
 * @author jeanpaul
 * */
public final class Jours {
	private ArrayList<ReleveDateHeureEau> listedeDonnes=new ArrayList<>(31);
	private final int numeroJours;
	public Jours(int numeroJours) {
		// TODO Auto-generated constructor stub
		this.numeroJours=numeroJours;
	}
	/**Renvoie la collection complete*/
	public ArrayList<ReleveDateHeureEau> getListedeDonnes() {
		return listedeDonnes;
	}
	/** Renvoie un element à la position X*/
	public ReleveDateHeureEau getListedeDonnes(int position) throws IndexOutOfBoundsException{
		return listedeDonnes.get(position);
	}
	/** Renvoie la position Xde l'élément Y*/
	public int getListedeDonnes(ReleveDateHeureEau listedeDonnes)  throws IndexOutOfBoundsException {
		int compteur = 0;
		for (ReleveDateHeureEau releveDateHeureEau : this.listedeDonnes) {
			if (listedeDonnes.getHauteurEau()==releveDateHeureEau.getHauteurEau()) {
				break;
			}else {
				compteur=compteur+1;
			}
		}
		return compteur;
	}
	/**
	 * Renvoie le numero du jour
	 * */
	public int getNumeroJours() {
		return numeroJours;
	}
	
	/** change l'ensemble de la collection*/
	public void setListedeDonnes(ArrayList<ReleveDateHeureEau> listedeDonnes) {
		this.listedeDonnes = listedeDonnes;
	}
	/**Ajoute un élément*/
	public void setListedeDonnes(ReleveDateHeureEau listedeDonnes) {
		this.listedeDonnes.add(listedeDonnes);
	}
	/**Remplace un élément à la postion*/
	public void setListedeDonnes(ReleveDateHeureEau listedeDonnes, int position) throws IndexOutOfBoundsException{
		this.listedeDonnes.set(position, listedeDonnes);
	}
	
}
