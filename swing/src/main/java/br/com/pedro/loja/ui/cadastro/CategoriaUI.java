package br.com.pedro.loja.ui.cadastro;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.pedro.loja.entity.CategoriaEntity;
import br.com.pedro.loja.service.CategoriaService;

public class CategoriaUI extends JPanel {
    
    private JLabel lblTitulo = new JLabel();
    private JLabel lblId = new JLabel();
    private JLabel lblNome = new JLabel();
    private JLabel lblData = new JLabel();
    private JTextField txtId = new JTextField();
    private JTextField txtNome = new JTextField();
    private JTextField txtData = new JTextField();
    private JButton btnSalvar = new JButton();
    private JButton btnLimpar = new JButton();
    private JButton btnExcluir = new JButton();
    private String[] tituloColunas = { "ID", "NOME", "DATA"};
    private JTable tblTabela = new JTable(){
        public boolean editCellAt(int row, int column, java.util.EventObject e) {  // n?o permitir edi??o
            return false;
        }
        };
    private JScrollPane scroll = new JScrollPane(tblTabela);


    public CategoriaUI(){
        componentes();
    }

    private void componentes(){
        this.setLayout(null);
        this.setSize(300, 300);
        lblTitulo.setText("CATEGORIA");
        lblTitulo.setBounds(130,10,100,25);
        this.add(lblTitulo);
        lblId.setText("ID");
        lblId.setBounds(10, 40, 20, 25);
        this.add(lblId);
        txtId.setBounds(30, 40, 50, 25);
        txtId.setEditable(false);
        this.add(txtId);
        lblNome.setText("Nome");
        lblNome.setBounds(120, 40, 50, 25);
        txtNome.setBounds(170, 40, 150, 25);
        this.add(lblNome);
        this.add(txtNome);
        lblData.setText("Data");
        lblData.setBounds(10, 80, 50, 25);
        txtData.setBounds(50, 80, 100, 25);
        this.add(lblData);
        this.add(txtData);
        btnSalvar.setText("Salvar");
        btnSalvar.setBounds(170, 80, 80, 25);
        btnLimpar.setText("Limpar");
        btnLimpar.setBounds(270, 80, 80, 25);
        btnExcluir.setText("Excluir");
        btnExcluir.setBounds(370, 80, 80, 25);
        this.add(btnSalvar);
        this.add(btnLimpar);
        this.add(btnExcluir);
        scroll.setBounds(10, 120, 450, 250);
        this.add(scroll);
        
        
        atualizarTabela();

    }

    private void atualizarTabela(){
        CategoriaService categoriaService = new CategoriaService();

        List<CategoriaEntity> lista = categoriaService.listar();


        DefaultTableModel modelo = (DefaultTableModel) tblTabela.getModel();
        modelo.setColumnIdentifiers(tituloColunas); // configura ttulos das colunas da tabela
        modelo.setRowCount(0); // remove todas as linhsa da tabela
        lista.forEach(categoria ->{ // percorre todos os objetos da lista
                //System.out.println( categoria.getCategoriaId() + " : " + categoria.getNome() + " : " + categoria.getCriado() );
                modelo.addRow(new Object[]{ // adiciona os atributos do objeto a linha da tabela
                    categoria.getCategoriaId(),
                    categoria.getNome(),
                    categoria.getCriado()
                });
        });            


    }

}
