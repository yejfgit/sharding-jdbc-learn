package com.netease.okr.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

/**
 * @author hzyejinfu
 * */
public class ImageUtil {
	final static Logger logger = Logger.getLogger(ImageUtil.class);
	
	private static PropertiesUtils properties = new PropertiesUtils("config", null);

	
	public static String toJPG(String inFilePath){
		
		String outFilePath = properties.getPropery("photo_dir");
		
		outFilePath +=  "/"+getRandomImageName()+".jpg";
		
		BufferedImage bufferedImage;
		try {
			File file = new File(inFilePath);
			file.getParentFile().mkdirs();
			bufferedImage=ImageIO.read(file);
			BufferedImage newBufferedImage =new BufferedImage(bufferedImage.getWidth(),bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
			newBufferedImage.createGraphics().drawImage(bufferedImage,0,0,Color.white,null);
			
			ImageIO.write(newBufferedImage,"jpg",new File(outFilePath));
			
			
		} catch (Exception e) {
			logger.info("*****toJPG exception"+outFilePath);
			e.printStackTrace();
		}
		
		return outFilePath;
	}
	
	
	/**
	 * 
	 * @return
	 */
	private static String getRandomImageName() {
		Random r = new Random();
		r.setSeed(new Date().getTime());
		String pre = String.valueOf(r.nextInt(10000));

		return pre + "-" + UUID.randomUUID().toString().replaceAll("-", "");
	}	
  
    /** 
     * 按设置的宽度高度压缩图片文件<br> 先保存原文件，再压缩、上传 
     * @param oldFile  要进行压缩的文件全路径 
     * @param newFile  新文件 
     * @param width  宽度 
     * @param height 高度 
     * @param quality 质量 
     * @return 返回压缩后的文件的全路径 
     */  
	public static String zipWidthHeightImageFile(File oldFile, int width, int height,float quality) {  
        if (oldFile == null) {  
            return null;  
        }  
        String outFilePath = properties.getPropery("photo_dir");
		
		outFilePath +=  "/"+getRandomImageName()+".jpg";
		
		File newFile = new File(outFilePath);
        try {  
            /** 对服务器上的临时文件进行处理 */  
            Image srcFile = ImageIO.read(oldFile);  
            
            String srcImgPath = newFile.getAbsoluteFile().toString();
            System.out.println(srcImgPath);
            String subfix = "jpg";
    		subfix = srcImgPath.substring(srcImgPath.lastIndexOf(".")+1,srcImgPath.length());

    		BufferedImage buffImg = null; 
    		if(subfix.equals("png")){
    			buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    		}else{
    			buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    		}

    		Graphics2D graphics = buffImg.createGraphics();
    		graphics.setBackground(new Color(255,255,255));
    		graphics.setColor(new Color(255,255,255));
    		graphics.fillRect(0, 0, width, height);
    		graphics.drawImage(srcFile.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);  

    		ImageIO.write(buffImg, subfix, new File(srcImgPath));  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return outFilePath;  
    }  
	
	
	public static void main(String[] arg) throws IOException{
		//System.out.println("ssssssssssssssss");
		//compressPic("d:/5.jpg","d:/6.jpg");
		//toJPG("d:/2.png");
		
		zipWidthHeightImageFile(new File("d:/7.jpg"),85,120,0.5f);
		ImageUtil.zipWidthHeightImageFile(new File("d:/7.jpg"),85,120,0.5f);
		//zipImageFile(new File("C:\\spider\\2.JPG"),new File("C:\\spider\\2-2.JPG"),425,638,0.7f);
		
		//zipImageFile(new File("C:\\spider\\3.jpg"),new File("C:\\spider\\3-3.jpg"),425,638,0.7f);
		
		System.out.println("ok");
		
		
		/*BufferedImage bufferedImage;
		try {
			bufferedImage=ImageIO.read(new File("d:\\3.bmp"));
			BufferedImage newBufferedImage =new BufferedImage(bufferedImage.getWidth(),bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
			newBufferedImage.createGraphics().drawImage(bufferedImage,0,0,Color.white,null);
			
			ImageIO.write(newBufferedImage,"jpg",new File("d:\\3.jpg")  );
			
			//JOptionPane.showMessageDialog(null,"ok");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e);
		}*/
	}

}
