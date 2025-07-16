package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.library.OnbotPathing_2wOmni;

/**
 * Autonomous OpMode that demonstrates shortest-path directional driving (with reverse).
 */
@Autonomous(name = "Omni Auto Reversible", group = "Auto")
public class AutoOmniPathingReversible extends LinearOpMode {

    @Override
    public void runOpMode() {
        DcMotor motorX = hardwareMap.dcMotor.get("motorX");
        DcMotor motorY = hardwareMap.dcMotor.get("motorY");

        OnbotPathing_2wOmni pathing = new OnbotPathing_2wOmni(motorX, motorY);
        pathing.setIntervalDistance(1.5); // Optional tuning

        waitForStart();

        // Move to a point forward-right
        pathing.directionalDriveTo(18, 18);
        sleep(500);

        // Move directly backward-left (will auto-reverse to drive forward)
        pathing.directionalDriveTo(-18, -18);
        sleep(500);

        // Move forward again
        pathing.directionalDriveTo(0, 24);
        sleep(500);

        // Efficiently move to the origin
        pathing.directionalDriveTo(-18, -6); // Reverse angle logic chooses best vector
    }
}
