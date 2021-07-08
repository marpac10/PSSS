package it.java.progettoPSSS.client.UI;

import java.awt.EventQueue;


import it.java.progettoPSSS.client.control.ControllerUtente;
import it.java.progettoPSSS.server.domain.*;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.util.TestUtils;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;

public class UtenteInterface {

	private JFrame frame;
	private Utente utente;
	JTextArea textAreaUsername;
	JTextArea textAreausername;
	JTextArea email;
	private JPasswordField passwordfield;
	private JPasswordField passwordField;
	private JTable tableprenotazioni;
	JCheckBox chckbxPagaalcampo;
	JComboBox comboBoxcampi;
	JLabel lblNewLabelCodiceCarta;
	JTextArea textAreaCodiceCarta;
	JLabel lblNomeTitolare;
	JTextArea textNomeTitolare;
	JLabel lblScadenzaCarta;
	JTextArea textScadenzaMese;
	JTextArea textScadenzaAnno;
	JLabel lblcvv;
	JTextArea textAreacvv;
	JButton btnPaga ;
	int idsel;
	private JTable table;
	private JTable table_prenotazioni;
	private JTable table_ticket;
	JLabel lbldasettareUsername;
	JLabel lbldasettarePP;
	JLabel lbldasettareStato;
	int id_utente = 0;
	JPanel panelprenota;
	JPanel panelannulla;
	JPanel panelprofilo;
	JPanel panelregistrazione;
	JTabbedPane tabbedPane;
	JButton btnEsci;
	JButton btnConfermaPrenotazione;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UtenteInterface window = new UtenteInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
		
		
	}

	/**
	 * Create the application.
	 */
	public UtenteInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 854, 604);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 662, 418);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panelaccesso = new JPanel();
		tabbedPane.addTab("Accedi", null, panelaccesso, null);
		panelaccesso.setLayout(null);
		
		JLabel lblinserisciusername = new JLabel("Inserisci username");
		lblinserisciusername.setBounds(50, 93, 166, 64);
		panelaccesso.add(lblinserisciusername);
		
		textAreaUsername = new JTextArea();
		textAreaUsername.setBounds(195, 113, 123, 22);
		panelaccesso.add(textAreaUsername);
		
		JLabel lblinseriscipassword = new JLabel("Inserisci password");
		lblinseriscipassword.setBounds(50, 191, 166, 64);
		panelaccesso.add(lblinseriscipassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(195, 211, 123, 22);
		panelaccesso.add(passwordField);
		
		
		
		JButton btnaccedi = new JButton("Accedi");
		btnaccedi.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnaccedi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				id_utente = ControllerUtente.getIstance().login(textAreaUsername.getText(), passwordField.getText());
				
				if (id_utente != 0) {
					
					utente = new Utente();
					
					utente.setId(id_utente);
					utente.setUsername(textAreaUsername.getText());
					utente.setPassword(passwordField.getText());
					
					tabbedPane.setEnabledAt(1, true);
					tabbedPane.setEnabledAt(2, true);
					tabbedPane.setEnabledAt(3, true);
					tabbedPane.setEnabledAt(4, false);
					
					btnEsci.setVisible(true);
					btnEsci.setEnabled(true);
					
					JOptionPane.showMessageDialog(null, "Accesso eseguito con successo");
					
					}
				
				else JOptionPane.showMessageDialog(null, "Accesso fallito, riprova");
				
				
				
				
				}
				
				
			});
		
		btnaccedi.setBounds(450, 301, 130, 30);
		panelaccesso.add(btnaccedi);
		
		
		btnEsci = new JButton("Esci");
		btnEsci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					id_utente = 0;
					
					tabbedPane.setEnabledAt(1, false);
					tabbedPane.setEnabledAt(2, false);
					tabbedPane.setEnabledAt(3, false);
					tabbedPane.setEnabledAt(4, true);
					
					textAreaUsername.setText("");
					passwordField.setText("");
					
					textAreaCodiceCarta.setText("");
					textAreacvv.setText("");
					textNomeTitolare.setText("");
					textScadenzaMese.setText("");
					textScadenzaAnno.setText("");
					
					chckbxPagaalcampo.setSelected(false);
					
					
					btnEsci.setVisible(false);
					btnEsci.setEnabled(false);
					
					DefaultTableModel model1 = (DefaultTableModel) tableprenotazioni.getModel();
					while(model1.getRowCount() != 0)
						model1.removeRow(0);
					
					DefaultTableModel model2 = (DefaultTableModel) table.getModel();
					while(model2.getRowCount() != 0)
						model2.removeRow(0);
					
					DefaultTableModel model3 = (DefaultTableModel) table_ticket.getModel();
					while(model3.getRowCount() != 0)
						model3.removeRow(0);
					
					DefaultTableModel model4 = (DefaultTableModel) table_prenotazioni.getModel();
					while(model4.getRowCount() != 0)
						model4.removeRow(0);
					
					lbldasettareUsername.setText("");
					lbldasettarePP.setText("");
					lbldasettareStato.setText("");
					
					JOptionPane.showMessageDialog(null, "Logout effettuato!");
			}
		});
		
		btnEsci.setVisible(false);
		btnEsci.setEnabled(false);
		btnEsci.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEsci.setBounds(450, 33, 130, 30);
		panelaccesso.add(btnEsci);
		
		
		
		panelprenota = new JPanel();
		panelprenota.setForeground(new Color(0, 0, 0));
		tabbedPane.addTab("Prenota un campo", null, panelprenota, null);
		tabbedPane.setEnabledAt(1, false);
		
		ArrayList<Campo> campi = ControllerUtente.getIstance().listaCampiDisponibili();
		panelprenota.setLayout(null);
		
		comboBoxcampi = new JComboBox();
		comboBoxcampi.setBounds(131, 40, 495, 20);
		panelprenota.add(comboBoxcampi);
		
		for (Campo campo : campi) {
			comboBoxcampi.addItem("Id: " + campo.getId() + " -- Sport: " + campo.getSport() + " -- Tipologia: " + campo.getTipologia() + " -- Prezzo: €" + (int)campo.getPrezzo() + ",00");
		}		
			
		JLabel lblselezionacampo = new JLabel("Seleziona un campo:");
		lblselezionacampo.setBounds(31, 14, 375, 14);
		panelprenota.add(lblselezionacampo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 104, 627, 138);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelprenota.add(scrollPane);
		
		tableprenotazioni = new JTable();
		tableprenotazioni.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Data", "Ora"
			}
		));
		scrollPane.setViewportView(tableprenotazioni);
		
		JButton btnCercaPrenotazioni = new JButton("Cerca");
		btnCercaPrenotazioni.setBounds(419, 71, 188, 23);
		btnCercaPrenotazioni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				idsel = comboBoxcampi.getSelectedIndex() + 1;
				
				ArrayList<Prenotazione> prenotazioni = ControllerUtente.getIstance().listaPrenotazioniDisponibili(idsel);
				DefaultTableModel model = (DefaultTableModel) tableprenotazioni.getModel();
				while(model.getRowCount() != 0)
				model.removeRow(0);
				
				for (Prenotazione prenotazione : prenotazioni) {
						model.addRow(new Object[] {prenotazione.getId()+"",prenotazione.getData()+"",prenotazione.getOra()+""});
				}
				
				
			}
		});
		
		chckbxPagaalcampo = new JCheckBox("Paga con carta");
		chckbxPagaalcampo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxPagaalcampo.isSelected()) {
					lblNewLabelCodiceCarta.setVisible(true);
					lblNomeTitolare.setVisible(true);
					lblScadenzaCarta.setVisible(true);
					lblcvv.setVisible(true);
					textAreacvv.setVisible(true);
					textAreaCodiceCarta.setVisible(true);
					textNomeTitolare.setVisible(true);
					textScadenzaMese.setVisible(true);
					textScadenzaAnno.setVisible(true);
					btnPaga.setVisible(true);
					btnConfermaPrenotazione.setVisible(false);
				}
				else {
					lblNewLabelCodiceCarta.setVisible(false);
					lblNomeTitolare.setVisible(false);
					lblScadenzaCarta.setVisible(false);
					lblcvv.setVisible(false);
					textAreacvv.setVisible(false);
					textAreaCodiceCarta.setVisible(false);
					textNomeTitolare.setVisible(false);
					textScadenzaMese.setVisible(false);
					textScadenzaAnno.setVisible(false);
					btnPaga.setVisible(false);
					btnConfermaPrenotazione.setVisible(true);
				}
			}
		});
		chckbxPagaalcampo.setBounds(42, 253, 290, 23);
		panelprenota.add(chckbxPagaalcampo);
		panelprenota.add(btnCercaPrenotazioni);
		
		lblNewLabelCodiceCarta = new JLabel("Numero Carta");
		lblNewLabelCodiceCarta.setBounds(42, 310, 130, 20);
		lblNewLabelCodiceCarta.setVisible(false);
		panelprenota.add(lblNewLabelCodiceCarta);
		
		textAreaCodiceCarta = new JTextArea();
		textAreaCodiceCarta.setBounds(220, 310, 130, 20);
		textAreaCodiceCarta.setVisible(false);
		panelprenota.add(textAreaCodiceCarta);
		
		lblcvv = new JLabel("CVV");
		lblcvv.setBounds(42, 360, 130, 20);
		lblcvv.setVisible(false);
		panelprenota.add(lblcvv);
		
		textAreacvv = new JTextArea();
		textAreacvv.setBounds(220, 360, 25, 20);
		textAreacvv.setVisible(false);
		panelprenota.add(textAreacvv);
		
		lblNomeTitolare = new JLabel("Titolare");
		lblNomeTitolare.setBounds(42, 285, 130, 15);
		lblNomeTitolare.setVisible(false);
		panelprenota.add(lblNomeTitolare);
		
		textNomeTitolare = new JTextArea();
		textNomeTitolare.setBounds(220, 285, 130, 20);
		textNomeTitolare.setVisible(false);
		panelprenota.add(textNomeTitolare);
		
		lblScadenzaCarta = new JLabel("Scadenza (MM/AA)");
		lblScadenzaCarta.setBounds(42, 335, 130, 20);
		lblScadenzaCarta.setVisible(false);
		panelprenota.add(lblScadenzaCarta);
		
		textScadenzaMese = new JTextArea();
		textScadenzaMese.setBounds(220, 335, 25, 20);
		textScadenzaMese.setVisible(false);
		panelprenota.add(textScadenzaMese);
		
		textScadenzaAnno = new JTextArea();
		textScadenzaAnno.setBounds(250, 335, 25, 20);
		textScadenzaAnno.setVisible(false);
		panelprenota.add(textScadenzaAnno);
		
		btnPaga = new JButton("Paga");
		btnPaga.setBounds(445, 345, 89, 23);
		btnPaga.setVisible(false);
		btnPaga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int error = ControllerUtente.getIstance().checkPagamento(textNomeTitolare.getText(), textAreaCodiceCarta.getText(), textScadenzaMese.getText(), textScadenzaAnno.getText(), textAreacvv.getText());
				
				if(error == 0) 
					JOptionPane.showMessageDialog(null, "Errore inserimento dati titolare, riprova");
				else if(error == 1)
					JOptionPane.showMessageDialog(null, "Errore inserimento numero carta, riprova");
				else if(error == 2)
					JOptionPane.showMessageDialog(null, "Errore inserimento mese scadenza, riprova");
				else if(error == 3)
					JOptionPane.showMessageDialog(null, "Errore inserimento anno scadenza, riprova");
				else if(error == 4)
					JOptionPane.showMessageDialog(null, "Errore inserimento cvv, riprova");
				
				
				else {
				if (ControllerUtente.getIstance().pagamento(textNomeTitolare.getText(), textAreaCodiceCarta.getText(), textScadenzaMese.getText(), textScadenzaAnno.getText(), textAreacvv.getText())) {
				int riga = tableprenotazioni.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) tableprenotazioni.getModel();
				Integer idselezionato = Integer.parseInt((String)model.getValueAt(riga, 0));
				LocalDate dataselezionata = LocalDate.parse((String)model.getValueAt(riga, 1));
				LocalTime oraselezionata = LocalTime.parse((String)model.getValueAt(riga, 2));
				Prenotazione p = new Prenotazione();
				p.setId(idselezionato);
				p.setData(dataselezionata);
				p.setOra(oraselezionata);
				p.setDisponibile(true);
				p.setPagata(true);
				p.setid_campo(idsel);
				p.setid_utente(5);
				
				if(ControllerUtente.getIstance().prenotaCampo(utente, p)) {
					JOptionPane.showMessageDialog(null, "Pagamento effettuato con successo e prenotazione effettuata!");
					model.removeRow(riga);
				}
				else JOptionPane.showMessageDialog(null, "Errore nella prenotazione, riprova");
				}
				else JOptionPane.showMessageDialog(null, "Errore nel pagamento, riprova");
			}
			
				
			}				
			
		});
		panelprenota.add(btnPaga);
		
		
		
		btnConfermaPrenotazione = new JButton("Conferma");
		btnConfermaPrenotazione.setBounds(419, 253, 188, 23);
		btnConfermaPrenotazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!(chckbxPagaalcampo.getModel().isSelected())) {
				int riga = tableprenotazioni.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) tableprenotazioni.getModel();
				Integer idselezionato = Integer.parseInt((String)model.getValueAt(riga, 0));
				LocalDate dataselezionata = LocalDate.parse((String)model.getValueAt(riga, 1));
				LocalTime oraselezionata = LocalTime.parse((String)model.getValueAt(riga, 2));
				Prenotazione p = new Prenotazione();
				p.setId(idselezionato);
				p.setData(dataselezionata);
				p.setOra(oraselezionata);
				p.setDisponibile(true);
				p.setPagata(false);
				p.setid_campo(idsel);
				p.setid_utente(5);
				
				if(ControllerUtente.getIstance().prenotaCampo(utente, p)) {
					JOptionPane.showMessageDialog(null, "Prenotazione confermata, dovrai pagare al campo");
					model.removeRow(riga);
				}
				else JOptionPane.showMessageDialog(null, "Errore nella prenotazione, riprova");
				}
			}
		});
		panelprenota.add(btnConfermaPrenotazione);
		
		
		
		
		
		panelannulla = new JPanel();
		tabbedPane.addTab("Annulla Prenotazione", null, panelannulla, null);
		tabbedPane.setEnabledAt(2, false);
		panelannulla.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(64, 65, 546, 181);
		panelannulla.add(scrollPane_1);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Data", "Ora", "Campo", "Pagata"
			}
		));
		scrollPane_1.setViewportView(table);
		
		JLabel lblAnnullaPrenotazione = new JLabel("Seleziona la prenotazione da annullare:");
		lblAnnullaPrenotazione.setBounds(64, 25, 280, 14);
		panelannulla.add(lblAnnullaPrenotazione);
		
		JButton btnAggiornaAnnulla = new JButton("Aggiorna");
		btnAggiornaAnnulla.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
				
				ArrayList<Prenotazione> prenotazioni = ControllerUtente.getIstance().prenotazioniEffettuate(utente.getId());
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				while(model.getRowCount() != 0)
				model.removeRow(0);
				
				for (Prenotazione prenotazione : prenotazioni) {
						String pag = new String();
						if (prenotazione.isPagata()) pag = "Si";
						else pag = "No";
						model.addRow(new Object[] {prenotazione.getId()+"",prenotazione.getData()+"",prenotazione.getOra()+"",prenotazione.getid_campo()+"",pag});
				}
					
			}
		});
		btnAggiornaAnnulla.setBounds(416, 21, 89, 23);
		panelannulla.add(btnAggiornaAnnulla);
		
		JButton btnAnnulla = new JButton("Conferma");
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int riga = table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				Integer idselezionato = Integer.parseInt((String)model.getValueAt(riga, 0));
				LocalDate dataselezionata = LocalDate.parse((String)model.getValueAt(riga, 1));
				LocalTime oraselezionata = LocalTime.parse((String)model.getValueAt(riga, 2));
				Integer id_campo_selezionato = Integer.parseInt((String)model.getValueAt(riga, 3));
				boolean pagata_selezionata;
				if (model.getValueAt(riga, 4).equals("Si")) pagata_selezionata=true;
				else pagata_selezionata = false;
				Prenotazione p = new Prenotazione();
				p.setId(idselezionato);
				p.setData(dataselezionata);
				p.setOra(oraselezionata);
				p.setDisponibile(false);
				p.setPagata(pagata_selezionata);
				p.setid_campo(id_campo_selezionato);
				p.setid_utente(utente.getId());
				if((p.getData().getDayOfYear() > LocalDate.now().getDayOfYear()) || (p.getData().getDayOfYear() == LocalDate.now().getDayOfYear() && p.getOra().getHour() > LocalTime.now().getHour()))
				{
					if(ControllerUtente.getIstance().annullaPrenotazione(p,utente)) {
					JOptionPane.showMessageDialog(null, "Annullamento confermato");
					model.removeRow(riga);
					}
					else JOptionPane.showMessageDialog(null, "Errore nell'annullamento, riprova");
				}
				else JOptionPane.showMessageDialog(null, "Impossibile annullare questa prenotazione!");
				
				
			}
		});
		btnAnnulla.setBounds(416, 257, 178, 23);
		panelannulla.add(btnAnnulla);
		
		panelprofilo = new JPanel();
		tabbedPane.addTab("Visualizza profilo", null, panelprofilo, null);
		tabbedPane.setEnabledAt(3, false);
		panelprofilo.setLayout(null);
		
		
		
		JLabel lblUsernameProfilo = new JLabel("Username");
		lblUsernameProfilo.setBounds(34, 77, 98, 14);
		panelprofilo.add(lblUsernameProfilo);
		
		lbldasettareUsername = new JLabel("");
		lbldasettareUsername.setBounds(211, 77, 127, 14);
		panelprofilo.add(lbldasettareUsername);
		
		JLabel lblPrenotazioniProfilo = new JLabel("Prenotazioni effettuate");
		lblPrenotazioniProfilo.setBounds(34, 128, 133, 14);
		panelprofilo.add(lblPrenotazioniProfilo);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(206, 125, 384, 67);
		panelprofilo.add(scrollPane_2);
		
		table_prenotazioni = new JTable();
		table_prenotazioni.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Data", "Ora", "Campo", "Pagata"
			}
		));
		scrollPane_2.setViewportView(table_prenotazioni);
		
		JLabel lblPuntiPremium = new JLabel("Punti premium");
		lblPuntiPremium.setBounds(49, 251, 98, 14);
		panelprofilo.add(lblPuntiPremium);
		
		JLabel lblStato = new JLabel("Stato");
		lblStato.setBounds(334, 251, 46, 14);
		panelprofilo.add(lblStato);
		
		lbldasettarePP = new JLabel("");
		lbldasettarePP.setBounds(167, 251, 55, 14);
		panelprofilo.add(lbldasettarePP);
		
		lbldasettareStato = new JLabel("");
		lbldasettareStato.setBounds(450, 251, 127, 14);
		panelprofilo.add(lbldasettareStato);
		
		JLabel lblTicket = new JLabel("Ticket accumulati");
		lblTicket.setBounds(32, 306, 115, 14);
		panelprofilo.add(lblTicket);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(206, 306, 384, 73);
		panelprofilo.add(scrollPane_3);
		
		table_ticket = new JTable();
		table_ticket.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Data rilascio", "Ora rilascio", "Valore"
			}
		));
		scrollPane_3.setViewportView(table_ticket);
	
	
	JButton btnAggiornaProfilo = new JButton("Aggiorna");
	btnAggiornaProfilo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			lbldasettareUsername.setText(utente.getUsername());
			ArrayList<Prenotazione> prenotazioni = ControllerUtente.getIstance().prenotazioniEffettuate(utente.getId());
			int punti = ControllerUtente.getIstance().visualizzaPuntiPremium(utente.getId());
			lbldasettarePP.setText((Integer.toString(punti))); 
			ArrayList<Ticket> ticket = ControllerUtente.getIstance().listaTicket(utente.getId());
			if (punti>=100) lbldasettareStato.setText("PREMIUM");
			else lbldasettareStato.setText("STANDARD");
			
			DefaultTableModel modelp = (DefaultTableModel) table_prenotazioni.getModel();
			while(modelp.getRowCount() != 0)
			modelp.removeRow(0);
			
			for (Prenotazione prenotazione : prenotazioni) {
				String pag = new String();
				if (prenotazione.isPagata()) pag = "Si";
				else pag = "No";
				modelp.addRow(new Object[] {prenotazione.getId()+"",prenotazione.getData()+"",prenotazione.getOra()+"",prenotazione.getid_campo()+"",pag});
			}
			
			DefaultTableModel modelt = (DefaultTableModel) table_ticket.getModel();
			while(modelt.getRowCount() != 0)
			modelt.removeRow(0);
			
			for (Ticket t : ticket) {
				modelt.addRow(new Object[] {t.getId()+"",t.getData()+"",t.getOra()+"",t.getValore()});
			}
		
			
		}
	});
	btnAggiornaProfilo.setBounds(455, 11, 89, 23);
	panelprofilo.add(btnAggiornaProfilo);
	
	panelregistrazione = new JPanel();
	tabbedPane.addTab("Registrazione", null, panelregistrazione, null);
	panelregistrazione.setLayout(null);
	
	JLabel lblusername = new JLabel("Inserisci username");
	lblusername.setBounds(50, 53, 166, 64);
	panelregistrazione.add(lblusername);
	
	textAreausername = new JTextArea();
	textAreausername.setBounds(195, 73, 123, 22);
	panelregistrazione.add(textAreausername);
	
	JLabel lblpassword = new JLabel("Inserisci password");
	lblpassword.setBounds(50, 103, 166, 64);
	panelregistrazione.add(lblpassword);
	
	passwordfield = new JPasswordField();
	passwordfield.setBounds(195, 123, 123, 22);
	panelregistrazione.add(passwordfield);
	
	JLabel lblemail = new JLabel("Inserisci email");
	lblemail.setBounds(50, 153, 166, 64);
	panelregistrazione.add(lblemail);
	
	email = new JTextArea();
	email.setBounds(195, 173, 150, 22);
	panelregistrazione.add(email);
	
	JButton btnRegistrati = new JButton("Registrati");
	btnRegistrati.setFont(new Font("Tahoma", Font.BOLD, 16));
	btnRegistrati.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			int error = ControllerUtente.getIstance().checkRegistrazione(textAreausername.getText(), passwordfield.getText(), email.getText());
			
			if(error == 0) 
				JOptionPane.showMessageDialog(null, "Formato Username non valido!");
			else if(error == 1)
				JOptionPane.showMessageDialog(null, "La Password deve essere almeno di 6 caratteri!");
			else if(error == 2)
				JOptionPane.showMessageDialog(null, "Formato Email non valido!");
			
			else {
			if (ControllerUtente.getIstance().registrazione(textAreausername.getText(), passwordfield.getText(), email.getText())) {
				JOptionPane.showMessageDialog(null, "Registrazione eseguita con successo");
				textAreausername.setText("");
				passwordfield.setText("");
				email.setText("");
			}
		
		else JOptionPane.showMessageDialog(null, "Registrazione fallita, riprova");
		
			}
			
		}
			
	});
		
	btnRegistrati.setBounds(400, 250, 130, 30);
	panelregistrazione.add(btnRegistrati);
	
}
}
