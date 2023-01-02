package br.com.pedro;


import java.awt.Dimension;

import javax.swing.JFrame;

import br.com.pedro.ui.MenuPrincipalUI;

/**
 * Hello world!
 */
public final class Loja {
    private Loja() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
            JFrame frame = new JFrame("Loja");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            MenuPrincipalUI menu = new MenuPrincipalUI(frame);
            frame.setJMenuBar(menu.getMenuBar());
            
            frame.setPreferredSize(new Dimension(800,800));
            frame.pack();
            frame.setVisible(true);
        
    }
}
