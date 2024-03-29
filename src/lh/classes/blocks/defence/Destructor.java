package lh.classes.blocks.defence;

import arc.Core;
import arc.graphics.g2d.*;
import arc.util.Nullable;
import mindustry.content.*;
import mindustry.entities.Units;
import mindustry.gen.Building;
import mindustry.graphics.*;
import mindustry.logic.Ranged;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

public class Destructor extends Block {
    public float destItem = 200f;
    public float range = 60f;
    public float damage = 50f;
    public @Nullable ItemStack[] outputItems;
    TextureRegion topRegion;
    public Destructor(String name) {
        super(name);
        hasPower = true;
        hasItems = true;
        solid = true;
        update = true;
        group = BlockGroup.projectors;
    }
    @Override
    public void load(){
        super.load();
        topRegion = Core.atlas.find(this.name + "-top");
    }
    @Override
    public boolean outputsItems(){
        return outputItems != null;
    }
    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);

        Drawf.dashCircle(x * tilesize + offset, y * tilesize + offset, range, Pal.darkerGray);
    }
    @Override
    public void setStats() {
        super.setStats();

        stats.add(Stat.range, range / tilesize, StatUnit.blocks);
        stats.add(Stat.damage, damage, StatUnit.none);
        if((hasItems && itemCapacity > 0) || outputItems != null){
            stats.add(Stat.productionTime, destItem / 60f, StatUnit.seconds);
        }

        if(outputItems != null){
            stats.add(Stat.output, StatValues.items(destItem, outputItems));
        }
    }
    public class DestructorBuild extends Building implements Ranged {
        float opacity = 0;
        public float timerItem;
        @Override
        public float range(){
            return range;
        }
        public void dumpOutputs() {
            if (outputItems != null && timer(timerDump, dumpTime / timeScale)) {
                for (ItemStack output : outputItems) {
                    dump(output.item);
                }
            }
        }
        @Override
        public void updateTile(){
            if (opacity > 0){
                opacity -= delta() / 50;
            }
            if (optionalEfficiency > 0 && timerItem >= destItem){
                Units.nearbyEnemies(team, x, y, range, otherUnit -> {
                        Fx.chainLightning.at(x, y, 0f, Pal.gray, otherUnit);
                        otherUnit.damage(damage);
                        opacity = 1;
                    if(outputItems != null){
                        for(var output : outputItems){
                            for(int i = 0; i < output.amount; i++){
                                offload(output.item);
                            }
                        }
                    }
                });
                timerItem = 0;
            } else {
                timerItem += delta();
            }
            dumpOutputs();
        }
        @Override
        public void drawSelect() {
            Drawf.dashCircle(x, y, range, Pal.darkerGray);
        }
        @Override
        public void draw(){
            super.draw();
            Draw.alpha(opacity);
            Draw.rect(topRegion, x, y);
        }
    }
}
