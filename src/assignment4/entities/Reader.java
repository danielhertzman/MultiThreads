package assignment4.entities;

import assignment4.buffers.BoundedBuffer;
import java.util.*;

/**
 * Created by Daniel Hertzman-Ericson on 2016-01-17.
 */
public class Reader implements Runnable {

    private BoundedBuffer buffer;
    private ArrayList<String> strList = new ArrayList<String>();
    private int count;

    public Reader(BoundedBuffer buffer, int count) {
        this.buffer = buffer;
        this.count = count;
    }

    public void run() {

        try {
            while (strList.size() < count) {
                strList.add(buffer.read());
            }
        } catch (InterruptedException e){}
    }

    public ArrayList<String> getList() {
        return strList;
    }
}
