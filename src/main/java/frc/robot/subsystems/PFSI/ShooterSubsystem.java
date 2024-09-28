// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.PFSI;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.NeutralOut;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.OperatorConstants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {

  private final TalonFX m_topSh = new TalonFX(ShooterConstants.topShooter);
  private final TalonFX m_botSh = new TalonFX(ShooterConstants.botShooter);
  private final VelocityVoltage m_voltageVelocity = new VelocityVoltage(0, 0, true, 0, 0, false, false, false);
  private final NeutralOut m_brake = new NeutralOut();
  TalonFXConfiguration shooterConfigs = new TalonFXConfiguration();

  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {
  /* Voltage-based velocity requires a feed forward to account for the back-emf of the motor */
  shooterConfigs.Slot0.kP = 0.11; // An error of 1 rotation per second results in 2V output
  shooterConfigs.Slot0.kI = 0.5; // An error of 1 rotation per second increases output by 0.5V every second
  shooterConfigs.Slot0.kD = 0.0001; // A change of 1 rotation per second squared results in 0.01 volts output
  shooterConfigs.Slot0.kV = 0.12; // Falcon 500 is a 500kV motor, 500rpm per V = 8.333 rps per V, 1/8.33 = 0.12 volts / Rotation per second
  // Peak output of 8 volts
  shooterConfigs.Voltage.PeakForwardVoltage = 8;
  shooterConfigs.Voltage.PeakReverseVoltage = -8;

  m_topSh.getConfigurator().apply(shooterConfigs);
  m_botSh.getConfigurator().apply(shooterConfigs);
  }

  private void disable()
  {
      m_topSh.setControl(m_brake);
      m_botSh.setControl(m_brake);
  }

  public double getSpeed()
{
  return m_topSh.getRotorVelocity().getValue();
}
  public void setVelocity(double desiredRotationsPerSecond)
  {
      m_topSh.setControl(m_voltageVelocity.withVelocity(desiredRotationsPerSecond));
      m_botSh.setControl(m_voltageVelocity.withVelocity(desiredRotationsPerSecond));
  }
  private void setVelocityDiff(double TOPdesiredRotationsPerSecond,double BOTdesiredRotationsPerSecond)
  {
     m_topSh.setControl(m_voltageVelocity.withVelocity(TOPdesiredRotationsPerSecond));
     m_botSh.setControl(m_voltageVelocity.withVelocity(BOTdesiredRotationsPerSecond));
  }

//  COMMANDS

public Command highSpeed()
{
  return runOnce(() -> this.setVelocity(ShooterConstants.shootSpeed));
}

public Command midSpeed()
{
  return runOnce(() -> this.setVelocity(100));
}

public Command lowSpeed()
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
    SmartDashboard.putNumber("Shooter Speed", this.getSpeed());
  }
}
