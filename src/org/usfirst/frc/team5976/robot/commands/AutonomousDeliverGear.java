package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousDeliverGear extends CommandGroup {

	public AutonomousDeliverGear(DriveTrain driveTrain) {
		//double revs = SmartDashboard.getNumber("Revs", 20.0);
		double rev1 = 1;
		double angle = 180;
		double rev2 = 1;
		
		Command forward = new DriveStraight(rev1, driveTrain);
		Command turn = new DriveStraight(angle, driveTrain);
		Command forward2 = new DriveStraight(rev2, driveTrain);

		addSequential(new InitDriveTrainForPositionMode(driveTrain));
		addSequential(forward);
		addSequential(turn);
		addSequential(forward2);
		System.out.println("END INIT MMC");
	}

}
