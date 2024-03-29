package lh.content.blocks;

import arc.graphics.Color;
import arc.math.Interp;
import arc.math.Mathf;
import lh.classes.blocks.defence.ArmorWall;
import lh.classes.blocks.defence.Destructor;
import lh.classes.bullets.AreaDamageBulletType;
import lh.classes.bullets.PointStreamBulletType;
import lh.classes.types.LHUnitSorts;
import lh.content.LHLiquids;
import lh.content.LHStatuses;
import lh.graphics.LHPal;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.ShootAlternate;
import mindustry.entities.pattern.ShootBarrel;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.consumers.ConsumeLiquid;
import mindustry.world.draw.DrawTurret;
import mindustry.world.meta.Env;

import java.util.Iterator;

import static mindustry.type.ItemStack.*;
import static mindustry.content.Items.*;
import static lh.content.LHItems.*;

public class LHDefence {
    public static Block
            //turrets
            mix,line,greed, set, rifle, oblivion, amplifier, beat, repulsion,
            //rust turrets
            stink, claw, care, straw, slam, point, pusher, roots,
            //walls
            reinforcedLeadWall, terriliumWall, rustWall,
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
        set = new ItemTurret("set"){{
            requirements(Category.turret, with(graphite, 260, tungsten, 190, silicon, 280, terriliumAlloy, 90, alphaChip, 155));
            ammo(
                    alphaChip, new BasicBulletType(0, 0){{
                        lifetime = 0;
                        hitEffect = despawnEffect = Fx.none;
                        width = height = 0;
                        ammoMultiplier = 2;
                        spawnBullets.add(new BasicBulletType(6.5f, 15){{
                            width = 8f;
                            height = 12f;
                            lifetime = 27f;
                            shootEffect = Fx.shootBigColor;
                            smokeEffect = Fx.shootSmokeSquareSparse;
                            hitColor = backColor = trailColor = LHPal.collapse;
                            frontColor = LHPal.coreLight;
                            trailWidth = 3f;
                            trailLength = 5;
                            hitEffect = despawnEffect = Fx.hitSquaresColor;
                        }}, new SapBulletType(){{
                            length = 175f;
                            damage = 30;
                            color = LHPal.coreLight;

                            lifetime = 10f;
                        }});
                    }},
                    zetaChip, new BasicBulletType(0, 0){{
                        lifetime = 0;
                        hitEffect = despawnEffect = Fx.none;
                        width = height = 0;
                        spawnBullets.add(new BasicBulletType(10f, 20){{
                            width = 5.5f;
                            hitSize = 4f;
                            height = 8f;
                            lifetime = 17.5f;
                            sprite = "missile";
                            collidesGround = false;
                            shootEffect = Fx.shootBigColor;
                            smokeEffect = Fx.shootSmokeSquareSparse;
                            hitColor = backColor = trailColor = LHPal.terrilium;
                            frontColor = LHPal.terriliumDark;
                            trailWidth = 2.5f;
                            trailLength = 7;
                            hitEffect = despawnEffect = Fx.flakExplosion;
                        }},
                                new BasicBulletType(5.5f, 20){{
                                    width = 12f;
                                    height = 12f;
                                    drag = 0.03f;
                                    lifetime = 50f;
                                    sprite = "circle-bullet";
                                    shootEffect = Fx.shootBigColor;
                                    smokeEffect = Fx.shootSmokeSquareSparse;
                                    hitColor = backColor = trailColor = LHPal.terrilium;
                                    frontColor = LHPal.terriliumDark;
                                    splashDamage = 25;
                                    splashDamageRadius = 50;
                                    splashDamagePierce = true;
                                    trailWidth = 2.5f;
                                    trailLength = 7;
                                    hitEffect = despawnEffect = new WaveEffect(){{
                                        sizeFrom = sizeTo = 50;
                                        strokeFrom = 3;
                                        strokeTo = 0;
                                        colorFrom = colorTo = LHPal.terrilium;
                                    }};
                                }}, new SapBulletType(){{
                                    length = 175f;
                                    damage = 45;
                                    color = LHPal.terrilium;
                                    status = LHStatuses.zeroGravity;
                                    lifetime = 10f;
                                }});
                    }}
            );

            drawer = new DrawTurret("reinforced-");

            size = 3;
            range = 175f;
            reload = 60f;
            consumeAmmoOnce = false;
            ammoEjectBack = 5f;
            recoil = 1.5f;
            shake = 1f;
            inaccuracy = 6;
            shoot.shots = 5;
            shoot.shotDelay = 6f;
            researchCostMultiplier = 0.5f;
            shootY = 4f;

            ammoUseEffect = Fx.casing2;
            scaledHealth = 240;
            shootSound = Sounds.shootBig;

            limitRange();
            coolant = consumeCoolant(0.25f);
        }};
        rifle = new ItemTurret("rifle"){{
            requirements(Category.turret, with(graphite, 275, tungsten, 210, silicon, 310, surgeAlloy, 110, alphaChip, 170, beryllium, 195));
            ammo(
                    quartz, new BasicBulletType(8, 70){{
                        pierceArmor = true;
                        splashDamage = 30;
                        splashDamageRadius = 6 * 8;
                        lifetime = 23.75f;
                        width = 10;
                        height = 14;
                        ammoMultiplier = 1;
                        for (int v = 0; v < 15; v++) {
                            spawnBullets.add(
                                    new BasicBulletType(6f, 8) {{
                                        width = 6f;
                                        height = 9f;
                                        lifetime = 7f;
                                        shootEffect = Fx.shootBigColor;
                                        smokeEffect = Fx.shootSmokeSquareSparse;
                                        hitColor = backColor = trailColor = LHPal.regardLight;
                                        frontColor = LHPal.regardMid;
                                        hitEffect = despawnEffect = Fx.hitSquaresColor;
                                    }}
                            );
                        }
                    }
                        @Override
                        public void init(Bullet b) {
                            if (this.killShooter) {
                                Entityc var3 = b.owner();
                                if (var3 instanceof Healthc) {
                                    Healthc h = (Healthc)var3;
                                    if (!h.dead()) {
                                        h.kill();
                                    }
                                }
                            }

                            if (this.instantDisappear) {
                                b.time = this.lifetime + 1.0F;
                            }

                            if (this.spawnBullets.size > 0) {
                                Iterator var4 = this.spawnBullets.iterator();

                                while(var4.hasNext()) {
                                    BulletType bullet = (BulletType)var4.next();
                                    bullet.create(b, b.x, b.y, b.rotation() + Mathf.random(-60, 60));
                                }
                            }

                        }}
            );

            drawer = new DrawTurret("reinforced-"){{
                parts.add(new RegionPart("-barrel"){{
                    heatProgress = PartProgress.recoil;
                    progress = PartProgress.recoil;
                    heatColor = LHPal.regardLight;
                    moveY = -3f;
                    mirror = false;
                    under = true;
                }}, new RegionPart("-wing"){{
                              progress = PartProgress.warmup;
                              heatColor = LHPal.regardLight;
                              moveY = -1.25f;
                              moveX = 1.25f;
                              mirror = true;
                              under = true;
                }}
                );
            }};
            ammoPerShot = 3;
            size = 3;
            range = 190f;
            reload = 130f;
            ammoEjectBack = 1f;
            recoil = 3f;
            shake = 1f;
            researchCostMultiplier = 0.5f;
            shootY = 5f;

            ammoUseEffect = Fx.casing2;
            scaledHealth = 270;
            shootSound = Sounds.pulseBlast;

            limitRange();
            coolant = consumeCoolant(0.3f);
        }};
        oblivion = new ItemTurret("oblivion"){{
            requirements(Category.turret, with(tungsten, 230, silicon, 300, alphaChip, 275, titanium, 280, quartz, 170));

            ammo(
                    pyratite, new AreaDamageBulletType(){{
                        hitEffect = despawnEffect = Fx.titanSmoke;
                        damage = 180;
                        speed = 2;
                        lifetime = 135f;
                        height = 20f;
                        width = 20f;
                        splashDamageRadius = 6 * 8f;
                        splashDamage = 170f;
                        scaledSplashDamage = true;
                        backColor = hitColor = trailColor = areaColor = Color.valueOf("ea8878").lerp(Pal.lightPyraFlame, 0.5f);
                        frontColor = Color.white;
                        ammoMultiplier = 1f;
                        hitSound = Sounds.titanExplosion;
                        areaStatuses = new StatusEffect[]{
                                StatusEffects.burning,
                                StatusEffects.melting
                        };
                        areaEffectChance = 0.45f;
                        areaEffect = Fx.fire;
                        areaDamage = 40;
                        areaRange = 7f;
                        homingPower = 0.8f;
                        homingRange = 170;

                        trailChance = 0.4f;
                        trailEffect = new ParticleEffect(){{
                            particles = 2;
                            sizeFrom = 3.25f;
                            cone = 65;
                            length = 45;
                            lifetime = 82;
                            baseRotation = 180;
                            sizeTo = 0;
                            colorFrom = Pal.lightPyraFlame;
                            colorTo = Pal.lightPyraFlame;
                        }};
                        despawnShake = 5f;

                        shootEffect = Fx.shootTitan;
                        smokeEffect = Fx.shootSmokeTitan;

                        trailInterp = v -> Math.max(Mathf.slope(v), 0.8f);
                        shrinkX = 0f;
                        shrinkY = 0f;
                        buildingDamageMultiplier = 0.3f;
                    }}
            );
            shootSound = Sounds.pulseBlast;
            ammoPerShot = 6;
            maxAmmo = ammoPerShot * 3;
            targetAir = true;
            shake = 4f;
            recoil = 1f;
            reload = 60f * 5f;
            shootY = 7f;
            rotateSpeed = 1.4f;
            minWarmup = 0.85f;
            shootWarmupSpeed = 0.07f;
            heatRequirement = 10f;
            maxHeatEfficiency = 3f;

            drawer = new DrawTurret("reinforced-");

            outlineColor = Pal.darkOutline;
            predictTarget = false;

            scaledHealth = 250;
            range = 280f;
            size = 3;
        }};
        amplifier = new ItemTurret("amplifier"){{
            range = 320f;

            requirements(Category.turret, with(copper, 480, silicon, 355, zetaChip, 190, surgeAlloy, 140, oxide, 125));
            ammo(
                    Items.silicon, new ArtilleryBulletType(6f, 200, "shell"){{
                        knockback = 2f;
                        lifetime = 65f;
                        width = height = 20f;
                        frontColor = backColor = Pal.surge;
                        collidesTiles = false;
                        collidesAir = true;
                        splashDamageRadius = 15f * 8f;
                        splashDamage = 125f;
                        ammoMultiplier = 2f;
                        homingPower = 0.1f;
                        homingRange = 80f;
                        status = LHStatuses.powerRelease;
                        statusDuration = 60f * 10f;
                        trailChance = 0.35f;
                        trailEffect = new WaveEffect(){{
                            colorFrom = Color.white;
                            colorTo = Pal.surge;
                            sides = 6;
                            strokeFrom = 3;
                            strokeTo = 0;
                            sizeFrom = 0;
                            sizeTo = 13;
                            lifetime = 8f;
                        }};
                        shootEffect = new WaveEffect(){{
                            colorFrom = Color.white;
                            colorTo = Pal.surge;
                            sides = 8;
                            strokeFrom = 5;
                            strokeTo = 0;
                            sizeFrom = 0;
                            sizeTo = 26;
                            lifetime = 26f;
                        }};
                        hitEffect = despawnEffect = new MultiEffect(
                        new WaveEffect(){{
                            colorFrom = Color.white;
                            colorTo = Pal.surge;
                            strokeFrom = 6;
                            strokeTo = 0;
                            sizeFrom = 0;
                            sizeTo = 15 * 8;
                            interp = Interp.pow2Out;
                            lifetime = 120f;
                        }},
                        new ParticleEffect(){{
                            line = true;
                            particles = 12;
                            lifetime = 120f;
                            cone = 360;
                            colorTo = colorFrom = Pal.surge;
                            length = 110;
                            lenFrom = 9;
                            lenTo = 0;
                            sizeFrom = 6;
                            sizeTo = 0;
                        }}
                        );
                    }}
            );

            maxAmmo = 16;
            ammoPerShot = 4;
            rotateSpeed = 3f;
            reload = 145f;
            recoil = 4.5f;
            cooldownTime = reload;
            shootY = 5.5f;
            shake = 4f;
            size = 4;
            shootCone = 5f;
            shootSound = Sounds.railgun;
            unitSort = LHUnitSorts.fastest;
            envEnabled |= Env.space;
            heatColor = Pal.surge;

            coolantMultiplier = 0.8f;
            scaledHealth = 170;
            drawer = new DrawTurret("reinforced-");

            coolant = consumeCoolant(0.6f);
            consumeLiquid(LHLiquids.electrifiedWater, 35 / 60f);
        }};
        beat = new PowerTurret("beat"){{
            requirements(Category.turret, with(graphite, 235, silicon, 190, ionite, 95, tungsten, 120, thorium, 160, alphaChip, 110));
            shootType = new BasicBulletType(7f, 35){{
                frontColor = Color.white;
                backColor = LHPal.tauLightlish;
                width = 9f;
                height = 11f;
                shrinkY = 0f;
                hitEffect = Fx.blastExplosion;
                despawnEffect = Fx.blastExplosion;
                lightningColor = LHPal.tauLightlish;
                lightningDamage = 10;
                lightning = 3;
                lightningLength = 8;
                lightningCone = 25;
                lifetime = 80;
                pierceCap = 2;
                homingRange = 75;
                homingPower = 0.4f;
                homingDelay = 8;
            }};

            shoot = new ShootBarrel(){{
                barrels = new float[]{
                        10.5f, 10.5f, -45,
                        15.25f, 0, -90,
                        10.5f, -10.5f, -135,
                        -10.5f, 10.5f, 45,
                        -15.25f, 0, 90,
                        -10.5f, -10.5f, 135,
                };
                shots = 6;
            }};

            shootY = 0f;
            recoil = 0;
            reload = 75f;
            inaccuracy = 0f;
            range = 220f;
            consumeAmmoOnce = false;
            size = 4;
            scaledHealth = 340;
            shootSound = Sounds.blaster;
            envEnabled |= Env.space;
            consumePower(400 / 60f);

            limitRange(8f);
            coolant = consumeCoolant(0.5f);
            drawer = new DrawTurret("reinforced-"){{
                for (int i = 0; i < 3; i++) {
                    int o = i;
                    parts.add(new RegionPart("-gun"){{
                        rotation = 45 * o + 45;
                        mirror = true;
                        under = true;
                        progress = PartProgress.recoil;
                        moveX = 1;
                        if (o == 0) {
                            moveY = -1;
                        } else if (o == 2) {
                            moveY = 1;
                        } else {
                            moveY = 0;
                        }
                    }});
                }
            }};
        }};
        stink = new ItemTurret("stink"){{
            requirements(Category.turret, with(copper, 80, rust, 65));
            ammo(
                    rust,  new BasicBulletType(3f, 5f){{
                        width = 6f;
                        height = 9f;
                        lifetime = range / speed;
                        ammoMultiplier = 2;
                        status = StatusEffects.corroded;
                        statusDuration = 5 * 60;
                        frontColor = Pal.heal;
                        backColor = Pal.plastanium;
                    }},
                    stainlessSteel,  new BasicBulletType(3f, 8f){{
                        width = 8f;
                        height = 11f;
                        lifetime = range / speed;
                        ammoMultiplier = 1;
                        status = StatusEffects.slow;
                        statusDuration = 6 * 60;
                        frontColor = Color.white;
                        backColor = Color.white.mul(2);
                    }}
            );
            shoot = new ShootSpread(3, 1);
            drawer = new DrawTurret("rust-");
            outlineColor = Color.valueOf("413637");

            recoil = 1f;
            shootY = 3.25f;
            reload = 42f;
            range = 75;
            size = 1;
            shootCone = 20f;
            ammoUseEffect = Fx.casing1;
            scaledHealth = 80;
            inaccuracy = 5f;
            rotateSpeed = 8f;
            coolant = consumeCoolant(0.1f);

            limitRange();
        }};
        straw = new ItemTurret("straw"){{
            requirements(Category.turret, with(copper, 180, rust, 110, stainlessSteel, 75));
            ammo(
                    rust,  new BasicBulletType(3f, 24){{
                        width = 7.5f;
                        height = 11f;
                        lifetime = range / speed;
                        ammoMultiplier = 2;
                        status = StatusEffects.corroded;
                        statusDuration = 5 * 60;
                        frontColor = Pal.heal;
                        backColor = Pal.plastanium;
                    }},
                    coal, new BasicBulletType(3.5f, 30){{
                        width = 7.5f;
                        height = 11f;
                        reloadMultiplier = 0.8f;
                        ammoMultiplier = 3;
                        lifetime = range / speed;
                        status = StatusEffects.burning;
                        statusDuration = 7 * 60;
                        frontColor = Pal.coalBlack;
                        backColor = Color.black;
                    }}
            );
            outlineColor = Color.valueOf("413637");

            shoot = new ShootAlternate(5.5f);

            recoils = 2;
            drawer = new DrawTurret("rust-"){{
                for(int i = 0; i < 2; i ++){
                    int f = i;
                    parts.add(new RegionPart("-barrel-" + (i == 0 ? "l" : "r")){{
                        progress = PartProgress.recoil;
                        recoilIndex = f;
                        under = true;
                        moveY = -1.8f;
                    }});
                }
            }};

            recoil = 0.8f;
            shootY = 6.5f;
            reload = 17f;
            range = 145;
            size = 2;
            shootCone = 20f;
            ammoUseEffect = Fx.casing1;
            scaledHealth = 80;
            inaccuracy = 0.5f;
            rotateSpeed = 10f;
            coolant = consumeCoolant(0.25f);

            limitRange();
        }};
        roots = new ItemTurret("roots"){{
            scaledHealth = 245;
            size = 3;
            range = 310;
            targetAir = targetGround = true;
            recoil = 2;
            reload = 80;
            inaccuracy = 2;
            outlineColor = Color.valueOf("302326");
            heatColor = LHPal.moleculeLight;
            shootSound = Sounds.shootBig;
            squareSprite = false;
            ammo(
                    molecule, new ArtilleryBulletType(){{
                        speed = 5;
                        damage = 60;
                        lifetime = 20f;
                        width = 12f; height = 12f;
                        backColor = LHPal.moleculeCol;
                        frontColor = trailColor = lightColor = LHPal.moleculeLight;
                        trailEffect = Fx.artilleryTrail;
                        trailWidth = 3;
                        trailLength = 20;
                        ammoMultiplier = 1;
                        splashDamage = 15f;
                        splashDamageRadius = 30f;
                        hitEffect = despawnEffect = Fx.explosion;
                        fragBullets = 1;
                        fragBullet = new AreaDamageBulletType(){
                            {
                                hitEffect = despawnEffect = Fx.fallSmoke;
                                damage = 0;
                                speed = 0;
                                lifetime = 300f;
                                height = width = 0f;
                                collides = reflectable = absorbable = false;
                                splashDamageRadius = 6 * 8f;
                                splashDamage = 0f;
                                scaledSplashDamage = true;
                                backColor = hitColor = trailColor = areaColor = Color.valueOf("ea8878").lerp(LHPal.moleculeLight, 0.8f);
                                frontColor = Color.white;
                                ammoMultiplier = 1f;
                                hitSound = Sounds.minebeam;
                                areaStatuses = new StatusEffect[]{
                                        StatusEffects.corroded
                                };
                                areaEffectChance = 0.6f;
                                areaEffect = Fx.hitLaser;
                                areaDamage = 70;
                                areaRange = 10f;
                            }};
                    }}
            );
            drawer = new DrawTurret("rust-"){{
                parts.add(new RegionPart("-side"){{
                              progress = PartProgress.recoil;
                              mirror = true;
                              under = true;
                              moveX = 1f;
                              moveY = -1.5f;
                              moveRot = -15f;
                          }},
                        new RegionPart("-back"){{
                            progress = PartProgress.recoil;
                            heatColor = LHPal.moleculeLight;
                            layerOffset = 0.0001f;
                            mirror = false;
                            under = true;
                            moveY = -2.8f;
                        }}
                );
            }};
            consumeCoolant(0.5f).boost();
            coolantMultiplier = 1.5f;
            requirements(Category.turret, with(lead, 195, tungsten, 110, rust, 220, stainlessSteel, 160, molecule, 135));
        }};

