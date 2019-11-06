-- --------------------------------------------------
-- 商品テーブル
-- --------------------------------------------------
CREATE TABLE IF NOT EXISTS `goods_manager_db`.`goods`
(
  `id`          INT          NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `name`        VARCHAR(50)  NOT NULL COMMENT '商品名',
  `description` VARCHAR(500) NULL DEFAULT NULL COMMENT '商品説明',
  `price`       INT          NOT NULL COMMENT '価格',
  `created_at`  DATETIME     NOT NULL COMMENT '登録日',
  `updated_at`  DATETIME     NOT NULL COMMENT '更新日',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

-- --------------------------------------------------
-- ユーザーテーブル
-- --------------------------------------------------
CREATE TABLE IF NOT EXISTS `goods_manager_db`.`user`
(
  `id`          VARCHAR(16) NOT NULL COMMENT 'ユーザーID',
  `name`        VARCHAR(10) NOT NULL COMMENT 'ユーザー名',
  `password`    VARCHAR(8)  NOT NULL COMMENT 'パスワード',
  `login_token` VARCHAR(32) NULL DEFAULT NULL COMMENT 'ログイントークン',
  `created_at`  DATETIME    NOT NULL COMMENT '登録日',
  `updated_at`  DATETIME    NOT NULL COMMENT '更新日',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

-- --------------------------------------------------
-- 統計テーブル
-- --------------------------------------------------
CREATE TABLE IF NOT EXISTS `goods_manager_db`.`aggregation`
(
  `id`            INT         NOT NULL AUTO_INCREMENT COMMENT '集計ID',
  `request`       VARCHAR(32) NOT NULL COMMENT 'リクエスト',
  `status_code`   INT         NOT NULL COMMENT 'ステータスコード',
  `access_times`  INT         NOT NULL COMMENT 'アクセス回数',
  `average_time`  INT         NOT NULL COMMENT '平均処理時間',
  `aggregated_at` DATETIME    NOT NULL COMMENT '集計日',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;
