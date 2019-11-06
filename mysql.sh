#!/bin/sh
# ------------------------------
# コンテナ内のMySQLに入るスクリプト
# ------------------------------
docker exec -it goods-manager-mysql \
  bash -c "mysql -u root -p"
