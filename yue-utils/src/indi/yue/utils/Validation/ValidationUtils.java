package indi.yue.utils.Validation;

import java.util.List;

public class ValidationUtils {
	public static boolean isEmpty(String s){
		if(null==s||"".equals(s)) return true ;
		return false;
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(List l){
		if(null==l||l.size()==0) return true ;
		return false;
	}
	
}
