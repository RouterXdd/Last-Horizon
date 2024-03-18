package lh.content;

import arc.graphics.Color;
import mindustry.content.StatusEffects;
import mindustry.type.Item;
import mindustry.type.Liquid;

public class LHLiquids {
    public static Liquid
            electrifiedWater;
    public static void load(){
        electrifiedWater = new Liquid("electrified-water", Color.valueOf("d1e542")){{
            heatCapacity = 0.4f;
            coolant = false;
            effect = StatusEffects.wet;
            boilPoint = 0.5f;
            lightColor = Color.valueOf("d1e542").a(0.2f);
            gasColor = Color.grays(0.9f);
        }};
    }
}
