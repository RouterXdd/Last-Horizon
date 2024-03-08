package lh.content.blocks;

import lh.classes.blocks.defence.Destructor;
import mindustry.content.*;
import mindustry.type.*;
import mindustry.world.*;

import static mindustry.type.ItemStack.*;
import static mindustry.content.Items.*;

public class LHDefence {
    public static Block
            //turrets
            mix,
            //special
            dawnBreaker
            ;
    public static void load(){
        dawnBreaker = new Destructor("dawn-breaker"){{
            requirements(Category.turret, with(copper, 300, lead, 90, thorium, 145));

            outputItems = with(Items.graphite, 1);
            size = 3;
            hasItems = true;
            consumePower(3f);
        }};
    }
}
