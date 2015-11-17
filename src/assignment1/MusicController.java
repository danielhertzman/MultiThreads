package assignment1;

import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.sound.sampled.*;

/**
 * Implements the Runnable interface and creates
 * a small inside media player that allows you to
 * play .waw-files.
 * @author danielhertzman-ericson
 *
 */
public class MusicController implements Runnable {
	
	private String description;
	private String path;
	private File soundFile;
	private AudioInputStream audioStream;
	private AudioFormat audioFormat;
	private SourceDataLine sourceLine;
	private boolean stop;
	
	public MusicController() {
		selectFile();
	}
	
	/**
	 * Open the directory manager that allows you to choose a file
	 */
	private void selectFile() {
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("wav", "WAV");
		fc.setFileFilter(filter);
		fc.showOpenDialog(null);
		path = fc.getSelectedFile().getAbsolutePath();
		description = fc.getSelectedFile().getName();
	}
	

	@Override
	public void run() {
		stop = false;
		soundFile = new File(path);

		/*
		 * Creats an audio stream that plays .wav-files
		 */
		while (!stop) {

			try {
				
				Thread.sleep(0);
				
				try {
					audioStream = AudioSystem.getAudioInputStream(soundFile);
				} catch (Exception e) {}

				audioFormat = audioStream.getFormat();

				DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
				try {
					sourceLine = (SourceDataLine) AudioSystem.getLine(info);
					sourceLine.open(audioFormat);
				} catch (LineUnavailableException e) {
					e.printStackTrace();
					System.exit(1);
				} catch (Exception e) {}

				sourceLine.start();

				int nBytesRead = 0;
				byte[] abData = new byte[128000]; // 128 kbps 
				while (nBytesRead != -1) {

					try {

						nBytesRead = audioStream.read(abData, 0, abData.length);

					} catch (IOException e) {}

					if (nBytesRead >= 0) {
						@SuppressWarnings("unused")
						int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
					}
				}

				sourceLine.drain();
				sourceLine.close();
				
			} catch (InterruptedException e) { stop = true; }
		}
	}

	/**
	 * Returns the songs file path
	 * @return file path
	 */
	public String getFilePath() {
		return path;
	}
	
	/**
	 * Returns the description of the song
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Closes the source stream
	 */
	public void stopMusic() {
		sourceLine.stop();
	}

}
