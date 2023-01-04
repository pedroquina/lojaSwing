package br.com.pedro;


import java.awt.Dimension;

import javax.swing.JFrame;

import br.com.pedro.loja.ui.MenuPrincipalUI;

/**
 * Hello world!
 */
public final class LojaApp {
    private LojaApp() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
            JFrame frame = new JFrame("Loja");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // define comportamento do botão "X"
            
            MenuPrincipalUI menu = new MenuPrincipalUI(frame); // instanciam objeto da classe MenuPrincipalUI
            frame.setJMenuBar(menu.getMenuBar());
            
            frame.setPreferredSize(new Dimension(800,800)); // tamanho do frame em largura e altura em pixels
            frame.pack();
            frame.setVisible(true); // faz o frame ser apresentado
        
    }
}
