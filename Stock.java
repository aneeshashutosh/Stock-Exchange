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
    StringBuilder quote = new StringBuilder(this.name + " (" + this.symbol + ")\nPrice: " + USD.format(this.price) + "  hi: " + USD.format(this.highPrice) + "  lo: " + USD.format(this.lowPrice) + "  vol: " + this.volume + "\nAsk: ");

    if (!this.sell.isEmpty())
    {
      TradeOrder to = this.sell.peek();
      if (to.isLimit())
      {
        quote.append(USD.format(to.getPrice()));
      }
      else
      {
        quote.append("market");
      }
      quote.append(" size: " + to.getShares());
    }
    else
    {
      quote.append("none");
    }

    quote.append("  Bid: ");

    if (!this.buy.isEmpty())
    {
      TradeOrder to = this.buy.peek();
      if (to.isLimit())
      {
        quote.append(USD.format(to.getPrice()));
      }
      else
      {
        quote.append("market");
      }
      quote.append(" size: " + to.getShares());
    }
    else
    {
      quote.append("none");
    }

    return quote.toString();
  }

  public void placeOrder(TradeOrder order)
  {
    StringBuilder message = new StringBuilder("New order:  ");
    if (order.isBuy()){
      message.append("Buy ");
    }
    else
    {
      message.append("Sell ");
    }
    message.append(order.getTrader().getName() + "\n" + this.symbol + " ");
    if (this.name != null && this.name.length() > 0)
    {
      message.append(" (" + this.name + ")");
    }
    message.append("\n" + order.getShares() + " shares");
    if (order.isMarket())
    {
      message.append(" at market ");
    }
    else
    {
      message.append(" at  " + USD.format(order.getPrice()));
    }

    Trader trader = order.getTrader();
    trader.receiveMessage(message.toString());

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
      
      String msg = String.format("%s %s at %s amt %s", shares, this.symbol, USD.format(price), USD.format(price*shares));
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