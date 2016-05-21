package de.goldmann.comercio.transfer.down;

import java.io.Serializable;

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

    // JSON
    public OrderRow()
    {}

    public OrderRow(long orderOd, String login, long time, int lineNumber)
    {
        this.orderOd = orderOd;
        this.login = login;
        this.time = time;
        this.lineNumber = lineNumber;
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
