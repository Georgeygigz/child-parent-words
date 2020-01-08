
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Gigz
 */
public class information extends javax.swing.JFrame {

    private Connection connection;
    private PreparedStatement pst;
    ResultSet resultSet;
    private ImageIcon format = null;
    String filename = null;
    int s = 0;
    byte[] person_image = null;
    Timer timer;

    /**
     * Creates new form information
     */
    public information() {
        initComponents();
        connection = Learnconnect.learnconnect();
        tmer();
        enbl1();
        jLabel4.setVisible(false);
        jLabel2.setVisible(false);
        jList2.setVisible(false);
        jButton2.setEnabled(false);
        init();
        txtcount.setVisible(false);
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        jButton5.setEnabled(false);
         jButton6.setEnabled(false);

    }

    private void init() {
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void close() {
        WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }
    /*
     private void voice() {
     InputStream in;
     try {
     in = new FileInputStream(new File("bells003.wav"));
     AudioStream audios = new AudioStream(in);
     AudioPlayer.player.start(audios);
            
     } catch (Exception e) {
     JOptionPane.showMessageDialog(null, e);
     }
        
     }*/

    private void enbl1() {
        I.setVisible(false);
        N.setVisible(false);
        F.setVisible(false);
        O.setVisible(false);
        R.setVisible(false);
        M.setVisible(false);
        A.setVisible(false);
        T.setVisible(false);
        I1.setVisible(false);
        O1.setVisible(false);
        N2.setVisible(false);
        txtfill.setVisible(false);
        fa.setVisible(false);
        sa.setVisible(false);
        ta.setVisible(false);
        firstattemp.setVisible(false);
        secondattemp.setVisible(false);
        thirdattemp.setVisible(false);
        pfa.setVisible(false);
        psa.setVisible(false);
        pta.setVisible(false);
        jLabel1.setVisible(false);

    }

    private void enbl() {
        I.setText(null);
        N.setText(null);
        F.setText(null);
        O.setText(null);
        R.setText(null);
        M.setText(null);
        A.setText(null);
        T.setText(null);
        I1.setText(null);
        O1.setText(null);
        N2.setText(null);
    }

    private void enable1() {
        i.setEnabled(true);
        n.setEnabled(true);
        f.setEnabled(true);
        o.setEnabled(true);
        r.setEnabled(true);
        a1.setEnabled(true);
        a.setEnabled(true);
        t.setEnabled(true);
        i1.setEnabled(true);
        o1.setEnabled(true);
        n1.setEnabled(true);
        i.setForeground(Color.black);
        n.setForeground(Color.black);
        f.setForeground(Color.black);
        o.setForeground(Color.black);
        r.setForeground(Color.black);
        a1.setForeground(Color.black);
        a.setForeground(Color.black);
        t.setForeground(Color.black);
        i1.setForeground(Color.black);
        o1.setForeground(Color.black);
        n1.setForeground(Color.black);
    }

    private void tmer() {
        //int name = Integer.parseInt(test.getText());
        int seconds = 900;
        timer = new java.util.Timer();
        timer.schedule(new RemindTask(), seconds * 1000);

    }

    class RemindTask extends TimerTask {

        public void run() {

            visb();
            status();
            jLabel2.setVisible(true);
            jLabel21.setVisible(false);
            jButton2.setEnabled(true);
            jButton1.setEnabled(true);
            jButton2.setEnabled(true);

            timer.cancel();
        }
    }

    private void status() {
        try {
            String sql = "select Photo from Status where Answer= 'Correct' ";
            pst = connection.prepareStatement(sql);
            resultSet = pst.executeQuery();
            if (resultSet.next()) {
                byte[] imagedata = resultSet.getBytes("Photo");
                format = new ImageIcon(imagedata);
                jLabel21.setIcon(format);
            }
            pst.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void status1() {
        try {
            String sql = "select Photo from Status where Answer= 'Wrong' ";
            pst = connection.prepareStatement(sql);
            resultSet = pst.executeQuery();
            if (resultSet.next()) {
                byte[] imagedata = resultSet.getBytes("Photo");
                format = new ImageIcon(imagedata);
                jLabel21.setIcon(format);
            }
            pst.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void visb() {
        i.setEnabled(false);
        n.setEnabled(false);
        f.setEnabled(false);
        o.setEnabled(false);
        a1.setEnabled(false);
        a.setEnabled(false);
        t.setEnabled(false);
        i1.setEnabled(false);
        o1.setEnabled(false);
        n1.setEnabled(false);
        r.setEnabled(false);
        btnsubmit.setEnabled(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        i = new javax.swing.JButton();
        n = new javax.swing.JButton();
        f = new javax.swing.JButton();
        o = new javax.swing.JButton();
        r = new javax.swing.JButton();
        a = new javax.swing.JButton();
        t = new javax.swing.JButton();
        i1 = new javax.swing.JButton();
        o1 = new javax.swing.JButton();
        n1 = new javax.swing.JButton();
        I = new javax.swing.JTextField();
        A = new javax.swing.JTextField();
        M = new javax.swing.JTextField();
        R = new javax.swing.JTextField();
        T = new javax.swing.JTextField();
        I1 = new javax.swing.JTextField();
        O1 = new javax.swing.JTextField();
        N2 = new javax.swing.JTextField();
        F = new javax.swing.JTextField();
        N = new javax.swing.JTextField();
        O = new javax.swing.JTextField();
        txtfill = new javax.swing.JTextField();
        btnsubmit = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblpercent = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtcount = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        a1 = new javax.swing.JButton();
        pta = new javax.swing.JLabel();
        secondattemp = new javax.swing.JLabel();
        pfa = new javax.swing.JLabel();
        psa = new javax.swing.JLabel();
        thirdattemp = new javax.swing.JLabel();
        fa = new javax.swing.JLabel();
        sa = new javax.swing.JLabel();
        ta = new javax.swing.JLabel();
        firstattemp = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1348, 711));

        i.setBackground(new java.awt.Color(0, 255, 255));
        i.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        i.setText("I");
        i.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iActionPerformed(evt);
            }
        });

        n.setBackground(new java.awt.Color(0, 255, 255));
        n.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        n.setText("N");
        n.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nActionPerformed(evt);
            }
        });

        f.setBackground(new java.awt.Color(0, 255, 255));
        f.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        f.setText("F");
        f.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fActionPerformed(evt);
            }
        });

        o.setBackground(new java.awt.Color(0, 255, 255));
        o.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        o.setText("O");
        o.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oActionPerformed(evt);
            }
        });

        r.setBackground(new java.awt.Color(0, 255, 255));
        r.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        r.setText("R");
        r.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rActionPerformed(evt);
            }
        });

        a.setBackground(new java.awt.Color(0, 255, 255));
        a.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        a.setText("A");
        a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aActionPerformed(evt);
            }
        });

        t.setBackground(new java.awt.Color(0, 255, 255));
        t.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        t.setText("T");
        t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tActionPerformed(evt);
            }
        });

        i1.setBackground(new java.awt.Color(0, 255, 255));
        i1.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        i1.setText("I");
        i1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                i1ActionPerformed(evt);
            }
        });

        o1.setBackground(new java.awt.Color(0, 255, 255));
        o1.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        o1.setText("O");
        o1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                o1ActionPerformed(evt);
            }
        });

        n1.setBackground(new java.awt.Color(0, 255, 255));
        n1.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        n1.setText("N");
        n1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n1ActionPerformed(evt);
            }
        });

        btnsubmit.setBackground(new java.awt.Color(0, 255, 255));
        btnsubmit.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnsubmit.setText("SUBMIT");
        btnsubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsubmitActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton2.setText("ANSWERS");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(102, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setText("TRY AGAIN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(102, 255, 255));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton3.setText("NEXT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(102, 255, 255));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton4.setText("LOGOUT");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bell.gif"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/animated-congratulation-image-0092.gif"))); // NOI18N

        lblpercent.setBackground(new java.awt.Color(51, 51, 51));
        lblpercent.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblpercent.setForeground(new java.awt.Color(153, 0, 0));

        jLabel1.setBackground(new java.awt.Color(51, 51, 51));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 0, 0));
        jLabel1.setText("%");

        jList1.setBackground(new java.awt.Color(255, 153, 255));
        jList1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jScrollPane1.setViewportView(jList1);

        jList2.setBackground(new java.awt.Color(255, 0, 255));
        jList2.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jList2.setForeground(new java.awt.Color(153, 255, 51));
        jList2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "I", "IN", "INFO", "INFORM", "FOR", "FORM", "FORMAT", "FORMATION", "OR", "MA", "MAT", "A", "AT", "ION", "ON" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList2);

        a1.setBackground(new java.awt.Color(0, 255, 255));
        a1.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        a1.setText("M");
        a1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a1ActionPerformed(evt);
            }
        });

        pta.setBackground(new java.awt.Color(51, 51, 51));
        pta.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        pta.setForeground(new java.awt.Color(153, 0, 0));
        pta.setText("%");

        secondattemp.setBackground(new java.awt.Color(51, 51, 51));
        secondattemp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        secondattemp.setForeground(new java.awt.Color(153, 0, 0));
        secondattemp.setText("Second Attemp");

        pfa.setBackground(new java.awt.Color(51, 51, 51));
        pfa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        pfa.setForeground(new java.awt.Color(153, 0, 0));
        pfa.setText("%");

        psa.setBackground(new java.awt.Color(51, 51, 51));
        psa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        psa.setForeground(new java.awt.Color(153, 0, 0));
        psa.setText("%");

        thirdattemp.setBackground(new java.awt.Color(51, 51, 51));
        thirdattemp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        thirdattemp.setForeground(new java.awt.Color(153, 0, 0));
        thirdattemp.setText("Third Attemp");

        fa.setBackground(new java.awt.Color(51, 51, 51));
        fa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        fa.setForeground(new java.awt.Color(153, 0, 0));

        sa.setBackground(new java.awt.Color(51, 51, 51));
        sa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        sa.setForeground(new java.awt.Color(153, 0, 0));

        ta.setBackground(new java.awt.Color(51, 51, 51));
        ta.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ta.setForeground(new java.awt.Color(153, 0, 0));

        firstattemp.setBackground(new java.awt.Color(51, 51, 51));
        firstattemp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        firstattemp.setForeground(new java.awt.Color(153, 0, 0));
        firstattemp.setText("First Attemp");

        jButton5.setBackground(new java.awt.Color(102, 255, 255));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton5.setText("DONE");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(102, 255, 255));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton6.setText("REPORT");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(i, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(n, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(f, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(o, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(r, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(a1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(t, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(i1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(o1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(n1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(I, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(N, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(F, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(O, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(R, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(M, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(A, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(T, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(I1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(O1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(N2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtfill, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnsubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addComponent(lblpercent, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(firstattemp)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(secondattemp, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                                                .addGap(62, 62, 62))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(thirdattemp, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(ta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(pta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(sa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(psa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(fa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pfa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(4, 4, 4)))))
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtcount, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(n, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(f, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(o, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(r, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(a1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(o1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(n1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(I, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(N, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(F, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(O, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(R, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(M, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(A, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(T, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(I1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(O1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(N2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtfill, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnsubmit)
                                                .addGap(3, 3, 3)
                                                .addComponent(jButton2)
                                                .addGap(3, 3, 3)
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(2, 2, 2)
                                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(8, 8, 8)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblpercent, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(48, 48, 48)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(psa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(sa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(3, 3, 3)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(pta, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(ta, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(pfa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(fa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(firstattemp, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(secondattemp, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(thirdattemp, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(15, 15, 15))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(txtcount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iActionPerformed
        // TODO add your handling code here:
        I.setText("I");
        i.setEnabled(false);
        i.setForeground(Color.lightGray);
        if (I.getText().endsWith("") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("I");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("IN");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("INFO");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("INFORM");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FOR");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FORM");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FORMAT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("FORMATION");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("OR");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("MA");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("MAT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("A");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("AT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("ION");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("ON");
        } else {
            txtfill.setText(null);
        }
        if (lblpercent.getText().equals("100")) {

        }
    }//GEN-LAST:event_iActionPerformed

    private void nActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nActionPerformed
        // TODO add your handling code here:
        N.setText("N");
        n.setEnabled(false);
        n.setForeground(Color.lightGray);

        if (I.getText().endsWith("") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("I");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("IN");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("INFO");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("INFORM");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FOR");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FORM");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FORMAT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("FORMATION");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("OR");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("MA");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("MAT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("A");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("AT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("ION");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("ON");
        } else {
            txtfill.setText(null);
        }
        if (lblpercent.getText().equals("100")) {

        }
    }//GEN-LAST:event_nActionPerformed

    private void fActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fActionPerformed
        // TODO add your handling code here:
        F.setText("F");
        f.setEnabled(false);
        f.setForeground(Color.lightGray);

        if (I.getText().endsWith("") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("I");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("IN");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("INFO");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("INFORM");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FOR");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FORM");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FORMAT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("FORMATION");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("OR");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("MA");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("MAT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("A");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("AT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("ION");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("ON");
        } else {
            txtfill.setText(null);
        }
        if (lblpercent.getText().equals("100")) {

        }
    }//GEN-LAST:event_fActionPerformed

    private void oActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oActionPerformed
        // TODO add your handling code here:

        O.setText("O");
        o.setEnabled(false);
        o.setForeground(Color.lightGray);
        if (I.getText().endsWith("") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("I");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("IN");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("INFO");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("INFORM");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FOR");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FORM");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FORMAT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("FORMATION");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("OR");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("MA");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("MAT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("A");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("AT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("ION");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("ON");
        } else {
            txtfill.setText(null);
        }
        if (lblpercent.getText().equals("100")) {

        }
    }//GEN-LAST:event_oActionPerformed

    private void rActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rActionPerformed
        // TODO add your handling code here:
        R.setText("R");
        // A.setVisible(false);
        r.setEnabled(false);
        r.setForeground(Color.lightGray);
        if (I.getText().endsWith("") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("I");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("IN");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("INFO");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("INFORM");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FOR");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FORM");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FORMAT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("FORMATION");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("OR");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("MA");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("MAT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("A");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("AT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("ION");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("ON");
        } else {
            txtfill.setText(null);
        }
        if (lblpercent.getText().equals("100")) {

        }
    }//GEN-LAST:event_rActionPerformed

    private void aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aActionPerformed
        // TODO add your handling code here:
        A.setText("A");
        a.setEnabled(false);
        a.setForeground(Color.lightGray);
        if (I.getText().endsWith("") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("I");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("IN");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("INFO");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("INFORM");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FOR");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FORM");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FORMAT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("FORMATION");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("OR");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("MA");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("MAT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("A");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("AT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("ION");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("ON");
        } else {
            txtfill.setText(null);
        }
        if (lblpercent.getText().equals("100")) {

        }
    }//GEN-LAST:event_aActionPerformed

    private void tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tActionPerformed
        // TODO add your handling code here:
        T.setText("T");
        t.setEnabled(false);
        t.setForeground(Color.lightGray);
        if (I.getText().endsWith("") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("I");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("IN");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("INFO");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("INFORM");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FOR");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FORM");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FORMAT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("FORMATION");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("OR");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("MA");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("MAT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("A");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("AT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("ION");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("ON");
        } else {
            txtfill.setText(null);
        }
        if (lblpercent.getText().equals("100")) {

        }
    }//GEN-LAST:event_tActionPerformed

    private void i1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_i1ActionPerformed
        // TODO add your handling code here:
        I1.setText("I");
        i1.setEnabled(false);
        i1.setForeground(Color.lightGray);

        if (I.getText().endsWith("") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("I");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("IN");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("INFO");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("INFORM");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FOR");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FORM");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FORMAT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("FORMATION");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("OR");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("MA");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("MAT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("A");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("AT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("ION");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("ON");
        } else {
            txtfill.setText(null);
        }
        if (lblpercent.getText().equals("100")) {

        }
    }//GEN-LAST:event_i1ActionPerformed

    private void o1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_o1ActionPerformed
        // TODO add your handling code here:
        O1.setText("O");
        o1.setEnabled(false);
        o1.setForeground(Color.lightGray);
        if (I.getText().endsWith("") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("I");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("IN");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("INFO");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("INFORM");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FOR");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FORM");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FORMAT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("FORMATION");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("OR");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("MA");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("MAT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("A");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("AT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("ION");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("ON");
        } else {
            txtfill.setText(null);
        }
        if (lblpercent.getText().equals("100")) {

        }
    }//GEN-LAST:event_o1ActionPerformed

    private void n1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n1ActionPerformed
        // TODO add your handling code here:
        N2.setText("N");
        n1.setEnabled(false);
        n1.setForeground(Color.lightGray);
        if (I.getText().endsWith("") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("I");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("IN");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("INFO");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("INFORM");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FOR");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FORM");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FORMAT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("FORMATION");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("OR");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("MA");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("MAT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("A");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("AT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("ION");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("ON");
        } else {
            txtfill.setText(null);
        }
        if (lblpercent.getText().equals("100")) {

        }
    }//GEN-LAST:event_n1ActionPerformed
    DefaultListModel m1 = new DefaultListModel();
    private void btnsubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsubmitActionPerformed
        // TODO add your handling code here:
 jLabel1.setVisible(true);
        String name1 = txtfill.getText();
        if (m1.contains(name1)) {

            enable1();
            status1();

        } else {
            enbl();

            if (txtfill.getText().equals("I") || txtfill.getText().equals("INFO") || txtfill.getText().equals("IN") || txtfill.getText().equals("FOR") || txtfill.getText().equals("FORM") || txtfill.getText().equals("INFORM") || txtfill.getText().equals("FORMAT") || txtfill.getText().equals("OR") || txtfill.getText().equals("FORMATION") || txtfill.getText().equals("MA") || txtfill.getText().equals("MAT") || txtfill.getText().equals("ON") || txtfill.getText().equals("A") || txtfill.getText().equals("ION") || txtfill.getText().equals("AT")) {

                m1.addElement(txtfill.getText());
                jList1.setModel(m1);
                //  m.addElement(txtfill1.getText());
                // jList1.setModel(m);
                status();
                jButton5.setEnabled(true);
            } else {
                status1();
            }

            double name = jList1.getModel().getSize();
            txtcount.setText(Double.toString(name));
            double num1 = Double.parseDouble(txtcount.getText());
            double percent = (num1 / 15) * 100;
            int i = (int) percent;
            lblpercent.setText(Integer.toString(i));

            txtfill.setText(null);

            enable1();
            if (lblpercent.getText().equals("100")) {
                jLabel4.setVisible(true);
                jButton3.setEnabled(true);
            }
        }
    }//GEN-LAST:event_btnsubmitActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jList2.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void a1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a1ActionPerformed
        // TODO add your handling code here:
        M.setText("M");
        a1.setEnabled(false);
        a1.setForeground(Color.lightGray);
        if (I.getText().endsWith("") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("I");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("IN");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("INFO");
        } else if (I.getText().endsWith("") && N.getText().equals("") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("INFORM");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FOR");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FORM");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("FORMAT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("FORMATION");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("") && R.getText().equals("") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("OR");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("MA");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("MAT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("A");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("") && T.getText().equals("") && I1.getText().equals("I") && O1.getText().equals("O") && N2.getText().equals("N")) {
            txtfill.setText("AT");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("ION");
        } else if (I.getText().endsWith("I") && N.getText().equals("N") && F.getText().equals("F") & O.getText().equals("O") && R.getText().equals("R") && M.getText().equals("M") && A.getText().equals("A") && T.getText().equals("T") && I1.getText().equals("I") && O1.getText().equals("") && N2.getText().equals("")) {
            txtfill.setText("ON");
        } else {
            txtfill.setText(null);
        }
        if (lblpercent.getText().equals("100")) {

        }
    }//GEN-LAST:event_a1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        close();
        information obj = new information();
        obj.setVisible(true);
        jButton1.setEnabled(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        close();
        Child_parent obj = new Child_parent();
        obj.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        close();
        Loginframe obj = new Loginframe();
        obj.setVisible(true);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        jButton6.setEnabled(true);
        jButton2.setEnabled(true);
        jButton1.setEnabled(true);
         String rpt = lblpercent.getText();
        fa.setText(rpt);
       // updt();
        jButton2.setEnabled(true);
        btnsubmit.setEnabled(false);
        jButton1.setEnabled(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        lblpercent.setVisible(false);
        jLabel1.setVisible(false);
        fa.setVisible(true);
        sa.setVisible(true);
        ta.setVisible(true);
        firstattemp.setVisible(true);
        secondattemp.setVisible(true);
        thirdattemp.setVisible(true);
        pfa.setVisible(true);
        psa.setVisible(true);
        pta.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(information.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(information.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(information.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(information.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new information().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField A;
    private javax.swing.JTextField F;
    private javax.swing.JTextField I;
    private javax.swing.JTextField I1;
    private javax.swing.JTextField M;
    private javax.swing.JTextField N;
    private javax.swing.JTextField N2;
    private javax.swing.JTextField O;
    private javax.swing.JTextField O1;
    private javax.swing.JTextField R;
    private javax.swing.JTextField T;
    private javax.swing.JButton a;
    private javax.swing.JButton a1;
    private javax.swing.JButton btnsubmit;
    private javax.swing.JButton f;
    private javax.swing.JLabel fa;
    private javax.swing.JLabel firstattemp;
    private javax.swing.JButton i;
    private javax.swing.JButton i1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblpercent;
    private javax.swing.JButton n;
    private javax.swing.JButton n1;
    private javax.swing.JButton o;
    private javax.swing.JButton o1;
    private javax.swing.JLabel pfa;
    private javax.swing.JLabel psa;
    private javax.swing.JLabel pta;
    private javax.swing.JButton r;
    private javax.swing.JLabel sa;
    private javax.swing.JLabel secondattemp;
    private javax.swing.JButton t;
    private javax.swing.JLabel ta;
    private javax.swing.JLabel thirdattemp;
    private javax.swing.JTextField txtcount;
    private javax.swing.JTextField txtfill;
    // End of variables declaration//GEN-END:variables
}
