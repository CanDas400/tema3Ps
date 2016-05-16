package BusinessLogic;
import java.util.ArrayList;
import java.util.List;

import DataLayer.BiletDAO;
import Models.Bilet;

public class BiletManager {
	
	private BiletDAO bil;
	
	public BiletManager(){
		bil = new BiletDAO();
	}
	public boolean addBilet(int nrMaxim,Bilet bil1){
		boolean reusit =bil.addBilet(nrMaxim,bil1);
		
		return reusit;
	}
	public List<Bilet> bileteSpectacol(String specName){
		List<Bilet> listaBil= new ArrayList<Bilet>();
		listaBil = bil.getListaBilete(specName);
		return listaBil;
	}

}
