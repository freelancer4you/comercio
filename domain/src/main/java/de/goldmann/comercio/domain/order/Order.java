package de.goldmann.comercio.domain.order;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import de.goldmann.comercio.domain.User;

@Entity
@Table(name = "OrderTable")
public class Order implements Serializable, OrderViewNames
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long              id;

    /**
     * 
     */
    @Column(name = "amountinput")
    private double            amountInput;

    /**
     * 
     */
    @Column(name = "multiplikator")
    private int               multiplikator;

    /**
     * 
     */
    @Column(name = "open")
    private boolean           open;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "USER_ID")
    private User              user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "STOCK_ID")
    private Stock             stock;

    @Column(name = "stockprice")
    private double            stockPrice;

    /**
     * 
     */
    @Column(name = "tpalarm")
    private double            tpAlarm;

    /**
     * Stopp loss
     */
    @Column(name = "slalarm")
    private double            slAlarm;

    /**
     * 
     */
    @Column(name = "tpstop")
    private double            tpStop;

    @Enumerated(EnumType.STRING)
    private OrderDirection    direction;

    /**
     * Stopp loss
     */
    @Column(name = "slstop")
    private double            slStop;

    @Temporal(TemporalType.TIMESTAMP)
    private Date              stamp;

    public Order()
    {
        stamp = new Date();
    }

    /**
     * @return the amountInput
     */
    public double getAmountInput()
    {
        return amountInput;
    }

    /**
     * @return the direction
     */
    public OrderDirection getDirection()
    {
        return direction;
    }

    /**
     * @return the id
     */
    public long getId()
    {
        return id;
    }

    /**
     * @return the multiplikator
     */
    public int getMultiplikator()
    {
        return multiplikator;
    }

    /**
     * @return the slAlarm
     */
    public double getSlAlarm()
    {
        return slAlarm;
    }

    /**
     * @return the slStop
     */
    public double getSlStop()
    {
        return slStop;
    }

    /**
     * @return the stamp
     */
    public Date getStamp()
    {
        return stamp;
    }

    /**
     * @return the stock
     */
    public Stock getStock()
    {
        return stock;
    }

    @Override
    @Transient
    public String getStockName()
    {
        return stock.getName();
    }

    /**
     * @return the stockPrice
     */
    public double getStockPrice()
    {
        return stockPrice;
    }

    /**
     * @return the tpAlarm
     */
    public double getTpAlarm()
    {
        return tpAlarm;
    }

    /**
     * @return the tpStop
     */
    public double getTpStop()
    {
        return tpStop;
    }

    /**
     * @return the user
     */
    public User getUser()
    {
        return user;
    }

    /**
     * @return the open
     */
    public boolean isOpen()
    {
        return open;
    }

    /**
     * @param amountInput
     *            the amountInput to set
     */
    public void setAmountInput(double amountInput)
    {
        this.amountInput = amountInput;
    }

    /**
     * @param direction
     *            the direction to set
     */
    public void setDirection(OrderDirection direction)
    {
        this.direction = direction;
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
     * @param multiplikator
     *            the multiplikator to set
     */
    public void setMultiplikator(int multiplikator)
    {
        this.multiplikator = multiplikator;
    }

    /**
     * @param open
     *            the open to set
     */
    public void setOpen(boolean open)
    {
        this.open = open;
    }

    /**
     * @param slAlarm
     *            the slAlarm to set
     */
    public void setSlAlarm(double slAlarm)
    {
        this.slAlarm = slAlarm;
    }

    /**
     * @param slStop
     *            the slStop to set
     */
    public void setSlStop(double slStop)
    {
        this.slStop = slStop;
    }

    /**
     * @param stock
     *            the stock to set
     */
    public void setStock(Stock stock)
    {
        this.stock = stock;
    }

    /**
     * @param stockPrice
     *            the stockPrice to set
     */
    public void setStockPrice(double stockPrice)
    {
        this.stockPrice = stockPrice;
    }

    /**
     * @param tpAlarm
     *            the tpAlarm to set
     */
    public void setTpAlarm(double tpAlarm)
    {
        this.tpAlarm = tpAlarm;
    }

    /**
     * @param tpStop
     *            the tpStop to set
     */
    public void setTpStop(double tpStop)
    {
        this.tpStop = tpStop;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(User user)
    {
        this.user = user;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "Order [id=" + id + ", amountInput=" + amountInput + ", multiplikator=" + multiplikator
                + ", open=" + open + ", " + (user != null ? "user=" + user + ", " : "")
                + (stock != null ? "stock=" + stock + ", " : "") + "stockPrice=" + stockPrice + ", tpAlarm="
                + tpAlarm + ", slAlarm=" + slAlarm + ", tpStop=" + tpStop + ", "
                + (direction != null ? "direction=" + direction + ", " : "") + "slStop=" + slStop + ", "
                + (stamp != null ? "stamp=" + stamp : "") + "]";
    }

}
