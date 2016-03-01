/*
 *SimpleAlgorithm.java	1.0 2014/11/26
 *
 */
package algorithms;
import java.util.*;
import javax.swing.*;
import data.*;
import lpsolve.*;
/**
 * A <code>SimpleAlgorithm</code> contains
 * the simple solver by TSP regardless of the time windows
 * @author Jefferson Amado Pe&ntilde;a Torres <jeffersonamado@gmail.com>
 * @version 1.0, 2010/11/11
 * @since   JDK1.0
 */
public class SimpleAlgorithm implements Algorithms {

        private int nVariables;
        private int nConstraints;
        private String[] contraints;
        private Vector<Site> cities;
        private Double[][] paths;
        private Boolean[] bSolution={false};
        private String[] strArrSolution={""};
        private String strSolution="";
        private double value=0.0;
        private double wait=0.0;
        private Boolean DEBUG=false;
        private Vector<String> names;
        private LpSolve solver;

        public SimpleAlgorithm(){
                if(DEBUG){
                        System.out.println("::: SimpleAlgorithm is alive :::");
                }
        }

        public void load(Vector<Site> cities,Double[][] paths){

                nVariables=paths.length;
                this.cities=cities;
                this.paths=paths;
                names = new Vector<String>();
                nConstraints=0;
                
        }

        /**
         * {@inheritDoc}
         */
        public void compute() throws LpSolveException{
                //~ // Create a problem with variables and constraints
                 solver = LpSolve.makeLp(nConstraints, nVariables);        
                
                int count=0;
                double[] rowSol =new double[paths.length+1];
                double[][] matIn = new double[paths.length+1][paths.length+1];
                if(DEBUG){
                        System.out.println("Run Objetive function");
                }
                for(int start=1;start<paths[1].length ;start++){
                        double[] rowOut =new double[paths.length+1] ;
                        for(int end=1;end<paths[1].length ;end++){
                                if(start != end){
                                        count++;
                                        names.add("x"+start+""+end+"");
                                        solver.setColName(count,"x"+start+""+end+"");
                                        solver.setBinary(count, true);
                                        rowSol[count]=paths[start][end];
                                        rowOut[count]=1;
                                        matIn[end][count]=1;
                                        if(DEBUG){
                                                System.out.println("ColName-> "+count+", x"+start+""+end+" value "+rowSol[count]);
                                        }
                                }
                        }
                        solver.addConstraint(rowOut, LpSolve.EQ, 1);
                        solver.setRowName(start,"Out-"+start);                        
                        if(DEBUG){
                                System.out.println("Constraint "+solver.getNrows()+" Out by-> [ x"+start+" ...] ");
                        }                         
                } 
                /*Contraint in to node*/
                for(int start=1;start<paths[1].length ;start++){
                        solver.addConstraint(matIn[start], LpSolve.EQ, 1);
                        solver.setRowName(solver.getNrows(),"In-"+start);                        
                        if(DEBUG){
                                System.out.println("Constraint "+solver.getNrows()+" In by-> [ x"+start+"] ");
                        }                        
                }
                /*no cycles*/ 
                /*Xij+Xji<=1*/ 
                //~ double[][] matCy = new double[solver.getNcolumns()+1][paths.length+1]; 
                //~ int id=0;
                //~ for(int start=1;start<paths[1].length ;start++){  
                        //~ for(int end=1;end<paths[1].length ;end++){
                                //~ if(start != end){
                                        //~ id++;
                                        //~ matCy[id][id]=1;
                                        //~ for(int aux=1;aux<solver.getNcolumns()+1 ;aux++){
                                                //~ if(solver.getColName(aux).equals("x"+end+""+start+"")){
                                                        //~ matCy[id][aux]=1;    
                                                //~ }
                                        //~ }                                       
                                //~ }
                        //~ }
//~ 
                //~ }
                //~ for(int start=1;start<solver.getNcolumns()+1 ;start++){
                        //~ solver.addConstraint(matCy[start], LpSolve.LE, 1);
                        //~ solver.setRowName(solver.getNrows(),"Cy-"+start);                        
                        //~ if(DEBUG){
                                //~ System.out.println("Constraint "+solver.getNrows()+" Simple by-> ["+start+"] ");
                        //~ }                     
                //~ }
                for(int start=0;start<names.size();start++){  
                        double[] rowOut =new double[names.size()+1] ;
                        String[] edge = names.get(start).split("");
                        rowOut[getIndexFromNameCol("x"+edge[2]+""+edge[3])]=1;
                        rowOut[getIndexFromNameCol("x"+edge[3]+""+edge[2])]=1;
                        solver.addConstraint(rowOut, LpSolve.LE, 1);
                        solver.setRowName(solver.getNrows(),"Cy-"+start);                        
                        if(DEBUG){
                                System.out.println("Constraint "+solver.getNrows()+" Simple by-> ["+start+"] ");
                        }                         
                }  
                /*Xim+Xmj+Xji<=2*/ 
                //~ String[] elementos = "x12,x13,x14,x15,x21,x23,x24,x25,x31,x32,x34,x35,x41,x42,x43,x45,x51,x52,x53,x54".split(",");
                //~ int n = 2;   //Tipos para escoger
                //~ int r = elementos.length;   //Elementos elegidos
                //~ Perm2(elementos, "", n, r);
                //~ int[] anArray;
                //~ anArray = new int[paths.length];
                //~ for(int i=0;i<paths.length;i++){
                        //~ anArray[i]=0;
                //~ }
                //~ for(int i=1;i<paths.length;i++){
                        //~ dfs(paths,i,anArray,2);
                //~ }
                
                
                /*funcion objetivo*//*Objetive function*/
                solver.setObjFn(rowSol);
                solver.setVerbose(0);
                solver.setMinim() ;
                if(DEBUG){
                        solver.setVerbose(2);
                        solver.printTableau();
                        solver.printLp();
                }
                //~ solver.solve();
                //~ Double objective = solver.getObjective();
                //~ value = solver.getObjective();
                //~ double [] var = solver.getPtrVariables();
                //~ bSolution = new Boolean[var.length];
                //~ 
                //~ points = new Vector<Integer>(cities.size()+1);
                //~ for(int nCstart=0;nCstart< cities.size()+1;nCstart++){
                        //~ points.add(-1);
                //~ }
                //~ for (int i = 0; i < var.length ; i++) {
                        //~ bSolution[i]=false;
                        //~ if(var[i]==1.0){ 
                                //~ String [] edge=solver.getColName(i+1).split("");
                                //~ strSolution +=edge[2]+"->"+edge[3]+", ";
                                //~ if(strSolution.contains("P") && !strSolution.contains("Q")){
                                        //~ points.set(0,new Integer(edge[3]));
                                        //~ System.out.println("XP->  s "+points);                                        
                                //~ }
                                //~ if (strSolution.contains("Q")){
                                        //~ points.set(new Integer(edge[2]),points.get(0));
                                        //~ System.out.println("X-Q  X  s "+points);
                                //~ }
                                //~ if(!strSolution.contains("P") && !strSolution.contains("Q")){
                                        //~ points.set(new Integer(edge[2]),new Integer(edge[3]));
                                        //~ System.out.println("---"+edge[2]+" -- "+edge[3]+" s "+points);
                                     
                                //~ }
                                //~ bSolution[i]=true;
                        //~ }
                        //~ 
                        //~ if(solver.getColName(i+1).contains("w")){
                            //~ wait+= var[i];   
                        //~ }
                        //~ 
                        //~ if(DEBUG){
                                //~ System.out.println("Value of  "+i+" [" + solver.getColName(i+1) +"] = " + var[i]+" "+bSolution[i]);                        
                        //~ }
                //~ }
                
                if(DEBUG){
                        System.out.println("Objective function value from lpsolve: "+value+" waits value: "+wait+" optimal value: "+(wait+value));
                }
                
                solver.deleteLp();
        }
        /**
        * {@inheritDoc}
        */
        public int getIndexColFromName(String name) throws LpSolveException{
                int index;
                for(index=1;index<solver.getNcolumns();index++){
                        if(name.equals(solver.getColName(index)))
                        break;
                }
                if(DEBUG){
                        System.out.println("search: "+name+" seek in "+index+" ncols: "+solver.getNcolumns() );                        
                }                 
                return index;
        }
        /**
        * {@inheritDoc}
        */
        
