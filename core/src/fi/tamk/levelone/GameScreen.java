package fi.tamk.levelone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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

    public GameScreen(Main g) {
        this.game = g;
        this.batch = this.game.getBatch();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 8f, 4.8F);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
}
