import java.util.*;

public class HasNext2_1 {
	public static void main(String[] args) {
		Vector<Integer> v = new Vector<Integer>();
		v.add(1);
		v.add(2);

		Iterator i = v.iterator();
		int sum = 0;

		mop.HasNext2RuntimeMonitor.hasnextEvent(i);
		if (i.hasNext()) {
			mop.HasNext2RuntimeMonitor.nextEvent(i);
			sum += (Integer)i.next();
			// JavaMOP should match "next next" on the following event
			mop.HasNext2RuntimeMonitor.nextEvent(i);
			sum += (Integer)i.next();
		}

		System.out.println("sum: " + sum);
	}
}
