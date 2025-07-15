package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "Auto: 2-Motor Forward then 45° Back", group = "Linear Opmode")
public class TwoMotorAuto extends LinearOpMode {

    private DcMotor leftDrive, rightDrive;

    @Override
    public void runOpMode() {
        // Initialize hardware
        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        rightDrive = hardwareMap.dcMotor.get("rightDrive");

        // Reverse one motor if needed for forward movement to work correctly
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        if (opModeIsActive()) {
            // 1. Drive forward for 1 second
            leftDrive.setPower(0.5);
            rightDrive.setPower(0.5);
            sleep(1000);

            // 2. Stop for 0.5 seconds
            leftDrive.setPower(0);
            rightDrive.setPower(0);
            sleep(500);

            // 3. Go backward at a 45° angle to the right
            // Only left motor powers backward
            leftDrive.setPower(-0.5);
            rightDrive.setPower(0.0);
            sleep(1000);

            // 4. Stop
            leftDrive.setPower(0);
            rightDrive.setPower(0);
        }
    }
}
