
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

class FlappyBird {

    public static void main(String args[]) {
        JFrame frame = new JFrame("Flappy Bird");
        Game gamePanel = new Game();

        frame.add(gamePanel);
        frame.pack();   // Fit window to component size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Add key listeners
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    System.out.println("Space bar pressed!");
                    gamePanel.getBird().flap();
                }
            }
        });

        gamePanel.startGameThread();
    }
}
