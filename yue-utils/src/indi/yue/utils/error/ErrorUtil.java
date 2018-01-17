package indi.yue.utils.error;

public class ErrorUtil {

	public static String getExceptionAllinformation(Exception ex){
		String sOut = "";
		StackTraceElement[] trace = ex.getStackTrace();
		sOut+=ex.getClass().getName()+":"+ex.getMessage()+"\n";
		for (StackTraceElement s : trace) {
			sOut += "\tat " + s + "\r\n";
		}
		return sOut;
	}
}
