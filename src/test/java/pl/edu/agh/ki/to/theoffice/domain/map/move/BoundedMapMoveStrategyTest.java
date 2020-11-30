package pl.edu.agh.ki.to.theoffice.domain.map.move;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.agh.ki.to.theoffice.domain.map.Location;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BoundedMapMoveStrategyTest {

    BoundedMapMoveStrategy boundedMapMoveStrategy = new BoundedMapMoveStrategy(10, 10);

    @Test
    public void testDirectionToApproachTargetSouthEast() {
        // given
        Location source = new Location(1, 3);
        Location target = new Location(2, 2);

        // when
        Location.Direction direction = boundedMapMoveStrategy.getDirectionToApproachTarget(source, target);

        // then
        assertEquals(Location.Direction.SOUTH_EAST, direction);
    }

    @Test
    public void testDirectionToApproachTargetSouthWest() {
        // given
        Location source = new Location(10, 3);
        Location target = new Location(2, 2);

        // when
        Location.Direction direction = boundedMapMoveStrategy.getDirectionToApproachTarget(source, target);

        // then
        assertEquals(Location.Direction.SOUTH_WEST, direction);
    }

    @Test
    public void testDirectionToApproachTargetNorthEast() {
        // given
        Location source = new Location(1, 3);
        Location target = new Location(20, 10);

        // when
        Location.Direction direction = boundedMapMoveStrategy.getDirectionToApproachTarget(source, target);

        // then
        assertEquals(Location.Direction.NORTH_EAST, direction);
    }

    @Test
    public void testDirectionToApproachTargetNorthWest() {
        // given
        Location source = new Location(10, 3);
        Location target = new Location(2, 20);

        // when
        Location.Direction direction = boundedMapMoveStrategy.getDirectionToApproachTarget(source, target);

        // then
        assertEquals(Location.Direction.NORTH_WEST, direction);
    }

    @Test
    public void testDirectionToApproachTargetNorth() {
        // given
        Location source = new Location(1, 3);
        Location target = new Location(1, 20);

        // when
        Location.Direction direction = boundedMapMoveStrategy.getDirectionToApproachTarget(source, target);

        // then
        assertEquals(Location.Direction.NORTH, direction);
    }

    @Test
    public void testDirectionToApproachTargetSouth() {
        // given
        Location source = new Location(1, 30);
        Location target = new Location(1, 2);

        // when
        Location.Direction direction = boundedMapMoveStrategy.getDirectionToApproachTarget(source, target);

        // then
        assertEquals(Location.Direction.SOUTH, direction);
    }

    @Test
    public void testDirectionToApproachTargetWest() {
        // given
        Location source = new Location(10, 3);
        Location target = new Location(2, 3);

        // when
        Location.Direction direction = boundedMapMoveStrategy.getDirectionToApproachTarget(source, target);

        // then
        assertEquals(Location.Direction.WEST, direction);
    }

    @Test
    public void testDirectionToApproachTargetEast() {
        // given
        Location source = new Location(10, 3);
        Location target = new Location(20, 3);

        // when
        Location.Direction direction = boundedMapMoveStrategy.getDirectionToApproachTarget(source, target);

        // then
        assertEquals(Location.Direction.EAST, direction);
    }

    @Test
    public void testMoveToAllowedPosition() {
        // given
        Location location = new Location(2, 2);
        Location.Direction direction = Location.Direction.EAST;

        // when then
        assertEquals(new Location(3, 2), boundedMapMoveStrategy.move(location, direction));
    }

    @Test
    public void testMoveToForbiddenPosition() {
        // given
        Location location = new Location(0, 2);
        Location.Direction direction = Location.Direction.WEST;

        // when then
        assertEquals(location, boundedMapMoveStrategy.move(location, direction));
    }

}