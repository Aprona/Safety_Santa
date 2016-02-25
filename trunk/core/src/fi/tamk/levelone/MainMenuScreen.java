package fi.tamk.levelone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by c5tarpon on 25.2.2016.
 */
public class MainMenuScreen implements Screen {

    private Main game;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    public MainMenuScreen(Main g) {
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
        batch.setProjectionMatrix(camera.combined);
        clearScreen();

        batch.begin();

        batch.end();
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
