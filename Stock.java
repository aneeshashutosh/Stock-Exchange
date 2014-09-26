import java.text.DecimalFormat;
import java.util.PriorityQueue;

/**
 * @author Amy Chou, Aneesh Ashutosh, Cam Wong, Seho Young
 * @date 10/01/14
 */

public class Stock
{
	private static final DecimalFormat USD = new DecimalFormat("0.00");
	private PriorityQueue<TradeOrder> buy;
	private PriorityQueue<TradeOrder> sell;
	private String symbol;
	private String name;
	private double highPrice;
	private double lowPrice;
	private double price;
	private int volume;

	public Stock(String symbol, String name, double price)
	{
		this.symbol = symbol;
		this.name = name;
		this.highPrice = price;
		this.lowPrice = price;
		this.price = price;
		this.volume = 0;

		this.buy = new PriorityQueue<TradeOrder>(1, new PriceComparator(false));
		this.sell = new PriorityQueue<TradeOrder>(1, new PriceComparator(true));
	}

	public String getQuote()
	{
		String quote = this.name + " (" + this.symbol + ")\nPrice: " + USD.format(this.price) + "  hi: " + USD.format(this.highPrice) + "  lo: " + USD.format(this.lowPrice) + "  vol: " + this.volume + "\nAsk: ";

		if (!this.sell.isEmpty())
		{
			TradeOrder to = this.sell.peek();
			if (to.isLimit())
			{
				quote += USD.format(to.getPrice());
			}
			else
			{
				quote += "market";
			}
			quote += " size: " + to.getShares();
		}
		else
		{
			quote += "none";
		}

		quote += "  Bid: ";

		if (!this.buy.isEmpty())
		{
			TradeOrder to = this.buy.peek();
			if (to.isLimit())
			{
				quote += USD.format(to.getPrice());
			}
			else
			{
				quote += "market";
			}
			quote += " size: " + to.getShares();
		}
		else
		{
			quote = quote + "none";
		}

		return quote;
	}

	public void placeOrder(TradeOrder order)
	{
		String message = "New order:  ";
		if (order.isBuy()){
			message += "Buy ";
		}
		else
		{
			message += "Sell ";
		}
		message += order.getTrader().getName() + "\n" + this.symbol + " ";
		if (this.name != null && this.name.length() > 0)
		{
			message += " (" + this.name + ")";
		}
		message += "\n" + order.getShares() + " shares";
		if (order.isMarket())
		{
			message += " at market ";
		}
		else
		{
			message += " at  " + USD.format(order.getPrice());
		}

		Trader trader = order.getTrader();
		trader.receiveMessage(message);

		if (order.isBuy())
		{
			this.sell.add(order);
		}
		else
		{
			this.buy.add(order);
		}

		while ((!this.sell.isEmpty()) && (!this.buy.isEmpty()))
		{
			TradeOrder sellOrder = (TradeOrder)this.sell.peek();
			TradeOrder buyOrder = (TradeOrder)this.buy.peek();
			if ((sellOrder.isLimit()) && (buyOrder.isLimit()) && (sellOrder.getPrice() > buyOrder.getPrice()))
			{
				break;
			}
			double p;
			if ((sellOrder.isMarket()) && (buyOrder.isMarket()))
			{
				p = this.price;
			}
			else
			{
				if ((sellOrder.isMarket()) && (buyOrder.isLimit()))
				{
					p = buyOrder.getPrice();
				}
				else
				{
					if ((sellOrder.isLimit()) && (buyOrder.isMarket()))
					{
						p = sellOrder.getPrice();
					}
					else
					{
						p = sellOrder.getPrice(); 
					}
				}
			}
			int shares = Math.min(buyOrder.getShares(), sellOrder.getShares());

			sellOrder.subtractShares(shares);
			buyOrder.subtractShares(shares);

			String msg = shares + " " + this.symbol + " at " + USD.format(price) + " amt " + USD.format(price * shares);
			buyOrder.getTrader().receiveMessage("You bought: " + msg);
			sellOrder.getTrader().receiveMessage("You sold: " + msg);

			if (buyOrder.getShares() == 0)
			{
				this.buy.remove();
			}
			if (sellOrder.getShares() == 0)
			{
				this.sell.remove();
			}
			
			this.volume += shares;
			if (p < this.lowPrice)
			{
				this.lowPrice = p;
			}
			if (p > this.highPrice)
			{
				this.highPrice = p;
			}
			this.price = p;
		}
	}
}
