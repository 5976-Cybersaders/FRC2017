package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5976.robot.subsystems.GearDelivery;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousDeliverGear extends CommandGroup {

	public AutonomousDeliverGear(DriveTrain driveTrain, GearDelivery gearDelivery) {
		//double revs = SmartDashboard.getNumber("Revs", 20.0);
		double rev1 = 1;
		double angle = 180;
		double rev2 = 1;
		double rev3 = -1;
		
		Command forward = new DriveStraight(rev1, driveTrain);
		Command turn = new Turn(angle, driveTrain);
		Command forward2 = new DriveStraight(rev2, driveTrain);
		Command deliverGear = new DeliverGear(gearDelivery);
		Command backward = new DriveStraight(rev3, driveTrain);

		addSequential(new InitDriveTrainForPositionMode(driveTrain));
		addSequential(forward);
		addSequential(turn);
		addSequential(forward2);
		addSequential(deliverGear);
		addSequential(backward);
		System.out.println("END INIT MMC");
	}
}
