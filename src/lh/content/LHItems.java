package lh.content;


import arc.graphics.Color;
import mindustry.type.Item;

public class LHItems {
    public static Item
    ionite, alphaChip, zetaChip, omegaChip, quartz, terriliumAlloy;
    public static void load(){
        ionite = new Item("ionite", Color.valueOf("6b8461")){{
            hardness = 1;
            cost = 1.4f;
            charge = 0.65f;
        }};
        alphaChip = new Item("alpha-chip", Color.valueOf("822d8b")){{
            hardness = 1;
        }};
        zetaChip = new Item("zeta-chip", Color.valueOf("557193")){{
            hardness = 1;
        }};
        quartz = new Item("quartz", Color.valueOf("dfe1b2")){{
            hardness = 4;
            cost = 1.35f;
            healthScaling = 0.7f;
        }};
        terriliumAlloy = new Item("terrilium-alloy", Color.valueOf("93c1d8")){{
            hardness = 1;
            cost = 1.5f;
            charge = 1.2f;
        }};
    }
}
