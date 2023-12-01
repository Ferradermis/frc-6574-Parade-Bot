// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class ArcadeDrive extends CommandBase {
  /** Creates a new ArcadeDrive. */
  //private DriveTrain driveTrain;
  private double THROTTLE = 1;
  private double DEADBAND = .02;
  /*public ArcadeDrive(DriveTrain driveTrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.driveTrain = driveTrain;
    addRequirements(RobotContainer.driveTrain);
    
  }
*/
public ArcadeDrive(){

}
// Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double steer_cmd=0;
    double drive_cmd=0;

    // Read data from joystick and drive per joystick positioning
    double x = RobotContainer.getDriverLeftY();
    double y = -RobotContainer.getDriverRightX(); 
    if (Math.abs(x) < DEADBAND){
      x = 0;
    }
    if (Math.abs(y) < DEADBAND){
      y = 0;
    }
    drive_cmd = Math.pow(y, 1);       // cubing y makes it more "sensitive", maybe need to adjust deadbands
    steer_cmd = Math.pow(x, 1) / 2; // cubing x and /2 makes it more "sensitive", maybe need to adjust deadbands
  
    // throttle is constant that controls "speed" of robot; helpful in testing in small areas
    RobotContainer.driveTrain.arcadeDrive(drive_cmd*THROTTLE, steer_cmd*0.5);   // Jacob Changed to make top speed 0.5
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
