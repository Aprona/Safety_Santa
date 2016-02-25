package fi.tamk.levelone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Vector;

import javafx.scene.Camera;

/**
 * Created by Ville on 24.2.2016.
 */
public class SafetySanta {

    private Texture santaImg;
    private Rectangle santaRectangle;
    private float santaSpeed = 2f;
    private float delta = Gdx.graphics.getDeltaTime();
    private GameScreen gameScreen;

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
                    checkAction();
            }

            if (touchPos.x  > gameScreen.buttonLeftRect.x && touchPos.x < gameScreen.buttonLeftRect.x + gameScreen.buttonLeftRect.getWidth() &&
                    touchPos.y > gameScreen.buttonLeftRect.y && touchPos.y < gameScreen.buttonLeftRect.y + gameScreen.buttonLeftRect.getHeight()) {
                santaRectangle.x -= santaSpeed * delta;
            }

            if (touchPos.x  > gameScreen.buttonRightRect.x && touchPos.x < gameScreen.buttonRightRect.x + gameScreen.buttonRightRect.getWidth() &&
                    touchPos.y > gameScreen.buttonRightRect.y && touchPos.y < gameScreen.buttonRightRect.y + gameScreen.buttonRightRect.getHeight()) {
                santaRectangle.x += santaSpeed * delta;
            }

        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) {
            santaRectangle.x -= santaSpeed * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) {
            santaRectangle.x += santaSpeed * delta;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) {
            santaRectangle.y += santaSpeed * delta;
        }
    }

    private void checkAction () {

    }

    public void santaDraw (SpriteBatch sp)  {
        sp.draw(santaImg,
                santaRectangle.x,
                santaRectangle.y,
                santaImg.getWidth() / 100f,
                santaImg.getHeight() / 100f);
    }

    public void changeFloor() {
        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            
        }
    }
}