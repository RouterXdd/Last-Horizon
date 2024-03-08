package lh.classes.blocks.effect;

import arc.graphics.g2d.TextureRegion;
import mindustry.content.Fx;
import mindustry.entities.Damage;
import mindustry.entities.Effect;
import mindustry.game.Team;
import mindustry.world.Block;
import mindustry.world.blocks.storage.CoreBlock;

public class CoreBlockAlt extends CoreBlock {
    public float exploRange = 10;
    public float exploDamage = 400;
    public Effect exploFx = Fx.reactorExplosion;
    public CoreBlockAlt(String name) {
        super(name);

    }
    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{region,teamRegions[Team.sharded.id]};
    }
    @Override
    public boolean canReplace(Block other){
        //nope
        return false;
    }
    public class CoreBlockAltBuild extends CoreBuild{
        @Override
        public void onDestroyed(){
            super.onDestroyed();
            Damage.damage(team, x, y, exploRange * 8, exploDamage);
            exploFx.at(x, y);
        }
    }
}
