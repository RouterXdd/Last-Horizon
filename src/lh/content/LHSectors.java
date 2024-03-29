package lh.content;

import arc.Core;
import lh.classes.generators.LHSector;
import mindustry.gen.Icon;
import mindustry.type.SectorPreset;

public class LHSectors {
    public static SectorPreset
            groundNull, bummer;
    public static void load() {

        groundNull = new LHSector("ground-null", LHPlanets.terranite, 19) {{
            alwaysUnlocked = true;
            difficulty = 1;
            captureWave = 30;
        }};
        bummer = new LHSector("bummer", LHPlanets.terranite, 34) {{
            difficulty = 4;
            captureWave = 35;
        }};
    }
}
