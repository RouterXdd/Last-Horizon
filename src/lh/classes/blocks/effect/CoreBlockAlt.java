package lh.classes.blocks.effect;

import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import lh.content.LHUnitTypes;
import mindustry.content.Fx;
import mindustry.content.UnitTypes;
import mindustry.entities.Damage;
import mindustry.entities.Effect;
import mindustry.game.Team;
import mindustry.gen.Unit;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.type.UnitType;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.storage.CoreBlock;

import static mindustry.Vars.state;

public class CoreBlockAlt extends CoreBlock {
    public float unitTime = 500;
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
        float unitTimeAfter;
        UnitType unitSpawn = getProduction();
        @Override
        public void onDestroyed(){
            super.onDestroyed();
            Damage.damage(team, x, y, exploRange * 8, exploDamage);
            exploFx.at(x, y);
        }
        public UnitType getProduction(){
            UnitType[][] unit = {
                    {UnitTypes.flare, UnitTypes.mono, LHUnitTypes.bit, LHUnitTypes.trap, LHUnitTypes.spark}
            };
            return unit[Mathf.random(unit.length - 1)][Mathf.random(0, 3)];
        }
        @Override
        public void update(){
            super.update();
            if (this.team == state.rules.waveTeam && !state.rules.pvp){
                unitTimeAfter += edelta();
                if (unitTimeAfter >= unitTime && unitSpawn != null){
                    unitSpawn.spawn(team,x + Mathf.random(0.1f), y + Mathf.random(0.1f));
                    unitSpawn = getProduction();
                    unitTimeAfter = 0;
                }
            }
        }
        @Override
        public void draw(){
            super.draw();
            if (this.team == state.rules.waveTeam && !state.rules.pvp) {
                Draw.draw(Layer.blockOver, () -> Drawf.construct(this, unitSpawn, 0f, unitTimeAfter / unitTime, 3, unitTimeAfter));
            }
        }
    }
}
