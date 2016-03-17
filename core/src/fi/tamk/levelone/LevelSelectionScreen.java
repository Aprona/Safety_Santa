package fi.tamk.levelone;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by c5tarpon on 17.3.2016.
 */
public class LevelSelectionScreen implements Screen {
    public Main game;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    public LevelSelectionScreen (Main g) {
        this.game = g;
        this.batch = game.getBatch();
        this.camera = game.getCamera();
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
