/*
 *Algorithms.java	1.0 2014/11/26
 *
 */
package algorithms;
import java.util.*;
import javax.swing.*;
import data.*;
import lpsolve.*;
/**
 * A <code>Algorithms</code> interface class
 * has the following methods
 * @author Jefferson Amado Pe&ntilde;a Torres <jeffersonamado@gmail.com>
 * @version 1.0, 2010/11/11
 * @since   JDK1.0
 */
 
public interface Algorithms{
	
        /***
         * 
         * 
         */
        public void load(Vector<Site> cities,Double[][] paths);
        /**
         * execute the algorithm 
         * @throws may be throw a LpSolveException
         */
        public void compute()throws LpSolveException;        
        /**
         * 
         * @return vector with ids vertex
         */
        public Vector<Integer> getVertexSolution();
        /**
         * @return 
         */
        public String getStringSolution();
        /**
         * With this method is obteined a objetive value
         *@return number of optimal tour
         */
        public Double getObjetiveValue();
        /**
         * Set debug mode in run algorithm
         * @param debug
         */
        public void setDebug(Boolean debug);
        /**
         * @return is debug mode
         */
        public Boolean isDebug();
        public Boolean[] getBoolSolution();
        /**
        * <p>
        * With this method is obtenined <b>all</b> cities
        * from file
        * <p/>
        * @return an cities with all sites
        */
        public Vector<Site> getCities();
        /**
         * <p>
         * This method sets cities in file
         * <p/>
         * <b>Warning:</b> this method may be
         * generate error
         * @param cities
        */
        public void setCities(Vector<Site> cities);
        /**
        * With this method is obteined all paths
        * from file
        * @return an array with all paths into cities
        */
        public Double[][] getPaths();
        /**
         * <p>
         * This method sets paths in file
         * <p/>
         * <b>Warning:</b> this method may be
         * generate error
         * @param paths
         */
        public void setPaths(Double[][] paths);  
        /**
         * With this method it is obteined number of variables 
         * in the funcion objetive
         * @return number variables in funtion objetive 
         */
        public int getNumVariables();
        /**
         * With this method is obteined number of contraints 
         * in the travelin salesman problem instace
         *@return number contraints in the traveling problem
         */
        public int getNumContraints();
        /**
         * With this method settle a number the 
         * variables in funcion objetive
         *@param numVariables number of variables
         */
        public void setNumVariables(int numVariables);
        /**
         * With this method settle a number 
         * the constraints in system
         *@param numConstraints number of constraints
         */
        public void setNumConstraints(int numConstraints);
        /**
         * With this method is obteined all constraints in the system
         * in array format 
         * @return array constraints
         */
        public String[] getContraints();
        /**
         * With this method settle the constraints in
         * the system
         *@param contraints array of contraints 
         */
        public void setConstraints(String[] contraints);
}
