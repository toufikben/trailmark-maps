package dev.trailmark.routing;

import com.graphhopper.GraphHopper;
import com.graphhopper.config.CHProfile;
import com.graphhopper.config.Profile;
import com.graphhopper.util.GHUtility;

/**
 * Imports an OSM extract into a GraphHopper routing graph with the two
 * profiles the Trailmark app asks for: foot (walk/run/hike) and bike.
 *
 * Usage: RoutingImport <area.osm.pbf> <output-graph-folder>
 */
public final class RoutingImport {

    /** Encoded values the bundled foot.json and bike.json custom models read. */
    public static final String ENCODED_VALUES =
            "foot_access, foot_priority, foot_average_speed, hike_rating, mtb_rating, "
                    + "bike_access, bike_priority, bike_average_speed, roundabout";

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
        hopper.setEncodedValuesString(ENCODED_VALUES);
        hopper.setProfiles(
                new Profile("foot").setCustomModel(GHUtility.loadCustomModelFromJar("foot.json")),
                new Profile("bike").setCustomModel(GHUtility.loadCustomModelFromJar("bike.json")));
        hopper.getCHPreparationHandler().setCHProfiles(new CHProfile("foot"), new CHProfile("bike"));

        hopper.importOrLoad();
        hopper.close();
        System.out.println("routing graph written to " + out);
    }
}
