package org.example.controller.managers;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {

    public static JFileChooser getFileChooser() {
        var fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save table");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files (*.txt)", "txt"));

        return fileChooser;
    }

    public static void saveToFile(String content, File file) throws IOException {
        if (!file.getName().endsWith(".txt"))
            file = new File(file.getAbsolutePath() + ".txt");

        Files.writeString(Paths.get(file.getAbsolutePath()), content);
    }
}
