package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class MultiMoveCommand extends CommandGroup {
	
	public MultiMoveCommand(DriveTrain driveTrain) {
		Command forward = new DriveStraight(toInches(2), driveTrain);
		//Command backward = new DriveStraight(toInches(-1), driveTrain);
		//Command backward2 = new DriveStraight(toInches(-1), driveTrain);

		addSequential(new InitDriveTrainForPositionMode(driveTrain));
		addSequential(forward);
		//addSequential(backward);
		//addSequential(backward2);
		System.out.println("END INIT MMC");
	}

	private double toInches(double numOfRotations) {
		return numOfRotations * Math.PI * 6;
	}
}