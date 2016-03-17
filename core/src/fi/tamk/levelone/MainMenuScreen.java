package fi.tamk.levelone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Vector;

/**
 * Created by c5tarpon on 25.2.2016.
 */
public class MainMenuScreen implements Screen {

    private Main game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture playButton;
    private Texture optionsButton;
    private Texture exitButton;
    private Rectangle playButtonRectangle;
    private Rectangle optionsButtonRectangle;
    private Rectangle exitButtonRectangle;
    private Vector3 vector;

    public MainMenuScreen(Main g) {
        this.game = g;
        this.batch = game.getBatch();
        this.camera = game.getCamera();
        exitButton = new Texture("testExitButton.png");
        playButton = new Texture("testPlayButton.png");
        optionsButton = new Texture("testOptionsButton.png");
        vector = new Vector3(0, 0, 0);

        playButtonRectangle = new Rectangle(3, 2.2f, 200/100f, 50/100f);
        optionsButtonRectangle = new Rectangle(3, 1.6f, 200/100f, 50/100f);
        exitButtonRectangle = new Rectangle(3, 1, 200/100f, 50/100f);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        clearScreen();
        batch.setProjectionMatrix(this.camera.combined);
        vector.x = Gdx.input.getX();
        vector.y = Gdx.input.getY();
        camera.unproject(vector);

        batch.begin();
        batch.draw(playButton, playButtonRectangle.x, playButtonRectangle.y, 2, 0.5f);
        batch.draw(optionsButton, optionsButtonRectangle.x, optionsButtonRectangle.y, 2, 0.5f);
        batch.draw(exitButton, exitButtonRectangle.x, exitButtonRectangle.y, 2, 0.5f);
        batch.end();
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            System.out.println(vector.x + ", " + vector.y);
        }

        if (playButtonRectangle.contains(vector.x, vector.y) &&
                Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            game.changeScreen("gameScreen");
        }

        if (playButtonRectangle.contains(vector.x, vector.y) &&
                Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            game.changeScreen("optionsScreen");
        }

        if (exitButtonRectangle.contains(vector.x, vector.y) &&
                Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            Gdx.app.exit();
        }
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
