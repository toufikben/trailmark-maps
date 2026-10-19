# trailmark-maps

Offline map data for [Trailmark](https://github.com/toufikben/trailmark).

This repository builds **MBTiles** vector packs from OpenStreetMap (via
Planetiler and the OpenMapTiles schema) and publishes them, with a
`manifest.json`, to the rolling `maps-latest` release.

The app fetches `manifest.json`, downloads the pack the user chooses, verifies
its SHA-256, and renders it fully offline.

It also builds **GraphHopper routing graphs** (foot + bike profiles) for
offline turn-by-turn navigation, published with `routing-manifest.json` to the
rolling `routing-latest` release.

## Build a pack

Actions → **build-pack** → Run workflow, with the region's Geofabrik path and
bounds. The result is attached to the `maps-latest` release and to the run's
artifacts.

## Build a routing graph

Actions → **build-routing** → Run workflow, with the region's Geofabrik path
and a region id (same id as the map pack). The result is attached to the
`routing-latest` release and to the run's artifacts.

## Data licence

Map data © OpenStreetMap contributors (ODbL). Style schema: OpenMapTiles.
