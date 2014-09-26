import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author Amy Chou, Aneesh Ashutosh, Cam Wong, Seho Young
 * @date 10/01/14
 */

public class Brokerage implements Login
{
	private final StockExchange exchange;
	private TreeMap<String, Trader> registeredUsers;
	private TreeSet<Trader> activeUsers;

	public Brokerage(StockExchange exchange)
	{
		this.exchange = exchange;
		this.registeredUsers = new TreeMap<String, Trader>();
		this.activeUsers = new TreeSet<Trader>();
	}

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

	public void getQuote(String symb, Trader trader)
	{
		trader.receiveMessage(this.exchange.getQuote(symb));
	}

	public void logout(Trader trader)
	{
		this.activeUsers.remove(trader);
	}

	public void placeOrder(TradeOrder order)
	{
		this.exchange.placeOrder(order);
	}
}
