package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.Bow;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.Shuriken;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.mechanics.Detection;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by taylor hudson on 2/10/2017.
 */
public class AnimatedEnemy extends Enemy {
        private Animation<TextureRegion> animation;
        float stateTime;
        public static DIRECTION ENEMYFACING;
        boolean is64;
        private boolean flipLEFT;
        public AnimatedEnemy(float x, float y, int level, TiledMapTileLayer collisionLayer, int row, int col, float speed, Play.MonsterType monster, String Weapon)
        {
            super(x, y, level, collisionLayer);

            weapon = spriteTextures.giveAWeapon(level, Weapon);

            is64 = false;
            ENEMYFACING = DIRECTION.SOUTH;
            flipLEFT = false;
            this.xpDrop = determineXP(level);
            animation = createAnimation(spriteTextures.sheet(monster), col, row, speed);
            stateTime = 0f;
            this.sprite = new Sprite(spriteTextures.stand(monster));
            if(sprite.getWidth() == 64){
                is64 = true;
                sprite = new Sprite(spriteTextures.basic32);
            }
            this.health = determineHealth(level);
            this.fullHealth = health;
            this.dmg = 10f;
            sprite.setPosition(collisionLayer.getTileWidth() * x-1, collisionLayer.getTileHeight() * y+1);
            detection = new Detection(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight(), 100);
            this.x = sprite.getX();
            this.y = sprite.getY();
        }

        @Override
        public int determineXP(int level) {
            return weapon.getLvl();
        }

        @Override
        public float determineHealth(int level) {
            float h = 20f;
            for(int i = 1; i < level; i++){
                h *= 1.2;
            }
            return h;
        }

        @Override
        public void draw(Batch batch) {
            stateTime += Gdx.graphics.getDeltaTime();
            TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
            sprite.setPosition(x,y);
            rotateSprite(currentFrame);
            //sprite.draw(batch);
            healthBar.setPosition(sprite.getX() - healthBarX, sprite.getY() + sprite.getHeight());
            lostHealthBar.setPosition(sprite.getX(), sprite.getY() + sprite.getHeight());
            lostHealthBar.draw(batch);
            healthBar.draw(batch);

            move();
            //Enemy checking for player
            setDir();
            DMGDETECT();

            if(reloadTime < System.currentTimeMillis() && detection.isInBigRadius(this)){
                Play.getEnemyProjectiles().add(weapon.getProjectile(sprite.getX() + sprite.getWidth()/4, sprite.getY() + sprite.getHeight()/4, Play.getPlayer().getSprite().getX(), Play.getPlayer().getSprite().getY(), "Enemy"));
                reloadTime = (weapon.getCooldown() == 0) ? System.currentTimeMillis() + weapon.getCooldown() + 100 : System.currentTimeMillis() + weapon.getCooldown() + 1000;
            }


        }

        /*
      This method needs a transition smoothing effect but is generalized.
                 */
        public void rotateSprite(TextureRegion currentFrame){

           if(ENEMYFACING == DIRECTION.SOUTH || ENEMYFACING == DIRECTION.SOUTHWEST){
              // currentFrame.flip(true, false); // MAKES ENEMIES FACE UPWARDS
           }
           else if(ENEMYFACING == DIRECTION.NORTH || ENEMYFACING == DIRECTION.NORTHEAST){
                //currentFrame.flip(false, true);
           }
           else if(ENEMYFACING == DIRECTION.EAST || ENEMYFACING == DIRECTION.SOUTHEAST){
              // System.out.println("WEST");
              //currentFrame.flip(true, false);
           }
            else{

           }
            Play.getRenderer().getBatch().draw(currentFrame, x-1,y+1);


        }

        public static Animation<TextureRegion> createAnimation(Texture spriteSheet, int col, int row, float speed){
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet, spriteSheet.getWidth() / col, spriteSheet.getHeight() / row);
        TextureRegion[] spriteFrames = new TextureRegion[row*col];
        int index = 0;
        for(int i = 0; i < row; i++) { // rows
            for (int j = 0; j < col; j++) { // cols
                spriteFrames [index++] = tmp[i][j];
            }
        }
        return new Animation<TextureRegion>(speed, spriteFrames);
    }

        public void setDir() {
            float x1 = Player.CharX;
            float y1 = Player.CharY;
            x = sprite.getX();
            y = sprite.getY();
            float padding = 16f; //pixels padding
            if (x1 + padding > x || x1 - padding > x) {

                if (y1 + padding > y || y1 - padding > y) {
                    ENEMYFACING = DIRECTION.NORTHEAST;
                } else if (y1 + padding < y || y1 - padding < y) {
                    ENEMYFACING = DIRECTION.SOUTHEAST;
                } else {
                    ENEMYFACING = DIRECTION.EAST;
                }
            } else if (x1 + padding < x || x1 - padding < x) {

                if ((y1 + padding) > y || (y1 - padding) > y) {
                    ENEMYFACING = DIRECTION.NORTHWEST;
                    //System.out.println("NORTHWEST");
                } else if ((y1+ padding) < y || (y1 - padding) < y ) {
                    ENEMYFACING = DIRECTION.SOUTHWEST;
                    //System.out.println("SOUTHWEST");
                } else {
                    ENEMYFACING = DIRECTION.WEST;
                    //System.out.println("POSS WEST");
                }
            } else {
                if (y1 + padding > y || y1 - padding > y) {
                    ENEMYFACING = DIRECTION.NORTH;
                } else {
                    ENEMYFACING = DIRECTION.SOUTH;
                }
            }
        }
}
