CREATE TABLE IF NOT EXISTS `goods_db`.`goods`
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
