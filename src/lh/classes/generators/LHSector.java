package lh.classes.generators;

import arc.Core;
import mindustry.gen.Icon;
import mindustry.type.Planet;
import mindustry.type.SectorPreset;

public class LHSector extends SectorPreset {
    public LHSector(String name, Planet planet, int sector) {
        super(name, planet, sector);
    }
    @Override
    public void loadIcon(){
        if(Icon.terrain != null){
            uiIcon = fullIcon = Core.atlas.find(this.name);
        }
    }
}
