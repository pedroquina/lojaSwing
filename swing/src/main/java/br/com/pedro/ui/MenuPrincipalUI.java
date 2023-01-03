package br.com.pedro.ui;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipalUI implements ActionListener{
    
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;
    private JFrame frame;

    public MenuPrincipalUI(JFrame frame){
        this.frame = frame;
        menuBar = new JMenuBar();
        menu = new JMenu("Cadastro");
        menuBar.add(menu);

        menuItem = new JMenuItem("Usuario", KeyEvent.VK_U);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem("Produto", KeyEvent.VK_P);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menu.addSeparator();

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
            UsuarioUI usuario = new UsuarioUI();
            this.frame.getContentPane().removeAll();
            this.frame.getContentPane().add(usuario);
            this.frame.validate();
            this.frame.repaint();
        }

        if(e.getActionCommand().equals("Produto")){
            ProdutoUI produto = new ProdutoUI();
            this.frame.getContentPane().removeAll();
            this.frame.getContentPane().add(produto);
            this.frame.validate();
            this.frame.repaint();
        }



       if ( e.getActionCommand().equals("Sair")){
            System.exit(0);

       } 
        
    }

}
