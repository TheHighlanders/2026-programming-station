// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.Auto;
import frc.robot.subsystems.XRPDrivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final XRPDrivetrain drivetrain = new XRPDrivetrain();

  private final Auto autoCommand = new Auto(drivetrain);

  private final CommandJoystick kbd = new CommandJoystick(0);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    drivetrain.setDefaultCommand(
      Commands.either(Commands.none(), Commands.runOnce(() -> drivetrain.stop(), drivetrain), () -> {
        return kbd.button(1).getAsBoolean() || kbd.button(2).getAsBoolean() || kbd.button(3).getAsBoolean() || kbd.button(4).getAsBoolean();
      }
    ));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    kbd.button(1)
    .onTrue(
      Commands.run(() -> drivetrain.goForward(), drivetrain)
    );

    kbd.button(2)
    .onTrue(
      Commands.run(() -> drivetrain.goLeft(), drivetrain)
    );

    kbd.button(3)
    .onTrue(
      Commands.run(() -> drivetrain.goBack(), drivetrain)
    );

    kbd.button(4)
    .onTrue(
      Commands.run(() -> drivetrain.goRight(), drivetrain)
    );
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autoCommand;
  }
}
