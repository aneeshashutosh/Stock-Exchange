/**
 * @author Amy Chou, Aneesh Ashutosh, Cam Wong, Seho Young
 * @date 10/01/14
 * 
 * Represents a buy or sell order for trading a given number 
 * of shares of a specified stock.
 */

public class TradeOrder
{
	private Trader trader;
	private String symbol;
	private boolean buyOrder;
	private boolean marketOrder;
	private int numShares;
	private double price;

	/**
	 * Constructs a new TradeOrder for a given trader, stock symbol, a 
	 * number of shares, and other parameters.
	 * 
	 * @param trader       a trader who placed this order.
	 * @param symbol       stock symbol.
	 * @param buyOrder     if true this is a buy order; otherwise this is a sell order.
	 * @param marketOrder  if true this is a market order; otherwise this is a limit order.
	 * @param numShares    the number of shares to be traded.
	 * @param price        the bid or ask price, if this is a limit order.
	 */
	public TradeOrder(Trader trader, String symbol, boolean buyOrder, boolean marketOrder, int numShares, double price)
	{
		this.trader = trader;
		this.symbol = symbol;
		this.buyOrder = buyOrder;
		this.marketOrder = marketOrder;
		this.numShares = numShares;
		this.price = price;
	}

	/**
	 * Returns the price per share for this trade order (used by a limit order).
	 * @return the price per share for this trade order.
	 */
	public double getPrice()
	{
		return this.price;
	}

	/**
	 * Returns the number of shares to be traded in this trade order.
	 * @return the number of shares to be traded in this trade order.
	 */
	public int getShares()
	{
		return this.numShares;
	}

	/**
	 * Returns the stock symbol for this trade order.
	 * @return the stock symbol for this trade order.
	 */
	public String getSymbol()
	{
		return this.symbol;
	}

	/**
	 * Returns the trader for this trade order.
	 * @return the trader who placed this trade order.
	 */
	public Trader getTrader()
	{
		return this.trader;
	}

	/**
	 * Returns true if this is a buy order; otherwise returns false.
	 * @return true if this is a buy order; false otherwise.
	 */
	public boolean isBuy()
	{
		return this.buyOrder;
	}

	/**
	 * Returns true if this is a limit order; otherwise returns false.
	 * @return true if this is a limit order; false otherwise.
	 */
	public boolean isLimit()
	{
		return !this.isMarket();
	}

	public boolean isMarket()
	{
		return this.marketOrder;
	}

	/**
	 * Returns true if this is a sell order; otherwise returns false.
	 * @return true if this is a market order; false otherwise.
	 */
	public boolean isSell()
	{
		return !this.buyOrder;
	}

	/**
	 * Subtracts a given number of shares from the total number of shares in this trade order.
	 * @param shares a number of shares to be subtracted.
	 */
	public void subtractShares(int shares)
	{
		if (shares > this.numShares)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			this.numShares -= shares;
		}
	}

}
