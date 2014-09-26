/**
 * @author Amy Chou, Aneesh Ashutosh, Cam Wong, Seho Young
 */

public class Trader implements Comparable<Trader>
{
  private Brokerage brokerage;
  private String name;
  private String pswd;
  
  public Trader(Brokerage brokerage, String name, String pswd)
  {
    this.brokerage = brokerage;
    this.name = name;
    this.pswd = pswd;
  }
  
  public int compareTo(Trader other)
  {
    return name.compareToIgnoreCase(other.name);
  }
}