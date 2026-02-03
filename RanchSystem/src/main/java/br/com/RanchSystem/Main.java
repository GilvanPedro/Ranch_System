package br.com.RanchSystem;

import br.com.RanchSystem.Controller.DespesaController;
import br.com.RanchSystem.View.MainView;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainView mainView = new MainView();
            mainView.setVisible(true);
        });
    }
}
