package lh.content;

import mindustry.type.SectorPreset;

public class LHSectors {
    public static SectorPreset
            groundNull, bummer;
    public static void load() {

        groundNull = new SectorPreset("ground-null", LHPlanets.terranite, 19) {{
            alwaysUnlocked = true;
            difficulty = 1;
            captureWave = 30;
        }};
        bummer = new SectorPreset("bummer", LHPlanets.terranite, 34) {{
            difficulty = 4;
            captureWave = 35;
        }};
    }
}
