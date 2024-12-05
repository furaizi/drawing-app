package org.example.controller.managers;

import org.example.model.shapes.Shape;
import org.example.model.shapes.ShapeFactory;
import org.example.model.shapes.ShapeType;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

public class FileManager {

    private static final ShapeFactory shapeFactory = new ShapeFactory();

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

    public static List<Shape> readFile(File file) throws IOException {
        var lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
        return lines.stream()
                .map(FileManager::parseShape)
                .collect(Collectors.toList());

    }

    private static Shape parseShape(String shapeString) {
        String[] parts = shapeString.split("\\{|, |}");
        var shapeTypeName = parts[0];

        var x1 = Integer.parseInt(parts[1].split("=")[1]);
        var y1 = Integer.parseInt(parts[2].split("=")[1]);
        var x2 = Integer.parseInt(parts[3].split("=")[1]);
        var y2 = Integer.parseInt(parts[4].split("=")[1]);

        var shapeType = ShapeType.fromName(shapeTypeName)
                                 .orElseThrow(() -> new RuntimeException("Error while parsing shape type from file"));
        return shapeFactory.create(shapeType, new Point(x1, y1), new Point(x2, y2));
    }
}
