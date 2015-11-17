package assignment1;

import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.sound.sampled.*;

public class MusicController implements Runnable {
	
	private String description;
	private String path;
	private File soundFile;
	private AudioInputStream audioStream;
	private AudioFormat audioFormat;
	private SourceDataLine sourceLine;
	
	public MusicController() {
		selectFile();
	}
	
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
		
		soundFile = new File(path);

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
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		sourceLine.start();

		int nBytesRead = 0;
		byte[] abData = new byte[128000];
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
	}

	public String getFilePath() {
		return path;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void stopMusic() {
		sourceLine.stop();
	}

}
