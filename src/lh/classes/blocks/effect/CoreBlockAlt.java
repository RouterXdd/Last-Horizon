package lh.classes.blocks.effect;

import arc.graphics.g2d.TextureRegion;
import mindustry.content.Fx;
import mindustry.entities.Damage;
import mindustry.entities.Effect;
import mindustry.game.Team;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.storage.CoreBlock;

import static mindustry.Vars.state;

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
    public boolean canPlaceOn(Tile tile, Team team, int rotation){
        if(tile == null) return false;
        //in the editor, you can place them anywhere for convenience
        if(state.isEditor()) return true;

        CoreBuild core = team.core();

        //special floor upon which cores can be placed
        tile.getLinkedTilesAs(this, tempTiles);
        if(!tempTiles.contains(o -> !o.floor().allowCorePlacement || o.block() instanceof CoreBlock)){
            return true;
        }

        //must have all requirements
        if(core == null || (!state.rules.infiniteResources && !core.items.has(requirements, state.rules.buildCostMultiplier))) return false;

        return tile.block() instanceof CoreBlock && size > tile.block().size && (!requiresCoreZone || tempTiles.allMatch(o -> o.floor().allowCorePlacement));
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
