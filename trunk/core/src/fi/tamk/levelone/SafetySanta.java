package fi.tamk.levelone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Ville on 24.2.2016.
 */
public class SafetySanta {

    private Texture santaImg;
    private Rectangle santaRectangle;
    private float santaSpeed = 2 / 100f;

    public SafetySanta () {
        santaImg = new Texture("safety_santa_player.png");
        santaRectangle = new Rectangle(0,0,santaImg.getWidth() / 100f, santaImg.getHeight() / 100f);
    }

    public void santaUpdate () {
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) {
            santaRectangle.x -= santaSpeed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) {
            santaRectangle.x += santaSpeed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) {
            santaRectangle.y += santaSpeed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)) {
            santaRectangle.y -= santaSpeed;
        }
    }

    public void santaDraw (SpriteBatch sp)  {
        sp.draw(santaImg,
                santaRectangle.x,
                santaRectangle.y,
                santaImg.getWidth() / 100f,
                santaImg.getHeight() / 100f);
    }
}