-- Franchise
INSERT INTO franchise (name) VALUES ('The Lord of the Rings'); --1
INSERT INTO franchise (name) VALUES ('Shrek'); --2
-- Movies
INSERT INTO movie (title, genre, release_year, director, franchise_id)
VALUES ('The Lord of the Rings: The Fellowship of the Ring', 'Adventure', 2001, 'Peter Jackson', 1); --1
INSERT INTO movie (title, genre, release_year, director, franchise_id)
VALUES ('The Lord of the Rings: The Two Towers', 'Adventure', 2002, 'Peter Jackson', 1); --2
INSERT INTO movie (title, genre, release_year, director, franchise_id)
VALUES ('The Lord of the Rings: The Return of the King', 'Adventure', 2003, 'Peter Jackson', 1); --3
INSERT INTO movie (title, genre, release_year, director, franchise_id)
VALUES ('Shrek', 'Adventure', 2001, 'Andrew Adamson', 2); --4
INSERT INTO movie (title, genre, release_year, director, franchise_id)
VALUES ('Shrek 2', 'Adventure', 2004, 'Andrew Adamson', 2); --5
-- Characters
INSERT INTO character (name, gender) VALUES ('Frodo Baggins', 'male'); --1
INSERT INTO character (name, gender) VALUES ('Bilbo Baggins', 'male'); --2
INSERT INTO character (name, gender) VALUES ('Gandalf', 'male'); --3
INSERT INTO character (name, gender) VALUES ('Galadriel', 'female'); --4
INSERT INTO character (name, gender) VALUES ('Boromir', 'male'); --5
INSERT INTO character (name, gender) VALUES ('Theoden King', 'male'); --6

INSERT INTO character (name, gender) VALUES ('Shrek', 'male'); --7
INSERT INTO character (name, gender) VALUES ('Fiona', 'female'); --8
INSERT INTO character (name, gender) VALUES ('Farquaad', 'male'); --9
-- Movies with characters
INSERT INTO movie_characters (movies_id, characters_id) VALUES (1, 1);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (1, 2);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (1, 3);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (1, 4);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (1, 5);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (2, 1);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (2, 3);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (2, 6);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (3, 1);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (3, 2);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (3, 3);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (3, 4);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (3, 5);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (3, 6);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (4, 7);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (4, 8);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (4, 9);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (5, 7);
INSERT INTO movie_characters (movies_id, characters_id) VALUES (5, 8);