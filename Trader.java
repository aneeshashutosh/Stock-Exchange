/**
 * @author Amy Chou, Aneesh Ashutosh, Cam Wong, Seho Young
 * @date 10/01/14
 */
import java.util.*;

public class Trader implements Comparable<Trader>
{
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