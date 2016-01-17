package assignment4.buffers;


/**
 * Created by Daniel Hertzman-Ericson on 2016-01-17.
 */
public class BoundedBuffer {

    private String[] strArray;
    private BufferStatus[] status;
    private static final int MAX = 10;
    private int writePos;
    private int readPos;
    private int findPos;
    private String findStr;
    private String replaceStr;
    private int start;
    private int nbrOfReplace;
    private boolean notify;

    public BoundedBuffer(String findStr, String replaceStr) {
        this.findStr = findStr;
        this.replaceStr = replaceStr;
        strArray = new String[MAX];
        status = new BufferStatus[MAX];

        for (int i = 0; i < status.length-1; i++) {
            status[i] = BufferStatus.EMPTY;
        }
    }

    public void modify() throws InterruptedException {

        while (status[findPos] != BufferStatus.NEW) {
            wait();
        }

        if (strArray[findPos].contains(findStr)) {
            String s = strArray[findPos].replaceAll(findStr, replaceStr);
            strArray[findPos] = s;
        }
        status[findPos] = BufferStatus.CHECKED;
        notifyAll();

    }

    public synchronized void write(String in) throws InterruptedException {

        while (status[findPos] != BufferStatus.EMPTY) {
            wait();
        }

        strArray[writePos] = in;
        status[writePos] = BufferStatus.NEW;
        notifyAll();
    }

    public synchronized String read() throws InterruptedException {

        while (status[findPos] != BufferStatus.CHECKED) {
            wait();
        }

        String s = strArray[readPos];
        status[readPos] = BufferStatus.EMPTY;
        notifyAll();
        return s;
    }
}
