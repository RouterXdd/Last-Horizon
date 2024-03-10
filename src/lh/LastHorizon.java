package lh;

import arc.*;
import arc.util.*;
import lh.content.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class LastHorizon extends Mod{

    public LastHorizon(){}

    @Override
    public void loadContent(){
        LHStatuses.load();
        LHItems.load();
        LHLiquids.load();
        LHUnitTypes.load();
        LHBlocks.load();
        LHSchematics.load();
        LHPlanets.load();
        LHSectors.load();
        LHTechTree.load();
    }

}
