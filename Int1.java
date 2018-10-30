package proyectoarduino;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *--------------------------------------------------------------------------------------------------
 * Conexion entre arduino y java
 * ------------------------------------------------------------------------------------------------- *
 * @author Iván Pacheco Vargas
 */

public class Int1 extends javax.swing.JFrame {
 // crea el objeto modelo para la tabla que va tener el menu y el indice 
    DefaultTableModel modelo=new DefaultTableModel();
 //variable global para guardar lo que se ecriba en el jtextfield
 String cadenaJava="";
 
 
 
 //esta clase se encarga de  recivir los datos  que arduino manda 
 private static PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
    private  final SerialPortEventListener listener = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent spe) {
        //declara el arreglo donde se va guardar el dato que manda arduino  
        String valor[]=new String[1];
        //se declara el arreglo donde se va guardar la cadena que esta en el jtextfield
        String valorNuevo[]=new String[1];
        //en este arreglo esta el menu 
        String datos[]=new String[5];
        // llena el arreglo del menu 
        datos[0]="hora actual";
        datos[1]="temperatura";
        datos[2]="int. luz";
        datos[3]="humedad";
        datos[4]="mensaje";
        //guarda el valor del jtextfield en el arreglo
         valorNuevo[0]=cadenaJava;
         
            try {
                //inicia la condicion para cundo hay comunicacion con arduino
                if (ino.isMessageAvailable()) {
                    //el valor que  recive de arduino es guardado en el arreglo
                 valor[0]=ino.printMessage();
                 //imprime el valor guardado
                    System.out.println("ID=  "+valor[0]);
                    //switch para validar lo que recivio
                    switch(valor[0]){
                        //en caso de que el dato sea 1
                  case "1":
                        //obtiene la hora del sistema
                       Calendar calendario = Calendar.getInstance();
                        int hora = calendario.get(Calendar.HOUR_OF_DAY);
                        int minutos = calendario.get(Calendar.MINUTE);
                         //cadena que guarda la hora con formato    
                     String tiempo = hora + ":" + minutos;
                        //manda a arduino la hora con el formato
                       ino.sendData(tiempo);                       
                      break;
                   //en caso de que el dato sea 2
                  case "2":
                      //envia el mensaje de temperatura a arduino
                       ino.sendData(datos[1]);
                      break;
                      //en caso de que el dato sea 3
                  case "3":
                      //envia el mensaje de  intensidad de la luz  a arduino
                      ino.sendData(datos[2]);
                      break;
                      //en el caso que sea 4
                  case "4":
                       //manda el valor del jtextfield
                      //envia el mensaje de humedad a arduino
                       ino.sendData(datos[3]);
                      break;
                  case "5":
                        //manda el valor del jtextfield
                      ino.sendData(valorNuevo[0]);
                      break;
                                  
                  }  
                              
                   
                //exepcion en caso de algun error en la conexion    
                }
            } catch (SerialPortException | ArduinoException ex) {
                Logger.getLogger(Int1.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    };
    
    public Int1() {
        initComponents();
        //crea los titulos para la tabla
        String []titulo=new String[]{"id","menu"};
        //es la informacion que contendra la tabla
        String [][]contenido=new String[][]{{"1","hora actual"},{"2","temperatura"},{"3","int. de luz"},{"4","humedad"},{"5","mensaje"}};
        //le agrega a la tabla el titulo
        modelo.setColumnIdentifiers(titulo);
        //ciclo en el cual inserta la informacion de la tabla
        for (int i = 0; i < 5; i++) {
             modelo.addRow(new Object[]{contenido[i][0],contenido[i][1]});            
        }
       //todos los cambios los pone en la tabla
        jTable1.setModel(modelo);
        try {
           //hace la conexion a arduino con el puerto com6 indicando que envia y recive informacion
            ino.arduinoRXTX("COM19", 9600,listener);
        } catch (ArduinoException ex) {
            Logger.getLogger(Int1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        BTNGuarda = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        campoTexto = new javax.swing.JTextField();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BTNGuarda.setText("Guardar");
        BTNGuarda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNGuardaActionPerformed(evt);
            }
        });

        jLabel1.setText("Introduzca su mensaje");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable1);

        campoTexto.setText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(BTNGuarda, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(142, 142, 142))
                    .addComponent(campoTexto))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoTexto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(BTNGuarda)
                .addGap(53, 53, 53)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTNGuardaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNGuardaActionPerformed
       //pasa el contenido del jtextfield a una cadena
        cadenaJava=campoTexto.getText();
       
      
    }//GEN-LAST:event_BTNGuardaActionPerformed

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
            java.util.logging.Logger.getLogger(Int1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Int1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Int1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Int1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Int1().setVisible(true);
            }
        });
    }

   

    

    public static PanamaHitek_Arduino getIno() {
        return ino;
    }

    public static void setIno(PanamaHitek_Arduino ino) {
        Int1.ino = ino;
    }

    public JButton getBTNGuarda() {
        return BTNGuarda;
    }

    public void setBTNGuarda(JButton BTNGuarda) {
        this.BTNGuarda = BTNGuarda;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    public JList<String> getjList1() {
        return jList1;
    }

    public void setjList1(JList<String> jList1) {
        this.jList1 = jList1;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNGuarda;
    private javax.swing.JTextField campoTexto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
