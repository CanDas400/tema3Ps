package GUI;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import BusinessLogic.BiletManager;
import Models.Bilet;
import javax.swing.JButton;
import BusinessLogic.SpectacolManager;
import Models.Spectacol;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;




public class UserLogin {

	public JFrame frame;
	private JLabel background;
	private JComboBox<String> comboBox;
	private JScrollPane scrollPane;
	private DefaultTableModel modelWareHouse;
	private JTable tableWarehouse;
	private BiletManager bMan;
	private SpectacolManager sMan;
	
	private JButton btnAdauga;


	/**
	 * Create the application.
	 */
	public UserLogin() {
		initialize();
		bMan = new BiletManager();
		sMan = new SpectacolManager();
		refreshChoices();
		refreshTable((String)comboBox.getSelectedItem());
		
	}
	
	private void refreshTable(String specName) {
		// TODO Auto-generated method stub
		modelWareHouse.setRowCount(0);
		
		for (Bilet b: bMan.bileteSpectacol(specName)) {
			modelWareHouse.addRow(new Object[] {b.getNumeSpectacol(),b.getNumar(),b.getRand()});
		}
		
		
	}
	private void refreshChoices() {
		// TODO Auto-generated method stub
		for(Spectacol s:sMan.listaSpectacole()){
			comboBox.addItem(s.getTitlul());
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
		
		
		modelWareHouse = new DefaultTableModel();
		tableWarehouse = new JTable(modelWareHouse);
		modelWareHouse.addColumn("Titlul");
		modelWareHouse.addColumn("Coloana");
		modelWareHouse.addColumn("Rand");

		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	refreshTable((String)comboBox.getSelectedItem());
		    }
		});
		
		comboBox.setBounds(73, 29, 298, 28);
		frame.getContentPane().add(comboBox);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(182, 81, 298, 279);
		scrollPane.setViewportView(tableWarehouse);
		frame.getContentPane().add(scrollPane);
		
		btnAdauga = new JButton("Adauga Bilet");
		btnAdauga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedValue = (String) comboBox.getSelectedItem();

				JTextField nume = new JTextField();
				JTextField coloana = new JTextField();
				JTextField numar = new JTextField();
				
				numar.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						metoda(e);
					}
				});
				coloana.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						metoda(e);
					}
				});
				
				nume.setText(selectedValue);
				nume.setEditable(false);
				
				Object[] message = {
					    "Titlul:", nume,
					    "Numar Coloana:", coloana,
					    "Rand:", numar
					  };
					int option = JOptionPane.showConfirmDialog(null, message, "Date", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						Bilet b = new Bilet();
						b.setNumeSpectacol(nume.getText());
						b.setNumar(Integer.parseInt(coloana.getText()));
						b.setRand(Integer.parseInt(numar.getText()));
						
						int nrMaximBil =sMan.getSpectacol(b.getNumeSpectacol()).getNumarBilete();
						
						if(!bMan.addBilet(nrMaximBil,b)){
							JOptionPane.showMessageDialog(null, "Exista deja sau nu mai sunt bilete de vanzare");
						}
						
					}
				
				refreshTable(selectedValue);
				
			}
		});
		btnAdauga.setBounds(412, 29, 144, 28);
		frame.getContentPane().add(btnAdauga);
		
		
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
