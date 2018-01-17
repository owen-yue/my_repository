package indi.yue.utils.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {
	public static List<String> getRow(String csvPath) throws IOException{
		
		File file = new File(csvPath);
		FileInputStream fileInputStream = new FileInputStream(file);
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"GBK");
		BufferedReader reader = new BufferedReader(inputStreamReader);
		String message;
		StringBuffer msgTeemp=new StringBuffer();
		List<String> results=new ArrayList<String>();
		boolean flag=false;
		while ((message = reader.readLine())!=null) {
				
				if(message.contains("\"")&&(CSVUtil.containsNum(message,'\"')%2)==1){
					
					flag=!flag;
				}
				if(flag){
					msgTeemp.append(message+"\r\n");
				}else {
					msgTeemp.append(message+"\r\n");
					results.add(msgTeemp.toString().trim());
					msgTeemp=new StringBuffer();
				}
		}
		try{
			
		}finally {
			reader.close();
			inputStreamReader.close();
			fileInputStream.close();
		}
		return results;
	}
	
	private static int containsNum(String s,char c){
		int num = 0;
        // 循环遍历每个字符，判断是否是字符 a ，如果是，累加次数
       for (int i=0;i<s.length();i++)
       {
           // 获取每个字符，判断是否是字符a
           if (s.charAt(i)==c) {
               // 累加统计次数
               num++; 
           }
       }
       return num;
   }
	
}
