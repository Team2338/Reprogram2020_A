package team.gif.lib;

import edu.wpi.first.wpilibj.geometry.*;
import edu.wpi.first.wpilibj.util.Units;

/*                      0
 *   Forward            +x    Heading can be -180 to 180 or 0 to 359, either will work
 *     _^_               ^       0 points straight ahead either way
 *    |   |   -90        |       code auto determines which is being used
 *    |   |    or  +y <--   90
 *    |___|   270
 *                      180
 */

public class Pose2dFeet {

    public Pose2dFeet () {
    }

    public Pose2d set (double x, double y, double heading) {

        // if compass heading is being used, convert to -180:180 scale
        heading = heading>180 ? -360 + heading : heading;

        Pose2d pos = new Pose2d(Units.feetToMeters(x), Units.feetToMeters(y), new Rotation2d(Units.degreesToRadians(-heading)));
        return pos;
    }
}
