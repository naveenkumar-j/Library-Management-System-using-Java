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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Name
 */
public class SignUpPage extends javax.swing.JFrame {

    /**
     * Creates new form SignUpPage
     */
    // default constructor
    public SignUpPage() {
        initComponents();
        Connect();
        setTitle("Signup page");
        setIconImage();
        txt_username.requestFocus();
    }

    int id;
    String uname;
    String usertype;

    // Parameterized constructor
    public SignUpPage(int id, String username, String utype) {
        initComponents();
        Connect();

        setTitle("Signup page");
        setIconImage();
        txt_username.requestFocus();

        setIconImage();
        this.id = id;

        this.usertype = utype;
        jLabel12.setText(utype);

    }

    // Set Icon method
    private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));

    }

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    // Database connectivity
    public void Connect() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library_management_system", "root", "");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SignUpPage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Adding user details into the database table
    public void insertUserDetails() {
        try {
            // TODO add your handling code here:
            String username = txt_username.getText();
            String password = txt_password.getText();
            String email = txt_email.getText();
            String contact = txt_mobile.getText();
            String utype = txtutype.getSelectedItem().toString();

            pst = con.prepareStatement("insert into users(username,password,email,contact,usertype)values(?,?,?,?,?)");
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, email);
            pst.setString(4, contact);
            pst.setString(5, utype);

            int updatedRowCount = pst.executeUpdate();
            if (updatedRowCount > 0) {
                JOptionPane.showMessageDialog(this, "Account created successfully...");
                txt_username.setText("");
                txt_password.setText("");
                txt_email.setText("");
                txt_mobile.setText("");
                txtutype.setSelectedIndex(0);

            } else {
                JOptionPane.showMessageDialog(this, "Account Insertion Failed");

            }

        } catch (SQLException ex) {
            Logger.getLogger(SignUpPage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Validating user details
    public boolean validateUser() {
        String username = txt_username.getText();
        String password = txt_password.getText();
        String email = txt_email.getText();
        String contact = txt_mobile.getText();

        if (checkDublicateUser()) {

            return false;
        }

        if (username.equals("")) {
            JOptionPane.showMessageDialog(this, "please Enter your username");
            return false;
        }

        if (password.equals("")) {
            JOptionPane.showMessageDialog(this, "please Enter your password");
            return false;
        }

        if (email.equals("") || !email.matches("^.+@.+\\..+$")) {
            JOptionPane.showMessageDialog(this, "please Enter valid email address");
            return false;
        }

        if (contact.equals("") || contact.length() < 10) {
            JOptionPane.showMessageDialog(this, "please Enter valid contact details");
            return false;
        }
        return true;
    }

    // Checking duplicate user details
    public boolean checkDublicateUser() {
        boolean isExits = false;
        try {
            String name = txt_username.getText();

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library_management_system", "root", "");
            pst = con.prepareStatement("select * from users where username=?");
            pst.setString(1, name);
            rs = pst.executeQuery();
            if (rs.next()) {
                isExits = true;

            } else {
                isExits = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isExits;

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
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jCTextField3 = new app.bolivia.swing.JCTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jCTextField2 = new app.bolivia.swing.JCTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_username = new app.bolivia.swing.JCTextField();
        jLabel1 = new javax.swing.JLabel();
        txt_password = new app.bolivia.swing.JCTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txt_email = new app.bolivia.swing.JCTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txt_mobile = new app.bolivia.swing.JCTextField();
        jLabel26 = new javax.swing.JLabel();
        loginbutton = new rojerusan.RSMaterialButtonRectangle();
        signupbutton = new rojerusan.RSMaterialButtonRectangle();
        jLabel12 = new javax.swing.JLabel();
        txtutype = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/signup-library-icon.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 750, 570));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/signup-library-icon.png"))); // NOI18N
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 750, 570));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 51, 51));
        jLabel16.setText("WELCOME TO");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, -1, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/signup-library-icon.png"))); // NOI18N
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 750, 570));

        jLabel18.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 51, 51));
        jLabel18.setText("EAGLE");
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel27.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel27.setText("Naveenkumar J");
        jLabel27.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 790, -1, -1));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 255));
        jLabel9.setText("Library Management System");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 590, 50));

        jLabel28.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 102, 204));
        jLabel28.setText("PROGRAMMER");
        jLabel28.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel28MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        jLabel29.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel29.setText("Developed by:");
        jPanel4.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 790, -1, -1));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 830));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Account_50px.png"))); // NOI18N
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("USERNAME");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jCTextField3.setBackground(new java.awt.Color(102, 102, 255));
        jCTextField3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        jCTextField3.setCaretColor(new java.awt.Color(204, 204, 204));
        jCTextField3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCTextField3.setPhColor(new java.awt.Color(204, 204, 204));
        jCTextField3.setPlaceholder("Enter username");
        jPanel3.add(jCTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 51, 51));
        jLabel4.setText("WELCOME TO");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 830));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Account_50px.png"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("USERNAME");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jCTextField2.setBackground(new java.awt.Color(102, 102, 255));
        jCTextField2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        jCTextField2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCTextField2.setPlaceholder("Enter username");
        jPanel1.add(jCTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 830));

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("USERNAME");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("X");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, -1, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("create new account here");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, -1, -1));

        txt_username.setBackground(new java.awt.Color(51, 153, 255));
        txt_username.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_username.setForeground(new java.awt.Color(51, 51, 51));
        txt_username.setCaretColor(new java.awt.Color(204, 204, 204));
        txt_username.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_username.setPhColor(new java.awt.Color(51, 51, 51));
        txt_username.setPlaceholder("Enter username");
        txt_username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_usernameFocusLost(evt);
            }
        });
        jPanel2.add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 300, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Account_50px.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 43, -1));

        txt_password.setBackground(new java.awt.Color(51, 153, 255));
        txt_password.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_password.setForeground(new java.awt.Color(51, 51, 51));
        txt_password.setCaretColor(new java.awt.Color(204, 204, 204));
        txt_password.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_password.setPhColor(new java.awt.Color(51, 51, 51));
        txt_password.setPlaceholder("Enter password");
        txt_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_passwordActionPerformed(evt);
            }
        });
        jPanel2.add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 300, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("PASSWORD");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, -1, -1));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Secure_50px.png"))); // NOI18N
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 55, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("EMAIL");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, -1, -1));

        txt_email.setBackground(new java.awt.Color(51, 153, 255));
        txt_email.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_email.setForeground(new java.awt.Color(51, 51, 51));
        txt_email.setCaretColor(new java.awt.Color(204, 204, 204));
        txt_email.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_email.setPhColor(new java.awt.Color(51, 51, 51));
        txt_email.setPlaceholder("Enter email");
        jPanel2.add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, 300, -1));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Secured_Letter_50px.png"))); // NOI18N
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 43, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("MOBILE NUMBER");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 460, -1, -1));

        txt_mobile.setBackground(new java.awt.Color(51, 153, 255));
        txt_mobile.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_mobile.setForeground(new java.awt.Color(51, 51, 51));
        txt_mobile.setCaretColor(new java.awt.Color(204, 204, 204));
        txt_mobile.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_mobile.setPhColor(new java.awt.Color(51, 51, 51));
        txt_mobile.setPlaceholder("Enter mobile number");
        jPanel2.add(txt_mobile, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 480, 300, -1));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Google_Mobile_50px.png"))); // NOI18N
        jPanel2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 460, 43, -1));

        loginbutton.setBackground(new java.awt.Color(0, 0, 255));
        loginbutton.setText("Back to Home");
        loginbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginbuttonActionPerformed(evt);
            }
        });
        jPanel2.add(loginbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 749, 156, 58));

        signupbutton.setBackground(new java.awt.Color(255, 51, 51));
        signupbutton.setText("Add User");
        signupbutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signupbuttonMouseClicked(evt);
            }
        });
        signupbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupbuttonActionPerformed(evt);
            }
        });
        jPanel2.add(signupbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 674, 156, 57));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("usertype");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, -1, -1));

        txtutype.setBackground(new java.awt.Color(51, 153, 255));
        txtutype.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtutype.setForeground(new java.awt.Color(51, 51, 51));
        txtutype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Admin", "Librarian", "Student", "Guest" }));
        txtutype.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(txtutype, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 570, 300, 40));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("USERTYPE");
        jPanel2.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 540, -1, -1));

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_Forgot_Password_50px_4.png"))); // NOI18N
        jPanel2.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 560, 55, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("WELCOME , ");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 0, 540, 830));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_passwordActionPerformed

    private void loginbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbuttonActionPerformed
        // TODO add your handling code here:
        HomePage hp = new HomePage(id, uname, usertype);
        hp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_loginbuttonActionPerformed

    private void signupbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupbuttonActionPerformed
        if (validateUser()) {
            insertUserDetails();

        }

    }//GEN-LAST:event_signupbuttonActionPerformed

    private void signupbuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signupbuttonMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_signupbuttonMouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void txt_usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_usernameFocusLost
        // TODO add your handling code here:
        if (checkDublicateUser() == true) {
            JOptionPane.showMessageDialog(this, "username is already exist");

        }
    }//GEN-LAST:event_txt_usernameFocusLost

    // Developer details
    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            Desktop.getDesktop().browse(new URI("https://github.com/naveenkumar-j"));
        } catch (IOException ex) {
            Logger.getLogger(SignUpPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(SignUpPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel27MouseClicked

    // Instagram details
    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            Desktop.getDesktop().browse(new URI("https://www.instagram.com/eagle_programming/?hl=en"));
        } catch (IOException ex) {
            Logger.getLogger(SignUpPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(SignUpPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel28MouseClicked

    // Instagram details
    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            Desktop.getDesktop().browse(new URI("https://www.instagram.com/eagle_programming/?hl=en"));
        } catch (IOException ex) {
            Logger.getLogger(SignUpPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(SignUpPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel18MouseClicked

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
            java.util.logging.Logger.getLogger(SignUpPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignUpPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignUpPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignUpPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignUpPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private app.bolivia.swing.JCTextField jCTextField2;
    private app.bolivia.swing.JCTextField jCTextField3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private rojerusan.RSMaterialButtonRectangle loginbutton;
    private rojerusan.RSMaterialButtonRectangle signupbutton;
    private app.bolivia.swing.JCTextField txt_email;
    private app.bolivia.swing.JCTextField txt_mobile;
    private app.bolivia.swing.JCTextField txt_password;
    private app.bolivia.swing.JCTextField txt_username;
    private javax.swing.JComboBox<String> txtutype;
    // End of variables declaration//GEN-END:variables
}
