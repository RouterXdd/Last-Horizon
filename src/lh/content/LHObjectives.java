package lh.content;

import arc.Core;
import mindustry.game.Objectives;
import mindustry.type.SectorPreset;

public class LHObjectives{
    public static class SectorSurvive implements Objectives.Objective {
        public SectorPreset preset;
        public int surviveWaves;
        boolean curWave;
        public SectorSurvive(int waves, SectorPreset zone){
            this.preset = zone;
            this.surviveWaves = waves;
        }

        protected SectorSurvive(){}

        @Override
        public boolean complete(){
             if (preset.sector.save != null) {
                 curWave = preset.sector.save.getWave() >= surviveWaves;
             }

            return preset.sector.save != null && preset.sector.hasBase() && curWave;
        }

        @Override
        public String display(){
            return Core.bundle.format("requirement.alive-waves", surviveWaves, preset.localizedName);
        }
    }
}
