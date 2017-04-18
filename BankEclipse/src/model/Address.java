package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import util.Formater;

@Entity
@Table(name = "Address")
@NamedQuery(name = "Address.findAll", query = "SELECT t FROM Address t")
public class Address implements Serializable{
	private static final long serialVersionUID = 1L;
	private String line1;
	private String line2 = null;
	private Cpville cpVille;
	private Integer id;
	
	private Address(){
		
	}
	public Address(String line1, String line2) {
		
		check_line1(line1);

		this.id = null;
		this.line1 = line1;
		this.line2 = line2;
	}

	private static void check_line1(String line1) throws IllegalArgumentException {
		if (line1.isEmpty()) {
			throw new IllegalArgumentException("line1 cannot be empty");
		}
	}


	@Column(name = "lign1")
	public String getLine1() {
		return this.line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	@Column(name = "lign2")
	public String getLine2() {
		return this.line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}
	
	@ManyToOne
	@JoinColumn(name = "idcpville")
	public Cpville getCpVille() {
		return cpVille;
	}

	public void setCpVille(Cpville cpVille) {
		this.cpVille = cpVille;
	}
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
