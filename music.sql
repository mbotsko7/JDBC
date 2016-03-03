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


