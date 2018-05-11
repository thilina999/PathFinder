package pathfinder;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import javax.swing.ImageIcon;

/**
 * @author THILINA DISSANAYAKE 
 * IIT ID - 2016124
 * UOW ID - W1626640
 */

public class PathFinder extends javax.swing.JFrame {

    static int gridSize = 20;// size of the grid
    private final static int WATER= 5;  // weight of the cells with water
    char heuristics = 'm';// m - Manhattan
    public static int costVH = 1;//vertical and horizontal cost
    public static double costD = 2;//diagonal cost
    public static int weight=1;//weight of a cell
    static Cell[][] grid = new Cell[20][20]; // available cells
    static ArrayList<Integer[]> path = new ArrayList<Integer[]>();//path from start to end
    static PriorityQueue<Cell> open;// unvisited cells
    static boolean closed[][];// closed cells
    int xCoordinateStart=18,yCoordinateStart=1,xCoordinateEnd=1,yCoordinateEnd=18;// start and end coordinates
    static long timeStart, timeEnd;//starting time and ending time 
    //The 2D array of map with its weights
    private int [][] map = 
            { 
              {1,1,1,1,1,1,1,1,1,1,2,1,2,1,1,1,1,1,1,1},
              {4,4,1,4,1,1,1,2,2,2,2,2,2,1,1,1,1,1,1,1},
              {4,4,4,4,4,4,1,1,2,3,3,3,2,1,1,1,1,1,1,1},
              {4,4,4,4,4,4,1,1,2,3,3,3,3,2,1,1,1,1,1,1},
              {1,1,4,1,1,1,1,1,2,2,3,3,3,2,1,1,2,2,1,1},
              {1,4,4,1,2,2,1,1,1,2,2,2,2,2,1,2,2,2,1,1},
              {4,2,1,1,2,2,1,1,1,1,1,1,1,1,1,2,2,1,1,1},
              {1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,4},
              {1,1,2,3,3,2,2,1,1,1,1,1,1,1,1,1,1,4,4,4},          
              {1,2,3,3,3,3,2,2,1,1,1,1,4,4,4,4,4,4,4,1},
              {1,2,3,2,2,2,3,2,4,1,1,1,4,4,4,4,2,1,1,1},
              {1,2,2,1,1,1,4,4,4,4,1,1,4,4,4,1,1,1,1,1},
              {1,1,4,4,4,4,4,4,4,4,1,1,1,1,1,1,1,1,1,1},
              {4,4,4,4,4,4,4,4,4,1,1,5,1,1,1,1,1,1,1,5},
              {1,1,4,4,4,4,1,1,1,2,2,5,5,1,1,1,1,1,1,5},
              {1,1,1,1,1,1,1,1,1,1,2,1,5,5,5,1,1,5,5,5},
              {1,2,2,2,2,2,1,1,1,2,1,1,1,5,5,5,5,5,5,5},
              {2,2,1,1,1,1,1,1,1,1,1,1,1,5,5,5,5,5,5,5},
              {1,1,1,1,1,1,1,1,1,1,1,5,5,5,5,5,5,5,5,5},
              {1,1,1,1,1,1,1,1,1,1,1,5,5,5,5,5,5,5,5,5}   
            }; 
    
