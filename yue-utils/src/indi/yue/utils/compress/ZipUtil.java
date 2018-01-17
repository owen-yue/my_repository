package indi.yue.utils.compress;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipUtil {
	@SuppressWarnings("rawtypes")
	public static List<String> getText(String path) throws IOException{
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		ZipFile zipFile = null;
		List<String> messages = null;
			try{
				zipFile = new ZipFile(path);
				Enumeration e = zipFile.entries();
				messages = new ArrayList<String>();
				while(e.hasMoreElements()){
					try{
						ZipEntry zipEntry = (ZipEntry) e.nextElement();
						inputStream = zipFile.getInputStream(zipEntry);
						inputStreamReader = new InputStreamReader(inputStream);
						StringBuffer buffer = new StringBuffer();
						char [] cArr =new char[2048];
						while (inputStreamReader.read(cArr)!=-1) {
							buffer.append(cArr);
						}
						
						messages.add(buffer.toString().trim());
					}finally {
						inputStreamReader.close();
						inputStream.close();
					}
				}
			}finally {
				if(zipFile!=null) zipFile.close();
			}
		return messages;
	}
}
