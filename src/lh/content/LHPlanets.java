package lh.content;

import arc.graphics.Color;
import arc.struct.Seq;
import lh.classes.generators.TerranitePlanetGenerator;
import lh.content.blocks.*;
import mindustry.content.Planets;
import mindustry.game.Team;
import mindustry.graphics.Pal;
import mindustry.graphics.g3d.*;
import mindustry.maps.planet.ErekirPlanetGenerator;
import mindustry.maps.planet.SerpuloPlanetGenerator;
import mindustry.type.*;
import static mindustry.content.Blocks.*;
import static mindustry.content.Items.*;

public class LHPlanets {
    public static Planet
    terranite;
    public static void load() {
        terranite = new Planet("terranite", Planets.erekir, 0.8f, 3) {{
            generator = new TerranitePlanetGenerator();
            meshLoader = () -> new HexMesh(this, 6);

            defaultCore = LHOtherBlocks.coreWatch;
            sectorSeed = 2;
            allowLaunchToNumbered = false;
            allowWaves = true;
            allowWaveSimulation = true;
            clearSectorOnLose = true;
            allowLaunchLoadout = true;
            launchCapacityMultiplier = 0.1f;
            //doesn't play well with configs
            prebuildBase = false;
            ruleSetter = r -> {
                r.waveTeam = Team.malis;
                r.placeRangeCheck = false;
                r.showSpawns = true;
                r.loadout = ItemStack.list(copper, 100, beryllium, 100);
                r.bannedBlocks.addAll(coreShard, coreFoundation, coreNucleus, coreBastion, coreCitadel, coreAcropolis, thermalGenerator, turbineCondenser, ventCondenser, launchPad, cliffCrusher, sporePress, siliconSmelter, surgeSmelter, phaseSynthesizer, coalCentrifuge, oilExtractor, copperWall, copperWallLarge, titaniumWall, titaniumWallLarge, foreshadow, smite, malign, afflict);
                r.hideBannedBlocks = true;
            };
            unlockedOnLand.add(LHOtherBlocks.coreWatch);
            iconColor = Pal.darkMetal;
            atmosphereColor = Pal.darkestMetal;
            atmosphereRadIn = 0.02f;
            atmosphereRadOut = 0.3f;
            startSector = 19;
            alwaysUnlocked = true;
            landCloudColor = Pal.darkerMetal.cpy().a(0.5f);
        }};
    }
}
