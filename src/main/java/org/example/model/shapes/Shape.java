package org.example.model.shapes;

import javax.swing.*;
import java.awt.*;

public abstract class Shape extends JComponent {

    protected Point startPoint, endPoint;
    protected boolean isBeingCreated = true;
    protected boolean isSelected = false;
    protected Color contourColor = Color.BLACK;

    public Shape(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    @Override
    public void paintComponent(Graphics g) {
        var g2d = (Graphics2D) g;
        if (isBeingCreated)
            drawWithStroke(g2d);
        else
            draw(g2d);
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
        recalculate();
    }

    public void setBeingCreated(boolean beingCreated) {
        isBeingCreated = beingCreated;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        contourColor = selected ? Color.RED : Color.BLACK;
    }

    protected void drawWithStroke(Graphics2D g2d) {
        setStroke(g2d);
        draw(g2d);
    }
    protected abstract void draw(Graphics2D g2d);
    protected abstract void recalculate();

    protected void setStroke(Graphics2D g2d) {
        float[] dashPattern = {10, 10};
        g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));
        g2d.setColor(contourColor);
    }

    @Override
    public String toString() {
        return String.format("%s{x1=%d, y1=%d, x2=%d, y2=%d}", getClass().getSimpleName(), startPoint.x, startPoint.y, endPoint.x, endPoint.y);
    }
}
