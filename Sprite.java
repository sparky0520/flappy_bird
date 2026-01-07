
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

abstract class Sprite {

    // State/Properties 
    BufferedImage image;
    int x, y;   // Position
    int velocityX, velocityY;   // Movement speed

    // Constructor
    public Sprite(String imagePath, int x, int y) {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.x = x;
        this.y = y;
    }

    // Update game logic (updated every frame)
    public void update() {
        x += velocityX;
        y += velocityY;
    }

    // Render to screen (called every frame)
    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }
}
