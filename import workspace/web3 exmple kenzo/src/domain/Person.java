package domain;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
	private String userid;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String salt;
	private Role role;

	public Person(String userid, String email, String password, String firstName, String lastName, Role role) {
		setUserid(userid);
		setEmail(email);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setRole(role);
	}
	
	public Person() {
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		if(userid.isEmpty()){
			throw new IllegalArgumentException("No userid given");
		}
		this.userid = userid;
	}

	public void setEmail(String email) {
		if(email.isEmpty()){
			throw new IllegalArgumentException("No email given");
		}
		String USERID_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(USERID_PATTERN);
		Matcher m = p.matcher(email);
		if (!m.matches()) {
			throw new IllegalArgumentException("Email not valid");
		}
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean isCorrectPassword(String password) {
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		
		password = this.hashPassword(password);
		System.out.println(password + " === " + this.getPassword());
		return getPassword().equals(password);
	}

	public void setPassword(String password) {
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		
		this.password = password;
	}
	
	public void setPasswordHashed(String password){
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
				
		this.password = this.hashPassword(password);
	}	
	public void setSalt(String salt){
		this.salt = salt;
	}
	
	public String getSalt(){
		return this.salt;
	}
	
	private String hashPassword(String password) {
		//create MessageDigest
		MessageDigest crypt = null;
		byte[] seed = null;
		
		try {
			crypt = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//reset
		crypt.reset();
		
		/*if( this.getSalt() == null ){
			//create SecureRandom*/
			SecureRandom random = new SecureRandom();
			//generate seed
			seed = random.generateSeed(20);
			this.salt = new BigInteger(1, seed).toString(16);
			/*System.out.println("salt1: " + this.salt);
			System.out.println("seed1: " + seed);
		} else {
			try {
				seed = this.getSalt().getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			System.out.println("salt2: " + this.getSalt());
			System.out.println("seed2: " + seed);
		}
		
		//update seed
		crypt.update(seed);*/
		//update password
		byte[] passwordBytes = null;
		try {
			passwordBytes = password.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		crypt.update(passwordBytes);
		//digest
		byte[] digest = crypt.digest();
		//convert to String
		BigInteger digestAsBigInteger = new BigInteger(1, digest);
		//return hashed password
		return digestAsBigInteger.toString(16);
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(firstName.isEmpty()){
			throw new IllegalArgumentException("No firstname given");
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if(lastName.isEmpty()){
			throw new IllegalArgumentException("No last name given");
		}
		this.lastName = lastName;
	}
	
	@Override
	public String toString(){
		return getFirstName() + " " + getLastName() + ": " + getUserid() + ", " + getEmail();
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		if(role == null){
			throw new IllegalArgumentException(); 
		}
		this.role = role;
	}	
}