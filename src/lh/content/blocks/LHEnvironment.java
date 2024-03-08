package lh.content.blocks;

import arc.struct.Seq;
import mindustry.world.Block;
import mindustry.world.blocks.environment.Floor;

public class LHEnvironment {
    public static Block heatPlate, heatPlate1, heatPlate2, plexPlate, tauPlate, regardPlate;
    public static void load(){
        heatPlate = new Floor("heat-plate", 0);
        heatPlate1 = new Floor("heat-plate1", 0);
        heatPlate2 = new Floor("heat-plate2", 0);
        plexPlate = new Floor("plex-plate", 0);
        tauPlate = new Floor("tau-plate", 0);
        regardPlate = new Floor("regard-plate", 0);
        Seq.with(heatPlate, heatPlate1, heatPlate2, plexPlate, tauPlate, regardPlate).each(b -> b.asFloor().blendGroup = heatPlate);
    }
}
