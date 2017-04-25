package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the transactiontype database table.
 * 
 */
@Entity
@Table(name = "transactiontype")
@NamedQuery(name = "TransactionType.findAll", query = "SELECT t FROM TransactionType t")
public class TransactionType implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String description;

	private TransactionType() {
	}

	public TransactionType(String description) {

		check_transactionTypeDescription(description);

		this.description = description;
	}

	private static void check_transactionTypeDescription(String description) throws IllegalArgumentException {
		if (description.isEmpty()) {
			throw new IllegalArgumentException("The transaction type description must be valid");
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	@Override
	public String toString() {
		return description;
	}

}