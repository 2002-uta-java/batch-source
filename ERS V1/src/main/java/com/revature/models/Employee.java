package com.revature.models;

public class Employee {

	// List of all the attributes in this entity
	private String employeeId;
	private String firstName;
	private String LastName;
	private String title;
	private String username;
	private String password;
	//private String phone;
	private String email;
//	private String address;
//	private String city;
//	private String state;
//	private String country;
//	private String postalCode;
	private int managerId;

	// Constructors with parameters
//	public Employee(String firstName, String lastName, String title, String username, String password, String phone,
//			String email, String address, String city, String state, String country, String postalCode, int roleId) {
//		super();
//		this.firstName = firstName;
//		this.LastName = lastName;
//		this.title = title;
//		this.username = username;
//		this.password = password;
//		this.phone = phone;
//		this.email = email;
//		this.address = address;
//		this.city = city;
//		this.state = state;
//		this.country = country;
//		this.postalCode = postalCode;
//		this.roleId = roleId;
//	}

	// Constructors without parameters(Default)
	public Employee() {
		super();
	}

	// Setters and Getters
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public String getPhone() {
//		return phone;
//	}

//	public void setPhone(String phone) {
//		this.phone = phone;
//	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public String getCity() {
//		return city;
//	}
//
//	public void setCity(String city) {
//		this.city = city;
//	}
//
//	public String getState() {
//		return state;
//	}
//
//	public void setState(String state) {
//		this.state = state;
//	}
//
//	public String getCountry() {
//		return country;
//	}
//
//	public void setCountry(String country) {
//		this.country = country;
//	}
//
//	public String getPostalCode() {
//		return postalCode;
//	}
//
//	public void setPostalCode(String postalCode) {
//		this.postalCode = postalCode;
//	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int roleId) {
		this.managerId = roleId;
	}

	// HashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((LastName == null) ? 0 : LastName.hashCode());
//		result = prime * result + ((address == null) ? 0 : address.hashCode());
//		result = prime * result + ((city == null) ? 0 : city.hashCode());
//		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
//		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
//		result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
		result = prime * result + managerId;
//		result = prime * result + ((state == null) ? 0 : state.hashCode());
	result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	// Equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (LastName == null) {
			if (other.LastName != null)
				return false;
		} else if (!LastName.equals(other.LastName))
			return false;
//		if (address == null) {
//			if (other.address != null)
//				return false;
//		} else if (!address.equals(other.address))
//			return false;
//		if (city == null) {
//			if (other.city != null)
//				return false;
//		} else if (!city.equals(other.city))
//			return false;
//		if (country == null) {
//			if (other.country != null)
//				return false;
//		} else if (!country.equals(other.country))
//			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
//		if (phone == null) {
//			if (other.phone != null)
//				return false;
//		} else if (!phone.equals(other.phone))
//			return false;
//		if (postalCode == null) {
//			if (other.postalCode != null)
//				return false;
//		} else if (!postalCode.equals(other.postalCode))
//			return false;
		if (managerId != other.managerId)
			return false;
//		if (state == null) {
//			if (other.state != null)
//				return false;
//		} else if (!state.equals(other.state))
//			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	// toString
	@Override
//	public String toString() {
//		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", LastName=" + LastName + ", title="
//				+ title + ", username=" + username + ", password=" + password + ", phone=" + phone + ", email=" + email
//				+ ", address=" + address + ", city=" + city + ", state=" + state + ", country=" + country
//				+ ", postalCode=" + postalCode + ", roleId=" + managerId + "]";
//	}
	
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", LastName=" + LastName + ", title="
				+ title + ", username=" + username + ", password=" + password + ", email=" + email
				 + ", managerId=" + managerId + "]";
	}

}
