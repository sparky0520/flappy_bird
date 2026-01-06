
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Game extends JPanel {

    static int SCREEN_HEIGHT = 512;
    static int SCREEN_WIDTH = 288;

    // Game entities
    private final Bird bird;
    private final ArrayList<Pipe> pipes;
    private final ArrayList<RotatedPipe> rotatedPipes;
    private BufferedImage background;
    private BufferedImage base;
    private int score = 0;

    // Initialize game objects
    public Game() {
        // Background and base
        try {
            background = ImageIO.read(new File("background-day.png"));
            base = ImageIO.read(new File("base.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initalise sprites
        bird = new Bird(50, 200);

        // Generating Pipes
        pipes = new ArrayList<>();
        rotatedPipes = new ArrayList<>();

        int y1 = (100 + (int) (Math.random() * 128)) - 320;
        int y2 = (100 + (int) (Math.random() * 128)) - 320;
        int x1 = 10;// 288;
        int x2 = 128;// 406;

        pipes.add(new Pipe(x1, y1));
        pipes.add(new Pipe(x2, y2));

        rotatedPipes.add(new RotatedPipe(x1, y1 + 72 + 320));
        rotatedPipes.add(new RotatedPipe(x2, y2 + 72 + 320));

        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
    }

    // Draw sprites in increasing order of z index
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);    // Paint background
        Graphics2D g2d = (Graphics2D) g;

        // Draw background
        g2d.drawImage(background, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);

        // Draw bird
        bird.draw(g2d);

        // Draw all pipes
        for (Pipe p : pipes) {
            p.draw(g2d);
        }

        // Draw all rotated pipes
        for (RotatedPipe rp : rotatedPipes) {
            rp.draw(g2d);
        }

        // Draw base
        g2d.drawImage(base, 0, SCREEN_HEIGHT - 112, null);

        // Draw UI elements
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 18));
        g2d.drawString("Score: " + score, 10, 30);
    }

    public void loop() {
        long lastUpdateTime = System.nanoTime();

        while (true) {
            long currentTime = System.nanoTime();
            long elapsed = currentTime - lastUpdateTime;

            if (elapsed >= 1_000_000_000 / 60) {    // 60 FPS
                update();   // Update state - event handlers, game over check
                lastUpdateTime = currentTime;
            }
        }
    }

    void update() {

    }

    Bird getBird() {
        return bird;
    }
}
