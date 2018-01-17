package indi.yue.dtcp;

import com.aliyun.datahub.DatahubClient;
import com.aliyun.datahub.DatahubConfiguration;
import com.aliyun.datahub.auth.AliyunAccount;

/**
 * datahub数据源
 * @author owen-yue
 *
 */
public class DatahubDataSource {

//	private DatahubClient client = null;
	
	private String endpoint;
	private String accessKeyId;
	private String secretAccessKey;
	private String projectName;
//	private DatahubClient client;
	
	public DatahubDataSource() {
		super();
	}

	public DatahubDataSource(String endpoint, String accessKeyId, String secretAccessKey) {
		super();
		this.endpoint = endpoint;
		this.accessKeyId = accessKeyId;
		this.secretAccessKey = secretAccessKey;
	}

	public DatahubClient getConnection() {
		return this.getConnection(endpoint, accessKeyId, secretAccessKey);
	}

	public DatahubClient getConnection(String endpoint, String accessKeyId,String secretAccessKey) {
		/*if(client==null) return client=new DatahubClient(new DatahubConfiguration(new AliyunAccount(accessKeyId, secretAccessKey), endpoint));
		return client;*/
		return new DatahubClient(new DatahubConfiguration(new AliyunAccount(accessKeyId, secretAccessKey), endpoint));
	}

	public void destory(DatahubClient datahubClient){
		datahubClient.close();
	}
	
	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getSecretAccessKey() {
		return secretAccessKey;
	}

	public void setSecretAccessKey(String secretAccessKey) {
		this.secretAccessKey = secretAccessKey;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
}
