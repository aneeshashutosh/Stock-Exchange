import java.util.Comparator;

/**
 * @author Amy Chou, Aneesh Ashutosh, Cam Wong, Seho Young
 * @date 10/01/14
 * 
 * A price comparator for trade orders
 */

public class PriceComparator implements Comparator<TradeOrder>
{
	private boolean asc;

	/**
	 * Constructs a price comparator that compares two orders in ascending 
	 * order. Sets the private boolean ascending flag to true.
	 */
	public PriceComparator()
	{
		this.asc = true;
	}

	/**
	 * Constructs a price comparator that compares two orders in 
	 * ascending or descending order
	 * @param asc  if true, make an ascending comparator; otherwise 
	 * make a descending comparator
	 */
	public PriceComparator(boolean asc)
	{
		this.asc = asc;
	}

	/**
	 * Compares two trade orders
	 * @param order1   the first order
	 * @param order2   the second order
	 * @return         {0 if both orders are market orders;
	 *                 -1 if order1 is market and order2 is limit;
	 *                  1 if order1 is limit and order2 is market;
	 *                  the difference in prices, rounded to the 
	 *                  nearest cent, if both order1 and order2 are 
	 *                  limit orders. In the latter case, the difference 
	 *                  returned is cents1 - cents2 or cents2 - cents1, 
	 *                  depending on whether this is an ascending or 
	 *                  descending comparator (ascending is true or false).}
	 * 
	 */
	public int compare(TradeOrder order1, TradeOrder order2)
	{
		if ((order1.isMarket()) && (order2.isMarket()))
		{
			return 0;
		}
		else if ((order1.isMarket()) && (order2.isLimit()) || (order1.isLimit()) && (order2.isMarket()))
		{
			return -1 * (int) Math.random() * 99; //Fun random negative value (kind of unnecessary, but what's compsci without some fun? :P )
		}

		if (this.asc)
		{
			return (int) Math.round(100D * order1.getPrice()) - (int) Math.round(100D * order2.getPrice());
		}

		return (int) Math.round(100D * order2.getPrice()) - (int) Math.round(100D * order1.getPrice());
	}
}