package com.gmail.sungmin0511a.etc;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class ImageStorage implements ImageObserver {
	static private ImageStorage mySelf;
	
	static public ImageStorage getImageStorage() {
		if (mySelf == null)
			mySelf = new ImageStorage();
		return mySelf;
	}
	
	HashSet<ImageLocker> images;
	ArrayList<ImageLoadListener> loadListeners;
	
	private ImageStorage() {
		images = new HashSet<ImageLocker>();
		loadListeners = new ArrayList<ImageLoadListener>();
		searchAndRegisterImage(new File("./img/"));
	}
	
	public void addListener(ImageLoadListener imageLoadListener) {
		loadListeners.add(imageLoadListener);
	}
	
	public ImageLocker findLockerWithImage(Image img) {
		Object imageLockers[];
		synchronized (images) {
			imageLockers = images.toArray();
		}
		// XXX
		for (Object object : imageLockers) {
			if (object instanceof ImageLocker) {
				ImageLocker imageLocker = (ImageLocker) object;
				if (imageLocker.getImage().equals(img))
					return imageLocker;
			}
		}
		return null;
	}
	
	synchronized public ImageLocker findLockerWithSkin(String skin) {
		for (ImageLocker imageLocker : images) {
			if (imageLocker.nameCheck(skin))
				return imageLocker;
		}
		return null;
	}
	
	public Image image(String skin) {
		ImageLocker imageLocker = findLockerWithSkin(skin);
		if (imageLocker == null)
			return null;
		else
			return imageLocker.getImage();
	}
	
	@Override
	public boolean imageUpdate(Image img, int flags, int x, int y, int w, int h) {
		boolean result = ((flags & (ALLBITS | ERROR)) == 0);
		try {
			if (result)
				findLockerWithImage(img).complete = true;
		} catch (NullPointerException ne) {
			ne.printStackTrace();
		}
		Iterator<ImageLoadListener> iterator = loadListeners.iterator();
		double complete = isLoadComplete();
		if (complete == 1)
			while (iterator.hasNext())
				iterator.next().complete();
		else
			while (iterator.hasNext())
				iterator.next().loading(complete);
		return result;
	}
	
	public boolean isImageLoaded(Image img) {
		return findLockerWithImage(img).complete;
	}
	
	public boolean isImageLoaded(String skin) {
		ImageLocker imageLocker = findLockerWithSkin(skin);
		if (imageLocker != null)
			return findLockerWithSkin(skin).complete;
		else
			return false;
	}
	
	public double isLoadComplete() {
		if (images.size() == 0) {
			return 1;
		}
		Object imageLockers[];
		synchronized (images) {
			imageLockers = images.toArray();
			// XXX
		}
		int counter = 0;
		for (Object object : imageLockers) {
			if (object instanceof ImageLocker) {
				ImageLocker imageLocker = (ImageLocker) object;
				if (imageLocker.complete)
					counter++;
			}
		}
		//		Iterator<ImageLocker> iterator = images.iterator();
		//		while (iterator.hasNext()) {
		//			ImageLocker imageLocker = iterator.next();
		//			if (imageLocker.complete)
		//				counter++;
		//		}
		return (double) counter / images.size();
	}
	
	public boolean registerImage(File file) throws ImageNotExistenceException {
		if (file.isFile()) {
			String name = file.getName();
			if (findLockerWithSkin(name) == null) {
				Image image = Toolkit.getDefaultToolkit().getImage(file.getPath());
				Toolkit.getDefaultToolkit().prepareImage(image, -1, -1, this);
				images.add(new ImageLocker(image, name));
				return true;
			} else
				return false;
		} else {
			throw new ImageNotExistenceException(file.getName());
		}
	}
	
	public void registerImage(Image image) {
		if (findLockerWithImage(image) == null)
			images.add(new ImageLocker(image, null));
	}
	
	public void removeListener(ImageLoadListener imageLoadListener) {
		loadListeners.remove(imageLoadListener);
	}
	
	private void searchAndRegisterImage(File dir) {
		File[] fileList = dir.listFiles();
		try {
			for (File tempFile : fileList) {
				if (tempFile.isFile()) {
					try {
						registerImage(new File(tempFile.getCanonicalPath()));
					} catch (ImageNotExistenceException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (tempFile.isDirectory()) {
					try {
						searchAndRegisterImage(new File(tempFile.getCanonicalPath().toString()));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (NullPointerException e) {
		}
	}
}

class ImageLocker {
	boolean complete;
	Image image;
	String skin;
	
	public ImageLocker(Image image, String skin) {
		super();
		this.image = image;
		this.skin = skin;
	}
	
	public Image getImage() {
		return image;
	}
	
	public boolean nameCheck(String skin) {
		if (this.skin.equals(skin))
			return true;
		else
			return false;
	}
	
	public void setSkin(String skin) {
		this.skin = skin;
	}
}

class ImageNotExistenceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1094138151874635046L;
	String skin;
	
	ImageNotExistenceException(String skin) {
		this.skin = skin;
	}
	
	@Override
	public String getMessage() {
		return skin + "을 찾을 수 없습니다";
	}
	
	@Override
	public void printStackTrace() {
		System.out.println(skin + "을 찾을 수 없습니다");
	}
}