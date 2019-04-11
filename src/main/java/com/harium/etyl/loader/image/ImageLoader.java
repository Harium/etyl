package com.harium.etyl.loader.image;


import com.harium.etyl.layer.StaticLayer;
import com.harium.etyl.loader.LoaderImpl;
import com.harium.etyl.util.PathHelper;
import com.harium.etyl.util.StringUtils;
import com.harium.etyl.util.io.IOHelper;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ImageLoader extends LoaderImpl {

    private static ImageLoader instance = null;

    private Map<String, ImageReader> loaders = new HashMap<String, ImageReader>();

    private Map<String, BufferedImage> images = new HashMap<String, BufferedImage>();

    private Map<String, List<ImageFrame>> animations = new HashMap<String, List<ImageFrame>>();

    private final static String BMP = "bmp";
    private final static String GIF = "gif";
    private final static String JPG = "jpg";
    private final static String JPEG = "jpeg";
    private final static String PCX = "pcx";
    private final static String HDR = "hdr";
    private final static String PNG = "png";
    private final static String TIF = "tif";
    private final static String TIFF = "tiff";
    private final static String TGA = "tga";

    private ImageLoader() {
        super();

        folder = "assets/images/";

        AWTReader awtReader = new AWTReader();

        loaders.put(BMP, awtReader);
        loaders.put(JPG, awtReader);
        loaders.put(JPEG, awtReader);
        loaders.put(PNG, awtReader);
        loaders.put(TIF, awtReader);
        loaders.put(TIFF, awtReader);
        loaders.put(GIF, new GIFReader());
        loaders.put(TGA, new TGAReader());
        loaders.put(PCX, new PCXReader());
    }

    public static ImageLoader getInstance() {
        if (instance == null) {
            instance = new ImageLoader();
        }

        return instance;
    }

    public StaticLayer loadImage(String path) {
        return loadImage(path, false);
    }

    public StaticLayer loadImage(String path, boolean absolute) {
        BufferedImage img = getImage(path, absolute);

        StaticLayer cam = new StaticLayer();
        cam.setSize(img.getWidth(), img.getHeight());
        cam.setOriginCenter();

        if (absolute) {
            cam.onLoad(IOHelper.FILE_PREFIX + path);
        } else {
            cam.onLoad(path);
        }

        return cam;
    }

    public StaticLayer loadImage(InputStream stream, String path) {
        String streamPath = IOHelper.STREAM_PREFIX + path;
        BufferedImage img = getImage(stream, streamPath);

        StaticLayer cam = new StaticLayer();
        cam.setSize(img.getWidth(), img.getHeight());
        cam.onLoad(streamPath);

        return cam;
    }

    public BufferedImage getImage(String path) {

        boolean absolute = false;

        if (path.startsWith(IOHelper.FILE_PREFIX)) {
            absolute = true;
        } else if (path.startsWith(IOHelper.STREAM_PREFIX)) {
            return getImageAsStream(path);
        }

        return getImage(path, absolute);
    }

    private BufferedImage getImage(InputStream stream, String path) {
        if (images.containsKey(path)) {
            return images.get(path);
        } else {

            String ext = StringUtils.fileExtension(path);
            ImageReader reader = loaders.get(ext);

            BufferedImage img = null;

            if (reader == null) {
                System.out.println("Etyl can't load " + ext + " files.");
            } else {
                try {
                    img = reader.loadImage(stream);
                    images.put(path, img);
                    if (img == null) {
                        System.err.println("Image " + path + " not found.");
                    }
                } catch (IOException e) {
                    System.err.println("Image " + path + " not found.");
                }
            }

            return img;
        }
    }

    public BufferedImage getImage(String path, boolean absolute) {
        String fullPath = fullPath(path, absolute);

        if (images.containsKey(fullPath)) {
            return images.get(fullPath);
        } else {
            URL dir = null;

            if (!absolute) {
                try {
                    dir = new URL(url, fullPath);
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                }
            } else {

                if (!isHttpContext() && !fullPath.startsWith(IOHelper.FILE_PREFIX)) {
                    fullPath = IOHelper.FILE_PREFIX + fullPath;
                }

                try {
                    dir = new URL(fullPath);
                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            String ext = StringUtils.fileExtension(fullPath);
            ImageReader reader = loaders.get(ext);

            BufferedImage img = null;

            if (reader == null) {
                System.out.println("Etyl can't load " + ext + " files.");
            } else {
                try {
                    img = reader.loadImage(dir.openStream());
                    images.put(fullPath, img);
                    if (img == null) {
                        System.err.println("Error on load: " + fullPath);
                    }
                } catch (IOException e) {
                    System.err.println("Image " + fullPath + " not found.");
                }
            }

            return img;
        }

    }

    public List<ImageFrame> getAnimation(String path) {

        return getAnimation(path, false);

    }

    public List<ImageFrame> getAnimation(String path, boolean absolute) {

        String fullPath = fullPath(path, absolute);

        if (animations.containsKey(fullPath)) {
            return animations.get(fullPath);

        } else {

            List<ImageFrame> list = null;

            URL dir = null;

            try {
                dir = new URL(url, fullPath);
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            }

            String ext = StringUtils.fileExtension(fullPath);

            if (ext.equals("gif")) {

                try {
                    list = GIFReader.getInstance().loadAnimation(dir);
                    animations.put(fullPath, list);

                } catch (IOException e) {

                    System.err.println("Image " + fullPath + " not found.");

                    e.printStackTrace();
                }
            }

            return list;
        }
    }

    public Set<String> supportedExtensions() {
        return loaders.keySet();
    }

    public void addLoader(String extension, ImageReader loader) {
        loaders.put(extension, loader);
    }

    public void disposeImage(String path) {
        images.remove(path);
    }

    public StaticLayer loadImageAsStream(String path) {
        String p = path.substring(IOHelper.STREAM_PREFIX.length());

        try {
            return loadImage(PathHelper.loadAsset(p), p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private BufferedImage getImageAsStream(String path) {
        if (images.containsKey(path)) {
            return images.get(path);
        } else {
            String p = path.substring(IOHelper.STREAM_PREFIX.length());

            InputStream stream = null;
            try {
                stream = PathHelper.loadAsset(p);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return getImage(stream, path);
        }
    }

    @Override
    public void dispose() {
        images.clear();
        animations.clear();
    }
}