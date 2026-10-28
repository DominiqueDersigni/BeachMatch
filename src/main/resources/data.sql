
--beaches tabel
INSERT INTO beaches (name, description, province, latitude, longitude)
VALUES ('Bergen aan Zee', 'A quiet, natural beach surrounded by high dunes, close to the dune area of the Schoorlse Duinen. Ideal for long walks and escaping the crowds.', 'Noord-Holland', 52.6627, 4.6272);

INSERT INTO beaches (name, description, province, latitude, longitude)
VALUES ('Scheveningen', 'The busiest seaside resort in the Netherlands, with a lively boulevard, the famous pier and many beach clubs. Perfect for a day full of activity.', 'Zuid-Holland', 52.1110, 4.2780);

INSERT INTO beaches (name, description, province, latitude, longitude)
VALUES ('Zandvoort', 'A popular beach close to Amsterdam and Haarlem, with plenty of beach pavilions and easy access by train. Great for a quick day at the sea.', 'Noord-Holland', 52.3745, 4.5265);

INSERT INTO beaches (name, description, province, latitude, longitude)
VALUES ('Banjaardstrand', 'A wide family-friendly beach on the coast of Noord-Beveland, with dunes behind it and views of the Oosterscheldekering.', 'Zeeland', 51.5880, 3.6750);

INSERT INTO beaches (name, description, province, latitude, longitude)
VALUES ('Oostkapelle', 'A spacious Zeeland beach next to the nature area De Manteling, popular with walkers and visitors looking for a quieter stretch of coast.', 'Zeeland', 51.5800, 3.5360);

INSERT INTO beaches (name, description, province, latitude, longitude)
VALUES ('Nollestrand', 'A lively city beach in Vlissingen, close to the boulevard and known for views of ships passing through the Westerschelde.', 'Zeeland', 51.4480, 3.5500);

INSERT INTO beaches (name, description, province, latitude, longitude)
VALUES ('Buren aan Zee', 'A broad North Sea beach on Ameland, with open views, plenty of space for walks and a lifeguard post during the summer season.', 'Friesland', 53.4490, 5.8170);

INSERT INTO beaches (name, description, province, latitude, longitude)
VALUES ('Strand Horst', 'A long recreation area on the Wolderwijd near Ermelo, offering lakeside beaches and space for water sports and a relaxing day outdoors.', 'Gelderland', 52.3330, 5.5650);

INSERT INTO beaches (name, description, province, latitude, longitude)
VALUES ('Strand Nulde', 'A freshwater beach on the Nuldernauw near Putten, with shallow water and a relaxed setting for swimming and family outings.', 'Gelderland', 52.2830, 5.4950);

INSERT INTO beaches (name, description, province, latitude, longitude)
VALUES ('Erkemederstrand', 'A long sandy beach on the Nuldernauw near Zeewolde, with open water views and plenty of room for walking and recreation.', 'Flevoland', 52.2700, 5.4140);

INSERT INTO beaches (name, description, province, latitude, longitude)
VALUES ('Makkum Beach', 'A popular sandy beach on the IJsselmeer, known for its shallow water, broad shoreline and views across the lake.', 'Friesland', 53.0560, 5.3990);

INSERT INTO beaches (name, description, province, latitude, longitude)
VALUES ('Mirnserklif', 'A small beach on the IJsselmeer in the wooded Gaasterland area, appreciated for its peaceful setting and views over the water.', 'Friesland', 52.8620, 5.4580);

INSERT INTO beaches (name, description, province, latitude, longitude)
VALUES ('Recreatiestrand De Zandmeren', 'A freshwater recreation beach beside the Maas near Kerkdriel, offering a place to swim and unwind away from the coast.', 'Gelderland', 51.7720, 5.3370);

INSERT INTO beaches (name, description, province, latitude, longitude)
VALUES ('Callantsoog', 'A spacious sandy beach on the North Sea, backed by dunes and close to the village of Callantsoog.', 'Noord-Holland', 52.8410, 4.6930);

INSERT INTO beaches (name, description, province, latitude, longitude)
VALUES ('Petten', 'A broad sandy beach beside the Hondsbossche Duinen, with plenty of room for a quiet walk and sea air.', 'Noord-Holland', 52.7660, 4.6590);

INSERT INTO beaches (name, description, province, latitude, longitude)
VALUES ('Egmond aan Zee', 'A classic seaside beach near the village centre, with a lighthouse, dunes and a lively promenade nearby.', 'Noord-Holland', 52.6210, 4.6270);

INSERT INTO beaches (name, description, province, latitude, longitude)
VALUES ('Noordwijk aan Zee', 'A wide resort beach with an inviting boulevard, beach pavilions and easy access to the dunes.', 'Zuid-Holland', 52.2430, 4.4300);

INSERT INTO beaches (name, description, province, latitude, longitude)
VALUES ('Katwijk aan Zee', 'A family-friendly sandy beach beside Katwijk’s promenade, with views of the old village and its white church.', 'Zuid-Holland', 52.2040, 4.3950);

INSERT INTO beaches (name, description, province, latitude, longitude)
VALUES ('Hoek van Holland', 'A long, broad beach at the mouth of the Nieuwe Waterweg, popular for swimming, walks and watching ships.', 'Zuid-Holland', 51.9900, 4.1050);

INSERT INTO beaches (name, description, province, latitude, longitude)
VALUES ('Renesse', 'A long Zeeland beach with sections for families, water sports and nature, next to the dunes and forests of Schouwen.', 'Zeeland', 51.7350, 3.7590);

INSERT INTO beaches (name, description, province, latitude, longitude)
VALUES ('Cadzand-Bad', 'A broad sandy beach at the southernmost seaside resort in the Netherlands, known for coastal walks and fossil shark teeth.', 'Zeeland', 51.3790, 3.3840);

