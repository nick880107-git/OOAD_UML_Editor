package UML;
import java.util.Comparator;

public class CompositeComparator implements Comparator<Composite>{
		@Override
		public int compare(Composite o1, Composite o2) {			
			return o2.GetDepth() - o1.GetDepth();
		}
	}
