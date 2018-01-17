package indi.yue.dtcp;

import com.aliyun.oss.OSSClient;

/**
 * oss数据源
 * @author owen-yue
 *
 */
public class OssDataSource {

	private String endpoint;
	private String accessKeyId;
	private String secretAccessKey;
	
	private String bucketName;
	
	public OssDataSource() {
		super();
	}

	public OssDataSource(String endpoint, String accessKeyId, String secretAccessKey, String bucketName) {
		super();
		this.endpoint = endpoint;
		this.accessKeyId = accessKeyId;
		this.secretAccessKey = secretAccessKey;
		this.bucketName = bucketName;
	}

	public OSSClient getConnection() {
		return this.getConnection(endpoint, accessKeyId, secretAccessKey);
	}

	public OSSClient getConnection(String endpoint, String accessKeyId,String secretAccessKey) {
		return new OSSClient(endpoint, accessKeyId, secretAccessKey);
	}

	public void destory(OSSClient ossClient){
		ossClient.shutdown();
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

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	
}
