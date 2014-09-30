import java.util.HashMap;

/**
 * @author Amy Chou, Aneesh Ashutosh, Cam Wong, Seho Young
 * @date 10/01/14
 */


public class StockExchange
{  
	private HashMap<String, Stock> stocks;
	private final String ERROR_MESSAGE = " not found";

	/**
	 * Constructs a new stock exchange object. Initializes 
	 * listed stocks to an empty map (a HashMap)
	 */
	public StockExchange()
	{
		stocks = new HashMap<String, Stock>();
	}

	/**
	 * Returns a quote for a given stock.
	 * @param symbol   stock symbol
	 * @return         a text message that contains the quote.
	 */
	public String getQuote(String symbol)
	{
		Stock stock = this.stocks.get(symbol);

		if (stock != null)
		{
			return stock.getQuote();
		}

		return symbol + ERROR_MESSAGE;
	}

	/**
	 * Adds a new stock with given parameters to the listed stocks.
	 * 
	 * @param symbol stock symbol
	 * @param name   full company name
	 * @param price  opening stock price
	 */
	public void listStock(String symbol, String name, double price)
	{
		stocks.put(symbol, new Stock(symbol, name, price));
	}

	/**
	 * Places a trade order by calling stock.placeOrder for the stock 
	 * specified by the stock symbol in the trade order.
	 * 
	 * @param order  a trading order to be placed with this stock exchange
	 */
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
