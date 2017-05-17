package donne;

/**
 * Sert à afficher un système de données lié aux marée
 * @author jeanpaul
 * */
public class ReleveDateHeureEau {
	private final double HauteurEau;
	private final String nomDuLieu;
	private final Calendrier dateDuReleve;
	
	public ReleveDateHeureEau(double HauteurEau, String nomDuLieu,Calendrier dateDuReleve) {
		// TODO Auto-generated constructor stub
		this.HauteurEau=HauteurEau;
		this.nomDuLieu=nomDuLieu;
		this.dateDuReleve = dateDuReleve;
	}
	/**
	 * Affiche à l'écrant tous ce qui est contenu dans l'object
	 * */
	public void afficher() {
		System.out.println("nomDuLieu "+nomDuLieu+" HauteurEau "+HauteurEau);
		dateDuReleve.afficher();
	}

	
	
	/*Getter*/
	public double getHauteurEau() {
		return HauteurEau;
	}
	public String getNomDuLieu() {
		return nomDuLieu;
	}
	public Calendrier getDateDuReleve() {
		return dateDuReleve;
	}
	
	
}
