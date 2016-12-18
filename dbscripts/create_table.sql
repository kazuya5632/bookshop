DROP DATABASE IF EXISTS bookshop;
CREATE DATABASE bookshop;

DROP TABLE IF EXISTS bookshop_customer;
DROP TABLE IF EXISTS books;

CREATE TABLE bookshop_customer (
      customer_name VARCHAR(64)
    , password VARCHAR(8) NOT NULL
    , PRIMARY KEY (customer_name)
);
CREATE TABLE books (
       isbn_code VARCHAR(13)
     , book_name VARCHAR(255) NOT NULL
     , author VARCHAR(255) NOT NULL
     , publisher VARCHAR(255) NOT NULL
     , price INT8 NOT NULL
     , PRIMARY KEY (isbn_code)
);
INSERT INTO books(author,isbn_code,book_name,publisher,price)VALUES('石井真 阿島哲夫','9784798010162','カンタンStruts1.2','秀和システム',2310);
INSERT INTO books(author,isbn_code,book_name,publisher,price)VALUES('技術評論社編集部','9784774119989','オープンソースJavaプロダクツ','技術評論社',1974);
INSERT INTO books(author,isbn_code,book_name,publisher,price)VALUES('テッド・ハスティッド(著)、株式会社 クイープ(翻訳)、芦沢嘉典(監修)','9784797323442','STRUTS・イン・アクション','ソフトバンクパブリッシング',5040);
INSERT INTO books(author,isbn_code,book_name,publisher,price)VALUES('Ｉ・シン(著)、Ｂ・スターンズ(著)、Ｍ・ジョンソン(著)、エンタープライズチーム(著)、（株）カサレアル (石井 真、尾形 隆彦、阿島 哲夫)(翻訳)、サン・マイクロシステムズ（株）(監修)','9784894716925','J2EEアプリケーション設計ガイド','ピアソン・エデュケーション',3360);
INSERT INTO books(author,isbn_code,book_name,publisher,price)VALUES('グラハム・グラス(著)、（株）カサレアル 尾形 隆彦(翻訳)、（株）カサレアル 石井 真(監修)、（株）タスカ 木村 和之(監修)','9784894715902','Ｗｅｂサービス入門','ピアソン・エデュケーション',3360);
INSERT INTO books(author,isbn_code,book_name,publisher,price)VALUES('Ｓ・ボドフ 他(著)、（株）カサレアル　(石井 真、尾形 隆彦、畠中 俊己、阿島 哲夫、藤沼 卓、上津 竜太郎、田坂 博志)(翻訳)、サン・マイクロシステムズ（株）(監修)','9784894716917','J2EETMチュートリアル日本語版','ピアソン・エデュケーション',5040);
INSERT INTO books(author,isbn_code,book_name,publisher,price)VALUES('マーティ・ホール(著)、岩谷 宏(翻訳)','9784797319895','モア・サーブレット＆ＪＳＰ','ソフトバンクパブリッシング',5460);
INSERT INTO books(author,isbn_code,book_name,publisher,price)VALUES('マーティ・ホール(著)、岩谷 宏(翻訳)','9784797314311','コア・サーブレット＆ＪＳＰ','ソフトバンクパブリッシング',4410);
INSERT INTO books(author,isbn_code,book_name,publisher,price)VALUES('WEB+DB PRESS編集部編','9784774119458','Jakartaプロジェクト徹底攻略2 -本格活用編-','技術評論社',1974);
