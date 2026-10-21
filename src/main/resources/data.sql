
--beaches tabel
INSERT INTO beaches (name, description, province, latitude, longitude)
VALUES ('Bergen aan Zee', 'A quiet, natural beach surrounded by high dunes, close to the dune area of the Schoorlse Duinen. Ideal for long walks and escaping the crowds.', 'Noord-Holland', 52.6627, 4.6272);

INSERT INTO beaches (name, description, province, latitude, longitude)
VALUES ('Scheveningen', 'The busiest seaside resort in the Netherlands, with a lively boulevard, the famous pier and many beach clubs. Perfect for a day full of activity.', 'Zuid-Holland', 52.1110, 4.2780);

INSERT INTO beaches (name, description, province, latitude, longitude)
VALUES ('Zandvoort', 'A popular beach close to Amsterdam and Haarlem, with plenty of beach pavilions and easy access by train. Great for a quick day at the sea.', 'Noord-Holland', 52.3745, 4.5265);

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
    (3, 5);  -- Zandvoort: Beach club