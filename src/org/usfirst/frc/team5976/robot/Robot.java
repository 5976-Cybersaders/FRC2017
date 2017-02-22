
package org.usfirst.frc.team5976.robot;

import org.usfirst.frc.team5976.robot.commands.AutonomousDeliverGear;
import org.usfirst.frc.team5976.robot.commands.AutonomousDeliverGearDashboardEnabled;
import org.usfirst.frc.team5976.robot.commands.AutonomousDriveStraight;
import org.usfirst.frc.team5976.robot.commands.AutonomousTimedDriveStraight;
import org.usfirst.frc.team5976.robot.commands.DoNothingMMC;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static DriveTrain driveTrain;
	//public static Climber climber;
	public static OI oi;

	Command autonomousCommand;
	SendableChooser<Command> chooser;
	
	public Robot() {
		System.out.println("CONSTRUCTING CMH COMMAND BASED ROBOT");
		SmartDashboard.putNumber("Revs", 2.0);
		SmartDashboard.putNumber("P-value", 1.0);
		SmartDashboard.putNumber("I-value", 0);
		SmartDashboard.putNumber("D-value", 0);
		SmartDashboard.putNumber("drive1", 75.0);
		SmartDashboard.putNumber("drive2", 0);
		SmartDashboard.putNumber("drive3", 0);
		SmartDashboard.putNumber("angle", 0);
		
	}
	
	public void startCompetition() {
		System.out.println("STARTING COMPETITON");
		super.startCompetition();
	}
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		System.out.println("START ROBOT INIT");
		CameraServer.getInstance().startAutomaticCapture();
		System.out.println("END INIT CAMERA");
		
		//Subsystems
		oi = new OI();
		driveTrain = new DriveTrain(oi);
		//climber = new Climber(oi);
		System.out.println("END INIT SUBSYSTEMS");
		
		chooser = makeChooser();
		System.out.println("END ROBOT INIT");
	}
	
	public SendableChooser<Command> makeChooser() {
		SendableChooser<Command> chooser = new SendableChooser<>();
		chooser.addObject("Deliver Straight with Dashboard", new AutonomousDriveStraight(driveTrain));
		chooser.addObject("Deliver Gear with Dashboard", new AutonomousDeliverGearDashboardEnabled(driveTrain));
		chooser.addObject("Deliver Gear without Dashboard", new AutonomousDeliverGear(driveTrain));
		chooser.addDefault("Drive Forward without Encoders", new AutonomousTimedDriveStraight(driveTrain));
		chooser.addObject("Do Nothing", new DoNothingMMC(driveTrain));
		SmartDashboard.putData("Autonomous Commands", chooser);
		System.out.println("END INIT CHOOSER");
		return chooser;
	}
	
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		System.out.println("START AUTO INIT");
		driveTrain.updateDefaultCommandForNonTeleOp();
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		
		if (autonomousCommand != null) {
			System.out.println("START " + autonomousCommand.getName());
			autonomousCommand.start();
		}
		else {
			System.out.println("autonomousCommand is null");
		}
		System.out.println("END AUTO INIT");
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		driveTrain.updateDefaultCommandForTeleOp();
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
