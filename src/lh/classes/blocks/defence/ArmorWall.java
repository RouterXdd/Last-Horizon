package lh.classes.blocks.defence;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.util.Nullable;
import mindustry.entities.bullet.BulletType;
import mindustry.graphics.Layer;
import mindustry.world.blocks.defense.Wall;

public class ArmorWall extends Wall {
    public TextureRegion armor1, armor2;
    public boolean stage2fragile = false;
    public int shots = 2;
    public float fragileTime = 25f;
    public @Nullable BulletType bullet;
    public float armorBonus = 5;

    public ArmorWall(String name) {
        super(name);
        update = true;
    }
    @Override
    public void load(){
        super.load();

        armor1 = Core.atlas.find(this.name + "-armor-1");
        armor2 = Core.atlas.find(this.name + "-armor-2");
    }
    public class ArmorWallsBuild extends WallBuild {
        int stage = 0;
        float initialArmor = armor;
        @Override
        public void updateTile(){
            if (stage == 1){
                armor = initialArmor + armorBonus;
            } else if (stage == 2) {
                armor = initialArmor + armorBonus * 2;
            } else {
                armor = initialArmor;
            }
            if (health <= maxHealth / 100 * 60 && health >= maxHealth / 100 * 30){
                stage = 1;
            } else if (health <= maxHealth / 100 * 30) {
                stage = 2;
            }else {
                stage = 0;
            }
            if (stage == 2 && stage2fragile && bullet != null && timer(0, fragileTime)){
                for(int i = 0; i < shots; i++){
                    bullet.create(this, x, y, (360f / shots) * i + Mathf.random(360));
                }
            }
        }
        @Override
        public void draw(){
            super.draw();
            Draw.z(Layer.blockAfterCracks);
            if (stage >= 1){
                Draw.rect(armor1, x, y);
                if (stage >= 2){
                    Draw.rect(armor2, x, y);
                }
            }
            Draw.reset();
        }
    }
}
