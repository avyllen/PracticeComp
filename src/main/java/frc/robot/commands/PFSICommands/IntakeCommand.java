// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.PFSICommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LEDLights.LEDSubsystem;
import frc.robot.subsystems.PFSI.FeederSubsystem;
import frc.robot.subsystems.PFSI.IntakeSubsystem;
import frc.robot.subsystems.PFSI.PivotSubsystem;

public class IntakeCommand extends Command {
  private final IntakeSubsystem intake;
  private final FeederSubsystem feeder;
  private final PivotSubsystem pivot;
  private final LEDSubsystem led;

  /** Creates a new IntakeCommand. */
  public IntakeCommand(IntakeSubsystem intake,FeederSubsystem feeder,LEDSubsystem led,PivotSubsystem pivot) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.intake = intake;
    this.feeder = feeder;
    this.led = led;
    this.pivot = pivot;
    addRequirements(pivot,intake,feeder,led);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    led.setRED();
    feeder.setVelocity(.32);
    intake.setVelocity(-50);
    pivot.intakePosition();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    feeder.setVelocity(.33);
    intake.setVelocity(-50);
  }

  @Override
  public boolean isFinished() {
    return feeder.noteCheck();
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    feeder.setVelocity(0);
    intake.setVelocity(0);
    pivot.setVelocity(0);

    if(!interrupted) {
      led.setGREEN();
      //LimelightHelpers.setLEDMode_ForceBlink("limelight-backup");

    }
  }

}
