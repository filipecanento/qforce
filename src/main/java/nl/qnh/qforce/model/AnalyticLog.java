package nl.qnh.qforce.model;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class AnalyticLog implements Serializable {
	
	private static final long serialVersionUID = -3024493925655623645L;
	
	public AnalyticLog() {
		super();
	}
	
	public AnalyticLog(Instant timestamp, String methodName, Object[] args) {
		this();
		this.timestamp = timestamp;
		this.methodName = methodName;
		this.args = args;
	}


	@Id
	@Column(name = "timestamp")
	private Instant timestamp;
	
	@Column(name = "method")
	private String methodName;
	
	@Column(name = "args")
	private Object[] args;
	
	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

}
