package DataLayer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.Spectacol;


public class SpectacolDAO {
	
	private databaseDAO bazaDate;
	
	public SpectacolDAO(){
		bazaDate = new databaseDAO();
	}
	
	public List<Spectacol> getSpectacol(){
		
		List<Spectacol> listaEv = new ArrayList<Spectacol>();
		Connection c = bazaDate.getConnection();
		
		String sqlSt = "SELECT * FROM spectacol";
		Statement stmt;
		try {
			stmt = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rst;
			rst = stmt.executeQuery(sqlSt);
			while(rst.next())
			{
				Spectacol spect = new Spectacol();
				
				spect.setTitlul(rst.getString("titlul"));
				spect.setDistributia(rst.getString("distributia"));
				spect.setNumarBilete(rst.getInt("numarBilete"));
				spect.setRegia(rst.getString("regia"));
				spect.setDataPremierei(rst.getDate("datapremierei"));
				listaEv.add(spect);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
		
		return listaEv;
		
	}

	public void addSpectacol(Spectacol spec) {
		// TODO Auto-generated method stub
		
		Connection c = bazaDate.getConnection();
		
		
		String sqlSt="INSERT INTO spectacol(titlul,distributia,regia,datapremierei,numarBilete) VALUES ('"+spec.getTitlul()+"','"+spec.getDistributia()+"','"+spec.getRegia()+"','"+spec.getDataPremierei()+"','"+spec.getNumarBilete()+"');";
		Statement stmt;
		try {
			stmt = c.createStatement();
		     stmt.executeUpdate(sqlSt);
	
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	
	public void updateSpectacol(Spectacol specBefore,Spectacol specAfter) {
		// TODO Auto-generated method stub
		
		Connection c = bazaDate.getConnection();
		
		
		String sqlSt="UPDATE spectacol SET distributia='"+specAfter.getDistributia()+"', regia='"+specAfter.getRegia()+"', datapremierei='"+specAfter.getDataPremierei()+"', numarBilete='"+specAfter.getNumarBilete()+"' WHERE titlul='"+specBefore.getTitlul()+"';"; 
		Statement stmt;
		try {
			stmt = c.createStatement();
		     stmt.executeUpdate(sqlSt);
	
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	public void deleteSpectacol(Spectacol spec){
		
     Connection c = bazaDate.getConnection();
		
        String sqlSt1="DELETE FROM bilet WHERE titlul ='"+spec.getTitlul()+"';";
		String sqlSt="DELETE FROM spectacol WHERE titlul='"+spec.getTitlul()+"' AND distributia= '"+spec.getDistributia()+"' AND regia='"+spec.getRegia()+"' AND numarBilete='"+spec.getNumarBilete()+"';";
		Statement stmt;
		try {
			stmt = c.createStatement();
			stmt.executeUpdate(sqlSt1);
		    stmt.executeUpdate(sqlSt);
	
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	
	
	
	

}