        reinforcedLeadWall = new ArmorWall("lead-wall"){{
            requirements(Category.defense, with(lead, 24, ionite, 8, tungsten, 4));
            health = 185 * 4 * 4;
            armor = 5f;
            armorBonus = 6;
            buildCostMultiplier = 6.5f;
            size = 2;
        }};
        terriliumWall = new ArmorWall("terrilium-wall"){{
            requirements(Category.defense, with(terriliumAlloy, 16, silicon, 8));
            health = 210 * 4 * 4;
            armor = 6f;
            armorBonus = 8;
            buildCostMultiplier = 6.5f;
            size = 2;
            stage2fragile = true;
            bullet = new SapBulletType(){{
                damage = 15;
                status = LHStatuses.zeroGravity;
                sapStrength = 0;
                length = 75;
                color = lightColor = LHPal.terrilium;
            }};
        }};
        rustWall = new Wall("rust-wall"){{
            requirements(Category.defense, with(rust, 24));
            health = 142 * 16;
            size = 2;
            buildCostMultiplier = 2.5f;
        }};
        dawnBreaker = new Destructor("dawn-breaker"){{
            requirements(Category.turret, with(copper, 300, tungsten, 120, thorium, 165, silicon, 110, ionite, 80));

            outputItems = with(silicon, 1);
            range = 20 * 8f;
            size = 3;
            hasItems = true;
            consumePower(3f);
        }};
    }
}
