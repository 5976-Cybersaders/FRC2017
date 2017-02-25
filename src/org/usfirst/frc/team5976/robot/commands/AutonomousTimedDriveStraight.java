package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

public class AutonomousTimedDriveStraight extends EncoderDriveCommand {
	
	private DriveTrain driveTrain;
	private long t0;
	private long timeMS = 6750; //3000 and 6750 hey thats pretty good
	private double speed = -0.4; //-0.5 and -0.4 hey thats pretty good
	
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
		System.out.println("INIT " + getClass().getSimpleName());
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
		System.out.println("END " + getClass().getSimpleName());
	}
}
