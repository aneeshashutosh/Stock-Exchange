import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author Amy Chou, Aneesh Ashutosh, Cam Wong, Seho Young
 * @date 10/01/14
 * 
 * Constructs new brokerage affiliated with a given stock exchange.
 */

public class Brokerage implements Login
{
	private final StockExchange exchange;
	private TreeMap<String, Trader> registeredUsers;
	private TreeSet<Trader> activeUsers;

	/**
	 * Constructs new brokerage affiliated with a given stock exchange. 
	 * Initializes the map of traders to an empty map (a TreeMap), keyed 
	 * by trader's name; initializes the set of active (logged-in) traders 
	 * to an empty set (a TreeSet).
	 * @param exchange   a stock exchange
	 */
	public Brokerage(StockExchange exchange)
	{
		this.exchange = exchange;
		this.registeredUsers = new TreeMap<String, Trader>();
		this.activeUsers = new TreeSet<Trader>();
	}

	/**
	 * Tries to register a new trader with a given screen name and password. 
	 * If successful, creates a Trader object for this trader and adds this 
	 * trader to the map of all traders (using the screen name as the key).
	 * 
	 * @param name       the screen name of the trader
	 * @param password   the password for the trader
	 * 
	 * @return <pre> 0 if successful, or an error code (a negative integer) 
   * if failed: 
   * -1 -- invalid screen name (must be 4-10 chars)
   * -2 -- invalid password (must be 2-10 chars)
   * -3 -- the screen name is already taken. </pre>
	 */
	public int addUser(String name, String password)
	{
		if (name.length() < 4 || name.length() > 10)
		{
			// "invalid username: must be between 4 and 10 chars!"
			return -1;
		}
		if (password.length() < 2 || password.length() > 10)
		{
			// "invalid password: must be between 2 and 10 chars!"
			return -2;
		}
		if (this.registeredUsers.containsKey(name))
		{
			// "this user already exists!"
			return -3;
		}

		this.registeredUsers.put(name, new Trader(this, name, password));
		return 0;
	}

	/**
	 * Tries to login a trader with a given screen name and password. If 
	 * no messages are waiting for the trader, sends a "Welcome to SafeTrade!" 
	 * message to the trader. Opens a dialog window for the trader by calling 
	 * trader's openWindow() method. Adds the trader to the set of all logged-in traders.
	 * 
	 * @param name       the screen name of the trader
   * @param password   the password for the trader
   * 
   * @return <pre> 0 if successful, or an error code (a negative integer) 
   * if failed: 
   * -1 -- invalid screen name (must be 4-10 chars)
   * -2 -- invalid password (must be 2-10 chars)
   * -3 -- the screen name is already taken. </pre>
	 */
	public int login(String name, String password)
	{
		Trader user = (Trader)this.registeredUsers.get(name);

		if (user == null || !this.registeredUsers.containsKey(name))
		{
			return -1;
		}
		if (!this.registeredUsers.get(name).getPassword().equals(password))
		{
			return -2;
		}
		if (this.activeUsers.contains(this.registeredUsers.get(name)))
		{
			return -3;
		}

		if (!user.hasMessages())
		{
			user.receiveMessage("Welcome to SafeTrade!");
		}
		
		user.openWindow();

		//assert this.registeredUsers.get(name).getPassword().equals(pswd)
		this.activeUsers.add(this.registeredUsers.get(name));
		return 0;
	}
	
	/**
	 * Removes a specified trader from the set of logged-in traders
	 * 
	 * @param trader   the trader that logs out.
	 */
  public void logout(Trader trader)
  {
    this.activeUsers.remove(trader);
  }

  /**
   * Requests a quote for a given stock from the stock exchange and 
   * passes it along to the trader by calling trader's receiveMessage method.
   * @param symbol      the stock symbol
   * @param trader      the trader who requested a quote
   */
	public void getQuote(String symbol, Trader trader)
	{
		trader.receiveMessage(this.exchange.getQuote(symbol));
	}

	/**
	 * Places an order at the stock exchange.
	 * @param order      an order to be placed at the stock exchange
	 */
	public void placeOrder(TradeOrder order)
	{
		this.exchange.placeOrder(order);
	}
}
