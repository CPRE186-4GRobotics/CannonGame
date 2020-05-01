package com.fourgrobotics.cannongame.gameobjects;

import java.util.HashSet;
import java.util.Set;

/**
 * A class to handle physics for an object. This class only handles ideal two-dimensional motion. There is no friction
 * or air resistance, and the only universal force is gravity. Objects in motion will stay in motion unless acted on by
 * an external force, so objects in the X direction need to manually be stopped once they start moving. This is only
 * basic motion, so there is no energy or momentum, nor is there angular motions.
 * @author Jeremy Noesen (JNDev)
 */
public class Physics {
    
    private static Set<Physics> objects = new HashSet<>();
    private final double GRAVITY = 0.0981;
    private double xPosition;
    private double yPosition;
    private double xVelocity;
    private double yVelocity;
    private double xAcceleration;
    private double yAcceleration;
    private double gravityMultiplier;
    private int objectWidth;
    private int objectHeight;
    private int bottomBound;
    private int topBound;
    private int rightBound;
    private int leftBound;
    private boolean onGround;
    private boolean collidable;
    
    /**
     * sets initial position to (0,0), initial velocity to <0,0>, initial acceleration to <0,0>, gravity multiplier to
     * 1.0, object width to 0, object height to 0, floor level to window bottom, ceiling level to window top, walls to
     * window sides, on object to false, collidable to true, and adds this object to a hashmap of all objects with
     * physics
     */
    public Physics() {
        xPosition = 0;
        yPosition = 0;
        xVelocity = 0;
        yVelocity = 0;
        xAcceleration = 0;
        yAcceleration = 0;
        gravityMultiplier = 1.0;
        objectWidth = 0;
        objectHeight = 0;
        bottomBound = 1000;
        topBound = 0;
        rightBound = 1000;
        leftBound = 0;
        onGround = false;
        collidable = true;
        objects.add(this);
    }
    
    /**
     * calculates the next frame of motion in the x and y axis, then checks for collisions and updates position based on
     * collisions
     */
    public void tickPhysics() {
        updateMotionX();
        updateMotionY();
        checkCollisions();
    }
    
    /**
     * increases the x velocity based on x acceleration and increases the x position based on x velocity
     */
    private void updateMotionX() {
        xVelocity += xAcceleration;
        xPosition += xVelocity;
    }
    
    /**
     * increases the y velocity based on y acceleration and gravity and increases the y position based on y velocity
     */
    private void updateMotionY() {
        if (!onGround) yVelocity -= GRAVITY * gravityMultiplier;
        yVelocity += yAcceleration;
        yPosition -= yVelocity;
    }
    
    /**
     * check if a player is on the ground, ceiling, right of left walls and prevent them from going past them. Also do
     * the same for all existing physics implementing objects
     */
    private void checkCollisions() {
        
        if (xPosition + objectWidth >= rightBound) {
            xPosition -= xPosition + objectWidth - rightBound;
        } else if (xPosition <= leftBound) {
            xPosition += leftBound - xPosition;
        }
        
        if (yPosition <= topBound) yPosition += topBound - yPosition;
        
        for (Physics object : objects) {
            if (object == this || !object.isCollidable() || !collidable) continue;
            if (xPosition <= object.getPositionX() + object.getObjectWidth() &&
                    xPosition + objectWidth >= object.getPositionX() &&
                    yPosition <= object.getPositionY() + object.getObjectHeight() &&
                    yPosition + objectHeight >= object.getPositionY()) {
                double left = Math.abs(xPosition - object.getPositionX() - object.getObjectWidth());
                double right = Math.abs(xPosition + objectWidth - object.getPositionX());
                double top = Math.abs(yPosition - object.getPositionY() - object.getObjectHeight());
                double bottom = Math.abs(yPosition + objectHeight - object.getPositionY());
                double min = Math.min(Math.min(left, right), Math.min(top, bottom));
                if (min == left) {
                    xPosition += left;
                    xVelocity = 0;
                } else if (min == right) {
                    xPosition -= right;
                    xVelocity = 0;
                } else if (min == top) {
                    yPosition += top;
                    yVelocity = 0;
                } else if (min == bottom) {
                    yPosition -= bottom;
                    yVelocity = 0;
                }
            }
        }
        
        if (yPosition + objectHeight >= bottomBound) {
            yPosition -= yPosition + objectHeight - bottomBound;
            onGround = true;
        } else {
            onGround = false;
        }
    }
    
    /**
     * gets the object's x position
     *
     * @return x position
     */
    public double getPositionX() {
        return xPosition;
    }
    
