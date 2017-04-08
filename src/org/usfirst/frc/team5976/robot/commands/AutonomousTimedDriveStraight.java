package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.SmartValue;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class AutonomousTimedDriveStraight extends Command {
	
	private DriveTrain driveTrain;
	private long t0;
	private long timeMS = 7750; //3000 and 6750 hey thats pretty good
	private double speed = -0.4; //-0.5 and -0.4 hey thats pretty good
	private SmartValue smartSpeed, smartTime;
	
	public AutonomousTimedDriveStraight(DriveTrain driveTrain){
		this.driveTrain = driveTrain;
		requires(driveTrain);
	}
	
	public AutonomousTimedDriveStraight(DriveTrain driveTrain, SmartValue smartTime){
		this(driveTrain);
		this.smartTime = smartTime;
	}
	
	public AutonomousTimedDriveStraight(DriveTrain driveTrain, SmartValue smartTime, SmartValue smartSpeed){
		this(driveTrain, smartTime);
		this.smartSpeed = smartSpeed;
	}
	
	protected void initialize(){
		super.initialize();
		if (smartSpeed != null) speed = smartSpeed.getDouble();
		if (smartTime != null) timeMS = smartTime.getLong();
		t0 = System.currentTimeMillis();
		//report(leftMaster);
		//report(rightMaster);
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
		//report(leftMaster);
		//report(rightMaster);
		System.out.println("END " + getClass().getSimpleName());
	}
}
