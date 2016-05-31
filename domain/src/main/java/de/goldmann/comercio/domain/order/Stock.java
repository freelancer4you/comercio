package de.goldmann.comercio.domain.order;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Stock implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long              id;

    /**
     * 
     */
    @Column(nullable = false, unique = true)
    private String            name;

    /**
     * 
     */
    @Column(unique = true)
    private String            isin;

    /**
     * 
     */
    @Column(unique = true)
    private String            wkn;
    /**
     * 
     */
	@Column(nullable = false, name = "searchkey", unique = true)
    private String            searchKey;

    /**
     * 
     */
    @Column(nullable = false)
    private String            currency;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "LEADINGINDEX_ID")
	private LeadingIndex leadingIndex;


	public LeadingIndex getLeadingIndex() {
		return leadingIndex;
	}

	public void setLeadingIndex(LeadingIndex leadingIndex) {
		this.leadingIndex = leadingIndex;
	}

	public Stock()
    {}

    /**
     * Getter of currency
     */
    public String getCurrency()
    {
        return currency;
    }

    /**
     * @return the id
     */
    public long getId()
    {
        return id;
    }

    /**
     * Getter of isin
     */
    public String getIsin()
    {
        return isin;
    }

    /**
     * Getter of name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Getter of wkn
     */
    public String getWkn()
    {
        return wkn;
    }




    /**
     * Setter of currency
     */
    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(long id)
    {
        this.id = id;
    }

    /**
     * Setter of isin
     */
    public void setIsin(String isin)
    {
        this.isin = isin;
    }


    /**
     * Setter of name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Setter of wkn
     */
    public void setWkn(String wkn)
    {
        this.wkn = wkn;
    }

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	@Override
	public String toString() {
		return "Stock [" + (name != null ? "name=" + name + ", " : "") + (isin != null ? "isin=" + isin + ", " : "")
				+ (wkn != null ? "wkn=" + wkn + ", " : "") + (searchKey != null ? "searchKey=" + searchKey + ", " : "")
				+ (currency != null ? "currency=" + currency + ", " : "")
				+ (leadingIndex != null ? "leadingIndex=" + leadingIndex : "") + "]";
	}




}
