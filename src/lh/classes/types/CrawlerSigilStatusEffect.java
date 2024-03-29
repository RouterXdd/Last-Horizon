package lh.classes.types;

import arc.math.Mathf;
import arc.util.Time;
import mindustry.content.UnitTypes;
import mindustry.gen.Unit;
import mindustry.type.UnitType;

public class CrawlerSigilStatusEffect extends SigilStatusEffect{
    public float timeS = 0;
    public float timeLimit = 160;
    public UnitType crawler = UnitTypes.crawler;

    public CrawlerSigilStatusEffect(String name) {
        super(name);
    }
    @Override
    public void update(Unit unit, float time){
        super.update(unit, time);
        if (timeS >= timeLimit){
            Unit unitS = crawler.create(unit.team);
            unitS.set(unit.x + Mathf.random(-0.001f,0.001f), unit.y + Mathf.random(-0.001f,0.001f));
            unitS.rotation = Mathf.random(0f,360f);
            unitS.add();
            timeS = 0;
        } else {
            timeS += Time.delta;
        }
    }
}
