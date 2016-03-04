CREATE TABLE recording_group (
    gpName VARCHAR(20) NOT NULL,
    leadSinger VARCHAR(20),
    yearFormed INT,
    genre VARCHAR(15),
    CONSTRAINT pk_artist PRIMARY KEY (gpName)
);

CREATE TABLE album (
    title VARCHAR(20) NOT NULL,
    gpName VARCHAR(20) NOT NULL,
    stName VARCHAR(20) NOT NULL,
    dateRecorded DATE,
    length TIME,
    numSongs INT,
    CONSTRAINT pk_album PRIMARY KEY (title, gpName)
);

CREATE TABLE studio (
    stName VARCHAR(20) NOT NULL,
    address VARCHAR(30),
    owner VARCHAR(20),
    phone CHAR(10),
    CONSTRAINT pk_studio PRIMARY KEY (stName)
);


ALTER TABLE album
    ADD CONSTRAINT fk_groupalbum
    FOREIGN KEY (gpName)
    REFERENCES recording_group (gpName);

ALTER TABLE album
    ADD CONSTRAINT fk_studioalbum
    FOREIGN KEY (stName)
    REFERENCES studio (stName);

INSERT INTO album
(title, gpName, stName, dateRecorded, length, numSongs)
VALUES
("Shatter Me", "Lindsey Stirling", "Universal Music", 2014-04-29, 00:47:07, 12),
("Lindsey", "Lindsey Stirling", "Universal Music", 2012-03-29, 01:15:07, 17),
("Waking Up", "OneRepublic", "Interscope", 2009-11-17, 00:47:28, 11)
("American Idiot", "Green Day", "Reprise", 2004-09-20, 00:57:12, 13)
("21st Century Breakdown", "Green Day", "Reprise", 2009-05-15, 00:69:16, 18)

INSERT INTO studio
VALUES
("Universal Music", "Universal Avenue", "Monsieur Universal","7145555555"),
("Interscope", "Something Avenue", "Iris Interscope", "7146666666"),
("Reprise", "Music Avenue", "Ren Reprise","7147755555")

INSERT INTO recording_group
VALUES
("Lindsey Stirling", "Lindsey Stirling", 2008, "Classic/Electric"),
("OneRepublic", "John Smith", 2005, "Pop"),
("Green Day", "Adam West", 1990, "Rock")


