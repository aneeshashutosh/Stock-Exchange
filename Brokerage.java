import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author Amy Chou, Aneesh Ashutosh, Cam Wong, Seho Young
 * @date 10/01/14
 */

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
public class Brokerage
	implements Login {
	private final StockExchange exchange;
	private TreeMap<String, Trader> registeredUsers;
	private TreeSet<Trader> activeUsers;
	
=======
public class Brokerage
implements Login {
=======
public class Brokerage implements Login
{
>>>>>>> FETCH_HEAD
	private final StockExchange exchange;
	private TreeMap<String, Trader> registeredUsers;
	private TreeSet<Trader> activeUsers;

<<<<<<< HEAD
>>>>>>> FETCH_HEAD
	@Override
	public int addUser(String name, String pswd) {
		if (this.registeredUsers.containsKey(name)) {
			// "this user already exists!"
			return -3;
		}
		if (name.length() < 4 || name.length() > 10) {
=======
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
>>>>>>> FETCH_HEAD
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
<<<<<<< HEAD
	
=======

<<<<<<< HEAD
>>>>>>> FETCH_HEAD
	@Override
	public int login(String name, String pswd) {
		if (!this.registeredUsers.containsKey(name)) {
=======
	public int login(String name, String password)
	{
		Trader user = (Trader)this.registeredUsers.get(name);

		if (user == null || !this.registeredUsers.containsKey(name))
		{
>>>>>>> FETCH_HEAD
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
<<<<<<< HEAD
	
	public void getQuote(String symb, Trader tr) {
		tr.receiveMessage(this.exchange.getQuote(symb));
	}
	
	public void logout(Trader tr) {
		this.activeUsers.remove(tr);
	}
	
	public void placeOrder(TradeOrder order) {
		this.exchange.placeOrder(order);
	}
	
	public Brokerage(StockExchange exchange) {
		this.exchange = exchange;
		this.registeredUsers = new TreeMap<>();
		this.activeUsers = new TreeSet<>();
	}
=======
public class Brokerage {

>>>>>>> parent of 258b852... Implement Login methods for Brokerage
=======

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
<<<<<<< HEAD

	public Brokerage(StockExchange exchange) {
		this.exchange = exchange;
		this.registeredUsers = new TreeMap<>();
		this.activeUsers = new TreeSet<>();
	}
>>>>>>> FETCH_HEAD
=======
>>>>>>> FETCH_HEAD
}
