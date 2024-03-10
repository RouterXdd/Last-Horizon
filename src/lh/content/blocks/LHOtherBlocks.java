package lh.content.blocks;

import arc.graphics.Color;
import lh.classes.blocks.effect.CoreBlockAlt;
import lh.content.LHUnitTypes;
import mindustry.content.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.draw.*;

import static mindustry.type.ItemStack.*;
import static mindustry.content.Items.*;
import static lh.content.LHItems.*;

public class LHOtherBlocks {
    public static Block
            //crafting
            chipMaker, ioniteSynthesizer,
            //storage
            coreWatch;
    public static void load(){
        chipMaker = new GenericCrafter("chip-maker"){{
            requirements(Category.crafting, with(copper, 160, titanium, 80, silicon, 110, beryllium, 95));

            craftEffect = Fx.smeltsmoke;
            outputItem = new ItemStack(alphaChip, 1);
            craftTime = 110f;
            size = 2;
            hasItems = true;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(Color.valueOf("f58bebff")));

            consumePower(0.85f);
            consumeItems(with(silicon, 2, lead, 1, coal, 1));
        }};
        ioniteSynthesizer = new GenericCrafter("ionite-synthesizer"){{
            requirements(Category.crafting, with(lead, 180, silicon, 115, beryllium, 140, alphaChip, 65, tungsten, 80));
            outputItem = new ItemStack(ionite, 1);
            size = 3;
            hasPower = true;
            hasItems = true;
            hasLiquids = true;
            rotate = false;
            solid = true;
            outputsLiquid = true;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.water), new DrawBubbles(Color.valueOf("7693e3")){{
                sides = 10;
                recurrence = 3f;
                spread = 7f;
                radius = 2f;
                amount = 25;
            }},new DrawDefault());
            liquidCapacity = 48f;
            craftTime = 170;
            lightLiquid = Liquids.cryofluid;

            consumePower(2f);
            consumeItems(with(lead, 2, titanium, 1));
            consumeLiquid(Liquids.water, 24f / 60f);
        }};

        coreWatch = new CoreBlockAlt("core-watch"){{
            requirements(Category.effect, with(copper, 2800, silicon, 2200, beryllium, 1850, titanium, 1460));

            unitType = LHUnitTypes.observer;
            health = 6400;
            armor = 5;
            itemCapacity = 5000;
            size = 4;
            thrusterLength = 34/4f;

            unitCapModifier = 10;
            alwaysUnlocked = isFirstTier = true;
        }};
    }
}
