/**
 * 
 */

/**
 * @author Prachi Chachondia
 * Comments: Function to read a stream of random numbers from Random.org
 * and use them to generate a RGB image by setting different start values
 * for the Red, Blue, Green components.
 * This approach requests the smallest possible set of random numbers that
 * can generate a different and interesting pattern on each run.
 */

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.imageio.ImageIO;


public class RNGenerator {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		int imgH = 128, imgW = 128;
		String is;
		BufferedImage image = new BufferedImage(imgH, imgW, BufferedImage.TYPE_INT_ARGB);

		URL url = new URL("https://www.random.org/integers/?num=3&min=0&max=255&col=1&base=10&format=plain&rnd=new");
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod("GET");
		BufferedReader bread = new BufferedReader(new InputStreamReader(http.getInputStream()));
		ArrayList<Integer> randnumlist = new ArrayList<>();
		while ((is = bread.readLine()) != null) {
			randnumlist.add(Integer.parseInt(is));
		}
		int count = randnumlist.size();
		System.out.println("Count: " + count);
		bread.close();
		int red = 0, green = 0, blue = 0;
		count = 0;
		Color colorobj; // = new Color(red, green, blue);
		red = randnumlist.get(count);
		count++;
		green = randnumlist.get(count); 
		count++;
		blue = randnumlist.get(count);
		for(int i = 0; i < imgH; i++){
			for(int j = 0; j < imgW; j++){
				colorobj = new Color(red, green, blue);
				red++;
				if(red==256)
					red = 0;
				green++;
				if(green==256)
					green = 0;
				blue++;
				if(blue==256)
					blue = 0;
				
				image.setRGB(i, j, colorobj.getRGB());
			}
		}
		
		File file = null;
		try{
			file = new File("RGBImage.png");
	        ImageIO.write(image, "png", file);
	      }catch(IOException e){
	        System.out.println("Error: " + e);
	      }
	}

}
