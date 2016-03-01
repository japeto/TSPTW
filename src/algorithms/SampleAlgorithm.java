/*
 *SampleAlgorithm.java	1.0 2014/11/26
 *
 */
package algorithms;
import java.util.*;
import javax.swing.*;
import data.*;
import lpsolve.*;
/**
 * A <code>SampleAlgorithm</code> contains
 * the sample problem the model
 * @author Jefferson Amado Pe&ntilde;a Torres <jeffersonamado@gmail.com>
 * @version 1.0, 2010/11/11
 * @since   JDK1.0
 */
public class SampleAlgorithm implements Algorithms {
        
        private int nVariables=0;
        private int nConstraints=0;
        private String[] contraints=null;
        private Vector<Site> cities=null;
        private Double[][] paths=null;
        private Boolean[] bSolution={false};
        private String[] strArrSolution={""};
        private String strSolution="";
        private double value=0.0;
        private Boolean DEBUG=false;
        
        public SampleAlgorithm(){
                System.out.println("::: SampleAlgorithm is alive :::");
        }

        public void load(Vector<Site> cities,Double[][] paths){
                //~ nVariables=paths.length;
                nVariables=20;
                this.paths=paths;
                nConstraints=0;
                //~ System.out.println("::: Values load :::"+paths.length);
        }

