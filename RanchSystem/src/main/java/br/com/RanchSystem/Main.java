package br.com.RanchSystem;

import br.com.RanchSystem.View.MainView;

import javax.swing.SwingUtilities;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(() -> {
            MainView mainView = new MainView();
            mainView.setVisible(true);
        });
    }
}
