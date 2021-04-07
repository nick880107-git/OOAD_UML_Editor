package UML;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;

public class CompositionLine extends ConnectionLine{
	
	protected double phi = Math.toRadians(45);
	public CompositionLine(BasicObject a, BasicObject b) {
		super(a, b);
	}
	
public void draw(Graphics2D g) {
		
		double rho = theta + phi;
		double x3_1, y3_1, x3_2, y3_2, x3_3,y3_3;
		x3_1 = x2 - barb * Math.cos(rho);
		y3_1 = y2 - barb * Math.sin(rho);
		rho = theta - phi;
		x3_2 = x2 - barb * Math.cos(rho);
		y3_2 = y2 - barb * Math.sin(rho);
		rho = theta;
		x3_3 = x2 - barb * Math.cos(rho) * Math.sqrt(2) ;
		y3_3 = y2 - barb * Math.sin(rho) * Math.sqrt(2);
		Path2D path = new Path2D.Double();
		Line2D[] lines = {
				new Line2D.Double(x3_1, y3_1, x2, y2),
				new Line2D.Double(x2, y2, x3_2, y3_2),
				new Line2D.Double(x3_2, y3_2, x3_3, y3_3),
				new Line2D.Double(x3_3, y3_3, x3_1, y3_1)
		};
		for(Line2D line : lines) {
			path.append(line, path.getCurrentPoint()!=null);
		}
		path.closePath();
		Shape shape = path;
		

		g.setColor(Color.BLACK);
		g.draw(new Line2D.Double(x,y,x3_3,y3_3));
		g.draw(shape);
	    
	}
	

}
