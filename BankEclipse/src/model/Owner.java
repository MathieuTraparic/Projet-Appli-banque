package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import util.Formater;
import util.Validator;

@Entity
@Table(name = "owner")
@NamedQuery(name = "Owner.findAll", query = "SELECT t FROM Owner t")
public class Owner implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String firstName;
	private Date birthday;
	private String login;
	private String pswd;
	private String salt;
	private String phoneNumber;
	private Address address;
	private List<Account> accounts;

	private Owner(){
		
	}
	public Owner(String name, String firstName, String phoneNumber, Date birthday, String login, String pswd,
			Address address) {
		check_firstName(firstName);
		check_login(login);
		check_name(name);
		check_phoneNumber(phoneNumber);
		check_pswd(pswd);
		check_birthday(birthday);

		this.name = Formater.formatNameCase(name);
		this.firstName = Formater.formatNameCase(firstName);
		this.phoneNumber = Formater.removeUsualSeparators(phoneNumber);
		this.birthday = birthday;
		this.login = login;
		this.pswd = pswd;
		this.address = address;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column ( name = "firstname")
	public String getFirstName() {
		return firstName;
	}
	

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Temporal (TemporalType.DATE)
	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	@Column ( name = "phonenumber")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@ManyToOne
	@JoinColumn (name = "idaddress")
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@ManyToMany
	@JoinTable(name = "Assign", joinColumns = { @JoinColumn(name = "id_owner") }, inverseJoinColumns = {
			@JoinColumn(name = "id_account") })
	public List<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	private static void check_name(String name) throws IllegalArgumentException {
		if (name.isEmpty()) {
			throw new IllegalArgumentException("name cannot be empty");
		}
		if (!isValidName(name)) {
			throw new IllegalArgumentException("name cannot be empty");
		}
	}

	public static boolean isValidName(String name) {
		return Validator.isValidName(name);
	}

	private static void check_firstName(String firstName) throws IllegalArgumentException {
		if (firstName.isEmpty()) {
			throw new IllegalArgumentException("firstName cannot be empty");
		}
	}

	private static void check_login(String login) throws IllegalArgumentException {
		if (login.isEmpty()) {
			throw new IllegalArgumentException("login cannot be empty");
		}
	}

	private static void check_pswd(String pswd) throws IllegalArgumentException {
		if (pswd.isEmpty()) {
			throw new IllegalArgumentException("pswd cannot be empty");
		} else if (!isValidPswd(pswd)) {
			throw new IllegalArgumentException("pswd must be valid");
		}
	}

	public static boolean isValidPswd(String pswd) {
		// TODO must not be in clear at that point !
		return true;
	}

	private static void check_phoneNumber(String phoneNumber) throws IllegalArgumentException {
		if (phoneNumber.isEmpty()) {
			throw new IllegalArgumentException("phoneNumber cannot be empty");
		}
		if (!isValidPhoneNumber(phoneNumber)) {
			throw new IllegalArgumentException("Phone number must be a valid phone number");
		}
	}

	public static boolean isValidPhoneNumber(String phoneNumber) {
		return Validator.isValidPhoneNumber(phoneNumber);
	}

	private static void check_birthday(Date birthday) throws IllegalArgumentException {
		if (!isValidBirthday(birthday)) {
			throw new IllegalArgumentException("The birthday date cannot be in the future");
		}
	}

	public static boolean isValidBirthday(Date date) {
		return date.before(new Date());
	}

}
