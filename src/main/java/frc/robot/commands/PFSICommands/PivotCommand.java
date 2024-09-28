// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.PFSICommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.PFSI.PivotSubsystem;

public class PivotCommand extends Command {

  private final PivotSubsystem pivot;
  private double speed;

  /** Creates a new PivotCommand. */
  public PivotCommand(PivotSubsystem pivot,double speed) {
    this.pivot = pivot;
    this.speed = speed;
    addRequirements(pivot);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pivot.setVelocity(speed);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public void end(boolean interrupted) {
    pivot.setVelocity(0);
  }
}
