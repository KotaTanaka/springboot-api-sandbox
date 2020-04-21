***goods-api-kotlin***

## About

商品管理アプリケーションのサーバーサイド。  
商品・ユーザー・統計データを操作する RESTful API。

*[Frontend - goods-spa-nuxt](https://github.com/KotaTanaka/goods-spa-nuxt)*

## Technology

* 言語 `Kotlin 1.2.71`
* フレームワーク `Spring Boot 2.1.7`
* ビルドツール `Gradle`
* DB `MySQL 5.7`
* ORM `Spring Data JPA`
* 開発環境 `Docker` `docker-compose`
* ユニットテスト `MockK 1.9`

## Getting Started

* インストール

```bash
$ git clone git@github.com:KotaTanaka/goods-api-kotlin.git
$ cd goods-api-kotlin
```

* サービスの起動

```bash
$ ./start-docker.sh
```

* アプリケーションの起動

```bash
$ ./start-server.sh
```

→ http://localhost:7070

* サービスの停止

```bash
$ ./stop-docker.sh
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
| `aggregattion` | 統計（アクセス集計） |

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
