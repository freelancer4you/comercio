package de.goldmann.comercio.transfer.down;

import java.io.Serializable;

import de.goldmann.comercio.domain.order.OrderDirection;

public class OrderRow implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private long              orderOd;
    private String            login;
    private long              time;
    private int               lineNumber;
	private OrderDirection direction;
	private String stockName;

    // JSON
    public OrderRow()
    {}

	public OrderRow(long orderOd, String login, String stockName, long time, OrderDirection direction, int lineNumber)
    {
        this.orderOd = orderOd;
        this.login = login;
		this.stockName = stockName;
        this.time = time;
		this.direction = direction;
        this.lineNumber = lineNumber;
    }

	public OrderDirection getDirection() {
		return direction;
	}

	public String getStockName() {
		return stockName;
	}

	/**
	 * @return the lineNumber
	 */
    public int getLineNumber()
    {
        return lineNumber;
    }

    /**
     * @return the login
     */
    public String getLogin()
    {
        return login;
    }

    /**
     * @return the orderOd
     */
    public long getOrderOd()
    {
        return orderOd;
    }

    /**
     * @return the time
     */
    public long getTime()
    {
        return time;
    }
}
