# 2018 Robot

Some definitions:

**Collector** refers to the entire elevator carriage assembly. **Arm** refers to the pneumatic actuating arms. **Intake** refers to the collector's wheels. **Tilt** refers to the collector's motor which allows it to angle itself. **Hook** refers to the climbing hook and the motor that moves its deployment arm. 

## Match Setup

I don't think anything special needs to be done to setup the robot before a match. The Arm uses an analog potentiometer, so it can be in any position when the robot is powered on. The Elevator carriage needs to be set in the DOWN position (triggering the lower limit switch) - I can't figure out how to zero the encoder counts on a limit switch trigger, so we can't ensure that DOWN is our zero position with a zeroing command. This shouldn't be a problem, because that's its physical resting position (gravity and all that).

## Pneumatics Configurations

|Name|Default|
|----|-------|
|Drive Transmission|Low Gear|
|Winch Transmission|Elevator Mode|
|Arms|Closed|
|Winch Brake|Enabled (Brake Engaged)|