    /**
     * sets the object's x position
     *
     * @param newX new x position
     */
    public void setPositionX(double newX) {
        xPosition = newX;
    }
    
    /**
     * get the object's y position
     *
     * @return y position
     */
    public double getPositionY() {
        return yPosition;
    }
    
    /**
     * sets the object's y position
     *
     * @param newY new y position
     */
    public void setPositionY(double newY) {
        yPosition = newY;
    }
    
    /**
     * gets the object's x velocity
     *
     * @return x velocity
     */
    public double getVelocityX() {
        return xVelocity;
    }
    
    /**
     * sets the object's x velocity
     *
     * @param newVelX new x velocity
     */
    public void setVelocityX(double newVelX) {
        xVelocity = newVelX;
    }
    
    /**
     * gets the object's y velocity
     *
     * @return y velocity
     */
    public double getVelocityY() {
        return yVelocity;
    }
    
    /**
     * sets the object's y velocity
     *
     * @param newVelY new y velocity
     */
    public void setVelocityY(double newVelY) {
        yVelocity = newVelY;
    }
    
    /**
     * gets the object's x acceleration
     *
     * @return x acceleration
     */
    public double getAccelerationX() {
        return xAcceleration;
    }
    
    /**
     * sets the object's x acceleration
     *
     * @param newAccX new x acceleration
     */
    public void setAccelerationX(double newAccX) {
        xAcceleration = newAccX;
    }
    
    /**
     * gets the object's y acceleration
     *
     * @return y acceleration
     */
    public double getAccelerationY() {
        return yAcceleration;
    }
    
    /**
     * sets the object's y acceleration
     *
     * @param newAccY new y acceleration
     */
    public void setAccelerationY(double newAccY) {
        yAcceleration = newAccY;
    }
    
    /**
     * gets the rate of gravity. default is 1.0
     *
     * @return gravity multiplier
     */
    public double getGravityMultiplier() {
        return gravityMultiplier;
    }
    
    /**
     * set a different gravity multiplier
     *
     * @param multiplier gravity multiplier
     */
    public void setGravityMultiplier(double multiplier) {
        gravityMultiplier = multiplier;
    }
    
    /**
     * check if an object can be collided with
     *
     * @return true if collidable
     */
    public boolean isCollidable() {
        return collidable;
    }
    
    /**
     * enable or disable collisions for the object
     *
     * @param collidable set to true to allow collisions
     */
    public void setCollidable(boolean collidable) {
        this.collidable = collidable;
    }
    
    /**
     * check if the object is on the ground or another object
     *
     * @return true if the object is on the ground or on top of another object
     */
    public boolean isOnGround() {
        return onGround;
    }
    
    /**
     * get the height of the object's bounding box
     *
     * @return object height
     */
    public int getObjectHeight() {
        return objectHeight;
    }
    
    /**
     * set the height of the object's bounding box
     *
     * @param objectHeight object height
     */
    public void setObjectHeight(int objectHeight) {
        this.objectHeight = objectHeight;
    }
    
    /**
     * get the width of the object's bounding box
     *
     * @return object width
     */
    public int getObjectWidth() {
        return objectWidth;
    }
    
    /**
     * set the width of the object's bounding box
     *
     * @param objectWidth object width
     */
    public void setObjectWidth(int objectWidth) {
        this.objectWidth = objectWidth;
    }
    
    /**
     * get the top bound that the object can collide with
     *
     * @return top bound position
     */
    public int getTopBound() {
        return topBound;
    }
    
    /**
     * set the top bound that the object can collide with
     *
     * @param topBound top bound position
     */
    public void setTopBound(int topBound) {
        this.topBound = topBound;
    }
    
    /**
     * get the bottom bound that the object can collide with
     *
     * @return bottom bound position
     */
    public int getBottomBound() {
        return bottomBound;
    }
    
    /**
     * set the bottom bound that the object can collide with
     *
     * @param bottomBound bottom bound position
     */
    public void setBottomBound(int bottomBound) {
        this.bottomBound = bottomBound;
    }
    
    /**
     * get the left bound position that the object can collide with
     *
     * @return left bound position
     */
    public int getLeftBound() {
        return leftBound;
    }
    
    /**
     * set the left bound position that the object can collide with
     *
     * @param leftBound left bound position
     */
    public void setLeftBound(int leftBound) {
        this.leftBound = leftBound;
    }
    
    /**
     * get the right bound position that the object can collide with
     *
     * @return right bound position
     */
    public int getRightBound() {
        return rightBound;
    }
    
    /**
     * set the right bound position that the object can collide with
     *
     * @param rightBound right bound position
     */
    public void setRightBound(int rightBound) {
        this.rightBound = rightBound;
    }
}
