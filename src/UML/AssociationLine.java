package UML;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class AssociationLine extends ConnectionLine{

	protected double phi = Math.toRadians(40);
	public AssociationLine(BasicObject a, BasicObject b) {
		super(a, b);		
	}
	
	public void draw(Graphics2D g) {
		
		double x3, y3, rho = theta + phi;
		g.setColor(Color.BLACK);
		for(int j = 0; j < 2; j++) {
			x3 = x2 - barb * Math.cos(rho);
			y3 = y2 - barb * Math.sin(rho);
			g.draw(new Line2D.Double(x2,y2,x3,y3));
			rho = theta - phi;
		}
		g.draw(new Line2D.Double(x,y,x2,y2));
	    
	}

}
