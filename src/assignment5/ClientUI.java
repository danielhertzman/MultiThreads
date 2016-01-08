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

public class ClientUI extends JPanel {
	
	private JTextField insert = new JTextField();
	private JTextPane textPane = new JTextPane();
	private JButton send = new JButton("Send");
	private JPanel subPnl = new JPanel();
	private StyledDocument doc = new DefaultStyledDocument();

	
	public ClientUI() {
		setPreferredSize(new Dimension(400,400));
		setLayout(new BorderLayout());
		subPnl.setLayout(new GridLayout(1, 2));
		
		textPane.setEditable(false);
		textPane.setDocument(doc);
		textPane.setBorder(new LineBorder(Color.BLACK));
		
		subPnl.add(insert);
		subPnl.add(send);
		
		add(textPane, BorderLayout.CENTER);
		add(subPnl, BorderLayout.SOUTH);
		
		send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					doc.insertString(doc.getLength(), insert.getText()+"\n", null);
					
				} catch (BadLocationException e1) {}
			}
		});
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Awesome chat");
		frame.add(new ClientUI());
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

}