INSERT INTO beaches (name, description, province, latitude, longitude)
VALUES ('De Koog', 'A popular Texel beach near the island’s main seaside village, with a wide sandy shore and access to dunes and beach pavilions.', 'Noord-Holland', 53.1020, 4.7470);

INSERT INTO beaches (name, description, province, latitude, longitude)
VALUES ('West aan Zee', 'A broad North Sea beach on Terschelling, with open horizons, dunes and plenty of space for long walks.', 'Friesland', 53.3900, 5.2220);


--beachfeatures tabel
INSERT INTO beachfeatures (name, description)
VALUES ('Relax beach', 'A calm beach, ideal for relaxing and enjoying the peace and quiet.');

INSERT INTO beachfeatures (name, description)
VALUES ('Surf beach', 'Good waves and wind conditions for surfing, kitesurfing or windsurfing.');

INSERT INTO beachfeatures (name, description)
VALUES ('Dog beach', 'Dogs are welcome on this beach.');

INSERT INTO beachfeatures (name, description)
VALUES ('Lifeguard', 'Lifeguards are present during the summer season.');

INSERT INTO beachfeatures (name, description)
VALUES ('Beach club', 'There is at least one beach club or pavilion.');

INSERT INTO beachfeatures (name, description)
VALUES ('BBQ allowed', 'Barbecues or a small campfire are allowed in designated areas.');

-- koppeltabel beaches_beachfeatures
INSERT INTO beaches_beachfeatures (beach_id, beachfeature_id)
VALUES
    (1, 1),  -- Bergen aan Zee: Relax beach
    (1, 3),  -- Bergen aan Zee: Dog beach
    (1, 6),  -- Bergen aan Zee: BBQ allowed
    (2, 2),  -- Scheveningen: Surf beach
    (2, 4),  -- Scheveningen: Lifeguard
    (2, 5),  -- Scheveningen: Beach club
    (3, 4),  -- Zandvoort: Lifeguard
    (3, 5),  -- Zandvoort: Beach club
    (4, 1),  -- Banjaardstrand: Relax beach
    (4, 4),  -- Banjaardstrand: Lifeguard
    (4, 5),  -- Banjaardstrand: Beach club
    (5, 1),  -- Oostkapelle: Relax beach
    (5, 3),  -- Oostkapelle: Dog beach
    (5, 5),  -- Oostkapelle: Beach club
    (6, 4),  -- Nollestrand: Lifeguard
    (6, 5),  -- Nollestrand: Beach club
    (7, 1),  -- Buren aan Zee: Relax beach
    (7, 3),  -- Buren aan Zee: Dog beach
    (7, 4),  -- Buren aan Zee: Lifeguard
    (8, 1),  -- Strand Horst: Relax beach
    (8, 2),  -- Strand Horst: Surf beach
    (9, 1),  -- Strand Nulde: Relax beach
    (9, 5),  -- Strand Nulde: Beach club
    (10, 1), -- Erkemederstrand: Relax beach
    (10, 3), -- Erkemederstrand: Dog beach
    (10, 5), -- Erkemederstrand: Beach club
    (11, 1), -- Makkum Beach: Relax beach
    (11, 2), -- Makkum Beach: Surf beach
    (11, 5), -- Makkum Beach: Beach club
    (12, 1), -- Mirnserklif: Relax beach
    (12, 2), -- Mirnserklif: Surf beach
    (13, 1), -- Recreatiestrand De Zandmeren: Relax beach
    (14, 1), -- Callantsoog: Relax beach
    (14, 2), -- Callantsoog: Surf beach
    (14, 4), -- Callantsoog: Lifeguard
    (14, 5), -- Callantsoog: Beach club
    (15, 1), -- Petten: Relax beach
    (15, 2), -- Petten: Surf beach
    (15, 5), -- Petten: Beach club
    (16, 1), -- Egmond aan Zee: Relax beach
    (16, 2), -- Egmond aan Zee: Surf beach
    (16, 4), -- Egmond aan Zee: Lifeguard
    (16, 5), -- Egmond aan Zee: Beach club
    (17, 1), -- Noordwijk aan Zee: Relax beach
    (17, 2), -- Noordwijk aan Zee: Surf beach
    (17, 4), -- Noordwijk aan Zee: Lifeguard
    (17, 5), -- Noordwijk aan Zee: Beach club
    (18, 1), -- Katwijk aan Zee: Relax beach
    (18, 2), -- Katwijk aan Zee: Surf beach
    (18, 4), -- Katwijk aan Zee: Lifeguard
    (18, 5), -- Katwijk aan Zee: Beach club
    (19, 1), -- Hoek van Holland: Relax beach
    (19, 2), -- Hoek van Holland: Surf beach
    (19, 4), -- Hoek van Holland: Lifeguard
    (19, 5), -- Hoek van Holland: Beach club
    (20, 1), -- Renesse: Relax beach
    (20, 2), -- Renesse: Surf beach
    (20, 4), -- Renesse: Lifeguard
    (20, 5), -- Renesse: Beach club
    (21, 1), -- Cadzand-Bad: Relax beach
    (21, 2), -- Cadzand-Bad: Surf beach
    (21, 4), -- Cadzand-Bad: Lifeguard
    (21, 5), -- Cadzand-Bad: Beach club
    (22, 1), -- De Koog: Relax beach
    (22, 2), -- De Koog: Surf beach
    (22, 4), -- De Koog: Lifeguard
    (22, 5), -- De Koog: Beach club
    (23, 1), -- West aan Zee: Relax beach
    (23, 2), -- West aan Zee: Surf beach
    (23, 4), -- West aan Zee: Lifeguard
    (23, 5); -- West aan Zee: Beach club