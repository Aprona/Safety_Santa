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

/**
 * Created by c5tarpon on 17.3.2016.
 */
public class LevelSelectionScreen implements Screen {
    public Main game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture mainMenuBackground;
    private Vector3 vector;
    private Texture levelOne;
    private Texture backButton;
    private Rectangle levelOneRectangle;
    private Rectangle backButtonRectangle;

    public LevelSelectionScreen (Main g) {
        this.game = g;
        this.batch = game.getBatch();
        this.camera = game.getCamera();
        mainMenuBackground = new Texture("mainMenuBackground.png");
        levelOne = new Texture("levelOne.png");
        backButton = new Texture("testBackButton.png");
        levelOneRectangle = new Rectangle(1f, 3f, levelOne.getWidth()/100f,
                levelOne.getHeight()/100f);
        backButtonRectangle = new Rectangle(0.2f, 0.2f, backButton.getWidth()/100f,
                backButton.getHeight()/100f);

        vector = new Vector3(0, 0, 0);
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
        batch.draw(levelOne, levelOneRectangle.x, levelOneRectangle.y,
                levelOne.getWidth()/100f, levelOne.getHeight()/100f);
        batch.draw(backButton, backButtonRectangle.x, backButtonRectangle.y,
                backButton.getWidth()/100f, backButton.getHeight()/100f);
        batch.end();

        if (levelOneRectangle.contains(vector.x, vector.y) &&
                Gdx.input.justTouched()) {
            game.changeScreen("gameScreen");
        }
        if (backButtonRectangle.contains(vector.x, vector.y) &&
                Gdx.input.justTouched()) {
            game.changeScreen("mainMenu");
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
