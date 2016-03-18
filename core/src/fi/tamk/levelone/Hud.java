package fi.tamk.levelone;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by c5vhaapa on 16.3.2016.
 */

public class Hud {
    private GameScreen gameScreen;

    private Texture buttonActionImg;
    private Rectangle buttonActionRect;
    private Texture buttonLeftImg;
    private Rectangle buttonLeftRect;
    private Texture buttonRightImg;
    private Rectangle buttonRightRect;

    private float PADDING = 0.3f;

    public Hud(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        createButtons();
    }


    public void draw (SpriteBatch batch) {
        drawButtons(batch);
    }

    public void update () {
        buttonUpdate();
    }

    private void buttonUpdate () {
        buttonLeftRect.x = gameScreen.game.getCamera().position.x -
                gameScreen.WINDOW_WIDTH / 2 + PADDING;
        buttonLeftRect.y = gameScreen.game.getCamera().position.y -
                gameScreen.WINDOW_HEIGHT / 2 + PADDING;
        buttonRightRect.x = gameScreen.game.getCamera().position.x -
                gameScreen.WINDOW_WIDTH / 2 + PADDING * 2 + buttonLeftImg.getWidth() / 100f;
        buttonRightRect.y = gameScreen.game.getCamera().position.y -
                gameScreen.WINDOW_HEIGHT / 2 + PADDING;
        buttonActionRect.x = gameScreen.game.getCamera().position.x +
                gameScreen.WINDOW_WIDTH / 2 - PADDING - buttonActionImg.getWidth() / 100f;
        buttonActionRect.y = gameScreen.game.getCamera().position.y -
                gameScreen.WINDOW_HEIGHT / 2 + PADDING;
    }

    private void createButtons () {
        buttonActionImg = new Texture("button_action.png");
        buttonActionRect = new Rectangle(
                (8f - buttonActionImg.getWidth() / 100f - PADDING),
                (0f + PADDING),
                buttonActionImg.getWidth() / 100f,
                buttonActionImg.getHeight() / 100f);

        buttonLeftImg = new Texture ("button_left.png");
        buttonLeftRect = new Rectangle(
                (0 + PADDING),
                (0 + PADDING),
                buttonLeftImg.getWidth() / 100f,
                buttonLeftImg.getHeight() / 100f);

        buttonRightImg = new Texture ("button_right.png");
        buttonRightRect = new Rectangle(
                1f,
                (0 + PADDING),
                buttonRightImg.getWidth() / 100f,
                buttonRightImg.getHeight() / 100f);
    }

    private void drawButtons (SpriteBatch batch) {
        batch.setColor(1f, 1f, 1f, 0.5f);

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

    public Rectangle getButtonLeftRect () {
        return buttonLeftRect;
    }

    public Rectangle getButtonRightRect () {
        return buttonRightRect;
    }

    public Rectangle getButtonActionRect () {
        return buttonActionRect;
    }
}
