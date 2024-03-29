package lh.content.blocks;

import arc.graphics.Color;
import lh.classes.blocks.effect.CoreBlockAlt;
import lh.classes.blocks.power.PowerLeaker;
import lh.classes.blocks.production.StatusGenericCrafter;
import lh.content.LHLiquids;
import lh.content.LHUnitTypes;
import lh.graphics.LHPal;
import mindustry.content.*;
import mindustry.entities.effect.ParticleEffect;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.blocks.production.BeamDrill;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.units.Reconstructor;
import mindustry.world.blocks.units.UnitFactory;
import mindustry.world.draw.*;
import mindustry.world.meta.BuildVisibility;
import mindustry.world.meta.Env;

import static mindustry.type.ItemStack.*;
import static mindustry.content.Items.*;
import static mindustry.content.Liquids.*;
import static lh.content.LHItems.*;
import static lh.content.LHLiquids.*;

public class LHOtherBlocks {
    public static Block
            //crafting
            chipMaker, chipCompressor, ioniteSynthesizer, oilCrystallizer, terriliumCrucible,
            steelMelter,
            //power
            powerLeaker, arcWaterReactor,
            //production
            proficientBore,
            //storage
            coreWatch, coreSilence,
            //units
            trapFabricator, actionFabricator, sealRefabricator, resultRefabricator, plexFactory, regardFactory
    ;
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
            consumeItems(with(silicon, 2, lead, 1));
        }};
        chipCompressor = new GenericCrafter("chip-compressor"){{
            requirements(Category.crafting, with(terriliumAlloy, 40, titanium, 145, silicon, 90, tungsten, 110));

            craftEffect = Fx.smeltsmoke;
            outputItem = new ItemStack(zetaChip, 1);
            craftTime = 165f;
            size = 2;
            hasItems = true;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(LHPal.terrilium));

            consumePower(2f);
            consumeItems(with(alphaChip, 2, terriliumAlloy, 1));
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
            consumeLiquid(Liquids.water, 22f / 60f);
        }};
        oilCrystallizer = new GenericCrafter("oil-crystallizer"){{
            requirements(Category.crafting, with(copper, 190, silicon, 120, tungsten, 105, alphaChip, 85, quartz, 70));
            outputLiquid = new LiquidStack(Liquids.oil, 10f / 60f);
            size = 3;
            hasPower = true;
            hasItems = true;
            hasLiquids = true;
            rotate = false;
            solid = true;
            outputsLiquid = true;
            envEnabled = Env.any;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(oil), new DrawDefault());
            liquidCapacity = 30f;
            craftTime = 145;

            consumePower(3.4f);
            consumeItems(with(coal, 3, quartz, 2));
            consumeLiquid(Liquids.water, 10f / 60f);
        }};
        terriliumCrucible = new StatusGenericCrafter("terrilium-crucible"){{
            requirements(Category.crafting, with(beryllium, 240, oxide, 165, silicon, 210, titanium, 180, alphaChip, 110, copper, 320, quartz, 140));
            craftEffect = Fx.none;
            outputItem = new ItemStack(terriliumAlloy, 1);
            craftTime = 210f;
            size = 5;
            hasPower = true;
            hasLiquids = false;
            envEnabled |= Env.space | Env.underwater;
            envDisabled = Env.none;
            itemCapacity = 30;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(electrifiedWater),new DrawArcSmelt(){{
                flameColor = Color.valueOf("93c1d8");
                midColor = Color.valueOf("b6ecee");
                flameRad = 2.5f;
                circleSpace = 3.7f;
                particleLife = 60f;
                particleLen = 12f;
            }}, new DrawDefault());
            fogRadius = 3;
            researchCostMultiplier = 0.5f;
            ambientSound = Sounds.plasmadrop;
            ambientSoundVolume = 0.12f;

            consumeItems(with(silicon, 3, titanium, 4, quartz, 2));
            consumeLiquid(electrifiedWater, 100 / 60f);
            consumePower(6f);
        }};
        powerLeaker = new PowerLeaker("power-leaker"){{
            requirements(Category.power, BuildVisibility.sandboxOnly, with());
            health = 2400;
            size = 4;
            powerProduction = 7.5f;
        }};
        steelMelter = new GenericCrafter("steel-melter"){{
            requirements(Category.crafting, with(copper, 160, rust, 130, silicon, 90));

            craftEffect = new ParticleEffect(){{
                particles = 3;
                cone = 0;
                sizeFrom = 4;
                baseRotation = 90;
                sizeTo = 0;
                baseLength = 8;
                length = 20;
                lifetime = 65;
                colorTo = Color.valueOf("00000000");
                colorFrom = Color.valueOf("534a4b");
            }};
            outputItem = new ItemStack(stainlessSteel, 1);
            craftTime = 135f;
            size = 2;
            hasItems = true;
            drawer = new DrawMulti(new DrawDefault(), new DrawWarmupRegion(){{
                color = Color.valueOf("534a4bff");
            }});

            consumePower(1.5f);
            consumeItems(with(rust, 2, coal, 1));
        }};
        arcWaterReactor = new ConsumeGenerator("arc-water-reactor"){{
            requirements(Category.power, with(lead, 240, silicon, 165, alphaChip, 90, tungsten, 155, titanium, 190));
            powerProduction = 2.25f;
            consumeLiquids(LiquidStack.with(electrifiedWater, 50f / 60f));
            size = 3;
            drawer = new DrawMulti(new DrawRegion("-bottom"),  new DrawGlowRegion(){{
                alpha = 1f;
                glowScale = 5f;
                color = Color.valueOf("daff7690");
            }},new DrawDefault());
            outputLiquid = new LiquidStack(water, 22f / 60f);
            generateEffect = Fx.none;

            liquidCapacity = 20f * 5;

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.06f;
        }};
        proficientBore = new BeamDrill("proficient-bore"){{
            requirements(Category.production, with(beryllium, 65, silicon, 30, alphaChip, 20));
            consumePower(0.5f);

            drillTime = 140f;
            tier = 4;
            size = 2;
            range = 6;
            fogRadius = 3;
            researchCost = with(beryllium, 195, silicon, 90, alphaChip, 60);

            consumeLiquid(cryofluid, 6f / 60f).boost();
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
        coreSilence = new CoreBlockAlt("core-silence"){{
            requirements(Category.effect, with(rust, 1800, lead, 1680, beryllium, 2430, graphite, 1160));

            unitType = LHUnitTypes.dart;
            health = 8100;
            armor = 3;
            itemCapacity = 6000;
            exploDamage = 0;
            size = 4;
            status = StatusEffects.corroded;
            thrusterLength = 34/4f;

            unitCapModifier = 8;
            alwaysUnlocked = isFirstTier = true;
            exploColor = Pal.heal;
        }};
        trapFabricator = new UnitFactory("trap-fabricator"){{
            requirements(Category.units, with(silicon, 220, beryllium, 180, alphaChip, 75, titanium, 80));
            size = 3;
            configurable = false;
            plans.add(new UnitPlan(LHUnitTypes.trap, 60f * 38f, with(silicon, 60, titanium, 25)));
            researchCost = with(silicon, 400, beryllium, 320, alphaChip, 150, titanium, 200);
            regionSuffix = "-dark";
            fogRadius = 3;
            consumePower(2.5f);
        }};
        sealRefabricator = new Reconstructor("seal-refabricator"){{
            requirements(Category.units, with(graphite, 220, alphaChip, 120, silicon, 210, tungsten, 130, ionite, 80, quartz, 130));
            regionSuffix = "-dark";

            size = 3;
            consumePower(5.65f);
            consumeLiquids(LiquidStack.with(ozone, 3f / 60f, electrifiedWater, 40f / 60f));
            consumeItems(with(silicon, 45, lead, 60, quartz, 20));

            constructTime = 60f * 32f;
            researchCostMultiplier = 0.75f;

            upgrades.addAll(
                    new UnitType[]{LHUnitTypes.trap, LHUnitTypes.seal}
            );
        }};
        actionFabricator = new UnitFactory("action-fabricator"){{
            requirements(Category.units, with(silicon, 190, beryllium, 240, alphaChip, 75, graphite, 120));
            size = 3;
            configurable = false;
            plans.add(new UnitPlan(LHUnitTypes.action, 60f * 36.5f, with(silicon, 70, graphite, 10, beryllium, 35)));
            researchCost = with(silicon, 350, beryllium, 400, alphaChip, 150, graphite, 300);
            regionSuffix = "-dark";
            fogRadius = 3;
            consumePower(2.5f);
        }};
        resultRefabricator = new Reconstructor("result-refabricator"){{
            requirements(Category.units, with(graphite, 245, alphaChip, 120, silicon, 205, titanium, 190, ionite, 80, quartz, 130));
            regionSuffix = "-dark";

            size = 3;
            consumePower(5.65f);
            consumeLiquids(LiquidStack.with(hydrogen, 3f / 60f, electrifiedWater, 40f / 60f));
            consumeItems(with(silicon, 50, tungsten, 35, alphaChip, 15));

            constructTime = 60f * 35f;
            researchCostMultiplier = 0.75f;

            upgrades.addAll(
                    new UnitType[]{LHUnitTypes.action, LHUnitTypes.result}
            );
        }};
        plexFactory = new UnitFactory("plex-factory"){{
            requirements(Category.units, with(silicon, 190, lead, 210, alphaChip, 75, beryllium, 135, graphite, 95));
            size = 3;
            plans.add(
                    new UnitPlan(LHUnitTypes.arrive, 60f * 32f, with(silicon, 35, lead, 20, graphite, 15)),
                    new UnitPlan(LHUnitTypes.spark, 60f * 27.5f, with(silicon, 20, beryllium, 30))
            );
            researchCost = with(silicon, 400, lead, 800, alphaChip, 150, beryllium, 300, graphite, 160);
            fogRadius = 3;
            consumePower(2.5f);
        }};
        regardFactory = new UnitFactory("regard-factory"){{
            requirements(Category.units, with(silicon, 190, copper, 245, alphaChip, 75, beryllium, 150, titanium, 100));
            size = 3;
            plans.add(
                    new UnitPlan(LHUnitTypes.bit, 60f * 34f, with(silicon, 30, copper, 40)),
                    new UnitPlan(LHUnitTypes.halo, 60f * 35.5f, with(silicon, 50, copper, 60, beryllium, 35))
            );
            researchCost = with(silicon, 600, copper, 1200, alphaChip, 150, beryllium, 300, titanium, 400);
            fogRadius = 3;
            consumePower(2.5f);
        }};
    }
}
