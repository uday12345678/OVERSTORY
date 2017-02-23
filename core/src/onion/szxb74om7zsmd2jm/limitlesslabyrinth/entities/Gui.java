package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Item;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.*;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Weapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons.traps.Mine;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.screens.Play;

/**
 * Created by 226812 on 1/27/2017.
 */
public class Gui {
    public int getSelected() {
        return selected;
    }
    private static int selected = 0;
    private long time1 = 0;
    private long time2 = 0;
    private long time3 = 0;
    private long time4 = 0;
    private Sprite playerHealthBar;
    private Sprite playerLostHealthBar;
    private Sprite itemBox1;
    private Sprite itemBox2;
    private Sprite itemBox3;
    private Sprite itemBox4;
    private static Sprite[] refreshItem = new Sprite[4];
    private static Item item1;
    private static Item item2;
    private static Item item3;
    private static Item item4;
    private static Item Equipped;
    private Texture HealthBar = new Texture("playerhealthbar.png");
    private Texture LostHealthBar = new Texture("playerredbar.png");
    private Texture ItemBox = new Texture("itemBox.png");
    private Texture SelectedBox = new Texture("selectedBox.png");

    public Backpack getBackpack() {
        return backpack;
    }

    private Backpack backpack = new Backpack();
    private static float healthBarX = 0;
    private static boolean isBackpackOpen = false;
    private static boolean[] isRefreshing = new boolean[4];
    public Item getEquipped() {
        return Equipped;
    }
    public void setEquipped(Item equipped) {
        Equipped = equipped;
        Play.getPlayer().setDmg(Equipped.getDmg());
    }
    public boolean getIsBackpackOpen() {
        return isBackpackOpen;
    }
    public void setIsBackpackOpen(boolean isBackpackOpen) {
        this.isBackpackOpen = isBackpackOpen;
    }
    public Item getItem1() {
        return item1;
    }
    public void setItem1(Item item1) {
        this.item1 = item1;
    }
    public Item getItem2() {
        return item2;
    }
    public void setItem2(Item item2) {
        this.item2 = item2;
    }
    public Item getItem3() {
        return item3;
    }
    public void setItem3(Item item3) {
        this.item3 = item3;
    }
    public Item getItem4() {
        return item4;
    }
    public void setItem4(Item item4) {
        this.item4 = item4;
    }
    public boolean[] getIsRefreshing() {
        return isRefreshing;
    }
    public void setIsRefreshing(boolean isRefreshing, int sel) {
        this.isRefreshing[sel] = isRefreshing;
    }
    public Sprite[] getRefreshItem() {
        return refreshItem;
    }

    public void reset(){
        selected = 0;
        time1 = 0;
        time2 = 0;
        time3 = 0;
        time4 = 0;
        healthBarX = 0;
        isBackpackOpen = false;
        playerHealthBar = new Sprite(HealthBar);
        item1 = new LightningStaff();
        item2 = new Bow();
        item3 = new WizardStaff();
        item4 = new Shuriken();
        refreshItem[0] = new Sprite(spriteTextures.guiRefreshBox);
        refreshItem[1] = new Sprite(spriteTextures.guiRefreshBox);
        refreshItem[2] = new Sprite(spriteTextures.guiRefreshBox);
        refreshItem[3] = new Sprite(spriteTextures.guiRefreshBox);
        refreshItem[0].setScale(0);
        refreshItem[1].setScale(0);
        refreshItem[2].setScale(0);
        refreshItem[3].setScale(0);
        isRefreshing[0] = false;
        isRefreshing[1] = false;
        isRefreshing[2] = false;
        isRefreshing[3] = false;
        Equipped = item1;
    }

    public Gui(){
        playerHealthBar = new Sprite(HealthBar);
        playerLostHealthBar = new Sprite(LostHealthBar);
        itemBox1 = new Sprite(SelectedBox);
        itemBox2 = new Sprite(ItemBox);
        itemBox3 = new Sprite(ItemBox);
        itemBox4 = new Sprite(ItemBox);
        item1 = new LightningStaff();
        item2 = new Bow();
        item3 = new WizardStaff();
        item4 = new Shuriken();
        refreshItem[0] = new Sprite(spriteTextures.guiRefreshBox);
        refreshItem[1] = new Sprite(spriteTextures.guiRefreshBox);
        refreshItem[2] = new Sprite(spriteTextures.guiRefreshBox);
        refreshItem[3] = new Sprite(spriteTextures.guiRefreshBox);
        refreshItem[0].setScale(0);
        refreshItem[1].setScale(0);
        refreshItem[2].setScale(0);
        refreshItem[3].setScale(0);
        isRefreshing[0] = false;
        isRefreshing[1] = false;
        isRefreshing[2] = false;
        isRefreshing[3] = false;
        Equipped = item1;
    }

