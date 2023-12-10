package Com.entity;

public class HostelAdministration {
    private int hid;
    private String name;
    private String email;
    private String password;
    private String role;
    private String qualification;
    
    public HostelAdministration(String name, String email, String password, String role, String qualification) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.qualification = qualification;
	}
	public HostelAdministration() {
		super();
		
	}

    // Getters and Setters for hosteladministration table columns
    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
}
