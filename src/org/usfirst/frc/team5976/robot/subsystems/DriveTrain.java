package org.usfirst.frc.team5976.robot.subsystems;

import org.usfirst.frc.team5976.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	private RobotDrive robotDrive;
	public CANTalon leftMaster, leftSlave, rightMaster, rightSlave;
	
	//Talon Values
	private final float PEAK_VOLTAGE = 6.0f;
	private final int ALLOWABLE_ERROR = 10;
	private final int kP = 2, kI = 0, kD = 50;
	//private final int RAMP_RATE = 4;
	
	public DriveTrain() {
		super();
		System.out.println("START INIT DriveTrain");
		leftMaster = new CANTalon(RobotMap.LEFT_MASTER);
		leftSlave = new CANTalon(RobotMap.LEFT_SLAVE);
		rightMaster = new CANTalon(RobotMap.RIGHT_MASTER);
		rightSlave = new CANTalon(RobotMap.RIGHT_SLAVE);
		
		//Master motors should always be the motor in front
		robotDrive = new RobotDrive(leftMaster, leftSlave, rightMaster, rightSlave);
		
		//Setup Talons for use
		leftMaster.setControlMode(CANTalon.TalonControlMode.Position.value);
		leftMaster.set(0);
		leftMaster.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		leftMaster.configEncoderCodesPerRev(360);
		leftMaster.setPosition(0);
		//lefMaster.setVoltageRampRate(RAMP_RATE);
		//Profile 0
		leftMaster.setProfile(0);
		leftMaster.configNominalOutputVoltage(+0.0f, -0.0f);
		leftMaster.configPeakOutputVoltage(+PEAK_VOLTAGE, -PEAK_VOLTAGE);
		leftMaster.setAllowableClosedLoopErr(ALLOWABLE_ERROR);
		leftMaster.setPID(kP, kI, kD);
		leftMaster.enableControl();
		System.out.println("END INIT left master");
		
		leftSlave.setControlMode(CANTalon.TalonControlMode.Follower.value);
		leftSlave.set(leftMaster.getDeviceID()); //Set the deviceID to follow drive outputs
		System.out.println("END INIT left slave");
		
		rightMaster.setControlMode(CANTalon.TalonControlMode.Position.value);
		rightMaster.set(0);
		rightMaster.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		rightMaster.configEncoderCodesPerRev(360);
		rightMaster.setPosition(0);
		//lefMaster.setVoltageRampRate(RAMP_RATE);
		//Profile 0
		rightMaster.setProfile(0);
		rightMaster.configNominalOutputVoltage(+0.0f, -0.0f);
		rightMaster.configPeakOutputVoltage(+PEAK_VOLTAGE, -PEAK_VOLTAGE);
		rightMaster.setAllowableClosedLoopErr(ALLOWABLE_ERROR);
		rightMaster.setPID(kP, kI, kD);
		rightMaster.enableControl();
		System.out.println("END INIT right master");
		
		rightSlave.setControlMode(CANTalon.TalonControlMode.Follower.value);
		rightSlave.set(leftMaster.getDeviceID());
		System.out.println("END INIT right slave");
		System.out.println("END INIT DriveTrain");
	}
	
	@Override
	protected void initDefaultCommand() {
		robotDrive.setExpiration(0.1);
		// TODO TeloOpTankDrive Command
	}
	
	
	public RobotDrive getRobotDrive() {
		return robotDrive;
	}

	public CANTalon getLeftMaster() {
		return leftMaster;
	}

	public CANTalon getRightMaster() {
		return rightMaster;
	}

	public double getAllowableError() {
		return ALLOWABLE_ERROR;
	}
}
