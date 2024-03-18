package lh.classes.blocks.power;

import arc.*;
import arc.math.Mathf;
import arc.util.*;
import lh.content.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.game.*;
import mindustry.graphics.Pal;
import mindustry.world.*;
import mindustry.world.blocks.defense.turrets.*;

import mindustry.world.blocks.power.PowerGenerator;

import static mindustry.Vars.*;
public class PowerLeaker extends PowerGenerator {
    public float captureInvicibility = 60f * 15f;
    public PowerLeaker(String name) {
        super(name);
        replaceable = alwaysReplace = breakable = false;
        update = true;
        buildType = PowerLeakerBuild::new;
    }
    @Override
    public boolean canBreak(Tile tile){
        return state.rules.editor || state.rules.infiniteResources;
    }
    public class PowerLeakerBuild extends GeneratorBuild {
        public Team lastDamage = Team.derelict;

        public float iframes = -1f;
        @Override
        public void update(){
            productionEfficiency = 1;
            if (timer(0, 2.5f)){
                Lightning.create(team, team.color, 0, x + Mathf.random(0, 1), y + Mathf.random(0, 1), Mathf.random(0, 360), Mathf.random(0, 5));
            }

        }

        @Override
        public void damage(@Nullable Team source, float damage){
            if(iframes > 0) return;

            if(source != null && source != team){
                lastDamage = source;
            }
            super.damage(source, damage);
        }
        @Override
        public void onDestroyed(){
            //just create an explosion, no fire. this prevents immediate recapture
            Damage.dynamicExplosion(x, y, 0, 1, 5, tilesize * block.size / 2f, state.rules.damageExplosions);
            Fx.commandSend.at(x, y, 140f);
            Fx.bigShockwave.at(x, y, 90);
        }
        @Override
        public void afterDestroyed(){
            if(!net.client()){
                tile.setBlock(block, lastDamage);
            }

            //delay so clients don't destroy it afterwards
            Core.app.post(() -> tile.setNet(block, lastDamage, 0));

            //building does not exist on client yet
            if(!net.client()){
                //leaker is invincible for several seconds to prevent recapture
                ((PowerLeaker.PowerLeakerBuild)tile.build).iframes = captureInvicibility;
            }
        }
    }
}
