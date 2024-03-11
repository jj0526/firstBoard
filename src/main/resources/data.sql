INSERT INTO article(title, content) VALUES('aaaa', '1111');
INSERT INTO article(title, content) VALUES('bbbb', '2222');
INSERT INTO article(title, content) VALUES('cccc', '3333');

INSERT INTO  article(title, content) VALUES('your favorite movie?', 'leave comments');
INSERT INTO  article(title, content) VALUES('your favorite food?', 'leave comments!');
INSERT INTO  article(title, content) VALUES('your hobby?', 'leave comments!!');

INSERT INTO  comment(article_id, nickname, body) VALUES(4, 'Park', 'Harry Potter');
INSERT INTO  comment(article_id, nickname, body) VALUES(4, 'Kim', 'Harry Potter2');
INSERT INTO  comment(article_id, nickname, body) VALUES(4, 'Lee', 'Harry Potter3');

INSERT INTO  comment(article_id, nickname, body) VALUES(5, 'Park', 'steak');
INSERT INTO  comment(article_id, nickname, body) VALUES(5, 'Kim', 'steak2');
INSERT INTO  comment(article_id, nickname, body) VALUES(5, 'Lee', 'steak3');

INSERT INTO  comment(article_id, nickname, body) VALUES(6, 'Park', 'Reading a book');
INSERT INTO  comment(article_id, nickname, body) VALUES(6, 'Kim', 'Reading a book2');
INSERT INTO  comment(article_id, nickname, body) VALUES(6, 'Lee', 'Reading a book3');