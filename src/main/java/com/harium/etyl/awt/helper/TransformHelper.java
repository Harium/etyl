package com.harium.etyl.awt.helper;

import com.harium.etyl.commons.layer.Layer;

import java.awt.geom.AffineTransform;

public class TransformHelper {

    public static AffineTransform getTransform(Layer layer) {
        return getTransform(layer, 0, 0);
    }

    public static AffineTransform getTransform(Layer layer, float offsetX, float offsetY) {
        AffineTransform transform = new AffineTransform();

        float px = layer.getX() + offsetX;
        float py = layer.getY() + offsetY;

        transform.translate(px + layer.getOriginX(), py + layer.getOriginY());

        // Scale
        if (layer.getScaleX() != 1 || layer.getScaleY() != 1) {
            transform.scale(layer.getScaleX(), layer.getScaleY());
        }

        // Rotate
        if (layer.getAngle() != 0) {
            if ((layer.getScaleY() > 0 && layer.getScaleX() > 0)
                    || (layer.getScaleX() < 0 && layer.getScaleY() < 0)) {
                transform.rotate(Math.toRadians(layer.getAngle()));
            } else {
                transform.rotate(Math.toRadians(-layer.getAngle()));
            }
        }

        // Move to origin (centered)
        transform.translate(-px - layer.getOriginX(), -py - layer.getOriginY());

        return transform;
    }

}
