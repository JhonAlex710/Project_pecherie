package donne;
/**
 * Position d'une station de barrage
 * */
public class NiveauMarin {
	
	private final String nom;
	private final long ageMax,ageMin,ageMedian;
	private final double positionNivauMarinMax,positionNivauMarinMin,positionNivauMarinMedian;
	
	public NiveauMarin(String nom,long ageMax,long ageMin,long ageMedian,double positionNivauMarinMax,
			double positionNivauMarinMin,double positionNivauMarinMedian) {
		// TODO Auto-generated constructor stub
		this.nom=nom;
		this.ageMax=ageMax;
		this.ageMin= ageMin;
		this.ageMedian=ageMedian;
		this.positionNivauMarinMax=positionNivauMarinMax;
		this.positionNivauMarinMin=positionNivauMarinMin;
		this.positionNivauMarinMedian=positionNivauMarinMedian;
	}
	
	public void afficher() {
		System.out.println(" nom "+nom);
		System.out.println("Position du niveau marin max "+ positionNivauMarinMax+
				" Min "+positionNivauMarinMin+" median "+positionNivauMarinMedian);
		System.out.println("Age max "+ ageMax+
				" Min "+ageMin+" median "+ageMedian);
	}
}
