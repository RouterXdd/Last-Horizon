package lh.content.blocks;

import lh.classes.blocks.effect.CoreBlockAlt;
import mindustry.content.*;
import mindustry.type.*;
import mindustry.world.*;

import static mindustry.type.ItemStack.*;
import static mindustry.content.Items.*;

public class LHOtherBlocks {
    public static Block
            //storage
            coreWatch;
    public static void load(){
        coreWatch = new CoreBlockAlt("core-watch"){{
            requirements(Category.effect, with(copper, 2800, silicon, 2200, beryllium, 1850, titanium, 1460));

            unitType = UnitTypes.emanate;
            health = 6400;
            armor = 5;
            itemCapacity = 5000;
            size = 4;
            thrusterLength = 34/4f;

            unitCapModifier = 10;
            alwaysUnlocked = true;
        }};
    }
}
