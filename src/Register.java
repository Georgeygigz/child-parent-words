
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import net.proteanit.sql.DbUtils;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author GEORGE
 */
public class Register extends javax.swing.JFrame {

    private Connection connection = null;
    private PreparedStatement pst = null;// used to select records from table
    private ResultSet resultset = null;// used to display the records from a table
    private ImageIcon format = null;
    String filename = null;
    int s = 0;
    byte[] person_image = null;

    /**
     * Creates new form
     */
    public Register() {// The term public is an access modifier, and it means that other class files can reference this method
        initComponents();
        connection = Learnconnect.learnconnect();
        init();// The init method is a method used to start an applet; it's from the JApplet class.
        updatetableregister();
        this.setDefaultCloseOperation(0);
        this.setResizable(false);
        currentDate();
        updatetableReport();
        fillCombo();
        updateParentWordTable();
        fillComboParentWord();
        updateChildWordTable();
    }

    public void init() //init() is a method name in Java. 
    { //  is used to begin the group of Java statements that will be executed within the init() method.

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    private void fillCombo() {
        try {
            String sql = "select distinct word from progress ";
            pst = connection.prepareStatement(sql);
            resultset = pst.executeQuery();
            while (resultset.next()) {
                String word = resultset.getString("WORD");
                cboWord.addItem(word);
                cboWord1.addItem(word);
            }

        } catch (Exception e) {
        }

    }

    private void fillComboParentWord() {
        try {
            String sql = "select distinct parent_word from parent_words ";
            pst = connection.prepareStatement(sql);
            resultset = pst.executeQuery();
            while (resultset.next()) {
                String word = resultset.getString("parent_word");
                cmb_parent_word.addItem(word);
            }

        } catch (Exception e) {
        }

    }

    private void currentDate() {

        Thread clock = new Thread() {
            public void run() {
                for (;;) {
                    Calendar cal = new GregorianCalendar();
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    int month = cal.get(Calendar.MONTH);
                    int year = cal.get(Calendar.YEAR);
                    menuYear.setText(Integer.toString(year));
                    //  menudate.setForeground(Color.MAGENTA);

                    int second = cal.get(Calendar.SECOND);
                    int minute = cal.get(Calendar.MINUTE);
                    int hour = cal.get(Calendar.HOUR);
                    //menutime.setText("THE TIME IS:    " + hour + ":" + minute + ":" + second);
                    // menutime.setForeground(Color.magenta);

                    try {
                        sleep(1000);
                    } catch (Exception e) {
                    }

                }

            }

        };
        clock.start();

    }

    private void clear() {
        cboClass.setSelectedItem(null);
        txtadmno.setText(null);
        txtfname.setText(null);
        txtoname.setText(null);

    }

    private void close() {// The term void means that the method will not return a value.
        WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);

    }

    private void updatetableregister() {
        String sql = "select * from Student";
        try {

            pst = connection.prepareStatement(sql);
            resultset = pst.executeQuery();
            tableregister.setModel(DbUtils.resultSetToTableModel(resultset));
            pst.close();
            resultset.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }

    }

    private void updateParentWordTable() {
        String sql = "select parent_word from parent_words";
        try {

            pst = connection.prepareStatement(sql);
            resultset = pst.executeQuery();
            parent_word_table.setModel(DbUtils.resultSetToTableModel(resultset));
            pst.close();
            resultset.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }

    }

    private void updateChildWordTable() {
        String sql = "select child_word from child_words";
        try {

            pst = connection.prepareStatement(sql);
            resultset = pst.executeQuery();
            child_word_table.setModel(DbUtils.resultSetToTableModel(resultset));
            pst.close();
            resultset.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }

    }

    private void updatetableReport() {
        String sql = "select * from report";
        try {

            pst = connection.prepareStatement(sql);
            resultset = pst.executeQuery();
            tableall.setModel(DbUtils.resultSetToTableModel(resultset));
            pst.close();
            resultset.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }

    }

