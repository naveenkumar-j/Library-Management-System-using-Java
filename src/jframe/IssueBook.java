/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Name
 */
public class IssueBook extends javax.swing.JFrame {

    /**
     * Creates new form IssueBook
     */
    // default constructor
    public IssueBook() {
        initComponents();
        Connect();
        setIconImage();

    }

    int id;
    String uname;
    String usertype;

    // Parameterized Constructor
    public IssueBook(int id, String username, String utype) {
        initComponents();
        Connect();

        setIconImage();
        this.id = id;
        this.uname = username;

        this.usertype = utype;
        jLabel19.setText(utype);

        if (usertype.equals("Admin")) {
            issuebutton.setEnabled(true);
            clearbutton.setEnabled(true);
        } else if (usertype.equals("Librarian")) {
            issuebutton.setEnabled(true);
            clearbutton.setEnabled(true);

        } else {
            issuebutton.setEnabled(false);
        }

    }

    // set Icon method
    private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));

    }

    // Database connectivity method
    public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library_management_system", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(ManageStudents.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManageStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    // Loading details from the database table method
    public void getBookDetails() {
        int book_id = Integer.parseInt(txt_bookid.getText());

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library_management_system", "root", "");
            pst = con.prepareStatement("select * from book_details where book_id=?");
            pst.setInt(1, book_id);
            rs = pst.executeQuery();
            if (rs.next()) {
                issue_bookid.setText(rs.getString("book_id"));
                issue_bookname.setText(rs.getString("book_name"));
                issue_author.setText(rs.getString("author"));
                issue_quantity.setText(rs.getString("quantity"));
                txt_bookerror.setText("");

            } else {
                txt_bookerror.setText("Book Id not found!");
                issue_bookid.setText("");
                issue_bookname.setText("");
                issue_author.setText("");
                issue_quantity.setText("");

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    // Loading student details from the database table
    public void getStudentDetails() {
        int student_id = Integer.parseInt(txt_studentid.getText());

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library_management_system", "root", "");
            pst = con.prepareStatement("select * from student_details where student_id=?");
            pst.setInt(1, student_id);
            rs = pst.executeQuery();
            if (rs.next()) {

                text_studentid.setText(rs.getString("student_id"));
                text_studentname.setText(rs.getString("student_name"));
                text_course.setText(rs.getString("course"));
                text_branch.setText(rs.getString("branch"));
                txt_studenterror.setText("");

            } else {
                txt_studenterror.setText("Student Id not found!");
                text_studentid.setText("");
                text_studentname.setText("");
                text_course.setText("");
                text_branch.setText("");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    // Update book count to the database table
    public void updateBookCount() {
        try {
            int bookId = Integer.parseInt(txt_bookid.getText());
            pst = con.prepareStatement("update book_details set quantity=quantity-1 where book_id=?");
            pst.setInt(1, bookId);
            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                JOptionPane.showMessageDialog(this, "Book Count updated successfully!");
                int initialCount = Integer.parseInt(issue_quantity.getText());
                issue_quantity.setText(Integer.toString(initialCount - 1));
            } else {
                JOptionPane.showMessageDialog(this, "Error in updating Book count");
            }

        } catch (SQLException ex) {
            Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Checking duplicate records
    public boolean isAlreadyIssued() {
        boolean isAlreadyIssued = false;

        int bookId = Integer.parseInt(txt_bookid.getText());
        int studentId = Integer.parseInt(txt_studentid.getText());

        try {
            Connect();
            String sql = "select * from issue_book_details where book_id=? and student_id=? and status=?";

            pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            pst.setInt(2, studentId);
            pst.setString(3, "pending");

            rs = pst.executeQuery();
            if (rs.next()) {
                isAlreadyIssued = true;
            } else {
                isAlreadyIssued = false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isAlreadyIssued;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel20 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        issue_quantity = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        issue_bookid = new javax.swing.JLabel();
        issue_bookname = new javax.swing.JLabel();
        issue_author = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_bookerror = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        text_branch = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        text_course = new javax.swing.JLabel();
        text_studentname = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        text_studentid = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txt_studenterror = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        txt_bookid = new app.bolivia.swing.JCTextField();
        jLabel23 = new javax.swing.JLabel();
        txt_studentid = new app.bolivia.swing.JCTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        clearbutton = new rojerusan.RSMaterialButtonRectangle();
        issuebutton = new rojerusan.RSMaterialButtonRectangle();
        date_issuedate = new com.toedter.calendar.JDateChooser();
        date_duedate = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel20.setText("  Student Details");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(51, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel1.setText("BACK");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 40));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel2.setText("  Book Details");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, -1, -1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 310, 5));

        issue_quantity.setBackground(new java.awt.Color(255, 255, 255));
        issue_quantity.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        issue_quantity.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(issue_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 600, 140, 40));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Book Name  :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, -1, -1));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Author         :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 530, -1, -1));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Book Id        :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 130, -1));

        issue_bookid.setBackground(new java.awt.Color(255, 255, 255));
        issue_bookid.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        issue_bookid.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(issue_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 350, 130, 30));

        issue_bookname.setBackground(new java.awt.Color(255, 255, 255));
        issue_bookname.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        issue_bookname.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(issue_bookname, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 440, 220, 30));

        issue_author.setBackground(new java.awt.Color(255, 255, 255));
        issue_author.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        issue_author.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(issue_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 520, 210, 50));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Quantity     :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 610, -1, -1));

        txt_bookerror.setBackground(new java.awt.Color(255, 255, 255));
        txt_bookerror.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txt_bookerror.setForeground(new java.awt.Color(255, 255, 0));
        jPanel2.add(txt_bookerror, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 690, 300, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 800));

        jPanel3.setBackground(new java.awt.Color(51, 153, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 310, 5));

        text_branch.setBackground(new java.awt.Color(255, 255, 255));
        text_branch.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        text_branch.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(text_branch, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 610, 170, 30));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Branch              :");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 610, -1, -1));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Course              :");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 530, -1, -1));

        text_course.setBackground(new java.awt.Color(255, 255, 255));
        text_course.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        text_course.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(text_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 530, 150, 30));

        text_studentname.setBackground(new java.awt.Color(255, 255, 255));
        text_studentname.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        text_studentname.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(text_studentname, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 430, 200, 40));

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Student Name   :");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, -1, -1));

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Student ID       :");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 150, -1));

        text_studentid.setBackground(new java.awt.Color(255, 255, 255));
        text_studentid.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        text_studentid.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(text_studentid, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 340, 150, 40));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel21.setText("  Student Details");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));

        txt_studenterror.setBackground(new java.awt.Color(255, 255, 255));
        txt_studenterror.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txt_studenterror.setForeground(new java.awt.Color(255, 255, 0));
        jPanel3.add(txt_studenterror, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 680, 300, 50));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 420, 800));

        jLabel11.setBackground(new java.awt.Color(255, 51, 51));
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 51, 51));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel11.setText("  Issue Book");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 130, -1, -1));

        jPanel8.setBackground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 190, -1, 5));

        jPanel9.setBackground(new java.awt.Color(51, 153, 255));
        jPanel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
        });
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("X");
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });
        jPanel9.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, -1));

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1350, 0, 60, 30));

        txt_bookid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        txt_bookid.setForeground(new java.awt.Color(51, 51, 51));
        txt_bookid.setCaretColor(new java.awt.Color(204, 204, 204));
        txt_bookid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_bookid.setPhColor(new java.awt.Color(51, 51, 51));
        txt_bookid.setPlaceholder("Enter Book Id");
        txt_bookid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookidFocusLost(evt);
            }
        });
        jPanel1.add(txt_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 250, 300, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 51, 51));
        jLabel23.setText("Book Id:");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 260, -1, 30));

        txt_studentid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        txt_studentid.setForeground(new java.awt.Color(51, 51, 51));
        txt_studentid.setCaretColor(new java.awt.Color(204, 204, 204));
        txt_studentid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_studentid.setPhColor(new java.awt.Color(51, 51, 51));
        txt_studentid.setPlaceholder("Enter Student Id");
        txt_studentid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentidFocusLost(evt);
            }
        });
        jPanel1.add(txt_studentid, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 340, 300, -1));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 51, 51));
        jLabel24.setText("Issue Date:");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 430, 110, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 51, 51));
        jLabel25.setText("Student Id:");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 350, 110, 30));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 51, 51));
        jLabel26.setText("Due Date   :");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 530, 110, 30));

        clearbutton.setBackground(new java.awt.Color(255, 51, 51));
        clearbutton.setText("clear data");
        clearbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearbuttonActionPerformed(evt);
            }
        });
        jPanel1.add(clearbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 650, 190, 50));

        issuebutton.setBackground(new java.awt.Color(255, 51, 51));
        issuebutton.setText("Issue Book");
        issuebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issuebuttonActionPerformed(evt);
            }
        });
        jPanel1.add(issuebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 650, 190, 50));

        date_issuedate.setBackground(new java.awt.Color(255, 255, 255));
        date_issuedate.setForeground(new java.awt.Color(255, 51, 51));
        jPanel1.add(date_issuedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 430, 290, 40));
        jPanel1.add(date_duedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 520, 290, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Naveenkumar J");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 770, -1, -1));

        jLabel8.setText("Developed by:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 770, -1, -1));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(51, 51, 51));
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Read_Online_26px.png"))); // NOI18N
        jLabel27.setText("Welcome,");
        jLabel27.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 0, 120, 40));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText(" usertype");
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 10, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1410, 800));

        setSize(new java.awt.Dimension(1411, 803));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        HomePage hm = new HomePage(id, uname, usertype);
        hm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel22MouseClicked

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jPanel9MouseClicked

    private void txt_bookidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookidFocusLost
        // TODO add your handling code here:
        if (!txt_bookid.getText().equals("")) {
            getBookDetails();
        }

    }//GEN-LAST:event_txt_bookidFocusLost

    private void txt_studentidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentidFocusLost
        // TODO add your handling code here:
        if (!txt_studentid.getText().equals("")) {
            getStudentDetails();
        }

    }//GEN-LAST:event_txt_studentidFocusLost

    private void clearbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearbuttonActionPerformed
        // TODO add your handling code here:
        txt_bookid.setText("");
        issue_bookid.setText("");
        issue_bookname.setText("");
        issue_author.setText("");
        issue_quantity.setText("");
        txt_bookerror.setText("");

        txt_studentid.setText("");
        text_studentid.setText("");
        text_studentname.setText("");
        text_course.setText("");
        text_branch.setText("");
        txt_studenterror.setText("");
        date_duedate.setDate(null);
        date_issuedate.setDate(null);

    }//GEN-LAST:event_clearbuttonActionPerformed

    private void issuebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issuebuttonActionPerformed
        try {
            // TODO add your handling code here:

            if (issue_quantity.getText().equals("0")) {
                JOptionPane.showMessageDialog(this, "This book is currently unavailable");
            } else {
                if (isAlreadyIssued() == false) {
                    int bookId = Integer.parseInt(txt_bookid.getText());
                    String bookName = issue_bookname.getText();
                    int studentId = Integer.parseInt(txt_studentid.getText());
                    String studentName = text_studentname.getText();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String issuedate = df.format(date_issuedate.getDate());
                    String duedate = df.format(date_duedate.getDate());

                    pst = con.prepareStatement("insert into issue_book_details(book_id,book_name,student_id,student_name,issue_date,due_date,status)values(?,?,?,?,?,?,?)");
                    pst.setInt(1, bookId);
                    pst.setString(2, bookName);
                    pst.setInt(3, studentId);
                    pst.setString(4, studentName);
                    pst.setString(5, issuedate);
                    pst.setString(6, duedate);
                    pst.setString(7, "pending");

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Book Issued successfully...");
                    updateBookCount();

                } else {
                    JOptionPane.showMessageDialog(this, "This student already has this book");
                }

            }

            txt_bookid.setText("");
            txt_studentid.setText("");
            date_duedate.setDate(null);
            date_issuedate.setDate(null);

        } catch (SQLException ex) {
            Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_issuebuttonActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            Desktop.getDesktop().browse(new URI("https://github.com/naveenkumar-j"));
        } catch (IOException ex) {
            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel27MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel19MouseClicked

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
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle clearbutton;
    private com.toedter.calendar.JDateChooser date_duedate;
    private com.toedter.calendar.JDateChooser date_issuedate;
    private javax.swing.JLabel issue_author;
    private javax.swing.JLabel issue_bookid;
    private javax.swing.JLabel issue_bookname;
    private javax.swing.JLabel issue_quantity;
    private rojerusan.RSMaterialButtonRectangle issuebutton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel text_branch;
    private javax.swing.JLabel text_course;
    private javax.swing.JLabel text_studentid;
    private javax.swing.JLabel text_studentname;
    private javax.swing.JLabel txt_bookerror;
    private app.bolivia.swing.JCTextField txt_bookid;
    private javax.swing.JLabel txt_studenterror;
    private app.bolivia.swing.JCTextField txt_studentid;
    // End of variables declaration//GEN-END:variables
}
