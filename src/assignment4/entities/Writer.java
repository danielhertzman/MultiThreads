package assignment4.entities;

import assignment4.buffers.BoundedBuffer;

import java.util.List;

/**
 * Created by Daniel Hertzman-Ericson on 2016-01-17.
 */
public class Writer implements Runnable {

    private BoundedBuffer buffer;
    private List<String> strList;

    public Writer(BoundedBuffer buffer, List<String> strList) {
        this.buffer = buffer;
        this.strList = strList;
    }

    public void run() {

        for (String in : strList) {

            try {
                buffer.write(in);
            } catch (InterruptedException e) {}
        }
    }

}
