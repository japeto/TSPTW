/*
 *GCanvas.java	1.13 2014/10/07
 *
 */
package view;
import data.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JComponent;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
/**
 * A <code>GCanvas</code> contains methods by create graphical elements
 * in user interface, this as board
 *
 * @author  Jefferson Amado Pe&ntilde;a <jeffersonamado@gmail.com>
 * @version 0.13, 2010/11/10
 * @see java.awt.Graphics2D
 * @see JComponent
 * @version 0.1
*/
public class GCanvas extends JComponent implements MouseListener, MouseMotionListener,MouseWheelListener{
	
	static final long serialVersionUID = 2L;
	private ArrayList<GVertex> arrayVertex= new ArrayList<GVertex>();
	private ArrayList<GEdge> arrayEdges=  new ArrayList<GEdge>();
	private GVertex anomVertex[];
	private Point a, b;
	private int index;
	private Color background,nodesColor,waysColor;
	private int scale = 1;
	private int speed = 200;
        private Vector<Site> map;
        //~ private String printorder="";
	/**
	*Constructs a new Canvas
	*/
	public GCanvas(){
		addMouseListener(this);
		addMouseMotionListener(this);
		setPreferredSize(new Dimension(900, 900));
	}
	/**
	 * This method sketch cities, vertex or points and ways,edges in
	 * the graphical interface
	 * @param map contains all cities,points to draw in graphical
	 * interface
	 * @param paths contains all paths into cities, points to draw in
	 * graphical interface
	 */
	public void sketch(Vector<Site> map,Double[][] paths){


		this.anomVertex = new GVertex[2];
 		this.anomVertex[0]=null;
 		this.anomVertex[1]=null;
                this.map = map;
 		for(int city=0;city<map.size();city++){
                        Site site=map.get(city);
                        GVertex gv=new GVertex(site.getXCoord(),site.getYCoord(),site.getName()+" "+site.getServiceTime(),site.getId());
                                gv.drawGVertex(this.getGraphics());
                                arrayVertex.add(gv);
                                //~ System.out.println("GCvs cities <:"+
                                //~ map.get(city).getName()+">");
 		}

		//~ System.out.println(arrayVertex.get(0)+" >");
 		for(int startroad=1;startroad<map.size()+1;startroad++){
                        for(int endroad=1;endroad<map.size()+1;endroad++){
                                if(paths[startroad][endroad]!=null){
                                        GEdge ge=new GEdge(arrayVertex.get(startroad-1),arrayVertex.get(endroad-1),paths[startroad][endroad]);
                                        ge.drawGEdge(this.getGraphics());
                                        arrayEdges.add(ge);
                                        try	{
                                          Thread.sleep(speed);
                                        }catch(InterruptedException ex){System.err.println("problema en el hilo sleep, GCanvas@sketch(...)");}
                                        //~ System.out.println("Gcvs path ("+startroad+" "+
                                        //~ endroad+")"+paths[startroad][endroad]+">");
                                }

                        }
 		}
	}
        /**
	 * This method sketch cities, vertex or points and ways,edges in
	 * the graphical interface
	 * @param map contains all cities,points to draw in graphical
	 * interface
	 * @param paths contains all paths into cities, points to draw in
	 * graphical interface
	 */
	public void sketch(Vector<Site> map,Double[][] paths,Vector<Integer> citiesId){


		this.anomVertex = new GVertex[2];
 		this.anomVertex[0]=null;
 		this.anomVertex[1]=null;

 		for(int city=0;city<map.size();city++){
                        Site site=map.get(city);
                        GVertex gv=new GVertex(site.getXCoord(),site.getYCoord(),site.getName()+" "+site.getServiceTime(),site.getId());
                                gv.drawGVertex(this.getGraphics());
                                arrayVertex.add(gv);
                                //~ System.out.println("GCvs cities <:"+citiesId.size()+">");
 		}
                createEdgesInSolution(citiesId,paths,citiesId.get(0),citiesId.size()-1,citiesId.get(0));
                //~ GEdge ge=new GEdge(arrayVertex.get(citiesId.get(0)-1),arrayVertex.get(endroad-1),paths[startroad][endroad]);
                //~ ge.drawGEdge(this.getGraphics());
                //~ arrayEdges.add(ge);
                //~ try{
                  //~ Thread.sleep(speed);
                //~ }catch(InterruptedException ex){
                        //~ System.err.println("problema en el hilo sleep, GCanvas@sketch(...)");
                //~ }

	}
        private void createEdgesInSolution(Vector<Integer> citiesId,Double[][] paths,int start,int depth,int header){
                if(depth >= 0){ 
                        //~ System.out.println("visiting " +start+" >> "+depth);               
                        int next=citiesId.get(start);
                        if(next>0){                                
                                GEdge ge=new GEdge(arrayVertex.get(start-1),arrayVertex.get(next-1),paths[start][next]);
                                ge.drawGEdge(this.getGraphics());
                                arrayEdges.add(ge);
                                try{
                                        Thread.sleep(speed);
                                }catch(InterruptedException ex){
                                        System.err.println("problema en el hilo sleep, GCanvas@sketch(...)");
                                }
                                createEdgesInSolution(citiesId,paths,next,depth-1,header);
                        }else{
                                
                                
                                next=header;
                                GEdge ge=new GEdge(arrayVertex.get(start-1),arrayVertex.get(next-1),paths[start][next]);
                                ge.drawGEdge(this.getGraphics());
                                arrayEdges.add(ge);
                        }
                }
        }
        /**
	 * This method sketch cities, vertex or points and ways,edges in
	 * the graphical interface
	 * @param map contains all cities,points to draw in graphical
	 * interface
	 * @param paths contains all paths into cities, points to draw in
	 * graphical interface
	 */
        public void sketch(Vector<Site> map,Double[][] paths,Boolean[] draw){


		this.anomVertex = new GVertex[2];
 		this.anomVertex[0]=null;
 		this.anomVertex[1]=null;

 		for(int city=0;city<map.size();city++){
                        Site site=map.get(city);
                        GVertex gv=new GVertex(site.getXCoord(),site.getYCoord(),site.getName()+" "+site.getServiceTime(),site.getId());
                                gv.drawGVertex(this.getGraphics());
                                arrayVertex.add(gv);
                                //~ System.out.println("GCvs cities <:"+
                                //~ map.get(city).getName()+">");
 		}
                int id=0;
		//~ System.out.println(arrayVertex.get(0)+" >");
 		for(int startroad=1;startroad<map.size()+1;startroad++){
                        for(int endroad=1;endroad<map.size()+1;endroad++){
                                if(paths[startroad][endroad]!=null ){ 
                                        if(draw[id]){
                                                //~ System.out.println("arista que va >"+ id +"  x"+startroad+""+endroad+"");                                                
                                                GEdge ge=new GEdge(arrayVertex.get(startroad-1),arrayVertex.get(endroad-1),paths[startroad][endroad]);
                                                ge.drawGEdge(this.getGraphics());
                                                arrayEdges.add(ge);
                                                try	{
                                                  Thread.sleep(speed);
                                                }catch(InterruptedException ex){System.err.println("problema en el hilo sleep, GCanvas@sketch(...)");}
                                                //~ System.out.println("Gcvs path ("+startroad+" "+
                                                //~ endroad+")"+paths[startroad][endroad]+">");
                                        }
                                        id++;
                                }
                        }
                        
 		}
	} 
	/**
	 * This method clear <b>all</b> object graphical in the
	 * user interface and clear of instance file
	 */
	public void scratchOut(){
			this.arrayVertex.clear();
			this.arrayEdges.clear();
			this.removeAll();
			this.updateUI();
			this.repaint();

	}
	/**
	 * This method set speed to graphics edges
	 * @param speed speed to graphics edges
	 */
	public void setSpeed(int speed){
		this.speed=speed;
	}
	/**
	 * With this method is obteined the speed value
	 * @return speed
	 */
	public int getSpeed(){
		return speed;
	}
	/**
	*{@inheritDoc}
	*/
	@Override
	public void paintComponent(Graphics g){
		g.setColor(background);
		g.fillRect(0, 0, getWidth(), getHeight());
		for (int i = 0; i < arrayEdges.size(); i++){
			arrayEdges.get(i).drawGEdge(g);
		}
		for (int i = 0; i < arrayVertex.size(); i++){
			arrayVertex.get(i).drawGVertex(g);
		}
	}
	/**
	 * Whit this method set a new Color background
	 * @param background new color background
	 */
	public void setBackground(Color background){
		this.background= background;
		repaint();
	}
	/**
	 * @return color from background in canvas
	 */
	public Color getBackground(){
		return background;
	}
        /**
         * {@inheritDoc}
         */
	public void mousePressed(MouseEvent arg0){
		index=0;
		for (int i = 0; i < arrayVertex.size(); i++){
			if (arrayVertex.get(i).circleEquation(arg0.getPoint())){
				arrayVertex.get(i).setGVertexColor(Color.RED);
				anomVertex[index] = arrayVertex.get(i);
				break;
			}
		}
		index=0;
		this.repaint();
 	}
        /**
         * {@inheritDoc}
         */
	public void mouseClicked(MouseEvent evento){}
        /**
         * {@inheritDoc}
         */
	public void mouseEntered(MouseEvent arg0){}
        /**
         * {@inheritDoc}
         */
 	public void mouseExited(MouseEvent arg0){}
        /**
         * {@inheritDoc}
         */
	public void mouseReleased(MouseEvent arg0){
                try{
                
 		if(anomVertex[0] != null)
 			anomVertex[0].setGVertexColor(Color.BLUE);
 		this.repaint();

 		index=0;
 		anomVertex[0]=null;
 		anomVertex[1]=null;      
                
                }catch(Exception a){
			//~ System.err.println("arrastra sin objetivo");
		}
 	}
        /**
         * {@inheritDoc}
         */
 	public void mouseDragged(MouseEvent e) {
 		try{
			Point location=new Point(e.getX()-5,e.getY()-5);
			anomVertex[0].setGVertexLocation(location);
                        map.get(anomVertex[0].getIdVertex()-1).setLocation(location);
			this.repaint();
		}catch(Exception a){
			//~ System.err.println("arrastra sin objetivo");
		}
 	}
        /**
         * {@inheritDoc}
         */
 	public void mouseMoved(MouseEvent e) {
		for (int i = 0; i < arrayVertex.size(); i++){
			if (arrayVertex.get(i).circleEquation(e.getPoint()))
				arrayVertex.get(i).drawGVertex(this.getGraphics(), Color.RED);
			else
				arrayVertex.get(i).drawGVertex(this.getGraphics(), Color.BLUE);
		}
 	}
	public void mouseWheelMoved(MouseWheelEvent e) {
		//~ int delta = 5 * (int) e.getPreciseWheelRotation();
		//~ scale +=  delta;	
		//~ this.repaint();
	}
}
//Jefferson Amado Peña Torres
