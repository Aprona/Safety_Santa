package fi.tamk.levelone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Ville on 24.2.2016.
 */
public class SafetySanta{

    private Texture santaImg;
    private Rectangle santaRectangle;
    private float santaSpeed = 2f;
    private float delta = Gdx.graphics.getDeltaTime();
    private GameScreen gameScreen;
    private boolean goRight = true;
    private float alpha = 1f;
    private float movement = 0f;

    public SafetySanta (GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        santaImg = new Texture("safety_santa_player.png");
        santaRectangle = new Rectangle(0,0,santaImg.getWidth() / 100f, santaImg.getHeight() / 100f);
    }

    public void santaUpdate (Vector3 touchPos) {
            // Gdx.app.log("x", String.valueOf(gameScreen.buttonActionRect.getWidth()));
            // Gdx.app.log("y", String.valueOf(gameScreen.buttonActionRect.y));

            if (touchPos.x  > gameScreen.buttonActionRect.x && touchPos.x < gameScreen.buttonActionRect.x + gameScreen.buttonActionRect.getWidth() &&
                    touchPos.y > gameScreen.buttonActionRect.y && touchPos.y < gameScreen.buttonActionRect.y + gameScreen.buttonActionRect.getHeight()) {

            }

            if (touchPos.x  > gameScreen.buttonLeftRect.x && touchPos.x < gameScreen.buttonLeftRect.x + gameScreen.buttonLeftRect.getWidth() &&
                    touchPos.y > gameScreen.buttonLeftRect.y && touchPos.y < gameScreen.buttonLeftRect.y + gameScreen.buttonLeftRect.getHeight()) {
                santaRectangle.x -= santaSpeed * delta;
                goRight = false;
            }

            if (touchPos.x  > gameScreen.buttonRightRect.x && touchPos.x < gameScreen.buttonRightRect.x + gameScreen.buttonRightRect.getWidth() &&
                    touchPos.y > gameScreen.buttonRightRect.y && touchPos.y < gameScreen.buttonRightRect.y + gameScreen.buttonRightRect.getHeight()) {
                santaRectangle.x += santaSpeed * delta;
                goRight = true;
            }



    }

    public Rectangle getSantaRectangle () {
        return santaRectangle;
    }

    public void moveSantaKeyboard() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            santaRectangle.x -= santaSpeed * delta;
            goRight = false;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            santaRectangle.x += santaSpeed * delta;
            goRight = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            santaRectangle.y += santaSpeed * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            santaRectangle.y -= santaSpeed * delta;
        }
    }

    public float getX() {
        return santaRectangle.getX();
    }

    public float getY() {
        return santaRectangle.getY();
    }

    public void setX(float x) {
        santaRectangle.setX(x);
    }

    public void setY(float y) {
        santaRectangle.setY(y);
    }

    public void santaDraw (SpriteBatch sp)  {
        sp.draw(santaImg,
                santaRectangle.x,
                santaRectangle.y,
                santaImg.getWidth() / 100f,
                santaImg.getHeight() / 100f,
                0,
                0,
                santaImg.getWidth(),
                santaImg.getHeight(),
                !goRight,
                false);
    }

    public float getRectangleWidth() {
        return santaRectangle.getWidth();
    }

    public float getRectangleHeight() {
        return santaRectangle.getHeight();
    }

    public void changeFloorUp(SpriteBatch batch) {
        boolean fading = true;

        batch.setColor(1f, 1f, 1f, alpha);
        batch.draw(santaImg,
                santaRectangle.x,
                santaRectangle.y,
                santaImg.getWidth() / 100f,
                santaImg.getHeight() / 100f,
                0,
                0,
                santaImg.getWidth(),
                santaImg.getHeight(),
                !goRight,
                false);


        if (alpha >= 0 && fading) {
            alpha -= 0.05f;
        }

        if (alpha < 0.2 && fading && movement < 2.56f) {
            Gdx.app.log("jotain", "on testattu");
            santaRectangle.y += 0.05f;
            movement += 0.05f;
        }

        if (alpha <= 1 && !fading) {
            alpha -= 0.05f;
        }
    }

    public void changeFloorDown(SpriteBatch batch) {
        batch.setColor(1f, 1f, 1f, alpha);
        batch.draw(santaImg,
                santaRectangle.x,
                santaRectangle.y,
                santaImg.getWidth() / 100f,
                santaImg.getHeight() / 100f,
                0,
                0,
                santaImg.getWidth(),
                santaImg.getHeight(),
                !goRight,
                false);

        if (alpha > 0) {
            alpha -= 0.1f;
        }
    }
}