CREATE TABLE recording_group (
    gpName VARCHAR(20) NOT NULL,
    leadSinger VARCHAR(20),
    yearFormed INT,
    genre VARCHAR(20),
    CONSTRAINT pk_artist PRIMARY KEY (gpName)
);

CREATE TABLE album (
    title VARCHAR(40) NOT NULL,
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



INSERT INTO recording_group VALUES ('Lindsey Stirling', 'Lindsey Stirling', 2008, 'Classic/Electric');
INSERT INTO recording_group VALUES ('OneRepublic', 'John Smith', 2005, 'Pop');
INSERT INTO recording_group VALUES ('Green Day', 'Adam West', 1990, 'Rock');
INSERT INTO recording_group VALUES ('Weezer', 'Rivers Cuomo', 1992, 'Alternative rock');
INSERT INTO recording_group VALUES ('The Naked and Famous', 'Alisa Xayalith', 2007, 'Indie electronic');
INSERT INTO recording_group VALUES ('The Wiggles', 'Anthony Field', 1991, 'Children''s music');
INSERT INTO recording_group VALUES ('Yeah Yeah Yeahs', 'Karen O', 2000, 'Indie rock');

INSERT INTO studio VALUES ('Universal Music', 'Universal Avenue', 'Monsieur Universal','7145555555');
INSERT INTO studio VALUES('Interscope', 'Something Avenue', 'Iris Interscope', '7146666666');
INSERT INTO studio VALUES('Reprise', 'Music Avenue', 'Ren Reprise','7147755555');
INSERT INTO studio VALUES ('The Lab', '537 Mt Eden, Mt Eden, WA 34024', 'Olly Harper' , '7696233930');
INSERT INTO studio VALUES ('Electric Lady', '52 W Fifth St, NY, NY 10011', 'Lee Foster' ,'2126774700');
INSERT INTO studio VALUES ('Tracking Station', '41 Holt St, Surrey, OR 82934', 'Jan Hunt', '4033453557');
INSERT INTO studio VALUES ('Headgear Studio', '23 Wye St, Brooklyn, NY 11211', 'Alex Lipsen', '2123023350');

INSERT INTO album VALUES ('Shatter Me', 'Lindsey Stirling', 'Universal Music', '2014-04-29', '00:47:07', 12);
INSERT INTO album VALUES('Lindsey', 'Lindsey Stirling', 'Universal Music', '2012-03-29', '01:15:07', 17);
INSERT INTO album VALUES('Waking Up', 'OneRepublic', 'Interscope', '2009-11-17', '00:47:28', 11);
INSERT INTO album VALUES('American Idiot', 'Green Day', 'Reprise', '2004-09-20', '00:57:12', 13);
INSERT INTO album VALUES('21st Century Breakdown', 'Green Day', 'Reprise', '2009-05-15', '00:69:16', 18);
INSERT INTO album VALUES ('Passive Me, Aggressive You', 'The Naked and Famous', 'The Lab', '2010-02-25', '00:49:15', 13);
INSERT INTO album VALUES ('Weezer (The Blue Album)', 'Weezer', 'Electric Lady', '1994-03-01', '00:41:17', 10); 
INSERT INTO album VALUES ('Pinkerton', 'Weezer', 'Electric Lady', '1996-11-15', '00:34:36', 10);
INSERT INTO album VALUES ('The Wiggles', 'The Wiggles', 'Tracking Station', '1991-06-29', '00:34:42',26);
INSERT INTO album VALUES ('Fever to Tell', 'Yeah Yeah Yeahs', 'Headgear Studio', '2003-04-22', '00:37:26', 12);




