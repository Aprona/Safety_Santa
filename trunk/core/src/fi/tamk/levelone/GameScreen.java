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
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private SafetySanta safetySanta;
    private Texture buttonActionImg = new Texture ("button_action.png");
    private Texture buttonLeftImg = new Texture ("button_left.png");
    private Texture buttonRightImg = new Texture ("button_right.png");

    public GameScreen(Main g) {
        this.game = g;
        this.batch = this.game.getBatch();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 8f, 4.8F);

        safetySanta = new SafetySanta();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        clearScreen();

        Gdx.app.log("", "gameScreen");

        batch.begin();
        drawButtons();
        batch.end();

    }

    public void drawButtons () {
        batch.draw(buttonActionImg, 7, 0);
        batch.draw(buttonLeftImg, 0, 0);
        batch.draw(buttonRightImg, 1, 0);
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
