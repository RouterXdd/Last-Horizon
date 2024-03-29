package lh.content;


import arc.graphics.Color;
import lh.classes.types.CrawlerSigilStatusEffect;
import lh.classes.types.SigilStatusEffect;
import lh.graphics.LHPal;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.effect.ParticleEffect;
import mindustry.entities.effect.WaveEffect;
import mindustry.graphics.Pal;
import mindustry.type.StatusEffect;

public class LHStatuses {
    public static StatusEffect
    speedSigil, damageSigil, healthSigil, reloadSigil, crawlerSigil, disabled, zeroGravity, stealYourSoul, powerRelease, infiniteReload, infiniteDamage;

    public static void load(){
        StatusEffects.corroded.effect = new ParticleEffect(){{
            particles = 1;
            length = cone = 0;
            sizeFrom = 1.75f;
            sizeTo = 0;
            colorFrom = Color.valueOf("89cf81");
            colorTo = Color.white;
        }};
        speedSigil = new SigilStatusEffect("sigils-speed"){{
            color = LHPal.collapse;
            speedMultiplier = 1.5f;
            dragMultiplier = 1.5f;
        }};
        damageSigil = new SigilStatusEffect("sigils-damage"){{
            color = LHPal.collapse;
            damageMultiplier = 1.5f;
        }};
        healthSigil = new SigilStatusEffect("sigils-health"){{
            color = LHPal.collapse;
            healthMultiplier = 1.5f;
            damage = -0.5f;
        }};
        reloadSigil = new SigilStatusEffect("sigils-reload"){{
            color = LHPal.collapse;
            reloadMultiplier = 1.5f;
            buildSpeedMultiplier = 1.5f;
        }};
        crawlerSigil = new CrawlerSigilStatusEffect("sigils-crawler"){{
            color = LHPal.collapse;
        }};
        disabled = new StatusEffect("disabled"){{
            color = Color.valueOf("b33caa");
            effect = Fx.regenSuppressParticle;
            disarm = true;
            speedMultiplier = 0;
        }};
        zeroGravity = new StatusEffect("zero-gravity"){{
            color = LHPal.terrilium;
            dragMultiplier = 0;
        }};
        stealYourSoul = new SigilStatusEffect("steal-your-soul"){{
            color = Color.valueOf("e94637");
            healthMultiplier = 0.0000000000001f;
        }};
        powerRelease = new StatusEffect("power-release"){{
            color = Pal.surge;
            speedMultiplier = 0.75f;
            reloadMultiplier = 0.9f;
            applyEffect = new WaveEffect(){{
                colorFrom = Color.white;
                colorTo = Pal.surge;
                strokeFrom = 4;
                strokeTo = 0;
                sizeFrom = 0;
                sizeTo = 15;
            }};
        }};
        infiniteReload = new SigilStatusEffect("infinite-reload"){{
            color = LHPal.regardLight;
            reloadMultiplier = Float.MAX_VALUE;
        }};
        infiniteDamage = new SigilStatusEffect("infinite-damage"){{
            color = LHPal.plexLight;
            damageMultiplier = Float.MAX_VALUE;
        }};
    }
}
