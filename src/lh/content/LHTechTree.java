package lh.content;

import arc.struct.*;
import lh.content.LHObjectives.*;

import static mindustry.content.TechTree.*;
import static mindustry.game.Objectives.*;
import static lh.content.blocks.LHOtherBlocks.*;
import static lh.content.blocks.LHDefence.*;
import static lh.content.LHSectors.*;
import static lh.content.LHItems.*;
import static lh.content.LHStatuses.*;
import static lh.content.LHUnitTypes.*;

public class LHTechTree {
    public static void load(){
        LHPlanets.terranite.techTree = nodeRoot("terranite", coreWatch , () -> {
            nodeProduce(alphaChip, () -> {
                nodeProduce(ionite, () -> {

                });
            });
            node(chipMaker, Seq.with(new SectorComplete(groundNull)),() -> {
                node(ioniteSynthesizer, Seq.with(new SectorComplete(groundNull)),() -> {

                });
            });
            node(mix, Seq.with(new SectorSurvive(10, groundNull)), () -> {
                node(dawnBreaker,() -> {

                });
            });
            node(observer, () -> {
                node(disabled, () -> {

                });
                node(speedSigil, () -> {
                    node(reloadSigil, () -> {
                        node(damageSigil, () -> {
                            node(healthSigil, () -> {

                            });
                        });
                    });
                });
            });
            node(groundNull, () -> {

            });

        });
    }
}
