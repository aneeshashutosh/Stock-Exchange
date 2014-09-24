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

	public void subtractShares(int shares)
	{
		if (shares > this.numShares)
			throw new IllegalArgumentException();
		else
			this.numShares -= shares;
	}

	public Trader getTrader() { return this.trader; }
	public String getSymbol() { return this.symbol; }
	public boolean isBuy() { return this.buyOrder; }
	public boolean isSell() { return !this.buyOrder; }
	public boolean isMarket() { return this.marketOrder; }
	public boolean isLimit() { return !this.marketOrder; }
	public int getShares() { return this.numShares; }
	public double getPrice() { return this.price; }
}
