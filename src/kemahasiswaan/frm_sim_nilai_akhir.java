/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kemahasiswaan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
/**
 *
 * @author M.Alif
 */
public class frm_sim_nilai_akhir extends javax.swing.JFrame {
koneksi dbsetting;
String driver,database,user,pass;
Object tabel;
    /**
     * Creates new form frm_sim_nilai_akhir
     */
    public frm_sim_nilai_akhir() {
        initComponents();
        
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        
        tabel_nilai.setModel(tablemodel);
        datacombobox();
        nonaktif_teks();
        settableload();
    }
    private javax.swing.table.DefaultTableModel tablemodel=getDefaultTableModel();
    private javax.swing.table.DefaultTableModel getDefaultTableModel(){
        return new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String [] {
                "No.","Nama MK","Persentase Absen","Persentase Tugas","Persentase UTS","Persentase UAS","Absensi","Tgs 1","Tgs 2","Tgs 3",
                "UTS","UAS","Nilai Absen","Nilai Tugas","Nilai UTS","Nilai UAS","Nilai Akhir","Index","Keterangan"
            }
                
        )
        {
            boolean[] canedit = new boolean[]{
                false,false,false,false,false
            };
            
            public boolean iscelleditable(int rowindex, int columnindex){
                return canedit[columnindex];
            }
        };
    }
    String data[] = new String[19];
    private void settableload(){
        String stat="";
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String SQL = "SELECT nilai_akhir.no, matakuliah.nama_mk, nilai_akhir.persen_absen, nilai_akhir.persen_tugas, nilai_akhir.persen_uts, nilai_akhir.persen_uas, "
                    + "nilai_akhir.absensi, nilai_akhir.tugas1, nilai_akhir.tugas2, nilai_akhir.tugas3, nilai_akhir.uts, nilai_akhir.uas, nilai_akhir.nilai_absen, nilai_akhir.nilai_tugas,"
                    + "nilai_akhir.nilai_uts, nilai_akhir.nilai_uas, nilai_akhir.nilai_akhir, nilai_akhir.indeks, nilai_akhir.keterangan"
                    + " FROM nilai_akhir INNER JOIN matakuliah ON nilai_akhir.kode_mk = matakuliah.no_mk";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next()){
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                data[2] = res.getString(3);
                data[3] = res.getString(4);
                data[4] = res.getString(5);
                data[5] = res.getString(6);
                data[6] = res.getString(7);
                data[7] = res.getString(8);
                data[8] = res.getString(9);
                data[9] = res.getString(10);
                data[10] = res.getString(11);
                data[11] = res.getString(12);
                data[12] = res.getString(13);
                data[13] = res.getString(14);
                data[14] = res.getString(15);
                data[15] = res.getString(16);
                data[16] = res.getString(17);
                data[17] = res.getString(18);
                data[18] = res.getString(19);
                tablemodel.addRow(data);
            }
            res.close();
            stt.close();
            kon.close();
        } 
        catch (Exception ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
    public void datacombobox(){
        combo_nama_matkul.addItem("Pilih Matkul");
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String SQL = "SELECT nama_mk FROM matakuliah";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next()){
                String name = res.getString("nama_mk");
                combo_nama_matkul.addItem(name);
            }
            stt.close();
            kon.close();
        } 
        catch (Exception ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);System.exit(0);
        }
    }
    public void setlimit(){
        int data = Integer.parseInt(txt_kehadiran.getText());
        if(data > 14){
            JOptionPane.showMessageDialog(null,"kehadiran tidak boleh lebih dari 14 pertemuan!");
            txt_kehadiran.requestFocus();
        }
    }
    public void membersihkan_teks(){
        txt_kehadiran.setText("");
        txt_kode_mk.setText("");
        txt_persen_absen.setText("");
        txt_persen_tugas.setText("");
        txt_persen_uts.setText("");
        txt_persen_uas.setText("");
        txt_tugas1.setText("");
        txt_tugas2.setText("");
        txt_tugas3.setText("");
        txt_uas.setText("");
        txt_uts.setText("");
        
    }
    public void nonaktif_teks(){
        txt_kehadiran.setEnabled(false);
        txt_kode_mk.setEnabled(false);
        txt_persen_absen.setEnabled(false);
        txt_persen_tugas.setEnabled(false);
        txt_persen_uts.setEnabled(false);
        txt_persen_uas.setEnabled(false);
        txt_tugas1.setEnabled(false);
        txt_tugas2.setEnabled(false);
        txt_tugas3.setEnabled(false);
        txt_uas.setEnabled(false);
        txt_uts.setEnabled(false);
    }
    public void aktif_teks(){
        txt_kehadiran.setEnabled(true);
        txt_kode_mk.setEnabled(true);
        txt_persen_absen.setEnabled(true);
        txt_persen_tugas.setEnabled(true);
        txt_persen_uts.setEnabled(true);
        txt_persen_uas.setEnabled(true);
        txt_tugas1.setEnabled(true);
        txt_tugas2.setEnabled(true);
        txt_tugas3.setEnabled(true);
        txt_uas.setEnabled(true);
        txt_uts.setEnabled(true);
    }
    int row = 0;
    public void tampil_field(){
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String SQL = "SELECT no_mk FROM matakuliah WHERE nama_mk='"+tablemodel.getValueAt(row, 1).toString()+"'";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next()){
                String name = res.getString("no_mk");
                txt_kode_mk.setText(name);
            }
            stt.close();
            kon.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        row = tabel_nilai.getSelectedRow();
        txt_persen_absen.setText(tablemodel.getValueAt(row,2).toString());
        txt_persen_tugas.setText(tablemodel.getValueAt(row,3).toString());
        txt_persen_uts.setText(tablemodel.getValueAt(row,4).toString());
        txt_persen_uas.setText(tablemodel.getValueAt(row,5).toString());
        txt_kehadiran.setText(tablemodel.getValueAt(row,6).toString());
        txt_tugas1.setText(tablemodel.getValueAt(row,7).toString());
        txt_tugas2.setText(tablemodel.getValueAt(row,8).toString());
        txt_tugas3.setText(tablemodel.getValueAt(row,9).toString());
        txt_uts.setText(tablemodel.getValueAt(row,10).toString());
        txt_uas.setText(tablemodel.getValueAt(row,11).toString());
        btn_simpan.setEnabled(false);
        btn_ubah.setEnabled(true);
        btn_hapus.setEnabled(true);
        btn_batal.setEnabled(false);
        aktif_teks();
    }
    public int nilai_absen(){
        int prtmn = Integer.parseInt(txt_kehadiran.getText());
        float persen_absen = Float.parseFloat(txt_persen_absen.getText());
        int nilai = (int) (((prtmn/14)*100*persen_absen)/100);
        return nilai;
    }
    public int nilai_tugas(){
        int tugas1 = Integer.parseInt(txt_tugas1.getText());
        int tugas2 = Integer.parseInt(txt_tugas2.getText());
        int tugas3 = Integer.parseInt(txt_tugas3.getText());
        float persen_tgs = Float.parseFloat(txt_persen_tugas.getText());
        int nilai = (int) ((int) ((tugas1 + tugas2 + tugas3)/3)*(persen_tgs/100));
        return nilai;
    }
    public int nilai_uts(){
        int uts = Integer.parseInt(txt_uts.getText());
        float persen_uts = Float.parseFloat(txt_persen_uts.getText());
        int nilai = (int) (uts * (persen_uts/100));
        return nilai;
    }
    public int nilai_uas(){
        int uas = Integer.parseInt(txt_uas.getText());
        float persen_uas = Float.parseFloat(txt_persen_uas.getText());
        int nilai = (int) (uas * (persen_uas/100));
        return nilai;
    }
    public int nilai_akhir(){
        int nilai = nilai_absen() + nilai_tugas() + nilai_uts() + nilai_uas();
        return nilai;
    }
    public char indeks(int nilai){
        char indeks;
        if (nilai >= 80 && nilai <= 100){
            indeks = 'A';
        }
        else if (nilai < 80 && nilai > 69) {
            indeks = 'B';
        }
        else if (nilai < 68 && nilai > 46) {
            indeks = 'C';
        }
        else if (nilai < 56 && nilai > 69) {
            indeks = 'D';
        }
        else {
            indeks = 'E';
        }
        return indeks;
    }
    public String keterangan(char indeks){
        String ket = "Tidak Lulus";
        if (indeks == 'A' || indeks == 'B' || indeks == 'C'){
            ket = "Lulus";
        }
        return ket;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_kode_mk = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_persen_absen = new javax.swing.JTextField();
        combo_nama_matkul = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txt_persen_tugas = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_persen_uts = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_persen_uas = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txt_kehadiran = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txt_tugas1 = new javax.swing.JTextField();
        txt_tugas3 = new javax.swing.JTextField();
        txt_tugas2 = new javax.swing.JTextField();
        txt_uts = new javax.swing.JTextField();
        txt_uas = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_nilai = new javax.swing.JTable();
        btn_tambah = new javax.swing.JButton();
        btn_ubah = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        btn_keluar = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jButton4.setText("Hapus");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("FORM SIMULASI NILAI AKHIR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(495, 495, 495)
                .addComponent(jLabel1)
                .addContainerGap(509, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Nama Mata Kuliah");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Kode MK");

        txt_kode_mk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kode_mkActionPerformed(evt);
            }
        });
        txt_kode_mk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_kode_mkKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Persentase Absen");

        combo_nama_matkul.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                combo_nama_matkulPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Persentase Tugas");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Persentase UTS");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Persentase UAS");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("%");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("%");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("%");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("%");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Kehadiran");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Tugas 1");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Tugas 2");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Tugas 3");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("UTS");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("UAS");

        txt_kehadiran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_kehadiranKeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Pertemuan");

        tabel_nilai.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_nilai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_nilaiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_nilai);

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
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1052, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(btn_tambah)
                        .addGap(76, 76, 76)
                        .addComponent(btn_ubah)
                        .addGap(80, 80, 80)
                        .addComponent(btn_hapus)
                        .addGap(80, 80, 80)
                        .addComponent(btn_simpan)
                        .addGap(78, 78, 78)
                        .addComponent(btn_batal)
                        .addGap(83, 83, 83)
                        .addComponent(btn_keluar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(414, 414, 414)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(combo_nama_matkul, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_kode_mk, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_persen_tugas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_persen_uas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_persen_uts, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_persen_absen, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_uas, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_uts, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_tugas2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_tugas3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_tugas1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(46, 46, 46)
                                        .addComponent(txt_kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(combo_nama_matkul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_kode_mk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_persen_absen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12)
                    .addComponent(txt_kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_persen_tugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel14)
                    .addComponent(txt_tugas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txt_persen_uts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel15))
                    .addComponent(txt_tugas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_persen_uas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel16)
                    .addComponent(txt_tugas3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txt_uts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txt_uas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tambah)
                    .addComponent(btn_ubah)
                    .addComponent(btn_hapus)
                    .addComponent(btn_simpan)
                    .addComponent(btn_batal)
                    .addComponent(btn_keluar))
                .addGap(0, 83, Short.MAX_VALUE))
        );

        setBounds(0, 0, 1296, 759);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_kode_mkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kode_mkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kode_mkActionPerformed

    private void tabel_nilaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_nilaiMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 1){
            tampil_field();
        }
    }//GEN-LAST:event_tabel_nilaiMouseClicked

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        membersihkan_teks();
        txt_kode_mk.requestFocus();
        btn_simpan.setEnabled(true);
        btn_ubah.setEnabled(false);
        btn_hapus.setEnabled(false);
        btn_keluar.setEnabled(false);
        btn_batal.setEnabled(true);
        aktif_teks();
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void combo_nama_matkulPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_combo_nama_matkulPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        String item = (String) combo_nama_matkul.getSelectedItem();
        String SQL = "SELECT * FROM matakuliah WHERE nama_mk = ?";
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            PreparedStatement pst = kon.prepareStatement(SQL);
            pst.setString(1, item);
            ResultSet res = pst.executeQuery();
            if(res.next()){
                String kode = res.getString("no_mk");
                txt_kode_mk.setText(kode);
            }
            pst.close();
            kon.close();
        } 
        catch (Exception ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_combo_nama_matkulPopupMenuWillBecomeInvisible

    private void txt_kehadiranKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_kehadiranKeyReleased
        // TODO add your handling code here:
        setlimit();
    }//GEN-LAST:event_txt_kehadiranKeyReleased

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        String data[] = new String[19];
        
        if ((txt_kode_mk.getText().isEmpty())){
            JOptionPane.showMessageDialog(null,"kode tidak boleh kosong, silahkan dilengkapi!");
            txt_kode_mk.requestFocus();
        }
        else {
            try {
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database,user,pass);
                Statement stt = kon.createStatement();
                String SQL = "INSERT INTO nilai_akhir(kode_mk,"+"persen_absen,"+"persen_tugas,"+"persen_uts,"
                        +"persen_uas,"+"absensi,"+"tugas1,"+"tugas2,"+"tugas3,"+"uts,"+"uas,"+"nilai_absen,"+"nilai_tugas,"
                        +"nilai_uts,"+"nilai_uas,"+"nilai_akhir,"+"indeks,"+"keterangan)"+"VALUES"
                        +"( '"+txt_kode_mk.getText()+" ' ,"+" ' "+Float.parseFloat(txt_persen_absen.getText())+" ' ,"+" ' "
                        +Float.parseFloat(txt_persen_tugas.getText())+" ' ,"+" ' "+Float.parseFloat(txt_persen_uts.getText())+" ' ,"+" ' "
                        +Float.parseFloat(txt_persen_uas.getText())+" ' ,"+" ' "+Integer.parseInt(txt_kehadiran.getText())+" ' ,"+" ' "
                        +Integer.parseInt(txt_tugas1.getText())+" ' ,"+" ' "+Integer.parseInt(txt_tugas2.getText())+" ' ,"+" ' "
                        +Integer.parseInt(txt_tugas3.getText())+" ' ,"+" ' "+Integer.parseInt(txt_uts.getText())+" ' ,"+" ' "
                        +Integer.parseInt(txt_uas.getText())+" ' ,"+" ' "+nilai_absen()+" ' ,"+" ' "
                        +nilai_tugas()+" ' ,"+" ' "+nilai_uts()+" ' ,"+" ' "
                        +nilai_uas()+" ' ,"+" ' "+nilai_akhir()+" ' ,"+" ' "
                        +indeks(nilai_akhir())+" ' ,"+" ' "+keterangan(indeks(nilai_akhir()))+"' )";
                stt.executeUpdate(SQL);
                data[1] = txt_kode_mk.getText();
                data[2] = txt_persen_absen.getText();
                data[3] = txt_persen_tugas.getText();
                data[4] = txt_persen_uts.getText();
                data[5] = txt_persen_uas.getText();
                data[6] = txt_kehadiran.getText();
                data[7] = txt_tugas1.getText();
                data[8] = txt_tugas2.getText();
                data[9] = txt_tugas3.getText();
                data[10] = txt_uts.getText();
                data[11] = txt_uas.getText();
                data[12] = String.valueOf(nilai_absen());
                data[13] = String.valueOf(nilai_tugas());
                data[14] = String.valueOf(nilai_uts());
                data[15] = String.valueOf(nilai_uas());
                data[16] = String.valueOf(nilai_akhir());
                data[17] = String.valueOf(indeks(nilai_akhir()));
                data[18] = keterangan(indeks(nilai_akhir()));
                tablemodel.insertRow(0, data);
                stt.close();
                kon.close();
                membersihkan_teks();
                btn_simpan.setEnabled(false);
                nonaktif_teks();
                tablemodel.setRowCount(0);
                settableload();
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        // TODO add your handling code here:
        nonaktif_teks();
        btn_ubah.setEnabled(true);
        btn_hapus.setEnabled(true);
        btn_keluar.setEnabled(true);
        membersihkan_teks();
    }//GEN-LAST:event_btn_batalActionPerformed

    private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
        // TODO add your handling code here:        
        if ((txt_kode_mk.getText().isEmpty())){
            JOptionPane.showMessageDialog(null,"data tidak boleh kosong, silahkan dilengkapi");
            txt_kode_mk.requestFocus();
        }
        else{
            try {
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database,user,pass);
                Statement stt = kon.createStatement();
                String SQL = "UPDATE `nilai_akhir` "+"SET `kode_mk`='"+txt_kode_mk.getText()+"',"
                        +"`persen_absen`='"+Float.parseFloat(txt_persen_absen.getText())+"',"
                        +"`persen_tugas`='"+Float.parseFloat(txt_persen_tugas.getText())+"',"
                        +"`persen_uts`='"+Float.parseFloat(txt_persen_uts.getText())+"',"
                        +"`persen_uas`='"+Float.parseFloat(txt_persen_uas.getText())+"',"
                        +"`absensi`='"+Integer.parseInt(txt_kehadiran.getText())+"',"
                        +"`tugas1`='"+Integer.parseInt(txt_tugas1.getText())+"',"
                        +"`tugas2`='"+Integer.parseInt(txt_tugas2.getText())+"',"
                        +"`tugas3`='"+Integer.parseInt(txt_tugas3.getText())+"',"
                        +"`uts`='"+Integer.parseInt(txt_uts.getText())+"',"
                        +"`uas`='"+Integer.parseInt(txt_uas.getText())+"',"
                        +"`nilai_absen`='"+nilai_absen()+"',"
                        +"`nilai_tugas`='"+nilai_tugas()+"',"
                        +"`nilai_uts`='"+nilai_uts()+"',"
                        +"`nilai_uas`='"+nilai_uas()+"',"
                        +"`nilai_akhir`='"+nilai_akhir()+"',"
                        +"`indeks`='"+indeks(nilai_akhir())+"',"
                        +"`keterangan`='"+keterangan(indeks(nilai_akhir()))+"'"
                        +"WHERE "+"`no`='"+tablemodel.getValueAt(row, 0).toString()+"';";
                stt.executeUpdate(SQL);
                data[1] = txt_kode_mk.getText();
                data[2] = txt_persen_absen.getText();
                data[3] = txt_persen_tugas.getText();
                data[4] = txt_persen_uts.getText();
                data[5] = txt_persen_uas.getText();
                data[6] = txt_kehadiran.getText();
                data[7] = txt_tugas1.getText();
                data[8] = txt_tugas2.getText();
                data[9] = txt_tugas3.getText();
                data[10] = txt_uts.getText();
                data[11] = txt_uas.getText();
                data[12] = String.valueOf(nilai_absen());
                data[13] = String.valueOf(nilai_tugas());
                data[14] = String.valueOf(nilai_uts());
                data[15] = String.valueOf(nilai_uas());
                data[16] = String.valueOf(nilai_akhir());
                data[17] = String.valueOf(indeks(nilai_akhir()));
                data[18] = keterangan(indeks(nilai_akhir()));
                tablemodel.setRowCount(0);
                settableload();
                stt.close();
                kon.close();
                membersihkan_teks();
                btn_simpan.setEnabled(false);
                nonaktif_teks();
            } 
            catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_btn_ubahActionPerformed

    private void btn_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_keluarActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new Frameutama().setVisible(true);
    }//GEN-LAST:event_btn_keluarActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            Statement stt = kon.createStatement();
            String SQL = "DELETE FROM nilai_akhir "+"WHERE "+"no='"+tablemodel.getValueAt(row, 0).toString()+"'";
            String ResetNo = "ALTER TABLE nilai_akhir DROP no";
            String ConsecutiveNo = "ALTER TABLE nilai_akhir ADD no INT NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST";
            stt.executeUpdate(SQL);
            stt.execute(ResetNo);
            stt.execute(ConsecutiveNo);
            tablemodel.removeRow(row);
            stt.close();
            kon.close();
            membersihkan_teks();
            tablemodel.setRowCount(0);
            settableload();
        } 
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void txt_kode_mkKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_kode_mkKeyReleased
        // TODO add your handling code here:
        String item = txt_kode_mk.getText();
        String SQL = "SELECT * FROM matakuliah WHERE no_mk = ?";
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database,user,pass);
            PreparedStatement pst = kon.prepareStatement(SQL);
            pst.setString(1, item);
            ResultSet res = pst.executeQuery();
            if(res.next()){
                String name = res.getString("nama_mk");
                combo_nama_matkul.setSelectedItem(name);
            }
            pst.close();
            kon.close();
        } 
        catch (Exception ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_txt_kode_mkKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        btn_hapus.setEnabled(false);
        btn_ubah.setEnabled(false);
        btn_simpan.setEnabled(false);
        btn_batal.setEnabled(false);
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(frm_sim_nilai_akhir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_sim_nilai_akhir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_sim_nilai_akhir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_sim_nilai_akhir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_sim_nilai_akhir().setVisible(true);
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
    private javax.swing.JComboBox<String> combo_nama_matkul;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tabel_nilai;
    private javax.swing.JTextField txt_kehadiran;
    private javax.swing.JTextField txt_kode_mk;
    private javax.swing.JTextField txt_persen_absen;
    private javax.swing.JTextField txt_persen_tugas;
    private javax.swing.JTextField txt_persen_uas;
    private javax.swing.JTextField txt_persen_uts;
    private javax.swing.JTextField txt_tugas1;
    private javax.swing.JTextField txt_tugas2;
    private javax.swing.JTextField txt_tugas3;
    private javax.swing.JTextField txt_uas;
    private javax.swing.JTextField txt_uts;
    // End of variables declaration//GEN-END:variables
}
