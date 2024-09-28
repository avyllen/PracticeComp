// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.PFSICommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.PFSI.FeederSubsystem;
import frc.robot.subsystems.PFSI.ShooterSubsystem;
import frc.robot.subsystems.LEDLights.LEDSubsystem;

public class ShootCommand extends Command {
  private final FeederSubsystem feeder;
  private final ShooterSubsystem shooter;
  private final LEDSubsystem led;

  /** Creates a new ShootCommand. */
  public ShootCommand(FeederSubsystem feeder,ShooterSubsystem shooter,LEDSubsystem led) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.feeder = feeder;
    this.shooter = shooter;
    this.led = led;
    addRequirements(shooter,feeder, led);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    led.setBLUE();
    shooter.setVelocity(100);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooter.setVelocity(100);

      if(shooter.getSpeed() < -70) {
       feeder.setVelocity(.32);
    }
  }


  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !feeder.noteCheck();
  }

  // Called once the command ends or is interrupted.
    @Override
  public void end(boolean interrupted) {
    feeder.setVelocity(0);
    shooter.setVelocity(0);
    led.setRED();
  }
}
