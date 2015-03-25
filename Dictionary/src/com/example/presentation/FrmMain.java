package com.example.presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import com.example.common.MyArrayList;
import com.example.dao.FavoriteDAO;
import com.example.dao.HistoryDAO;
import com.example.dao.HistorySessionDAO;
import com.example.dao.LoadDictionaryDAO;
import com.example.model.Dictionary;
import com.example.model.HistorySession;

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
	private JButton btnFavorite = null;
	private JButton btnDislike = null;
	private JTextField txtSearch = null;
	private MyArrayList<Dictionary> dict;
	private HistoryDAO history;
	private HistorySessionDAO session;
	private DefaultListModel<String> model;
	private JList list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMain frame = new FrmMain();
					UIManager
							.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					SwingUtilities.updateComponentTreeUI(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	public FrmMain() throws ParserConfigurationException, SAXException,
			IOException {
		//
		setTitle("Dictionary");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/images/dictionary.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 750, 600);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// add compoment
		loadCompoment();
		// load dict
		long startTime = System.nanoTime();
		dict = new LoadDictionaryDAO().read("Anh_Viet.xml");
		long duration = System.nanoTime() - startTime;
		float s = (float) duration / 1000000000f;
		System.out.println("Read: " + s);
		// load dict in list
		loadMore();
		duration = System.nanoTime() - startTime;
		s = (float) duration / 1000000000f;
		System.out.println("Load:" + s);
	}

	private void loadCompoment() {
		contentPane.add(getJTabbedPane());
		contentPane.add(getJButtonAdd());
		contentPane.add(getJTextField());
		contentPane.add(getJButtonSearch());
		contentPane.add(getJButtonDislike());
		list = frmDictionary.getList();

	}

	private void loadMore() {
		session = new HistorySessionDAO();
		history = new HistoryDAO();
		model = new DefaultListModel<String>();
		for (int i = 0; i < dict.size(); i++) {
			model.addElement(dict.get(i).getWord());
		}
		list.setModel(model);
		list.setSelectedIndex(0);
		frmDictionary.getLblMean().setText(dict.get(0).getMean().substring(1));
		list.addListSelectionListener(new MyListSelectionHandler());
		long startTime = System.nanoTime();
		try {
			history.read("history.xml");
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long duration = System.nanoTime() - startTime;
		float s = (float) duration / 1000000000f;
		System.out.println("Read history: " + s);
	}

	private JTabbedPane getJTabbedPane() {
		tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBounds(0, 55, 730, 502);

		JLabel lbl1 = new JLabel("Dictionary");
		Icon icon1 = new ImageIcon(getClass().getResource("/images/dict.png"));
		lbl1.setIcon(icon1);
		lbl1.setIconTextGap(5);
		lbl1.setHorizontalTextPosition(SwingConstants.RIGHT);

		JLabel lbl2 = new JLabel("History");
		Icon icon2 = new ImageIcon(getClass()
				.getResource("/images/history.png"));
		lbl2.setIcon(icon2);
		lbl2.setIconTextGap(5);
		lbl2.setHorizontalTextPosition(SwingConstants.RIGHT);

		JLabel lbl3 = new JLabel("Session");
		Icon icon3 = new ImageIcon(getClass()
				.getResource("/images/session.png"));
		lbl3.setIcon(icon3);
		lbl3.setIconTextGap(5);
		lbl3.setHorizontalTextPosition(SwingConstants.RIGHT);

		JLabel lbl4 = new JLabel("Favorite");
		Icon icon4 = new ImageIcon(getClass().getResource(
				"/images/favorite.png"));
		lbl4.setIcon(icon4);
		lbl4.setIconTextGap(5);
		lbl4.setHorizontalTextPosition(SwingConstants.RIGHT);

		frmDictionary = new FrmDictionary();
		frmFavorite = new FrmFavorite();
		frmSession = new FrmSession();
		frmHistory = new FrmHistory();

		tabbedPane.add(frmDictionary, "Dictionary");
		tabbedPane.add(frmHistory, "History");
		tabbedPane.add(frmSession, "Session");
		tabbedPane.add(frmFavorite, "Favorite");

		tabbedPane.setTabComponentAt(0, lbl1);
		tabbedPane.setTabComponentAt(1, lbl2);
		tabbedPane.setTabComponentAt(2, lbl3);
		tabbedPane.setTabComponentAt(3, lbl4);

		tabbedPane.addChangeListener(new TabbedChange());
		return tabbedPane;
	}

	private JButton getJButtonAdd() {
		btnAdd = new JButton("");
		btnAdd.setIcon(new ImageIcon(getClass().getResource("/images/add.png")));
		btnAdd.setBounds(578, 11, 43, 33);
		btnAdd.addActionListener(new AddButtonListener());
		return btnAdd;
	}

	private JButton getJButtonSearch() {
		btnFavorite = new JButton("");
		btnFavorite.setIcon(new ImageIcon(getClass().getResource(
				"/images/like.png")));
		btnFavorite.setBounds(687, 11, 43, 33);
		btnFavorite.addActionListener(new FavoriteButtonListener());
		return btnFavorite;
	}

	private JButton getJButtonDislike() {
		btnDislike = new JButton("");
		btnDislike.setIcon(new ImageIcon(getClass().getResource(
				"/images/dislike.png")));
		btnDislike.setBounds(634, 11, 43, 33);
		btnDislike.addActionListener(new DislikeButtonListener());
		return btnDislike;
	}

	private JTextField getJTextField() {
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(0, 11, 43, 33);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource(
				"/images/search.png")));
		contentPane.add(lblNewLabel);
		Border border = BorderFactory.createLineBorder(Color.BLUE, 2);

		txtSearch = new JTextField();
		txtSearch.setBounds(41, 11, 377, 33);
		txtSearch.getDocument().addDocumentListener(new SearchChangedText());
		txtSearch.setBorder(border);
		return txtSearch;
	}

	private class MyListSelectionHandler implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			if (!e.getValueIsAdjusting()) {
				String word = list.getSelectedValue().toString();
				for (int i = 0; i < dict.size(); i++) {
					if (dict.get(i).getWord().equalsIgnoreCase(word)) {
						// Show text
						String mean = dict.get(i).getMean().substring(1);
						frmDictionary.getLblMean().setText(mean);
						// add session
						session.add(new HistorySession(word, mean));
						// add history
						history.addList(dict.get(i));
						try {
							history.write("history.xml", "history");
						} catch (ParserConfigurationException | SAXException
								| IOException | TransformerException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;
					}
				}
			}
		}
	}

	private class SearchChangedText implements DocumentListener {

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub
			String word = txtSearch.getText();
			for (int i = 0; i < dict.size(); i++) {
				if (dict.get(i).getWord().startsWith(word)) {
					String mean = dict.get(i).getMean();
					frmDictionary.getLblMean().setText(mean.substring(1));
					list.setSelectedIndex(i);
					list.ensureIndexIsVisible(list.getSelectedIndex());
					break;
				}
			}
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub
			String word = txtSearch.getText();
			for (int i = 0; i < dict.size(); i++) {
				if (dict.get(i).getWord().startsWith(word)) {
					String mean = dict.get(i).getMean();
					frmDictionary.getLblMean().setText(mean.substring(1));
					list.setSelectedIndex(i);
					list.ensureIndexIsVisible(list.getSelectedIndex());
					break;
				}
			}
		}
	}

	private class TabbedChange implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			switch (tabbedPane.getSelectedIndex()) {
			case 1:
				FrmHistory frmHis = new FrmHistory("history");
				tabbedPane.setComponentAt(1, frmHis);
				frmHis.load(history.getHistory());
				break;
			case 2:
				FrmSession frmSess = new FrmSession("session");
				tabbedPane.setComponentAt(2, frmSess);
				frmSess.load(session.getHistory());
				break;

			case 3:
				frmFavorite = new FrmFavorite("favorite");
				tabbedPane.setComponentAt(3, frmFavorite);
				try {
					frmFavorite.load();
				} catch (ParserConfigurationException | SAXException
						| IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(FrmMain.this,
							"File not exits!", "Message",
							JOptionPane.INFORMATION_MESSAGE);
				}
				break;
			}
		}

	}

	private class FavoriteButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (tabbedPane.getSelectedIndex() != 0) {
				JOptionPane.showMessageDialog(FrmMain.this,
						"You can not implement this interface!", "Message",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			String word = frmDictionary.getList().getSelectedValue().toString();
			String mean = frmDictionary.getLblMean().getText();
			FavoriteDAO dao = new FavoriteDAO();
			try {
				dao.read("favorite.xml");
				if (dao.isCheck(word))
					dao.write("favorite.xml", word, mean);
				else
					JOptionPane.showMessageDialog(FrmMain.this,
							"You added word in favorite!", "Message",
							JOptionPane.INFORMATION_MESSAGE);
			} catch (ParserConfigurationException | SAXException | IOException
					| TransformerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	private class DislikeButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (tabbedPane.getSelectedIndex() != 3) {
				JOptionPane.showMessageDialog(FrmMain.this,
						"You can not implement this interface!", "Message",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			try {
				frmFavorite.removeFavorite();
			} catch (FileNotFoundException | XMLStreamException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(FrmMain.this,
						"Cant not remove word!", "Message",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

	}

	private class AddButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			AddWordPane addPane = new AddWordPane();
			int result = JOptionPane.showConfirmDialog(null, addPane,
					"Add word", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				for (int i = 0; i < dict.size(); i++) {
					if (dict.get(i).getWord()
							.equalsIgnoreCase(addPane.getWord())) {
						JOptionPane.showMessageDialog(FrmMain.this,
								"Word exits in database!", "Message",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				}
				// Ghi file
				long startTime = System.nanoTime();
				dict.add(new Dictionary(addPane.getWord(), addPane.getMean()));
				reloadModel(addPane.getWord());
				long duration = System.nanoTime() - startTime;
				float s = (float) duration / 1000000000f;
				System.out.println("Reload: " + s);

				//
				new Thread(new RunBackground()).start();
			}
		}
	}

	private void reloadModel(String word) {
		model.addElement(word);
		Object a[] = model.toArray();
		Arrays.sort(a);
		for (int i = 0; i < model.size(); i++) {
			model.set(i, (String) a[i]);
		}
	}

	private class AddWordPane extends JPanel {

		private JTextField txtWord;
		private JTextArea txtMean;

		public AddWordPane() {
			this.setLayout(new BorderLayout(2, 2));
			txtWord = new JTextField(30);
			this.add(txtWord, BorderLayout.PAGE_START);
			txtMean = new JTextArea(10, 30);
			this.add(txtMean);
		}

		public String getWord() {
			return txtWord.getText();
		}

		public String getMean() {
			return txtMean.getText();
		}
	}

	private class RunBackground implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			long startTime = System.nanoTime();
			try {
				new LoadDictionaryDAO().write("Anh_Viet.xml", dict);
				long duration = System.nanoTime() - startTime;
				float s = (float) duration / 1000000000f;
				System.out.println(s);
			} catch (XMLStreamException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
