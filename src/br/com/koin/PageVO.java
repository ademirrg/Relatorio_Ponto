package br.com.koin;

public class PageVO {
	public static String user = "";
	public static String pass = "";
	public static String saldoBH = "0";
	public static boolean loginOK = false;
	
	public static String getUser() {
		return user;
	}
	public static void setUser(String user) {
		PageVO.user = user;
	}
	public static String getPass() {
		return pass;
	}
	public static void setPass(String pass) {
		PageVO.pass = pass;
	}
	public static String getSaldoBH() {
		return saldoBH;
	}
	public static void setSaldoBH(String saldoBH) {
		PageVO.saldoBH = saldoBH;
	}
	public static boolean getLoginOK() {
		return loginOK;
	}
	public static void setLoginOK(boolean loginOK) {
		PageVO.loginOK = loginOK;
	}
}
