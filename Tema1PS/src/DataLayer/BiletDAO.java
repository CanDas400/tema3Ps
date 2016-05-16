package DataLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Models.Bilet;

public class BiletDAO {
	
   private databaseDAO bazaDate;
   
   public BiletDAO(){
	   
	   bazaDate = new databaseDAO();
   }
	
	public List<Bilet> getBilet(){
		
		List<Bilet> listaEv = new ArrayList<Bilet>();
		Connection c = bazaDate.getConnection();
		
		String sqlSt = "SELECT * FROM bilet";
		Statement stmt;
		try {
			stmt = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rst;
			rst = stmt.executeQuery(sqlSt);
			while(rst.next())
			{
				Bilet bil = new Bilet();
				
				bil.setNumar(rst.getInt("numar"));
				bil.setRand(rst.getInt("rand"));
				bil.setNumeSpectacol(rst.getString("titlul"));
				
					
				listaEv.add(bil);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
		
		return listaEv;
		
	}
	
	public List<Bilet> getListaBilete(String specName){
		List<Bilet> listaEv = getBilet();
		List<Bilet> listaBil = new ArrayList<Bilet>();
		for(Bilet b: listaEv){
			if(b.getNumeSpectacol().equals(specName)){
				listaBil.add(b);
			}
		}
		
		return listaBil;
	}

	public boolean addBilet(int nrMaxim,Bilet bil) {
		// TODO Auto-generated method stub
		
		List<Bilet> listaEv = getBilet();
		boolean ok = false;
		
		for(Bilet b: listaEv){
			if(b.getNumeSpectacol().equals(bil.getNumeSpectacol())){
					if(b.getNumar()==bil.getNumar() && b.getRand()==bil.getRand()){
							ok = true;
					}
			}
			
		}
		int nrBilete=listaEv.size();
		
		if(nrBilete>nrMaxim){
			ok = true;
		}
		
		
		
		
		if(ok==false){
			
			Connection c = bazaDate.getConnection();
			int nr=0;
			Statement s;
			try {
				s = c.createStatement();
				ResultSet rezi= s.executeQuery("SELECT MAX(id) FROM bilet");
	            if(rezi.next()){
	            	nr=rezi.getInt("MAX(id)");
	            }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			String sqlSt="INSERT INTO bilet(titlul,rand,numar,id) VALUES ('"+bil.getNumeSpectacol()+"','"+bil.getRand()+"','"+bil.getNumar()+"','"+nr+1+"');";
			Statement stmt;
			try {
				stmt = c.createStatement();
			     stmt.executeUpdate(sqlSt);
		
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return true;
			
		}
		else return false;
	}
	

}
