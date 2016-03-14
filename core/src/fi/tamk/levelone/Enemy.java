package fi.tamk.levelone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by c5vhaapa on 10.3.2016.
 */
public class Enemy {
    private Texture enemyImg;
    private Rectangle enemyRectangle;
    private float enemySpeed = 1f;
    private float delta = Gdx.graphics.getDeltaTime();
    private boolean goRight = true;
    private GameScreen gameScreen;

    public Enemy(GameScreen gameScreen, float x, float y) {
        this.gameScreen = gameScreen;
        enemyImg = new Texture("safety_santa_enemy.png");
        enemyRectangle = new Rectangle(x, y, enemyImg.getWidth() / 100f, enemyImg.getHeight() / 100f);
    }

    public void enemyUpdate () {

        for (RectangleMapObject rectangleObject : gameScreen.initialize.getRectangleWallObjects()) {
            Rectangle rectangle = rectangleObject.getRectangle();

            if (enemyRectangle.overlaps(rectangle) && goRight) {
                goRight = false;
                enemyRectangle.x -= enemySpeed * delta;
            } else if (enemyRectangle.overlaps(rectangle) && !goRight) {
                goRight = true;
                enemyRectangle.x += enemySpeed * delta;
            }

        }

        if (goRight) {
            enemyRectangle.x += enemySpeed * delta;
        } else {
            enemyRectangle.x -= enemySpeed * delta;
        }

        if (enemyRectangle.x > gameScreen.getWORLD_WIDTH_METERS()) {
            goRight = false;

        } else if (enemyRectangle.x < 0.1f) {
            goRight = true;
        }
    }

    public void enemyDraw (SpriteBatch sp)  {
        sp.draw(enemyImg,
                enemyRectangle.x,
                enemyRectangle.y,
                enemyImg.getWidth() / 100f,
                enemyImg.getHeight() / 100f,
                0,
                0,
                enemyImg.getWidth(),
                enemyImg.getHeight(),
                !goRight,
                false);

    }


}
