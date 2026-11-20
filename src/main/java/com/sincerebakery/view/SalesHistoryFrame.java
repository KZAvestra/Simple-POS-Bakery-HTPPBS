package com.sincerebakery.view;

import com.sincerebakery.entity.User;
import com.sincerebakery.service.TransactionService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.NumberFormat;
import java.util.*;
import java.util.Locale;
import java.util.Map;

public class SalesHistoryFrame extends javax.swing.JFrame {
    private final User user;
    private final TransactionService service = new TransactionService();
    private final DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Customer", "Tanggal", "Total", "Payment"}, 0) { @Override public boolean isCellEditable(int r,int c){return false;} };
    private final NumberFormat money = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    public SalesHistoryFrame() { this(null); }

    public SalesHistoryFrame(User user) {
        initComponents(); this.user = user;
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        tabelHistory.setModel(model);
        btnDetail.addActionListener(e -> showDetail());
        btnKembali.addActionListener(e -> { if (user == null) new LoginFrame().setVisible(true); else new MainFrame(user).setVisible(true); dispose(); });
        setLocationRelativeTo(null); loadHistory();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelHistory = new javax.swing.JTable();
        btnDetail = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("SINCERE BAKERY TRANSACTION HISTORY");

        tabelHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Cutomer", "Tanggal", "Total", "Payment"
            }
        ));
        jScrollPane1.setViewportView(tabelHistory);

        btnDetail.setText("Detail/Struk");

        btnKembali.setText("Kembali");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(btnDetail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnKembali)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDetail)
                    .addComponent(btnKembali))
                .addGap(20, 20, 20))
        );

        pack();
    }
    // </editor-fold>

    private void loadHistory() {
        try {
            model.setRowCount(0);
            for (Map<String,Object> row : service.getHistory()) model.addRow(new Object[]{row.get("id"), row.get("customer") == null ? "Umum" : row.get("customer"), row.get("date"), money.format((java.math.BigDecimal) row.get("total")), row.get("payment")});
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Gagal memuat riwayat: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
    }

    private void showDetail() {
        int row = tabelHistory.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Pilih transaksi terlebih dahulu."); return; }
        int id = Integer.parseInt(model.getValueAt(row, 0).toString());
        try {
            StringBuilder detail = new StringBuilder("DETAIL TRANSAKSI #" + id + "\n================================\n");
            for (Map<String,Object> item : service.getDetails(id)) detail.append(item.get("name")).append("\n").append(item.get("qty")).append(" x ").append(money.format((java.math.BigDecimal)item.get("price"))).append(" = ").append(money.format((java.math.BigDecimal)item.get("subtotal"))).append("\n\n");
            JTextArea area = new JTextArea(detail.toString(), 18, 36); area.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(area), "Detail / Struk", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Gagal memuat detail: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new SalesHistoryFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetail;
    private javax.swing.JButton btnKembali;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelHistory;
    // End of variables declaration//GEN-END:variables
}
