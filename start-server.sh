#!/bin/sh
# ------------------------------
# サーバーを起動するスクリプト
# ------------------------------
docker-compose exec app /var/app/gradlew bootRun
