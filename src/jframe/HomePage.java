/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Name
 */
public class HomePage extends javax.swing.JFrame {

    /**
     * Creates new form HomePage
     */
    // Mouse Hover Effect
    Color mouseEnterColor = new Color(0, 0, 0);
    Color mouseExitColor = new Color(51, 51, 51);

    // default constructor
    public HomePage() {
        initComponents();
        Connect();
        showPieChart();
        Book_Load();
        Student_Load();
        setIconImage();
        countStudentDetails();
        countBookDetails();
        countIssueBookDetails();
        countDefaultersDetails();
    }

    // set Icon method
    private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));

    }

    int id;
    String uname;
    String usertype;

    // User defined constructor
    public HomePage(int id, String username, String utype) {
        initComponents();
        Connect();
        showPieChart();
        Book_Load();
        Student_Load();
        setIconImage();
        countStudentDetails();
        countBookDetails();
        countIssueBookDetails();
        countDefaultersDetails();

        this.uname = username;

        this.usertype = utype;
        jLabel2.setText(utype);

        this.id = id;

    }

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    DefaultTableModel d;

    // Database connectivity method
    public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library_management_system", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(ManageBooks.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManageBooks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Loading book details from the database table
    public void Book_Load() {
        int c;
        try {
            pst = con.prepareStatement("select * from book_details");
            rs = pst.executeQuery();

            ResultSetMetaData rsd = rs.getMetaData();
            c = rsd.getColumnCount();

            d = (DefaultTableModel) jTable2.getModel();
            d.setRowCount(0);
            while (rs.next()) {
                Vector v2 = new Vector();
                for (int i = 1; i <= c; i++) {
                    v2.add(rs.getString("book_id"));
                    v2.add(rs.getString("book_name"));
                    v2.add(rs.getString("author"));
                    v2.add(rs.getString("quantity"));

                }
                d.addRow(v2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageBooks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Loading student details from the database table
    public void Student_Load() {
        int c;
        try {
            pst = con.prepareStatement("select * from student_details");
            rs = pst.executeQuery();

            ResultSetMetaData rsd = rs.getMetaData();
            c = rsd.getColumnCount();

            d = (DefaultTableModel) jTable1.getModel();
            d.setRowCount(0);
            while (rs.next()) {
                Vector v2 = new Vector();
                for (int i = 1; i <= c; i++) {
                    v2.add(rs.getString("student_id"));
                    v2.add(rs.getString("student_name"));
                    v2.add(rs.getString("course"));
                    v2.add(rs.getString("branch"));

                }
                d.addRow(v2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Counting No.of.Books from the database table
    public void countBookDetails() {

        int noOfBooksCount = 0;
        try {
            pst = con.prepareStatement("select * from book_details");
            rs = pst.executeQuery();
            while (rs.next()) {
                noOfBooksCount++;

            }
            lbl_noOfBooks.setText(Integer.toString(noOfBooksCount));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Counting No.of.students rom the database table
    public void countStudentDetails() {

        int noOfStudentsCount = 0;
        try {
            pst = con.prepareStatement("select * from student_details");
            rs = pst.executeQuery();
            while (rs.next()) {
                noOfStudentsCount++;

            }
            lbl_noOfStudents.setText(Integer.toString(noOfStudentsCount));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Counting No.of.Issued books from the database table
    public void countIssueBookDetails() {

        int noOfIssuedBooksCount = 0;
        try {
            pst = con.prepareStatement("select * from issue_book_details where status=?");
            pst.setString(1, "pending");
            rs = pst.executeQuery();
            while (rs.next()) {
                noOfIssuedBooksCount++;

            }
            lbl_noOfIssuedBooks.setText(Integer.toString(noOfIssuedBooksCount));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Counting No.of.Defaulters from the database table
    public void countDefaultersDetails() {
        long l = System.currentTimeMillis();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = df.format(l);
        int noOfDefaultersCount = 0;
        try {
            pst = con.prepareStatement("select * from issue_book_details where due_date<?");
            pst.setString(1, todayDate);
            rs = pst.executeQuery();
            while (rs.next()) {
                noOfDefaultersCount++;

            }
            lbl_noOfDefaulters.setText(Integer.toString(noOfDefaultersCount));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Visualization method
    public void showPieChart() {

        //create dataset
        DefaultPieDataset barDataset = new DefaultPieDataset();

        try {
            pst = con.prepareStatement("select book_name, count(*) as issue_count from issue_book_details group by book_name");
            rs = pst.executeQuery();
            while (rs.next()) {
                barDataset.setValue(rs.getString("book_name"), new Double(rs.getDouble("issue_count")));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //create chart
        JFreeChart piechart = ChartFactory.createPieChart("Issued Book details", barDataset, true, true, false);//explain

        PiePlot piePlot = (PiePlot) piechart.getPlot();

        piePlot.setBackgroundPaint(Color.white);

        //create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(piechart);
        panelPieChart.removeAll();
        panelPieChart.add(barChartPanel, BorderLayout.CENTER);
        panelPieChart.validate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel38 = new javax.swing.JPanel();
        jPanel39 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jPanel40 = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        lbl_noOfBooks = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        lbl_noOfStudents = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        lbl_noOfIssuedBooks = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        lbl_noOfDefaulters = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new rojeru_san.complementos.RSTableMetro();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new rojeru_san.complementos.RSTableMetro();
        jLabel28 = new javax.swing.JLabel();
        panelPieChart = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_menu_48px_1.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 37, -1));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 5, 50));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("usertype");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1420, 20, -1, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("X");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 0, -1, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Library Management System");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/male_user_50px.png"))); // NOI18N
        jLabel7.setText("Welcome,");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 0, -1, 70));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1900, 70));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setForeground(new java.awt.Color(153, 153, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 51, 51));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel6.setBackground(new java.awt.Color(255, 51, 51));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 51, 51));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(153, 153, 153));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Library_26px_1.png"))); // NOI18N
        jLabel14.setText("  About Project");
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel14MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel14MouseExited(evt);
            }
        });
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 190, -1));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 590, 300, 47));

        jPanel8.setBackground(new java.awt.Color(51, 51, 51));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(255, 51, 51));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel8.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 153, 153));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Library_26px_1.png"))); // NOI18N
        jLabel10.setText("  LMS Dashboard");
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel8.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 190, -1));

        jPanel3.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 300, 47));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Features");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 190, -1));

        jPanel14.setBackground(new java.awt.Color(51, 51, 51));
        jPanel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel14MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel14MouseExited(evt);
            }
        });
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel15.setBackground(new java.awt.Color(255, 51, 51));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel14.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 153, 153));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Books_26px.png"))); // NOI18N
        jLabel13.setText("  Manage Books");
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel13MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel13MousePressed(evt);
            }
        });
        jPanel14.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 190, -1));

        jPanel3.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 300, 47));

        jPanel16.setBackground(new java.awt.Color(51, 51, 51));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel17.setBackground(new java.awt.Color(255, 51, 51));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel16.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(153, 153, 153));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Read_Online_26px.png"))); // NOI18N
        jLabel15.setText("  Manage Students");
        jLabel15.setAutoscrolls(true);
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel15MouseExited(evt);
            }
        });
        jPanel16.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 190, -1));

        jPanel3.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 300, 47));

        jPanel20.setBackground(new java.awt.Color(51, 51, 51));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel21.setBackground(new java.awt.Color(255, 51, 51));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel20.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(153, 153, 153));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Sell_26px.png"))); // NOI18N
        jLabel17.setText("  Issue Book");
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel17MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel17MouseExited(evt);
            }
        });
        jPanel20.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 190, -1));

        jPanel3.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 300, 47));

        jPanel22.setBackground(new java.awt.Color(51, 51, 51));
        jPanel22.setForeground(new java.awt.Color(153, 153, 153));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel23.setBackground(new java.awt.Color(255, 51, 51));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel22.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(153, 153, 153));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Return_Purchase_26px.png"))); // NOI18N
        jLabel18.setText("  Return Book");
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel18MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel18MouseExited(evt);
            }
        });
        jPanel22.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 190, -1));

        jPanel3.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 300, 47));

        jPanel24.setBackground(new java.awt.Color(51, 51, 51));
        jPanel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel25.setBackground(new java.awt.Color(255, 51, 51));
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel24.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(153, 153, 153));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel19.setText("  View Records");
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel19MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel19MouseExited(evt);
            }
        });
        jPanel24.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 190, -1));

        jPanel3.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 300, 47));

        jPanel26.setBackground(new java.awt.Color(51, 51, 51));
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel27.setBackground(new java.awt.Color(255, 51, 51));
        jPanel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel26.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(153, 153, 153));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Books_26px.png"))); // NOI18N
        jLabel20.setText("  View Issued Books");
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel20MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel20MouseExited(evt);
            }
        });
        jPanel26.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 230, -1));

        jPanel3.add(jPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 300, 47));

        jPanel28.setBackground(new java.awt.Color(51, 51, 51));
        jPanel28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel29.setBackground(new java.awt.Color(255, 51, 51));
        jPanel29.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel28.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(153, 153, 153));
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Conference_26px.png"))); // NOI18N
        jLabel21.setText("  View Defaulter List");
        jLabel21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel21MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel21MouseExited(evt);
            }
        });
        jPanel28.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 240, -1));

        jPanel3.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 300, 47));

        jPanel38.setBackground(new java.awt.Color(255, 51, 51));
        jPanel38.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel39.setBackground(new java.awt.Color(255, 51, 51));
        jPanel39.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setBackground(new java.awt.Color(255, 255, 255));
        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Home_26px_2.png"))); // NOI18N
        jLabel34.setText("  Home Page");
        jLabel34.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel39.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 150, -1));

        jPanel38.add(jPanel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel35.setBackground(new java.awt.Color(255, 255, 255));
        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Home_26px_2.png"))); // NOI18N
        jLabel35.setText("  Home Page");
        jPanel38.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 150, -1));

        jPanel40.setBackground(new java.awt.Color(255, 51, 51));
        jPanel40.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel41.setBackground(new java.awt.Color(255, 51, 51));
        jPanel41.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel36.setBackground(new java.awt.Color(255, 255, 255));
        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Home_26px_2.png"))); // NOI18N
        jLabel36.setText("  Home Page");
        jPanel41.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 150, -1));

        jPanel40.add(jPanel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel37.setBackground(new java.awt.Color(255, 255, 255));
        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Home_26px_2.png"))); // NOI18N
        jLabel37.setText("  Home Page");
        jPanel40.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 150, -1));

        jPanel38.add(jPanel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel3.add(jPanel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 47));

        jPanel12.setBackground(new java.awt.Color(51, 153, 255));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(255, 51, 51));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel12.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Exit_26px_2.png"))); // NOI18N
        jLabel12.setText("  Logout");
        jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        jPanel12.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 190, -1));

        jPanel3.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 650, 300, 47));

        jPanel10.setBackground(new java.awt.Color(51, 153, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setBackground(new java.awt.Color(255, 51, 51));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel10.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/male_user_50px.png"))); // NOI18N
        jLabel11.setText("Account Management");
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel10.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 260, 60));

        jPanel3.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 300, 60));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 300, 960));

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel31.setBackground(new java.awt.Color(255, 102, 0));
        jPanel31.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 102, 0)));
        jPanel31.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_noOfBooks.setFont(new java.awt.Font("Calibri", 1, 50)); // NOI18N
        lbl_noOfBooks.setForeground(new java.awt.Color(102, 102, 102));
        lbl_noOfBooks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Book_Shelf_50px.png"))); // NOI18N
        lbl_noOfBooks.setText("10");
        jPanel31.add(lbl_noOfBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 120, 90));

        jPanel30.add(jPanel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 260, 140));

        jLabel22.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
        jLabel22.setText("Student Details");
        jPanel30.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        jLabel23.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(102, 102, 102));
        jLabel23.setText("No. Of Students");
        jPanel30.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, -1, -1));

        jPanel32.setBackground(new java.awt.Color(255, 255, 0));
        jPanel32.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 255, 0)));
        jPanel32.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_noOfStudents.setFont(new java.awt.Font("Calibri", 1, 50)); // NOI18N
        lbl_noOfStudents.setForeground(new java.awt.Color(102, 102, 102));
        lbl_noOfStudents.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_People_50px.png"))); // NOI18N
        lbl_noOfStudents.setText("10");
        jPanel32.add(lbl_noOfStudents, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 120, 90));

        jPanel30.add(jPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 260, 140));

        jLabel25.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 102, 102));
        jLabel25.setText("No. Of Students");
        jPanel30.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, -1, -1));

        jPanel33.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 51, 51)));
        jPanel33.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setFont(new java.awt.Font("Calibri", 1, 50)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(102, 102, 102));
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_People_50px.png"))); // NOI18N
        jLabel26.setText("10");
        jPanel33.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 120, 90));

        jPanel30.add(jPanel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 260, 140));

        jPanel35.setBackground(new java.awt.Color(51, 204, 0));
        jPanel35.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(51, 204, 0)));
        jPanel35.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_noOfIssuedBooks.setFont(new java.awt.Font("Calibri", 1, 50)); // NOI18N
        lbl_noOfIssuedBooks.setForeground(new java.awt.Color(102, 102, 102));
        lbl_noOfIssuedBooks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Sell_50px.png"))); // NOI18N
        lbl_noOfIssuedBooks.setText("10");
        jPanel35.add(lbl_noOfIssuedBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 120, 90));

        jPanel30.add(jPanel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 60, 260, 140));

        jLabel29.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(102, 102, 102));
        jLabel29.setText("Issued Books");
        jPanel30.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 30, -1, -1));

        jLabel31.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(102, 102, 102));
        jLabel31.setText("Defaulter List");
        jPanel30.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 30, -1, -1));

        jPanel36.setBackground(new java.awt.Color(255, 51, 255));
        jPanel36.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 51, 255)));
        jPanel36.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_noOfDefaulters.setFont(new java.awt.Font("Calibri", 1, 50)); // NOI18N
        lbl_noOfDefaulters.setForeground(new java.awt.Color(102, 102, 102));
        lbl_noOfDefaulters.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_List_of_Thumbnails_50px.png"))); // NOI18N
        lbl_noOfDefaulters.setText("10");
        jPanel36.add(lbl_noOfDefaulters, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 120, 90));

        jPanel30.add(jPanel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 60, 260, 140));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Student ID", "Student Name", "Course", "Branch"
            }
        ));
        jTable1.setColorBackgoundHead(new java.awt.Color(51, 153, 255));
        jTable1.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        jTable1.setColorSelBackgound(new java.awt.Color(255, 51, 51));
        jTable1.setFuenteFilas(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable1.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable1.setFuenteHead(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable1.setRowHeight(30);
        jScrollPane1.setViewportView(jTable1);

        jPanel30.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 570, 170));

        jLabel27.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(102, 102, 102));
        jLabel27.setText("No. Of Books");
        jPanel30.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Book ID", "Book Name", "Author", "Quantity"
            }
        ));
        jTable2.setColorBackgoundHead(new java.awt.Color(51, 153, 255));
        jTable2.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        jTable2.setColorSelBackgound(new java.awt.Color(255, 51, 51));
        jTable2.setFuenteFilas(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable2.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable2.setFuenteHead(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTable2.setRowHeight(30);
        jScrollPane2.setViewportView(jTable2);

        jPanel30.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 490, 570, 170));

        jLabel28.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(102, 102, 102));
        jLabel28.setText("Book Details");
        jPanel30.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, -1, -1));

        panelPieChart.setLayout(new java.awt.BorderLayout());
        jPanel30.add(panelPieChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 220, 540, 450));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Naveenkumar J");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel30.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 680, -1, -1));

        jLabel8.setText("Developed by:");
        jPanel30.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 680, -1, -1));

        getContentPane().add(jPanel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 1230, 700));

        setSize(new java.awt.Dimension(1528, 770));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
        ManageBooks mb = new ManageBooks(id, uname, usertype);
        mb.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        if (usertype.equals("Admin")) {
            SignUpPage sp = new SignUpPage(id, uname, usertype);
            sp.setVisible(true);
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Only Admin can manage the Accounts!");
        }

    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
        AboutPage ap = new AboutPage(id, uname, usertype);
        ap.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        // TODO add your handling code here:
        ManageStudents ms = new ManageStudents(id, uname, usertype);
        ms.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
        LoginPage lp = new LoginPage();
        lp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseEntered
        // TODO add your handling code here:
        jPanel14.setBackground(mouseEnterColor);

    }//GEN-LAST:event_jLabel13MouseEntered

    private void jLabel13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseExited
        // TODO add your handling code here:
        jPanel14.setBackground(mouseExitColor);

    }//GEN-LAST:event_jLabel13MouseExited

    private void jPanel14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel14MouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_jPanel14MouseEntered

    private void jPanel14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel14MouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_jPanel14MouseExited

    private void jLabel13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jLabel13MousePressed

    private void jLabel15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseEntered
        // TODO add your handling code here:
        jPanel16.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel15MouseEntered

    private void jLabel15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseExited
        // TODO add your handling code here:
        jPanel16.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel15MouseExited

    private void jLabel17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseEntered
        // TODO add your handling code here:
        jPanel20.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel17MouseEntered

    private void jLabel17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseExited
        // TODO add your handling code here:
        jPanel20.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel17MouseExited

    private void jLabel18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseEntered
        // TODO add your handling code here:
        jPanel22.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel18MouseEntered

    private void jLabel18MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseExited
        // TODO add your handling code here:
        jPanel22.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel18MouseExited

    private void jLabel19MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseEntered
        // TODO add your handling code here:
        jPanel24.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel19MouseEntered

    private void jLabel19MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseExited
        // TODO add your handling code here:
        jPanel24.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel19MouseExited

    private void jLabel20MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseEntered
        // TODO add your handling code here:
        jPanel26.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel20MouseEntered

    private void jLabel20MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseExited
        // TODO add your handling code here:
        jPanel26.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel20MouseExited

    private void jLabel21MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseEntered
        // TODO add your handling code here:
        jPanel28.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel21MouseEntered

    private void jLabel21MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseExited
        // TODO add your handling code here:
        jPanel28.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel21MouseExited

    private void jLabel14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseEntered
        // TODO add your handling code here:
        jPanel4.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel14MouseEntered

    private void jLabel14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseExited
        // TODO add your handling code here:
        jPanel4.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel14MouseExited

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        // TODO add your handling code here:
        IssueBook ib = new IssueBook(id, uname, usertype);
        ib.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        // TODO add your handling code here:
        ReturnBook rb = new ReturnBook(id, uname, usertype);
        rb.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        // TODO add your handling code here:
        ViewAllRecord vr = new ViewAllRecord(id, uname, usertype);
        vr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        // TODO add your handling code here:
        IssueBookDetails ib = new IssueBookDetails(id, uname, usertype);
        ib.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        // TODO add your handling code here:
        DefaultersList df = new DefaultersList(id, uname, usertype);
        df.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel21MouseClicked

    // Developer details
    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            Desktop.getDesktop().browse(new URI("https://github.com/naveenkumar-j"));
        } catch (IOException ex) {
            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel5MouseClicked

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
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private rojeru_san.complementos.RSTableMetro jTable1;
    private rojeru_san.complementos.RSTableMetro jTable2;
    private javax.swing.JLabel lbl_noOfBooks;
    private javax.swing.JLabel lbl_noOfDefaulters;
    private javax.swing.JLabel lbl_noOfIssuedBooks;
    private javax.swing.JLabel lbl_noOfStudents;
    private javax.swing.JPanel panelPieChart;
    // End of variables declaration//GEN-END:variables
}
