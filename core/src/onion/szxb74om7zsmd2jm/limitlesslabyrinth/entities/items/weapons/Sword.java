package onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.items.Weapon;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.Projectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.projectiles.SwordProjectile;
import onion.szxb74om7zsmd2jm.limitlesslabyrinth.entities.spriteTextures;

/**
 * Created by chris on 2/1/2017.
 */
public class Sword extends Weapon {
    public Sword(){
        sprite = new Sprite(spriteTextures.SwordItemSprite);
        dmg = 200f;
        lvl = 1;
        type = "projectile";
        cooldown = 0;
        XPtoLVL = 10 * lvl;
    }

    @Override
    public Projectile getProjectile(float x1, float y1, float x2, float y2) {
        return new SwordProjectile(x1, y1, x2, y2, dmg, this);
    }
}
