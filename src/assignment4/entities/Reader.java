package assignment4.entities;

import assignment4.buffers.BoundedBuffer;

/**
 * Created by Daniel Hertzman-Ericson on 2016-01-17.
 */
public class Reader implements Runnable {

    private BoundedBuffer buffer;

    public Reader(BoundedBuffer buffer) {
        this.buffer = buffer;
    }

    public void run() {


    }
}
