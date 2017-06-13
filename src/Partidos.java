
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author danie
 */
public class Partidos extends javax.swing.JInternalFrame {
    DefaultComboBoxModel model;
    DefaultComboBoxModel model1;
    /**
     * Creates new form Tabla
     */
    public Partidos() {
        initComponents();
        cargarMes();
        carPartidos("");
    }
    public void cargarDia() {
        cmbDia.addItem("Dia");
        String mes = "";

        mes = String.valueOf(cmbMes.getSelectedItem());

        int d = 1;
        String año = "";

        año = String.valueOf(cmbAño.getSelectedItem());

        int a = Integer.valueOf(año);
        int b = 1920;
        while (b < a) {
            b = b + 4;
        }
//        JOptionPane.showMessageDialog(null, b);
        String dia = String.valueOf(cmbDia.getSelectedItem());
        DefaultComboBoxModel modelodia = new DefaultComboBoxModel();
        if (mes.equals("Enero") || mes.equals("Marzo") || mes.equals("Mayo")
                || mes.equals("Julio") || mes.equals("Agosto") || mes.equals("Octubre")
                || mes.equals("Diciembre")) {
            while (d <= 31) {
                modelodia.addElement(String.valueOf(d));
                d++;
            }
        } else if (mes.equals("Abril") || mes.equals("Junio") || mes.equals("Septiembre")
                || mes.equals("Noviembre")) {
            while (d <= 30) {
                modelodia.addElement(String.valueOf(d));
                d++;
            }
        } else if (mes.equals("Febrero")) {
            if (b == a) {
                while (d <= 29) {
                    modelodia.addElement(String.valueOf(d));
                    d++;
                }
            } else {
                while (d <= 28) {
                    modelodia.addElement(String.valueOf(d));
                    d++;
                }
            }
        }
        cmbDia.setModel(modelodia);

        cmbDia.setSelectedItem(dia);
    }
    public String seleccionarDMes(String x) {
        String mes = "";
        if (x.equals("Enero")) {
            mes = "01";
        } else if (x.equals("Febrero")) {
            mes = "02";
        } else if (x.equals("Marzo")) {
            mes = "03";
        } else if (x.equals("Abril")) {
            mes = "04";
        } else if (x.equals("Mayo")) {
            mes = "05";
        } else if (x.equals("Junio")) {
            mes = "06";
        } else if (x.equals("Julio")) {
            mes = "07";
        } else if (x.equals("Agosto")) {
            mes = "08";
        } else if (x.equals("Septiembre")) {
            mes = "09";
        } else if (x.equals("Octubre")) {
            mes = "10";
        } else if (x.equals("Noviembre")) {
            mes = "11";
        } else if (x.equals("Diciembre")) {
            mes = "12";
        }
        return mes;
    }

