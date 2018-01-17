package indi.yue.utils.ali.datahub;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.aliyun.datahub.DatahubClient;
import com.aliyun.datahub.common.data.RecordSchema;
import com.aliyun.datahub.model.ListShardResult;
import com.aliyun.datahub.model.RecordEntry;
import com.aliyun.datahub.model.ShardEntry;
import com.aliyun.datahub.model.ShardState;

import indi.yue.utils.date.DateUtil;

public class DatahubUtils {
	
	private static Map<String, List<String>> topicFieldsTemp=new Hashtable<String, List<String>>();
	
	@SuppressWarnings("rawtypes")
	public static RecordEntry entity2RecordEntry(RecordSchema schema,Object obj,String shardId) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, DatahubException{
		if(obj==null) return null;
		RecordEntry recordEntry = new RecordEntry(schema);
		String topicName=obj.getClass().getSimpleName().toLowerCase();
		
		
		
		if(topicFieldsTemp.get(topicName)==null){
			List<String> topicFields=new ArrayList<String>();
			for (com.aliyun.datahub.common.data.Field field : recordEntry.getFields()) {
				if("part_by_day".equals(field.getName())){
					continue;
				}
				topicFields.add(field.getName().toLowerCase());
			}
			topicFieldsTemp.put(topicName, topicFields);
		}
		
		Map<String, Object> objMap = new HashMap<String, Object>();
		for(Field field : obj.getClass().getDeclaredFields()){
			if("serialVersionUID".equals(field.getName())){
				continue;
			}
			field.setAccessible(true);
			Object objTemp = field.get(obj);
			objMap.put(field.getName().toLowerCase(), objTemp);
		}
		
		
		List<String> beanFields=new ArrayList<String>();
		
		for (com.aliyun.datahub.common.data.Field field: recordEntry.getFields()) {
			String fieldName = field.getName();
			if("part_by_day".equals(fieldName)){
				recordEntry.setString(fieldName, DateUtil.DateToString(new Date(), "yyyyMMdd"));
			}else{
				Object objTemp = objMap.get(fieldName);
				if(objTemp==null){ 
					recordEntry.setString(fieldName, null); 
					beanFields.add(fieldName.toLowerCase());
					continue;
				}
				else if(objTemp instanceof String){ 
					recordEntry.setString(fieldName, (String)objTemp); 
					beanFields.add(fieldName.toLowerCase());
					continue;
				}
				else if(objTemp instanceof Long){
					recordEntry.setBigint(fieldName, (Long)objTemp);
					beanFields.add(fieldName.toLowerCase());
					continue;
				}
				else if(objTemp instanceof Integer){
					recordEntry.setBigint(fieldName, Long.valueOf((Integer)objTemp));
					beanFields.add(fieldName.toLowerCase());
					continue;
				}
				else if(objTemp instanceof Short){
					recordEntry.setBigint(fieldName, Long.valueOf((Short)objTemp));
					beanFields.add(fieldName.toLowerCase());
					continue;
				}
				else if(objTemp instanceof Boolean){
					recordEntry.setBoolean(fieldName, (Boolean)objTemp);
					beanFields.add(fieldName.toLowerCase());
					continue;
				}
				else if(objTemp instanceof BigDecimal){
					recordEntry.setDecimal(fieldName, (BigDecimal)objTemp);
					beanFields.add(fieldName.toLowerCase());
					continue;
				}
				else if(objTemp instanceof Double){
					recordEntry.setDouble(fieldName, (Double)objTemp);
					beanFields.add(fieldName.toLowerCase());
					continue;
				}
				else if(objTemp instanceof Float){
					recordEntry.setDouble(fieldName, Double.valueOf((Float)objTemp));
					beanFields.add(fieldName.toLowerCase());
					continue;
				}
				else if(objTemp instanceof Date){
					recordEntry.setTimeStamp(fieldName, ((Date)objTemp).getTime());
					beanFields.add(fieldName.toLowerCase());
					continue;
				}
				else if(objTemp instanceof List){
					List list = (List) objTemp;
					if(list.size()>0&&list.get(0) instanceof String){
						StringBuffer s = new StringBuffer();
						for (int i =0;i<list.size();i++) {
							s.append(list.get(i));
							if(i<list.size()-1){
								s.append("|");
							}
						}
						recordEntry.setString(fieldName, s.toString());
						beanFields.add(fieldName.toLowerCase());
					}
					continue;
				}
				throw new TypeNotNupportException(fieldName+":Type not support.");
			}
		}
		
		/*if(!ValidationUtils.isEmpty(FieldUtils.getDiffrent(topicFieldsTemp.get(topicName), beanFields))||!ValidationUtils.isEmpty(FieldUtils.getDiffrent(beanFields,topicFieldsTemp.get(topicName)))){
			throw new DatahubException("The bean and the topic field are unequal.\ttopic"+obj.getClass().getName().toLowerCase()+".");
		}*/
		
		if(shardId!=null) recordEntry.setShardId(shardId);
		
		return recordEntry;
	}
	
	public static RecordEntry entity2RecordEntry(RecordSchema schema,Object obj) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, DatahubException{
		return DatahubUtils.entity2RecordEntry(schema,obj, null);
	}
	
	public static List<String> getActiveShardid(DatahubClient client,String projectName,String topicName){
		List<String> result = new ArrayList<String>();
		ListShardResult listShardResult = client.listShard(projectName, topicName);
		List<ShardEntry> entries = listShardResult.getShards();
		for (ShardEntry shardEntry : entries) {
			if(shardEntry.getState()==ShardState.ACTIVE){
				result.add(shardEntry.getShardId());
			}
		}
		return result;
		
	}
	
}
