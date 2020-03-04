package models;

public class Reimbursement {
	private long requestid;
	private long sourceid;
	private long superid;
	private String status;
	private long numeric;
	
	
	public Reimbursement(long requestid, long sourceid, long superid, String status, long numeric) {
		super();
		this.requestid = requestid;
		this.sourceid = sourceid;
		this.superid = superid;
		this.status = status;
		this.numeric = numeric;
	}
	
	
	
}
