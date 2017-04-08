package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.SmartValue;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class AutonomousTimedTurn extends Command {
	private long timeMS = 2000;
	private long t0 = 0;
	private DriveTrain driveTrain;	
	private SmartValue smartTurnDirection, smartTurnTime;
	private double speed = 0.4;
	//Positive speed is left
	
	public AutonomousTimedTurn(DriveTrain driveTrain, SmartValue smartTurnTime, SmartValue turnDirection){
		this.driveTrain = driveTrain;
		this.smartTurnTime = smartTurnTime;
		this.smartTurnDirection = turnDirection;
		
	}
	protected void initialize(){
		t0 = System.currentTimeMillis();
		if (smartTurnDirection.getDouble() < 0) speed = -speed;
		if (smartTurnTime != null) timeMS = smartTurnTime.getLong();
		System.out.println("INIT " + getClass().getSimpleName() + " TimeMS: " + timeMS + "Turn Direction " + smartTurnDirection.getDouble());
	}
	
	protected void execute(){
		driveTrain.getRobotDrive().tankDrive(speed, -speed);
	}
	
	@Override
	protected boolean isFinished() {
		return System.currentTimeMillis() - t0 >= timeMS;
	}
	
	protected void end() {
		System.out.println("END " + getClass().getSimpleName());
	}

}
