package test;

import javax.swing.JFrame;

import com.gmail.sungmin0511a.major.Halloween;

public class Test {
	public static void main(String[] args) {
		new Test();
	}
	
	JFrame frame;
	Halloween halloween;
	
	public Test() {
		halloween = new Halloween();
		frame = halloween.getFrame();
	}
}
