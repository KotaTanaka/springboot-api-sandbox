#!/bin/sh
# ------------------------------
# ER図(SchemaSpy)を生成するスクリプト
# ------------------------------
PWD=`pwd`

docker run \
  --net "host" \
  -v "$PWD/docker/db/schemaspy/output:/output" \
  -v "$PWD/docker/db/schemaspy/schemaspy.properties:/schemaspy.properties" \
  schemaspy/schemaspy:snapshot

echo "============================================="
echo "Generated ER diagram by SchemaSpy."
echo "Show /docker/db/schemaspy/output/index.html"
echo "============================================="
