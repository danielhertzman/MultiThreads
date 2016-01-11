package assignment5;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * UI for the client
 * @author Daniel Hertzman-Ericson
 *
 */
public class ClientUI extends JPanel implements ActionListener {
	private JButton login = new JButton("Logga In!");
	private JButton send = new JButton("Skicka!");
	private JButton logout = new JButton("Logga ut!");

	private Client client;
	private boolean isConnected;

	private StyledDocument document;
	private JTextPane chatLbl;
	private String[] recipients;
	
	private String myUsername = "";
	private String recipient = "";

	private JFileChooser chooser = new JFileChooser();
	private FileNameExtensionFilter filter = new FileNameExtensionFilter(
			"'.jpg', '.png', '.gif'", "jpg", "png", "gif");

	private Style imgStyle;
	private ImageIcon image = null;
	private JLabel bifogadLbl = new JLabel("Bifogad bild:");

	private DefaultListModel<String> userArray = new DefaultListModel<String>();
	private JList<String> usersTableView = new JList<String>(userArray);

	private JPanel rightPnl = new JPanel();
	private JPanel messagePnl = new JPanel();

	private JLabel userLbl = new JLabel("Användarnamn:");
	private JTextField userTxtField = new JTextField();
	private JTextField messageField = new JTextField();
	private JTextField groupField = new JTextField();
	private JLabel groupLbl = new JLabel("<< Mottagare");
	
	/**
	 * Konstruktor designar UI 
	 */
	public ClientUI() {
		isConnected = false;
		updateState();

		setLayout(null);
		setPreferredSize(new Dimension(860, 560));

		userLbl.setBounds(3, 0, 100, 35);
		add(userLbl);

		userTxtField.setBounds(105, 0, 200, 35);
		add(userTxtField);

		login.setBounds(310, 0, 90, 35);
		add(login);
		login.addActionListener(this);

		logout.setBounds(405, 0, 90, 35);
		add(logout);
		logout.addActionListener(this);

		bifogadLbl.setBounds(3, 448, 520, 20);
		bifogadLbl.setFont(new Font("Helvetica", Font.PLAIN, 11));
		add(bifogadLbl);

		messageField.setBounds(0, 510, 520, 35);
		add(messageField);

		send.setBounds(565, 510, 90, 35);
		send.addActionListener(this);
		add(send);

		groupField.setBounds(0, 475, 280, 35);
		add(groupField);
		groupLbl.setBounds(290, 475, 120, 35);
		add(groupLbl);

		TitledBorder title;
		title = BorderFactory.createTitledBorder("Online nu!");
		title.setTitleJustification(TitledBorder.CENTER);

		rightPnl.setBounds(660, 0, 200, 500);
		rightPnl.setBorder(title);
		rightPnl.setBackground(Color.WHITE);
		add(rightPnl);
		usersTableView.setBounds(0, 0, 200, 500);
		rightPnl.add(usersTableView);

		TitledBorder titleMsg;
		titleMsg = BorderFactory.createTitledBorder("Meddelande:");

		messagePnl.setBounds(0, 45, 620, 400);
		;
		messagePnl.setBackground(Color.WHITE);
		add(messagePnl);

		document = new DefaultStyledDocument();
		imgStyle = document.addStyle("imgStyle", null);

		chatLbl = new JTextPane(document);
		chatLbl.setAutoscrolls(true);
		chatLbl.setBounds(0, 0, 620, 400);
		chatLbl.setCaretPosition(document.getLength());

		JScrollPane scroll = new JScrollPane(chatLbl,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(600, 385));
		scroll.setViewportView(chatLbl);
		scroll.setBorder(titleMsg);
		scroll.setBounds(0, 0, 600, 385);

		messagePnl.add(scroll);

	}
	
	/**
	 * Metod som kollar vilket läge användare befinner sig i.
	 * Uppdateringar för UI sker beroende på isConnected eller ej
	 */
	public void updateState() {
		if (isConnected == false) {
			send.setEnabled(false);
			login.setEnabled(true);
			messageField.setEditable(false);
			userTxtField.setEditable(true);
		} else {
			send.setEnabled(true);
			login.setEnabled(false);
			messageField.setEditable(true);
			userTxtField.setEditable(false);
		}
	}
	
	/**
	 * Tar emot Meddelande och bild för att printa ut i documents 
	 * @param msg meddelande
	 * @param image bild
	 */
	public void append(String msg, ImageIcon image) {

		try {
			document.insertString(document.getLength(), msg, null);
	
			if (image != null) {

				// Justering av storlek på bild
				Image img = image.getImage();
				Image newimg = img.getScaledInstance(320, 210,
						java.awt.Image.SCALE_SMOOTH);
				image = new ImageIcon(newimg);
				
				// Sätt bild till document med Style
				StyleConstants.setIcon(imgStyle, image);
				document.insertString(document.getLength(), "ignored", imgStyle);
				document.insertString(document.getLength(), "\n", null);
				image = null;

			}

		} catch (BadLocationException e) {
			System.out.print("Något gick fel med documents");
		}
	}
	
	/**
	 * Metoden printar ut privat meddelanden mellan användare
	 * @param msg meddelande
	 * @param image bild
	 * @param recipient mottagare
	 * @param sender avsändare
	 */
	public void privateAppend(String msg, ImageIcon image, String recipient, String sender){

		if(myUsername.equals(recipient)){
			append(msg, image);
		}
		
		if(myUsername.equals(sender)){
			append(msg, image);
		}
	}	

	
	/**
	 * Updates list of online users
	 * @param arr Array som skickas in
	 */
	public void updateUsers(ArrayList<String> arr) {
		userArray.clear();
		for (String elem : arr) {
			userArray.addElement(elem);
		}
	}
	
	/**
	 * ActionListener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == login) {
			String userName = userTxtField.getText();
			myUsername = userTxtField.getText();

			client = new Client(3540, userName, this);
			if (!client.start())
				return;
			isConnected = true; // Online
			userTxtField.setText("");
			updateState(); 
			client.sendMessage(new Message(Message.USER, userName, recipients, myUsername));
		}

		if (e.getSource() == send) {
			if (isConnected) {
			
				String str = groupField.getText();
				recipients = str.split( "," );
				

				client.sendMessage(new Message(Message.MESSAGE,
					messageField.getText(), recipients, myUsername));
				messageField.setText("");
				image = null;
				bifogadLbl.setText("Bifogad bild:");
				recipient = "";
				usersTableView.clearSelection();
				return;		
			}
		}

		if (e.getSource() == logout) {
			client.sendMessage(new Message(Message.LOGOUT, "", recipients, myUsername));
			isConnected = false;
			updateState();
		}

	}
	
	public String getUsername() {
		return myUsername;
	}

}
