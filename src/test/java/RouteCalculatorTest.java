import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.*;

public class RouteCalculatorTest extends TestCase {

    StationIndex stationIndex = new StationIndex();
    RouteCalculator routeCalculator = new RouteCalculator(stationIndex);
    List<Station> rout = new ArrayList<>();
    List<Station> routWithOneConnection = new ArrayList<>();
    List<Station> routWithTwoConnections = new ArrayList<>();

    @Override
    protected void setUp() {
        Line red = new Line(1, "Красная");
        Line blue = new Line(2, "Синяя");
        Line green = new Line(3, "Зелёная");

        Station red1 = new Station("Красная один", red);
        Station red2 = new Station("Красная два", red);
        Station red3 = new Station("Красная три", red);
        Station blue1 = new Station("Синяя один", blue);
        Station blue2 = new Station("Синяя два", blue);
        Station blue3 = new Station("Синяя три", blue);
        Station green1 = new Station("Зелёная один", green);
        Station green2 = new Station("Зелёная два", green);
        Station green3 = new Station("Зелёная три", green);

        red.addStation(red1);
        red.addStation(red2);
        red.addStation(red3);
        blue.addStation(blue1);
        blue.addStation(blue2);
        blue.addStation(blue3);
        green.addStation(green1);
        green.addStation(green2);
        green.addStation(green3);

        List<Station> connectRedBlue = Arrays.asList(red3, blue1);
        List<Station> connectBlueGreen = Arrays.asList(blue3, green1);

        stationIndex.addStation(red1);
        stationIndex.addStation(red2);
        stationIndex.addStation(red3);
        stationIndex.addStation(blue1);
        stationIndex.addStation(blue2);
        stationIndex.addStation(blue3);
        stationIndex.addStation(green1);
        stationIndex.addStation(green2);
        stationIndex.addStation(green3);
        stationIndex.addConnection(connectBlueGreen);
        stationIndex.addConnection(connectRedBlue);
        stationIndex.addLine(red);
        stationIndex.addLine(blue);
        stationIndex.addLine(green);

        rout.add(red1);
        rout.add(red2);
        rout.add(red3);

        routWithOneConnection.add(red1);
        routWithOneConnection.add(red2);
        routWithOneConnection.add(red3);
        routWithOneConnection.add(blue1);
        routWithOneConnection.add(blue2);


        routWithTwoConnections.add(red1);
        routWithTwoConnections.add(red2);
        routWithTwoConnections.add(red3);
        routWithTwoConnections.add(blue1);
        routWithTwoConnections.add(blue2);
        routWithTwoConnections.add(blue3);
        routWithTwoConnections.add(green1);
        routWithTwoConnections.add(green2);
        routWithTwoConnections.add(green3);

    }

    public void testCalculateDuration() {
        double actual = RouteCalculator.calculateDuration(rout);
        double expected = 5;
        assertEquals(expected, actual);
    }


    public void testGetShortestRouteOnTheLine() {

        List<Station> actual = routeCalculator.getShortestRoute(rout.get(0), rout.get(2));
        List<Station> expected = new ArrayList<>(rout);
        assertEquals(expected, actual);

    }

    public void testGetShortestRouteWithOneConnection() {
        List<Station> actual = routeCalculator.getShortestRoute(routWithOneConnection.get(0),
                routWithOneConnection.get(4));
        List<Station> expected = new ArrayList<>(routWithOneConnection);
        assertEquals(expected, actual);
    }

    public void testGetShortestWithTwoConnections() {
        List<Station> actual = routeCalculator.getShortestRoute(routWithOneConnection.get(0),
                routWithTwoConnections.get(8));
        List<Station> expected = new ArrayList<>(routWithTwoConnections);
        assertEquals(expected, actual);
    }


    @Override
    protected void tearDown() {

    }
}
