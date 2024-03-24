CREATE TABLE actor
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255),
    surName VARCHAR(255)
);

CREATE TABLE movie
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255),
    year INTEGER,
    shortDescription VARCHAR(),
    genre VARCHAR(255),
    rating INTEGER
);

CREATE TABLE actor_movie
(
    actorId BIGINT NOT NULL,
    movieId BIGINT NOT NULL,
    FOREIGN KEY (movieId) REFERENCES movie (id),
    FOREIGN KEY (actorId) REFERENCES actors (id),
    PRIMARY KEY (actorId, movieId)
);
