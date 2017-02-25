package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DoNothingMMC extends CommandGroup {
	public DoNothingMMC(DriveTrain driveTrain) {
		addSequential(new InitDriveTrainForPositionMode(driveTrain));
		addSequential(new DoNothingCommand(driveTrain));
		System.out.println("END INIT MMC Do Nothing");
	}
}
