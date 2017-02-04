package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.OI;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class EnterTeleOpCommand extends CommandGroup {
	
	public EnterTeleOpCommand(DriveTrain driveTrain) {
		addSequential(new InitDriveTrainForPercentVBusAllMasters(driveTrain));
		addSequential(new TeleOpTankDrive(driveTrain.getOI().getDriveController(), driveTrain));
		System.out.println("END INIT EnterTeleOpCommand");
	}
}
