package com.visionary.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 225;

    private Vector3 position;
    private Vector3 velovity;
    private Rectangle bounds;
    private Animation birdAnimation;
    private Texture texture;

    public Bird(int x, int y){
        position = new Vector3(x,y,0);
        velovity = new Vector3(0,0,0);
        texture = new Texture("birdAnimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3,0.1f);
        bounds = new Rectangle(x, y, texture.getWidth()/3, texture.getHeight());
    }

    public void update(float dt){
        birdAnimation.update(dt);
        if(position.y > 0){
            velovity.add(0,GRAVITY,0);
        }

        velovity.scl(dt);
        position.add(MOVEMENT * dt, velovity.y, 0);
        position.add(0,velovity.y,0);

        if(position.y < 0){
            position.y = 0;
        }

        velovity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    public void jump(){
        velovity.y = 250;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){
        texture.dispose();
    }
}