        /**
         * {@inheritDoc}
         */
        public void compute() throws LpSolveException{
                //~ System.out.println("::: Compute :::");
                //~ // Create a problem with variables and constraints
                LpSolve solver = LpSolve.makeLp(0, nVariables);
                //~ solver.setLpName("Simple Problem TSP");

        //~ double[] rowSol ={/*z*/0,/*x12*/3,/*x13*/2,/*x14*/5,/*x15*/12,
                                 //~ /*x21*/3,/*x23*/5,/*x24*/6,/*x25*/7,
                                 //~ /*x31*/4,/*x32*/5,/*x34*/7,/*x35*/8,
                                 //~ /*x41*/5,/*x42*/2,/*x43*/7,/*x45*/9,
                                 //~ /*x51*/2,/*x52*/7,/*x53*/8,/*x54*/9};
        
        
        //~ double[] row ={/*z*/0,/*x12*/0,/*x13*/0,/*x14*/0,/*x15*/0,
                                 //~ /*x21*/0,/*x23*/0,/*x24*/0,/*x25*/0,
                                 //~ /*x31*/0,/*x32*/0,/*x34*/0,/*x35*/0,
                                 //~ /*x41*/0,/*x42*/0,/*x43*/0,/*x45*/0,
                                 //~ /*x51*/0,/*x52*/0,/*x53*/0,/*x54*/0};
        
        double[] row1 ={/*z*/0,/*x12*/1,/*x13*/1,/*x14*/1,/*x15*/1,
                                 /*x21*/0,/*x23*/0,/*x24*/0,/*x25*/0,
                                 /*x31*/0,/*x32*/0,/*x34*/0,/*x35*/0,
                                 /*x41*/0,/*x42*/0,/*x43*/0,/*x45*/0,
                                 /*x51*/0,/*x52*/0,/*x53*/0,/*x54*/0};
                                 
        double[] row2 ={/*z*/0,/*x12*/0,/*x13*/0,/*x14*/0,/*x15*/0,
                                 /*x21*/1,/*x23*/1,/*x24*/1,/*x25*/1,
                                 /*x31*/0,/*x32*/0,/*x34*/0,/*x35*/0,
                                 /*x41*/0,/*x42*/0,/*x43*/0,/*x45*/0,
                                 /*x51*/0,/*x52*/0,/*x53*/0,/*x54*/0};
                                 
        double[] row3 ={/*z*/0,/*x12*/0,/*x13*/0,/*x14*/0,/*x15*/0,
                                 /*x21*/0,/*x23*/0,/*x24*/0,/*x25*/0,
                                 /*x31*/1,/*x32*/1,/*x34*/1,/*x35*/1,
                                 /*x41*/0,/*x42*/0,/*x43*/0,/*x45*/0,
                                 /*x51*/0,/*x52*/0,/*x53*/0,/*x54*/0};
                                 
        double[] row4 ={/*z*/0,/*x12*/0,/*x13*/0,/*x14*/0,/*x15*/0,
                                 /*x21*/0,/*x23*/0,/*x24*/0,/*x25*/0,
                                 /*x31*/0,/*x32*/0,/*x34*/0,/*x35*/0,
                                 /*x41*/1,/*x42*/1,/*x43*/1,/*x45*/1,
                                 /*x51*/0,/*x52*/0,/*x53*/0,/*x54*/0}; 
                                 
         double[] row5 ={/*z*/0,/*x12*/0,/*x13*/0,/*x14*/0,/*x15*/0,
                                 /*x21*/0,/*x23*/0,/*x24*/0,/*x25*/0,
                                 /*x31*/0,/*x32*/0,/*x34*/0,/*x35*/0,
                                 /*x41*/0,/*x42*/0,/*x43*/0,/*x45*/0,
                                 /*x51*/1,/*x52*/1,/*x53*/1,/*x54*/1};
        /***/
        double[] row6 ={/*z*/0,/*x12*/0,/*x13*/0,/*x14*/0,/*x15*/0,
                                 /*x21*/1,/*x23*/0,/*x24*/0,/*x25*/0,
                                 /*x31*/1,/*x32*/0,/*x34*/0,/*x35*/0,
                                 /*x41*/1,/*x42*/0,/*x43*/0,/*x45*/0,
                                 /*x51*/1,/*x52*/0,/*x53*/0,/*x54*/0};                                 
                                 
        double[] row7 ={/*z*/0,/*x12*/1,/*x13*/0,/*x14*/0,/*x15*/0,
                                 /*x21*/0,/*x23*/0,/*x24*/0,/*x25*/0,
                                 /*x31*/0,/*x32*/1,/*x34*/0,/*x35*/0,
                                 /*x41*/0,/*x42*/1,/*x43*/0,/*x45*/0,
                                 /*x51*/0,/*x52*/1,/*x53*/0,/*x54*/0};    
                                                              
        double[] row8 ={/*z*/0,/*x12*/0,/*x13*/1,/*x14*/0,/*x15*/0,
                                 /*x21*/0,/*x23*/1,/*x24*/0,/*x25*/0,
                                 /*x31*/0,/*x32*/0,/*x34*/0,/*x35*/0,
                                 /*x41*/0,/*x42*/0,/*x43*/1,/*x45*/0,
                                 /*x51*/0,/*x52*/0,/*x53*/1,/*x54*/0};                                 

        double[] row9 ={/*z*/0,/*x12*/0,/*x13*/0,/*x14*/1,/*x15*/0,
                                 /*x21*/0,/*x23*/0,/*x24*/1,/*x25*/0,
                                 /*x31*/0,/*x32*/0,/*x34*/1,/*x35*/0,
                                 /*x41*/0,/*x42*/0,/*x43*/0,/*x45*/0,
                                 /*x51*/0,/*x52*/0,/*x53*/0,/*x54*/1};
                                 
        double[] row10 ={/*z*/0,/*x12*/0,/*x13*/0,/*x14*/0,/*x15*/1,
                                 /*x21*/0,/*x23*/0,/*x24*/0,/*x25*/1,
                                 /*x31*/0,/*x32*/0,/*x34*/0,/*x35*/1,
                                 /*x41*/0,/*x42*/0,/*x43*/0,/*x45*/1,
                                 /*x51*/0,/*x52*/0,/*x53*/0,/*x54*/0};                                         
                int id=0;
                double[] rowSol =new double[paths.length+1] ;
                for(int start=1;start<paths[1].length ;start++){
                        double[] row =new double[paths.length+1] ;
                        for(int end=1;end<paths[1].length ;end++){
                                if(start != end){
                                        id++;
                                        rowSol[id]=paths[start][end];
                                        //~ System.out.println("names-> "+id+", x"+start+""+end+" : "+rowSol[id]);
                                        row[id]=1;
                                        System.out.println("x-> "+id+" "+start+" "+end+" ");
                                }
                        }
                        solver.addConstraint(row, LpSolve.EQ, 1);
                        System.out.println("x-> 1");
                        
                }
                
                solver.setColName( 1,"x12");
                solver.setColName( 2,"x13");
                solver.setColName( 3,"x14");
                solver.setColName( 4,"x15");
                
                solver.setColName( 5,"x21");
                solver.setColName( 6,"x23");
                solver.setColName( 7,"x24");
                solver.setColName( 8,"x25");
                
                solver.setColName(9,"x31");
                solver.setColName(10,"x32");
                solver.setColName(11,"x34");
                solver.setColName(12,"x35");
                
                solver.setColName(13,"x41");
                solver.setColName(14,"x42");
                solver.setColName(15,"x43");
                solver.setColName(16,"x45");
                
                solver.setColName(17,"x51");             
                solver.setColName(18,"x52");
                solver.setColName(19,"x53");
                solver.setColName(20,"x54");

                solver.setObjFn(rowSol);
                //add constraints
                solver.addConstraint(row1, LpSolve.EQ, 1);
                solver.addConstraint(row2, LpSolve.EQ, 1);
                solver.addConstraint(row3, LpSolve.EQ, 1);
                solver.addConstraint(row4, LpSolve.EQ, 1);
                solver.addConstraint(row5, LpSolve.EQ, 1);
                
                solver.addConstraint(row6, LpSolve.EQ, 1);
                solver.addConstraint(row7, LpSolve.EQ, 1);
                solver.addConstraint(row8, LpSolve.EQ, 1);
                solver.addConstraint(row9, LpSolve.EQ, 1);
                solver.addConstraint(row10, LpSolve.EQ, 1);
                
        double[] row11 ={/*z*/0,/*x12*/1,/*x13*/0,/*x14*/0,/*x15*/0,
                                 /*x21*/1,/*x23*/0,/*x24*/0,/*x25*/0,
                                 /*x31*/0,/*x32*/0,/*x34*/0,/*x35*/0,
                                 /*x41*/0,/*x42*/0,/*x43*/0,/*x45*/0,
                                 /*x51*/0,/*x52*/0,/*x53*/0,/*x54*/0};
        double[] row12 ={/*z*/0,/*x12*/0,/*x13*/1,/*x14*/0,/*x15*/0,
                                 /*x21*/0,/*x23*/0,/*x24*/0,/*x25*/0,
                                 /*x31*/1,/*x32*/0,/*x34*/0,/*x35*/0,
                                 /*x41*/0,/*x42*/0,/*x43*/0,/*x45*/0,
                                 /*x51*/0,/*x52*/0,/*x53*/0,/*x54*/0};
        double[] row13 ={/*z*/0,/*x12*/0,/*x13*/0,/*x14*/1,/*x15*/0,
                                 /*x21*/0,/*x23*/0,/*x24*/0,/*x25*/0,
                                 /*x31*/0,/*x32*/0,/*x34*/0,/*x35*/0,
                                 /*x41*/1,/*x42*/0,/*x43*/0,/*x45*/0,
                                 /*x51*/0,/*x52*/0,/*x53*/0,/*x54*/0};
        double[] row14 ={/*z*/0,/*x12*/0,/*x13*/0,/*x14*/0,/*x15*/1,
                                 /*x21*/0,/*x23*/0,/*x24*/0,/*x25*/0,
                                 /*x31*/0,/*x32*/0,/*x34*/0,/*x35*/0,
                                 /*x41*/0,/*x42*/0,/*x43*/0,/*x45*/0,
                                 /*x51*/1,/*x52*/0,/*x53*/0,/*x54*/0};
        double[] row15 ={/*z*/0,/*x12*/0,/*x13*/0,/*x14*/0,/*x15*/0,
                                 /*x21*/0,/*x23*/1,/*x24*/0,/*x25*/0,
                                 /*x31*/0,/*x32*/1,/*x34*/0,/*x35*/0,
                                 /*x41*/0,/*x42*/0,/*x43*/0,/*x45*/0,
                                 /*x51*/0,/*x52*/0,/*x53*/0,/*x54*/0};
        double[] row16 ={/*z*/0,/*x12*/0,/*x13*/0,/*x14*/0,/*x15*/0,
                                 /*x21*/0,/*x23*/0,/*x24*/1,/*x25*/0,
                                 /*x31*/0,/*x32*/0,/*x34*/0,/*x35*/0,
                                 /*x41*/0,/*x42*/1,/*x43*/0,/*x45*/0,
                                 /*x51*/0,/*x52*/0,/*x53*/0,/*x54*/0};
        double[] row17 ={/*z*/0,/*x12*/0,/*x13*/0,/*x14*/0,/*x15*/0,
                                 /*x21*/0,/*x23*/0,/*x24*/0,/*x25*/1,
                                 /*x31*/0,/*x32*/0,/*x34*/0,/*x35*/0,
                                 /*x41*/0,/*x42*/0,/*x43*/0,/*x45*/0,
                                 /*x51*/0,/*x52*/1,/*x53*/0,/*x54*/0};
        double[] row18 ={/*z*/0,/*x12*/0,/*x13*/0,/*x14*/0,/*x15*/0,
                                 /*x21*/0,/*x23*/0,/*x24*/0,/*x25*/0,
                                 /*x31*/0,/*x32*/0,/*x34*/1,/*x35*/0,
                                 /*x41*/0,/*x42*/0,/*x43*/1,/*x45*/0,
                                 /*x51*/0,/*x52*/0,/*x53*/0,/*x54*/0};
        double[] row19 ={/*z*/0,/*x12*/0,/*x13*/0,/*x14*/0,/*x15*/0,
                                 /*x21*/0,/*x23*/0,/*x24*/0,/*x25*/0,
                                 /*x31*/0,/*x32*/0,/*x34*/0,/*x35*/1,
                                 /*x41*/0,/*x42*/0,/*x43*/0,/*x45*/0,
                                 /*x51*/0,/*x52*/0,/*x53*/1,/*x54*/0};
        double[] row20 ={/*z*/0,/*x12*/0,/*x13*/0,/*x14*/0,/*x15*/0,
                                 /*x21*/0,/*x23*/0,/*x24*/0,/*x25*/0,
                                 /*x31*/0,/*x32*/0,/*x34*/0,/*x35*/0,
                                 /*x41*/0,/*x42*/0,/*x43*/0,/*x45*/1,
                                 /*x51*/0,/*x52*/0,/*x53*/0,/*x54*/1};        
                                                                          
                solver.addConstraint(row11, LpSolve.LE, 1);
                solver.addConstraint(row12, LpSolve.LE, 1);
                solver.addConstraint(row13, LpSolve.LE, 1);
                solver.addConstraint(row14, LpSolve.LE, 1);
                solver.addConstraint(row15, LpSolve.LE, 1);
                solver.addConstraint(row16, LpSolve.LE, 1);
                solver.addConstraint(row17, LpSolve.LE, 1);
                solver.addConstraint(row18, LpSolve.LE, 1);
                solver.addConstraint(row19, LpSolve.LE, 1);
                solver.addConstraint(row20, LpSolve.LE, 1);
        double[] row21 ={/*z*/0,/*x12*/1,/*x13*/0,/*x14*/0,/*x15*/0,
                                 /*x21*/0,/*x23*/1,/*x24*/0,/*x25*/0,
                                 /*x31*/1,/*x32*/0,/*x34*/0,/*x35*/0,
                                 /*x41*/0,/*x42*/0,/*x43*/0,/*x45*/0,
                                 /*x51*/0,/*x52*/0,/*x53*/0,/*x54*/0};
        double[] row22 ={/*z*/0,/*x12*/1,/*x13*/0,/*x14*/0,/*x15*/0,
                                 /*x21*/0,/*x23*/0,/*x24*/1,/*x25*/0,
                                 /*x31*/0,/*x32*/0,/*x34*/0,/*x35*/0,
                                 /*x41*/1,/*x42*/0,/*x43*/0,/*x45*/0,
                                 /*x51*/0,/*x52*/0,/*x53*/0,/*x54*/0};
        double[] row23 ={/*z*/0,/*x12*/1,/*x13*/0,/*x14*/0,/*x15*/0,
                                 /*x21*/0,/*x23*/0,/*x24*/0,/*x25*/1,
                                 /*x31*/0,/*x32*/0,/*x34*/0,/*x35*/0,
                                 /*x41*/0,/*x42*/0,/*x43*/0,/*x45*/0,
                                 /*x51*/1,/*x52*/0,/*x53*/0,/*x54*/0};
                                 
        double[] row24 ={/*z*/0,/*x12*/0,/*x13*/1,/*x14*/0,/*x15*/0,
                                 /*x21*/1,/*x23*/0,/*x24*/0,/*x25*/0,
                                 /*x31*/0,/*x32*/1,/*x34*/0,/*x35*/0,
                                 /*x41*/0,/*x42*/0,/*x43*/0,/*x45*/0,
                                 /*x51*/0,/*x52*/0,/*x53*/0,/*x54*/0};
        double[] row25 ={/*z*/0,/*x12*/0,/*x13*/1,/*x14*/0,/*x15*/0,
                                 /*x21*/0,/*x23*/0,/*x24*/0,/*x25*/0,
                                 /*x31*/0,/*x32*/0,/*x34*/1,/*x35*/0,
                                 /*x41*/1,/*x42*/0,/*x43*/0,/*x45*/0,
                                 /*x51*/0,/*x52*/0,/*x53*/0,/*x54*/0};
        double[] row26 ={/*z*/0,/*x12*/0,/*x13*/1,/*x14*/0,/*x15*/0,
                                 /*x21*/0,/*x23*/0,/*x24*/0,/*x25*/0,
                                 /*x31*/0,/*x32*/0,/*x34*/0,/*x35*/0,
                                 /*x41*/0,/*x42*/0,/*x43*/0,/*x45*/0,
                                 /*x51*/1,/*x52*/0,/*x53*/0,/*x54*/0};
                                 
        double[] row27 ={/*z*/0,/*x12*/0,/*x13*/0,/*x14*/1,/*x15*/0,
                                 /*x21*/1,/*x23*/0,/*x24*/0,/*x25*/0,
                                 /*x31*/0,/*x32*/0,/*x34*/0,/*x35*/0,
                                 /*x41*/0,/*x42*/1,/*x43*/0,/*x45*/0,
                                 /*x51*/0,/*x52*/0,/*x53*/0,/*x54*/0};
        double[] row28 ={/*z*/0,/*x12*/0,/*x13*/0,/*x14*/1,/*x15*/0,
                                 /*x21*/0,/*x23*/0,/*x24*/0,/*x25*/0,
                                 /*x31*/1,/*x32*/0,/*x34*/0,/*x35*/0,
                                 /*x41*/0,/*x42*/0,/*x43*/1,/*x45*/0,
                                 /*x51*/0,/*x52*/0,/*x53*/0,/*x54*/0};
        double[] row29 ={/*z*/0,/*x12*/0,/*x13*/0,/*x14*/1,/*x15*/0,
                                 /*x21*/0,/*x23*/0,/*x24*/0,/*x25*/0,
                                 /*x31*/0,/*x32*/0,/*x34*/0,/*x35*/0,
                                 /*x41*/0,/*x42*/0,/*x43*/0,/*x45*/1,
                                 /*x51*/1,/*x52*/0,/*x53*/0,/*x54*/0};
                                 
        double[] row30 ={/*z*/0,/*x12*/0,/*x13*/0,/*x14*/0,/*x15*/1,
                                 /*x21*/1,/*x23*/0,/*x24*/0,/*x25*/0,
                                 /*x31*/0,/*x32*/0,/*x34*/0,/*x35*/0,
                                 /*x41*/0,/*x42*/0,/*x43*/0,/*x45*/0,
                                 /*x51*/0,/*x52*/1,/*x53*/0,/*x54*/0};
        double[] row31 ={/*z*/0,/*x12*/0,/*x13*/0,/*x14*/0,/*x15*/1,
                                 /*x21*/0,/*x23*/0,/*x24*/0,/*x25*/0,
                                 /*x31*/1,/*x32*/0,/*x34*/0,/*x35*/0,
                                 /*x41*/0,/*x42*/0,/*x43*/0,/*x45*/0,
                                 /*x51*/0,/*x52*/0,/*x53*/1,/*x54*/0};
        double[] row32 ={/*z*/0,/*x12*/0,/*x13*/0,/*x14*/0,/*x15*/1,
                                 /*x21*/0,/*x23*/0,/*x24*/0,/*x25*/0,
                                 /*x31*/0,/*x32*/0,/*x34*/0,/*x35*/0,
                                 /*x41*/1,/*x42*/0,/*x43*/0,/*x45*/0,
                                 /*x51*/0,/*x52*/0,/*x53*/0,/*x54*/1};
                                                  
                solver.addConstraint(row21, LpSolve.LE, 2);
                solver.addConstraint(row22, LpSolve.LE, 2);
                solver.addConstraint(row23, LpSolve.LE, 2);
                solver.addConstraint(row24, LpSolve.LE, 2);
                solver.addConstraint(row25, LpSolve.LE, 2);
                solver.addConstraint(row26, LpSolve.LE, 2);
                solver.addConstraint(row27, LpSolve.LE, 2);
                solver.addConstraint(row28, LpSolve.LE, 2);
                solver.addConstraint(row29, LpSolve.LE, 2);
                solver.addConstraint(row30, LpSolve.LE, 2);
                solver.addConstraint(row31, LpSolve.LE, 2);
                solver.addConstraint(row32, LpSolve.LE, 2);

                solver.setBinary(1, true);
                solver.setBinary(2, true);
                solver.setBinary(3, true);
                solver.setBinary(4, true);
                solver.setBinary(5, true);
                solver.setBinary(6, true);
                solver.setBinary(7, true);
                solver.setBinary(8, true);
                solver.setBinary(9, true);
                solver.setBinary(10, true);
                solver.setBinary(11, true);
                solver.setBinary(12, true);
                solver.setBinary(13, true);
                solver.setBinary(14, true);
                solver.setBinary(15, true);
                solver.setBinary(16, true);
                solver.setBinary(17, true);
                solver.setBinary(18, true);
                solver.setBinary(19, true);
                solver.setBinary(20, true);

                //~ solver.setMinim() ;
                //~ solver.setVerbose(0);
                
                solver.setMinim() ;
                solver.setVerbose(2);
                solver.printDuals();
                solver.printTableau();
                solver.printLp();
                
                solver.solve();
                Double objective = solver.getObjective();
                        // print solution
                //~ System.out.println("Value of objective function: "+ objective.intValue());
                
                value = solver.getObjective();
                double [] var = solver.getPtrVariables();
                bSolution = new Boolean[var.length];
                strArrSolution=new String[var.length];
                
                for (int i = 0; i < var.length; i++) {
                        bSolution[i]=false;
                        if(var[i]==1.0){               
                                strArrSolution[i]=solver.getColName(i+1);                 
                                strSolution +=solver.getColName(i+1)+" ";                 
                                bSolution[i]=true;
                        }
                        //~ System.out.println("Value of  "+i+" [" + solver.getColName(i+1) +"] = " + var[i]+" "+bSolution[i]);                        
                }
                solver.deleteLp();

        }
        public Boolean[] getBoolSolution(){
                return bSolution;
        }
        public String[] getStringArrSolution(){
                return strArrSolution;
        }
        public String getStringSolution(){
                return strSolution;
        }
        public Double getObjevtiveValue(){
                return value;
        }
        public void setDebug(Boolean debug){
			this.DEBUG=debug;
		}
        public Boolean getDebug(){
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


