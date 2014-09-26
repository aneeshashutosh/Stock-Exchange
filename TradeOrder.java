/**
 * @author Amy Chou, Aneesh Ashutosh, Cam Wong, Seho Young
 * @date 10/01/14
 */

public class TradeOrder
{
	private Trader trader;
	private String symbol;
	private boolean buyOrder;
	private boolean marketOrder;
	private int numShares;
	private double price;

	public TradeOrder(Trader trader, String symbol, boolean buyOrder, boolean marketOrder, int numShares, double price)
	{
		this.trader = trader;
		this.symbol = symbol;
		this.buyOrder = buyOrder;
		this.marketOrder = marketOrder;
		this.numShares = numShares;
		this.price = price;
	}

	public double getPrice()
	{
		return this.price;
	}

	public int getShares()
	{
		return this.numShares;
	}

	public String getSymbol()
	{
		return this.symbol;
	}

	public Trader getTrader()
	{
		return this.trader;
	}

	public boolean isBuy()
	{
		return this.buyOrder;
	}

	public boolean isLimit()
	{
		return !this.isMarket();
	}

	public boolean isMarket()
	{
		return this.marketOrder;
	}

	public boolean isSell()
	{
		return !this.buyOrder;
	}

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
