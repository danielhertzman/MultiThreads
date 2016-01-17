package assignment4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by danielhertzman-ericson on 2016-01-13.
 */
public class MainForm extends JPanel {

    private JTextField find = new JTextField();
    private JTextField replace = new JTextField();
    private JTextArea mainArea = new JTextArea();
    private JPanel centerPnl = new JPanel();
    private JPanel northPnl = new JPanel();
    private JButton copy = new JButton("Copy to destination");
    private JButton clear = new JButton("Clear");
    private JScrollBar scroll = new JScrollBar();

    public MainForm() {

        setPreferredSize(new Dimension(400,400));
        setLayout(new GridLayout(1,1));

        northPnl.setLayout(new GridLayout(1,2));
        northPnl.add(find);
        northPnl.add(copy);
        northPnl.add(replace);
        northPnl.add(clear);

        add(northPnl);

        centerPnl.add(mainArea);

        add(centerPnl);
        scroll.add(this);

    }

    //-------------------------------------------------------------------

    public static void main(String...args) {
        JFrame frame = new JFrame("djskfhs");
        frame.add(new MainForm());
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
