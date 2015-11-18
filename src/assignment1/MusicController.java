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
	private Clip clip;
	
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
		soundFile = new File(path);
		
		/*
		 * Creats an audio stream that plays .wav-files
		 */
		try {
			clip = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
			clip.open(ais);
			clip.start();
		} catch (RuntimeException | LineUnavailableException | IOException | UnsupportedAudioFileException e) {}
				
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
	
	public void stop() {
		
		if (clip.isActive()) {
			clip.stop();
		}
		
	}

}
