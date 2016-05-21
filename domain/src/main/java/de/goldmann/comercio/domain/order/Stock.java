package de.goldmann.comercio.domain.order;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    @Column(nullable = false, name = "yahoosearchkey")
    private String            yahooSearchKey;

    /**
     * 
     */
    @Column(nullable = false)
    private String            currency;

    @Column(name = "isindex")
    private boolean           isindex;

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
     * Getter of yahooSearchKey
     */
    public String getYahooSearchKey()
    {
        return yahooSearchKey;
    }

    /**
     * @return the isindex
     */
    public boolean isIsindex()
    {
        return isindex;
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
     * @param isindex
     *            the isindex to set
     */
    public void setIsindex(boolean isindex)
    {
        this.isindex = isindex;
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

    /**
     * Setter of yahooSearchKey
     */
    public void setYahooSearchKey(String yahooSearchKey)
    {
        this.yahooSearchKey = yahooSearchKey;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "Stock [" + (name != null ? "name=" + name + ", " : "")
                + (isin != null ? "isin=" + isin + ", " : "") + (wkn != null ? "wkn=" + wkn + ", " : "")
                + (yahooSearchKey != null ? "yahooSearchKey=" + yahooSearchKey + ", " : "")
                + (currency != null ? "currency=" + currency + ", " : "") + "isindex=" + isindex + "]";
    }

}
