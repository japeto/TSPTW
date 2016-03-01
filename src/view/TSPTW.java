/*
 *TSPTWMainWindow.java	1.0 2014/11/11
 *
 */
package view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.io.Serializable;
import data.*;
import algorithms.*;
import lpsolve.*;
import java.text.*;

/**
 * A <code>TSPTWMainWindow</code> contains
 *
 * @author Jefferson Amado Pe&ntilde;a Torres <jeffersonamado@gmail.com>
 * @version 1.0, 2010/11/11
 * @since   JDK1.0
 */
public class TSPTW extends JFrame implements ActionListener{
        static final long serialVersionUID = 1L;                
        private JButton buttonInfo;
        private JButton buttonOpen;
        private JButton buttonRun;
        private JButton buttonSave;
        private JButton buttonHelp;
        private JMenuItem menuItemHelp;
        private JMenuItem menuItemConver;
        private JMenuItem menuItemInfo;
        private JScrollPane scroll;
        private JScrollPane scrollProblem;
        private JScrollPane scrollSolution;
        private JMenuBar menuBar;
        private JMenu menuFile;
        private JMenu menuHelp;
        private JButton buttonSetting;
        private JButton buttonVelocity;
        private JMenuItem menuItemExit;
        private JMenuItem menuItemOpen;
        private JMenuItem menuItemRun;
        private JMenuItem menuItemSave;
        private JMenuItem menuItemSaveAs;
        private JMenu menuTools;
        private GCanvas panelGraphicsProblem;
        private GCanvas panelGraphicsSolution;
        private JSeparator separator;
        private JToolBar toolBar;
        private JLabel txtOut;
        private FileLoader objFile;
        private Algorithms solver;
        public Boolean DEBUG=false;
        
