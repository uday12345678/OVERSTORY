package com.mygdx.overstory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ColorAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


/**
 * Created by chris on 1/17/2017.
 */
public class Enemy extends Character {
    float GLD; //Amount of gold enemy drops

    long time = 0;
    int enrandmvmt = 0;
    float xp = 1f; //Amount of xp enemy drops


    public Enemy(Sprite sprite, float health, float DMG, float x, float y, String Name) {
        super(sprite, health, DMG, x , y, Name);
        //AI movement


    }

    public void isHit(Character character) {
        Gdx.app.log(getName(), "IS HIT");
        //Actions.color(Color.RED, 5f);
    }

    public void removeHealth(float amount) {
        health -= amount;
        Gdx.app.log(getName(), String.valueOf(health));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
        if(health <= 0){
            this.remove();
            sprite.getTexture().dispose();
        }

        if(System.currentTimeMillis() > time) {
            enrandmvmt = (int)(Math.random() * 5 + 1);
            switch (enrandmvmt){
                case 1: addAction(Actions.moveBy(100f, 0f, .2f));
                    time = System.currentTimeMillis() + 400;
                    break;
                case 2: addAction(Actions.moveBy(-100f, 0f, .2f));
                    time = System.currentTimeMillis() + 400;
                    break;
                case 3: addAction(Actions.moveBy(0f, 100f, .2f));
                    time = System.currentTimeMillis() + 400;
                    break;
                case 4: addAction(Actions.moveBy(0f, -100f, .2f));
                    time = System.currentTimeMillis() + 400;
                    break;
                case 5: addAction(Actions.moveBy(0f, 0f, .2f));
                    time = System.currentTimeMillis() + 400;
                break;
            }

        }
    }

    @Override
    public void isHit() { //Currently damages enemy for 50% to 150% of DMG (FEEL FREE TO MODIFY)
        removeHealth((float)(int)(om.getPlayer().DMG * (float)(Math.random() * 11 + 5)/10));
        //removeHealth((om.getPlayer().DMG);
    }


}
