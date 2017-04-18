package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the transactiontype database table.
 * 
 */
@Entity
@NamedQuery(name="Transactiontype.findAll", query="SELECT t FROM Transactiontype t")
public class Transactiontype implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String wording;

	public Transactiontype() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getWording() {
		return this.wording;
	}

	public void setWording(String wording) {
		this.wording = wording;
	}

}