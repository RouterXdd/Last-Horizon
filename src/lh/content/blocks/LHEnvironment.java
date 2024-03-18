package lh.content.blocks;

import arc.graphics.Color;
import arc.struct.Seq;
import lh.content.LHItems;
import lh.content.LHLiquids;
import mindustry.content.StatusEffects;
import mindustry.graphics.CacheLayer;
import mindustry.world.Block;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.environment.StaticWall;

public class LHEnvironment {
    public static Block heatPlate, heatPlate1, heatPlate2, heatPlateEmpty, heatPlateCross, plexPlate, tauPlate, regardPlate, quartzPlate, heatMetal, quartzMetal, rustPlate, rustMetal, electroWater;
    public static void load(){
        heatPlate = new Floor("heat-plate", 0);
        heatPlate1 = new Floor("heat-plate1", 0);
        heatPlate2 = new Floor("heat-plate2", 0);
        heatPlateEmpty = new Floor("heat-plate-empty", 0);
        heatPlateCross = new Floor("heat-plate-cross", 0);
        plexPlate = new Floor("plex-plate", 0);
        tauPlate = new Floor("tau-plate", 0);
        regardPlate = new Floor("regard-plate", 0);
        quartzPlate = new Floor("quartz-plate", 0);
        heatMetal = new StaticWall("heat-metal"){{
            variants = 3;
        }};
        quartzMetal = new StaticWall("quartz-metal"){{
            quartzPlate.asFloor().wall = this;
            itemDrop = LHItems.quartz;
        }};
        rustPlate = new Floor("rust-plate", 3);
        rustMetal = new StaticWall("rust-metal");
        electroWater = new Floor("electro-water"){{
            speedMultiplier = 0.3f;
            damageTaken = 0.3f;
            variants = 0;
            status = StatusEffects.wet;
            statusDuration = 90f;
            liquidDrop = LHLiquids.electrifiedWater;
            isLiquid = true;
            cacheLayer = CacheLayer.water;
            albedo = 0.9f;
            supportsOverlay = true;
            lightRadius = 25f;
            lightColor = Color.yellow.cpy().a(0.19f);
        }};
        Seq.with(heatPlate, heatPlate1, heatPlate2, heatPlateEmpty, heatPlateCross, plexPlate, tauPlate, regardPlate, quartzPlate).each(b -> b.asFloor().blendGroup = heatPlate);
        Seq.with(heatPlate, heatPlate1, heatPlate2, heatPlateEmpty, heatPlateCross, plexPlate, tauPlate, regardPlate).each(b -> b.asFloor().wall = heatMetal);
        Seq.with(rustPlate, electroWater).each(b -> b.asFloor().wall = rustMetal);
    }
}
