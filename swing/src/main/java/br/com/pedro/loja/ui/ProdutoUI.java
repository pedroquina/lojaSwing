package br.com.pedro.loja.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProdutoUI extends JPanel {
    
    JLabel lblProduto = new JLabel();

    public ProdutoUI(){
        componentes();
    }


    void componentes(){
        this.setLayout(null);
        lblProduto.setText("Produto");
        lblProduto.setBounds(10,10,80,25);

        this.add(lblProduto);
    }
}
