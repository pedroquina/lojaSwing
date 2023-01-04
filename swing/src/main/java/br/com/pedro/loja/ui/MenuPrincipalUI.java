package br.com.pedro.loja.ui;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipalUI implements ActionListener {
    
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;
    private JFrame frame;

    public MenuPrincipalUI(JFrame frame){
        this.frame = frame;
        menuBar = new JMenuBar(); // barra de menu
        menu = new JMenu("Cadastro"); // opção do menu
        menuBar.add(menu); // adicionando na barra de menu a opção Cadastro

        menuItem = new JMenuItem("Usuario", KeyEvent.VK_U); // item de menu
        menuItem.addActionListener(this);
        menu.add(menuItem); // adiciono o item a opção do menu

        menuItem = new JMenuItem("Produto", KeyEvent.VK_P);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menu.addSeparator(); // linha separadora de itens de menu

        menuItem = new JMenuItem("Sair",KeyEvent.VK_S);
        menuItem.addActionListener(this);
        menu.add(menuItem);

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
            UsuarioUI usuario = new UsuarioUI();   // instancia o painel do cdastro de usuário 
            this.frame.getContentPane().removeAll(); // remove todos os painéis do frame
            this.frame.getContentPane().add(usuario); // adiciona o painel do cadastro de usuário ao frame
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
            System.exit(0);  // encerra a aplicação

       } 
        
    }



}
