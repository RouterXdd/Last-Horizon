package lh.classes.entities.abilities;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import arc.scene.ui.layout.Table;
import arc.util.Strings;
import arc.util.Time;
import arc.util.Tmp;
import lh.graphics.LHPal;
import mindustry.entities.Lightning;
import mindustry.entities.Units;
import mindustry.entities.abilities.Ability;
import mindustry.gen.Unit;
import mindustry.graphics.Layer;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;
import mindustry.world.meta.StatValues;
import mindustry.world.meta.Stats;

import static mindustry.Vars.indexer;
import static mindustry.Vars.tilesize;

public class StaticFieldAbility extends Ability {
    public float reload, range, damage, x = 0, y = 0;
    public int shots;
    public Color color = LHPal.tauLightlish;
    float reloadTime = reload;
    StaticFieldAbility(){}

    public StaticFieldAbility(float reload, float range, float damage, int shots){
        this.reload = reload;
        this.range = range;
        this.damage = damage;
        this.shots = shots;
    }
    @Override
    public void addStats(Table t){
        t.add("[lightgray]" + Stat.reload.localized() + ": [white]" + Strings.autoFixed(60 / reload, 2) + " " + StatUnit.seconds.localized());
        t.row();
        t.add("[lightgray]" + Stat.range.localized() + ": [white]" + Strings.autoFixed(range / tilesize, 2) + " " +StatUnit.blocks.localized());
        t.row();
        t.add("[lightgray]" + Stat.damage.localized() + ": [white]" + Strings.autoFixed(range / tilesize, 2));
    }
    @Override
    public void update(Unit unit){
        reloadTime -= Time.delta * unit.reloadMultiplier;
        if (reloadTime <= 0){
            for(int i = 0; i < shots; i++) {
                Tmp.v1.trns(Mathf.random(360f), Mathf.random(range));
                Lightning.create(unit.team, color, damage * unit.damageMultiplier, x + unit.x + Tmp.v1.x, y + unit.y + Tmp.v1.y, Mathf.random(360f), Mathf.random(4, 7));
                reloadTime = reload;
            }
        }
    }
    @Override
    public void draw(Unit unit){
        super.draw(unit);

        Draw.z(Layer.light);
        Draw.color(color);
        Tmp.v1.trns(unit.rotation - 90, x, y);
        float rx = unit.x + Tmp.v1.x, ry = unit.y + Tmp.v1.y;
        for(int i = 0; i < 4; i++){
            float rot = i * 360f/4 + Time.time * 4 * unit.reloadMultiplier;
            Lines.arc(rx, ry, range, 0.2f, rot);
        }
    }
}
