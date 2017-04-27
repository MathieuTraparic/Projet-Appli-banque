package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "address")
@NamedQuery(name = "Address.findAll", query = "SELECT t FROM Address t")
public class Address implements Serializable{

	private static final long serialVersionUID = 3089550499268003492L;
	private int id;
	private String line1;
	private String line2 = null; //line2 is not mandatory
	private CpVille cpVille;
	
	/*
	 * Used only by the ORM
	 */
	@SuppressWarnings("unused")
	private Address(){
	}
	
	/**Constructor
	 * @param line1
	 * @param line2 can be null
	 */
	public Address(String line1, String line2) {

		this.setLine1(line1);
		this.line2 = line2;
	}


	private static void checkLine1(String line1) throws IllegalArgumentException {
		if (line1.isEmpty()) {
			throw new IllegalArgumentException("line1 cannot be empty");
		}
	}

	public String getLine1() {
		return this.line1;
	}

	public void setLine1(String line1) {
		
		checkLine1(line1);
		
		this.line1 = line1;
	}


	public String getLine2() {
		return this.line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}
	
	@ManyToOne
	@JoinColumn(name = "idCpVille")
	public CpVille getCpVille() {
		return cpVille;
	}

	public void setCpVille(CpVille cpVille) {
		if (cpVille!=null){
			this.cpVille = cpVille;
		}
	}
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}
	
	/*
	 * Used only by the ORM
	 */
	@SuppressWarnings("unused")
	private void setId(int id) {
		this.id = id;
	}

}
