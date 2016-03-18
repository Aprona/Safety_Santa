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
    private Texture mainMenuBackground;
    private Rectangle playButtonRectangle;
    private Rectangle optionsButtonRectangle;
    private Rectangle exitButtonRectangle;
    private Vector3 vector;

    public MainMenuScreen(Main g) {
        this.game = g;
        this.batch = game.getBatch();
        this.camera = game.getCamera();
        exitButton = new Texture("exitButton.png");
        playButton = new Texture("playButton.png");
        optionsButton = new Texture("optionsButton.png");
        mainMenuBackground = new Texture("mainMenuBackground.png");

        vector = new Vector3(0, 0, 0);

        playButtonRectangle = new Rectangle(2.25f, 2.2f, playButton.getWidth() /100f,
                playButton.getHeight()/100f);
        optionsButtonRectangle = new Rectangle(2.25f, 1.2f, optionsButton.getWidth()/100f,
                optionsButton.getHeight()/100f);
        exitButtonRectangle = new Rectangle(0.2f, 0.2f, exitButton.getWidth()/100f,
                exitButton.getHeight()/100f);

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
        batch.draw(mainMenuBackground, 0, 0, 8, 4.8f);
        batch.draw(playButton, playButtonRectangle.x, playButtonRectangle.y,
                playButton.getWidth()/100f, playButton.getHeight()/100f);
        batch.draw(optionsButton, optionsButtonRectangle.x, optionsButtonRectangle.y,
                optionsButton.getWidth()/100f, optionsButton.getHeight()/100f);
        batch.draw(exitButton, exitButtonRectangle.x, exitButtonRectangle.y,
                exitButton.getWidth()/100f, exitButton.getHeight()/100f);
        batch.end();
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            System.out.println(vector.x + ", " + vector.y);
        }

        if (playButtonRectangle.contains(vector.x, vector.y) &&
                Gdx.input.justTouched()) {
            game.changeScreen("levelSelectionScreen");
        }

        if (optionsButtonRectangle.contains(vector.x, vector.y) &&
                Gdx.input.justTouched()) {
            game.changeScreen("optionsScreen");
        }

        if (exitButtonRectangle.contains(vector.x, vector.y) &&
                Gdx.input.justTouched()) {
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
