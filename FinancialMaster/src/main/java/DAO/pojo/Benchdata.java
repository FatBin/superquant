package DAO.pojo;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Benchdata generated by hbm2java
 */
@Entity
@Table(name = "benchdata", catalog = "superquant")
public class Benchdata implements java.io.Serializable {

	private BenchdataId id;
	private double open;
	private double close;
	private double high;
	private double low;
	private double adjPrice;
	private long volume;

	public Benchdata() {
	}

	public Benchdata(BenchdataId id, double open, double close, double high, double low, double adjPrice, long volume) {
		this.id = id;
		this.open = open;
		this.close = close;
		this.high = high;
		this.low = low;
		this.adjPrice = adjPrice;
		this.volume = volume;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "benchId", column = @Column(name = "benchID", nullable = false, length = 20) ),
			@AttributeOverride(name = "date", column = @Column(name = "date", nullable = false, length = 20) ) })
	public BenchdataId getId() {
		return this.id;
	}

	public void setId(BenchdataId id) {
		this.id = id;
	}

	@Column(name = "open", nullable = false, precision = 22, scale = 0)
	public double getOpen() {
		return this.open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	@Column(name = "close", nullable = false, precision = 22, scale = 0)
	public double getClose() {
		return this.close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	@Column(name = "high", nullable = false, precision = 22, scale = 0)
	public double getHigh() {
		return this.high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	@Column(name = "low", nullable = false, precision = 22, scale = 0)
	public double getLow() {
		return this.low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	@Column(name = "adj_price", nullable = false, precision = 22, scale = 0)
	public double getAdjPrice() {
		return this.adjPrice;
	}

	public void setAdjPrice(double adjPrice) {
		this.adjPrice = adjPrice;
	}

	@Column(name = "volume", nullable = false)
	public long getVolume() {
		return this.volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

}