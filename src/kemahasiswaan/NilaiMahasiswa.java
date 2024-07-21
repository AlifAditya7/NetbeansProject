/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kemahasiswaan;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author dinar
 */
public class NilaiMahasiswa extends javax.swing.JFrame {

    
        koneksi dbsetting;
        String driver,database,user,pass;
        Object table;
    
    /**
     * Creates new form NilaiMahasiswa
     */
    public NilaiMahasiswa() {
        initComponents();
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        SimpleDateFormat tahun = new SimpleDateFormat("YYYY");
        Date date = new Date();
        btn_hapus.setEnabled(false);
        btn_ubah.setEnabled(false);
        btn_simpan.setEnabled(false);
        btn_batal.setEnabled(false);
        txt_angkatan.setText(tahun.format(date));
        table_nilai.setModel(tableModel);
        settableload();
        
        jc_nama.addItem("Pilih Nama");
        jc_nm.addItem("Pilih Mata Kuliah");
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String SQL = "SELECT * FROM matakuliah"; 
            ResultSet res = stt.executeQuery(SQL);
            while(res.next()){
                String data = res.getString(2);
                jc_nm.addItem(data);
            }
            res.close();
            stt.close();
            kon.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String SQL = "SELECT * FROM mahasiswa"; 
            ResultSet res = stt.executeQuery(SQL);
            while(res.next()){
                String data = res.getString(2);
                jc_nama.addItem(data);
            }
            res.close();
            stt.close();
            kon.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
    private javax.swing.table.DefaultTableModel tableModel=getDefaultTabelModel();
    private javax.swing.table.DefaultTableModel getDefaultTabelModel() {
        //Membuat judul header
        return new javax.swing.table.DefaultTableModel(
                new Object[][] {},
                new String [] {"Nama",
                               "Nama MK",
                               "Absensi",
                               "Tgs 1",
                               "Tgs 2",
                               "Tgs 3",
                               "UTS",
                               "UAS",
                               "Nilai Absen",
                               "Nilai Tugas",
                               "Nilai UTS",
                               "Nilai UAS",
                               "Nilai Akhir",
                               "Indeks",
                               "Keterangan"}
        )
        //disable perubahan grid
        {
            boolean[] canEdit = new boolean[]
            {
                false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return canEdit[columnIndex];
            }
        };
    }
    
    String data[] = new String[15];
    private void settableload(){
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String SQL = "SELECT mahasiswa.nama,matakuliah.nama_mk, nilai_mhs.absensi,nilai_mhs.tugas1,nilai_mhs.tugas2,nilai_mhs.tugas3,nilai_mhs.uts,nilai_mhs.uas,nilai_mhs.nilai_absen,nilai_mhs.nilai_tgs,nilai_mhs.nilai_uts,\n" +
            "nilai_mhs.nilai_uas,nilai_mhs.nilai_akhir,nilai_mhs.indeks,nilai_mhs.keterangan FROM nilai_mhs INNER JOIN mahasiswa ON nilai_mhs.nim = mahasiswa.nim INNER JOIN matakuliah ON nilai_mhs.kode_mk = matakuliah.no_mk"; 
            ResultSet res = stt.executeQuery(SQL);
            while(res.next()){
                data[0]= res.getString(1);
                data[1]= res.getString(2);
                data[2]= res.getString(3);
                data[3]= res.getString(4);
                data[4]= res.getString(5);
                data[5]= res.getString(6);
                data[6]= res.getString(7);
                data[7]= res.getString(8);
                data[8]= res.getString(9);
                data[9]= res.getString(10);
                data[10]= res.getString(11);
                data[11]= res.getString(12);
                data[12]= res.getString(13);
                data[13]= res.getString(14);
                data[14]= res.getString(15);
                tableModel.addRow(data);
            }
            res.close();
            stt.close();
            kon.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
    
    private void unsettable(){
        tableModel.setRowCount(0);
    }
    public void  nonaktif_teks(){
        jc_nama.setEnabled(false);
        jc_nm.setEnabled(false);
        txt_nim.setEnabled(false);
        txt_angkatan.setEnabled(false);
        txt_kehadiran.setEnabled(false);
        txt_tugas1.setEnabled(false);
        txt_tugas2.setEnabled(false);
        txt_tugas3.setEnabled(false);
        txt_kodeMK.setEnabled(false);
        txt_uas.setEnabled(false);
        txt_uts.setEnabled(false);
    }
    public void  aktif_teks(){
        jc_nama.setEnabled(true);
        jc_nm.setEnabled(true);
        txt_kehadiran.setEnabled(true);
        txt_tugas1.setEnabled(true);
        txt_tugas2.setEnabled(true);
        txt_tugas3.setEnabled(true);
        txt_uas.setEnabled(true);
        txt_uts.setEnabled(true);
    }
    
    public void membersihkan_teks(){
        jc_nama.setSelectedItem("Pilih Nama");
        jc_nm.setSelectedItem("Pilih Mata Kuliah");
        txt_nim.setText("");
        txt_kehadiran.setText("");
        txt_tugas1.setText("");
        txt_tugas2.setText("");
        txt_tugas3.setText("");
        txt_kodeMK.setText("");
        txt_uas.setText("");
        txt_uts.setText("");
        
    }
    int row = 0;
    public void tampil_field(){
        row = table_nilai.getSelectedRow();
        jc_nama.setSelectedItem(tableModel.getValueAt(row, 0).toString());
        jc_nm.setSelectedItem(tableModel.getValueAt(row, 1).toString());
        txt_kehadiran.setText(tableModel.getValueAt(row, 2).toString());
        txt_tugas1.setText(tableModel.getValueAt(row, 3).toString());
        txt_tugas2.setText(tableModel.getValueAt(row, 4).toString());
        txt_tugas3.setText(tableModel.getValueAt(row, 5).toString());
        txt_uts.setText(tableModel.getValueAt(row, 6).toString());
        txt_uas.setText(tableModel.getValueAt(row, 7).toString());
        btn_simpan.setEnabled(false);
        btn_ubah.setEnabled(true);
        btn_hapus.setEnabled(true);
        btn_batal.setEnabled(false);
        aktif_teks();
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
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        txt_data = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jc_nama = new javax.swing.JComboBox<>();
        txt_nim = new javax.swing.JTextField();
        txt_kehadiran = new javax.swing.JTextField();
        txt_tugas1 = new javax.swing.JTextField();
        txt_tugas2 = new javax.swing.JTextField();
        txt_tugas3 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jc_nm = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txt_kodeMK = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_uts = new javax.swing.JTextField();
        txt_uas = new javax.swing.JTextField();
        txt_angkatan = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_nilai = new javax.swing.JTable();
        btn_tambah = new javax.swing.JButton();
        btn_ubah = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        btn_keluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Form Nilai Mahasiswa");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(506, 506, 506))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jLabel2.setText("Pencarian Data");

        jLabel3.setText("Masukan Data");

        txt_data.setToolTipText("");
        txt_data.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_dataKeyReleased(evt);
            }
        });

