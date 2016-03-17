package fi.tamk.levelone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;

/**
 * Created by Ville on 24.2.2016.
 */
public class SafetySanta{

    private Texture santaImg;
    private Rectangle santaRectangle;
    private float santaSpeed = 2f;
    private float delta = Gdx.graphics.getDeltaTime();
    private GameScreen gameScreen;
    private boolean goRight = true;
    private float alpha = 1f;
    private float movement = 0;
    private boolean fading;
    private boolean canMove = true;
    private boolean canChangeFloor = true;
    public boolean floorChangeInProgress = false;
    private boolean floorUp;
    private SpriteBatch batch;
    private boolean actionButtonPressed = false;
    private Vector3 touchPos;

    public SafetySanta (GameScreen gameScreen, SpriteBatch b) {
        this.gameScreen = gameScreen;
        fading = true;
        this.batch = b;
        santaImg = new Texture("safety_santa_player.png");
        santaRectangle = new Rectangle(0,0,santaImg.getWidth() / 100f, santaImg.getHeight() / 100f);
        touchPos = new Vector3(0, 0, 0);
    }

    public void update() {
        canMove = true;
        actionButtonPressed = false;
        if (Gdx.input.isTouched()) {
            float realX = Gdx.input.getX();
            float realY = Gdx.input.getY();

            touchPos.x = realX;
            touchPos.y = realY;
            gameScreen.game.getCamera().unproject(touchPos);

            if (touchPos.x > gameScreen.hud.getButtonActionRect().x && touchPos.x < gameScreen.hud.getButtonActionRect().x + gameScreen.hud.getButtonActionRect().getWidth() &&
                    touchPos.y > gameScreen.hud.getButtonActionRect().y && touchPos.y < gameScreen.hud.getButtonActionRect().y + gameScreen.hud.getButtonActionRect().getHeight()) {
                if (!actionButtonPressed){
                    Gdx.app.log("ActionButton", "Pressed");
                    actionButtonPressed = true;
                }
            }

            if (touchPos.x > gameScreen.hud.getButtonLeftRect().x && touchPos.x < gameScreen.hud.getButtonLeftRect().x + gameScreen.hud.getButtonLeftRect().getWidth() &&
                    touchPos.y > gameScreen.hud.getButtonLeftRect().y && touchPos.y < gameScreen.hud.getButtonLeftRect().y + gameScreen.hud.getButtonLeftRect().getHeight()) {

                for (RectangleMapObject rectangleObject : gameScreen.initialize.getRectangleWallObjects()) {
                    Rectangle rectangle = rectangleObject.getRectangle();
                    if (rectangle.contains(santaRectangle.x - 0.05f,
                            (santaRectangle.y + santaRectangle.height / 2))) {
                        canMove = false;
                    }
                }

                if (canMove) {
                    santaRectangle.x -= santaSpeed * delta;
                    goRight = false;
                }
            }

            if (touchPos.x > gameScreen.hud.getButtonRightRect().x && touchPos.x < gameScreen.hud.getButtonRightRect().x + gameScreen.hud.getButtonRightRect().getWidth() &&
                    touchPos.y > gameScreen.hud.getButtonRightRect().y && touchPos.y < gameScreen.hud.getButtonRightRect().y + gameScreen.hud.getButtonRightRect().getHeight()) {

                for (RectangleMapObject rectangleObject : gameScreen.initialize.getRectangleWallObjects()) {
                    Rectangle rectangle = rectangleObject.getRectangle();
                    if (rectangle.contains(santaRectangle.x + santaRectangle.width + 0.05f,
                            santaRectangle.y + (santaRectangle.height / 2))) {
                        canMove = false;
                    }
                }

                if (canMove) {
                    santaRectangle.x += santaSpeed * delta;
                    goRight = true;
                }
            }
        }
    }

    public void checkInput() {

    }

    public void checkActions() {
        checkFloorChange();

        if (floorChangeInProgress) {
            if (floorUp) {
                changeFloorUp(batch);
                // Gdx.app.log("mennään", "ylös");
            } else {
                changeFloorDown(batch);
                Gdx.app.log("mennään", "alas");
            }
        } else {
            draw(batch);
        }
    }

