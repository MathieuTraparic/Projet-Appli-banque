package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the frequency database table.
 * 
 */
@Entity
@NamedQuery(name="frequency.findAll", query="SELECT f FROM frequency f")
public class Frequency implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String unit;
	private List<PeriodicTransaction> periodictransactions;

	private Frequency() {
	}
	
	public Frequency(String unit){
		this.unit=unit;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}


	//bi-directional many-to-one association to Periodictransaction
	@OneToMany(mappedBy="frequency")
	public List<PeriodicTransaction> getPeriodictransactions() {
		return this.periodictransactions;
	}

	public void setPeriodictransactions(List<PeriodicTransaction> periodictransactions) {
		this.periodictransactions = periodictransactions;
	}

	public PeriodicTransaction addPeriodictransaction(PeriodicTransaction periodictransaction) {
		getPeriodictransactions().add(periodictransaction);
		periodictransaction.setFrequency(this);

		return periodictransaction;
	}

	public PeriodicTransaction removePeriodictransaction(PeriodicTransaction periodictransaction) {
		getPeriodictransactions().remove(periodictransaction);
		periodictransaction.setFrequency(null);

		return periodictransaction;
	}

}