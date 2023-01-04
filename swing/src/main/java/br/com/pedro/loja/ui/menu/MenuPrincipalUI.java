package br.com.pedro.loja.ui.menu;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import br.com.pedro.LojaApp;
import br.com.pedro.loja.ui.cadastro.ProdutoUI;
import br.com.pedro.loja.ui.cadastro.UsuarioUI;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.EventObject;

public class MenuPrincipalUI implements ActionListener,MouseListener{
    
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;
    private JFrame frame;

    public MenuPrincipalUI(JFrame frame){
        this.frame = frame;
        menuBar = new JMenuBar(); // barra de menu

        menu = new JMenu("Cadastro"); // opção do menu
        menuBar.add(menu); // adicionando na barra de menu a opção Cadastro


        menuItem = new JMenuItem("Usuário", KeyEvent.VK_U); // item de menu
        menuItem.addActionListener(this);
        menu.add(menuItem); // adiciono o item a op��o do menu

        menuItem = new JMenuItem("Produto", KeyEvent.VK_P);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menu.addSeparator(); // linha separadora de itens de menu

        menuItem = new JMenuItem("Sair",KeyEvent.VK_S);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menu = new JMenu("Venda"); // op��o do menu
        menu.addMouseListener(this);
        menuBar.add(menu); // adicionando na barra de menu a op��o Cadastro


        menu = new JMenu("Sobre"); // op��o do menu
        menu.addMouseListener(this);
        menuBar.add(menu); // adicionando na barra de menu a op��o Cadastro

    }



    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public JMenu getMenu() {
        return menu;
    }

    public void setMenu(JMenu menu) {
        this.menu = menu;
    }

    public JMenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(JMenuItem menuItem) {
        this.menuItem = menuItem;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
       System.out.println("Command : " + e.getActionCommand());
       System.out.println("ID : " + e.getID());
       

        if(e.getActionCommand().equals("Usuario")){
            UsuarioUI usuario = new UsuarioUI();   // instancia o painel do cdastro de usu�rio 
            this.frame.getContentPane().removeAll(); // remove todos os pain�is do frame
            this.frame.getContentPane().add(usuario); // adiciona o painel do cadastro de usu�rio ao frame
            this.frame.validate();
            this.frame.repaint(); // atualiza o frame visualmente com os novos componentes
        }

        if(e.getActionCommand().equals("Produto")){
            ProdutoUI produto = new ProdutoUI();
            this.frame.getContentPane().removeAll();
            this.frame.getContentPane().add(produto);
            this.frame.validate();
            this.frame.repaint();
        }



       if ( e.getActionCommand().equals("Sair")){
            System.exit(0);  // encerra a aplica��o

       } 


    }



    @Override
    public void mouseClicked(MouseEvent e) {
        String opcaoSelecionada = ((JMenuItem)((JMenu)e.getSource())).getActionCommand().toString() ;
        if ( opcaoSelecionada.equals("Venda")){
            JOptionPane.showMessageDialog(null, "Não implementado !!!");
           }
            
           if( opcaoSelecionada.equals("Sobre")){
              JOptionPane.showMessageDialog(null, "Não implementado !!!");
              if( true ) return;
              String IMAGE_URL = "/resource/loja.png";
              JDialog sobre = new JDialog(this.frame,"Sobre");
              sobre.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
              try {
                sobre.add(new JLabel(new ImageIcon(ImageIO.read(LojaApp.class.getResourceAsStream(IMAGE_URL)))));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
              JLabel lblTexto = new JLabel("Programa para uma loja");
              sobre.add(lblTexto);
              sobre.setSize(200,200);
              sobre.validate();
              sobre.repaint();
              sobre.setVisible(true);
              
    
           }
    
    }



    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }



    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }



    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }



    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }






}
