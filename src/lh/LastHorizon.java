package lh;

import arc.*;
import arc.util.*;
import lh.content.LHBlocks;
import lh.content.LHStatuses;
import lh.content.LHUnitTypes;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class LastHorizon extends Mod{

    public LastHorizon(){}

    @Override
    public void loadContent(){
        LHStatuses.load();
        LHUnitTypes.load();
        LHBlocks.load();
    }

}
