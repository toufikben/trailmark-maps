package dev.trailmark.routing;

import com.graphhopper.GraphHopper;
import com.graphhopper.config.CHProfile;
import com.graphhopper.config.Profile;
import com.graphhopper.util.CustomModel;

/**
 * Imports an OSM extract into a GraphHopper routing graph with the two
 * profiles the Trailmark app asks for: foot (walk/run/hike) and bike.
 *
 * Usage: RoutingImport <area.osm.pbf> <output-graph-folder>
 */
public final class RoutingImport {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("usage: RoutingImport <area.osm.pbf> <output-graph-folder>");
            System.exit(2);
        }
        String osm = args[0];
        String out = args[1];

        GraphHopper hopper = new GraphHopper();
        hopper.setOSMFile(osm);
        hopper.setGraphHopperLocation(out);
        hopper.setEncodedValuesString("foot_access, foot_average_speed, bike_access, bike_average_speed");
        hopper.setProfiles(
                new Profile("foot").setVehicle("foot").setCustomModel(new CustomModel()),
                new Profile("bike").setVehicle("bike").setCustomModel(new CustomModel()));
        hopper.getCHPreparationHandler().setCHProfiles(new CHProfile("foot"), new CHProfile("bike"));

        hopper.importOrLoad();
        hopper.close();
        System.out.println("routing graph written to " + out);
    }
}
