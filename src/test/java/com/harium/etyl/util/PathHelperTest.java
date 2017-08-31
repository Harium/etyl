package com.harium.etyl.util;

import org.junit.Assert;
import org.junit.Test;

public class PathHelperTest {

    @Test
    public void testUpperDirectory() {

        String path = "/a/b/c";

        Assert.assertEquals("/a/b/", PathHelper.upperDirectory(path));
    }

    @Test
    public void testClearPath() {
        String path = "a/b/c/../d/e/../";

        Assert.assertEquals("a/b/d/", PathHelper.clearPath(path));
    }

    @Test
    public void testGetExtension() {
        String path = "a/b/c/d.exe";
        Assert.assertEquals("exe", PathHelper.getExtension(path));
    }

    @Test
    public void testGetExtensionWithDotInPath() {
        String path = "a/b.c/d.jpg";
        Assert.assertEquals("jpg", PathHelper.getExtension(path));
    }

    @Test
    public void testGetEmptyExtension() {
        String path = "a/b/c/d";
        Assert.assertEquals("", PathHelper.getExtension(path));
    }

    @Test
    public void testGetExtensionFromHideFile() {
        String path = "a/b/c/.d";
        Assert.assertEquals("d", PathHelper.getExtension(path));
    }

}
