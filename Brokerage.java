/**
 * @author Amy Chou, Aneesh Ashutosh, Cam Wong, Seho Young
 * @date 10/01/14
 */

<<<<<<< HEAD
public class Brokerage
	implements Login {
	private final StockExchange exchange;
	private TreeMap<String, Trader> registeredUsers;
	private TreeSet<Trader> activeUsers;
	
	@Override
	public int addUser(String name, String pswd) {
		if (this.registeredUsers.containsKey(name)) {
			// "this user already exists!"
			return -3;
		}
		if (name.length() < 4 || name.length() > 10) {
			// "invalid username: must be between 4 and 10 chars!"
			return -1;
		}
		if (pswd.length() < 2 || pswd.length() > 10) {
			// "invalid password: must be between 2 and 10 chars!"
			return -2;
		}
		this.registeredUsers.put(name, new Trader(this, name, pswd));
		return 0;
	}
	
	@Override
	public int login(String name, String pswd) {
		if (!this.registeredUsers.containsKey(name)) {
			return -1;
		}
		if (!this.registeredUsers.get(name).getPassword().equals(pswd)) {
			return -2;
		}
		if (this.activeUsers.contains(this.registeredUsers.get(name))) {
			return -3;
		}
		//assert this.registeredUsers.get(name).getPassword().equals(pswd)
		this.activeUsers.add(this.registeredUsers.get(name));
		return 0;
	}
	
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
}
