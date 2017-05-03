package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
@NamedQuery(name = "Owner.findAll", query = "SELECT o FROM Owner o")
public class Owner implements Serializable {

	private static final long serialVersionUID = -5806508181292371902L;
	private int id;
	private String name;
	private String firstName;
	private Date birthday;
	private String login;
	private String pswd;
	private String salt;
	private String email;
	private String phoneNumber;
	private Address address;
	private boolean newUser;
	private List<Account> accounts;

	@SuppressWarnings("unused")
	private Owner() {
	}

	/**
	 * @param login
	 * @param pswd
	 *            : password
	 * @param email
	 * @param salt
	 */
	public Owner(String login, String pswd, String email, String salt) {
		this.setLogin(login);
		this.setPswd(pswd);
		this.setEmail(email);
		this.setSalt(salt);
	}

	/**
	 * @param name
	 * @param firstName
	 * @param phoneNumber
	 * @param birthday
	 * @param login
	 * @param pswd
	 * @param email
	 * @param address
	 * @param newUser (0 == false 1 == true)
	 */
	public Owner(String name, String firstName, String phoneNumber, Date birthday, String login, String pswd,
			String email, Address address, boolean newUser) {
		this.setName(name);
		this.setFirstName(firstName);
		this.setPhoneNumber(phoneNumber);
		this.setBirthday(birthday);
		this.setLogin(login);
		this.setPswd(pswd);
		this.setEmail(email);
		this.setAddress(address);
		this.setNewUser(newUser);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	@SuppressWarnings("unused")
	private void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		checkName(name);
		this.name = Formater.formatNameCase(name);
		;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		checkFirstName(firstName);
		this.firstName = Formater.formatNameCase(firstName);
	}

	@Temporal(TemporalType.DATE)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		checkBirthday(birthday);
		this.birthday = birthday;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		checkLogin(login);
		this.login = login;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		checkPswd(pswd);
		this.pswd = pswd;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		checkSalt(salt);
		this.salt = salt;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		checkPhoneNumber(phoneNumber);
		this.phoneNumber = Formater.removeUsualSeparators(phoneNumber);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		checkEmail(email);
		this.email = email;
	}

	@ManyToOne
	@JoinColumn(name = "idAddress")
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		checkAddress(address);
		this.address = address;
	}

	@ManyToMany(mappedBy = "owners")
	public List<Account> getAccounts() {
		return this.accounts;
	}

	public boolean getNewUser() {
		return this.newUser;
	}

	public void setNewUser(boolean newUser) {
		//checkNewUser(newUser);
		this.newUser = newUser;
	}

	public void setAccounts(List<Account> accounts) {
		checkAccounts(accounts);
		this.accounts = accounts;
	}

	private static void checkName(String name) throws IllegalArgumentException {
		if (name.isEmpty()) {
			throw new IllegalArgumentException("name cannot be empty");
		}
		if (!isValidName(name)) {
			throw new IllegalArgumentException("name cannot be valid");
		}
	}

	public static boolean isValidName(String name) {
		return Validator.isValidName(name);
	}

	private static void checkFirstName(String firstName) throws IllegalArgumentException {
		if (firstName.isEmpty()) {
			throw new IllegalArgumentException("firstName cannot be empty");
		}
	}

	private static void checkLogin(String login) throws IllegalArgumentException {
		if (login.isEmpty()) {
			throw new IllegalArgumentException("login cannot be empty");
		}
	}

	private static void checkPswd(String pswd) throws IllegalArgumentException {
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

	private static void checkPhoneNumber(String phoneNumber) throws IllegalArgumentException {
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

	private static void checkBirthday(Date birthday) throws IllegalArgumentException {
		if (!isValidBirthday(birthday)) {
			throw new IllegalArgumentException("The birthday date cannot be in the future");
		}
	}

	public static boolean isValidBirthday(Date date) {
		return date.before(new Date());
	}

	private static void checkEmail(String email) throws IllegalArgumentException {
		if (email.isEmpty()) {
			throw new IllegalArgumentException("Email cannot be empty");
		} else if (!isValidEmail(email)) {
			throw new IllegalArgumentException("Email must be a valid email address");
		}
	}

	public static boolean isValidEmail(String email) {
		return Validator.isValidEmailAddress(email);
	}

	private static void checkSalt(String salt) throws NullPointerException {
		if (salt == null) {
			throw new NullPointerException("The salt can't be null");
		}
	}

	private static void checkAddress(Address address) throws NullPointerException {
		if (address == null) {
			throw new NullPointerException("The address can't be null");
		}
	}

	private static void checkAccounts(List<Account> accounts) throws NullPointerException {
		if (accounts == null) {
			throw new NullPointerException("The accounts can't be null");
		}
	}

//	private static void checkNewUser(int newUser) throws IllegalArgumentException {
//		if (newUser < 0 || newUser > 1) {
//			throw new IllegalArgumentException("The newUser variable can be only 0 or 1");
//		}
//	}

}
