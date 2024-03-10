package lh.content;


import arc.graphics.Color;
import mindustry.type.Item;

public class LHItems {
    public static Item
    ionite, alphaChip, sigmaChip, omegaChip;
    public static void load(){
        ionite = new Item("ionite", Color.valueOf("6b8461")){{
            hardness = 1;
            cost = 1.4f;
            charge = 0.65f;
        }};
        alphaChip = new Item("alpha-chip", Color.valueOf("822d8b")){{
            hardness = 1;
        }};
    }
}
