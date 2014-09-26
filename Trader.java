import java.util.LinkedList;

/**
 * @author Amy Chou, Aneesh Ashutosh, Cam Wong, Seho Young
 * @date 10/01/14
 */
import java.util.*;

public class Trader implements Comparable<Trader>
{
<<<<<<< HEAD
  private Brokerage brokerage;
  private String name;
  private String pswd;
  private TraderWindow myWindow;
  private Queue<String> mailbox;
  
  public Trader(Brokerage brokerage, String name, String pswd)
  {
    this.brokerage = brokerage;
    this.name = name;
    this.pswd = pswd;
  }
  
  public String getName()
  {
    return name;
  }
  
  public String getPassword()
  {
    return pswd;
  }
  
  public int compareTo(Trader other)
  {
    return name.compareToIgnoreCase(other.name);
  }
<<<<<<< HEAD
<<<<<<< HEAD
  
  public boolean equals(Trader other)
  {
    return name.equalsIgnoreCase(other.name);
  }
  
  public void openWindow()
  {
    myWindow = new TraderWindow(this);
  }
  
  public boolean hasMessages()
  {
    return !mailbox.isEmpty();
  }
  
  public void receiveMessage(String msg)
  {
    mailbox.add(msg);
  }
  
  public void getQuote(String symbol)
  {
    brokerage.getQuote(symbol, this);
  }
  
  public void placeOrder(TradeOrder order)
  {
    brokerage.placeOrder(order);
  }
  
  public void quit()
  {
    brokerage.logout(this);
    myWindow = null;
  }
}
=======
=======
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
>>>>>>> FETCH_HEAD
}
>>>>>>> FETCH_HEAD
