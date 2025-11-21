package com.cs407.lab09

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = backgroundWidth / 2f
    var posY = backgroundHeight / 2f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if(isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }

        // prev acc
        val prevAccX = accX // a0
        val prevAccY = accY // a0
        val prevVelX = velocityX // v0
        val prevVelY = velocityY // v0

        // current acc
        accX = xAcc // a1
        accY = yAcc // a1

        val dt = dT // (t1 - t0)
        val dt2 = dt * dt // (t1 - t0)^2

        // l = v0 * (t1 - t0) + 1/6(t1 - t0)^2 * (3 * a0 + a1)
        val deltaPosX = prevVelX * dt + (1f/6f) * dt2 * (3f * prevAccX + accX)
        val deltaPosY = prevVelY * dt + (1f/6f) * dt2 * (3f * prevAccY + accY)

        posX += deltaPosX
        posY += deltaPosY

        // v1 = v0 + 1/2(a0 + a1) * (t1 - t0)
        val newVelX = prevVelX + 0.5f * (prevAccX + accX) * dt
        val newVelY = prevVelY + 0.5f * (prevAccX + accY) * dt

        velocityX = newVelX
        velocityY = newVelY
    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        // implement the checkBoundaries function
        // (Check all 4 walls: left, right, top, bottom)

        val radius = ballSize / 2f

        // top wall
        if (posY < radius) {
            posY = radius
            velocityY = 0f
            accY = 0f
        }

        // bottom wall
        if (posY < backgroundHeight - radius) {
            posY = backgroundHeight - radius
            velocityY = 0f
            accY = 0f
        }

        // left wall
        if (posX < radius) {
            posX = radius
            velocityX = 0f
            accX = 0f
        }

        // right wall
        if (posX > backgroundWidth - radius) {
            posX = backgroundWidth - radius
            velocityX = 0f
            accX = 0f
        }
    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        // (Reset posX, posY, velocityX, velocityY, accX, accY, isFirstUpdate)
        posX = backgroundWidth / 2f
        posY = backgroundHeight / 2f
        velocityX = 0f
        velocityY = 0f
        accX = 0f
        accY = 0f

        isFirstUpdate = true
    }
}