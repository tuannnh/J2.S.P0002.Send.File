package j2.s.p0002.send.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ServerApp extends javax.swing.JFrame implements Runnable {

    ServerSocket socket = null;
     Socket clientSocket = null;
    BufferedReader br = null;
    InputStream is = null;
    JFileChooser fc = new JFileChooser();
    String path, fileName;
    Thread t;

    public ServerApp() {
        initComponents();
        txtMessage.setEditable(false);
        try {
            socket = new ServerSocket(53411);

        } catch (Exception e) {
        }
        t = new Thread(this);
    }

    private void saveFile(String fileName) {
        path = txtPath.getText().trim();
        
        try {
            File receiveFile = new File( path +"/"+fileName);
            FileOutputStream fos = new FileOutputStream(receiveFile);
            byte [] fileByteArray = new byte[4096];
            is = clientSocket.getInputStream();
            int byteRead = is.read(fileByteArray, 0, fileByteArray.length);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bos.write(fileByteArray, 0, byteRead);
            bos.flush();
            bos.close();
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPath = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMessage = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        btnStart = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 300));
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(280, 100));

        jLabel1.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 15)); // NOI18N
        jLabel1.setText("File Path");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel1.setPreferredSize(new java.awt.Dimension(250, 100));
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

        getContentPane().add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel2.setPreferredSize(new java.awt.Dimension(300, 200));

        jLabel2.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 15)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Message");
        jLabel2.setPreferredSize(new java.awt.Dimension(260, 50));
        jPanel2.add(jLabel2);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(260, 150));

        txtMessage.setColumns(20);
        txtMessage.setRows(5);
        txtMessage.setPreferredSize(new java.awt.Dimension(180, 140));
        jScrollPane1.setViewportView(txtMessage);

        jPanel2.add(jScrollPane1);

        getContentPane().add(jPanel2, java.awt.BorderLayout.EAST);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 5));

        btnStart.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 14)); // NOI18N
        btnStart.setText("Start");
        btnStart.setPreferredSize(new java.awt.Dimension(100, 50));
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
        jPanel3.add(btnStart);

        btnSave.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 14)); // NOI18N
        btnSave.setText("Save");
        btnSave.setPreferredSize(new java.awt.Dimension(100, 50));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel3.add(btnSave);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        // TODO add your handling code here:
        t.start();
        txtMessage.append("Server is running!\n");
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        saveFile(fileName);
      

    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPathActionPerformed
        // TODO add your handling code here:
     
    }//GEN-LAST:event_txtPathActionPerformed

    private void txtPathMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPathMouseClicked
        // TODO add your handling code here:
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setDialogTitle("Choose a Directory to save your File");
        int result = fc.showSaveDialog(null);
        try {
            if (result == JFileChooser.APPROVE_OPTION) {
                File directory = fc.getSelectedFile();
                txtPath.setText(directory.getAbsolutePath());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Save File Error!");
            System.exit(0);

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
            java.util.logging.Logger.getLogger(ServerApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtMessage;
    private javax.swing.JTextField txtPath;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        try {
              clientSocket = socket.accept();
              if(clientSocket != null){
                   txtMessage.append("Client:" + clientSocket.getInetAddress().getHostAddress()+" is connected!\n");
                   br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                   fileName = br.readLine();
                   System.out.println(fileName);
                   Thread.sleep(500);
              }
        } catch (Exception e) {
        }
    }
}
