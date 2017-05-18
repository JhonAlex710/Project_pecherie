package donne;

public class Pecherie {
	private double  surfaceDeau;
	public Pecherie() {
		// TODO Auto-generated constructor stub
	}
	public class calcul{
		public calcul() {
			// TODO Auto-generated constructor stub
		}
		public final static double GRAVITE=9.81;//metre par seconde -2
		/**
		 * Renvoie le pods de l'eau sur une suface donnee
		 * @param profondeur hauteur de la colonne d'eau
		 * @param masseVolumique nombre de kilogramme par metre cube
		 * @return {@link Double} kilogrammme
		 * */
		public double poidsEau(double profondeur,double masseVolumique) {
			double poidsDeEau=masseVolumique*GRAVITE*(surfaceDeau*profondeur);
			return poidsDeEau;
		}
		/**
		 * Renvoie le resultat de P = F/S
		 * @param pressionAirSurface
		 * @param poidsEau est calculer par la fonction poidsEau
		 * @return {@link Double}
		 * */
		public double pressionHydrotatique(double pressionAirSurface, double poidsEau) {
			double ForceSoumiseMembrane=pressionAirSurface*surfaceDeau+poidsEau;
			return ForceSoumiseMembrane;
		}
	}
	
}
/*
 *Source : 
 * https://fr.wikipedia.org/wiki/Hydrostatique
 * */
