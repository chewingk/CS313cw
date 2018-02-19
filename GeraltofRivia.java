import lejos.nxt.*;
import lejos.robotics.subsumption.*;
import lejos.robotics.navigation.DifferentialPilot;

public class GeraltofRivia {
  DifferentialPilot pilot;
	LightSensor light_left;
  LightSensor light_right;

  public GeraltofRivia () {
    light_left = new LightSensor(SensorPort.S3);
    light_right = new LightSensor(SensorPort.S2);
    pilot = new DifferentialPilot(2.25f, 5.5f, Motor.C, Motor.B);
    pilot.setTravelSpeed(1);
  }

  public static void main(String[] args) {
    GeraltofRivia robot = new GeraltofRivia();
    Behavior line_follower = new LineFollower(robot.pilot, robot.light_left, robot.light_right);
    Behavior adjust = new AjustDirection(robot.pilot, robot.light_left, robot.light_right, robot.light_left.readNormalizedValue(), robot.light_right.readNormalizedValue());
    Behavior term = new Terminate(robot.pilot, robot.light_left, robot.light_right);
    Behavior dir = new TurnDirect(robot.pilot, robot.light_left, robot.light_right);
    Behavior [] b_array = {line_follower, adjust, dir, term};
    Arbitrator arby = new Arbitrator(b_array);
    arby.start();
  }
}
