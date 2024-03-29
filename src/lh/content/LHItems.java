package lh.content;


import arc.graphics.Color;
import mindustry.type.Item;

public class LHItems {
    public static Item
    ionite, alphaChip, zetaChip, omegaChip, quartz, terriliumAlloy, rust, stainlessSteel, molecule, ceramic;
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
        omegaChip = new Item("omega-chip", Color.valueOf("d99f6b")){{
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
        rust = new Item("rust", Color.valueOf("b58a84")){{
            hardness = 2;
            cost = 1.9f;
            healthScaling = 1.5f;
        }};
        stainlessSteel = new Item("stainless-steel", Color.valueOf("7f96ad")){{
            hardness = 1;
            cost = 2.5f;
            healthScaling = 2.25f;
        }};
        molecule = new Item("molecule", Color.valueOf("4cb63d")){{
            hardness = 1;
            cost = 1.9f;
            healthScaling = 1.5f;
        }};
        ceramic = new Item("ceramic", Color.valueOf("c78e39")){{
            hardness = 1;
            cost = 0.85f;
        }};
    }
}
