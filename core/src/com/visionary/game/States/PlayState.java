package com.visionary.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.visionary.game.FlappyDemo;
import com.visionary.game.Sprites.Bird;
import com.visionary.game.Sprites.Tube;

public class PlayState extends State{
    private static final int TUBE_SPACING = 281;
    private static final int TUBE_COUNT = 4;

    private Bird bird;
    private Texture bg;
    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,100);
        cam.setToOrtho(false, FlappyDemo.width/2, FlappyDemo.height/2);
        bg = new Texture("background.jpg");

        tubes = new Array<Tube>();

        for (int i=0; i<= TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);

        cam.position.x = bird.getPosition().x + 80;

        for (int i=0; i<tubes.size; i++){
            Tube tube = tubes.get(i);

            if(cam.position.x - (cam.viewportWidth/2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }

            if(tube.collides(bird.getBounds())){
                gsm.set(new PlayState(gsm)); //move to end game instead
            }
        }

        if(bird.getPosition().y <= 0){
            gsm.set(new PlayState(gsm));
        }

        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth /2),0, FlappyDemo.width/2, FlappyDemo.height/2);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y, 50, 50);

        for(Tube tube: tubes ){
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }

        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        for(Tube tube: tubes){
            tube.dispose();
        }
    }
}
