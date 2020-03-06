package models;

public class Reimbursement {
	private long requestid;
	private long sourceid;
	private long superid;
	private String status;
	private long amount;
	
	
	
	
	public Reimbursement() {
		super();
	}

	public Reimbursement(long requestid, long sourceid, long superid, String status, long amount) {
		super();
		this.requestid = requestid;
		this.sourceid = sourceid;
		this.superid = superid;
		this.status = status;
		this.amount = amount;
	}

	public long getRequestid() {
		return requestid;
	}

	public void setRequestid(long requestid) {
		this.requestid = requestid;
	}

	public long getSourceid() {
		return sourceid;
	}

	public void setSourceid(long sourceid) {
		this.sourceid = sourceid;
	}

	public long getSuperid() {
		return superid;
	}

	public void setSuperid(long superid) {
		this.superid = superid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	
	
	
	
}
