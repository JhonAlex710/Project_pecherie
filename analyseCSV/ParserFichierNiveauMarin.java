package analyseCSV;

import java.util.ArrayList;
import java.util.List;

import donne.NiveauMarin;
/**
 * Converite le niveau générale du fonds des océans sous forme de fichiers en une série d'object
 * @author jeanpaul
 * */
public class ParserFichierNiveauMarin extends PartagerAnalyserPlusieursAnalyseCSV{
	public ParserFichierNiveauMarin() {
		// TODO Auto-generated constructor stub
		super();
	}
	/**
	 * On parse le fichier /home/jeanpaul/Bureau/ter/Donnees_Niveau Marin_BZH.csv
	 * @return {@link ArrayList}{@link NiveauMarin} renvoie le résultat du parse
	 * */
	public ArrayList<NiveauMarin> barrage(){
		String fileName=System.getProperty("user.home")+"/ter/Donnees_Niveau Marin_BZH.csv";
		List<String[]> reultat = readCsvFile(fileName, ';');
		ArrayList<NiveauMarin> listeMarin=new ArrayList<NiveauMarin>(reultat.size());
		for (String[] strings : reultat) {
			if ((strings[0].contains("14C lab. Code") || strings[1].contains("min"))==false) {
				final String nom=strings[0];
				long ageMax,ageMedian,ageMin;
				double positionNivauMarinMax,positionNivauMarinMin,positionNivauMarinMedian;
				boolean test=true;
				try { 
					ageMin	 =Integer.parseInt(strings[1]);
					ageMedian=Integer.parseInt(strings[2]);
					ageMax=Integer.parseInt(strings[3]);
				} catch (NumberFormatException e) {
					// TODO: handle exception
					System.err.println("erreur 1 : ParserFichierBarage.barrage");
					e.printStackTrace();
					test = false;
					ageMax=ageMin=ageMedian=0;
					positionNivauMarinMax=positionNivauMarinMin=positionNivauMarinMedian=0;
				}
				if (strings[6].contains(",")) {
					strings[6]=strings[6].replace(',', '.');
				}
				if (strings[5].contains(",")) {
					strings[5]=strings[5].replace(',', '.');
				}
				if (strings[4].contains(",")) {
					strings[4]=strings[4].replace(',', '.');
				}
				try {
					positionNivauMarinMin	=	Double.parseDouble(strings[4]);
					positionNivauMarinMedian=   Double.parseDouble(strings[5]);
					positionNivauMarinMax   =   Double.parseDouble(strings[6]);
				} catch (NumberFormatException e) {
					// TODO: handle exception
					System.err.println("erreur 2 : ParserFichierBarage.barrage");
					e.printStackTrace();
					test = false;
					ageMax=ageMin=ageMedian=0;
					positionNivauMarinMax=positionNivauMarinMin=positionNivauMarinMedian=0;
				} catch (NullPointerException e) {
					// TODO: handle exception
					System.err.println("erreur 3 : ParserFichierBarage.barrage");
					e.printStackTrace();
					test = false;
					ageMax=ageMin=ageMedian=0;
					positionNivauMarinMax=positionNivauMarinMin=positionNivauMarinMedian=0;
				}
				if ( test == true) {
					listeMarin.add(new NiveauMarin(nom, ageMax, ageMin, ageMedian, positionNivauMarinMax, 
							positionNivauMarinMin, positionNivauMarinMedian));
				}
				
			}
		}
		return listeMarin;
	}
	
	
}
