-- Seed Users (parent table for JOINED inheritance)
INSERT INTO USERS (id, name, email, phone, password)
VALUES (
        1,
        'Rahul Kumar',
        'rahul@example.com',
        '9876543210',
        'pass123'
    );
INSERT INTO USERS (id, name, email, phone, password)
VALUES (
        2,
        'Anjali Singh',
        'anjali@example.com',
        '9876543211',
        'pass123'
    );
INSERT INTO USERS (id, name, email, phone, password)
VALUES (
        3,
        'Arjun Kumar',
        'arjun@example.com',
        '9876543212',
        'pass123'
    );
INSERT INTO USERS (id, name, email, phone, password)
VALUES (
        4,
        'Priya Sharma',
        'priya@example.com',
        '9876543213',
        'pass123'
    );
INSERT INTO USERS (id, name, email, phone, password)
VALUES (
        5,
        'Ravi Menon',
        'ravi@example.com',
        '9876543214',
        'pass123'
    );
-- Seed Admin
INSERT INTO USERS (id, name, email, phone, password)
VALUES (
        6,
        'Admin User',
        'admin@rideshare.com',
        '9000000000',
        'admin123'
    );
INSERT INTO ADMIN (id)
VALUES (6);
-- Seed Commuters
INSERT INTO COMMUTER (id)
VALUES (1);
INSERT INTO COMMUTER (id)
VALUES (2);
-- Seed Drivers
INSERT INTO DRIVER (id, license_number, available)
VALUES (3, 'KA-1234567890', TRUE);
INSERT INTO DRIVER (id, license_number, available)
VALUES (4, 'KA-2345678901', TRUE);
INSERT INTO DRIVER (id, license_number, available)
VALUES (5, 'KA-3456789012', TRUE);
-- Place distances (Bengaluru areas, in km, bidirectional)
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('Koramangala', 'Indiranagar', 4.5);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('Koramangala', 'MG Road', 5.2);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('Koramangala', 'Whitefield', 18.0);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('Koramangala', 'Electronic City', 14.0);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('Koramangala', 'HSR Layout', 3.8);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('Koramangala', 'BTM Layout', 3.2);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('Koramangala', 'Jayanagar', 5.5);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('Koramangala', 'Marathahalli', 10.0);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('Indiranagar', 'MG Road', 3.1);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('Indiranagar', 'Whitefield', 16.5);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('Indiranagar', 'Electronic City', 17.0);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('Indiranagar', 'HSR Layout', 6.5);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('Indiranagar', 'BTM Layout', 7.0);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('Indiranagar', 'Jayanagar', 7.5);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('Indiranagar', 'Marathahalli', 9.5);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('MG Road', 'Whitefield', 15.0);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('MG Road', 'Electronic City', 19.0);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('MG Road', 'HSR Layout', 8.0);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('MG Road', 'BTM Layout', 8.5);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('MG Road', 'Jayanagar', 6.5);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('MG Road', 'Marathahalli', 12.0);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('Whitefield', 'Electronic City', 25.0);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('Whitefield', 'HSR Layout', 19.5);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('Whitefield', 'BTM Layout', 20.0);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('Whitefield', 'Marathahalli', 8.5);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('Electronic City', 'HSR Layout', 12.0);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('Electronic City', 'BTM Layout', 11.5);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('Electronic City', 'Jayanagar', 15.0);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('HSR Layout', 'BTM Layout', 2.5);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('HSR Layout', 'Jayanagar', 5.0);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('HSR Layout', 'Marathahalli', 11.0);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('BTM Layout', 'Jayanagar', 4.0);
INSERT INTO PLACE_DISTANCE (place_from, place_to, distance_km)
VALUES ('Jayanagar', 'Marathahalli', 13.0);
-- Reset ID sequence to avoid collision with seeded data
ALTER TABLE USERS
ALTER COLUMN ID RESTART WITH 100;
-- Reset vehicle sequence
ALTER TABLE VEHICLE
ALTER COLUMN ID RESTART WITH 100;
-- Seed Vehicles (linked to seeded drivers)
INSERT INTO VEHICLE (license_plate, model, type, active, driver_id)
VALUES (
        'KA-01-AB-1234',
        'Toyota Etios',
        'Sedan',
        TRUE,
        3
    );
INSERT INTO VEHICLE (license_plate, model, type, active, driver_id)
VALUES (
        'KA-02-CD-5678',
        'Mahindra XUV',
        'SUV',
        TRUE,
        4
    );
INSERT INTO VEHICLE (license_plate, model, type, active, driver_id)
VALUES (
        'KA-03-EF-9012',
        'Bajaj RE',
        'Auto',
        TRUE,
        5
    );