# trailmark-maps

Offline map data for [Trailmark](https://github.com/toufikben/trailmark).

This repository builds **MBTiles** vector packs from OpenStreetMap (via
Planetiler and the OpenMapTiles schema) and publishes them, with a
`manifest.json`, to the rolling `maps-latest` release.

The app fetches `manifest.json`, downloads the pack the user chooses, verifies
its SHA-256, and renders it fully offline.

## Build a pack

Actions → **build-pack** → Run workflow, with the region's Geofabrik path and
bounds. The result is attached to the `maps-latest` release and to the run's
artifacts.

## Data licence

Map data © OpenStreetMap contributors (ODbL). Style schema: OpenMapTiles.
