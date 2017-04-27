package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the frequency database table.
 * 
 */
@Entity
@Table(name = "frequency")
@NamedQuery(name = "Frequency.findAll", query = "SELECT f FROM Frequency f")

public class Frequency implements Serializable {

	private static final long serialVersionUID = 2903748555649709137L;
	private int id;
	private String unit;

	@SuppressWarnings("unused")
	private Frequency() {}

	/**
	 * @param unit
	 */
	public Frequency(String unit) {
		this.setUnit(unit);
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

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		checkUnit(unit);
		this.unit = unit;
	}

	@Override
	public String toString() {
		return unit;
	}

	private static void checkUnit(String unit) throws IllegalArgumentException {
		if (unit.isEmpty()) {
			throw new IllegalArgumentException("The unit frequency must be valid");
		}
	}
	
	// //bi-directional many-to-one association to Periodictransaction
	// @OneToMany(mappedBy="frequency")
	// public List<PeriodicTransaction> getPeriodictransactions() {
	// return this.periodictransactions;
	// }
	//
	// public void setPeriodictransactions(List<PeriodicTransaction>
	// periodictransactions) {
	// this.periodictransactions = periodictransactions;
	// }
	//
	// public PeriodicTransaction addPeriodictransaction(PeriodicTransaction
	// periodictransaction) {
	// getPeriodictransactions().add(periodictransaction);
	// periodictransaction.setFrequency(this);
	//
	// return periodictransaction;
	// }
	//
	// public PeriodicTransaction removePeriodictransaction(PeriodicTransaction
	// periodictransaction) {
	// getPeriodictransactions().remove(periodictransaction);
	// periodictransaction.setFrequency(null);
	//
	// return periodictransaction;
	// }

}