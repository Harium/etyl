package com.harium.etyl.loader.image;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class PCXReader implements ImageReader {
	
	private static class PCXHeader {
		
		private char	manufacturer;
		private char	version;
		private char	encoding;
		private char	bits_per_pixel;
		private short 	xmin,ymin,xmax,ymax;
		private short 	hres,vres;
		private int 	palette[]=new int[48];
		private char	reserved;
		private char	color_planes;
		private short  bytes_per_line;
		private short 	palette_type;
		private byte 	filler[]=new byte[58];
		private byte[]	data;

		PCXHeader(byte[] raw){
			manufacturer=(char)raw[0];
			version=(char)raw[1];
			encoding=(char)raw[2];
			bits_per_pixel=(char)raw[3];
			xmin=(short)((raw[4]+(raw[5]<<8))&0xff);
			ymin=(short)((raw[6]+(raw[7]<<8))&0xff);
			xmax=(short)((raw[8]+(raw[9]<<8))&0xff);
			ymax=(short)((raw[10]+(raw[11]<<8))&0xff);
			hres=(short)((raw[12]+(raw[13]<<8))&0xff);
			vres=(short)((raw[14]+(raw[15]<<8))&0xff);
			for (int i = 0; i < 48; i++)
				palette[i]=(raw[16+i]&0xff);
			reserved=(char)raw[64];
			color_planes=(char)raw[65];
			bytes_per_line=(short)((raw[66]+(raw[67]<<8))&0xff);
			palette_type=(short)((raw[68]+(raw[69]<<8))&0xff);
			for (int i = 0; i < 58; i++)
				filler[i]=raw[70+i];
			data=new byte[raw.length-128];
			for (int i = 0; i < raw.length-128; i++)
				data[i]=raw[128+i];
		}
	}

	@Override
	public BufferedImage loadImage(URL url) throws IOException {
		InputStream f = url.openStream();
		return loadImage(f);
	}

	@Override
	public BufferedImage loadImage(InputStream input) throws IOException {
		int len=input.available();
		
		byte[] buffer = new byte[len+1];
		
		buffer[len] = 0;

		for (int i = 0; i < len; i++){
			buffer[i] = (byte)input.read();
		}

		input.close();
		
		//
		// parse the PCX file
		//
		
		PCXHeader pcx = new PCXHeader(buffer);
		
		byte[] raw = pcx.data;

		if (pcx.manufacturer != 0x0a
				|| pcx.version != 5
				|| pcx.encoding != 1
				|| pcx.bits_per_pixel != 8
				|| pcx.xmax >= 640
				|| pcx.ymax >= 480)
		{
			throw new IOException("Bad pcx file");
		}

		int palette[] = new int[768];
		
		for (int i = 0; i < 768; i++){
			if ((len-128-768+i)<pcx.data.length){
				palette[i]=pcx.data[len-128-768+i]&0xff;
			}
		}

		int imageWidth = pcx.xmax+1;
		int imageHeight = pcx.ymax+1;

		int[] out = new int[(pcx.ymax+1) * (pcx.xmax+1)];

		int[] pic = out;
		int[] pix = out;

		int pixcount = 0;
		int rawcount = 0;
		
		int	dataByte, runLength;

		for (int y=0 ; y<= pcx.ymax ; y++, pixcount += pcx.xmax+1){

			for (int x=0 ; x<=pcx.xmax ; ){

				dataByte = raw[rawcount++];

				if((dataByte & 0xC0) == 0xC0){

					runLength = dataByte & 0x3F;
					dataByte = raw[rawcount++];
				}
				else{
					runLength = 1;
				}

				while(runLength-- > 0){
					pix[pixcount+x++] = dataByte&0xff;
				}
			}

		}

		if (pic == null || palette == null){
			return null;
		}

		byte[] imageData = new byte [(imageWidth+1)*(imageHeight+1)* 3];

		//convert to rgb format
		for (int k=0; k<(imageWidth*imageHeight); k++)
		{
			imageData[k * 3] = (byte)palette[pic[k] * 3 ];
			imageData[k * 3 + 1] = (byte)palette[pic[k] * 3 + 1];
			imageData[k * 3 + 2] = (byte)palette[pic[k] * 3 + 2];
		}

		//Convert to BufferedImage
		int pixel[] = new int[imageWidth*imageHeight*3];

		int z=0;
		int w=0;
		
		for (int i = 0; i < imageWidth; i++){
			for (int j = 0; j < imageHeight; j++){
				pixel[z++]=(0xff<<24)
						|((imageData[w++]&0x00ff)<<16)
						|((imageData[w++]&0x0000ff)<<8)
						|((imageData[w++]&0x000000ff));
			}
		}

		BufferedImage bimg = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
		bimg.setRGB(0, 0, imageWidth,imageHeight, pixel, 0,imageWidth);

		return bimg;
	}

}