/*
 *FileLoader.java	1.13 2014/10/07
 *
 */
package data;
import java.io.*;
import java.util.*;
/**
 * <p>
 * A <code>FileLoader</code> contains methods that allow create TSPTW
 * object
 * <p/>
 * <p>
 * This class provides a set of simplified read-only text file methods
 * that hide some of the details of file streams for TSPTW format.
 * <p/>
 * <>
 *
 * @author  Jefferson Amado Pe&ntilde;a
 * @version 1.13, 2010/11/10
 * @since   JDK1.0
 */
public class FileLoader{

        private String content="";
        private File _file;
        private FileInputStream _fis = null;
        private BufferedInputStream _bis = null;
        private DataInputStream _dis = null;
        private Vector<String> rows;
        private Vector<Site> cities;
        private Double[][] paths;
        private Boolean DEBUG=false;
        /**
        * Class constructor no-specifying file to read
        */
        private FileLoader(){
                rows =new  Vector<String>();
                //tsptwinstance= new TSPTWinstance();
        }
        /**
        * Class constructor specifying file to read
        * @param file a File object to read
        * @throws may be generate IOException
        */
        public FileLoader(File file)throws IOException{
                rows =new  Vector<String>();
                _file = file;
                loadTSPTWFile();
        }
        /**
         * Opens a new read-only text file for reading data.
         * @param name and path the file
         */
        public boolean openFile(String name){
                boolean tmp=false;

                try{
                        _file = new File(name);
                        tmp=_file.exists();
                        if (tmp) {
                                rows =new  Vector<String>();
                                loadTSPTWFile();
                                if(DEBUG){
                                        System.out.println("Load new file:"+name);
                                }
                                loadTSPTWFile();
                        }
                }catch( IOException ioe){
                        tmp=false;
                }

                return tmp;
        }
        /**
         * Closes an open file.
         * <b>Warning:<b/> This method may be
         * generate errors
         */
        public int closeFile() {
                int test=0;
                try{
                        _file=null;
                         rows.clear();
                        _fis.close();
                        _bis.close();
                        _dis.close();
                        test=1;
                }catch( IOException ioe){
                     test=0;
                }
                return test;
        }
        /**
        *
        */
        private void loadTSPTWFile() throws IOException{
                content+="@File : "+_file.getName()+"\n";
                
                _fis = new FileInputStream(_file);
                _bis = new BufferedInputStream(_fis);
                _dis = new DataInputStream(_bis);
                while (_dis.available() != 0) {
                        String strDatos=_dis.readLine();
                        content+=strDatos+"\n";
                        StringTokenizer tokens=new StringTokenizer(strDatos, " ");
                        while(tokens.hasMoreTokens()){
                                String str=tokens.nextToken();
                                boolean comment=(str.contains("[") || str.contains("]")); //omitir los comentarios
                                if(!comment) rows.add(str);
                        }
                }
                createMap();
                _fis.close();
                _bis.close();
                _dis.close();
        }
        /**
        *
        */
        private void createMap(){
                int nNodes=Integer.parseInt(rows.get(0));
                cities = new Vector<Site>(nNodes); //array cities
                paths = new Double[(nNodes*(nNodes-1))][nNodes+1]; //array paths
                if(DEBUG){
                        System.out.println("rows: "+rows.size()+" nodes: "+nNodes);
                }
                //create sites
                for(int i=0;i<nNodes*4;i+=4){
                        cities.add(new Site(Integer.parseInt(rows.get(i+1)),//id
                        "C: "+rows.get(i+1)+"["+new Double(rows.get(i+3)).intValue()+","+new Double(rows.get(i+4)).intValue()+"]", // name
                        Double.parseDouble(rows.get(i+2)), //service time
                        Double.parseDouble(rows.get(i+3)), //ready time
                        Double.parseDouble(rows.get(i+4)))); //due time
                        
                        if(DEBUG){
                                System.out.println("Create city "+"c: "+rows.get(i+1)+"["+new Double(rows.get(i+3)).intValue()+","+new Double(rows.get(i+4)).intValue()+"]");
                        }
                }
                //create edges
                int limit = ((nNodes*(nNodes-1))*3)+(nNodes*4); // edges from file
                Vector<Double> cost=new Vector<Double>();
                for(int i=(nNodes*4); i<limit;i+=3){
                        for(int j=1;j<4;j++){
                                cost.add(Double.parseDouble(rows.get(i+j)));//cost
                        }
                }
                //obtain paths and create ways
                for(int row=0;row< cost.size(); row+=3){
                        paths[cost.get(row).intValue()][cost.get(row+1).intValue()] = cost.get(row+2);
                        if(DEBUG){
                               System.out.println("Create way: "+cost.get(row)+" to "+cost.get(row+1)+" cost "+cost.get(row+2));
                        }
                }
                
                //System.out.println("rows: "+rows.size()+" pointer: "+limit);
                if(rows.size()>limit+1){ //lines in file major than limit plus 1 that indicate number node
                        for(int i=0;i<cities.size();i++){
                                cities.get(i).setName(cities.get(i).getName()+"."+rows.get(limit+i+1));
                        }
                        for(int i=0;i<cities.size()*2;i+=2){
                                //~ System.out.println((i/2)+" x: "+rows.get(limit+i+1+nNodes)+" y: "+rows.get(limit+i+2+nNodes));
                                cities.get(i/2).setLocation(Double.parseDouble(rows.get(limit+i+1+nNodes)),
                                Double.parseDouble(rows.get(limit+i+2+nNodes)));
                        }
                }
        }
        /**
         */
        public void writeSolution(File file, String content)throws IOException{
                BufferedWriter writer = null;
                writer = new BufferedWriter( new FileWriter(file));
                writer.write(content);
                if ( writer != null)
                        writer.close( );              
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
        protected void setCities(Vector<Site> cities){
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
        protected void setPaths(Double[][] paths){
                this.paths=paths;
        }
        /**
        *Returns an String object that can then be showed on the screen
        *@return an String object with file contents
        *@see java.io.File
        */
        public String toString(){
                int nNodes=Integer.parseInt(rows.get(0));
                content="[NUMBER NODES] \n"+nNodes+"\n";
                content+="[NODE] [SERVICE TIME] [READY TIME] [DUE DATE]\n";
                for(int i=0;i<nNodes*4;i+=4){
                        for(int j=1;j<5;j++){
                        content+=rows.get(i+j)+"\t";
                        }
                        content+="\n";
                }
                content+="[START NODE][END NODE][DISTANCE] \n";
                //limit:filas (n*(n-1)) por la 3 columnas
                //		mas lo que ya se leyo nodos por las 4 columnas
                int limit = ((nNodes*(nNodes-1))*3)+(nNodes*4);

                for(int i=(nNodes*4); i<limit;i+=3){
                        for(int j=1;j<4;j++){
                                //content+=rows.get(i+j)+" ["+(i+j)+"]\t";
                                content+=rows.get(i+j)+"\t";
                        }
                        content+="\n";
                }
                return content;
        }
        /**
        * With this method is obteined information the file load
        * @return an array with name,number of cities and number of
        * paths
        */
        public String[] getInfoFile(){
                String []  values = new String[4];
                values[0]= _file.getName(); //nombre
                values[1]= cities.size()+"";//ciudades
                values[2]= paths.length+"";
                return values;
        }
        /**
         * This method sets infomation file
         * <b>Warning:<b/> this method may be
         * generate error
         * <b>NO IMPLEMENT<b/>
         * @param info array info. files
         * @deprecated
         */
        protected  void setInfoFile(String[] info){

        }
//~ public static void main(String args[]){
//~ try{
//~ FileLoader test= new FileLoader(new File(args[0]));
//~ //System.out.println(test.toString());
//~ }catch(IOException a){
//~ System.err.println("lectura");
//~ }
//~ }
}


