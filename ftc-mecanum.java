package org.firstinspires.ftc.teamcode.library;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Omni-directional pathing class for 2-wheel omni drive robots.
 * Moves in any direction using vector math and time-based driving.
 */
public class OnbotPathing_2wOmni {

    private final DcMotor motorX;  // Strafing motor (X-axis)
    private final DcMotor motorY;  // Forward/backward motor (Y-axis)

    private double intervalDistance = 1.0; // Inches per 0.1s interval
    private static final double INTERVAL_TIME_SEC = 0.1;

    /**
     * Constructor.
     *
     * @param motorX Motor responsible for lateral (X-axis) movement
     * @param motorY Motor responsible for longitudinal (Y-axis) movement
     */
    public OnbotPathing_2wOmni(DcMotor motorX, DcMotor motorY) {
        this.motorX = motorX;
        this.motorY = motorY;
    }

    /**
     * Sets how far (in inches) the robot travels in 0.1s at full power.
     *
     * @param distanceInInches inches per interval (default is 1.0)
     */
    public void setIntervalDistance(double distanceInInches) {
        this.intervalDistance = distanceInInches;
    }

    /**
     * Moves the robot toward the (x, y) coordinate using the shortest direction.
     * Will reverse if it's more efficient.
     *
     * @param x Target X in inches
     * @param y Target Y in inches
     */
    public void directionalDriveTo(double x, double y) {
        double distance = Math.hypot(x, y);
        if (distance == 0) return;

        double angle = Math.atan2(y, x);  // Forward vector angle
        double powerX = Math.cos(angle);
        double powerY = Math.sin(angle);

        // Choose reverse if more efficient (shorter total motor movement)
        if (Math.abs(powerX) + Math.abs(powerY) > Math.abs(-powerX) + Math.abs(-powerY)) {
            powerX *= -1;
            powerY *= -1;
        }

        double totalTimeSec = (distance / intervalDistance) * INTERVAL_TIME_SEC;

        ElapsedTime timer = new ElapsedTime();
        timer.reset();

        motorX.setPower(powerX);
        motorY.setPower(powerY);

        while (timer.seconds() < totalTimeSec) {
            // Passive wait
        }

        stopMotors();
    }

    /**
     * Stops both motors.
     */
    private void stopMotors() {
        motorX.setPower(0);
        motorY.setPower(0);
    }
}
