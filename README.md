## 商品管理API
商品管理アプリケーションのサーバーサイド。  
商品・ユーザー・統計データを操作するRESTful API。

### 技術要素
* 言語 `Kotlin`
* フレームワーク `Spring Boot 2.1.7`
* DB `MySQL 5.7`
* ORM `Spring Data JPA`

### ローカル開発環境構築

* ソースコードのクローン

```
$ git clone git@github.com:KotaTanaka/goods-api-kotlin.git
$ cd goods-api-kotlin
```

*コンテナの起動 (docker-compose up)

```
$ ./start-docker.sh
```

* コンテナの停止 (docker-compose down)

```
$ ./stop-docker.sh
```

* アプリケーションサーバー起動

```
$ ./start-server.sh
```

→ http://localhost:7070 でサーバーが起動します。

* データベースログイン

```
$ ./mysql.sh
Enter password: password
mysql> use goods_manager_db;
```

* データベース初期化

```
$ rm -rf docker/db/mysql_data
$ ./stop-docker.sh && ./start-docker.sh
```

* ER図(データベース定義書)生成

```
$ ./generate-er.sh
```

### API一覧
| リクエスト | メソッド | URL |
|:---|:---|:---|
| 商品全件取得 | GET | /app/goods |
| 商品詳細取得 | GET | /app/goods/:id |
| 商品名部分一致検索 | GET | /app/goods/search |
| 商品登録 | POST | /app/goods |
| 商品情報更新 | PUT | /app/goods/:id |
| 商品削除 | DELETE | /app/goods/:id |
| ユーザー登録 | POST | /app/user |
| ログイン | PUT | /app/user/login |
| ログアウト | PUT | /app/user/logout |

*TODO OpenAPIでAPI定義書作成*

### DB定義
* 商品テーブル
```
+-------------+--------------+------+-----+---------+
| Field       | Type         | Null | Key | Default |
+-------------+--------------+------+-----+---------+
| id          | int(11)      | NO   | PRI | NULL    |
| name        | varchar(50)  | NO   |     | NULL    |
| description | varchar(500) | YES  |     | NULL    |
| price       | int(11)      | NO   |     | NULL    |
| created_at  | datetime     | NO   |     | NULL    |
| updated_at  | datetime     | NO   |     | NULL    |
+-------------+--------------+------+-----+---------+
```

* ユーザーテーブル
```
+-------------+-------------+------+-----+---------+
| Field       | Type        | Null | Key | Default |
+-------------+-------------+------+-----+---------+
| id          | varchar(16) | NO   | PRI | NULL    |
| name        | varchar(10) | NO   |     | NULL    |
| password    | varchar(8)  | NO   |     | NULL    |
| login_token | varchar(32) | YES  |     | NULL    |
| created_at  | datetime    | NO   |     | NULL    |
| updated_at  | datetime    | NO   |     | NULL    |
+-------------+-------------+------+-----+---------+
```

* 統計テーブル
```
+---------------+-------------+------+-----+---------+
| Field         | Type        | Null | Key | Default |
+---------------+-------------+------+-----+---------+
| id            | int(11)     | NO   | PRI | NULL    | auto_increment
| request       | varchar(32) | NO   |     | NULL    |
| status_code   | int(11)     | NO   |     | NULL    |
| access_times  | int(11)     | NO   |     | NULL    |
| average_time  | int(11)     | NO   |     | NULL    |
| aggregated_at | datetime    | NO   |     | NULL    |
+---------------+-------------+------+-----+---------+
```

### パッケージ構成
```
src/main/kotlin/com/kotatanaka/goodsapi
├── GoodsApiKotlinApplication.kt
├── app
│   ├── aop
│   ├── batch
│   ├── controller
│   ├── filter
│   └── handler
├── config
├── domain
│   ├── dto
│   │   ├── request
│   │   └── response
│   ├── entity
│   ├── enums
│   ├── exception
│   ├── repository
│   └── service
├── factory
├── util
└── validation
    └── validator
```
