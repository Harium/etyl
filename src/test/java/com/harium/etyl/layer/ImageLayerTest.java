package com.harium.etyl.layer;

import org.junit.Assert;
import org.junit.Test;

public class ImageLayerTest {

    @Test
    public void testGetSrcMeasures() {
        ImageLayer layer = new ImageLayer(1, 2, 3, 4);

        Assert.assertEquals(3, layer.getW());
        Assert.assertEquals(4, layer.getH());
    }

    @Test
    public void testCopyLayer() {
        ImageLayer layer = new ImageLayer(1, 2, 3, 4);
        layer.setOpacity(90);

        ImageLayer b = new ImageLayer();
        b.copy(layer);

        Assert.assertEquals(1, b.getX());
        Assert.assertEquals(2, b.getY());
        Assert.assertEquals(3, b.getW());
        Assert.assertEquals(4, b.getH());

        Assert.assertEquals(1.5, b.getOriginX(), 0);
        Assert.assertEquals(2, b.getOriginY(), 0);

        Assert.assertEquals(90, b.getOpacity());
    }

}
