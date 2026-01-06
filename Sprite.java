
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

abstract class Sprite {

    // State/Properties 
    private BufferedImage image;
    private int x, y;   // Position
    private int width, height;  // Dimensions
    int velocityX, velocityY;   // Movement speed

    // Constructor
    public Sprite(String imagePath, int x, int y) {
        try {
            image = ImageIO.read(new File(imagePath));
            width = image.getWidth();
            height = image.getHeight();
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

    // Collision detection
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void setVelocity(int vx, int vy) {
        velocityX = vx;
        velocityY = vy;
    }
}
