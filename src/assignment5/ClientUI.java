package assignment5;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientUI extends JPanel implements ActionListener {
	
	private JTextField insert = new JTextField();
	private JTextPane textPane = new JTextPane();
	private JButton send = new JButton("Send");
	private JButton connect = new JButton("Connect");
	private JPanel subPnl = new JPanel();
	private StyledDocument doc = new DefaultStyledDocument();

	
	public ClientUI() {
		
		setPreferredSize(new Dimension(400,400));
		setLayout(new BorderLayout());
		subPnl.setLayout(new GridLayout(1, 3));
		
		textPane.setEditable(false);
		textPane.setDocument(doc);
		textPane.setBorder(new LineBorder(Color.BLACK));
		
		subPnl.add(insert);
		subPnl.add(send);
		subPnl.add(connect);
		
		add(textPane, BorderLayout.CENTER);
		add(subPnl, BorderLayout.SOUTH);
		
		send.setEnabled(false);
		send.addActionListener(this);
		connect.addActionListener(this);
		
	}
	public void append(String message, String username) {
		try {
			doc.insertString(doc.getLength(), username + " wrote: " +message+"\n", null);
		} catch (BadLocationException e) {}
	}
	
	public String getMessage() {
		return insert.getText();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == send) {
			try {
				doc.insertString(doc.getLength(), "You wrote : " + insert.getText()+"\n", null);
			} catch (BadLocationException e1) {}
		}
		
		if (e.getSource() == connect) {
			Client client = new Client(this, "Blubb");
			Thread t = new Thread(client);
			t.start();
			send.setEnabled(true);
		}
		
	}
	
}
