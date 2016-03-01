/*
 *GEdge.java	1.13 2014/10/07
 *
 */
package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.geom.QuadCurve2D;
/**
 * A <code>GEdge</code> contains methods by creategraphical edges
 * and add to GCanvas
 *
 * @author  Jefferson Amado Pe&ntilde;a <jeffersonamado@gmail.com>
 * @version 0.13, 2010/11/10
 * @see GCanvas

*/
public class GEdge{
        //~ private Relacion edge;
        private GVertex sVertex, eVertex;
        private Point start;  
        private Point end, edgeLocation;
        public Color edgeColor =new Color((int)(Math.random() * 0x1000000)), letterColor = Color.WHITE;
        private int type=0;
        private double cost;
        /**
        * Creates new graphics edge
        * @param start vertex from start edge
        * @param end  vertex from end edge
        * @param cost value or cost the going from start to end
        * @throws IllegalArgumentException if <tt>cost</tt> is <tt>NaN</tt>
        * @throws IllegalArgumentException if <tt>cost</tt> is <tt>Zero</tt>
        */
        public GEdge(GVertex start, GVertex end,double cost){
                if (Double.isNaN(cost)) throw new IllegalArgumentException("Weight is NaN");                
                //~ if (cost==0) throw new IllegalArgumentException("Weight is zero");                
                sVertex = start;
                eVertex = end;
                this.cost = cost;
        }
	/**
	* This method draw a line in graphical user interface
	*@param ga graphics object 
	*/
	public void drawGEdge(Graphics ga){
		start = sVertex.getGVertexLocation();
		end = eVertex.getGVertexLocation();
		Graphics2D g = (Graphics2D) ga;
		double a = (start.y - end.y);
		double b = (start.x - end.x);
		double m = a / b;
		double grade = Math.atan(m);

		//~ if(edgeColor != null) {
			//~ g.setColor(edgeColor);
		//~ }else{
			g.setColor(edgeColor);
		//~ }
		
		g.setStroke(new BasicStroke( 3.0f ));
		g.drawLine(start.x + 5, start.y + 5, end.x + 5, end.y + 5); //grafica linea
		g.setColor(letterColor);
		g.drawString( cost + "", (start.x + end.x)/2, (start.y + end.y)/2);//valor del costo
	}
        /**
        * Returns the weight of the edge.
        * @return the weight of the edge
        */
        public double weight() {
                return this.cost;
        }        
	/**
	* <p>
        * With this method is obteined the edge location
        * <p/>
        * <b>Warning</b> this method may be generate invalid locations
	*@return Point edge ubication
	*/
	public Point getGEdgeLocation(){
		return edgeLocation;
	}
	/**
	* With this method is obtenied the color of edge
        * 
	*@return Color this color the edge
	*/
	public Color getGEdgeColor(){		
		return edgeColor;
	}
	/**
	* This method settle a new color by edge
	* @param color new color the edge
	* @deprecated
	*/
	@Deprecated
	public void setGEdgeColor(Color color){
		
		double distance = (color.getRed() - 10)*(color.getRed() - 10) + (color.getGreen() - 10)*(color.getGreen() - 10) + (color.getBlue() - 10)*(color.getBlue() - 10);
		if(distance > 10){
			letterColor = Color.BLUE;
		}else{
			letterColor = Color.WHITE;
		}
			
		this.edgeColor = color;
	
	}
        
}

