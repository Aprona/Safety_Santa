package fi.tamk.levelone;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.Rectangle;

public class Main extends Game {
	SpriteBatch batch;
    MainMenuScreen mainMenu;
	GameScreen gameScreen;

	@Override
	public void create () {
		batch = new SpriteBatch();
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
    }
}


