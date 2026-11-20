package com.sincerebakery.view;

import com.sincerebakery.entity.*;
import com.sincerebakery.service.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;
import java.util.List;

public class TransactionFrame extends javax.swing.JFrame {
    private final User user;
    private final InventoryService inventory = new InventoryService();
    private final CartService cart = new CartService();
    private final TransactionService transactions = new TransactionService();
    private final DefaultTableModel productsModel = new DefaultTableModel(new Object[]{"ID", "Nama Produk", "Kategori", "Harga", "Stok"}, 0) { @Override public boolean isCellEditable(int r,int c){return false;} };
    private final DefaultTableModel cartModel = new DefaultTableModel(new Object[]{"ID", "Nama Produk", "Jumlah", "Harga", "Subtotal"}, 0) { @Override public boolean isCellEditable(int r,int c){return false;} };
    private final NumberFormat money = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    public TransactionFrame() { this(null); }

    public TransactionFrame(User user) {
        initComponents();
        this.user = user;
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        if (user != null && !user.isCashier()) {
            JOptionPane.showMessageDialog(this, "Fitur transaksi hanya dapat diakses oleh kasir.", "Akses Ditolak", JOptionPane.WARNING_MESSAGE);
            dispose(); return;
        }
        tabelProduk.setModel(productsModel); tabelCart.setModel(cartModel);
        fieldTotal.setEditable(false); fieldTotal.setText("Rp0"); fieldJumlah.setText("1");
        btnAddtoCart.addActionListener(e -> addToCart());
        btnKembali.addActionListener(e -> { if (user == null) new LoginFrame().setVisible(true); else new MainFrame(user).setVisible(true); dispose(); });
        setLocationRelativeTo(null); loadProducts();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelProduk = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelCart = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        btnCheckout = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        fieldJumlah = new javax.swing.JTextField();
        btnAddtoCart = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();
        fieldTotal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("SINCERE BAKERY TRANSACTION");

        jLabel2.setText("Daftar Produk");

        jLabel3.setText("Cart");

        tabelProduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Produk", "Kategori", "Harga", "Stok"
            }
        ));
        jScrollPane1.setViewportView(tabelProduk);

        tabelCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Produk", "Jumlah", "Harga", "Subtotal"
            }
        ));
        jScrollPane2.setViewportView(tabelCart);

        jLabel4.setText("Total: ");

        btnCheckout.setText("Checkout");
        btnCheckout.addActionListener(this::btnCheckoutActionPerformed);

        btnHapus.setText("Hapus Item");
        btnHapus.addActionListener(this::btnHapusActionPerformed);

        jLabel5.setText("Jumlah");

        btnAddtoCart.setText("Tambah ke Cart");

        btnKembali.setText("Kembali");

        fieldTotal.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(355, 355, 355)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(175, 175, 175))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(7, 7, 7)
                                .addComponent(fieldJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAddtoCart)
                                .addGap(175, 175, 175)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHapus)
                                .addGap(64, 64, 64)
                                .addComponent(btnCheckout)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnKembali)))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(btnCheckout)
                    .addComponent(btnHapus)
                    .addComponent(jLabel5)
                    .addComponent(fieldJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddtoCart)
                    .addComponent(btnKembali)
                    .addComponent(fieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        pack();
    }
    // </editor-fold>

    private void btnCheckoutActionPerformed(java.awt.event.ActionEvent evt) { checkout(); }
    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) { removeFromCart(); }

    private void loadProducts() {
        try {
            productsModel.setRowCount(0);
            for (Product p : inventory.getProducts()) productsModel.addRow(new Object[]{p.getProductId(), p.getProductName(), p.getCategory(), money.format(p.getPrice()), p.getStock()});
        } catch (Exception e) { JOptionPane.showMessageDialog(this, e.getMessage(), "Gagal memuat produk", JOptionPane.ERROR_MESSAGE); }
    }

    private void addToCart() {
        int row = tabelProduk.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Pilih produk terlebih dahulu."); return; }
        try {
            int id = Integer.parseInt(productsModel.getValueAt(row, 0).toString());
            int qty = Integer.parseInt(fieldJumlah.getText().trim());
            Product p = inventory.getProduct(id); cart.add(p, qty); refreshCart();
        } catch (Exception e) { JOptionPane.showMessageDialog(this, e.getMessage(), "Input tidak valid", JOptionPane.WARNING_MESSAGE); }
    }

    private void removeFromCart() {
        int row = tabelCart.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Pilih item pada keranjang."); return; }
        cart.remove(Integer.parseInt(cartModel.getValueAt(row, 0).toString())); refreshCart();
    }

    private void refreshCart() {
        cartModel.setRowCount(0); BigDecimal total = BigDecimal.ZERO;
        for (CartItem item : cart.getItems()) {
            cartModel.addRow(new Object[]{item.getProduct().getProductId(), item.getProduct().getProductName(), item.getQuantity(), money.format(item.getProduct().getPrice()), money.format(item.getSubtotal())});
            total = total.add(item.getSubtotal());
        }
        fieldTotal.setText(money.format(total));
    }

    private void checkout() {
        List<CartItem> items = cart.getItems();
        if (items.isEmpty()) { JOptionPane.showMessageDialog(this, "Keranjang masih kosong."); return; }
        String[] options = {"Tunai", "QRIS", "Debit"};
        int choice = JOptionPane.showOptionDialog(this, "Pilih metode pembayaran", "Pembayaran", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (choice < 0) return;
        Payment payment = choice == 0 ? new CashPayment() : choice == 1 ? new QRISPayment() : new DebitPayment();
        try {
            int id = transactions.checkout(items, payment, user.getUserId());
            StringBuilder receipt = new StringBuilder("SINCERE BAKERY\n================================\nTransaksi: #" + id + "\nPembayaran: " + payment.getType() + "\n--------------------------------\n");
            BigDecimal total = BigDecimal.ZERO;
            for (CartItem item : items) { receipt.append(item.getProduct().getProductName()).append(" x ").append(item.getQuantity()).append(" = ").append(money.format(item.getSubtotal())).append("\n"); total = total.add(item.getSubtotal()); }
            receipt.append("--------------------------------\nTOTAL: ").append(money.format(total)).append("\nTerima kasih!");
            JTextArea area = new JTextArea(receipt.toString(), 18, 36); area.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(area), "Transaksi Berhasil - Struk", JOptionPane.INFORMATION_MESSAGE);
            cart.clear(); refreshCart(); loadProducts();
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Transaksi gagal: " + e.getMessage(), "Checkout Error", JOptionPane.ERROR_MESSAGE); loadProducts(); }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new TransactionFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddtoCart;
    private javax.swing.JButton btnCheckout;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKembali;
    private javax.swing.JTextField fieldJumlah;
    private javax.swing.JTextField fieldTotal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabelCart;
    private javax.swing.JTable tabelProduk;
    // End of variables declaration//GEN-END:variables
}
