package org.example.model.shapes;

import javax.swing.*;
import java.awt.*;

public abstract class Shape extends JComponent {

    protected Point startPoint, endPoint;
    protected boolean isBeingCreated = true;

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

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
        recalculate();
    }

    public void setBeingCreated(boolean beingCreated) {
        isBeingCreated = beingCreated;
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
        g2d.setColor(Color.BLACK);
    }
}
