package com.example.presentation;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * @version 1.0 Mar 23, 2015.
 * @author Moscow209
 */
public class FrmMain extends JFrame {

	private JPanel contentPane = null;
	private JTabbedPane tabbedPane = null;
	private FrmDictionary frmDictionary = null;
	private FrmHistory frmHistory = null;
	private FrmFavorite frmFavorite = null;
	private FrmSession frmSession = null;
	private JButton btnAdd = null;
	private JButton btnSearch = null;
	private JTextField txtSearch = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMain frame = new FrmMain();
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
	public FrmMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 650, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getJTabbedPane());
		contentPane.add(getJButtonAdd());
		contentPane.add(getJTextField());
		contentPane.add(getJButtonSearch());

	}
	
	private JTabbedPane getJTabbedPane(){
		tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBounds(0, 55, 634, 506);
		
		frmDictionary = new FrmDictionary();
		frmFavorite = new FrmFavorite();
		frmHistory = new FrmHistory();
		frmSession = new FrmSession();
		
		tabbedPane.add(frmDictionary, "Dictionary");
		tabbedPane.add(frmHistory, "History");
		tabbedPane.add(frmSession, "Session");
		tabbedPane.add(frmFavorite, "Favorite");
		
		return tabbedPane;
	}
	
	private JButton getJButtonAdd(){
		btnAdd = new JButton("Add");
		btnAdd.setBounds(549, 11, 75, 33);
		return btnAdd;
	}
	
	private JButton getJButtonSearch(){
		btnSearch = new JButton("Search");
		btnSearch.setBounds(0, 11, 75, 33);
		return btnSearch;
	}
	
	private JTextField getJTextField(){
		txtSearch = new JTextField();
		txtSearch.setBounds(81, 11, 288, 33);
		return txtSearch;
	}
}