    private void checkFloorChange() {
        for (RectangleMapObject rectangleObject : gameScreen.initialize.getRectangleUpObjects()) {
            Rectangle rectangle = rectangleObject.getRectangle();

            if (canChangeFloor) {
                if (rectangle.overlaps(getSantaRectangle()) &&
                        (Gdx.input.isKeyJustPressed(Input.Keys.H) || actionButtonPressed)) {
                    canChangeFloor = false;
                    floorChangeInProgress = true;
                    floorUp = true;
                }
            }
        }

        for (RectangleMapObject rectangleObject : gameScreen.initialize.getRectangleDownObjects()) {
            Rectangle rectangle = rectangleObject.getRectangle();

            if (canChangeFloor) {
                if (getSantaRectangle().overlaps(rectangle) &&
                        (Gdx.input.isKeyJustPressed(Input.Keys.H) || actionButtonPressed)) {
                    canChangeFloor = false;
                    floorChangeInProgress = true;
                    floorUp = false;
                }
            }
        }

        if (!canChangeFloor) {
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    canChangeFloor = true;
                }
            }, 3);
        }
    }

    public void moveSantaKeyboard() {
        canMove = true;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

            for (RectangleMapObject rectangleObject : gameScreen.initialize.getRectangleWallObjects()) {
                Rectangle rectangle = rectangleObject.getRectangle();
                if (rectangle.contains(santaRectangle.x - 0.05f,
                        (santaRectangle.y + santaRectangle.height / 2))) {
                    canMove = false;
                }
            }

            if (canMove) {
                santaRectangle.x -= santaSpeed * delta;
                goRight = false;
            } else {

            }
        }

        canMove = true;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {

            for (RectangleMapObject rectangleObject : gameScreen.initialize.getRectangleWallObjects()) {
                Rectangle rectangle = rectangleObject.getRectangle();
                if (rectangle.contains(santaRectangle.x + santaRectangle.width + 0.05f,
                        santaRectangle.y + (santaRectangle.height / 2))) {
                    canMove = false;
                }
            }

            if (canMove) {
                santaRectangle.x += santaSpeed * delta;
                goRight = true;
            }
        }
    }

    public void draw(SpriteBatch sp)  {
        // batch.setColor(1f, 1f, 1f, alpha);
        sp.draw(santaImg,
                santaRectangle.x,
                santaRectangle.y,
                santaImg.getWidth() / 100f,
                santaImg.getHeight() / 100f,
                0,
                0,
                santaImg.getWidth(),
                santaImg.getHeight(),
                !goRight,
                false);
    }

    public float getX() {
        return santaRectangle.getX();
    }

    public float getY() {
        return santaRectangle.getY();
    }

    public void setX(float x) {
        santaRectangle.setX(x);
    }

    public void setY(float y) {
        santaRectangle.setY(y);
    }

    public Rectangle getSantaRectangle() {
        return santaRectangle;
    }

    public float getRectangleWidth() {
        return santaRectangle.getWidth();
    }

    public float getRectangleHeight() {
        return santaRectangle.getHeight();
    }

    public void changeFloorUp(SpriteBatch batch) {

        batch.setColor(1f, 1f, 1f, alpha);
        batch.draw(santaImg,
                santaRectangle.x,
                santaRectangle.y,
                santaImg.getWidth() / 100f,
                santaImg.getHeight() / 100f,
                0,
                0,
                santaImg.getWidth(),
                santaImg.getHeight(),
                !goRight,
                false);

        if (alpha >= 0 && fading) {
            alpha -= 0.05f;
        }

        if (alpha < 1f && !fading) {
            Gdx.app.log("Going up", "fade out");
            alpha += 0.05f;
        }

        if (alpha < 0.05f && fading && movement <= 2.23f && floorChangeInProgress) {
            Gdx.app.log("Going up", "moving");
            santaRectangle.y += 0.04f;
            movement += 0.04f;
        }

        if (alpha <= 0 && movement >= 2.23f) {
            fading = false;
        }

        if (!fading && alpha > 0.95f) {
            Gdx.app.log("Going up", "näkyvissä");
            fading = true;
            alpha = 1;
            movement = 0;
            floorChangeInProgress = false;
        }
    }

    public void changeFloorDown(SpriteBatch batch) {
        batch.setColor(1f, 1f, 1f, alpha);
        batch.draw(santaImg,
                santaRectangle.x,
                santaRectangle.y,
                santaImg.getWidth() / 100f,
                santaImg.getHeight() / 100f,
                0,
                0,
                santaImg.getWidth(),
                santaImg.getHeight(),
                !goRight,
                false);

        if (alpha >= 0 && fading) {
            alpha -= 0.05f;
        }

        if (alpha < 1f && !fading) {
            Gdx.app.log("Going down", "fade in");
            alpha += 0.05f;
        }

        if (alpha < 0.05f && fading && movement <= 2.23f && floorChangeInProgress) {
            Gdx.app.log("Going down", "moving");
            santaRectangle.y -= 0.04f;
            movement += 0.04f;
        }

        if (alpha <= 0 && movement >= 2.23f) {
            // Gdx.app.log("", "alpha = 0");
            fading = false;
        }

        if (!fading && alpha > 0.95f) {
            Gdx.app.log("Going down", "näkyvissä");
            fading = true;
            alpha = 1;
            movement = 0;
            floorChangeInProgress = false;

        }
    }
}