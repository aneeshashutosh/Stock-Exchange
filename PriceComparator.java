import java.util.Comparator;

/**
 * @author Amy Chou, Aneesh Ashutosh, Cam Wong, Seho Young
 * @date 10/01/14
 */

public class PriceComparator implements Comparator<TradeOrder>
{
	private boolean asc;

	public PriceComparator()
	{
		this.asc = true;
	}

	public PriceComparator(boolean asc)
	{
		this.asc = asc;
	}

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