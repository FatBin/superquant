package DAO.pojo;
// default package
// Generated 2016-9-6 21:09:16 by Hibernate Tools 5.1.0.Beta1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BenchdataAccurateId generated by hbm2java
 */
@Embeddable
public class BenchdataAccurateId implements java.io.Serializable {

	private String benchId;
	private Date date;

	public BenchdataAccurateId() {
	}

	public BenchdataAccurateId(String benchId, Date date) {
		this.benchId = benchId;
		this.date = date;
	}

	@Column(name = "benchId", nullable = false, length = 20)
	public String getBenchId() {
		return this.benchId;
	}

	public void setBenchId(String benchId) {
		this.benchId = benchId;
	}

	@Column(name = "date", nullable = false, length = 19)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BenchdataAccurateId))
			return false;
		BenchdataAccurateId castOther = (BenchdataAccurateId) other;

		return ((this.getBenchId() == castOther.getBenchId()) || (this.getBenchId() != null
				&& castOther.getBenchId() != null && this.getBenchId().equals(castOther.getBenchId())))
				&& ((this.getDate() == castOther.getDate()) || (this.getDate() != null && castOther.getDate() != null
						&& this.getDate().equals(castOther.getDate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getBenchId() == null ? 0 : this.getBenchId().hashCode());
		result = 37 * result + (getDate() == null ? 0 : this.getDate().hashCode());
		return result;
	}

}