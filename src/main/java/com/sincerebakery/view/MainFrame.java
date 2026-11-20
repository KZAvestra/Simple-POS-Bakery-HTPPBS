package com.sincerebakery.view;

import com.sincerebakery.entity.User;
import javax.swing.JOptionPane;

public class MainFrame extends javax.swing.JFrame {
    private final User currentUser;

    public MainFrame(User user) {
        initComponents();
        this.currentUser = user;
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        jLabel1.setText("SINCERE BAKERY - Selamat datang, " + user.getFullName());
        btnManageInventory.setVisible(user.isStockManager());
        jButton1.setVisible(user.isCashier());
        jButton2.addActionListener(e -> { new SalesHistoryFrame(user).setVisible(true); dispose(); });
        btnDailyReport.addActionListener(e -> { new SalesReportFrame(user).setVisible(true); dispose(); });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        btnManageInventory = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnDailyReport = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("SINCERE BAKERY");

        btnLogout.setText("LOGOUT");
        btnLogout.addActionListener(this::btnLogoutActionPerformed);

        btnManageInventory.setText("Kelola Inventory");
        btnManageInventory.addActionListener(this::btnManageInventoryActionPerformed);

        jButton1.setText("Transaksi");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setText("History");

        btnDailyReport.setText("Daily Report");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnLogout)
                .addGap(41, 41, 41))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(btnManageInventory))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jButton2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(btnDailyReport)))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(btnManageInventory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDailyReport)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(btnLogout)
                .addGap(33, 33, 33))
        );

        pack();
    }
    // </editor-fold>

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        int confirm = JOptionPane.showConfirmDialog(this, "Apakah kamu yakin ingin logout?", "Konfirmasi Logout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) { new LoginFrame().setVisible(true); dispose(); }
    }

    private void btnManageInventoryActionPerformed(java.awt.event.ActionEvent evt) {
        if (!currentUser.isStockManager()) return;
        new InventoryForm(currentUser).setVisible(true); dispose();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (!currentUser.isCashier()) return;
        new TransactionFrame(currentUser).setVisible(true); dispose();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new LoginFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDailyReport;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnManageInventory;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
