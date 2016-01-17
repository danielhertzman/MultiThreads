package assignment4.entities;

import assignment4.MainForm;
import assignment4.buffers.BoundedBuffer;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Daniel Hertzman-Ericson on 2016-01-17.
 */
public class Controller {

    private Reader reader;
    private Writer writer;
    private Modifier modifier;
    private BoundedBuffer buffer;
    private MainForm gui;
    private String txtFile;

    public Controller(MainForm gui) {
        this.gui = gui;
    }

    public void open() {

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Txt", "txt");
        fileChooser.setFileFilter(filter);

        int option = fileChooser.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            txtFile = file.getAbsolutePath();
            readFile();
        }
    }

    public void readFile() {

        try {
            BufferedReader br = new BufferedReader(new FileReader(txtFile));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String total = sb.toString();
//            gui.put(total);
        } catch (IOException e) {}

    }

    public void start() {
        Thread t1, t2, t3;
        t1 = new Thread(reader);
        t2 = new Thread(writer);
        t3 = new Thread(modifier);
        t1.start();
        t2.start();
        t3.start();
    }


}
