package com.sincerebakery.view;

import com.sincerebakery.entity.Product;
import com.sincerebakery.entity.User;
import com.sincerebakery.service.InventoryService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.NumberFormat;
import java.util.Locale;

public class InventoryForm extends javax.swing.JFrame {
    private final User currentUser;
    private final InventoryService service = new InventoryService();
    private final DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"ID", "Nama Produk", "Kategori", "Harga", "Stok"}, 0) {
        @Override public boolean isCellEditable(int row, int column) { return false; }
    };
    private final NumberFormat money = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    public InventoryForm() { this(null); }

    public InventoryForm(User user) {
        initComponents();
        currentUser = user;
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        tblInventory.setModel(tableModel);
        btnHapus.addActionListener(e -> deleteSelected());
        setLocationRelativeTo(null);
        loadData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInventory = new javax.swing.JTable();
        btnTambah = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("SINCERE BAKERY INVENTORY");

        tblInventory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nama Produk", "Kategori", "Harga", "Stok"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblInventory);

        btnTambah.setText("Tambah");
        btnTambah.addActionListener(this::btnTambahActionPerformed);

        btnEdit.setText("Edit");
        btnEdit.addActionListener(this::btnEditActionPerformed);

        btnKembali.setText("Kembali");
        btnKembali.addActionListener(this::btnKembaliActionPerformed);

        btnHapus.setText("Hapus");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(154, 154, 154))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnTambah)
                        .addGap(12, 12, 12)
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnKembali)
                        .addGap(37, 37, 37))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 146, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTambah)
                            .addComponent(btnEdit)
                            .addComponent(btnKembali)
                            .addComponent(btnHapus))
                        .addGap(30, 30, 30))))
        );

        pack();
    }
    // </editor-fold>

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {
        new ProductForm(0, null, this::loadData).setVisible(true);
    }

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {
        int row = tblInventory.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Pilih produk yang ingin diedit."); return; }
        int id = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
        try { new ProductForm(id, service.getProduct(id), this::loadData).setVisible(true); }
        catch (Exception e) { showError("Gagal mengambil data produk", e); }
    }

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {
        if (currentUser == null) new LoginFrame().setVisible(true);
        else new MainFrame(currentUser).setVisible(true);
        dispose();
    }

    private void deleteSelected() {
        int row = tblInventory.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Pilih produk yang ingin dihapus."); return; }
        int id = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
        int confirm = JOptionPane.showConfirmDialog(this, "Hapus produk ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try { service.deleteProduct(id); loadData(); }
            catch (Exception e) { showError("Gagal menghapus produk", e); }
        }
    }

    private void loadData() {
        try {
            tableModel.setRowCount(0);
            for (Product p : service.getProducts()) tableModel.addRow(new Object[]{p.getProductId(), p.getProductName(), p.getCategory(), money.format(p.getPrice()), p.getStock()});
        } catch (Exception e) { showError("Gagal memuat inventory", e); }
    }

    private void showError(String title, Exception e) {
        JOptionPane.showMessageDialog(this, title + ": " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new InventoryForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblInventory;
    // End of variables declaration//GEN-END:variables
}
