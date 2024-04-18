package com.pedro.hud;


import com.pedro.service.FileService;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class HudService {

    private FileService fileService = new FileService();

    public List<String> listOfAllRenameds = new ArrayList<>();

    public HudService() {}

    public HudService(FileService fileService) {
        this.fileService = fileService;
    }

    public int askIfCanStart() {
        return JOptionPane.showConfirmDialog(null, "Todos os arquivos .pdf serão alterados\n "
                + "\nDeseja continuar?");
    }

    public void showDirectoryCreated() {
        JOptionPane.showMessageDialog(null, "A pasta não foi encontrada"
                + "\nTente colocar nesta que foi aberta!",
                "Diretório não encontrado", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showReport() {
        SwingUtilities.invokeLater((Runnable) () -> {
            JFrame frame = new JFrame("Relatório de Mudanças");
            JTextArea textArea = new JTextArea();


            // Definindo a fonte e o tamanho
            Font novaFonte = new Font("Arial", Font.PLAIN, 14);
            textArea.setFont(novaFonte);

            JScrollPane scrollPane = new JScrollPane(textArea);
            frame.add(scrollPane);

            frame.setSize(1000, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            printChanges(textArea);
        });
    }

    private void printChanges(JTextArea textArea) {
        for (String fileRenamed : getListOfAllRenameds()) {
            textArea.append(fileRenamed+"\n");
        }

        textArea.append("Finalizado com êxito! "
                + "\nFeche esta aba para encerrar.");
    }

    public List<String> getListOfAllRenameds() {
        return listOfAllRenameds;
    }

    public void setListOfAllRenameds(List<String> listOfAllRenameds) {
        this.listOfAllRenameds = listOfAllRenameds;
    }
}
