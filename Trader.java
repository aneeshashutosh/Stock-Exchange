import java.util.LinkedList;

/**
 * @author Amy Chou, Aneesh Ashutosh, Cam Wong, Seho Young
 * @date 10/01/14
 * 
 * Represents a stock trader.
 */

public class Trader implements Comparable<Trader>
{
	private Brokerage brokerage;
	private LinkedList<String> messages;
	private String name;
	private String pswd;
	private TraderWindow window;

	/**
	 * Constructs a new trader, affiliated with a given brokerage, 
	 * with a given screen name and password.
	 * @param brokerage    the brokerage for this trader
	 * @param name         user name
	 * @param pswd         password
	 */
	public Trader(Brokerage brokerage, String name, String pswd)
	{
		this.brokerage = brokerage;
		this.name = name;
		this.pswd = pswd;

		messages = new LinkedList<String>();
	}

	/**
	 * Compares this trader to another by comparing their 
	 * screen names case blind.
	 * 
	 * @param other  the reference to a trader with which to compare
	 * @return       the result of the comparison of this trader and other
	 */
	public int compareTo(Trader other)
	{
		return this.name.compareToIgnoreCase(other.name);
	}

	/**
	 * Indicates whether some other trader is "equal to" this one, based on 
	 * comparing their screen names case blind. This method will throw a 
	 * ClassCastException if other is not an instance of Trader.
	 * 
	 * @param other the reference to an object with which to compare.
	 * @return true if this trader's screen name is the same as other's; false otherwise.
	 */
	public boolean equals(Object other) 
	{
		if (other instanceof Trader)
		{
			return this.compareTo((Trader)other) == 0;
		}
		return false;
	}
	

  /**
   * Creates a new TraderWindow for this trader and saves a reference to 
   * it in myWindow. Removes and displays all the messages, if any, 
   * from this trader's mailbox by calling myWindow.show(msg) for each message.
   */
  public void openWindow()
  {
    this.window = new TraderWindow(this);
    
    while (!this.messages.isEmpty())
    {
      this.window.showMessage(this.messages.remove());
    }
  }
  
  /**
   * Returns true if this trader has any messages in its mailbox.
   * @return  true if this trader has messages; false otherwise.
   */
  public boolean hasMessages()
  {
    return !this.messages.isEmpty();
  }
  

  /**
   * Adds msg to this trader's mailbox and displays all messages. 
   * If this trader is logged in (myWindow is not null) removes 
   * and shows all the messages in the mailbox by calling 
   * myWindow.showMessage(msg) for each msg in the mailbox.
   * 
   * @param msg a message to be added to this trader's mailbox.
   */
  public void receiveMessage(String msg) 
  {
    this.messages.add(msg);

    if (this.window != null)
    {
      while (!this.messages.isEmpty())
      {
        this.window.showMessage(this.messages.remove());
      }
    }
  }

  /**
   * Returns the screen name for this trader.
   * @return  the screen name for this trader.
   */
	public String getName()
	{
		return this.name;
	}

	/**
	 * Returns the password for this trader.
	 * @return the pasword for this trader.
	 */
	public String getPassword()
	{
		return this.pswd;
	}
	
	/**
	 * Requests a quote for a given stock symbol from the 
	 * brokerage by calling brokerage's {@code getQuote}.
	 * 
	 * @param symbol a stock symbol for which a quote is requested.
	 */
	public void	getQuote(String symbol)
	{
		this.brokerage.getQuote(symbol, this);
	}

	/**
	 * Places a given order with the brokerage by calling brokerage's placeOrder.
	 * 
	 * @param order a trading order to be placed.
	 */
	public void placeOrder(TradeOrder order)
	{
		this.brokerage.placeOrder(order);
	}

	/**
	 * Logs out this trader. Calls {@code brokerage}'s {@code logout} for this trader. Sets 
	 * {@code myWindow} to {@code null} (this method is called from a {@code TraderWindow}'s 
	 * window listener when the "close window" button is clicked).
	 */
	public void quit() 
	{
		this.brokerage.logout(this);
		this.window.dispose();
		this.window = null;
	}
}
