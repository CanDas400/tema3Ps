package BusinessLogic;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


import javax.swing.JFileChooser;

import Models.Bilet;

public class CsvExporter implements Exporter{
	
	  JFileChooser chooser;
	  
	  public CsvExporter(){
		  
	  }

	@Override
	public void export(List<Bilet> listaBilete) {
		// TODO Auto-generated method stub
		
		try
		{
		
		    FileWriter writer = new FileWriter("BileteTeatru.csv");
			 
		    for(Bilet t: listaBilete){
		     writer.append(t.getNumeSpectacol());
		     writer.append(" ");
		     writer.append(String.valueOf(t.getNumar()));
		     writer.append(" ");
		     writer.append(String.valueOf(t.getRand()));
		     writer.append("\n");
		    }
				
		    //generate whatever data you want
				
		    writer.flush();
		    writer.close();
		}
		catch(IOException e)
		{
		     e.printStackTrace();
		} 
	    
		
		
	}

}
