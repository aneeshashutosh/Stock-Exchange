import java.util.LinkedList;

/**
 * @author Amy Chou, Aneesh Ashutosh, Cam Wong, Seho Young
 * @date 10/01/14
 */

public class Trader implements Comparable<Trader>
{
	private Brokerage brokerage;
	private LinkedList<String> messages;
	private String name;
	private String pswd;
	private TraderWindow window;

	public Trader(Brokerage brokerage, String name, String pswd)
	{
		this.brokerage = brokerage;
		this.name = name;
		this.pswd = pswd;

		messages = new LinkedList<String>();
	}

	public int compareTo(Trader other)
	{
		return this.name.compareToIgnoreCase(other.name);
	}

	public boolean equals(Object other) 
	{
		if (other instanceof Trader)
		{
			return this.compareTo((Trader)other) == 0;
		}
		return false;
	}

	public String getName()
	{
		return this.name;
	}

	public String getPassword()
	{
		return this.pswd;
	}

	public void	getQuote(String symbol)
	{
		this.brokerage.getQuote(symbol, this);
	}

	public boolean hasMessages()
	{
		return !this.messages.isEmpty();
	}

	public void openWindow()
	{
		this.window = new TraderWindow(this);
		
		while (!this.messages.isEmpty())
		{
			this.window.showMessage(this.messages.remove());
		}
	}

	public void placeOrder(TradeOrder order)
	{
		this.brokerage.placeOrder(order);
	}

	public void quit() 
	{
		this.brokerage.logout(this);
		this.window.dispose();
		this.window = null;
	}

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
}
