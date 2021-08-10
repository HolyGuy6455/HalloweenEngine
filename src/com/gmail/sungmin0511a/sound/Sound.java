package com.gmail.sungmin0511a.sound;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.sound.sampled.*;

public class Sound extends Thread {
	protected boolean repeat;
	// TODO 이중 몇개는 protected로 전환해야함
	private AudioInputStream audioInputStream;
	private boolean done;
	private File file;
	private FloatControl floatControl;
	private AudioFormat format;
	private long frameLength;
	private SourceDataLine line;
	private boolean playing;
	private boolean running;
	
	public Sound(File file) {
		super();
		this.file = file;
		//		volume = (float) 1.0;
		playing = false;
		repeat = false;
		this.start();
	}

	public Sound(String path) {
		this(new File(path));
	}
	
	public void deleteSound() {
		running = false;
	}

	public long getFrameLength() {
		return frameLength;
	}
	
	public float getVolume() {
		return floatControl.getValue();
		//		return volume;
	}
	
	public boolean isDone() {
		return done;
	}

	public boolean isRepeat() {
		return repeat;
	}

	public void pause() {
		playing = false;
	}
	
	public void play() {
		if (done)
			return;
		playing = true;
		synchronized (this) {
			this.notify();
		}
	}
	
	public void reset() {
		if (audioInputStream != null) {
			try {
				audioInputStream.reset();
				done = false;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		super.run();
		running = true;
		format = null;
		BufferedInputStream bufferedInputStream;
		try {
			//			InputStream inputStream = getClass().getResourceAsStream("sound.wav");
			bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
			audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream);
			//			System.out.println(inputStream.markSupported());
			//			System.out.println(bufferedInputStream.markSupported());
			//			System.out.println(bufferedInputStream.markSupported());
			frameLength = audioInputStream.getFrameLength();
			format = audioInputStream.getFormat();
		} catch (UnsupportedAudioFileException ex) {
			ex.printStackTrace();
			return;
		} catch (IOException ex) {
			ex.printStackTrace();
			return;
		}
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
		if (AudioSystem.isLineSupported(info)) {
			try {
				audioInputStream.mark(audioInputStream.available());
				line = (SourceDataLine) AudioSystem.getLine(info);
				line.open(format);
				line.start();
				floatControl = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
				int read = 0;
				byte[] bytes = new byte[256]; // 256Bytes??
				while (running) {
					if (playing) {
						read = audioInputStream.read(bytes, 0, bytes.length);
						if (read > -1) {
							line.write(bytes, 0, read);
						} else {
							if (repeat) {
								reset();
							} else {
								done = true;
								playing = false;
							}
							//														audioInputStream.close();
							//							System.out.println(audioInputStream.markSupported());
						}
					} else {
						synchronized (this) {
							try {
								this.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
				;
				//				while (((read = audioInputStream.read(bytes, 0, bytes.length)) > -1)/* && playable*/) {
				//					if (volume == 0 /*|| pauseMode*/)
				//						floatControl.setValue(floatControl.getMinimum());
				//					else
				//						floatControl.setValue((floatControl.getMinimum() + 35) * (1.0f - volume)); // Reduce volume by 10 decibels.
				//					//		System.out.println("현재 실제 볼륨 : " + fc.getValue());
				//					line.write(bytes, 0, read);
				//				}
				line.flush();
				line.drain();
				line.stop();
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (LineUnavailableException ex) {
				ex.printStackTrace();
			}
		} else
			System.out.println("Liner Not Supported!");
		line.close();
	}
	
	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}

	public void setVloume(double volume) {
		setVloume((float) volume);
	}

	public void setVloume(float volume) {
		//		this.volume = volume;
		if (floatControl != null) {
			float f = (float) (((floatControl.getMaximum() - floatControl.getMinimum())
								* Math.pow(2, volume) / 2) + floatControl.getMinimum());
			//			System.out.println(floatControl.getMaximum());
			//			System.out.println(floatControl.getMinimum());
			//			float f = (float) (((floatControl.getMaximum() - floatControl.getMinimum()) * volume + floatControl
			//					.getMinimum()));
			//			System.out.println("v " + volume);
			//			System.out.println("p " + Math.pow(2, volume) / 2);
			//			System.out.println(f);
			if (f > floatControl.getMaximum())
				f = floatControl.getMaximum();
			else if (f < floatControl.getMinimum())
				f = floatControl.getMinimum();
			floatControl.setValue(f);
		}
	}
}
