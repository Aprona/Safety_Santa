package fi.tamk.levelone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.Rectangle;

/**
 * Created by Aprona on 24.2.2016.
 */
public class GameScreen implements Screen {

    private Main game;
    public OrthographicCamera camera;
    private SpriteBatch batch;
    private SafetySanta safetySanta;

    private float PADDING = 0.2f;

    private Texture buttonActionImg = new Texture ("button_action.png");
    public Rectangle buttonActionRect = new Rectangle(
            (int)(8 - buttonActionImg.getWidth() / 100f - PADDING),
            (int)(0 + PADDING),
            buttonActionImg.getWidth() / 100, buttonActionImg.getHeight() / 100);
    private Texture buttonLeftImg = new Texture ("button_left.png");
    public  Rectangle buttonLeftRect = new Rectangle(
            (int)(0 + PADDING),
            (int)(0 + PADDING),
            buttonLeftImg.getWidth() / 100, buttonLeftImg.getHeight() / 100);
    private Texture buttonRightImg = new Texture ("button_right.png");
    public Rectangle buttonRightRect = new Rectangle(
            1,
            (int)(0 + PADDING),
            buttonRightImg.getWidth() / 100, buttonRightImg.getHeight() / 100);


    public GameScreen(Main g) {
        this.game = g;
        this.batch = this.game.getBatch();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 8f, 4.8F);

        safetySanta = new SafetySanta(this);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        clearScreen();

        // Gdx.app.log("", "gameScreen");

        safetySanta.santaUpdate();

        batch.begin();
        drawButtons();
        safetySanta.santaDraw(batch);
        batch.end();

    }

    public void drawButtons () {
        batch.draw(
                buttonActionImg,
                buttonActionRect.x,
                buttonActionRect.y,
                buttonActionImg.getWidth() / 100f,
                buttonActionImg.getHeight() / 100f);
        batch.draw(
                buttonLeftImg,
                buttonLeftRect.x,
                buttonLeftRect.y,
                buttonLeftImg.getWidth() / 100f,
                buttonRightImg.getHeight() / 100f);
        batch.draw(
                buttonRightImg,
                buttonRightRect.x,
                buttonRightRect.y,
                buttonRightImg.getWidth() / 100f,
                buttonRightImg.getHeight() / 100f);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void clearScreen () {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