        jLabel4.setText("Nama");

        jLabel5.setText("NIM");

        jLabel6.setText("Kehadiran");

        jLabel7.setText("Tugas 1");

        jLabel8.setText("Tugas 2");

        jLabel9.setText("Tugas 3");

        jc_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jc_namaActionPerformed(evt);
            }
        });
        jc_nama.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jc_namaPropertyChange(evt);
            }
        });

        jLabel10.setText("Nama Mata Kuliah");

        jc_nm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jc_nmActionPerformed(evt);
            }
        });

        jLabel11.setText("Kode MK");

        txt_kodeMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kodeMKActionPerformed(evt);
            }
        });

        jLabel12.setText("UTS");

        jLabel13.setText("UAS");

        jLabel14.setText("Angkatan");

        table_nilai.setModel(new javax.swing.table.DefaultTableModel(
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
        table_nilai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_nilaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_nilai);

        btn_tambah.setText("Tambah");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_ubah.setText("Ubah");
        btn_ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ubahActionPerformed(evt);
            }
        });

        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_batal.setText("Batal");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });

        btn_keluar.setText("Keluar");
        btn_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_keluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txt_data, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jc_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_nim, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txt_tugas3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                                        .addComponent(txt_tugas2, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_tugas1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_kehadiran, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addGap(261, 261, 261)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel13))
                                        .addGap(72, 72, 72)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_angkatan, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jc_nm, 0, 200, Short.MAX_VALUE)
                                                .addComponent(txt_kodeMK)
                                                .addComponent(txt_uts, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txt_uas, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_tambah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
                                .addComponent(btn_ubah)
                                .addGap(154, 154, 154)
                                .addComponent(btn_hapus)
                                .addGap(216, 216, 216)
                                .addComponent(btn_simpan)
                                .addGap(204, 204, 204)
                                .addComponent(btn_batal)
                                .addGap(130, 130, 130)
                                .addComponent(btn_keluar)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jc_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jc_nm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_nim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(txt_kodeMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12)
                                .addComponent(txt_uts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_tugas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(txt_uas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_tugas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txt_tugas3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_angkatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tambah)
                    .addComponent(btn_ubah)
                    .addComponent(btn_hapus)
                    .addComponent(btn_simpan)
                    .addComponent(btn_batal)
                    .addComponent(btn_keluar))
                .addGap(30, 30, 30))
        );

        setBounds(0, 0, 1298, 767);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        nonaktif_teks();
    }//GEN-LAST:event_formWindowActivated

    private void jc_namaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jc_namaPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jc_namaPropertyChange

    private void jc_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jc_namaActionPerformed
        // TODO add your handling code here:
        try {
            if (jc_nama.getSelectedItem() == "Pilih Nama") {
                txt_nim.setText("");
            }
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String SQL = "SELECT * FROM mahasiswa WHERE nama = '"+jc_nama.getSelectedItem()+"';"; 
            ResultSet res = stt.executeQuery(SQL);
            while(res.next()){
                String data = res.getString(1);
                txt_nim.setText(data);
            }
            res.close();
            stt.close();
            kon.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }//GEN-LAST:event_jc_namaActionPerformed

    private void txt_kodeMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kodeMKActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txt_kodeMKActionPerformed

    private void jc_nmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jc_nmActionPerformed
        // TODO add your handling code here:
        try {
            if (jc_nm.getSelectedItem() == "Pilih Mata Kuliah") {
                txt_kodeMK.setText("");
            }
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String SQL = "SELECT * FROM matakuliah WHERE nama_mk = '"+jc_nm.getSelectedItem()+"';"; 
            ResultSet res = stt.executeQuery(SQL);
            while(res.next()){
                String data = res.getString(1);
                txt_kodeMK.setText(data);
            }
            res.close();
            stt.close();
            kon.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }//GEN-LAST:event_jc_nmActionPerformed

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        aktif_teks();
        txt_nim.requestFocus();
        btn_simpan.setEnabled(true);
        btn_ubah.setEnabled(false);
        btn_hapus.setEnabled(false);
        btn_keluar.setEnabled(false);
        btn_batal.setEnabled(true);
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        // TODO add your handling code here:
        nonaktif_teks();
        membersihkan_teks();
        btn_keluar.setEnabled(true);
    }//GEN-LAST:event_btn_batalActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        String data[] = new String[15];
        Integer tugas1 = Integer.parseInt(txt_tugas1.getText());
        Integer tugas2 = Integer.parseInt(txt_tugas2.getText());
        Integer tugas3 = Integer.parseInt(txt_tugas3.getText());
        Integer uts = Integer.parseInt(txt_uts.getText());
        Integer uas = Integer.parseInt(txt_uas.getText());
        Integer absensi = Integer.parseInt(txt_kehadiran.getText());
        Integer nilai_absen = (absensi/14)*100*5/100;
        Integer nilai_tugas = (tugas1 + tugas2 + tugas3) /3 * 25/100 ;
        Integer nilai_uts = uts * 30 / 100;
        Integer nilai_uas = uas * 40 / 100;
        Integer nilai_akhir = nilai_absen + nilai_tugas + nilai_uts + nilai_uas;
        String indeks;
        if (nilai_akhir >= 80) {
            indeks = "A";
        } else if(nilai_akhir >= 68) {
            indeks = "B";
        }else if (nilai_akhir >= 56 ) {
            indeks = "C";
        }else if (nilai_akhir >= 45) {
            indeks = "D";
        }else{
            indeks = "E";
        }
        String keterangan;
        if (absensi < 11) {
            keterangan = "Tidak Lulus";
        }else if (indeks == "A" || indeks == "B" || indeks == "C"){
            keterangan = "Lulus";
        }else{
            keterangan = "Tidak Lulus";
        }
        
        
        if ((txt_nim.getText().isEmpty()) || (txt_kodeMK.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "data tidak boleh kosong,silahkan dilengkapi");
            txt_nim.requestFocus();
        } else {
            try {
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database,user,pass);
                Statement stt = kon.createStatement();
                String SQL = "INSERT INTO nilai_mhs(nim,"
                        +"kode_mk,"
                        +"absensi,"
                        +"tugas1,"
                        +"tugas2,"
                        +"tugas3,"
                        +"uts,"
                        +"uas,"
                        +"nilai_absen,"
                        +"nilai_tgs,"
                        +"nilai_uts,"
                        +"nilai_uas,"
                        +"nilai_akhir,"
                        +"indeks,"
                        +"keterangan) "
                        +"VALUES "
                        +"( '"+txt_nim.getText()+"',"
                        +" '"+txt_kodeMK.getText()+"',"
                        +" ' "+txt_kehadiran.getText()+"',"
                        +" ' "+txt_tugas1.getText()+"',"
                        +" ' "+txt_tugas2.getText()+"',"
                        +" ' "+txt_tugas3.getText()+"',"
                        +" ' "+txt_uts.getText()+"',"
                        +" ' "+txt_uas.getText()+"',"
                        +" ' "+nilai_absen+"',"
                        +" ' "+nilai_tugas+"',"
                        +" ' "+nilai_uts+"',"
                        +" ' "+nilai_uas+"',"
                        +" ' "+nilai_akhir+"',"
                        +" ' "+indeks+"',"
                        +" ' "+keterangan+" ')";
                stt.executeUpdate(SQL);
//                data[0] = String.valueOf(jc_nama.getSelectedItem());
//                data[1] = String.valueOf(jc_nm.getSelectedItem());
//                data[2] = txt_kehadiran.getText();
//                data[3] = txt_tugas1.getText();
//                data[4] = txt_tugas2.getText();
//                data[5] = txt_tugas3.getText();
//                data[6] = txt_uts.getText();
//                data[7] = txt_uas.getText();
//                data[8] = String.valueOf(nilai_absen);
//                data[9] = String.valueOf(nilai_tugas);
//                data[10] = String.valueOf(nilai_uts);
//                data[11] = String.valueOf(nilai_uas);
//                data[12] = String.valueOf(nilai_akhir);
//                data[13] = indeks;
//                data[14] = keterangan;
                unsettable();
                settableload();
                stt.close();
                kon.close();
                membersihkan_teks();
                btn_simpan.setEnabled(false);
                nonaktif_teks();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void table_nilaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_nilaiMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            tampil_field();
        }
    }//GEN-LAST:event_table_nilaiMouseClicked

    private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
        // TODO add your handling code here:
        
        String data[] = new String[15];
        Integer tugas1 = Integer.parseInt(txt_tugas1.getText());
        Integer tugas2 = Integer.parseInt(txt_tugas2.getText());
        Integer tugas3 = Integer.parseInt(txt_tugas3.getText());
        Integer uts = Integer.parseInt(txt_uts.getText());
        Integer uas = Integer.parseInt(txt_uas.getText());
        Integer absensi = Integer.parseInt(txt_kehadiran.getText());
        Integer nilai_absen = (absensi/14)*100*5/100;
        Integer nilai_tugas = (tugas1 + tugas2 + tugas3) /3 * 25/100 ;
        Integer nilai_uts = uts * 30 / 100;
        Integer nilai_uas = uas * 40 / 100;
        Integer nilai_akhir = nilai_absen + nilai_tugas + nilai_uts + nilai_uas;
        Integer no = row+1;
        String indeks;
        if (nilai_akhir >= 80) {
            indeks = "A";
        } else if(nilai_akhir >= 68) {
            indeks = "B";
        }else if (nilai_akhir >= 56 ) {
            indeks = "C";
        }else if (nilai_akhir >= 45) {
            indeks = "D";
        }else{
            indeks = "E";
        }
        String keterangan;
        if (absensi < 11) {
            keterangan = "Tidak Lulus";
        }else if (indeks == "A" || indeks == "B" || indeks == "C"){
            keterangan = "Lulus";
        }else{
            keterangan = "Tidak Lulus";
        }
        
        
        if ((txt_nim.getText().isEmpty()) || (txt_kodeMK.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "data tidak boleh kosong,silahkan dilengkapi");
            txt_nim.requestFocus();
        } else {
            try {
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database,user,pass);
                Statement stt = kon.createStatement();
                String SQL = "UPDATE nilai_mhs "
                        +  "SET nim='"+txt_nim.getText()+"',"
                        + "kode_mk='"+txt_kodeMK.getText()+"',"
                        + "absensi='"+txt_kehadiran.getText()+"',"
                        + "tugas1='"+txt_tugas1.getText()+"',"
                        + "tugas2='"+txt_tugas2.getText()+"',"
                        + "tugas3='"+txt_tugas3.getText()+"',"
                        + "uts='"+txt_uts.getText()+"',"
                        + "uas='"+txt_uas.getText()+"',"
                        + "nilai_absen='"+nilai_absen+"',"
                        + "nilai_tgs='"+nilai_tugas+"',"
                        + "nilai_uts='"+nilai_uts+"',"
                        + "nilai_uas='"+nilai_uas+"',"
                        + "nilai_akhir='"+nilai_akhir+"',"
                        + "indeks='"+indeks+"',"
                        + "keterangan='"+keterangan+"' "
                        + "WHERE "
                        +"no='"+no+"';";
                stt.executeUpdate(SQL);
                data[0] = String.valueOf(jc_nama.getSelectedItem());
                data[1] = String.valueOf(jc_nm.getSelectedItem());
                data[2] = txt_kehadiran.getText();
                data[3] = txt_tugas1.getText();
                data[4] = txt_tugas2.getText();
                data[5] = txt_tugas3.getText();
                data[6] = txt_uts.getText();
                data[7] = txt_uas.getText();
                data[8] = String.valueOf(nilai_absen);
                data[9] = String.valueOf(nilai_tugas);
                data[10] = String.valueOf(nilai_uts);
                data[11] = String.valueOf(nilai_uas);
                data[12] = String.valueOf(nilai_akhir);
                data[13] = indeks;
                data[14] = keterangan;
                tableModel.removeRow(row);
                tableModel.insertRow(row, data);
                stt.close();
                kon.close();
                membersihkan_teks();
                btn_simpan.setEnabled(false);
                nonaktif_teks();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_ubahActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        Integer no = row+1;
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String SQL = "DELETE FROM nilai_mhs "
                    +"WHERE "
                    +"no='"+no+"';";
            String ResetNo = "ALTER TABLE nilai_mhs DROP no";
            String ConsecutiveNo = "ALTER TABLE nilai_mhs ADD no INT NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST";
            stt.executeUpdate(SQL);
            stt.execute(ResetNo);
            stt.execute(ConsecutiveNo);
            tableModel.removeRow(row);
            stt.close();
            kon.close();
            membersihkan_teks();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_keluarActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new Frameutama().setVisible(true);
    }//GEN-LAST:event_btn_keluarActionPerformed

    private void txt_dataKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_dataKeyReleased
        // TODO add your handling code here:
        tableModel.setRowCount(0);
        if (txt_data.getText().isEmpty()){
            settableload();
        }
        else{
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String SQL = "SELECT mahasiswa.nama,matakuliah.nama_mk, nilai_mhs.absensi,nilai_mhs.tugas1,nilai_mhs.tugas2,nilai_mhs.tugas3,nilai_mhs.uts,nilai_mhs.uas,nilai_mhs.nilai_absen,nilai_mhs.nilai_tgs,nilai_mhs.nilai_uts,nilai_mhs.nilai_uas,nilai_mhs.nilai_akhir,nilai_mhs.indeks,nilai_mhs.keterangan FROM nilai_mhs INNER JOIN mahasiswa ON nilai_mhs.nim = mahasiswa.nim INNER JOIN matakuliah ON nilai_mhs.kode_mk = matakuliah.no_mk WHERE nilai_mhs.nim ='"+txt_data.getText()+"';";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next()){
                data[0]= res.getString(1);
                data[1]= res.getString(2);
                data[2]= res.getString(3);
                data[3]= res.getString(4);
                data[4]= res.getString(5);
                data[5]= res.getString(6);
                data[6]= res.getString(7);
                data[7]= res.getString(8);
                data[8]= res.getString(9);
                data[9]= res.getString(10);
                data[10]= res.getString(11);
                data[11]= res.getString(12);
                data[12]= res.getString(13);
                data[13]= res.getString(14);
                data[14]= res.getString(15);
                tableModel.addRow(data);
            }
            res.close();
            stt.close();
            kon.close();
        } 
        catch (Exception ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        }
    }//GEN-LAST:event_txt_dataKeyReleased

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
            java.util.logging.Logger.getLogger(NilaiMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NilaiMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NilaiMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NilaiMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NilaiMahasiswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_keluar;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton btn_ubah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox<String> jc_nama;
    private javax.swing.JComboBox<String> jc_nm;
    private javax.swing.JTable table_nilai;
    private javax.swing.JTextField txt_angkatan;
    private javax.swing.JTextField txt_data;
    private javax.swing.JTextField txt_kehadiran;
    private javax.swing.JTextField txt_kodeMK;
    private javax.swing.JTextField txt_nim;
    private javax.swing.JTextField txt_tugas1;
    private javax.swing.JTextField txt_tugas2;
    private javax.swing.JTextField txt_tugas3;
    private javax.swing.JTextField txt_uas;
    private javax.swing.JTextField txt_uts;
    // End of variables declaration//GEN-END:variables
}
