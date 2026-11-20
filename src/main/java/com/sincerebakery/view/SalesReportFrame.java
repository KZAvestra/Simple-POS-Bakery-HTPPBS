package com.sincerebakery.view;

import com.sincerebakery.entity.User;
import com.sincerebakery.service.ReportService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.NumberFormat;
import java.util.*;
import java.util.Locale;
import java.util.Map;

public class SalesReportFrame extends javax.swing.JFrame {
    private final User user;
    private final ReportService service = new ReportService();
    private final DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Tanggal", "Total Pendapatan", "Jumlah Transaksi", "Ditutup Pada"}, 0) { @Override public boolean isCellEditable(int r,int c){return false;} };
    private final NumberFormat money = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    public SalesReportFrame() { this(null); }

    public SalesReportFrame(User user) {
        initComponents(); this.user = user;
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jTable1.setModel(model);
        jButton1.addActionListener(e -> closeToday());
        jButton2.addActionListener(e -> { if (user == null) new LoginFrame().setVisible(true); else new MainFrame(user).setVisible(true); dispose(); });
        setLocationRelativeTo(null); loadReports();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("SINCERE BAKERY DAILY SALES REPORT");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Tanggal", "Total Pendapatan", "Jumlah Transaksi", "Ditutup Pada"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Tutup Hari Ini");

        jButton2.setText("Kembali");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 728, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(23, 23, 23))
        );

        pack();
    }
    // </editor-fold>

    private void loadReports() {
        try {
            model.setRowCount(0);
            for (Map<String,Object> row : service.getReports()) model.addRow(new Object[]{row.get("id"), row.get("date"), money.format((java.math.BigDecimal)row.get("income")), row.get("count"), row.get("closed")});
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Gagal memuat laporan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
    }

    private void closeToday() {
        int confirm = JOptionPane.showConfirmDialog(this, "Tutup operasional hari ini? Setelah ditutup, transaksi baru tidak dapat diproses.", "Konfirmasi Tutup Hari", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;
        try {
            Map<String,Object> result = service.closeToday();
            JOptionPane.showMessageDialog(this, "Laporan harian berhasil ditutup.\nPendapatan: " + money.format((java.math.BigDecimal)result.get("income")) + "\nJumlah transaksi: " + result.get("count"), "Berhasil", JOptionPane.INFORMATION_MESSAGE);
            loadReports();
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Gagal menutup hari: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new SalesReportFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
