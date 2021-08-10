/**
 * 
 */
package com.gmail.sungmin0511a.major;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

import com.gmail.sungmin0511a.costume.Costume;
import com.gmail.sungmin0511a.drawAbles.Party;
import com.gmail.sungmin0511a.etc.ImageStorage;

/** @author �ż��� */
/** @author �ż��� */
public class Halloween {
	static boolean play;
	
	/** @return */
	public static boolean playing() {
		return play;
	}
	
	Color backgroundColor;
	JComponent component;
	Party currentParty;
	Party cursorIcon;
	Thread drawThread;
	JFrame frm;
	Point mousePosition;
	Timer witchTimer;
	private Dimension size;

	/**
	 * 
	 */
	public Halloween() {							// ������
		super();
		size = new Dimension(640, 480);
		currentParty = new Party("Stage");
		play = true;
		cursorIcon = new Party("CursorIcon");
		mousePosition = new Point();
		ImageStorage.getImageStorage();
		backgroundColor = Color.white;
		component = new JComponent() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				//				long start = System.nanoTime();
				Image image = createImage(size.width, size.height);
				Graphics2D g2 = (Graphics2D) image.getGraphics();
				//				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(backgroundColor);
				g2.fillRect(0, 0, (int) size.getWidth(), (int) size.getHeight());
				;
				Iterator<Costume> iterator = currentParty.getCostumes().iterator();
				while (iterator.hasNext())
					iterator.next().dressUp(g2);
				currentParty.draw(g2);
				iterator = currentParty.getCostumes().iterator();
				cursorIcon.draw(g2);
				g.drawImage(image, 0, 0, null);
				//				long end = System.nanoTime();
				//				System.out.println((double) (end - start) / 1000000);
			}
		};
		component.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				e.translatePoint(mousePosition.x - e.getX(), mousePosition.y - e.getY());
				currentParty.processMouseEvent(e, MouseEvent.MOUSE_CLICKED);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				e.translatePoint(mousePosition.x - e.getX(), mousePosition.y - e.getY());
				currentParty.processMouseEvent(e, MouseEvent.MOUSE_ENTERED);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				e.translatePoint(mousePosition.x - e.getX(), mousePosition.y - e.getY());
				currentParty.processMouseEvent(e, MouseEvent.MOUSE_EXITED);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				e.translatePoint(mousePosition.x - e.getX(), mousePosition.y - e.getY());
				currentParty.processMouseEvent(e, MouseEvent.MOUSE_PRESSED);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				e.translatePoint(mousePosition.x - e.getX(), mousePosition.y - e.getY());
				currentParty.processMouseEvent(e, MouseEvent.MOUSE_RELEASED);
			}
		});
		component.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (component != null) {
					Point point = component.getMousePosition();
					if (mousePosition != null && point != null) {
						mousePosition.y = point.y;
						mousePosition.x = point.x;
						e.translatePoint(mousePosition.x - e.getX(), mousePosition.y - e.getY());
					}
				}
				currentParty.processMouseEvent(e, MouseEvent.MOUSE_DRAGGED);
			}
			
			@Override
			public void mouseMoved(MouseEvent e) {
				if (component != null) {
					Point point = component.getMousePosition();
					if (mousePosition != null && point != null) {
						mousePosition.y = point.y;
						mousePosition.x = point.x;
						e.translatePoint(mousePosition.x - e.getX(), mousePosition.y - e.getY());
					}
				}
				currentParty.processMouseEvent(e, MouseEvent.MOUSE_MOVED);
			}
		});
		component.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				currentParty.processKeyEvent(e, KeyEvent.KEY_PRESSED);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				currentParty.processKeyEvent(e, KeyEvent.KEY_RELEASED);
			}
			
			@Override
			public void keyTyped(KeyEvent e) {
				currentParty.processKeyEvent(e, KeyEvent.KEY_TYPED);
			}
		});
		component.setSize(size);
		component.requestFocus();
		;
		{
			drawThread = new Thread() {
				@Override
				public void run() {
					super.run();
					while (true) {
						component.repaint();
						synchronized (drawThread) {
							try {
								drawThread.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			};
			drawThread.start();
		}
		{
			witchTimer = new Timer(10, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (play)
						currentParty.locate();
					synchronized (drawThread) {
						drawThread.notify();
					}
				}
			});
			new Thread(new Runnable() {
				@Override
				public void run() {
					witchTimer.start();
				}
			}).start();
		}
	}
	
	public void addMouseListener(MouseListener mouseListener) {
		component.addMouseListener(mouseListener);
	}

	public void addMouseMotionListener(MouseMotionListener mouseMotionListener) {
		component.addMouseMotionListener(mouseMotionListener);
	}
	
	public void addMouseWheelListener(MouseWheelListener mouseWheelListener) {
		component.addMouseWheelListener(mouseWheelListener);
	}
	
	/** @return the backgroundColor */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/** @return the component */
	public JComponent getComponent() {
		return component;
	}
	
	/** @return the currentParty */
	public Party getCurrentParty() {
		return currentParty;
	}

	/** @return */
	public Party getCursorIcon() {
		return cursorIcon;
	}
	
	/** @return */
	public JFrame getFrame() {
		try {
			frm.add(component);
			return frm;
		} catch (NullPointerException npe) {
			frm = new JFrame();
			frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frm.setResizable(false);
			frm.setSize(size);
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			frm.setLocation((dimension.width - size.width) / 2,
					(dimension.height - size.height) / 2);
			frm.add(component);
			frm.setVisible(true);
			component.requestFocus();
			return frm;
		}
	}
	
	public Point getMousePosition() {
		return new Point(mousePosition.x, mousePosition.y);
	}
	
	/** @return the size */
	public Dimension getSize() {
		return size;
	}
	
	/**
	 * 
	 */
	public void playTheWorld() {
		play = true;
	}
	
	/** @param backgroundColor
	 *        the backgroundColor to set */
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	/** @param currentParty
	 *        the currentParty to set */
	public void setCurrentParty(Party currentParty) {
		this.currentParty = currentParty;
	}
	
	/** @param size
	 *        the size to set */
	public void setSize(Dimension size) {
		this.size = size;
		component.setSize(size);
		try {
			frm.setSize(size);
		} catch (NullPointerException npe) {
		}
	}
	
	public void setSize(int x, int y) {
		setSize(new Dimension(x, y));
	}
	
	public void setTime(int delay) {
		witchTimer.setDelay(delay);
	}

	/**
	 * 
	 */
	public void stopTheWorld() {
		play = false;
	}
}
