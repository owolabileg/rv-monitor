
import java.util.*;

public class HasNext_1 {
	public static void main(String[] args){
		Vector<Integer> v = new Vector<Integer>();

		v.add(1);
		v.add(2);
		v.add(4);
		v.add(8);

		Iterator i = v.iterator();
		int sum = 0;

		if(i.hasNext()){
			mop.HasNextRuntimeMonitor.hasnextEvent(i);
			mop.HasNextRuntimeMonitor.nextEvent(i);
			sum += (Integer)i.next();
			mop.HasNextRuntimeMonitor.nextEvent(i);
			sum += (Integer)i.next();
			mop.HasNextRuntimeMonitor.nextEvent(i);
			sum += (Integer)i.next();
			mop.HasNextRuntimeMonitor.nextEvent(i);
			sum += (Integer)i.next();
		} else {
			mop.HasNextRuntimeMonitor.hasnextEvent(i);
		}

		System.out.println("sum: " + sum);
		mop.HasNextRuntimeMonitor.endProgEvent();
	}
}


