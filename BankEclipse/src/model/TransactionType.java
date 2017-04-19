package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the transactiontype database table.
 * 
 */
@Entity
@Table(name="transactiontype")
@NamedQuery(name = "TransactionType.findAll", query = "SELECT t FROM TransactionType t")
public class TransactionType implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String description;

	private TransactionType() {
	}
	
	public TransactionType(String wording) {
		this.description=wording;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String getDescription() {
		return this.description;
	}

	private void setDescription(String description) {
		this.description = description;
	}



}