package de.goldmann.comercio.transfer.up;

import java.io.Serializable;

import de.goldmann.comercio.domain.order.OrderDirection;

public class OrderRow implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private long              userId;
    private String            stockName;
    private double            stockPrice;
    private double            amountInput;
    private OrderDirection    direction;
    private int               multiplikator;
    private int               lineNumber;

    // JSON
    public OrderRow()
    {}

    public OrderRow(long userId, String stockName, double stockPrice, double amountInput,
            OrderDirection direction, int multiplikator, int lineNumber)
    {
        this.userId = userId;
        this.stockName = stockName;
        this.stockPrice = stockPrice;
        this.amountInput = amountInput;
        this.direction = direction;
        this.multiplikator = multiplikator;
        this.lineNumber = lineNumber;
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
     * @return the lineNumber
     */
    public int getLineNumber()
    {
        return lineNumber;
    }

    /**
     * @return the multiplikator
     */
    public int getMultiplikator()
    {
        return multiplikator;
    }

    /**
     * @return the stockName
     */
    public String getStockName()
    {
        return stockName;
    }

    /**
     * @return the stockPrice
     */
    public double getStockPrice()
    {
        return stockPrice;
    }

    /**
     * @return the userId
     */
    public long getUserId()
    {
        return userId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "OrderRow [userId=" + userId + ", "
                + (stockName != null ? "stockName=" + stockName + ", " : "") + "stockPrice=" + stockPrice
                + ", amountInput=" + amountInput + ", "
                + (direction != null ? "direction=" + direction + ", " : "") + "multiplikator="
                + multiplikator + ", lineNumber=" + lineNumber + "]";
    }

}
