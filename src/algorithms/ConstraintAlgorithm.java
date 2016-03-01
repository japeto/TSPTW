/*
 *ConstraintAlgorithm.java	1.0 2014/11/27
 *
 */
package algorithms;
import java.util.*;
import javax.swing.*;
import data.*;
import lpsolve.*;
/**
 * A <code>ConstraintAlgorithm</code> contains
 * the generating dynamic restrictions modeled
 * @author Jefferson Amado Pe&ntilde;a Torres <jeffersonamado@gmail.com>
 * @version 1.0, 2010/11/11
 * @since   JDK1.0
 */
 
public class ConstraintAlgorithm implements Algorithms {
        
        private int nVariables;
        private int nConstraints;
        private String[] contraints;
        private Vector<Site> cities;
        private Vector<String> names;
        private Vector<Integer> points;
        private Double[][] paths;
        private Boolean[] bSolution={false};
        private String strSolution="";
        private double objetiveValue=0.0;
        private double waitValue=0.0;
        private int BigMvalue = 1000;
        private Boolean DEBUG=false;
        private LpSolve solver;

        public ConstraintAlgorithm(){
                if(DEBUG){
                        System.out.println("::: SimpleAlgorithm is alive :::");
                }
        }

        public void load(Vector<Site> cities,Double[][] paths){

                nVariables=paths.length;
                this.cities=cities;
                this.paths=paths;
                nConstraints=0;
                
        }

