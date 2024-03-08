package lh.classes.types;

import arc.graphics.g2d.Draw;
import mindustry.gen.Unit;
import mindustry.type.StatusEffect;

public class SigilStatusEffect extends StatusEffect {
    public SigilStatusEffect(String name) {
        super(name);
        permanent = true;
    }
    @Override
    public void draw(Unit unit, float time){
        super.draw(unit);
        //FIXME idk why, but units start looks cursed with them
        if (false) {
            Draw.alpha(0.45f);
            Draw.scl(0.7f);
            Draw.rect(this.uiIcon, unit.x, unit.y);
        }
    }
}
