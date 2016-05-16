package BusinessLogic;
import Models.Spectacol;

import java.util.List;

import DataLayer.SpectacolDAO;

public class SpectacolManager {
	
	private SpectacolDAO spect;
	
	public SpectacolManager(){
	  spect =new SpectacolDAO();
	}
	
	public void addSpectacol(Spectacol spec){
		spect.addSpectacol(spec);
	}
	public void updateSpectacol(Spectacol specBefore,Spectacol specAfter){
		spect.updateSpectacol(specBefore, specAfter);
	}
	public List<Spectacol> listaSpectacole(){
		return spect.getSpectacol();	
	}
	
	public void deleteSpectacol(Spectacol spec){
		spect.deleteSpectacol(spec);
	}
	public Spectacol getSpectacol(String specName){
		Spectacol spe = new Spectacol();
		for(Spectacol s:spect.getSpectacol()){
			if(s.getTitlul().equals(specName)){
				spe = s;
			}
		}
		return spe;
	}
	

}
