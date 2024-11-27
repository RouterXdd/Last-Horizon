package lh.classes.blocks.effect;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.util.Time;
import lh.classes.types.LHStats;
import lh.content.LHUnitTypes;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.content.UnitTypes;
import mindustry.entities.Damage;
import mindustry.entities.Effect;
import mindustry.entities.Units;
import mindustry.game.Team;
import mindustry.gen.Unit;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.StatusEffect;
import mindustry.type.UnitType;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;
import mindustry.world.meta.Stats;

import static mindustry.Vars.state;
import static mindustry.Vars.tilesize;

public class CoreBlockAlt extends CoreBlock {
    public float unitTime = 3500;
    public float exploRange = 7 * 8;
    public float exploDamage = 400;
    public float statusRange = 7 * 8;
    public float statusDuration = 15 * 60;
    public StatusEffect status;
    public Effect exploFx = Fx.reactorExplosion;
    public Color exploColor = Pal.reactorPurple;
    public CoreBlockAlt(String name) {
        super(name);

    }
    @Override
    public void setStats(){
        super.setStats();
        if (status != null) {
            stats.add(Stat.range, statusRange / tilesize, StatUnit.blocks);
            stats.add(LHStats.duration, statusDuration / 60, StatUnit.seconds);
            stats.add(LHStats.status, status.emoji() + "" + status.localizedName);
        }
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
        UnitType unitSpawnT2 = getProduction();
        @Override
        public void onDestroyed(){
            super.onDestroyed();
            Damage.damage(team, x, y, exploRange * 8, exploDamage);
            exploFx.at(x, y, exploColor);
            Units.nearbyEnemies(this.team, x, y, statusRange, other -> {
                other.apply(status, statusDuration);
            });
        }
        public UnitType getProduction(){
            UnitType[][] unit = {
                    {UnitTypes.flare, UnitTypes.mono, LHUnitTypes.bit, LHUnitTypes.trap, LHUnitTypes.spark}
            };
            return unit[Mathf.random(unit.length - 1)][Mathf.random(0, 3)];
        }
        public UnitType getProductionT2(){
            UnitType[][] unit = {
                    {UnitTypes.horizon, UnitTypes.poly, LHUnitTypes.byteU, LHUnitTypes.seal, LHUnitTypes.flick}
            };
            return unit[Mathf.random(unit.length - 1)][Mathf.random(0, 3)];
        }
        @Override
        public void update(){
            super.update();
            if (this.team == state.rules.waveTeam && !state.rules.pvp){
                unitTimeAfter += edelta();
                    if (unitTimeAfter >= unitTime && unitSpawn != null) {
                        unitSpawn.spawn(team, x + Mathf.random(0.1f), y + Mathf.random(0.1f));
                        unitSpawn = getProduction();
                        unitTimeAfter = 0;

                    }
            }
        }
        @Override
        public void draw(){
            super.draw();
            if (this.team == state.rules.waveTeam && !state.rules.pvp) {
                if (Time.time < 20000) {
                    Draw.draw(Layer.blockOver, () -> Drawf.construct(this, unitSpawn, 0f, unitTimeAfter / unitTime * 1.5f, 3, unitTimeAfter));
                } else {
                    Draw.draw(Layer.blockOver, () -> Drawf.construct(this, unitSpawnT2, 0f, unitTimeAfter / unitTime * 1.5f, 3, unitTimeAfter));
                }
            }
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
