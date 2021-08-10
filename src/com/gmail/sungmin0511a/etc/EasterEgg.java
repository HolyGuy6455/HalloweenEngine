package com.gmail.sungmin0511a.etc;

/** 이스터에그들을 모아놓은 클래스입니다. <p> 게임이나 프로그램에만 이스터에그 있으란법 있습니까?
 * 라이브러리형 게임엔진에도 이스터에그는 있을 수 있죠!
 * 
 * @author 신성민 */
public class EasterEgg {
	static double version;
	static {
		version = 2.0;
	}
	
	public static String getInfomation() {
		StringBuffer result = new StringBuffer();
		result.append("HalloweenEngine ver");
		result.append(version);
		result.append("\nmade by : Holy_Guy_ (신성민)\n");
		result.append("20140105~\n");
		return result.toString();
	}
	
	public static int toLife_theUniverse_andEverything() {
		return 42;
	}
	
	public static void printRabbit() {
		StringBuffer result = new StringBuffer();
		result.append("\n   ()     ()");
		result.append("\n ( ㅡ ㅅ ㅡ )");
		result.append("\n    (      )");
		System.out.println(result);
	}
	
	private EasterEgg() {
	}
}
