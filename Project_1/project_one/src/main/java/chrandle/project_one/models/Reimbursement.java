package chrandle.project_one.models;

public class Reimbursement {
	private String firstname;
	private String lastanme;
	private String email;
	private long uid;
	private String password;
	private long supervisor;
	
	public Reimbursement(String firstname, String lastanme, String email, String password) {
		super();
		this.firstname = firstname;
		this.lastanme = lastanme;
		this.email = email;
		this.password = password;
	}
}