    /**
     * Creates new form TSPTWMainWindow
     */
    public TSPTW() {
        initComponents();
    }
    private void initComponents() {

        toolBar = new JToolBar();
        buttonOpen = new JButton();
        buttonRun = new JButton();
        buttonInfo = new JButton();
        buttonSave = new JButton();
        buttonHelp = new JButton();
        panelGraphicsProblem = new GCanvas();
        panelGraphicsSolution = new GCanvas();
        separator = new JSeparator();
        scroll = new JScrollPane();
        scrollProblem = new JScrollPane();
        scrollSolution = new JScrollPane();
        txtOut = new JLabel();
        menuBar = new JMenuBar();
        menuFile = new JMenu();
        menuItemOpen = new JMenuItem();
        menuItemSave = new JMenuItem();
        menuItemSaveAs = new JMenuItem();
        menuItemExit = new JMenuItem();
        menuTools = new JMenu();
        menuItemRun = new JMenuItem();
        menuItemInfo = new JMenuItem();
        buttonSetting = new JButton();
        buttonVelocity = new JButton();
        menuHelp = new JMenu();
        menuItemHelp = new JMenuItem();
        menuItemConver = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        buttonOpen.setIcon(new ImageIcon("./icons/open32.png")); // NOI18N
        buttonOpen.setText("Open");
        buttonOpen.setFocusable(false);
        buttonOpen.setHorizontalTextPosition(SwingConstants.CENTER);
        buttonOpen.setVerticalTextPosition(SwingConstants.BOTTOM);
        toolBar.add(buttonOpen);

        buttonRun.setIcon(new ImageIcon("./icons/run32.png")); // NOI18N
        buttonRun.setText("Run");
        buttonRun.setFocusable(false);
        buttonRun.setHorizontalTextPosition(SwingConstants.CENTER);
        buttonRun.setVerticalTextPosition(SwingConstants.BOTTOM);
        toolBar.add(buttonRun);

        buttonInfo.setIcon(new ImageIcon("./icons/info32.png")); // NOI18N
        buttonInfo.setText("Info.");
        buttonInfo.setFocusable(false);
        buttonInfo.setHorizontalTextPosition(SwingConstants.CENTER);
        buttonInfo.setVerticalTextPosition(SwingConstants.BOTTOM);
        toolBar.add(buttonInfo);

        buttonSave.setIcon(new ImageIcon("./icons/save32.png")); // NOI18N
        buttonSave.setText("Save");
        buttonSave.setFocusable(false);
        buttonSave.setHorizontalTextPosition(SwingConstants.CENTER);
        buttonSave.setVerticalTextPosition(SwingConstants.BOTTOM);
        toolBar.add(buttonSave);

        buttonHelp.setIcon(new ImageIcon("./icons/help32.png")); // NOI18N
        buttonHelp.setText("Help");
        buttonHelp.setFocusable(false);
        buttonHelp.setHorizontalTextPosition(SwingConstants.CENTER);
        buttonHelp.setVerticalTextPosition(SwingConstants.BOTTOM);
        toolBar.add(buttonHelp);

        panelGraphicsProblem.setBackground(new Color(47,79,79));
        panelGraphicsProblem.setToolTipText("Mapa grafico desde el archivo");
        panelGraphicsProblem.setSpeed(5);
        panelGraphicsProblem.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));

        GroupLayout panelGraphicsProblemLayout = new GroupLayout(panelGraphicsProblem);
        panelGraphicsProblem.setLayout(panelGraphicsProblemLayout);
        panelGraphicsProblemLayout.setHorizontalGroup(
            panelGraphicsProblemLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 374, Short.MAX_VALUE)
        );
        panelGraphicsProblemLayout.setVerticalGroup(
            panelGraphicsProblemLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelGraphicsSolution.setBackground(new Color(47,79,79));
        panelGraphicsSolution.setToolTipText("Solucion generada por el algoritmo");
        panelGraphicsSolution.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));

        GroupLayout panelGraphicsSolutionLayout = new GroupLayout(panelGraphicsSolution);
        panelGraphicsSolution.setLayout(panelGraphicsSolutionLayout);
        panelGraphicsSolutionLayout.setHorizontalGroup(
            panelGraphicsSolutionLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelGraphicsSolutionLayout.setVerticalGroup(
            panelGraphicsSolutionLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );

        separator.setOrientation(SwingConstants.VERTICAL);

		scroll.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Log"));        
        scroll.setViewportView(txtOut);
        scrollProblem.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Graph"));
        scrollProblem.setViewportView(panelGraphicsProblem);
        scrollSolution.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Solve"));
        scrollSolution.setViewportView(panelGraphicsSolution);

        menuFile.setText("File");

        menuItemOpen.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        menuItemOpen.setIcon(new ImageIcon("./icons/open32.png")); // NOI18N
        menuItemOpen.setText("Open");
        menuFile.add(menuItemOpen);

        menuItemSave.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuItemSave.setIcon(new ImageIcon("./icons/save32.png")); // NOI18N
        menuItemSave.setText("Save Sol.");
        menuFile.add(menuItemSave);
        
        menuItemSaveAs.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        menuItemSaveAs.setIcon(new ImageIcon("./icons/saveas32.png")); // NOI18N
        menuItemSaveAs.setText("Save in As");
        menuFile.add(menuItemSaveAs);

        menuItemExit.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        menuItemExit.setIcon(new ImageIcon("./icons/exit32.png")); // NOI18N
        menuItemExit.setText("Exit");
        menuFile.add(menuItemExit);

        menuBar.add(menuFile);
        menuTools.setText("Tools");

        menuItemRun.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        menuItemRun.setIcon(new ImageIcon("./icons/run32.png")); // NOI18N
        menuItemRun.setText("Run");
        menuTools.add(menuItemRun);

        menuItemInfo.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        menuItemInfo.setIcon(new ImageIcon("./icons/info32.png")); // NOI18N
        menuItemInfo.setText("Info. File");
        menuItemInfo.setToolTipText("");
        menuTools.add(menuItemInfo);

        menuHelp.setText("Utiles");
        menuHelp.setToolTipText("Ayuda");

        menuItemHelp.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        menuItemHelp.setIcon(new ImageIcon("./icons/help32.png")); // NOI18N
        menuItemHelp.setText("Help");
        menuHelp.add(menuItemHelp);

        menuItemConver.setIcon(new ImageIcon("./icons/convert32.png")); // NOI18N
        menuItemConver.setText("Converter");
        menuHelp.add(menuItemConver);

        menuBar.add(menuHelp);
        
        menuBar.add(Box.createHorizontalGlue());
        buttonSetting.setIcon(new ImageIcon("./icons/clear32.png")); // NOI18
        buttonSetting.setBorderPainted(false);
        buttonSetting.setFocusPainted(false);
        buttonSetting.setSize(20,10);
        buttonSetting.setPreferredSize(new Dimension(30,10));
        buttonSetting.setContentAreaFilled(false);
        
        buttonVelocity.setIcon(new ImageIcon("./icons/vel32.png")); // NOI18
        buttonVelocity.setBorderPainted(false);
        buttonVelocity.setFocusPainted(false);
        buttonVelocity.setSize(20,10);
        buttonVelocity.setPreferredSize(new Dimension(25,10));
        buttonVelocity.setContentAreaFilled(false);
        
        menuBar.add(buttonVelocity);
        menuBar.add(buttonSetting);
        
        setJMenuBar(menuBar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(toolBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollProblem, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scrollSolution, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                    .addComponent(scroll, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(separator)
                    .addComponent(scrollProblem, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrollSolution, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scroll, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );        
        menuItemExit.addActionListener(this);
        buttonSetting.addActionListener(this);
        buttonVelocity.addActionListener(this);
        menuItemRun.addActionListener(this);
        menuItemOpen.addActionListener(this);
        menuItemSave.addActionListener(this);
        menuItemConver.addActionListener(this);
        menuItemHelp.addActionListener(this);
        buttonInfo.addActionListener(this);
        buttonOpen.addActionListener(this);
        buttonRun.addActionListener(this);
        buttonSave.addActionListener(this);
        buttonHelp.addActionListener(this);        
        //~ pack();
        setSize(900,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Univalle TSPTW Solver");
    }
        public void showMap(){
                panelGraphicsProblem.scratchOut();
                String[] data=objFile.getInfoFile();
                txtOut.setText("<html><b>File name: </b><font color='green'>"+data[0]+"</font><br>");
                txtOut.setText(txtOut.getText()+"<b>Nodes: </b><font color='blue'>"+data[1]+"</font> ");
                txtOut.setText(txtOut.getText()+"<b>Edges: </b><font color='blue'>"+data[2]+"</font></html>");
                //~ panelGraphicsProblem.setBackground(new Color(89,15,24));
                Thread t = new Thread(){
                public void run(){
                        panelGraphicsProblem.sketch(objFile.getCities(),objFile.getPaths()); 
                }};
                t.start();
        }
        private void createSolution(){
                try{
                        
                        solver = new ConstraintAlgorithm();
                        //~ solver = new SimpleAlgorithm();
                        //~ solver = new SampleAlgorithm();
                        solver.setDebug(DEBUG);
                        solver.load(objFile.getCities(),objFile.getPaths());
                        solver.compute();	
                        String[] data=objFile.getInfoFile();
                        panelGraphicsSolution.scratchOut();
                        if (solver.getObjetiveValue()> 0){
                                //~ DecimalFormat df = new DecimalFormat("#.####");
                                txtOut.setText("<html><b>File name: </b><font color='green'>"+data[0]+"</font><br>");
                                txtOut.setText(txtOut.getText()+"<b>Nodes: </b><font color='blue'>"+data[1]+"</font> ");
                                txtOut.setText(txtOut.getText()+"<b>Edges: </b><font color='blue'>"+data[2]+"</font><br>");
                                txtOut.setText(txtOut.getText()+"<b>Edges in solution: </b><font color='BLUE'>"+solver.getStringSolution()+"</font><br>");
                                //~ txtOut.setText(txtOut.getText()+"<b>Value Objective: </b><font color='red'>"+df.format(solver.getObjetiveValue())+"</font></html>");
                                txtOut.setText(txtOut.getText()+"<b>Value Objective: </b><font color='red'>"+solver.getObjetiveValue()+"</font></html>");

                                Thread t = new Thread(){
                                public void run(){
                                        panelGraphicsSolution.sketch(objFile.getCities(),objFile.getPaths(),solver.getVertexSolution());
                                        //~ panelGraphicsSolution.sketch(objFile.getCities(),objFile.getPaths(),solver.getBoolSolution());
                                }};
                                t.start(); 
                        }                     
                        
                }catch(IllegalArgumentException iae){
                      JOptionPane.showMessageDialog(this,
                        "Problem load instance from error cause: "+iae.getMessage(),
                        "\nSolution Problem "+this.getTitle(),
                        JOptionPane.ERROR_MESSAGE);                        
                }catch(LpSolveException lpse){
                        JOptionPane.showMessageDialog(this,
                        "This problem is infeasible.\n objetive value obtained: "+solver.getObjetiveValue()
                        +" Error cause: "+lpse.getMessage(),
                        "\nSolution Problem "+this.getTitle(),
                        JOptionPane.ERROR_MESSAGE);                        
                }catch(NullPointerException npe){			
                        JOptionPane.showMessageDialog(this,
                        "Problem load instance from file before running\n '."+npe.getCause().getMessage(),
                        "User Problem "+this.getTitle(),
                        JOptionPane.ERROR_MESSAGE);
		}
        }   
        public void actionPerformed(ActionEvent e) {
                if(e.getSource()==menuItemExit){
                        System.exit(0);
                }
                if(e.getSource()==buttonSetting){
                        Color initialBackground = panelGraphicsProblem.getBackground();
                        Color background = JColorChooser.showDialog(this,
                        "Background color "+this.getTitle(), initialBackground);
                        if (background != null) {
                                panelGraphicsProblem.setBackground(background);
                                panelGraphicsSolution.setBackground(background);
                        }
                
                }
                if(e.getSource()==buttonVelocity){
                        Integer speedValue = new Integer (JOptionPane.showInputDialog(this,"Velocity","Velocity dialog "+this.getTitle(),
                        JOptionPane.QUESTION_MESSAGE));
                        if (speedValue > 0 && speedValue<2000) {
                                panelGraphicsProblem.setSpeed(speedValue);
                                panelGraphicsSolution.setSpeed(speedValue);
                        }
                }
                if(e.getSource()==menuItemRun||e.getSource()==buttonRun){
                        createSolution();
                }
                if(e.getSource()==menuItemOpen||e.getSource()==buttonOpen){
                        JFileChooser chooser = new JFileChooser("./entradas");
                        int returnVal = chooser.showOpenDialog(this);
                        if(returnVal == JFileChooser.APPROVE_OPTION) {
                                try{
                                        objFile=new FileLoader(chooser.getSelectedFile());
                                        showMap();
                                        if(DEBUG){
                                //~ System.out.println(":FILELOADER:\n"+objFile.toString());
										}                                        
                                }catch(Exception ioe){
                                  JOptionPane.showMessageDialog(this,
                                        "Problem reading file: '" + chooser.getSelectedFile().getName()+"\n"+ioe+ "'.",
                                        "Read file problem "+this.getTitle(),
                                        JOptionPane.ERROR_MESSAGE);
                                }
                        }
                }
                if(e.getSource()==menuItemSave||e.getSource()==buttonSave){
                        String[] data=objFile.getInfoFile();
                        String content = "( "+data[0]+" ) ( "+solver.getObjetiveValue()+" )\n"+solver.getStringSolution();
                        JFileChooser chooser = new JFileChooser("./salidas");
                        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                                File file = chooser.getSelectedFile();
                                try{
                                objFile.writeSolution(file,content);
                                }catch(Exception ioe){
                                  JOptionPane.showMessageDialog(this,
                                        "Problem writing file: '" + chooser.getSelectedFile().getName()+"\n"+ioe+ "'.",
                                        "Write file problem "+this.getTitle(),
                                        JOptionPane.ERROR_MESSAGE);
                                }
                        }
                }               
                if(e.getSource()==menuItemHelp||e.getSource()==buttonHelp){
                        final JComponent[] inputs = new JComponent[] {
                                new JLabel("<html>Jefferson Amado Pe&ntilde;a Torres<br>"+
                                                "id.code: 1425590<br>"+
                                                "mail: <i>jeffersonamado@gmail.com</i><br>"+
                                                "Cindy Valencia Tenorio<br>"+
                                                "id.code: 1110400<br>"+
                                                "mail: <i>cindypv23@gmail.com</i><br>"+
                                                "Julieth Alegria Vaca<br>"+
                                                "id.code: 1110009<br>"+
                                                "mail: <i>jalegria28@gmail.com</i><br>"+
                                                "</html>"),
                                new JLabel("<html><p>"+
                                "<br><br>The Traveling Salesman Problem with Time Windows (TSPTW)<br> is the problem of finding"+
                                "a minimum-cost path visiting a set of cities exactly once,<br> where each city must be visited"+
                                "within a specific time window<p><html>")
                        };
                        JOptionPane.showMessageDialog(this, inputs, "Help & About",
                         JOptionPane.INFORMATION_MESSAGE);
                } 
                if(e.getSource()==menuItemConver){      
                        final JFileChooser chooser = new JFileChooser("./entradas");
                        int returnVal = chooser.showOpenDialog(this);
                        if(returnVal == JFileChooser.APPROVE_OPTION) {
                                try{
                                        //~ Thread t = new Thread(){
                                        //~ public void run(){
                                                new LectorArchivoTxt(chooser.getSelectedFile());
                                        //~ }};
                                        //~ t.start();                                                
                                        if(DEBUG){
                                                System.out.println(":CONVERTER:\n");
                                        }                                        
                                }catch(Exception ioe){
                                  JOptionPane.showMessageDialog(this,
                                        "Problem reading file: '" + chooser.getSelectedFile().getName()+"\n"+ioe+ "'.",
                                        "Read file problem "+this.getTitle(),
                                        JOptionPane.ERROR_MESSAGE);
                                }
                        }                        
                }
                if(e.getSource()==menuItemInfo||e.getSource()==buttonInfo){
                        String[] data=objFile.getInfoFile();
                        try{
                        JComponent[] inputs = new JComponent[] {
                                new JLabel("<html><b>File name:</b>"+data[0]+"</html>"),
                                new JLabel("<html><b>Nodes:</b>"+data[1]+"</html>"),
                                new JLabel("<html><b>Edges:</b>"+data[2]+"</html>")
                        };
                        JOptionPane.showMessageDialog(this, inputs, "Information about File",
                         JOptionPane.INFORMATION_MESSAGE);
                        }catch(NullPointerException npe){
                                JOptionPane.showMessageDialog(this,
                                        "Problem in load file:",
                                        "Not Graph load "+this.getTitle(),
                                        JOptionPane.ERROR_MESSAGE);                                
                        }
                }
        }
        public static void main(String args[]){
                if(args.length >= 1){				
                        if(args[0].equals("--debug")){
                                TSPTW mi=new TSPTW();
                                System.out.println("TSPTW RUN IN DEBUG MODE");
                                mi.DEBUG=true;
                                mi.setVisible(true);  
                        }
                }else{
										
                        TSPTW mi=new TSPTW();
                        mi.setVisible(true);
                }
        } 
}
