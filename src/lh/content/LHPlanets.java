package lh.content;

import arc.graphics.Color;
import arc.struct.Seq;
import lh.content.blocks.*;
import mindustry.content.Planets;
import mindustry.game.Team;
import mindustry.graphics.Pal;
import mindustry.graphics.g3d.*;
import mindustry.maps.planet.SerpuloPlanetGenerator;
import mindustry.type.*;
import static mindustry.content.Blocks.*;
import static mindustry.content.Items.*;

public class LHPlanets {
    public static Planet
    terranite;
    public static void load() {
        terranite = new Planet("terranite", Planets.erekir, 0.8f, 3) {{
            generator = new SerpuloPlanetGenerator(){{
                defaultLoadout = LHSchematics.watchBasic;
            }};
            meshLoader = () -> new HexMesh(this, 6);
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 11, 0.15f, 0.13f, 5, new Color().set(Pal.spore).mul(0.9f).a(0.75f), 2, 0.45f, 0.9f, 0.38f),
                    new HexSkyMesh(this, 1, 0.6f, 0.16f, 5, Color.white.cpy().lerp(Pal.spore, 0.55f).a(0.75f), 2, 0.45f, 1f, 0.41f)
            );

            launchCapacityMultiplier = 0.5f;
            defaultCore = LHOtherBlocks.coreWatch;
            sectorSeed = 2;
            allowLaunchToNumbered = false;
            allowWaves = true;
            allowWaveSimulation = true;
            allowSectorInvasion = false;
            clearSectorOnLose = true;
            enemyCoreSpawnReplace = true;
            //doesn't play well with configs
            prebuildBase = false;
            ruleSetter = r -> {
                r.waveTeam = Team.malis;
                r.placeRangeCheck = false;
                r.showSpawns = true;
                r.loadout = ItemStack.list(copper, 100, beryllium, 100);
                r.bannedBlocks.addAll(coreShard, coreFoundation, coreNucleus, coreBastion, coreBastion, coreAcropolis, thermalGenerator, turbineCondenser, ventCondenser, launchPad, cliffCrusher);
                r.hideBannedBlocks = true;
            };
            iconColor = Color.valueOf("7d4dff");
            atmosphereColor = Color.valueOf("3c1b8f");
            atmosphereRadIn = 0.02f;
            atmosphereRadOut = 0.3f;
            startSector = 19;
            alwaysUnlocked = true;
            landCloudColor = Pal.spore.cpy().a(0.5f);
        }};
    }
}