    public void input(){
        /** Switching between item boxes */
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)){
            itemBox1 = new Sprite(SelectedBox);
            itemBox2 = new Sprite(ItemBox);
            itemBox3 = new Sprite(ItemBox);
            itemBox4 = new Sprite(ItemBox);
            selected = 0;
            Equipped = item1;
            Play.getPlayer().setDmg(Equipped.getDmg());
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)){
            itemBox2 = new Sprite(SelectedBox);
            itemBox1 = new Sprite(ItemBox);
            itemBox3 = new Sprite(ItemBox);
            itemBox4 = new Sprite(ItemBox);
            selected = 1;
            Equipped = item2;
            Play.getPlayer().setDmg(Equipped.getDmg());
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)){
            itemBox3 = new Sprite(SelectedBox);
            itemBox2 = new Sprite(ItemBox);
            itemBox1 = new Sprite(ItemBox);
            itemBox4 = new Sprite(ItemBox);
            selected = 2;
            Equipped = item3;
            Play.getPlayer().setDmg(Equipped.getDmg());
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)){
            itemBox4 = new Sprite(SelectedBox);
            itemBox2 = new Sprite(ItemBox);
            itemBox3 = new Sprite(ItemBox);
            itemBox1 = new Sprite(ItemBox);
            selected = 3;
            Equipped = item4;
            Play.getPlayer().setDmg(Equipped.getDmg());
        }

        /** Backpack open / close */
        if(Gdx.input.isKeyJustPressed(Input.Keys.TAB)){
            if(isBackpackOpen) {
                isBackpackOpen = false;
            }
            else {
                isBackpackOpen = true;
            }
        }


        /** Refer here to know how to remove health from player properly */
        if(Gdx.input.isKeyPressed(Input.Keys.N)){
            Play.getPlayer().setHealth(Play.getPlayer().getHealth() - 10f);
            healthBarX += ((10f / Play.getPlayer().getFullHealth()) * playerHealthBar.getWidth()) / 2;
            playerHealthBar.setScale(playerHealthBar.getScaleX() - 10f / Play.getPlayer().getFullHealth(), playerHealthBar.getScaleY());
        }
    }

    public void update(){

        /** Health Bar Update */
        playerHealthBar.setPosition(Play.getCamera().position.x - Play.getCamera().viewportWidth/2 + 10 - healthBarX, Play.getCamera().position.y + Play.getCamera().viewportHeight/2 - 28);
        playerLostHealthBar.setPosition(Play.getCamera().position.x - Play.getCamera().viewportWidth/2 + 10, Play.getCamera().position.y + Play.getCamera().viewportHeight/2 - 28);
        playerLostHealthBar.draw(Play.getRenderer().getBatch());
        playerHealthBar.draw(Play.getRenderer().getBatch());

        /** itemBox update */
        itemBox1.setPosition(Play.getCamera().position.x - 150 - itemBox1.getWidth()/2, Play.getCamera().position.y - Play.getCamera().viewportHeight/2 + 70);
        itemBox2.setPosition(Play.getCamera().position.x - 50 - itemBox2.getWidth()/2, Play.getCamera().position.y - Play.getCamera().viewportHeight/2 + 70);
        itemBox3.setPosition(Play.getCamera().position.x + 50 - itemBox3.getWidth()/2, Play.getCamera().position.y - Play.getCamera().viewportHeight/2 + 70);
        itemBox4.setPosition(Play.getCamera().position.x + 150 - itemBox4.getWidth()/2, Play.getCamera().position.y - Play.getCamera().viewportHeight/2 + 70);
        itemBox1.draw(Play.getRenderer().getBatch());
        itemBox2.draw(Play.getRenderer().getBatch());
        itemBox3.draw(Play.getRenderer().getBatch());
        itemBox4.draw(Play.getRenderer().getBatch());

        /** Items Update */
        item1.getSprite().setPosition(itemBox1.getX(), itemBox1.getY());
        item2.getSprite().setPosition(itemBox2.getX(), itemBox2.getY());
        item3.getSprite().setPosition(itemBox3.getX(), itemBox3.getY());
        item4.getSprite().setPosition(itemBox4.getX(), itemBox4.getY());
        item1.getSprite().draw(Play.getRenderer().getBatch());
        item2.getSprite().draw(Play.getRenderer().getBatch());
        item3.getSprite().draw(Play.getRenderer().getBatch());
        item4.getSprite().draw(Play.getRenderer().getBatch());

        /** Handle the cool down of items */
        refreshItem[0].setPosition(itemBox1.getX(), itemBox1.getY());
        refreshItem[1].setPosition(itemBox2.getX(), itemBox2.getY());
        refreshItem[2].setPosition(itemBox3.getX(), itemBox3.getY());
        refreshItem[3].setPosition(itemBox4.getX(), itemBox4.getY());
        refreshItem[0].draw(Play.getRenderer().getBatch());
        refreshItem[1].draw(Play.getRenderer().getBatch());
        refreshItem[2].draw(Play.getRenderer().getBatch());
        refreshItem[3].draw(Play.getRenderer().getBatch());
        if(isRefreshing[0] && System.currentTimeMillis() > time1){
            refreshItem[0].setScale(refreshItem[0].getScaleX() - .1f, refreshItem[0].getScaleY() - .1f);
            time1 = System.currentTimeMillis() + item1.getCooldown();
            if (refreshItem[0].getScaleX() <= 0) isRefreshing[0] = false;
        }
        if(isRefreshing[1] && System.currentTimeMillis() > time2){
            refreshItem[1].setScale(refreshItem[1].getScaleX() - .1f, refreshItem[1].getScaleY() - .1f);
            time2 = System.currentTimeMillis() + item2.getCooldown();
            if (refreshItem[1].getScaleX() <= 0) isRefreshing[1] = false;
        }
        if(isRefreshing[2] && System.currentTimeMillis() > time3){
            refreshItem[2].setScale(refreshItem[2].getScaleX() - .1f, refreshItem[2].getScaleY() - .1f);
            time3 = System.currentTimeMillis() + item3.getCooldown();
            if (refreshItem[2].getScaleX() <= 0) isRefreshing[2] = false;
        }
        if(isRefreshing[3] && System.currentTimeMillis() > time4){
            refreshItem[3].setScale(refreshItem[3].getScaleX() - .1f, refreshItem[3].getScaleY() - .1f);
            time4 = System.currentTimeMillis() + item4.getCooldown();
            if (refreshItem[3].getScaleX() <= 0) isRefreshing[3] = false;
        }



        /** Backpack draw */
        if(isBackpackOpen){
            backpack.input();
            backpack.draw();
        }

    }
}