        public Boolean[] getBoolSolution(){
                return bSolution;
        }
        /**
        * {@inheritDoc}
        */
        
        public Vector<Integer> getVertexSolution(){
                return points;
        }
        /**
        * {@inheritDoc}
        */
        
        public String getStringSolution(){
                return strSolution;
        }
        /**
        * {@inheritDoc}
        */
        
        public Double getObjetiveValue(){
                return wait+value;
        }
        /**
        * {@inheritDoc}
        */
        
        public void setDebug(Boolean debug){
                this.DEBUG=debug;
        }
        /**
        * {@inheritDoc}
        */
        
        public Boolean isDebug(){
                return DEBUG;
        }        
        /**
        * <p>
        * With this method is obtenined <b>all</b> cities
        * from file
        * <p/>
        * @return an cities with all sites
        */
        public Vector<Site> getCities(){
                return cities;
        }
        /**
         * <p>
         * This method sets cities in file
         * <p/>
         * <b>Warning:</b> this method may be
         * generate error
         * @param cities
        */
        public void setCities(Vector<Site> cities){
                this.cities=cities;
        }
        /**
        * With this method is obteined all paths
        * from file
        * @return an array with all paths into cities
        */
        public Double[][] getPaths(){
                return paths;
        }
        /**
         * <p>
         * This method sets paths in file
         * <p/>
         * <b>Warning:</b> this method may be
         * generate error
         * @param paths
         */
        public void setPaths(Double[][] paths){
                this.paths=paths;
        }
        /**
         * {@inheritDoc}
         * For this specific implementation of the interface, you're better off
         * calling the more efficient {@link Algorithms} method, provided you have the
         * additional arguments!
         */
        public int getNumVariables(){
                return nVariables;
        }
        /**
         * {@inheritDoc}
         */
        public int getNumContraints(){
                return nConstraints;
        }
        /**
         * {@inheritDoc}
         */
        public void setNumVariables(int numVariables){
                this.nVariables=numVariables;
        }
        /**
         * {@inheritDoc}
         */
        public void setNumConstraints(int numConstraints){
                this.nConstraints=numConstraints;
        }
        /**
         * {@inheritDoc}
         */
        public String[] getContraints(){
                return contraints;
        }
        /**
         * {@inheritDoc}
         */
        public void setConstraints(String[] contraints){
                this.contraints=contraints;
        }
}


