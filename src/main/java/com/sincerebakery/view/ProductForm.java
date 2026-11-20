package com.sincerebakery.view;

import com.sincerebakery.entity.Product;
import com.sincerebakery.service.InventoryService;
import javax.swing.JOptionPane;

public class ProductForm extends javax.swing.JFrame {
    private final InventoryService service = new InventoryService();
    private int productId = 0;
    private Runnable onSave = () -> {};

    public ProductForm() { this(0, null, null); }

    public ProductForm(int id, Product product, Runnable callback) {
        initComponents();
        productId = id;
        if (callback != null) onSave = callback;
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        if (product != null) {
            fieldNamaProduk.setText(product.getProductName());
            fieldKategori.setText(product.getCategory());
            fieldHarga.setText(product.getPrice().toPlainString());
            fieldStok.setText(String.valueOf(product.getStock()));
        }
        btnSimpan.addActionListener(e -> saveProduct());
        btnBatal.addActionListener(e -> dispose());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fieldNamaProduk = new javax.swing.JTextField();
        fieldKategori = new javax.swing.JTextField();
        fieldHarga = new javax.swing.JTextField();
        fieldStok = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("DATA PRODUK");

        jLabel2.setText("Nama Produk");

        jLabel3.setText("Kategori");

        jLabel4.setText("Harga");

        jLabel5.setText("Stok");

        fieldNamaProduk.addActionListener(this::fieldNamaProdukActionPerformed);

        fieldKategori.addActionListener(this::fieldKategoriActionPerformed);

        fieldHarga.addActionListener(this::fieldHargaActionPerformed);

        fieldStok.addActionListener(this::fieldStokActionPerformed);

        btnSimpan.setText("Simpan");

        btnBatal.setText("Batal");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(fieldNamaProduk, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                .addComponent(fieldKategori)
                                .addComponent(fieldHarga)
                                .addComponent(fieldStok))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSimpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBatal)))))
                .addGap(80, 80, 80))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(fieldNamaProduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fieldKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fieldHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(fieldStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnBatal))
                .addGap(31, 31, 31))
        );

        pack();
    }
    // </editor-fold>

    private void saveProduct() {
        try {
            service.saveProduct(productId, fieldNamaProduk.getText(), fieldKategori.getText(), fieldHarga.getText(), fieldStok.getText());
            JOptionPane.showMessageDialog(this, "Data produk berhasil disimpan.", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
            onSave.run(); dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Gagal menyimpan produk", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void fieldNamaProdukActionPerformed(java.awt.event.ActionEvent evt) { fieldKategori.requestFocus(); }
    private void fieldKategoriActionPerformed(java.awt.event.ActionEvent evt) { fieldHarga.requestFocus(); }
    private void fieldHargaActionPerformed(java.awt.event.ActionEvent evt) { fieldStok.requestFocus(); }
    private void fieldStokActionPerformed(java.awt.event.ActionEvent evt) { saveProduct(); }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new ProductForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JTextField fieldHarga;
    private javax.swing.JTextField fieldKategori;
    private javax.swing.JTextField fieldNamaProduk;
    private javax.swing.JTextField fieldStok;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
