// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.OperatorConstants.ClimberConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ElevatorClimber.ElevatorCommand;
import frc.robot.commands.PFSICommands.FeederCommand;
import frc.robot.commands.PFSICommands.IntakeCommand;
import frc.robot.commands.PFSICommands.PivotCommand;
import frc.robot.commands.PFSICommands.ShootCommand;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.LEDLights.LEDSubsystem;
import frc.robot.subsystems.PFSI.FeederSubsystem;
import frc.robot.subsystems.PFSI.IntakeSubsystem;
import frc.robot.subsystems.PFSI.PivotSubsystem;
import frc.robot.subsystems.PFSI.ShooterSubsystem;

import com.ctre.phoenix.led.CANdle.LEDStripType;

import edu.wpi.first.wpilibj.simulation.ElevatorSim;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final SendableChooser<Command> autoChooser;

  // SUBSYSTEMS

  public final IntakeSubsystem intake = new IntakeSubsystem();

  public final ShooterSubsystem shooter = new ShooterSubsystem();

  public final FeederSubsystem feeder = new FeederSubsystem();

  public final PivotSubsystem pivot = new PivotSubsystem();

  public final ElevatorSubsystem elevator = new ElevatorSubsystem();

  public final LEDSubsystem led = new LEDSubsystem(0);

  public final ClimberSubsystem climber = new ClimberSubsystem();

  //COMMANDS

  public final ElevatorCommand elevatorUp = new ElevatorCommand(elevator, .1);
  public final ElevatorCommand elevatorDown = new ElevatorCommand(elevator,-.1);

  public final IntakeCommand intakeCommand = new IntakeCommand(intake, feeder, led, pivot);
  public final ShootCommand shootCommand = new ShootCommand(feeder, shooter, led);
  public final FeederCommand feederCommand = new FeederCommand(feeder, intake);
  public final PivotCommand pivotCommand = new PivotCommand(pivot, 0);

  // CONTROLLERS

  private final CommandXboxController xbox = new CommandXboxController(0);
  private final CommandJoystick joystick = new CommandJoystick(1);

  // SWERVE DRIVE CONFIGS TBD

  // INTAKE

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
   

    
    configureBindings();
// AUTO COMMANDS TBD

  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() 
  {
}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
//   public Command getAutonomousCommand() {
//     // An example command will be run in autonomous  }
// }
}
