***springboot-api-sandbox***

## About

SpringBoot + Kotlin でのサーバーサイドDDDの素振り  
商品を管理する RESTful API

*[Frontend - nuxt-frontend-sandbox](https://github.com/KotaTanaka/nuxt-frontend-sandbox)*

## Technology

* 言語 - `Kotlin 1.2.71`
* フレームワーク - `Spring Boot 2.1.7`
* ビルドツール - `Gradle`
* DB - `MySQL 5.7`
* ORM - `Spring Data JPA`
* 開発環境 - `Docker` `docker-compose`
* ユニットテスト - `MockK 1.9`

## Getting Started

* サービスの起動

```bash
$ docker-compose up -d
```

* アプリケーションの起動

```bash
$ ./start-server.sh
```

→ http://localhost:7070

* サービスの停止

```bash
$ docker-compose down
```

## Utility Commands

* データベースログイン

```bash
$ ./mysql.sh
Enter password: password
mysql> use goods_manager_db;
```

* データベース初期化

```bash
# DB削除
$ rm -rf docker/db/mysql_data

# サービス再起動(DB再生成)
$ ./stop-docker.sh && ./start-docker.sh
```

* ER図（データベース定義書）生成

```bash
# SchemaSpy
$ ./generate-er.sh
```

## API List
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

## Database

データベース名 `goods_manager_db`

| テーブル物理名 | 論理名 |
|:---|:---|
| `goods` | 商品 |
| `user` | ユーザー |
| `aggregation` | 統計（アクセス集計） |

## Package Architecture

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
