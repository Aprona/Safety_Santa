package fi.tamk.levelone;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game {
	SpriteBatch batch;
    MainMenuScreen mainMenu;
	GameScreen gameScreen;
    LevelSelectionScreen levelSelectionScreen;
	public OrthographicCamera camera;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 8f, 4.8f );
		changeScreen("mainMenu");

	}

	@Override
	public void render () {
		super.render();
	}

    public SpriteBatch getBatch() {
        return batch;
    }

	public void clearScreen () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

    public void changeScreen(String screen) {

        if (screen.equalsIgnoreCase("mainMenu")) {
            mainMenu = new MainMenuScreen(this);
            setScreen(mainMenu);
        }

        if (screen.equalsIgnoreCase("gameScreen")) {
            if (!(gameScreen == null)) {
                gameScreen.dispose();
                gameScreen = null;
            }

            gameScreen = new GameScreen(this);
            setScreen(gameScreen);

        }

        if (screen.equalsIgnoreCase("levelSelectionScreen")) {
            levelSelectionScreen = new LevelSelectionScreen(this);
            setScreen(gameScreen);
        }
    }

	public OrthographicCamera getCamera () {
		return camera;
	}
}


