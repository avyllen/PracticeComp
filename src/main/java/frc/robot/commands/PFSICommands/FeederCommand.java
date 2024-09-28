// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.PFSICommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.PFSI.FeederSubsystem;
import frc.robot.subsystems.PFSI.IntakeSubsystem;

public class FeederCommand extends Command {
  private final FeederSubsystem feeder;
  private final IntakeSubsystem intake;
  public FeederCommand(FeederSubsystem feeder,IntakeSubsystem intake) {
    this.feeder = feeder;
    this.intake = intake;
    addRequirements(feeder,intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    feeder.setVelocity(.32);
    intake.setVelocity(-.32);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    feeder.setVelocity(.36);
    intake.setVelocity(.47);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
