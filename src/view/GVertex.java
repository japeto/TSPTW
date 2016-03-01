/*
 *GVertex.java	1.13 2014/10/07
 *
 */
package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point; 
/**
 * A <code>GVertex</code> contains methods by creategraphical vertex 
 * and add to GCanvas
 * @author  Jefferson Amado Pe&ntilde;a <jeffersonamado@gmail.com>
 * @version 0.13, 2010/11/10
 * @see GCanvas
*/
public class GVertex{
	private Point vertexLocation;
	private String vertexName;
	private Color vertexColor = Color.BLUE,letterColor= Color.BLACK;
	private static final int RADIO = 10;
        private int id;
	/**
	* Creates new graphics vertex
	* @param x location in x coord
	* @param y location in y coord
	* @param vertexName City vertexName
	*/
	public GVertex(Double x, Double y, String vertexName,int id){
		vertexLocation = new Point(x.intValue(), y.intValue());
		this.vertexName = vertexName;
                this.id=id;
	}
	/**
	*Paints the object by setting up the necessary parameters and then dispatching to the paint procedure for this object.
	*@param g 
	*/
	public void drawGVertex(Graphics g){
		g.setColor(Color.BLACK);
		g.drawString(vertexName, vertexLocation.x, vertexLocation.y);
		g.setColor(vertexColor);
		g.fillOval(vertexLocation.x, vertexLocation.y, 10, 10);
	}
	/**
	*Paints the object by setting up the necessary parameters and then dispatching to the paint procedure for this object.
	*@param g 
	*@param color 
	*/
	public void drawGVertex(Graphics g, Color color){
		g.setColor(letterColor);
		g.drawString(vertexName, vertexLocation.x, vertexLocation.y);
		g.setColor(color);
		g.fillOval(vertexLocation.x, vertexLocation.y, 10, 10);
	}
	/**
	* @param point
	* @return <code>true<code> wheter (x-h)<sup>2</sup>+(y-k)<sup>2</sup>=r<sup>2</sup>,<code>false</code> else
	*/
	public boolean circleEquation(Point point){
		return (((point.x - vertexLocation.x) * (point.x - vertexLocation.x) + (point.y - vertexLocation.y) * (point.y - vertexLocation.y)) <= (RADIO * RADIO));
	}
	/**
	*With this method is obteined the location vertex
	*@return Point vertex location
	*/
	public Point getGVertexLocation(){
		return vertexLocation;
	}
	/**
	* With this method is obteined the name vertex
	* @return String whith vertex name
	*/
	public String getGVertexName(){
		return vertexName;
	}
	/**
	* <p>
        * With this method is settle the vertex location
        * <p/>
        * <b>Warning</b> this method may be generate invalid locations
	*/
	public void setGVertexLocation(Point vertexLocation){
		this.vertexLocation = vertexLocation;
	}
	/**
	*With this method is obtenied the color of vertex
	*@return this color the vertex
	*/
	public Color getGVertexColor(){
		return vertexColor;
	}
	/**
	* This method settle a new color by vertex
	* @param vertexColor
	*/
	public void setGVertexColor(Color vertexColor){
		this.vertexColor = vertexColor;
	}
        /**
	*With this method is obtenied the identifier of vertex
	*@return this color the vertex
	*/
	public int getIdVertex(){
		return this.id;
	}
	/**
	* This method settle a new identifier by vertex
	* @param vertexColor
	*/
	public void setIdVertex(int id){
		this.id = id;
	}
}

