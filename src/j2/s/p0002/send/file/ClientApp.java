/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j2.s.p0002.send.file;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author tuannnh
 */
public class ClientApp extends javax.swing.JFrame {

    /**
     * Creates new form ClientApp
     */
    JFileChooser fc = new JFileChooser();
    Socket serverSocket = null;
    String ip, path;
    int port;
    DataOutputStream dos = null;
    OutputStream os = null;
    BufferedInputStream bis;

    public ClientApp() throws IOException {
        initComponents();
        serverSocket = new Socket(txtAddress.getText().trim(), 53411);
    }

    private void sendFile(File sendfile) {
        try {
            byte[] fileByteArray = new byte[(int) sendfile.length()];
            System.out.println(sendfile.length());
            bis = new BufferedInputStream(new FileInputStream(sendfile));
            bis.read(fileByteArray, 0, fileByteArray.length);
            os = serverSocket.getOutputStream();
            os.write(fileByteArray, 0, fileByteArray.length);
            os.flush();
            bis.close();
            System.out.println("Send file Success");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Send file Error!");
            return;
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPath = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnSend = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Client");
        setPreferredSize(new java.awt.Dimension(600, 320));
        setResizable(false);
        getContentPane().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        jPanel1.setPreferredSize(new java.awt.Dimension(250, 300));

        jLabel1.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 15)); // NOI18N
        jLabel1.setText("File Path");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel1.setPreferredSize(new java.awt.Dimension(240, 100));
        jPanel1.add(jLabel1);

        txtPath.setPreferredSize(new java.awt.Dimension(250, 30));
        txtPath.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPathMouseClicked(evt);
            }
        });
        txtPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPathActionPerformed(evt);
            }
        });
        jPanel1.add(txtPath);

        getContentPane().add(jPanel1);

        jPanel2.setPreferredSize(new java.awt.Dimension(120, 250));
        jPanel2.setLayout(new java.awt.BorderLayout(0, 20));

        btnSend.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 14)); // NOI18N
        btnSend.setText("Send");
        btnSend.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSend.setPreferredSize(new java.awt.Dimension(75, 50));
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });
        jPanel2.add(btnSend, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel2);

        jPanel3.setPreferredSize(new java.awt.Dimension(190, 300));

        jLabel2.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 15)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("IP Address of Server");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel2.setPreferredSize(new java.awt.Dimension(180, 100));
        jPanel3.add(jLabel2);

        txtAddress.setText("127.0.0.1");
        txtAddress.setPreferredSize(new java.awt.Dimension(190, 30));
        jPanel3.add(txtAddress);

        getContentPane().add(jPanel3);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPathActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtPathActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        // TODO add your handling code here:
        try {
     
            path = txtPath.getText().trim();
            File sendfile = new File(path);
            if (sendfile.isFile()) {
                dos = new DataOutputStream(serverSocket.getOutputStream());
                dos.writeBytes(sendfile.getName());
                dos.writeByte(13);
                dos.flush();
                sendFile(sendfile);
            } else {
                JOptionPane.showMessageDialog(this, "Must be path to file!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

    }//GEN-LAST:event_btnSendActionPerformed

    private void txtPathMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPathMouseClicked
        // TODO add your handling code here:
        try {
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fc.setDialogTitle("Choose a Text File to send");
            int result = fc.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                path = fc.getSelectedFile().getAbsolutePath();
                txtPath.setText(path);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);

        }
    }//GEN-LAST:event_txtPathMouseClicked

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
            java.util.logging.Logger.getLogger(ClientApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ClientApp().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(ClientApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSend;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtPath;
    // End of variables declaration//GEN-END:variables
}
