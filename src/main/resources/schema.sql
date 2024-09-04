CREATE TABLE if not exists HOTEL(
    id INT primary key AUTO_INCREMENT,
    name VARCHAR(250),
    location VARCHAR(250),
    rating INT
);

CREATE TABLE if not exists ROOM (
    id INT primary key AUTO_INCREMENT,
    roomNumber VARCHAR(250),
    type VARCHAR(250),
    price DOUBLE,
    hotelId INT ,
    FOREIGN KEY (hotelId) REFERENCES hotel(id)
);

