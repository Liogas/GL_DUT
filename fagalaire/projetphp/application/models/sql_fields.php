<?php
    $table = array(
                "utilisateur" => "CREATE TABLE IF NOT EXISTS utilisateur(uid int auto_increment,login varchar(50) NOT NULL UNIQUE,password varchar(255) NOT NULL,PRIMARY KEY(uid));",
                
                "formulaire" => "CREATE TABLE IF NOT EXISTS formulaire(skey char(64),creator_id int NOT NULL,stitle varchar(256) NOT NULL default 'Mon formulaire',description varchar(4096) default 'Voici une description de mon formulaire.',activate boolean NOT NULL default FALSE,creation_date datetime NOT NULL,activation_date datetime,lastmodified datetime,CONSTRAINT Fkc FOREIGN KEY(creator_id) REFERENCES utilisateur(uid) ON DELETE CASCADE,PRIMARY KEY(skey));",
                
                "questiontypes" => "CREATE TABLE IF NOT EXISTS questiontypes(qtype varchar(10) NOT NULL,PRIMARY KEY(qtype));",
                
                "question" => "CREATE TABLE IF NOT EXISTS question(qid int auto_increment,skey char(64) NOT NULL,qtitle varchar(512) NOT NULL default 'Question',qtype varchar(10) NOT NULL,qchoice varchar(512),help varchar(128),CONSTRAINT Fkq FOREIGN KEY (skey) REFERENCES formulaire(skey) ON DELETE CASCADE,FOREIGN KEY(qtype) REFERENCES questiontypes(qtype),PRIMARY KEY(qid));",
                
                "reponse" => "CREATE TABLE IF NOT EXISTS reponse(aid int auto_increment,qid int NOT NULL,skey char(64) NOT NULL,text varchar(4096) NOT NULL,submission_date datetime NOT NULL,CONSTRAINT Fkr FOREIGN KEY (qid) REFERENCES question(qid) ON DELETE CASCADE,CONSTRAINT Fks FOREIGN KEY (skey) REFERENCES formulaire(skey) ON DELETE CASCADE,PRIMARY KEY(aid));",
    );

    $insert = array(
                "INSERT IGNORE INTO questiontypes VALUES('text');",
                "INSERT IGNORE INTO questiontypes VALUES('textarea');",
                "INSERT IGNORE INTO questiontypes VALUES('checkbox');",
                "INSERT IGNORE INTO questiontypes VALUES('radio');",
                "INSERT IGNORE INTO questiontypes VALUES('date');",
                "INSERT IGNORE INTO questiontypes VALUES('list');",
    );
?>