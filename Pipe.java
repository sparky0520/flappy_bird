
public class Pipe extends Sprite {

    public Pipe(int x, int y) {
        super("pipe-red.png", x, y);
        setVelocity(-3, 0);     // Move left
    }
}
