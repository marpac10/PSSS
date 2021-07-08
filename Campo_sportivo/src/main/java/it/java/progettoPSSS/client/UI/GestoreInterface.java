package it.java.progettoPSSS.client.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import it.java.progettoPSSS.client.control.ControllerUtente;
import it.java.progettoPSSS.server.domain.Campo;
import it.java.progettoPSSS.server.domain.Prenotazione;
import it.java.progettoPSSS.server.domain.Ticket;
import it.java.progettoPSSS.server.domain.Utente;

public class GestoreInterface extends JFrame {

	private JPanel contentPane;
	private Utente utente;
	private JTable tableprenotazioni;
	JComboBox comboBoxcampi;
	int idsel;
	private JTable table;
	private JTable table_prenotazioni;
	private JTable table_ticket;
	JLabel lbldasettareUsername;
	JLabel lbldasettarePP;
	JLabel lbldasettareStato;
	JPanel panelprenota;
	JPanel panelannulla;
	JPanel panelprofilo;
	JPanel panelmodifica;
	JTabbedPane tabbedPane;
	JButton btnConfermaPrenotazione;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestoreInterface frame = new GestoreInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestoreInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,  854, 604);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 662, 418);
		getContentPane().add(tabbedPane);
		
		panelprenota = new JPanel();
		panelprenota.setForeground(new Color(0, 0, 0));
		tabbedPane.addTab("Prenota un campo", null, panelprenota, null);
		
		panelprenota.setLayout(null);
		
		comboBoxcampi = new JComboBox();
		comboBoxcampi.setBounds(131, 40, 495, 20);
		panelprenota.add(comboBoxcampi);
			
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

		panelprenota.add(btnCercaPrenotazioni);
			
		btnConfermaPrenotazione = new JButton("Conferma");
		btnConfermaPrenotazione.setBounds(419, 253, 188, 23);
		panelprenota.add(btnConfermaPrenotazione);
		
		panelannulla = new JPanel();
		tabbedPane.addTab("Annulla Prenotazione", null, panelannulla, null);
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
		
		JButton btnAnnulla = new JButton("Conferma");
		btnAnnulla.setBounds(416, 257, 178, 23);
		panelannulla.add(btnAnnulla);
		
		panelprofilo = new JPanel();
		tabbedPane.addTab("Visualizza profilo", null, panelprofilo, null);
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
	
	
	JButton btnCercaUtente = new JButton("Cerca Utente");
	btnCercaUtente.setBounds(400, 11, 170, 23);
	panelprofilo.add(btnCercaUtente);
		
	panelmodifica = new JPanel();
	tabbedPane.addTab("Modifica Prenotazioni", null, panelmodifica, null);
	panelmodifica.setLayout(null);
	
	JButton btnCaricaPrenotazione = new JButton("Carica Prenotazioni");
	btnCaricaPrenotazione.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		}
	});
	btnCaricaPrenotazione.setBounds(200, 30, 300, 30);
	panelmodifica.add(btnCaricaPrenotazione);
	
	}

}
