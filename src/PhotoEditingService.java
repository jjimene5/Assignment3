import java.awt.Color;

public class PhotoEditingService {

	private static final int X_SIZE = 512;
	private static final int Y_SIZE = 512;
	
	public static void main(String[] args) {
		Color[][] my2DImageArray = new Color[512][512];
		ImageUtils imgUtil = new ImageUtils();
		my2DImageArray = imgUtil.loadImage("src/LennaCV.png");
		makeRed(my2DImageArray);
		imgUtil.addImage(my2DImageArray, "Red Shifted");
		my2DImageArray = imgUtil.loadImage("src/LennaCV.png");
		makeBlue(my2DImageArray);
		imgUtil.addImage(my2DImageArray, "Blue Shifted");
		my2DImageArray = imgUtil.loadImage("src/LennaCV.png");
		makeGreen(my2DImageArray);
		imgUtil.addImage(my2DImageArray, "Green Shifted");
		my2DImageArray = imgUtil.loadImage("src/LennaCV.png");
		simplifyColor(my2DImageArray);
		imgUtil.addImage(my2DImageArray, "Color Simplified");
		my2DImageArray = imgUtil.loadImage("src/LennaCV.png");
		imgUtil.addImage(my2DImageArray, "Original");
		imgUtil.display();
	}
	
	public static void makeRed(Color[][] img) {
		for(int x=0; x < X_SIZE; x++) {
			for(int y=0; y < Y_SIZE; y++) {
				int R = img[x][y].getRed();
				if(R < 220) {
					R += (int) .4*R;
				}
				int G = img[x][y].getGreen();
				if(G > 30) {
					G = G-30;
				}
				int B = img[x][y].getBlue();
				if(B > 30) {
					B = B-30;
				}
				img[x][y] = new Color(R, G, B);
			}
		}
	}
	
	public static void makeBlue(Color[][] img) {
		for(int x=0; x < X_SIZE; x++) {
			for(int y=0; y < Y_SIZE; y++) {
				int R = img[x][y].getRed();
				if(R > 30) {
					R = R-30;
				}
				int G = img[x][y].getGreen();
				if(G > 30) {
					G = G-30;
				}
				int B = img[x][y].getBlue();
				if(B < 220) {
					B += (int) .35*B;
				}
				img[x][y] = new Color(R, G, B);
			}
		}
	}
	
	public static void makeGreen(Color[][] img) {
		for(int x=0; x < X_SIZE; x++) {
			for(int y=0; y < Y_SIZE; y++) {
				int R = img[x][y].getRed();
				if(R > 30) {
					R = R-30;
				}
				int G = img[x][y].getGreen();
				if(G < 220) {
					G += (int) .35*G;
				}
				int B = img[x][y].getBlue();
				if(B > 30) {
					B = B-30;
				}
				img[x][y] = new Color(R, G, B);
			}
		}
	}
	
	public static void simplifyColor(Color[][] img) {
		final int simpAmt = 6;
		for(int x=0; x < X_SIZE; x++) {
			for(int y=0; y < Y_SIZE; y++) {
				int R = img[x][y].getRed();
				int G = img[x][y].getGreen();
				int B = img[x][y].getBlue();
				for(int i=0; i < x; i++) {
					if(Math.abs((img[i][y].getBlue() - img[x][y].getBlue())) < simpAmt && Math.abs((img[i][y].getRed() - img[x][y].getRed())) < simpAmt && Math.abs((img[i][y].getGreen() - img[x][y].getGreen())) < simpAmt) {
						img[i][y] = img[x][y];
					}
				}
				img[x][y] = new Color(R, G, B);
			}
		}
	}
}
