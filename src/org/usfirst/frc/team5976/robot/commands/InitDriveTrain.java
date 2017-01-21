package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;

public abstract class InitDriveTrain extends Command {
	protected CANTalon leftMaster, rightMaster, leftSlave, rightSlave; 
	
	public InitDriveTrain(DriveTrain driveTrain) {
		leftMaster = driveTrain.getLeftMaster();
		leftSlave = driveTrain.getLeftSlave();
		rightMaster = driveTrain.getRightMaster();
		rightSlave = driveTrain.getRightSlave();
		initTalons();
		requires(driveTrain);
	}
	
	protected abstract void initTalons();
	
	protected void initialize() {
		leftMaster.reset();
		leftMaster.enable();
		leftMaster.setPosition(0);
		rightMaster.reset();
		rightMaster.enable();
		rightMaster.setPosition(0);
		System.out.println("INIT " + getClass().getSimpleName());
	}

	protected void execute() {
		System.out.println("EXECUTING " + getClass().getSimpleName());
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	protected void end() {
		System.out.println("END " + getClass().getSimpleName());
	}

	protected void interrupted() {
		end();
	}

}
