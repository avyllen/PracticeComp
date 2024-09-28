// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.PFSI;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.NeutralOut;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.OperatorConstants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {

  private final TalonFX m_intake = new TalonFX(IntakeConstants.intakeID);
  private final VelocityVoltage m_voltageVelocity = new VelocityVoltage(0, 0, true, 0, 0, false, false, false);

private final NeutralOut m_brake = new NeutralOut();

TalonFXConfiguration intakeconfigs = new TalonFXConfiguration();

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {
           /* Voltage-based velocity requires a feed forward to account for the back-emf of the motor */
           intakeconfigs.Slot0.kP = 0.11; // An error of 1 rotation per second results in 2V output
           intakeconfigs.Slot0.kI = 0.5; // An error of 1 rotation per second increases output by 0.5V every second
           intakeconfigs.Slot0.kD = 0.0001; // A change of 1 rotation per second squared results in 0.01 volts output
           intakeconfigs.Slot0.kV = 0.12; // Falcon 500 is a 500kV motor, 500rpm per V = 8.333 rps per V, 1/8.33 = 0.12 volts / Rotation per second
           // Peak output of 8 volts
           intakeconfigs.Voltage.PeakForwardVoltage = 8;
           intakeconfigs.Voltage.PeakReverseVoltage = -8;
    
           m_intake.getConfigurator().apply(intakeconfigs);
  }

//  COMMANDS  //

  private void disable()
  {
    m_intake.setControl(m_brake);
  }

  public void setVelocity(double desiredRotationsPerSecond)
{
    m_intake.setControl(m_voltageVelocity.withVelocity(desiredRotationsPerSecond));
}

public Command highspeed()
{
  return runOnce(() -> this.setVelocity(250));
}

public Command midspeed()
{
  return runOnce(() -> this.setVelocity(100));
}

public Command slowspeed()
{
  return runOnce(() -> this.setVelocity(50));
}

public Command withVelocity(double desiredRotationsPerSecond)
{
  return runOnce(() -> this.setVelocity(desiredRotationsPerSecond));
}



public Command withDisable()
{
    return runOnce(() -> this.disable());
}


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
