package de.goldmann.comercio.domain.order;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "leadingindex")
public class LeadingIndex implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/**
	 * 
	 */
	@Column(nullable = false, unique = true)
	private String name;
	/**
	 * 
	 */
	@Column(unique = true)
	private String isin;

	/**
	 * 
	 */
	@Column(unique = true)
	private String wkn;

	/**
	* 
	*/
	@Column(nullable = false, name = "searchkey")
	private String searchKey;

	@OneToMany(mappedBy = "leadingIndex", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Stock> stocks;

	public LeadingIndex() {
	}

	public List<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	@Override
	public String toString() {
		return "Index [" + (name != null ? "name=" + name + ", " : "") + (isin != null ? "isin=" + isin + ", " : "")
				+ (wkn != null ? "wkn=" + wkn + ", " : "") + (searchKey != null ? "searchKey=" + searchKey : "") + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsin() {
		return isin;
	}

	public void setIsin(String isin) {
		this.isin = isin;
	}

	public String getWkn() {
		return wkn;
	}

	public void setWkn(String wkn) {
		this.wkn = wkn;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
}
