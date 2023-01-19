package br.com.pedro.loja.ui.cadastro;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.pedro.loja.entity.CategoriaEntity;
import br.com.pedro.loja.service.CategoriaService;

public class CategoriaUI extends JPanel implements ActionListener, ListSelectionListener{
    
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
    private JTable tblCategoria = new JTable(){
        public boolean editCellAt(int row, int column, java.util.EventObject e) {  // n?o permitir edi??o
            return false;
        }
        };
    private JScrollPane scroll = new JScrollPane(tblCategoria);


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
        txtData.setBounds(50, 80, 150, 25);
        txtData.setEditable(false);
        this.add(lblData);
        this.add(txtData);
        btnSalvar.setText("Salvar");
        btnSalvar.setBounds(220, 80, 80, 25);
        btnLimpar.setText("Limpar");
        btnLimpar.setBounds(320, 80, 80, 25);
        btnExcluir.setText("Excluir");
        btnExcluir.setBounds(420, 80, 80, 25);
        this.add(btnSalvar);
        this.add(btnLimpar);
        this.add(btnExcluir);
        scroll.setBounds(10, 120, 450, 250);
        this.add(scroll);
        // Listeners de eventos
        tblCategoria.getSelectionModel().addListSelectionListener(this);
        btnSalvar.addActionListener(this);
        btnExcluir.addActionListener(this);
        btnLimpar.addActionListener(this);
        
        
        atualizarTabela();

    }

    private void atualizarTabela(){
        CategoriaService categoriaService = new CategoriaService();

        List<CategoriaEntity> lista = categoriaService.listar();


        DefaultTableModel modelo = (DefaultTableModel) tblCategoria.getModel();
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

    @Override
    public void valueChanged(ListSelectionEvent e) {
       
        if ( tblCategoria.getSelectedRow()<0) return;

        int linha = tblCategoria.getSelectedRow();

        txtId.setText(tblCategoria.getModel().getValueAt(linha, 0).toString());
        txtNome.setText(tblCategoria.getModel().getValueAt(linha, 1).toString());
        txtData.setText(tblCategoria.getModel().getValueAt(linha,2).toString());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        System.out.println("Objeto : " + e.getActionCommand());

        if ( e.getActionCommand().equals("Limpar") ) {
            limparComponentes();
            return;
        }

        if ( e.getActionCommand().equals("Excluir")){
            if ( txtId.getText().equals("") ){
                JOptionPane.showMessageDialog(this, "Esse registro não pode ser excluído");
                return;
            }
            if (JOptionPane.showConfirmDialog(this, "Confirma a exclusão ?","Excluir",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.CANCEL_OPTION){
                return;
            }
            CategoriaService categoriaService = new CategoriaService();
            categoriaService.excluir( Integer.valueOf(txtId.getText()) );
            atualizarTabela();
            limparComponentes();
        }

        if ( e.getActionCommand().equals("Salvar")){

            CategoriaEntity categoriaEntity = new CategoriaEntity();
            categoriaEntity.setCategoriaId(Integer.valueOf("0"+txtId.getText()));
            categoriaEntity.setNome(txtNome.getText());
            // conversão String para LocalDateTime
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            //LocalDateTime dateTime = LocalDateTime.parse(txtData.getText(), formatter);            
            //categoriaEntity.setCriado( dateTime );

            CategoriaService categoriaService = new CategoriaService();
            categoriaEntity = categoriaService.salvar(categoriaEntity);
            atualizarTabela();
            limparComponentes();
        }
        
    }


    private void limparComponentes(){
        txtId.setText("");
        txtNome.setText("");
        txtData.setText("");
        txtNome.requestFocus(); // coloca o cursor no objeto
    }
}
