package lh.content;

import arc.struct.*;
import lh.content.LHObjectives.*;

import static mindustry.content.TechTree.*;
import static mindustry.game.Objectives.*;
import static lh.content.blocks.LHOtherBlocks.*;
import static lh.content.blocks.LHDefence.*;
import static lh.content.LHSectors.*;
import static lh.content.LHItems.*;
import static lh.content.LHLiquids.*;
import static lh.content.LHStatuses.*;
import static lh.content.LHUnitTypes.*;

public class LHTechTree {
    public static void load(){
        LHPlanets.terranite.techTree = nodeRoot("terranite", coreWatch , () -> {
            nodeProduce(alphaChip, () -> {
                nodeProduce(ionite, () -> {
                    nodeProduce(terriliumAlloy, () -> {

                    });
                });
                nodeProduce(quartz, () -> {
                    nodeProduce(rust, () -> {
                        nodeProduce(stainlessSteel, () -> {

                        });
                    });
                });
                nodeProduce(zetaChip, () -> {
                    nodeProduce(omegaChip, () -> {

                    });
                });
            });
            nodeProduce(electrifiedWater, () -> {

            });
            node(chipMaker, Seq.with(new SectorComplete(groundNull)),() -> {
                node(ioniteSynthesizer, Seq.with(new Research(alphaChip), new SectorSurvive(5, bummer)),() -> {
                    node(terriliumCrucible, Seq.with(new ComingSoon()),() -> {
                        node(zeroGravity, () -> {

                        });

                    });
                });
                node(oilCrystallizer, Seq.with(new OnSector(sensor)),() -> {

                });
                node(chipCompressor, Seq.with(new ComingSoon()),() -> {

                });
                node(steelMelter, Seq.with(new ComingSoon()),() -> {

                });
            });
            node(arcWaterReactor, Seq.with(new SectorSurvive(28, bummer)),() -> {

            });
            node(proficientBore, Seq.with(new OnSector(sensor)),() -> {

            });
            node(mix, Seq.with(new SectorSurvive(10, groundNull)), () -> {
                node(dawnBreaker, Seq.with(new ComingSoon()), () -> {

                });
                node(line, Seq.with(new SectorSurvive(20, bummer)),() -> {
                    node(amplifier, Seq.with(new ComingSoon()),() -> {

                    });
                });
                node(greed, Seq.with(new OnSector(sensor)),() -> {
                    node(beat, Seq.with(new ComingSoon()),() -> {

                    });
                });
                node(set, Seq.with(new ComingSoon()),() -> {

                });
                node(rifle, Seq.with(new SectorComplete(sensor)),() -> {
                    node(oblivion, Seq.with(new ComingSoon()),() -> {

                    });
                });
                node(stink, Seq.with(new ComingSoon()),() -> {
                    node(straw, Seq.with(new ComingSoon()),() -> {

                    });
                });
            });
            node(reinforcedLeadWall, () -> {
                node(terriliumWall, () -> {

                });
                node(rustWall, () -> {

                });
            });
            node(plexFactory, Seq.with(new OnSector(sensor)),() -> {
                node(arrive, () -> {

                });
                node(spark, () -> {

                });
                node(trapFabricator, Seq.with(new OnSector(sensor)),() -> {
                    node(trap, () -> {

                    });
                    node(sealRefabricator, () -> {
                        node(seal, () -> {

                        });
                    });
                });
                node(actionFabricator, Seq.with(new OnSector(sensor)),() -> {
                    node(action, () -> {

                    });
                    node(resultRefabricator, () -> {
                        node(result, () -> {

                        });
                    });
                });
                node(regardFactory, Seq.with(new OnSector(sensor)),() -> {
                    node(bit, () -> {

                    });
                    node(halo, () -> {

                    });
                });
            });
            node(observer, () -> {
                node(disabled, () -> {

                });
                node(speedSigil, () -> {
                    node(reloadSigil, () -> {
                        node(damageSigil, () -> {
                            node(healthSigil, () -> {
                                node(crawlerSigil, () -> {

                                });
                            });
                        });
                    });
                });
            });
            node(groundNull, () -> {
                node(bummer, Seq.with(new SectorComplete(groundNull)),() -> {
                    node(sensor, Seq.with(new SectorComplete(bummer)),() -> {

                    });
                });
            });

        });
    }
}
