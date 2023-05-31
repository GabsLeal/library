CREATE TABLE book (
    id BIGINT PRIMARY KEY,
    title VARCHAR(255),
    subjects TEXT[],
    bookshelves TEXT[],
    languages TEXT[],
    copyright BOOLEAN,
    media_type VARCHAR(255),
    download_count INTEGER
);
CREATE TABLE author (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    birth_year INTEGER,
    death_year INTEGER
);
CREATE TABLE book_author (
    book_id BIGINT REFERENCES book (id),
    author_id BIGINT REFERENCES author (id),
    PRIMARY KEY (book_id, author_id)
);
CREATE TABLE review (
    id BIGSERIAL PRIMARY KEY,
    rating INTEGER,
    review_text TEXT,
    book_id BIGINT REFERENCES book (id)
);

ALTER TABLE book_author
ADD CONSTRAINT fk_book_author_book
FOREIGN KEY (book_id)
REFERENCES book (id);

ALTER TABLE book_author
ADD CONSTRAINT fk_book_author_author
FOREIGN KEY (author_id)
REFERENCES author (id);

ALTER TABLE review
ADD CONSTRAINT fk_review_book
FOREIGN KEY (book_id)
REFERENCES book (id);
