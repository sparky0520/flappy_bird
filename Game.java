
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

public class Game extends JPanel implements Runnable {

    private static final int SCREEN_HEIGHT = 512;
    private static final int SCREEN_WIDTH = 288;
    private static final int FPS = 60;

    // Game entities
    private Bird bird;
    private ArrayList<Pipe> pipes;
    private ArrayList<RotatedPipe> rotatedPipes;
    private BufferedImage background;
    private BufferedImage base;
    private Thread thread;
    private int score;
    private Graphics2D g2d;

    boolean gameStart;
    boolean gameOver;

    // Initialize game objects
    public Game() {
        // Background and base
        try {
            background = ImageIO.read(new File("background-day.png"));
            base = ImageIO.read(new File("base.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        init();

        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
    }

    // Draw sprites in increasing order of z index
    @Override
    protected void paintComponent(Graphics g) {
        // super.paintComponent(g);    // Paint background
        g2d = (Graphics2D) g;

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
        // Score
        g2d.drawString("Score: " + score, 10, 30);

        if (gameOver) {
            // Game Over
            g2d.setFont(new Font("Arial", Font.BOLD, 36));
            g2d.drawString("Game Over", 50, 256);
        }
        if (!gameStart) {
            // Press to start
            g2d.setFont(new Font("Arial", Font.BOLD, 18));
            g2d.drawString("Press SPACE to start", 50, 256);
        }
    }

    public void init() {
        score = 0;
        gameOver = false;
        gameStart = false;

        // Initalise sprites
        bird = new Bird(50, 200);

        // Generating Pipes
        pipes = new ArrayList<>();
        rotatedPipes = new ArrayList<>();

        int y1 = (100 + (int) (Math.random() * 100)) - 320;
        int y2 = (100 + (int) (Math.random() * 100)) - 320;
        int x1 = 288;
        int x2 = 432;

        pipes.add(new Pipe(x1, y1));
        pipes.add(new Pipe(x2, y2));

        rotatedPipes.add(new RotatedPipe(x1, y1 + 100 + 320));
        rotatedPipes.add(new RotatedPipe(x2, y2 + 100 + 320));
    }

    public void startGameThread() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        long lastUpdateTime = System.nanoTime();
        long deltaTime = 1_000_000_000 / FPS;
        while (thread != null) {
            long currentTime = System.nanoTime();
            long elapsed = currentTime - lastUpdateTime;

            if (elapsed >= deltaTime) {
                if (gameStart && !gameOver) {
                    update();   // Update game state
                }
                repaint();     // Calls paintComponent() function
                lastUpdateTime = currentTime;
            }
        }
    }

    private void update() {
        int birdX = bird.x + 17;
        int birdY = bird.y + 12;

        if (birdY >= 400) {
            gameOver = true;
        } else {
            bird.updateState();
        }

        for (Pipe p : pipes) {
            if (p.x == 24) {
                score++;
            }
            if (p.x <= -52) {
                p.x = 288;
            }
            if (birdX > p.x && birdX < p.x + 52 && birdY < p.y + 320) {
                gameOver = true;
            }
            p.update();
        }

        for (RotatedPipe rp : rotatedPipes) {
            if (rp.x <= -52) {
                rp.x = 288;
            }
            if (birdX > rp.x && birdX < rp.x + 52 && birdY > rp.y) {
                gameOver = true;
            }
            rp.update();
        }
    }

    Bird getBird() {
        return bird;
    }
}