    private void getValue() {
        try {
            txtadmno.setText(resultset.getString("admno"));
            txtfname.setText(resultset.getString("firstname"));
            txtoname.setText(resultset.getString("othernames"));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }

    private void addRegister() {
        if (txtfname.getText().equals("") || txtoname.getText().equals("") || txtadmno.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "All fields are required for you to continue");
        } else {
            String sql = "insert into Student ( admno,firstname, othernames, class)values(?,?,?,?)";

            try {
                pst = connection.prepareStatement(sql);
                pst.setString(1, txtadmno.getText());
                pst.setString(2, txtfname.getText());
                pst.setString(3, txtoname.getText());
                pst.setString(4, (String) cboClass.getSelectedItem());

                pst.execute();
                JOptionPane.showMessageDialog(rootPane, "Data Saved Succesfully");
                pst.close();
                resultset.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "ID Number Exist");
            }
            updatetableregister();
            //updatetableland2();

            clear();
        }
    }

    private void addParentWord() {
        if (txt_parent_word.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "All fields are required for you to continue");
        } else {
            String sql = "insert into parent_words ( parent_word)values(?)";

            try {
                pst = connection.prepareStatement(sql);
                pst.setString(1, txt_parent_word.getText().toUpperCase());

                pst.execute();
                JOptionPane.showMessageDialog(rootPane, "Data Saved Succesfully");
                pst.close();
                resultset.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }
            updatetableregister();
            //updatetableland2();

            clear();
        }
    }

    private void addChildWord() {
        if (txt_child_word.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "All fields are required for you to continue");
        } else {
            String sql = "insert into child_words ( child_word,parent_word)values(?,?)";

            try {
                pst = connection.prepareStatement(sql);
                pst.setString(1, txt_child_word.getText().toUpperCase());
                pst.setString(2, (String) cmb_parent_word.getSelectedItem());

                pst.execute();
                JOptionPane.showMessageDialog(rootPane, "Data Saved Succesfully");
                pst.close();
                resultset.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }
            updatetableregister();
            //updatetableland2();

            clear();
        }
    }

    private void updateaddParentWord() {
        if (txt_parent_word.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "All fields are required for you to continue");
        } else {
            try {
                String parent_word = txt_parent_word.getText().toUpperCase();

                String sql = "update parent_words set parent_word=? where parent_word='" + parent_word + "' ";

                pst = connection.prepareStatement(sql);
                pst.setString(1, txt_parent_word.getText().toUpperCase());

                pst.execute();
                JOptionPane.showMessageDialog(rootPane, "Data Saved successfully");
                pst.close();
                resultset.close();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }

            updatetableregister();
            //updatetableland2();

            // updateStatement();
            //updateFinance();
            clear();
        }
    }

    private void updateChildWord() {
        if (txt_child_word.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "All fields are required for you to continue");
        } else {
            try {
                String child_word = txt_child_word.getText().toUpperCase();
                String parent_word = cmb_parent_word.getSelectedItem().toString();
                String sql = "update child_words set child_word=?, parent_word=? where child_word='" + child_word + "' and parent_word='" + parent_word + "'";

                pst = connection.prepareStatement(sql);
                pst.setString(1, txt_child_word.getText().toUpperCase());
                pst.setString(2, (String) cmb_parent_word.getSelectedItem());

                pst.execute();
                JOptionPane.showMessageDialog(rootPane, "Data Saved successfully");
                pst.close();
                resultset.close();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }

            updatetableregister();
            //updatetableland2();

            // updateStatement();
            //updateFinance();
            clear();
        }
    }

    private void updateRegister() {
        if (txtfname.getText().equals("") || txtoname.getText().equals("") || txtadmno.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "All fields are required for you to continue");
        } else {
            try {

                String sql = "update Student set firstname=?,othernames=?,class=? where admno=?";

                pst = connection.prepareStatement(sql);
                pst.setString(1, txtfname.getText());
                pst.setString(2, txtoname.getText());
                pst.setString(3, (String) cboClass.getSelectedItem());
                pst.setString(4, txtadmno.getText());

                pst.execute();
                JOptionPane.showMessageDialog(rootPane, "Data Saved successfully");
                pst.close();
                resultset.close();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }

            updatetableregister();
            //updatetableland2();

            // updateStatement();
            //updateFinance();
            clear();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableregister = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnadd = new javax.swing.JButton();
        txtsearch = new javax.swing.JTextField();
        btndelete = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtadmno = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtoname = new javax.swing.JTextField();
        txtfname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cboClass = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        dob = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableall = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        regNo = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tableall1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        regNo1 = new javax.swing.JTextField();
        cboWord = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cboWord1 = new javax.swing.JComboBox();
        jButton5 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        parent_word_table = new javax.swing.JTable();
        jPanel20 = new javax.swing.JPanel();
        add_parent_word = new javax.swing.JButton();
        delete_parent_word = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        jPanel21 = new javax.swing.JPanel();
        txt_parent_word = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        add_parent_word1 = new javax.swing.JButton();
        delete_parent_word1 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        txt_child_word = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cmb_parent_word = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        child_word_table = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        menuYear = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1300, 430));

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTabbedPane1.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(153, 0, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1253, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));

        tableregister.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        tableregister.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableregister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableregisterMouseClicked(evt);
            }
        });
        tableregister.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableregisterKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tableregister);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1079, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1059, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4914, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 4892, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jScrollPane2.setViewportView(jPanel5);

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnadd.setBackground(new java.awt.Color(102, 255, 255));
        btnadd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/save.png"))); // NOI18N
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        txtsearch.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        txtsearch.setText("Search..");
        txtsearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtsearchMouseClicked(evt);
            }
        });
        txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchKeyReleased(evt);
            }
        });

        btndelete.setBackground(new java.awt.Color(102, 255, 255));
        btndelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Delete.png"))); // NOI18N
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtsearch)
                    .addComponent(btndelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addComponent(btnadd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnadd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btndelete)
                .addContainerGap(232, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 0, 204));
        jLabel7.setText("Class");

        txtadmno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtadmnoKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 204));
        jLabel2.setText("Admission Number");

        jLabel3.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 204));
        jLabel3.setText("First Name");

        txtoname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtonameKeyTyped(evt);
            }
        });

        txtfname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfnameKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 0, 204));
        jLabel4.setText("Other Names");

        cboClass.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1", "2", "3", "4", "5", "6", "7", "8" }));

        jLabel11.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 0, 204));
        jLabel11.setText("Date Of Birth");

        dob.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dobKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtoname)
                    .addComponent(txtfname)
                    .addComponent(txtadmno)
                    .addComponent(cboClass, javax.swing.GroupLayout.Alignment.TRAILING, 0, 173, Short.MAX_VALUE)
                    .addComponent(dob, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtadmno, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtfname, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtoname, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cboClass, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(dob, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(jPanel3);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1296, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(19, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 561, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(83, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("ADD NEW STUDENT", jPanel6);

        jTabbedPane2.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N

        jPanel13.setBackground(new java.awt.Color(204, 204, 255));

        tableall.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        tableall.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableall.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableallMouseClicked(evt);
            }
        });
        tableall.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableallKeyReleased(evt);
            }
        });
        jScrollPane6.setViewportView(tableall);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1272, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4914, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 4892, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jScrollPane5.setViewportView(jPanel13);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Enter Reg Number");

        jButton2.setBackground(new java.awt.Color(102, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/print.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 204, 204));
        jButton1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jButton1.setText("RESET REPORT RECORDS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(27, 27, 27)
                        .addComponent(regNo, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)))
                .addGap(0, 26, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(regNo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("VIEW ALL STUDENTS", jPanel10);

        jPanel14.setBackground(new java.awt.Color(204, 204, 255));

        tableall1.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        tableall1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableall1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableall1MouseClicked(evt);
            }
        });
        tableall1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableall1KeyReleased(evt);
            }
        });
        jScrollPane8.setViewportView(tableall1);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1272, Short.MAX_VALUE)
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 1252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4914, Short.MAX_VALUE)
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 4892, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jScrollPane7.setViewportView(jPanel14);

        jLabel5.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel5.setText("ENTER REG NO");

        jButton4.setBackground(new java.awt.Color(102, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/print.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        cboWord.setFont(new java.awt.Font("Courier New", 0, 16)); // NOI18N
        cboWord.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cboWord.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboWordItemStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel8.setText("SELECT WORD");

        jLabel6.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel6.setText("     SINGLE STUDENT");

        jLabel9.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel9.setText("     ALL STUDENTS");

        jLabel10.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel10.setText("SELECT WORD");

        cboWord1.setFont(new java.awt.Font("Courier New", 0, 16)); // NOI18N
        cboWord1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cboWord1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboWord1ItemStateChanged(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(102, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/print.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cboWord1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel12Layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cboWord, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                                .addComponent(regNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(6, 6, 6))))))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGap(60, 60, 60)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 23, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(regNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboWord, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(38, 38, 38)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboWord1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 60, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("PROGRESS", jPanel11);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("VIEW STUDENT", jPanel8);

        jTabbedPane3.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N

        jPanel18.setBackground(new java.awt.Color(153, 0, 51));
        jPanel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1253, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        jPanel19.setBackground(new java.awt.Color(204, 204, 255));

        parent_word_table.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        parent_word_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        parent_word_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                parent_word_tableMouseClicked(evt);
            }
        });
        parent_word_table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                parent_word_tableKeyReleased(evt);
            }
        });
        jScrollPane10.setViewportView(parent_word_table);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1079, Short.MAX_VALUE)
            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel19Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 1059, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4914, Short.MAX_VALUE)
            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel19Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 4892, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jScrollPane9.setViewportView(jPanel19);

        jPanel20.setBackground(new java.awt.Color(204, 204, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        add_parent_word.setBackground(new java.awt.Color(102, 255, 255));
        add_parent_word.setIcon(new javax.swing.ImageIcon(getClass().getResource("/save.png"))); // NOI18N
        add_parent_word.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_parent_wordActionPerformed(evt);
            }
        });

        delete_parent_word.setBackground(new java.awt.Color(102, 255, 255));
        delete_parent_word.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Delete.png"))); // NOI18N
        delete_parent_word.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_parent_wordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(add_parent_word, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete_parent_word, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(add_parent_word)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(delete_parent_word)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel21.setBackground(new java.awt.Color(204, 204, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_parent_word.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_parent_wordKeyTyped(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 0, 204));
        jLabel13.setText("Parent Word");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(txt_parent_word, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txt_parent_word, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(285, Short.MAX_VALUE))
        );

        jScrollPane11.setViewportView(jPanel21);

        jLabel14.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 0, 204));
        jLabel14.setText("Parent Word");

        jPanel30.setBackground(new java.awt.Color(204, 204, 255));
        jPanel30.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        add_parent_word1.setBackground(new java.awt.Color(102, 255, 255));
        add_parent_word1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/save.png"))); // NOI18N
        add_parent_word1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_parent_word1ActionPerformed(evt);
            }
        });

        delete_parent_word1.setBackground(new java.awt.Color(102, 255, 255));
        delete_parent_word1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Delete.png"))); // NOI18N
        delete_parent_word1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_parent_word1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(add_parent_word1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete_parent_word1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(add_parent_word1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(delete_parent_word1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel15.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 0, 204));
        jLabel15.setText("Child Words");

        jPanel31.setBackground(new java.awt.Color(204, 204, 255));
        jPanel31.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_child_word.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_child_wordKeyTyped(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(204, 0, 204));
        jLabel16.setText("Child Words");

        cmb_parent_word.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jLabel23.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(204, 0, 204));
        jLabel23.setText("Parent Word");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel23))
                .addGap(18, 18, 18)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmb_parent_word, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_child_word, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txt_child_word, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_parent_word, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addGap(23, 23, 23))
        );

        child_word_table.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        child_word_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        child_word_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                child_word_tableMouseClicked(evt);
            }
        });
        child_word_table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                child_word_tableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(child_word_table);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addGap(13, 13, 13)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(68, 68, 68))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1296, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel16Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(19, Short.MAX_VALUE)))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel16Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(52, Short.MAX_VALUE)))
        );

        jTabbedPane3.addTab("Add Words", jPanel16);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jTabbedPane1.addTab("Add Word", jPanel15);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jButton3.setBackground(new java.awt.Color(102, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Back.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Close");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(menuYear, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1289, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(menuYear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        // TODO add your handling code here:
        try {
            String sql = " select * from Student where admno=?";

            pst = connection.prepareStatement(sql);
            pst.setString(1, txtadmno.getText());
            resultset = pst.executeQuery();
            if (resultset.next()) {
                updateRegister();
            } else {
                addRegister();
            }
            pst.close();
            resultset.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }

    }//GEN-LAST:event_btnaddActionPerformed

    private void deleteStatement() {

        String sql = "delete from statement where admno=?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, txtadmno.getText());
            pst.execute();
            pst.close();
            resultset.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }

        //updatetableland2();
    }

    private void deletStudent() {

        if (txtadmno.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Nothing is selected");
        } else {
            int p = JOptionPane.showConfirmDialog(rootPane, "Do you want to delete the selected Student from the system completly?", "Delete", JOptionPane.YES_NO_OPTION);

            if (p == 0) {
                String sql = "delete from Student where admno=?";
                try {
                    pst = connection.prepareStatement(sql);
                    pst.setString(1, txtadmno.getText());
                    pst.execute();
                    JOptionPane.showMessageDialog(rootPane, "Data deleted successfully");
                    pst.close();
                    resultset.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane, e);
                }
            }
            updatetableregister();
            //deleteStatement();
            //deleteFinance();
        }
    }

    private void deletStudent1() {

        String sql = "delete from student where admno=?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, txtadmno.getText());
            pst.execute();

            pst.close();
            resultset.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }

        updatetableregister();
        deleteStatement();

    }
    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        // TODO add your handling code here:
        deletStudent();
        clear();
    }//GEN-LAST:event_btndeleteActionPerformed

    private void txtsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyReleased
        // TODO add your handling code here:
        try {
            String sql = " select * from Student where firstname=?";
            pst = connection.prepareStatement(sql);
            pst.setString(1, txtsearch.getText());
            resultset = pst.executeQuery();
            if (resultset.next()) {
                getValue();
            }
            pst.close();
            resultset.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
        try {
            String sql = " select * from Student where admno=?";

            pst = connection.prepareStatement(sql);
            pst.setString(1, txtsearch.getText());
            resultset = pst.executeQuery();
            if (resultset.next()) {
                getValue();
            }
            pst.close();
            resultset.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_txtsearchKeyReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        try {
            close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        } finally {
            try {
                resultset.close();
                pst.close();
                connection.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void txtsearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtsearchMouseClicked
        // TODO add your handling code here:
        txtsearch.setText(null);
        clear();
    }//GEN-LAST:event_txtsearchMouseClicked

    private void tableregisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableregisterMouseClicked
        // TODO add your handling code here:
        int row = tableregister.getSelectedRow();
        String tableClick = (tableregister.getModel().getValueAt(row, 0).toString());

        try {

            String sql = "select * from Student where admno='" + tableClick + "'";
            pst = connection.prepareStatement(sql);
            resultset = pst.executeQuery();
            if (resultset.next());

            getValue();
            pst.close();
            resultset.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_tableregisterMouseClicked

    private void tableregisterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableregisterKeyReleased
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            int row = tableregister.getSelectedRow();
            String tableClick = (tableregister.getModel().getValueAt(row, 0).toString());
            try {

                String sql = "select * from Student where admno='" + tableClick + "' ";
                pst = connection.prepareStatement(sql);
                resultset = pst.executeQuery();
                if (resultset.next());
                getValue();
                pst.close();
                resultset.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }

        }
    }//GEN-LAST:event_tableregisterKeyReleased

    private void tableallMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableallMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableallMouseClicked

    private void tableallKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableallKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tableallKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            // String report="C:\\Users\\Georgey\\Documents\\NetBeansProjects\\Kwamwagovillagelandmanagementsystem\\Kwamwagoreport.jrxml";
            JasperDesign jd = JRXmlLoader.load("report.jrxml");
            String sql = " SELECT * FROM report";
            JRDesignQuery jrq = new JRDesignQuery();
            jrq.setText(sql);
            jd.setQuery(jrq);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, connection);
            // JasperViewer.viewReport(jp);
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            connection.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
        this.dispose();
        Loginframe obj = new Loginframe();
        obj.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtadmnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtadmnoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c)) || (c == evt.VK_BACK_SPACE || c == evt.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtadmnoKeyTyped

    private void txtonameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtonameKeyTyped
        // TODO add your handling code here:
        /*   char c = evt.getKeyChar();
         if (!(Character.isLetter(c)) || (c == evt.VK_BACK_SPACE || c == evt.VK_DELETE)) {
         evt.consume();
         }*/
    }//GEN-LAST:event_txtonameKeyTyped

    private void txtfnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfnameKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!(Character.isLetter(c)) || (c == evt.VK_BACK_SPACE || c == evt.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtfnameKeyTyped

    private void dobKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dobKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_dobKeyTyped
    private void deleteReport() {
        try {
            String sql = "DELETE FROM report";
            pst = connection.prepareStatement(sql);
            pst.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        updatetableReport();
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            Statement st = connection.createStatement();
            int rows = st.executeUpdate("INSERT INTO progress SELECT * FROM report");
            if (rows == 0) {
                JOptionPane.showMessageDialog(rootPane, "Haven't added any row!");
            } else {
                JOptionPane.showMessageDialog(rootPane, (rows + " Studens were moved to Progress Succesfully"));
                deleteReport();

                //connection.close();
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tableall1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableall1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableall1MouseClicked

    private void tableall1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableall1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tableall1KeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (cboWord.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Please Select The word");
        } else {
            String reg = regNo1.getText();
            String word = cboWord.getSelectedItem().toString();
            try {
                // String report="C:\\Users\\Georgey\\Documents\\NetBeansProjects\\Kwamwagovillagelandmanagementsystem\\Kwamwagoreport.jrxml";
                JasperDesign jd = JRXmlLoader.load("progress.jrxml");
                String sql = " SELECT * FROM progress where reg_no='" + reg + "' and word='" + word + "' ";
                JRDesignQuery jrq = new JRDesignQuery();
                jrq.setText(sql);
                jd.setQuery(jrq);
                JasperReport jr = JasperCompileManager.compileReport(jd);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, connection);
                // JasperViewer.viewReport(jp);
                JasperViewer.viewReport(jp, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cboWordItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboWordItemStateChanged
        // TODO add your handling code here:
        String word = cboWord.getSelectedItem().toString();
        try {
            String sql = "select * from progress where reg_no=? and word='" + word + "'";
            pst = connection.prepareStatement(sql);
            pst.setString(1, regNo1.getText());
            resultset = pst.executeQuery();
            tableall1.setModel(DbUtils.resultSetToTableModel(resultset));
            pst.close();
            resultset.close();

        } catch (Exception e) {
        }
    }//GEN-LAST:event_cboWordItemStateChanged

    private void cboWord1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboWord1ItemStateChanged
        // TODO add your handling code here:
        String word = cboWord1.getSelectedItem().toString();
        try {
            String sql = "select * from progress where word='" + word + "'";
            pst = connection.prepareStatement(sql);
            resultset = pst.executeQuery();
            tableall1.setModel(DbUtils.resultSetToTableModel(resultset));
            pst.close();
            resultset.close();

        } catch (Exception e) {
        }
    }//GEN-LAST:event_cboWord1ItemStateChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

        if (cboWord.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Please Select The word");
        } else {

            String word = cboWord1.getSelectedItem().toString();
            try {
                // String report="C:\\Users\\Georgey\\Documents\\NetBeansProjects\\Kwamwagovillagelandmanagementsystem\\Kwamwagoreport.jrxml";
                JasperDesign jd = JRXmlLoader.load("progress.jrxml");
                String sql = " SELECT * FROM progress where word='" + word + "' ";
                JRDesignQuery jrq = new JRDesignQuery();
                jrq.setText(sql);
                jd.setQuery(jrq);
                JasperReport jr = JasperCompileManager.compileReport(jd);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, connection);
                // JasperViewer.viewReport(jp);
                JasperViewer.viewReport(jp, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void parent_word_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_parent_word_tableMouseClicked
        // TODO add your handling code here:

        int row = parent_word_table.getSelectedRow();
        String tableClick = (parent_word_table.getModel().getValueAt(row, 0).toString());

        try {

            String sql = "select * from parent_words where parent_word='" + tableClick + "'";
            pst = connection.prepareStatement(sql);
            resultset = pst.executeQuery();
            if (resultset.next());

            txt_parent_word.setText(resultset.getString("parent_word"));
            pst.close();
            resultset.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_parent_word_tableMouseClicked

    private void parent_word_tableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_parent_word_tableKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            int row = parent_word_table.getSelectedRow();
            String tableClick = (parent_word_table.getModel().getValueAt(row, 0).toString());
            try {

                String sql = "select * from parent_words where parent_word='" + tableClick + "' ";
                pst = connection.prepareStatement(sql);
                resultset = pst.executeQuery();
                if (resultset.next());
                txt_parent_word.setText(resultset.getString("parent_word"));
                pst.close();
                resultset.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }

        }
    }//GEN-LAST:event_parent_word_tableKeyReleased

    private void add_parent_wordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_parent_wordActionPerformed
        // TODO add your handling code here:
        try {
            String sql = " select * from parent_words where parent_word=?";

            pst = connection.prepareStatement(sql);
            pst.setString(1, txt_parent_word.getText().toUpperCase());
            resultset = pst.executeQuery();
            if (resultset.next()) {

                updateaddParentWord();
            } else {
                addParentWord();
            }
            pst.close();
            resultset.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
        updateParentWordTable();
        fillComboParentWord();

    }//GEN-LAST:event_add_parent_wordActionPerformed

    private void delete_parent_wordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_parent_wordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_delete_parent_wordActionPerformed

    private void txt_parent_wordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_parent_wordKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_parent_wordKeyTyped

    private void add_parent_word1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_parent_word1ActionPerformed
        // TODO add your handling code here:
        try {
            String sql = " select * from child_words where child_word=? and parent_word=?";

            pst = connection.prepareStatement(sql);
            pst.setString(1, txt_child_word.getText().toUpperCase());
            pst.setString(2, (String) cmb_parent_word.getSelectedItem());

            resultset = pst.executeQuery();
            if (resultset.next()) {

                updateChildWord();
            } else {
                addChildWord();
            }
            pst.close();
            resultset.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
        updateParentWordTable();
        fillComboParentWord();
        updateChildWordTable();
    }//GEN-LAST:event_add_parent_word1ActionPerformed

    private void delete_parent_word1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_parent_word1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_delete_parent_word1ActionPerformed

    private void txt_child_wordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_child_wordKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_child_wordKeyTyped

    private void child_word_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_child_word_tableMouseClicked
        // TODO add your handling code here:
         int row = child_word_table.getSelectedRow();
        String tableClick = (child_word_table.getModel().getValueAt(row, 0).toString());

        try {

            String sql = "select * from child_words where child_word='" + tableClick + "'";
            pst = connection.prepareStatement(sql);
            resultset = pst.executeQuery();
            if (resultset.next());

            txt_child_word.setText(resultset.getString("child_word"));
            pst.close();
            resultset.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
        
    }//GEN-LAST:event_child_word_tableMouseClicked

    private void child_word_tableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_child_word_tableKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            int row = child_word_table.getSelectedRow();
            String tableClick = (child_word_table.getModel().getValueAt(row, 0).toString());
            try {

                String sql = "select * from child_words where child_word='" + tableClick + "' ";
                pst = connection.prepareStatement(sql);
                resultset = pst.executeQuery();
                if (resultset.next());
                txt_child_word.setText(resultset.getString("child_word"));
                pst.close();
                resultset.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }

        }
    }//GEN-LAST:event_child_word_tableKeyReleased

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
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_parent_word;
    private javax.swing.JButton add_parent_word1;
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btndelete;
    private javax.swing.JComboBox cboClass;
    private javax.swing.JComboBox cboWord;
    private javax.swing.JComboBox cboWord1;
    private javax.swing.JTable child_word_table;
    private javax.swing.JComboBox<String> cmb_parent_word;
    private javax.swing.JButton delete_parent_word;
    private javax.swing.JButton delete_parent_word1;
    private javax.swing.JTextField dob;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel menuYear;
    private javax.swing.JTable parent_word_table;
    private javax.swing.JTextField regNo;
    private javax.swing.JTextField regNo1;
    private javax.swing.JTable tableall;
    private javax.swing.JTable tableall1;
    private javax.swing.JTable tableregister;
    private javax.swing.JTextField txt_child_word;
    private javax.swing.JTextField txt_parent_word;
    private javax.swing.JTextField txtadmno;
    private javax.swing.JTextField txtfname;
    private javax.swing.JTextField txtoname;
    private javax.swing.JTextField txtsearch;
    // End of variables declaration//GEN-END:variables
}
