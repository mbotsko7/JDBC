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

INSERT INTO recording_group VALUES ('Weezer', 'Rivers Cuomo', '1992', 'Alternative rock');
INSERT INTO recording_group VALUES ('The Naked and Famous', 'Alisa Xayalith', '2007', 'Indie electronic');
INSERT INTO recording_group VALUES ('The Wiggles', 'Anthony Field', '1991', 'Children''s music');
INSERT INTO recording_group VALUES ('Yeah Yeah Yeahs', 'Karen O', '2000', 'Indie rock');

INSERT INTO album VALUES ('Passive Me, Aggressive You', 'The Naked and Famous', 'The Lab', '2010', '49:15', '13');
INSERT INTO album VALUES ('Weezer ("The Blue Album")', 'Weezer', 'Electric Lady Studios', '1994', '41:17', '10'); 
INSERT INTO album VALUES ('Pinkerton', 'Weezer', 'Electric Lady Studios', '1996', '34:36', '10');
INSERT INTO album VALUES ('The Wiggles', 'The Wiggles', 'Tracking Station Recording Studios', '1991', '34:42','26');
INSERT INTO album VALUES ('Fever to Tell', 'Yeah Yeah Yeahs', 'Headgear Studio', '2003', '37:26', '12');

INSERT INTO studio VALUES ('The Lab', '537 Mount Eden Rd, Mt Eden 1024, New Zealand', 'Olly Harper' , '+64 9-623 3930');
INSERT INTO studio VALUES ('Electric Lady Studios', '52 West Eighth Street, Greenwich Village, New York City 10011', 'Lee Foster' ,'212–677–4700');
INSERT INTO studio VALUES ('Tracking Station Recording Studio', '41 Holt St, SURRY HILLS NSW 2010, AUSTRALIA', , '(02) 9281 3557');
INSERT INTO studio VALUES ('Headgear Studio', '234 Wythe Ave, Brooklyn, NY 11211', 'Alex Lipsen', '718-302-3350');