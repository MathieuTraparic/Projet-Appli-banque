package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cpville database table.
 * 
 */
@Entity
@NamedQuery(name="Cpville.findAll", query="SELECT c FROM Cpville c")
public class Cpville implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String cp;
	private String ville;

	public Cpville() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getCp() {
		return this.cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}


	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

}