        /**
         * {@inheritDoc}
         */
        public void compute() throws LpSolveException{
                //~ Creo una instancia de lp solver con nContraints y nVariables
                //~ Create a problem with variables and constraints
                Stopwatch stc = new Stopwatch();
                solver = LpSolve.makeLp(nConstraints, nVariables);        
                //vector de los nombres de las variables  xij
                names = new Vector<String>();
                                
                if(DEBUG){
                        System.out.println("**********************************\n"
                                        +"Building solver matrix\n"
                                        +"**********************************\n");
                }
                
                /*
                 * Esta es la suma que presisa la cantidad de variables que se tendran en la matriz
                 *  
                 * z:           variable        (z)
                 * (n*n-1)+2n:  paths           (xij)
                 * (n*n-1):     wait vars       (wij)
                 * n:           equi. vars      (ci)
                 * n+2:         service vars    (si)
                 * n:			cycles var      (ui)
                 * 
                 * size columns in matriz
                 *              *z + 2*(n*n-1) + 4n + 2
                 * 
                 */
                int sizeCols = 1 + 2*paths.length + 6*cities.size()+2;
                                
                int count=0;
                double[] rowSol =new double[sizeCols];
                double[][] matrixOut = new double[sizeCols][sizeCols];
				
				//~ Creo las variables Xij les establesco el nombre en la matriz, les asigno el valor en la columna objetivo
				//~ en este parte tambien creo las restricciones de entrada, que plantean que a cada nodo solo se puede ir una sola vez
				
                for(int start=1;start<paths[1].length ;start++){
                        double[] rowOut =new double[sizeCols] ;
                        for(int end=1;end<paths[1].length ;end++){
                                if(start != end){
                                        count++;
                                        solver.setColName(count,"x"+start+""+end+"");	//pongo el nombre de la columna
                                        names.add("x"+start+""+end+"");
                                        solver.setBinary(count, true);					//deben ser binarias
                                        if(paths[start][end]>-1 && cities.get(start-1).getServiceTime()>-1){			//si el tiempo de servivio o el valor del desplazamiento es negativo hay un problema
                                                rowSol[count]=paths[start][end]+cities.get(start-1).getServiceTime();   // Qij -> tdij+tsi  coeficiente de la variable
                                                rowOut[count]=1;														//el uno donde debe ir el 1 para la restriccion
                                                matrixOut[end][count]=1;												//
                                        }else{
                                                System.err.println("time (tdij) : "+paths[start][end]
                                                                        +" time service (tsij): "+cities.get(start-1).getServiceTime());
                                               throw new IllegalArgumentException("Negative Values in service time or edge time"); 
                                        }
                                        if(DEBUG){
                                                System.out.println("col-> "+count+", with name: x"+start+""+end+" and value "+rowSol[count]);
                                        }
                                }
                        }
                        solver.addConstraint(rowOut, LpSolve.EQ, 1);										//restriccion es igual a 1
                        solver.setRowName(start,"I"+start);                        							//en la tabla aparecenran con este nombre
                        if(DEBUG){
                                System.out.println("add constraint "+solver.getNrows()+" \"in\" type from  x"+start+" to ... ");
                        }                         
                } 
                /*No cycle constraints*/
                /*Creo las columnas con nombre ui que seran usadas para no permitir los ciclos*/
                for(int start=1;start<cities.size()+1;start++){
                        int cols =solver.getNcolumns()+1;
                        solver.setColName(cols,"u"+start);
                        solver.setInt(cols,true);
                        if(DEBUG){
                                System.out.println("add no cycle vars u"+start+" ");
                        }  
                }
                /*uso la formula ui-uj +N Xij <= N-1*/
                for(int start=1;start<paths[1].length ;start++){
                        for(int end=1;end<paths[1].length ;end++){
                                if(start != end){ 
                                        double[] rowOut =new double[sizeCols] ;
                                        rowOut[getIndexColFromName("u"+start)]=1;
                                        rowOut[getIndexColFromName("u"+end)]=-1;
                                        rowOut[getIndexColFromName("x"+start+""+end)]=cities.size();
                                        solver.addConstraint(rowOut, LpSolve.LE, (cities.size()-1));
                                        if(DEBUG){
                                                System.out.println("add  contraint  u"+start+" - u"+end+" n:"+cities.size()
                                                +" in "+getIndexColFromName("x"+start+""+end)+" < "+(cities.size()-1));
                                        }
                                }
                        }
                }
                /* artificial nodes */
                /* Agrego los nodos artificiales que me permitiran prohibir todos los ciclos pero si me permitiran crear un ruta con origen
                 * P y con destino Q*/
                /*make nodes*/
                /*Creo las aristas xPi*/
                for(int start=1;start<cities.size()+1;start++){
                        int rows =solver.getNcolumns()+1;
                        solver.setColName(rows,"xP"+start);
                        solver.setInt(rows,true);
                        if(DEBUG){
                                System.out.println("add special nodes xP"+start+" ");
                        }  
                }
                /*Creo las aristas  xiQ*/
                for(int start=1;start<cities.size()+1;start++){
                        int rows =solver.getNcolumns()+1;
                        solver.setColName(rows,"x"+start+"Q");
                        solver.setInt(rows,true);
                        if(DEBUG){
                                System.out.println("add special nodes x"+start+"Q");
                        }  
                }                
                /*update constraints with new special nodes*/
                /*Actualizo las columnas de las matriz con ahora teniendo en cuenta los nodos artificiales */
                for(int constr=1;constr<solver.getNrows(); constr++){
                        //udate objetive
                        rowSol[getIndexColFromName("xP"+constr)]=1;
                        rowSol[getIndexColFromName("x"+constr+"Q")]=1;
                        //update constraints
                        double[] row =solver.getPtrRow(constr);
                        row[getIndexColFromName("x"+constr+"Q")]=1;
                        solver.setRow(constr, row);
                } 
                /*contraint in type by special nodes*/
                /*Creo las restricciones del los nodos aritificiales, Cualquier nodo puede ir a XiQ pero ningun nodo puede ir xPi */
                double[] rowSpecial =new double[sizeCols] ;
                for(int start=1;start<cities.size()+1;start++){
                        rowSpecial[getIndexColFromName("x"+start+"Q")]=1;
                }
                solver.addConstraint(rowSpecial, LpSolve.EQ, 1);
                solver.setRowName(solver.getNrows(),"EI"+solver.getNrows());                
                
                rowSpecial =new double[sizeCols];
                for(int start=1;start<cities.size()+1;start++){
                        rowSpecial[getIndexColFromName("xP"+start)]=1;
                }
                solver.addConstraint(rowSpecial, LpSolve.EQ, 1);
                solver.setRowName(solver.getNrows(),"EO"+solver.getNrows());
                /*Constraint in to nodes*/
                /*restricciones para los nodos pues de cualquier nodo se puede ir otro, menos a los xiQ */
                for(int start=1;start<paths[1].length ;start++){
                        matrixOut[start][getIndexColFromName("xP"+start)]=1;
                        solver.addConstraint(matrixOut[start], LpSolve.EQ, 1);
                        solver.setRowName(solver.getNrows(),"0"+start);                        
                        if(DEBUG){
                                System.out.println("add constraint  "+solver.getNrows()+" \"out\" type from  x... to "+start+" ");
                        }                        
                }
                //~ /*no cycles*/    
                /*restricciones ventanas*//*windows constraints*/
                /*ai <= si <= bi*/
                if(DEBUG){
                        System.out.println("Time Windows Constraint by N. "+cities.size()+" cities");
                }         
                for(int nCities=1;nCities<= cities.size();nCities++){
                        int vars =solver.getNcolumns()+1;
                        int rows =solver.getNrows()+1;
                        double[] rowWin =new double[sizeCols];
                        rowWin[vars]=1;
                        solver.setColName(vars,"s"+nCities);
                        if(cities.get(nCities-1).getReadyTime().doubleValue() <= cities.get(nCities-1).getDueDate().doubleValue()){
                        
                                solver.addConstraint(rowWin, LpSolve.GE, cities.get(nCities-1).getReadyTime().doubleValue());
                                solver.setRowName(rows,"a"+nCities);
                                solver.addConstraint(rowWin, LpSolve.LE, cities.get(nCities-1).getDueDate().doubleValue());
                                solver.setRowName(rows+1,"b"+nCities);
                                
                        }else{
                                System.err.println("ai: "+cities.get(nCities-1).getReadyTime().doubleValue()
                                                        +"bi: "+cities.get(nCities-1).getDueDate().doubleValue());
                                throw new IllegalArgumentException("Time windows ai > bi ");
                        }
                        if(DEBUG){
                                System.out.println("time windows Constraint by "+nCities+" is finished");
                        }
                }
                /*ventanas para los artificiales*/
                int vars =solver.getNcolumns()+1;
                int rows =solver.getNrows()+1;
                double[] rowWin =new double[sizeCols];
                rowWin[vars]=1;
                solver.setColName(vars,"sP");
                solver.addConstraint(rowWin, LpSolve.GE,0);
                solver.setRowName(rows,"aP");
                solver.addConstraint(rowWin, LpSolve.LE,0);
                solver.setRowName(rows+1,"bP");
                
                vars =solver.getNcolumns()+1;
                rows =solver.getNrows()+1;
                rowWin =new double[sizeCols];
                rowWin[vars]=1;      
                solver.setColName(vars,"sQ");
                solver.addConstraint(rowWin, LpSolve.GE,BigMvalue);
                solver.setRowName(rows,"aQ");
                solver.addConstraint(rowWin, LpSolve.LE,BigMvalue);
                solver.setRowName(rows+1,"bQ");
                //~ //solver.setColName(vars,"s"+nCities);
                /*restricciones bonitas*//*beatiful constraints*/
                /*restricciones de la espera *//*wait constraints*/
                /****************************************
                * Qij= tsi+tdij                         *
                * si + Qij + ci - sj <= M(1 - xij)      *
                * sj - Qij - si + ci + M (xij - 1)<=wij *
                *****************************************/
                if(DEBUG){
                        System.out.println("big M value: "+BigMvalue);
                }
                
                
                for(int nCstart=1;nCstart< cities.size()+1;nCstart++){
                        solver.setColName(solver.getNcolumns()+1,"c"+nCstart);
                        for(int nCend=1;nCend< cities.size()+1;nCend++){
                                if(nCstart != nCend){
                                        
                                        int col=solver.getNcolumns()+1;
                                        solver.setColName(col,"w"+nCstart+""+nCend+"");
                                        //~ solver.setInt(col,true);
                                        
                                        double[] rowA=new double[sizeCols];
                                        double[] rowB =new double[sizeCols];
                                        
                                        double Qij=(paths[nCstart][nCend]+cities.get(nCstart-1).getServiceTime());                                        
                                        rowA[getIndexColFromName("s"+nCstart)]=1;
                                        rowA[getIndexColFromName("c"+nCstart)]=1;
                                        rowA[getIndexColFromName("s"+nCend)]=-1;
                                        rowA[getIndexColFromName("x"+nCstart+""+nCend)]= BigMvalue;
                                        solver.addConstraint(rowA, LpSolve.LE, BigMvalue - Qij);
                                        solver.setRowName(solver.getNrows(), "B"+nCstart+""+nCend);
                                        
                                        rowB[getIndexColFromName("s"+nCstart)]= -1;
                                        rowB[getIndexColFromName("c"+nCstart)]= 1;
                                        rowB[getIndexColFromName("s"+nCend)]= 1;
                                        rowB[getIndexColFromName("x"+nCstart+""+nCend)]= BigMvalue;
                                        rowB[getIndexColFromName("w"+nCstart+""+nCend+"")]= -1;
                                        solver.addConstraint(rowB, LpSolve.LE, BigMvalue + Qij);
                                        solver.setRowName(solver.getNrows(), "W"+nCstart+""+nCend);
                                        
                                        if(DEBUG){
                                                System.out.println(" s"+nCstart
                                                        +" +"+Qij
                                                        +" +c"+nCstart
                                                        +" -s"+nCend+" <= "
                                                        +" M - M * x"+nCstart+""+nCend
                                                        );
                                        }
                                }
                        }
                }
                /*restrciciones bonitas para los x*Q */
                for(int nCstart=1;nCstart< cities.size()+1;nCstart++){
                        for(int nCend=1;nCend< cities.size()+1;nCend++){
                                if(nCstart != nCend){
                                        double[] rowA=new double[sizeCols];
                                        rowA[getIndexColFromName("s"+nCstart)]=1;
                                        rowA[getIndexColFromName("c"+nCstart)]=1;
                                        rowA[getIndexColFromName("sQ")]=-1;
                                        rowA[getIndexColFromName("x"+nCstart+""+nCend)]= BigMvalue;
                                        solver.addConstraint(rowA, LpSolve.LE, BigMvalue - 1);
                                        solver.setRowName(solver.getNrows(), "B"+nCstart+"Q");
                                }
                        }
                }
                /*funcion objetivo*//*Objetive function*/
                solver.setObjFn(rowSol);
                solver.setVerbose(0);
                solver.setMinim() ;
                if(DEBUG){
                        solver.setVerbose(2);
                        solver.printTableau();
                        solver.printLp();
                }
                double contrainttime=stc.elapsedTime();
                Stopwatch slv= new Stopwatch();
                solver.solve();
                double solvetime=slv.elapsedTime();
                Double objective = solver.getObjective();
                objetiveValue = solver.getObjective();
                double [] var = solver.getPtrVariables();
                bSolution = new Boolean[var.length];
                
                //~ if(solver.getStatus()==2){
                        //~ throw new LpSolveException("INFEASIBLE SOLUTION");
                //~ }
                                                               
                points = new Vector<Integer>(cities.size()+1);
                Double lastServicetime=0.0;
                for(int nCstart=0;nCstart< cities.size()+1;nCstart++){
                        points.add(-1);
                }
                for (int i = 0; i < var.length ; i++) {
                        bSolution[i]=false;
                        
                        if(var[i]==1.0 && solver.getColName(i+1).contains("x")){
                                String [] edge=solver.getColName(i+1).split("");
                                strSolution +=edge[2]+"->"+edge[3]+",";
                                if(strSolution.contains("P") && !strSolution.contains("Q")){
                                        points.set(0,new Integer(edge[3]));
                                        //~ System.out.println("XP->  s "+points);                                        
                                }
                                if (strSolution.contains("Q")){
                                        lastServicetime=cities.get(new Integer(edge[2])-1).getServiceTime();
                                        //~ System.out.println("->>>>"+cities.get(new Integer(edge[2])-1).getServiceTime());
                                }
                                if(!strSolution.contains("P") && !strSolution.contains("Q")){
                                        points.set(new Integer(edge[2]),new Integer(edge[3]));
                                        //~ System.out.println("---"+edge[2]+" -- "+edge[3]+" s "+points);
                                     //~ 
                                }
                        if(DEBUG){
                                System.out.println("Value of  "+i+" [" + solver.getColName(i+1) +"] = " + var[i]+" ");                        
                        }
                                bSolution[i]=true;
                        }
                        if(solver.getColName(i+1).contains("w")){
                            waitValue+= var[i];   
                            //~ System.out.println("wait"+waitValue);
                        }
                        
                }

                objetiveValue +=  lastServicetime + paths[points.indexOf(-1)][points.get(0)] - 2 ;
                
                if(DEBUG){
                        System.out.println("Objective function value from lpsolve: "
                                +objetiveValue+" waits value: "+waitValue
                                +" optimal value: "+(objetiveValue+waitValue)+" contrainttime: "+contrainttime+" solvetime: "+solvetime);                        
                        for (int i = 0; i < var.length; i++) {
                                System.out.println("Value of var[" + solver.getColName(i+1)+"" +"] = " + var[i]);
                        }
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
                return waitValue+objetiveValue;
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
