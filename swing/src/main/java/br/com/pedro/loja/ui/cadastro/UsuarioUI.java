package br.com.pedro.loja.ui.cadastro;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import br.com.pedro.loja.entity.UsuarioEntity;
import br.com.pedro.loja.service.UsuarioService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsuarioUI extends JPanel implements ActionListener, ListSelectionListener{
    
    private JButton btnSalvar = new JButton();
    private JButton btnLimpar = new JButton();
    private JButton btnExcluir = new JButton();
    private JLabel lblId = new JLabel();
    private JLabel lblNome = new JLabel();
    private JLabel lblEmail = new JLabel();
    private JLabel lblSenha = new JLabel();
    private JTextField txtId = new JTextField();
    private JTextField txtNome = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JPasswordField txtSenha = new JPasswordField();
    private String[] tituloColunas = {"ID","Nome","Email",""};
    private JTable tblUsuario = new JTable(){
        public boolean editCellAt(int row, int column, java.util.EventObject e) {  // n�o permitir edi��o
            return false;
         }
     };
    private JScrollPane scroll = new JScrollPane(tblUsuario);// barra de rolagem na tabela
    
    public UsuarioUI(){
        componentes();
    }

    void componentes(){
        this.setLayout(null); // layout � o programador que define a posi��o dos componentes
        int larguraPanel = this.getWidth();
        int alturaPanel = this.getHeight();

        lblId.setText("ID");  // configura o texto do componente
        lblId.setBounds(10,10,80,25); // coordenadas ( x, y, largura e altura )
        txtId.setBounds(10,30,80,25);
        txtId.setEnabled(false);  // desabilita edi��o
        lblNome.setText("Nome");
        lblNome.setBounds(10,60,80,25);
        txtNome.setBounds(10,80,80,25);
        lblEmail.setText("Email");
        lblEmail.setBounds(10, 120, 80, 25);
        txtEmail.setBounds(10, 140, 80, 25);
        lblSenha.setText("Senha");
        lblSenha.setBounds(10, 180, 80, 25);
        txtSenha.setBounds(10, 200, 80, 25);
        btnSalvar.setText("Salvar");
        btnSalvar.setBounds(120,200,80,25);
        btnLimpar.setText("Limpar");
        btnLimpar.setBounds(200,200,80,25);
        btnExcluir.setText("Excluir");
        btnExcluir.setBounds(280,200,80,25);
        scroll.setBounds(10, 250, 600, 400);
        btnSalvar.addActionListener(this); // configura captura de evento
        btnLimpar.addActionListener(this);
        btnExcluir.addActionListener(this);
        tblUsuario.getSelectionModel().addListSelectionListener(this);  // configura captura de evento na tabela
        tblUsuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // permite sele��o da linha da tabela
        
        
        
        this.add(lblId); // adiciona o componente no painel
        this.add(txtId);
        this.add(lblNome);
        this.add(txtNome);
        this.add(lblEmail);
        this.add(txtEmail);
        this.add(lblSenha);
        this.add(txtSenha);
        this.add(btnSalvar);
        this.add(btnLimpar);
        this.add(btnExcluir);
        this.add(scroll);
        atualizarTabela();
        txtNome.requestFocus();  // coloca o foco ( cursor ) no componente
        
    }


    /*
     * Atualizar conteúdo da tabela (Jtable) com os dados do banco de dados
     */
    public void atualizarTabela(){
        UsuarioService usuarioService = new UsuarioService();

        List<UsuarioEntity> lista = usuarioService.listar();
        DefaultTableModel modelo = (DefaultTableModel) tblUsuario.getModel();
        modelo.setColumnIdentifiers(tituloColunas); // configura t�tulos das colunas da tabela
        modelo.setRowCount(0); // remove todas as linhsa da tabela
        lista.forEach(usuario ->{ // percorre todos os objetos da lista
            modelo.addRow(new Object[]{ // adiciona os atributos do objeto a linha da tabela
                usuario.getUsuarioId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha()
            });
        });
        //tblUsuario.removeColumn(tblUsuario.getColumnModel().getColumn(3));

    }



    /*
     * Esse método é chamado a cada evento de click do mouse
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Command : " + e.getActionCommand());
        if ( e.getActionCommand().equals("Salvar")){
            UsuarioEntity usuarioEntity = new UsuarioEntity(
                Integer.valueOf("0"+txtId.getText()),
                txtNome.getText(),
                txtEmail.getText(),
                String.valueOf(txtSenha.getPassword())
                );
            UsuarioService usuarioService = new UsuarioService();
            usuarioService.salvar(usuarioEntity);
            atualizarTabela();
            return;
        }
        if ( e.getActionCommand().equals("Limpar")){
            txtId.setText("");
            txtNome.setText("");
            txtEmail.setText("");
            txtSenha.setText("");
            txtNome.requestFocus();
            return;
        }

        if( e.getActionCommand().equals("Excluir")){
            if ( txtId.getText().equals("") ){
                JOptionPane.showMessageDialog(this, "Esse registro n�o pode ser excluido");
                return;
            }
            if (JOptionPane.showConfirmDialog(this, "Confirma a exclus�o ?","Excluir",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.CANCEL_OPTION){
                return;
            }
            UsuarioService usuarioService = new UsuarioService();
            usuarioService.excluir(Integer.valueOf(txtId.getText()));
            atualizarTabela();
        }
        
    }


    /*
     * Esse m�todo � chamado ao clicar nas linhas da tabela
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println("Line :" + tblUsuario.getSelectedRow());

        if ( tblUsuario.getSelectedRow()<0) return;

        int linha = tblUsuario.getSelectedRow();
        txtId.setText(tblUsuario.getModel().getValueAt(linha, 0).toString());
        txtNome.setText(tblUsuario.getModel().getValueAt(linha, 1).toString());
        txtEmail.setText(tblUsuario.getModel().getValueAt(linha, 2).toString());
        txtSenha.setText(tblUsuario.getModel().getValueAt(linha, 3).toString());
        
    }
}
