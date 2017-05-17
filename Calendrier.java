package donne;
/**
 * Classe servant à enregistrer la date
 * @author jeanpaul
 * */
public class Calendrier {
	private final int jour,mois, annee,heure, minute,seconde;
	/**
	 * Toutes les valeurs sont initalisé à zéro
	 * jour=mois= annee=heure= minute=seconde=0;
	 * */
	public Calendrier() {
		// TODO Auto-generated constructor stub 
		jour=mois= annee=heure= minute=seconde=0;
	}
	/**
	 * Toutes les valeurs sont entières
	 * @param jour
	 * @param mois
	 * @param annee
	 * @param heure
	 * @param minute
	 * @param seconde
	 * */
	public Calendrier(int jour, int mois, int annee, int heure, int minute, int seconde) {
		// TODO Auto-generated constructor stub 
		this.jour=jour;
		this.mois=mois;
		this.annee=annee;
		this.heure=heure;
		this.minute=minute;
		this.seconde=seconde;
	}

	public void afficher() {
		// TODO Auto-generated method stub
		System.out.println("jour" + jour+ " mois "+mois+" annee "+annee+" heure "+heure+ " minute "+minute+" seconde " +seconde);
	}
	
	public int getJour() {
		return jour;
	}
	public int getMois() {
		return mois;
	}
	public int getAnnee() {
		return annee;
	}
	public int getHeure() {
		return heure;
	}
	public int getMinute() {
		return minute;
	}
	public int getSeconde() {
		return seconde;
	}
	
}
