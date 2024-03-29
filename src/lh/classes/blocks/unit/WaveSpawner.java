package lh.classes.blocks.unit;

import arc.graphics.Camera;
import arc.util.Time;
import mindustry.Vars;
import mindustry.gen.Building;
import mindustry.gen.Unit;
import mindustry.type.StatusEffect;
import mindustry.type.UnitType;
import mindustry.world.Block;

public class WaveSpawner extends Block {
    public UnitType unit;
    public float spawnTime = 150;
    public int spawnWave = 45;
    public StatusEffect status;

    public WaveSpawner(String name) {
        super(name);
    }
    public class WaveSpawnerBuild extends Building {
        boolean spawned = false;
        @Override
        public void update(){
            if (Vars.state.wave == spawnWave && !spawned){
                Time.run(spawnTime, () -> {
                    Unit u = unit.spawn(team, x, y);
                    if (status != null) {
                        u.apply(status, Float.MAX_VALUE);
                    }
                    spawned = true;
                    this.remove();
                });
            }
        }
    }
}
