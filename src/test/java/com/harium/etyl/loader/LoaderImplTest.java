package com.harium.etyl.loader;

import com.harium.etyl.util.io.IOHelper;
import org.junit.Assert;
import org.junit.Test;

public class LoaderImplTest {

    @Test
    public void testGetPathWithFilePrefix() {
        LoaderImpl loader = new LoaderImpl();
        loader.setUrl(IOHelper.FILE_PREFIX + "/home/me");

        Assert.assertEquals("/home/me", loader.getPath());
    }

    @Test
    public void testGetPathWithHttpPrefix() {
        LoaderImpl loader = new LoaderImpl();
        loader.setUrl("http://localhost/");

        Assert.assertEquals("http://localhost/", loader.getPath());
    }

}
