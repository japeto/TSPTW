/*
 *Site.java	0.1 2014/10/07
 *
 */
package data;
import java.lang.*;
import java.awt.*;
/**
 * A <code>Site</code>
 * provide methods that allow get and set attributes by points,cities
 * etc. that are specific in TSP problem 
 *
 * @author  Jefferson Amado Pe&ntilde;a
 * @version 0.1, 2010/11/10
 * @since   JDK1.0
 */
public class Site{

        private Double[] position = new Double[2];// position.x -position.y
        private int id; //name or identifier
        private String name; // human name or id string
        private Double readyTime; // 
        private Double dueDate; //
        private Double serviceTime; //

        /**
        * Returns an Site object that is create from file and contains all
        * information about this city, point, node
        * <p>
        * This method always returns a new node o city
        * @param id an identifier
        * @param name an name 
        * @param serviceTime an service Time
        * @param readyTime an readyTime
        * @param dueDate an dueDate
        */
        public Site(int id,String name,Double serviceTime,Double readyTime, Double dueDate) {
                this.id = id;
                this.name= name;
                this.readyTime = readyTime;
                this.dueDate = dueDate;
                this.serviceTime = serviceTime;
                this.position[0]= 4.0 + (int)(Math.random()*800);
                this.position[1]= 4.0 + (int)(Math.random()*800);
        }
        /**
        * Returns an Site object that is create from file and contains all
        * information about this city, point, node
        * <p>
         * @param id an identifier
         * @param name an name 
         * @param serviceTime an service Time
         * @param readyTime an readyTime
         * @param dueDate an dueDate
         * @param xCoord an xCoord
         * @param yCoord an yCoord
         */
        public Site(int id,String name,Double serviceTime,Double readyTime,
         Double dueDate,Double xCoord,Double yCoord) {
                this.id = id;
                this.name= name;                
                this.readyTime = readyTime;
                this.dueDate = dueDate;
                this.serviceTime = serviceTime;
                this.position[0]= xCoord;
                this.position[1]= yCoord;
        }
        /**
         * This method <i>override<i/> return a string with contains of
         * this city or point
         * @return a containt this point or city
         * @see Object
         */
        public String toString(){
                return id + " " +
                        name + " " +
                        readyTime + " " +
                        dueDate + " " +
                        serviceTime;
        }
        /**
         * Whit this method is obteined a due date the 
         * this city,point or site
         * @return returns the due date
         */
        public Double getDueDate() {
                return dueDate;
        }
        /**
         * <p>
         * Whit this method is settle the due date the 
         * this city,point or site
         * <p/>
         * <b>Warning<b/> modify this value may be 
         *  generate error
        * @param dueDate a due date
        */
        protected void setDueDate(Double dueDate) {
                this.dueDate = dueDate;
        }
        /**
         * Whit this method is obteined a identifier the 
         * this city,point or site
         *@return returns the identifier
         */
        public int getId() {
                return id;
        }
        /**
         * <p>
         * Whit this method is settle the identifier the 
         * this city,point or site
         * <p/>
         * <b>Warning<b/> modify this value may be 
         *  generate error
         * @param id an identifier
         * 
        */
        protected void setId(int id) {
                this.id = id;
        }  
        /**
         * Whit this method is obteined the name the 
         * this city,point or site
         *@return returns the name
         */
        public String getName() {
                return name;
        }
        /**
         * Whit this method is settle the id  the 
         * this city,point or site
         *@param name an name
         */
        public void setName(String name) {
                this.name = name;
        }     
        /**
         * Whit this method is obteined the ready time the 
         * this city,point or site
         *@return returns the ready time
         */
        public Double getReadyTime() {
                return readyTime;
        }
        /**
         * <p>
         * Whit this method is settle the Ready time the 
         * this city,point or site
         * <p/>
         * <b>Warning:<b/> modify this value may be 
         *  generate error
         *@param readyTime an value
         */
        protected void setReadyTime(Double readyTime) {
                this.readyTime = readyTime;
        }
        /**
         * Whit this method is obteined the service time the 
         * this city,point or site
         *@return returns the service time
         */
        public Double getServiceTime() {
                return serviceTime;
        }
        /**
         * <p>
         * Whit this method is settle the service time the 
         * this city,point or site
         * <p/>
         * <b>Warning:<b/> modify this value may be 
         *  generate error
         *@param serviceTime an value
         */
        protected void setServiceTime(Double serviceTime) {
                this.serviceTime = serviceTime;
        }
        /**
         * <p>
         * Whit this method is obteined the location the 
         * this city,point or site
         * <p/>
         * Allows the use of the abbreviated form get position
         *@return returns the x and y position of the given Site
         */
        public Double[] getLocation() {
                return position;
        }
        /**
         * <p>
         * Whit this method is settle the ubication the 
         * this city,point or site
         * <p/>
         * Allows the use of the abbreviated form locate position
         * @param xCoord the x-coordinate
         * @param yCoord the y-coordinate
         */
        public void setLocation(Double xCoord,Double yCoord) {
                position[0]=xCoord;
                position[1]=yCoord;
        }
        /**
         * <p>
         * Whit this method is settle the ubication the 
         * this city,point or site
         * <p/>
         * Allows the use of the abbreviated form locate position
         * @param pnt object point
         */
        public void setLocation(Point pnt) {
                position[0]=new Double(pnt.x);
                position[1]=new Double(pnt.y);
        }        
        /**
         * Whit this method is obteined x coordiante the 
         * this city,point or site
         *@return returns the x-coordinate of the given Site
         */
        public Double getXCoord() {
                return position[0];
        }
        /**
         * <p>
         * Whit this method is settle the x coordinate the 
         * this city,point or site
         * <p/>
         *@param coord the x-coordinate
         */
        public void setXCoord(Double coord) {
                position[0] = coord;
        }
        /**
         * Whit this method is obteined the y coordinate the 
         * this city,point or site
         *@return returns the y-coordinate of the given Site
         */
        public Double getYCoord() {
                return position[1];
        }
        /**
         * <p>
         * Whit this method is settle the y coordinate the 
         * this city,point or site
         * <p/>
         *@param coord the y-coordinate
         */
        public void setYCoord(Double coord) {
                position[1] = coord;
        }

}