    public void cargarMes() {

        int a = 2017;
        while (a >= 1920) {
            cmbAño.addItem(String.valueOf(a));
            a--;
        }
        int m = 1;

        cmbMes.addItem("Enero");
        cmbMes.addItem("Febrero");
        cmbMes.addItem("Marzo");
        cmbMes.addItem("Abril");
        cmbMes.addItem("Mayo");
        cmbMes.addItem("Junio");
        cmbMes.addItem("Julio");
        cmbMes.addItem("Agosto");
        cmbMes.addItem("Septiembre");
        cmbMes.addItem("Octubre");
        cmbMes.addItem("Noviembre");
        cmbMes.addItem("Diciembre");

    }
    public void carPartidos(String Dato) {
        String registros="";
        model = new DefaultComboBoxModel();
        model1 = new DefaultComboBoxModel();
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        String sql = "";
        sql = "select * from EQUIPOS";
        try {
            Statement psd = cn.createStatement(); //clases propias de sql
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                registros = rs.getString("NOM_EQU");
                model.addElement(registros);
                model1.addElement(registros);

            }
            cmbLocal.setModel(model);
            cmbVisitante.setModel(model1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }
    public void guardar() {
        if (txtNumPart.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Numero de Partido");
        } else if (txtEstadio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Estadio");
        } else {
            String EST_PAR,FEC_HOR_PAR,COD_EQU_LOC,COD_EQU_VIS;
            int NUM_PAR;
                Conexion cc = new Conexion();
                Connection cn = cc.conectar();
                NUM_PAR = Integer.valueOf(txtNumPart.getText());
                EST_PAR=txtEstadio.getText();
                String mesn = seleccionarDMes(String.valueOf(cmbMes.getSelectedItem()));
                FEC_HOR_PAR = String.valueOf(cmbDia.getSelectedItem()) + "/" + mesn + "/" + String.valueOf(cmbAño.getSelectedItem());
                COD_EQU_LOC = String.valueOf(cmbLocal.getSelectedItem());
                COD_EQU_VIS = String.valueOf(cmbVisitante.getSelectedItem());
                String sql = "";
                String sql2 = "";
                String equLoc;
                String equLoc1=String.valueOf(cmbLocal.getSelectedItem());
                String equLoc2="";
                String equVis;
                String equVis1=String.valueOf(cmbVisitante.getSelectedItem());
                String equVis2="";
                sql = "insert into PARTIDOS(NUM_PAR,EST_PAR,FEC_HOR_PAR,COD_EQU_LOC,COD_EQU_VIS) values(?,?,?,?,?)";
                sql2 = "select * from EQUIPOS";
                String sql3="";
                sql3="select * from PARTIDOS";
                String numpart="";
                String numpart1=txtNumPart.getText();
                String numpart2="";
                try {
                    Statement psd2=cn.createStatement();
                    ResultSet rs1=psd2.executeQuery(sql3);
                    while(rs1.next()){
                        numpart=rs1.getString("NUM_PAR");
                        if(numpart.equals(numpart1)){
                            numpart2=numpart;
//                            JOptionPane.showMessageDialog(null, numpart2);
                        }
                    }
                    if(numpart2.equals(numpart1)){
                        JOptionPane.showMessageDialog(null, "Error: El Número de Partido ya existe");
                        txtNumPart.setText("");
                        txtNumPart.requestFocus();
                    }else{
                    Statement psd = cn.createStatement();
                    ResultSet rs = psd.executeQuery(sql2);
                    while (rs.next()) {
                        equLoc = rs.getString("NOM_EQU");
                        if (equLoc.equals(equLoc1)) {
                            equLoc2=rs.getString("COD_EQU");
                        }
                        equVis = rs.getString("NOM_EQU");
                        if (equVis.equals(equVis1)) {
                            equVis2=rs.getString("COD_EQU");
                        }
                    }
                    PreparedStatement psd1 = cn.prepareStatement(sql);
//                    NUM_PART,EST_PAR,FEC_HOR_PAR,COD_EQU_LOC,COD_EQU_VIS
                            psd1.setInt(1, NUM_PAR);
                            psd1.setString(2, EST_PAR);
                            psd1.setString(3, FEC_HOR_PAR);
                            psd1.setString(4, equLoc2);
                            psd1.setString(5, equVis2);
                            int n = psd1.executeUpdate();
                            if (n > 0) {
//                    JOptionPane.showMessageDialog(null, psd);
                                JOptionPane.showMessageDialog(null, "Se inserto correctamente el dato");
//                                botonesInicio();
//                                limpiarTextos();
//                                textosInicio();
                            }
                    }    
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }

                
                      
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

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNumPart = new javax.swing.JTextField();
        cmbDia = new javax.swing.JComboBox();
        cmbMes = new javax.swing.JComboBox();
        cmbAño = new javax.swing.JComboBox();
        cmbLocal = new javax.swing.JComboBox<>();
        cmbVisitante = new javax.swing.JComboBox<>();
        txtEstadio = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/salir.png"))); // NOI18N
        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Numero Partido:");

        jLabel2.setText("Equipo Local:");

        jLabel3.setText("Equipo Visitante:");

        jLabel4.setText("Fecha:");

        jLabel5.setText("Estadio:");

        txtNumPart.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumPartKeyTyped(evt);
            }
        });

        cmbMes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbMesItemStateChanged(evt);
            }
        });
        cmbMes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbMesMouseClicked(evt);
            }
        });

        cmbAño.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbAñoItemStateChanged(evt);
            }
        });

        txtEstadio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEstadioKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNumPart, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addGap(10, 10, 10)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cmbDia, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbMes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbAño, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cmbLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbVisitante, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEstadio, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNumPart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbVisitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtEstadio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel6.setText("Partidos");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        String local=String.valueOf(cmbLocal.getSelectedItem());
        String visitante=String.valueOf(cmbVisitante.getSelectedItem());;
        if(!local.equals(visitante)){
            guardar();
        }else{
            JOptionPane.showMessageDialog(null,"Error: El mismo equipo no puede ser local y visitante");
            cmbLocal.requestFocus();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmbMesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbMesItemStateChanged
        // TODO add your handling code here:
        cargarDia();
    }//GEN-LAST:event_cmbMesItemStateChanged

    private void cmbMesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMesMouseClicked

    private void cmbAñoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbAñoItemStateChanged
        // TODO add your handling code here:
        cargarDia();
    }//GEN-LAST:event_cmbAñoItemStateChanged

    private void txtNumPartKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumPartKeyTyped
        // TODO add your handling code here:
        char a = evt.getKeyChar();
        if(Character.isLetter(a)){
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtNumPartKeyTyped

    private void txtEstadioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstadioKeyTyped
        // TODO add your handling code here:
        ValidacionMayusculas(evt);
        char a = evt.getKeyChar();
        if(Character.isDigit(a)){
            evt.consume();
            getToolkit().beep();
        }
        char[] cadena = txtEstadio.getText().toCharArray();

        if (txtEstadio.getText().length()>=10) {
            evt.consume();
            
        }
    }//GEN-LAST:event_txtEstadioKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed
    
    public void ValidacionMayusculas(KeyEvent evt) {
        char caracter = evt.getKeyChar();
        if (Character.isLowerCase(caracter)) {
            String cadena = ("" + caracter);
            caracter = cadena.toUpperCase().charAt(0);
            evt.setKeyChar(caracter);
        }
    }
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
            java.util.logging.Logger.getLogger(Partidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Partidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Partidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Partidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Partidos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbAño;
    private javax.swing.JComboBox<String> cmbDia;
    private javax.swing.JComboBox<String> cmbLocal;
    private javax.swing.JComboBox<String> cmbMes;
    private javax.swing.JComboBox<String> cmbVisitante;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtEstadio;
    private javax.swing.JTextField txtNumPart;
    // End of variables declaration//GEN-END:variables
}