    /**
     * Creates new form PathFinder
     */
    public PathFinder() {
        initComponents();
        setTitle("Path Finder");
        setIconImage(new ImageIcon("src/resources/icon.png").getImage());
        setLocationRelativeTo(null);
        setResizable(false);
        
            MouseHandler listener = new MouseHandler();
            super.addMouseListener(listener);
            super.addMouseMotionListener(listener);    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAlgorithms = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        btnFindPath = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        pnlHeuristics = new javax.swing.JPanel();
        radioManhattan = new javax.swing.JRadioButton();
        radioEuclidean = new javax.swing.JRadioButton();
        radioChebyshev = new javax.swing.JRadioButton();
        lblTite = new javax.swing.JLabel();
        lblVersion = new javax.swing.JLabel();
        lblStart = new javax.swing.JLabel();
        lblEnd = new javax.swing.JLabel();
        lblRed = new javax.swing.JLabel();
        lblGreen = new javax.swing.JLabel();
        pnlStatus = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnFindPath.setText("Find Path");
        btnFindPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindPathActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        pnlHeuristics.setBorder(javax.swing.BorderFactory.createTitledBorder("Heuristics"));
        pnlHeuristics.setLayout(null);

        btnAlgorithms.add(radioManhattan);
        radioManhattan.setSelected(true);
        radioManhattan.setText("Manhattan");
        pnlHeuristics.add(radioManhattan);
        radioManhattan.setBounds(20, 40, 89, 25);

        btnAlgorithms.add(radioEuclidean);
        radioEuclidean.setText("Euclidean");
        pnlHeuristics.add(radioEuclidean);
        radioEuclidean.setBounds(20, 70, 83, 25);

        btnAlgorithms.add(radioChebyshev);
        radioChebyshev.setText("Chebyshev");
        pnlHeuristics.add(radioChebyshev);
        radioChebyshev.setBounds(20, 100, 89, 25);

        lblTite.setFont(new java.awt.Font("Tahoma", 1, 26)); // NOI18N
        lblTite.setForeground(new java.awt.Color(0, 204, 204));
        lblTite.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTite.setText("Path Finder");

        lblVersion.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblVersion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVersion.setText("Version 1.0");

        lblStart.setText("START");

        lblEnd.setText("END");

        lblRed.setBackground(new java.awt.Color(255, 0, 0));
        lblRed.setOpaque(true);

        lblGreen.setBackground(new java.awt.Color(0, 204, 0));
        lblGreen.setOpaque(true);

        pnlStatus.setBackground(new java.awt.Color(255, 255, 255));
        pnlStatus.setBorder(javax.swing.BorderFactory.createTitledBorder("Status"));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Total Cost");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("-");

        javax.swing.GroupLayout pnlStatusLayout = new javax.swing.GroupLayout(pnlStatus);
        pnlStatus.setLayout(pnlStatusLayout);
        pnlStatusLayout.setHorizontalGroup(
            pnlStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlStatusLayout.setVerticalGroup(
            pnlStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStatusLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblVersion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFindPath, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClear, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlHeuristics, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblRed, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblStart)
                        .addGap(18, 18, 18)
                        .addComponent(lblGreen, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(lblEnd)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(lblTite, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStart)
                    .addComponent(lblEnd)
                    .addComponent(lblRed, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGreen, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(pnlHeuristics, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnFindPath)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(pnlStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(lblVersion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(607, 607, 607)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        initGrid();        
//        for (int row = 0; row < maze.length; row++){
//            for (int col = 0; col < maze[0].length; col++){
//               System.out.print(maze[row][col]+" "); 
//            }System.out.println(); 
//        }       
//            System.out.println("START "+xCoordinateStart+" "+yCoordinateStart);
//            System.out.println("END "+xCoordinateEnd+" "+yCoordinateEnd);             
        path.clear();
        jLabel10.setText("-");
        radioManhattan.setEnabled(true);
        radioEuclidean.setEnabled(true);
        radioChebyshev.setEnabled(true);
        btnFindPath.setEnabled(true);
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnFindPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindPathActionPerformed

        if(radioManhattan.isSelected()){
            // manhattan is selected
                        heuristics = 'm';
                        costD = 2;
                        costVH = 1;
        }else if(radioEuclidean.isSelected()){
            // euclidean is selected
                        heuristics = 'e';
                        costD = Math.sqrt(2.0);
                        costVH = 1;
        } else if(radioChebyshev.isSelected()){
            // chebyshev is selected
                        heuristics = 'c';
                        costD = 1; 
                        costVH = 1; 
        } 
        
    ArrayList<int[]> blockedList = new ArrayList<int[]>();
        // add blocked cells to 'blockedList' arraylist
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map.length; y++) {
                // weight with '5' are blocked
               if (map[x][y]==WATER) {
                    blockedList.add(new int[]{x, y});
                }
            }
    }

    int[][] blockedArray = new int[blockedList.size()][2];
    int count = 0;

    for (int[] blockedlist : blockedList) {
        blockedArray[count][0] = blockedlist[0]; // x 
        blockedArray[count][1] = blockedlist[1]; // y 
        count++;
    }
    // Find path
    run(gridSize, xCoordinateStart, yCoordinateStart, xCoordinateEnd, yCoordinateEnd, blockedArray);

    // show path
    showPath( false, xCoordinateStart, yCoordinateStart, xCoordinateEnd, yCoordinateEnd, path);

        System.out.println("COST : " + grid[xCoordinateEnd][yCoordinateEnd].finalCost);
        jLabel10.setText(""+Math.round(grid[xCoordinateEnd][yCoordinateEnd].finalCost));       
    }//GEN-LAST:event_btnFindPathActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PathFinder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PathFinder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PathFinder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PathFinder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PathFinder().setVisible(true);
            }
        });
    }
    
    private class Cell{
            int x, y; 
            double groundCost = 0; // distance from starting point
            double heuristicCost = 0; // heuristic distance
            double finalCost = 0; // finalCost = groundCost + heuristicCost
            Cell prevCell; // previously visited cell

            Cell(int x, int y) {
                this.x = x;
                this.y = y;
            }            
        }
    
    int rows = 20,columns = 20,squareSize = 600/rows;

    Cell startPos,endPos; // start and end positions
    
    /**
    *  initialize the grid.
    */
    private void initGrid() {

        for (int x = 0; x < rows; x++)
            for (int y = 0; y < columns; y++) {
                if (map[x][y] == map[xCoordinateStart][yCoordinateStart])
                    startPos = new Cell(x,y);
                if (map[x][y] == map[xCoordinateEnd][yCoordinateEnd])
                    endPos = new Cell(x,y);
            }
        
        map[endPos.x][endPos.y] = map[xCoordinateEnd][yCoordinateEnd]; 
        map[startPos.x][startPos.y] = map[xCoordinateStart][yCoordinateStart];
        repaint();

    }    
                
    public static void setBlocked(int x, int y) {
        grid[x][y] = null;
    }
 
     /**
     * method to update final cost
     */
    static void updateCost(Cell currCell, Cell nextCell, double cost) {
        if (nextCell == null || closed[nextCell.x][nextCell.y]) {
            return;
        }
        // temporary final cost for nextCell
        double finalCostTemp = cost + nextCell.heuristicCost; 
        boolean openCell = open.contains(nextCell);

        // check whether new final cost is less than old final cost
        if (!openCell || finalCostTemp < nextCell.finalCost) {
            //update finalcost
            nextCell.finalCost = finalCostTemp;
            nextCell.groundCost = cost;

            nextCell.prevCell = currCell;

            if (!openCell) {
                open.add(nextCell);
            }
        }
    }


    /**
     * moves from START to END
     */
    public void move() {

        //START
        open.add(grid[xCoordinateStart][yCoordinateStart]);

        Cell current;
        while (true) {
            // assign from priority queue to current cell
            current = open.poll();

            if (current == null) {
                break;
            }
            closed[current.x][current.y] = true;

            // if end cell return
            if (current.equals(grid[xCoordinateEnd][yCoordinateEnd])) {
                return;
            }
            // update costs of connected cells
            Cell next;
            
            if (current.x - 1 >= 0) {

                // left cell
                next = grid[current.x - 1][current.y];
                weight = map[current.x - 1][current.y];
                updateCost(current, next, current.groundCost + (costVH*weight));
                if (current.y - 1 >= 0) {
                    if (heuristics != 'm') {
                        // left bottom cell (not for manhattan)
                        next = grid[current.x - 1][current.y - 1];
                        weight = map[current.x - 1][current.y-1];
                        updateCost(current, next, current.groundCost + (costD*weight));//System.out.println("[current.x][current.y] "+current.x+ "  "+current.y+"weight: "+weight);
                    }

                }

                if (current.y + 1 < grid[0].length) {

                    if (heuristics != 'm') {
                        // left top cell 
                        next = grid[current.x - 1][current.y + 1];                        
                      weight = map[current.x - 1][current.y+1];
                        updateCost(current, next, current.groundCost + (costD*weight));//System.out.println("[current.x][current.y] "+current.x+ "  "+current.y+"weight: "+weight);
                    }
                }
            }

            if (current.y - 1 >= 0) {
                // bottom cell
                next = grid[current.x][current.y - 1];
                weight = map[current.x][current.y-1];
                updateCost(current, next, current.groundCost + (costVH*weight));
            }

            if (current.y + 1 < grid[0].length) {
                // top cell
                next = grid[current.x][current.y + 1];
                weight = map[current.x][current.y+1];
                updateCost(current, next, current.groundCost + (costVH*weight));//System.out.println("costVH("+costVH+") * weight("+maze[current.x][current.y+1] +") = "+weight*costVH);
            }

            if (current.x + 1 < grid.length) {
                // right cell
                next = grid[current.x + 1][current.y];
                weight = map[current.x + 1][current.y];
                updateCost(current, next, current.groundCost + (costVH*weight));

                if (current.y - 1 >= 0) {

                    if (heuristics != 'm') {
                        // right bottom cell
                        next = grid[current.x + 1][current.y - 1];
                       weight = map[current.x + 1][current.y-1];
                        updateCost(current, next, current.groundCost + (costD*weight));//System.out.println("[current.x][current.y] "+current.x+ "  "+current.y+"weight: "+weight);
                    }
                }

                if (current.y + 1 < grid[0].length) {

                    if (heuristics != 'm') {
                        // top right cell)
                        next = grid[current.x + 1][current.y + 1];
                      weight = map[current.x + 1][current.y+1];
                        updateCost(current, next, current.groundCost + (costD*weight));//System.out.println("[current.x][current.y] "+current.x+ "  "+current.y+"weight: "+weight);
                    }
                }
            }
        }
    }    
        

    /**
     * sets heuristic values 
     */
    public void run(int n, int sx, int sy, int ex, int ey, int[][] blocked) {

        grid = new Cell[n][n];
        closed = new boolean[n][n];//all cells are closed in the beginning

        // cell with least finalCost comes to top
        open = new PriorityQueue<>(new Comparator<Object>() {
            @Override
            public int compare(Object object01, Object object02) {

                Cell cell01 = (Cell) object01;
                Cell cell02 = (Cell) object02;

                if (cell01.finalCost < cell02.finalCost) {
                    return -1; 
                } else if (cell01.finalCost > cell02.finalCost) {
                    return 1;
                } else {
                    return 0; //when equals, first in first out
                }
            }
        });
        
        timeStart = System.nanoTime();// start time

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {

                // create a cell object
                grid[x][y] = new Cell(x, y);                
                // calculate & assign heuristic cost for cell object
                switch (heuristics) {
                    case 'm':
                        // manhattan
                        grid[x][y].heuristicCost = Math.abs(x - xCoordinateEnd) + Math.abs(y - yCoordinateEnd);
                        break;
                    case 'e':
                        // euclidean
                        grid[x][y].heuristicCost = Math.sqrt(Math.pow((x - xCoordinateEnd), 2) + Math.pow((y - yCoordinateEnd), 2));
                        break;
                    case 'c': 
                        // chebyshev
                        grid[x][y].heuristicCost = Math.max(Math.abs(x - xCoordinateEnd), Math.abs(y - yCoordinateEnd));
                        break;                       
                    default:  
                        // manhattan
                        grid[x][y].heuristicCost = Math.abs(x - xCoordinateEnd) + Math.abs(y - yCoordinateEnd);
                        break;
                }
            }
        }

        
        grid[sx][sy].groundCost = 0;// groundCost begins from 0
        grid[sx][sy].finalCost = grid[sx][sy].groundCost + grid[sx][sy].heuristicCost; 
        for (int[] blockedcell : blocked) {
            setBlocked(blockedcell[0], blockedcell[1]);
        }
        // move from starting point to ending point
        move();

        // trace path
        if (closed[xCoordinateEnd][yCoordinateEnd]) {

            // start from end point
            Cell current = grid[xCoordinateEnd][yCoordinateEnd];
            path.add(new Integer[]{Integer.valueOf(current.x), Integer.valueOf(current.y)});

            // till starting cell is found
            while (current.prevCell != null) {
                // add parent of the current node
                path.add(new Integer[]{Integer.valueOf(current.prevCell.x), Integer.valueOf(current.prevCell.y)});

                current = current.prevCell;
            }
            
            timeEnd = System.nanoTime();//end time
            System.out.println();
            System.out.println("Process time : " + ((timeEnd - timeStart) / 1000000.0) + " milliseconds");

        }
    }       
    
    public void showPath( boolean which, int x1, int y1, int x2, int y2, ArrayList<Integer[]> path) {
        repaint();
        radioManhattan.setEnabled(false);
        radioEuclidean.setEnabled(false);
        radioChebyshev.setEnabled(false);
        btnFindPath.setEnabled(false);
    }
        
    /**
    * Class that handles mouse movements
    */        
    private class MouseHandler implements MouseListener, MouseMotionListener {
        private int current_row, current_col,pre_val_start=1,pre_val_end=1;
        
        @Override
        public void mousePressed(MouseEvent evt) {
            int row = (evt.getY() - 35) / squareSize;
            int col = (evt.getX() - 5) / squareSize;
            if (row >= 0 && row < rows && col >= 0 && col < columns) {
                    current_row = row;
                    current_col = col;
                    if (current_row == xCoordinateStart && current_col == yCoordinateStart) {
                            pre_val_start = pre_val_start;
                    } else if(current_row == xCoordinateEnd && current_col == yCoordinateEnd) {
                        pre_val_end = pre_val_end;
                    }
                }
        }         
            
        @Override
        public void mouseDragged(MouseEvent evt) { 
            int row = (evt.getY() - 35) / squareSize;
            int col = (evt.getX() - 5) / squareSize;
            if (row >= 0 && row < rows && col >= 0 && col < columns){
                    if (!(row == current_row && col == current_col)){
                        int new_val = map[row][col];                        
                        if (new_val != WATER){
                            if (current_row == xCoordinateStart && current_col == yCoordinateStart) {
                                  xCoordinateStart = row;
                                  yCoordinateStart = col;
                                  map[current_row][current_col] = pre_val_start;
                                  pre_val_start = new_val;
                            } else if(current_row == xCoordinateEnd && current_col == yCoordinateEnd) {
                                  xCoordinateEnd = row;
                                  yCoordinateEnd = col;
                                  map[current_row][current_col] = pre_val_end;
                                  pre_val_end = new_val;
                            }
                            
                            current_row = row;
                            current_col = col;
                            repaint();
                        }
                    }
                } 
        }
        @Override
        public void mouseReleased(MouseEvent evt) { }
        @Override
        public void mouseEntered(MouseEvent evt) { }
        @Override
        public void mouseExited(MouseEvent evt) { }
        @Override
        public void mouseMoved(MouseEvent evt) { }
        @Override
        public void mouseClicked(MouseEvent evt) { }
        
    }
         
    @Override
    public void paint (Graphics g){
        super.paint(g);
        g.translate(5, 35);
        for (int row = 0; row < map.length; row++){
            for (int col = 0; col < map[0].length; col++){
                Color color;
                switch(map[row][col]){
                    case 1 : color = Color.WHITE;break;
                    case 2 : color = Color.LIGHT_GRAY;break;
                    case 3 : color = Color.GRAY;break;
                    case 4 : color = Color.DARK_GRAY;break;
                    case 5 : color = Color.BLACK;break;
                    default : color = Color.WHITE;break;
                }
                
                for (Integer[] patharray : path) {
                    if (patharray[0] == row && patharray[1] == col) {
                        color = Color.YELLOW;
                    }
                }
                if(row==xCoordinateStart && col==yCoordinateStart){
                    color = Color.RED;
                }else if(xCoordinateEnd==row && yCoordinateEnd==col){
                    color = Color.GREEN;
                }
                g.setColor(color);
                g.fillRect(30 * col, 30 * row, 30, 30);
                g.setColor(Color.BLUE);
                g.drawRect(30 * col, 30 * row, 30, 30);
            }
        }        
    }
          
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnAlgorithms;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnFindPath;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblEnd;
    private javax.swing.JLabel lblGreen;
    private javax.swing.JLabel lblRed;
    private javax.swing.JLabel lblStart;
    private javax.swing.JLabel lblTite;
    private javax.swing.JLabel lblVersion;
    private javax.swing.JPanel pnlHeuristics;
    private javax.swing.JPanel pnlStatus;
    private javax.swing.JRadioButton radioChebyshev;
    private javax.swing.JRadioButton radioEuclidean;
    private javax.swing.JRadioButton radioManhattan;
    // End of variables declaration//GEN-END:variables
}
