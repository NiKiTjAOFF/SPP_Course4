package Particles;

import org.lwjgl.util.vector.Vector3f;
import renderEngine.DisplayManager;

public class Particle {
    private Vector3f position;
    private Vector3f velocity;
    private final float GRAVITY = -50;
    private float gravityEffect;
    private float lifeLength;
    private float rotation;
    private float scale;
    private float elapsedTime = 0;
    private Vector3f startColor;
    private Vector3f endColor;

    public Particle(Vector3f position, Vector3f velocity, float gravityEffect, float lifeLength, float rotation,
                    float scale, Vector3f startColor, Vector3f endColor) {
        this.position = position;
        this.velocity = velocity;
        this.gravityEffect = gravityEffect;
        this.lifeLength = lifeLength;
        this.rotation = rotation;
        this.scale = scale;
        this.startColor = startColor;
        this.endColor = endColor;
        ParticleMaster.addParticle(this);
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getRotation() {
        return rotation;
    }

    public float getScale() {
        return scale;
    }

    public float getLifePercentage() {
        return elapsedTime / lifeLength;
    }

    public Vector3f getStartColor() {
        return startColor;
    }

    public Vector3f getEndColor() {
        return endColor;
    }

    protected boolean update() {
        velocity.y += GRAVITY * gravityEffect * DisplayManager.getFrameTimeSeconds();
        Vector3f change = new Vector3f(velocity);
        change.scale(DisplayManager.getFrameTimeSeconds());
        Vector3f.add(change, position, position);
        elapsedTime += DisplayManager.getFrameTimeSeconds();
        return elapsedTime < lifeLength;
    }
}
