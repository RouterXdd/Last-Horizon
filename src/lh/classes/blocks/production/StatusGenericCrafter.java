package lh.classes.blocks.production;

import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.math.Angles;
import arc.math.Mathf;
import arc.util.Time;
import arc.util.Tmp;
import lh.content.LHStatuses;
import lh.graphics.LHPal;
import mindustry.entities.Units;
import mindustry.game.Team;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.logic.LStatements;
import mindustry.type.StatusEffect;
import mindustry.world.blocks.production.GenericCrafter;

public class StatusGenericCrafter extends GenericCrafter {
    public float statusRange = 8 * 8;
    public StatusEffect status = LHStatuses.zeroGravity;
    public float statusDuration = 12 * 60;
    public float cubeSize = 8;
    public int amount = 4;
    public StatusGenericCrafter(String name) {
        super(name);
    }
    public class StatusGenericCrafterBuild extends GenericCrafterBuild {
        float realRange, sizeC;
        @Override
        public void update(){
            super.update();
            realRange = Mathf.approachDelta(realRange, statusRange * efficiency, efficiency / 20 + 0.1f);
            if (realRange > 0) {
                Units.nearbyEnemies(Team.derelict, x, y, realRange, other -> {
                    other.apply(status, statusDuration);
                });
            }
        }
        @Override
        public void draw(){
            super.draw();
            float baseRot = Time.time * 6;
            Tmp.v1.set(x * 0, y * 0).rotate(progress);
            float haloRot = (2.5f * Time.time * efficiency);
            float
            rx = x + Tmp.v1.x,
            ry = y + Tmp.v1.y;

            if (realRange > 0) {
                for (int v = 0; v < amount; v++) {
                    sizeC = Mathf.approachDelta(sizeC, cubeSize * efficiency, efficiency / 150 + 0.1f);
                    float rot = haloRot + v * 360f / 4;
                    float shapeX = Angles.trnsx(rot, realRange) + rx, shapeY = Angles.trnsy(rot, realRange) + ry;
                    float pointRot = rot + baseRot;
                    Draw.z(Layer.groundUnit + 1);
                    Draw.color(LHPal.terriliumDark);
                    Fill.poly(shapeX, shapeY, 4, sizeC + 2, pointRot);
                    Draw.color(LHPal.terrilium);
                    Fill.poly(shapeX, shapeY, 4, sizeC, pointRot);
                }
            }
        }
    }
}
