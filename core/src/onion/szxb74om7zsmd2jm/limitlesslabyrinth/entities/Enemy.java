package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics.Detection;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by chris on 1/21/2017.
 */
public class Enemy extends Entity{
    private ArrayList<Integer> canmove = new ArrayList<Integer>(); //Specifies which directions an enemy can move
    public int getXpDrop() {
        return xpDrop;
    }
    private int xpDrop;
    Sprite healthBar = new Sprite(new Texture("greenbar.png"));
    Sprite lostHealthBar = new Sprite(new Texture("redbar.png"));

    public Enemy(Sprite sprite, float x, float y, float dmg, float health, int xpDrop, TiledMapTileLayer collisionLayer) {
        super(sprite, x, y, dmg, health, collisionLayer);
        this.xpDrop = xpDrop;
        detection = new Detection(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight(), 100);
    }

    @Override
    public void draw(Batch batch) {
        sprite.draw(batch);
        healthBar.setPosition(sprite.getX(), sprite.getY() + sprite.getHeight());
        lostHealthBar.setPosition(sprite.getX(), sprite.getY() + sprite.getHeight());
        lostHealthBar.draw(batch);
        healthBar.draw(batch);
        move();

        //Enemy checking for player
        if(detection.isInRadius(this)){
            //Enemy is hit
            if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
                //Enemy loses health and is represented on the health bar
                health -= pl.getPlayer().getDmg();
                healthBar.setScale(healthBar.getScaleX() - pl.getPlayer().getDmg() / fullHealth, healthBar.getScaleY());
                //Gdx.app.log("Enemy Health", String.valueOf(health));
            }
        }
     }

    @Override
    public void move() {
        //Checks what side of player enemy is on
        //Above
        if (sprite.getY() + sprite.getHeight() / 2 > pl.getPlayer().getSprite().getY() + pl.getPlayer().getSprite().getHeight() / 2) {
            canmove.add(3);
        }
        //Below
        if (sprite.getY() + sprite.getHeight() / 2 < pl.getPlayer().getSprite().getY() + pl.getPlayer().getSprite().getHeight() / 2) {
            canmove.add(4);
        }
        //Left
        if(sprite.getX() + sprite.getWidth()/2 > pl.getPlayer().getSprite().getX() + pl.getPlayer().getSprite().getWidth()/2){
            canmove.add(2);
        }
        //Right
        if(sprite.getX() + sprite.getWidth()/2 < pl.getPlayer().getSprite().getX() + pl.getPlayer().getSprite().getWidth()/2){
            canmove.add(1);
        }
        int num;
        Random rand = new Random();
            if(canmove.size() == 0){
                num = rand.nextInt(5 + 1);
            }
            else{
                num = canmove.get(rand.nextInt(canmove.size()));
            }

        if(!detection.isInSmallRadius(this)) {
            if (num == 4) { //Up
                if (checkCollision(0f, sprite.getHeight(), 0f, speed)) {
                    sprite.setY(sprite.getY() + speed);
                }
            }
            if (num == 3) { //Down
                if (checkCollision(0f, -sprite.getHeight(), 0f, -speed)) {
                    sprite.setY(sprite.getY() + -speed);
                }
            }
            if (num == 2) { //Left
                if (checkCollision(-sprite.getWidth(), 0f, -speed, 0f)) {
                    sprite.setX(sprite.getX() + -speed);
                }
            }
            if (num == 1) { // Right
                if (checkCollision(sprite.getWidth(), 0f, speed, 0f)) {
                    sprite.setX(sprite.getX() + speed);
                }
            }
        }
        canmove.clear();
    }

}
