package com.almasb.fxglgames.spaceinvaders.level;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.extra.entity.components.CircularMovementComponent;
import com.almasb.fxglgames.spaceinvaders.Config;
import javafx.geometry.Point2D;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

import static com.almasb.fxglgames.spaceinvaders.Config.ENEMIES_PER_ROW;
import static com.almasb.fxglgames.spaceinvaders.Config.ENEMY_ROWS;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class Level6 extends SpaceLevel {

    private List<Animation<?>> animations = new ArrayList<>();

    private double t = 0.0;

    @Override
    public void init() {
        for (int y = 0; y < ENEMY_ROWS; y++) {
            for (int x = 0; x < ENEMIES_PER_ROW; x++) {

                final int finalY = y;
                FXGL.getMasterTimer().runOnceAfter(() -> {
                    Entity enemy;

                    if (finalY == 0) {
                        enemy = spawnEnemy(Config.WIDTH / 2, 50);

                        Animation<?> anim = Entities.animationBuilder()
                            .autoReverse(true)
                            .duration(Duration.seconds(1))
                            .repeat(Integer.MAX_VALUE)
                            .translate(enemy)
                            .from(enemy.getPosition())
                            .to(new Point2D(Config.WIDTH - 50 - 40, Config.HEIGHT / 2 - 50))
                            .buildAndPlay();

                        animations.add(anim);

                        enemy = spawnEnemy(Config.WIDTH - 50 - 40, 50);

                        anim = Entities.animationBuilder()
                                .autoReverse(true)
                                .duration(Duration.seconds(1))
                                .repeat(Integer.MAX_VALUE)
                                .translate(enemy)
                                .from(enemy.getPosition())
                                .to(new Point2D(Config.WIDTH / 2, Config.HEIGHT / 2 - 50))
                                .buildAndPlay();

                        animations.add(anim);

                    } else {
                        enemy = spawnEnemy(220, 180);
                        enemy.addComponent(new CircularMovementComponent(1.5, 100));
                    }

                }, Duration.seconds(t));

                t += 0.2;
            }
        }
    }

    @Override
    public void destroy() {
        animations.forEach(Animation::stop);
    }
}
