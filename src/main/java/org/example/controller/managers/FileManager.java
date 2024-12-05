package org.example.controller.managers;

import org.example.model.Model;
import org.example.model.shapes.Shape;
import org.example.model.shapes.ShapeFactory;
import org.example.model.shapes.ShapeType;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileManager {

    private static final ShapeFactory shapeFactory = new ShapeFactory();

    public static JFileChooser getTXTFileChooser() {
        var fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save shapes");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files (*.txt)", "txt"));

        return fileChooser;
    }

    public static JFileChooser getPNGFileChooser() {
        var fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save shapes");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files (*.png)", "png"));

        return fileChooser;
    }

    public static void saveToTXTFile(Model model, File file) throws IOException {
        file = addExtensionIfAbsent(file, "txt");
        Files.writeString(Paths.get(file.getAbsolutePath()), model.getStringRepresentation());
    }

    public static void saveToPNGFile(Model model, File file) throws IOException {
        var image = new BufferedImage(1280, 720, BufferedImage.TYPE_INT_ARGB);
        var g2d = image.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, 1280, 720);

        model.getShapes()
                .forEach(shape -> shape.paintComponent(g2d));

        file = addExtensionIfAbsent(file, "png");
        ImageIO.write(image, "png", file);
    }

    public static List<Shape> readFile(File file) throws IOException {
        var lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
        return lines.stream()
                .map(FileManager::parseShape)
                .collect(Collectors.toList());

    }

    private static File addExtensionIfAbsent(File file, String extension) {
        if (!file.getName().endsWith(extension))
            file = new File(file.getAbsolutePath() + "." + extension);

        return file;
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
