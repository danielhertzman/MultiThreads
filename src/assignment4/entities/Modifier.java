package assignment4.entities;


import assignment4.buffers.BoundedBuffer;

/**
 * Created by Daniel Hertzman-Ericson on 2016-01-17.
 */
public class Modifier implements Runnable {

    private BoundedBuffer buffer;
    private int count;

    public Modifier(BoundedBuffer buffer, String[] txtIn) {
        this.buffer = buffer;
        count = txtIn.length;
    }

    public void run() {

        for (int i = 0; i < count; i++) {

            try {
                buffer.modify();
            } catch (InterruptedException e) {}
        }
    }
}
