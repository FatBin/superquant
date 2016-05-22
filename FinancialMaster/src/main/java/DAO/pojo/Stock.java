package DAO.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Stock generated by hbm2java
 */
@Entity
@Table(name = "stock", catalog = "superquant")
public class Stock implements java.io.Serializable {

	private String stockId;
	private String stockName;
	private String industry;

	public Stock() {
	}

	public Stock(String stockId, String stockName, String industry) {
		this.stockId = stockId;
		this.stockName = stockName;
		this.industry = industry;
	}

	@Id

	@Column(name = "stockID", unique = true, nullable = false, length = 20)
	public String getStockId() {
		return this.stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	@Column(name = "stockName", nullable = false, length = 20)
	public String getStockName() {
		return this.stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	@Column(name = "industry", nullable = false, length = 20)
	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

}