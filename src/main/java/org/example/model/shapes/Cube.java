package org.example.model.shapes;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Cube extends Shape {

    private int rectX, rectY, width, height, xShift, yShift;

    public Cube(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
        recalculate();
    }

    @Override
    protected void draw(Graphics2D g2d) {
        g2d.setColor(contourColor);
        drawRectangles(g2d);
        drawLines(g2d);
    }

    private void drawRectangles(Graphics2D g2d) {
        g2d.draw(new Rectangle2D.Double(rectX, rectY, width, height));
        g2d.draw(new Rectangle2D.Double(rectX + xShift, rectY - yShift, width, height));
    }

    private void drawLines(Graphics2D g2d) {
        g2d.drawLine(startPoint.x, startPoint.y, startPoint.x + xShift, startPoint.y - yShift);
        g2d.drawLine(startPoint.x, endPoint.y, startPoint.x + xShift, endPoint.y - yShift);
        g2d.drawLine(endPoint.x, endPoint.y, endPoint.x + xShift, endPoint.y - yShift);
        g2d.drawLine(endPoint.x, startPoint.y, endPoint.x + xShift, startPoint.y - yShift);
    }

    @Override
    protected void recalculate() {
        rectX = Math.min(startPoint.x, endPoint.x);
        rectY = Math.min(startPoint.y, endPoint.y);

        width = Math.abs(endPoint.x - startPoint.x);
        height = Math.abs(endPoint.y - startPoint.y);

        xShift = (int) (width * 0.25);
        yShift = (int) (height * 0.25);
    }

}
