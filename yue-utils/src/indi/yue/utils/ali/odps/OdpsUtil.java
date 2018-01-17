package indi.yue.utils.ali.odps;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aliyun.odps.Column;
import com.aliyun.odps.Instance;
import com.aliyun.odps.Odps;
import com.aliyun.odps.OdpsException;
import com.aliyun.odps.TableSchema;
import com.aliyun.odps.account.Account;
import com.aliyun.odps.account.AliyunAccount;
import com.aliyun.odps.data.Record;
import com.aliyun.odps.data.RecordReader;
import com.aliyun.odps.task.SQLTask;
import com.aliyun.odps.tunnel.TableTunnel;
import com.aliyun.odps.tunnel.TableTunnel.DownloadSession;
import com.aliyun.odps.tunnel.TunnelException;
import com.datatech.exception.NoDataException;

import indi.yue.dtcp.OdpsDataSource;
import indi.yue.utils.Validation.ValidationUtils;

public class OdpsUtil<T> {
	
	public static boolean excuteSql (OdpsDataSource odpsDataSource,String sql) throws OdpsException{
		boolean result = false;
		Account account = new AliyunAccount(odpsDataSource.getAccess_id(), odpsDataSource.getAccess_key());
	    Odps odps = new Odps(account);
	    odps.setEndpoint(odpsDataSource.getUrl());
	    odps.setDefaultProject(odpsDataSource.getProject_name());
			Instance instance = SQLTask.run(odps,sql);
		    instance.waitForSuccess();
		    result = true;
			return result;
	}
	
	public  List<T> tunnelDownload(OdpsDataSource odpsDataSource,String tablename ,Class<T> clazz) throws Exception{
		
		Account account = new AliyunAccount(odpsDataSource.getAccess_id(), odpsDataSource.getAccess_key());
        Odps odps = new Odps(account);
		TableTunnel tunnel = new TableTunnel(odps);
		tunnel.setEndpoint(odpsDataSource.getDt_url());
		List<T> result=new ArrayList<T>();
		try {
			DownloadSession downloadsession = tunnel.createDownloadSession(odpsDataSource.getProject_name(), tablename.trim());
			long count = downloadsession.getRecordCount();
			
			if(count<1){
				throw new NoDataException("odps no data!");
			}
			
			RecordReader reader = downloadsession.openRecordReader(0, count);
			Record record;
			TableSchema schema = downloadsession.getSchema();
			
			while ((record = reader.read()) != null) {
				T t= clazz.newInstance();
				for (int i = 0; i<schema.getColumns().size(); i++) {
					Column column = schema.getColumn(i);
					String columnName=column.getName();
//					String colValue = null;
					switch (column.getType()) {
					case BIGINT: {
						Long v = record.getBigint(i);
//						colValue = v == null ? "" : v.toString();
						if(v!=null) t.getClass().getMethod("set"+columnName.substring(0,1).toUpperCase() +columnName.substring(1, columnName.length()), Long.class).invoke(t, v);
						break;
					}
					case BOOLEAN: {
						Boolean v = record.getBoolean(i);
//						colValue = v == null ? "" : v.toString();
						if(v!=null) t.getClass().getMethod("set"+columnName.substring(0,1).toUpperCase() +columnName.substring(1, columnName.length()), Boolean.class).invoke(t, v);
						break;
					}
					case DATETIME: {
//						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date v = record.getDatetime(i);
//						colValue = v == null ? "" : sdf.format(v);
						if(v!=null) t.getClass().getMethod("set"+columnName.substring(0,1).toUpperCase() +columnName.substring(1, columnName.length()), Date.class).invoke(t, v);
						break;
					}
					case DOUBLE: {
						Double v = record.getDouble(i);
//						colValue = v == null ? "" : v.toString();
						if(v!=null) t.getClass().getMethod("set"+columnName.substring(0,1).toUpperCase() +columnName.substring(1, columnName.length()), Double.class).invoke(t, v);
						break;
					}
					case STRING: {
						String v = record.getString(i);
//						colValue = v == null ? "" : v.toString();
						if(v!=null) t.getClass().getMethod("set"+columnName.substring(0,1).toUpperCase() +columnName.substring(1, columnName.length()), String.class).invoke(t, v);
						break;
					}
					default:
						throw new RuntimeException("Unknown column type: "
								+ column.getType());
					}
					;
					
					
				}
				result.add(t);
			}
			
		} catch (TunnelException e) {
			e.printStackTrace();
		}
		
		return ValidationUtils.isEmpty(result)?null:result;
	}
	
}
