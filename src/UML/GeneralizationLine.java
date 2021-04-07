package UML;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;


public class GeneralizationLine extends ConnectionLine {

	protected double phi = Math.toRadians(30);
	public GeneralizationLine(BasicObject a, BasicObject b) {
		super(a, b);
	}
	public void draw(Graphics2D g) {
		
		double rho = theta + phi;
		double x3_1, y3_1, x3_2, y3_2;
		x3_1 = x2 - barb * Math.cos(rho);
		y3_1 = y2 - barb * Math.sin(rho);
		rho = theta - phi;
		x3_2 = x2 - barb * Math.cos(rho);
		y3_2 = y2 - barb * Math.sin(rho);
		
		Path2D path = new Path2D.Double();
		Line2D[] lines = {
				new Line2D.Double(x3_1, y3_1, x2, y2),
				new Line2D.Double(x2, y2, x3_2, y3_2),
				new Line2D.Double(x3_2, y3_2, x3_1, y3_1),
		};
		for(Line2D line : lines) {
			path.append(line, path.getCurrentPoint()!=null);
		}
		path.closePath();
		Shape shape = path;		
		
		double m1,b1;
		double m2,b2;
		m1 = (y2-y)/(x2-x);
		m2 = (y3_2-y3_1)/(x3_2-x3_1);
		b1 = y - m1*x;
		b2 = y3_1 - m2*x3_1;
		double intersect_x, intersect_y;
		intersect_x = (b2-b1) / (m1-m2);
		intersect_y = m1 * intersect_x + b1;
		
		g.setColor(Color.BLACK);
		g.draw(new Line2D.Double(x,y,intersect_x,intersect_y));
		g.draw(shape);
	    
	}

}