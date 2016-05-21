package testobject;
// Generated 2016-5-20 23:45:10 by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * UserStock generated by hbm2java
 */
@Entity
@Table(name = "user_stock", catalog = "superquant")
public class UserStock implements java.io.Serializable {

	private UserStockId id;

	public UserStock() {
	}

	public UserStock(UserStockId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "userId", column = @Column(name = "userID", nullable = false) ),
			@AttributeOverride(name = "stockId", column = @Column(name = "stockID", nullable = false, length = 20) ) })
	public UserStockId getId() {
		return this.id;
	}

	public void setId(UserStockId id) {
		this.id = id;
	}

}