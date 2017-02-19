package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

public class AutonomousTimedDriveStraight extends EncoderDriveCommand {
	
	private DriveTrain driveTrain;
	private long t0;
	private long timeMS = 1000;
	private double speed = -0.5;
	
	public AutonomousTimedDriveStraight(DriveTrain driveTrain){
		super(driveTrain);
		this.driveTrain = driveTrain;
		requires(driveTrain);
	}
	
	protected void initialize(){
		super.initialize();
		t0 = System.currentTimeMillis();
		report(leftMaster);
		report(rightMaster);
	}
	
	protected void execute(){
		driveTrain.getRobotDrive().tankDrive(speed, speed);
	}
	
	@Override
	protected boolean isFinished() {
		return System.currentTimeMillis() - t0 >= timeMS;
	}
	
	protected void end() {
		report(leftMaster);
		report(rightMaster);
	}
}
