package lh.content.blocks;

import arc.graphics.Color;
import arc.math.Mathf;
import lh.classes.blocks.defence.ArmorWall;
import lh.classes.blocks.defence.Destructor;
import lh.classes.bullets.PointStreamBulletType;
import lh.classes.types.LHUnitSorts;
import lh.graphics.LHPal;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.ShootBarrel;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.draw.DrawTurret;
import mindustry.world.meta.Env;

import static mindustry.type.ItemStack.*;
import static mindustry.content.Items.*;
import static lh.content.LHItems.*;

public class LHDefence {
    public static Block
            //turrets
            mix,line,greed,
            //walls
            reinforcedLeadWall,
            //special
            dawnBreaker
            ;
    public static void load(){
        mix = new ItemTurret("mix"){{
            requirements(Category.turret, with(copper, 100, beryllium, 65, silicon, 120, titanium, 70));
            ammo(
                    Items.graphite, new BasicBulletType(4f, 20){{
                        width = 9f;
                        height = 12f;
                        reloadMultiplier = 0.6f;
                        ammoMultiplier = 3;
                        lifetime = 35f;
                        spawnBullets.add(new BasicBulletType(8f, 30){{
                            knockback = 4f;
                            width = 15f;
                            hitSize = 5f;
                            height = 10f;
                            lifetime = 17.5f;
                            shootEffect = Fx.shootBigColor;
                            smokeEffect = Fx.shootSmokeSquareSparse;
                            hitColor = backColor = trailColor = Color.valueOf("ea8878");
                            frontColor = Pal.redLight;
                            trailWidth = 4f;
                            trailLength = 3;
                            hitEffect = despawnEffect = Fx.hitSquaresColor;
                            buildingDamageMultiplier = 0.5f;
                        }});
                    }},
                    Items.thorium, new BasicBulletType(4f, 35){{
                        width = 10f;
                        height = 13f;
                        shootEffect = Fx.shootBig;
                        smokeEffect = Fx.shootBigSmoke;
                        ammoMultiplier = 2;
                        lifetime = 35f;
                        spawnBullets.add(new ShrapnelBulletType(){{
                            length = 150;
                            damage = 50f;
                            toColor = Pal.thoriumPink;
                            shootEffect = smokeEffect = Fx.thoriumShoot;
                        }});
                    }}
            );

            drawer = new DrawTurret("reinforced-");

            size = 2;
            range = 140f;
            reload = 45f;
            consumeAmmoOnce = false;
            ammoEjectBack = 3f;
            recoil = 1.5f;
            shake = 1f;
            inaccuracy = 3;
            shoot.shots = 3;
            shoot.shotDelay = 4f;
            researchCostMultiplier = 0.5f;

            ammoUseEffect = Fx.casing2;
            scaledHealth = 240;
            shootSound = Sounds.shootBig;

            limitRange();
            coolant = consumeCoolant(0.25f);
        }};
        line = new ContinuousTurret("line"){{
            requirements(Category.turret, with(silicon, 220, graphite, 160, oxide, 70, ionite, 120, plastanium, 50));

            shootType = new PointStreamBulletType(){{
                damage = 45f;
                buildingDamageMultiplier = 0.3f;
                hitColor = LHPal.plexLight;
            }};

            drawer = new DrawTurret("reinforced-"){{
                var heatp = DrawPart.PartProgress.warmup.blend(p -> Mathf.absin(2f, 1f) * p.warmup, 0.2f);
                        parts.add(new RegionPart("-barrel"){{
                            heatProgress = heatp;
                            progress = PartProgress.warmup;
                            heatColor = Color.valueOf("ff6214");
                            moveY = -6f;
                            mirror = false;
                            under = true;
                        }});
            }};

            shootSound = Sounds.none;
            loopSoundVolume = 1f;
            loopSound = Sounds.laserbeam;

            shootWarmupSpeed = 0.08f;
            shootCone = 360f;

            aimChangeSpeed = 0.9f;
            rotateSpeed = 2f;

            shootY = 2.5f;
            outlineColor = Pal.darkOutline;
            size = 3;
            envEnabled |= Env.space;
            range = 190f;
            scaledHealth = 180;

            unitSort = LHUnitSorts.slowest;

            consumePower(6.5f);
        }};
        greed = new ItemTurret("greed"){{
            requirements(Category.turret, with(graphite, 130, silicon, 95, ionite, 60, beryllium, 45));
            ammo(
                    beryllium, new MissileBulletType(4f, 8){{
                        frontColor = Color.white;
                        backColor = Pal.berylShot;
                        width = 9f;
                        height = 9f;
                        shrinkY = 0f;
                        ammoMultiplier = 5f;
                        hitEffect = Fx.blastExplosion;
                        despawnEffect = Fx.blastExplosion;
                        lifetime = range / speed;
                        pierceCap = 2;
                    }},
                    tungsten, new MissileBulletType(4f, 15){{
                        frontColor = Color.white;
                        backColor = Pal.tungstenShot;
                        width = 9f;
                        height = 9f;
                        shrinkY = 0f;
                        ammoMultiplier = 5f;
                        hitEffect = Fx.blastExplosion;
                        status = StatusEffects.slow;
                        lifetime = range / speed;
                        pierceCap = 4;
                    }},
                    ionite, new MissileBulletType(4f, 12){{
                        frontColor = Color.white;
                        backColor = LHPal.tauLightlish;
                        width = 9f;
                        height = 9f;
                        shrinkY = 0f;
                        hitEffect = Fx.blastExplosion;
                        despawnEffect = Fx.blastExplosion;
                        ammoMultiplier = 4f;
                        lightningColor = LHPal.tauLightlish;
                        lightningDamage = 2;
                        lightning = 4;
                        lightningLength = 6;
                        lifetime = range / speed;
                        pierceCap = 2;
                    }}
            );

            shoot = new ShootBarrel(){{
                barrels = new float[]{
                        -3, -1.25f, 0,
                        0, 0, 0,
                        3, -1.25f, 0
                };
                shots = 3;
                shotDelay = 6f;
            }};

            shootY = 4f;
            reload = 48f;
            inaccuracy = 10f;
            range = 260f;
            consumeAmmoOnce = false;
            size = 2;
            scaledHealth = 340;
            shootSound = Sounds.missile;
            envEnabled |= Env.space;

            limitRange(8f);
            coolant = consumeCoolant(0.3f);
            drawer = new DrawTurret("reinforced-");
        }};
        reinforcedLeadWall = new ArmorWall("lead-wall"){{
            requirements(Category.defense, with(lead, 24, ionite, 8, tungsten, 4));
            health = 185 * 4 * 4;
            armor = 5f;
            armorBonus = 6;
            buildCostMultiplier = 6.5f;
            size = 2;
        }};
        dawnBreaker = new Destructor("dawn-breaker"){{
            requirements(Category.turret, with(copper, 300, tungsten, 120, thorium, 165, silicon, 110, ionite, 80));

            outputItems = with(silicon, 1);
            range = 12 * 8f;
            size = 3;
            hasItems = true;
            consumePower(3f);
        }};
    }
}
