import java.util.HashMap;

/**
 * @author Amy Chou, Aneesh Ashutosh, Cam Wong, Seho Young
 * @date 10/01/14
 */

public class StockExchange
{  
	private HashMap<String, Stock> stocks;
	private final String ERROR_MESSAGE = " not found";

	public StockExchange()
	{
		stocks = new HashMap<String, Stock>();
	}

	public String getQuote(String symbol)
	{
		Stock stock = this.stocks.get(symbol);

		if (stock != null)
		{
			return stock.getQuote();
		}

		return symbol + ERROR_MESSAGE;
	}

	public void listStock(String symbol, String name, double price)
	{
		stocks.put(symbol, new Stock(symbol, name, price));
	}

	public void placeOrder(TradeOrder order)
	{
		String sym = order.getSymbol();
		Stock stock = this.stocks.get(sym);

		if (stock != null)
		{
			stock.placeOrder(order);
			return;
		}
		Trader trader = order.getTrader();
		trader.receiveMessage(sym + ERROR_MESSAGE);
	}
}
