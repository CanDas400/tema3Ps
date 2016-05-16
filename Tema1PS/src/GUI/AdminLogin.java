package GUI;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import BusinessLogic.SpectacolManager;
import BusinessLogic.UserManager;
import Models.Spectacol;
import Models.User;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class AdminLogin {

	public JFrame frame;
	private JLabel background;
	private DefaultTableModel modelWareHouse;
	private JTable tableWarehouse;
	private JScrollPane scrollPane;
	private SpectacolManager sMan;
	private UserManager uMan;

	private JButton btnEditareSpectacol;
	private JButton btnStergSpectacol;

	/**
	 * Create the application.
	 */
	public AdminLogin() {
		initialize();
		sMan = new SpectacolManager();
		uMan = new UserManager();
		refreshTable();
	 
	}
	
	private void refreshTable() {
		// TODO Auto-generated method stub
		modelWareHouse.setRowCount(0);
		
		for (Spectacol c: sMan.listaSpectacole()) {
			modelWareHouse.addRow(new Object[] {c.getTitlul(),c.getRegia(),c.getDistributia(),c.getDataPremierei(),c.getNumarBilete()});
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 517);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JButton btnAngajatNou = new JButton("Angajat Nou");
		btnAngajatNou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				JTextField nume = new JTextField();
				JTextField username = new JTextField();
				JTextField parola = new JTextField();

				
				Object[] message = {
					    "Nume:", nume,
					    "Username:", username,
					    "Password:", parola
					  };
					int option = JOptionPane.showConfirmDialog(null, message, "Date", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						User usr = new User();
						usr.setNume(nume.getText());
						usr.setPassword(parola.getText());
						usr.setUsername(username.getText());
						
						uMan.createUserAccount(usr);
						
					}
				
				
				
			}
		});
		btnAngajatNou.setBounds(231, 382, 137, 32);
		frame.getContentPane().add(btnAngajatNou);
		
		JButton btnSpectacolNou = new JButton("Spectacol nou");
		btnSpectacolNou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JTextField titlul = new JTextField();
				JTextField regia = new JTextField();
				JTextField distributia = new JTextField();
				JTextField data = new JTextField();
				JTextField numarbilete = new JTextField();
				numarbilete.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						metoda(e);
					}
				});
				
				Object[] message = {
					    "Titlul:", titlul,
					    "Regia:", regia,
					    "Distributia:", distributia,
					    "Data", data,
					    "Numar Bilete", numarbilete
					  };
					int option = JOptionPane.showConfirmDialog(null, message, "Date", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						
						Spectacol spec = new Spectacol();
						spec.setTitlul(titlul.getText());
						spec.setRegia(regia.getText());
						spec.setDistributia(regia.getText());
						
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				        Date parsed;
						try {
							parsed = format.parse(data.getText());
							java.sql.Date sql = new java.sql.Date(parsed.getTime());
							spec.setDataPremierei(sql);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Data introdusa incorect");
							//e.printStackTrace();
						}
						
						spec.setNumarBilete(Integer.parseInt(numarbilete.getText()));
						
						sMan.addSpectacol(spec);
					}
				refreshTable();
				
			}
		});
		btnSpectacolNou.setBounds(430, 325, 142, 32);
		frame.getContentPane().add(btnSpectacolNou);
		
		
		modelWareHouse = new DefaultTableModel();
		tableWarehouse = new JTable(modelWareHouse);
		modelWareHouse.addColumn("Titlul");
		modelWareHouse.addColumn("Regia");
		modelWareHouse.addColumn("Distributia");
		modelWareHouse.addColumn("Data");
		modelWareHouse.addColumn("NumarBilete");
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 47, 535, 252);
		scrollPane.setViewportView(tableWarehouse);
		frame.getContentPane().add(scrollPane);
		
		btnEditareSpectacol = new JButton("Editare Spectacol");
		btnEditareSpectacol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(tableWarehouse.getSelectedRow()>=0){
				
				Spectacol spec = new Spectacol();
				
				spec.setTitlul((String)modelWareHouse.getValueAt(tableWarehouse.getSelectedRow(), 0));
				spec.setDistributia((String)modelWareHouse.getValueAt(tableWarehouse.getSelectedRow(), 2));
				spec.setRegia((String)modelWareHouse.getValueAt(tableWarehouse.getSelectedRow(), 1));
				
				
				spec.setDataPremierei((java.sql.Date)modelWareHouse.getValueAt(tableWarehouse.getSelectedRow(), 3));
				spec.setNumarBilete((int)modelWareHouse.getValueAt(tableWarehouse.getSelectedRow(), 4));
				
				JTextField titlul = new JTextField();
				JTextField regia = new JTextField();
				JTextField distributia = new JTextField();
				JTextField data = new JTextField();
				JTextField numarbilete = new JTextField();
				numarbilete.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						metoda(e);
					}
				});
				
				titlul.setText(spec.getTitlul());
				titlul.setEditable(false);
				regia.setText(spec.getRegia());
				distributia.setText(spec.getDistributia());
				data.setText(spec.getDataPremierei().toString());
				int bilet = spec.getNumarBilete();
				String bilet1 = new String();
				bilet1+=bilet;
				numarbilete.setText(bilet1);
				Object[] message = {
				    "Titlul:", titlul,
				    "Regia:", regia,
				    "Distributia:", distributia,
				    "Data", data,
				    "Numar Bilete", numarbilete
				  };
				int option = JOptionPane.showConfirmDialog(null, message, "Date", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					Spectacol newSpec = new Spectacol();
					newSpec.setTitlul(titlul.getText());
					newSpec.setDistributia(distributia.getText());
					newSpec.setNumarBilete(Integer.parseInt(numarbilete.getText()));
					newSpec.setRegia(regia.getText());
					
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			        Date parsed;
					try {
						parsed = format.parse(data.getText());
						java.sql.Date sql = new java.sql.Date(parsed.getTime());
						newSpec.setDataPremierei(sql);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Data introdusa incorect");
						//e.printStackTrace();
					}
					
					sMan.updateSpectacol(spec, newSpec);
					
					refreshTable();
				}
				
				}
				
			}
		});
		btnEditareSpectacol.setBounds(231, 325, 142, 32);
		frame.getContentPane().add(btnEditareSpectacol);
		
		btnStergSpectacol = new JButton("Sterge Spectacol");
		btnStergSpectacol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tableWarehouse.getSelectedRow()>=0){
					
					Spectacol spec = new Spectacol();
					
					spec.setTitlul((String)modelWareHouse.getValueAt(tableWarehouse.getSelectedRow(), 0));
					spec.setDistributia((String)modelWareHouse.getValueAt(tableWarehouse.getSelectedRow(), 2));
					spec.setRegia((String)modelWareHouse.getValueAt(tableWarehouse.getSelectedRow(), 1));
					spec.setDataPremierei((java.sql.Date)modelWareHouse.getValueAt(tableWarehouse.getSelectedRow(), 3));
					spec.setNumarBilete((int)modelWareHouse.getValueAt(tableWarehouse.getSelectedRow(), 4));
				
				    sMan.deleteSpectacol(spec);
				    refreshTable();
				}
				
				
			}
		});
		btnStergSpectacol.setBounds(37, 325, 142, 32);
		frame.getContentPane().add(btnStergSpectacol);
		
	
		background = new JLabel(new ImageIcon(getClass().getResource("/GUI/background.png")));
		background.setBounds(0, 0, 634, 478);
		frame.getContentPane().add(background);
	}
	//metoda pentru a nu se putea introduce decat cifre in TextField-----------------------------------------------------
	private void metoda(KeyEvent arg0) {
		char c=arg0.getKeyChar();
		 if (!( ((c >= '0') && (c <= '9')) || (c=='+'))) {
			        arg0.consume();
		}	
	}
}

