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

    public SafetySanta (GameScreen gameScreen, SpriteBatch b) {
        this.gameScreen = gameScreen;
        fading = true;
        this.batch = b;
        santaImg = new Texture("safety_santa_player.png");
        santaRectangle = new Rectangle(0,0,santaImg.getWidth() / 100f, santaImg.getHeight() / 100f);


    }

    public void santaUpdate (Vector3 touchPos) {
        canMove = true;
            if (touchPos.x  > gameScreen.buttonActionRect.x && touchPos.x < gameScreen.buttonActionRect.x + gameScreen.buttonActionRect.getWidth() &&
                    touchPos.y > gameScreen.buttonActionRect.y && touchPos.y < gameScreen.buttonActionRect.y + gameScreen.buttonActionRect.getHeight()) {
                    checkActions();
            }

            if (touchPos.x  > gameScreen.buttonLeftRect.x && touchPos.x < gameScreen.buttonLeftRect.x + gameScreen.buttonLeftRect.getWidth() &&
                    touchPos.y > gameScreen.buttonLeftRect.y && touchPos.y < gameScreen.buttonLeftRect.y + gameScreen.buttonLeftRect.getHeight()) {

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

            if (touchPos.x  > gameScreen.buttonRightRect.x && touchPos.x < gameScreen.buttonRightRect.x + gameScreen.buttonRightRect.getWidth() &&
                    touchPos.y > gameScreen.buttonRightRect.y && touchPos.y < gameScreen.buttonRightRect.y + gameScreen.buttonRightRect.getHeight()) {

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

    public void checkActions () {
        checkFloorChange();
        if (!floorChangeInProgress) {
            santaDraw(batch);
        } else {
            if (floorUp) {
                changeFloorUp(batch);
            } else {
                changeFloorDown(batch);
            }
        }
    }

    private void checkFloorChange() {
        for (RectangleMapObject rectangleObject : gameScreen.initialize.getRectangleUpObjects()) {
            Rectangle rectangle = rectangleObject.getRectangle();

            if (canChangeFloor) {
                if (rectangle.overlaps(getSantaRectangle()) &&
                        Gdx.input.isKeyJustPressed(Input.Keys.H)) {
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
                        Gdx.input.isKeyJustPressed(Input.Keys.H)) {
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

    public Rectangle getSantaRectangle () {
        return santaRectangle;
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

    public void santaDraw (SpriteBatch sp)  {
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
            Gdx.app.log("jotain", "fade in");
            alpha += 0.05f;
        }

        if (alpha < 0.05f && fading && movement <= 2.23f && floorChangeInProgress) {
            Gdx.app.log("jotain", "on testattu");
            santaRectangle.y += 0.04f;
            movement += 0.04f;
        }

        if (alpha <= 0 && movement >= 2.23f) {
            Gdx.app.log("", String.valueOf(movement));
            fading = false;
        }

        if (!fading && alpha > 0.95f) {
            Gdx.app.log("", "näkyvissä");
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
            Gdx.app.log("jotain", "fade in");
            alpha += 0.05f;
        }

        if (alpha < 0.05f && fading && movement <= 2.23f && floorChangeInProgress) {
            Gdx.app.log("jotain", "on testattu");
            santaRectangle.y -= 0.04f;
            movement += 0.04f;
        }

        if (alpha <= 0 && movement >= 2.23f) {
            Gdx.app.log("", "alpha = 0");
            fading = false;
        }

        if (!fading && alpha > 0.95f) {
            Gdx.app.log("", "näkyvissä");
            fading = true;
            alpha = 1;
            movement = 0;
            floorChangeInProgress = false;

        }
    }
}