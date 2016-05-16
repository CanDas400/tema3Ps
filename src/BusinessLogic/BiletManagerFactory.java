package BusinessLogic;
import java.util.ArrayList;
import java.util.List;

import DataLayer.BiletDAO;

import Models.Bilet;

public class BiletManagerFactory {
	
	private BiletDAO bil;
	
	public BiletManagerFactory(){
		bil = new BiletDAO();
	}
	public boolean addBilet(int nrMaxim,Bilet bil1){
		boolean reusit =bil.addBilet(nrMaxim,bil1);
		
		return reusit;
	}
	
	public Exporter getExporter(String exporterType){
	      if(exporterType == null){
	         return null;
	      }		
	      if(exporterType.equalsIgnoreCase("CSV")){
	         return new CsvExporter();
	         
	      } else if(exporterType.equalsIgnoreCase("JSON")){
	         return new JsonExporter();
	
	      }
	      
	      return null;
	   }
	
	
	
	public List<Bilet> bileteSpectacol(String specName){
		List<Bilet> listaBil= new ArrayList<Bilet>();
		listaBil = bil.getListaBilete(specName);
		return listaBil;
	}

}
