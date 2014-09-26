/**
 * @author Amy Chou, Aneesh Ashutosh, Cam Wong, Seho Young
 * @date 10/01/14
 */

public class Stock
{
	private String symbol;
	private String name;
	private double price;
	
	public Stock(String symbol, String name, double price)
	{
		this.symbol = symbol;
		this.name = name;
		this.price = price;
	}
<<<<<<< HEAD
=======
	
	public String getQuote()
	{
	  
	}
	
	public void placeOrder(TradeOrder order)
	{
	  String message = "New order:  ";
	  if (order.isBuy()){
	    message += "Buy ";
	  }
	  else{
	    message += "Sell ";
	  }
	  message += order.getTrader().getName();
	  message += "\n";
	  message += 
	}
>>>>>>> FETCH_HEAD
}
