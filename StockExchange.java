/**
<<<<<<< HEAD
 * @author Amy Chou
=======
 * @author Amy Chou, Aneesh Ashutosh, Cam Wong, Seho Young
 * @date 10/01/14
<<<<<<< HEAD
>>>>>>> FETCH_HEAD
=======
>>>>>>> FETCH_HEAD
 */

import java.util.*;

public class StockExchange {
  
  private Map<String, Stock> stocks;
  
  public StockExchange()
  {
    stocks = new HashMap<String, Stock>();
  }
  
  public void listStock(String symbol, String name, double price)
  {
    stocks.put(symbol, new Stock(symbol, name, price));
  }
  
  public String getQuote(String symbol) { return stocks.get(symbol).getQuote(); }
  
  public void placeOrder(TradeOrder order)
  {
    stocks.get(order.getSymbol().placeOrder(order));
  }
<<<<<<< HEAD
  
  
=======
>>>>>>> FETCH_HEAD
}
