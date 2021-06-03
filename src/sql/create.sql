CREATE TABLE Users
(
	user_id BIGSERIAL PRIMARY KEY, 
	user_name VARCHAR(20) NOT NULL, 
	user_email VARCHAR(320) NOT NULL UNIQUE, 
	user_password VARCHAR(30) NOT NULL
);

CREATE TABLE Questions
(
	question_id BIGSERIAL PRIMARY KEY,
	question_name VARCHAR(300) NOT null unique,
	question_description VARCHAR(600) default null,
	user_id BIGINT NOT NULL,
	FOREIGN KEY (user_id) REFERENCES Users(user_id),
	question_words TSVECTOR NOT NULL
);

CREATE TABLE Answers
(
	answer_id BIGSERIAL PRIMARY KEY, 
	answer_name TEXT NOT NULL,
	question_id BIGINT NOT NULL,
	user_id BIGINT NOT NULL,
	FOREIGN KEY (question_id) REFERENCES Questions(question_id),
	FOREIGN KEY (user_id) REFERENCES Users(user_id),
	answer_words TSVECTOR NOT NULL
);

CREATE TABLE Topics
(
	topic_id BIGSERIAL PRIMARY KEY,
	topic_name VARCHAR(70) NOT null unique,
	topic_words TSVECTOR NOT NULL
);

CREATE TABLE TopicFollowers
(
	topic_follower_id BIGSERIAL PRIMARY KEY,
	topic_id BIGINT NOT NULL,
	user_id BIGINT NOT NULL,
	FOREIGN KEY (user_id) REFERENCES Users(user_id),
	FOREIGN KEY (topic_id) REFERENCES Topics(topic_id) 
);

CREATE TABLE QuestionRelatedTopics
(
	question_related_topic_id BIGSERIAL PRIMARY KEY,
	question_id BIGINT NOT NULL,
	topic_id BIGINT NOT NULL,
	FOREIGN KEY (question_id) REFERENCES Questions(question_id),
	FOREIGN KEY (topic_id) REFERENCES Topics(topic_id)
);

-- Predefined topics added by admin.
insert into Topics (topic_name, topic_words) values ('Communications Skills', to_tsvector('Communications Skills'));
insert into Topics (topic_name, topic_words) values ('Business, Work, and Careers', to_tsvector('Business, Work, and Careers'));
insert into Topics (topic_name, topic_words) values ('Jobs and Careers', to_tsvector('Jobs and Careers'));
insert into Topics (topic_name, topic_words) values ('Healthy Eating', to_tsvector('Healthy Eating'));
insert into Topics (topic_name, topic_words) values ('Technology Trends', to_tsvector('Technology Trends'));
insert into Topics (topic_name, topic_words) values ('Scientific Research', to_tsvector('Scientific Research'));
insert into Topics (topic_name, topic_words) values ('Linguistics', to_tsvector('Linguistics'));
insert into Topics (topic_name, topic_words) values ('Science, Technology, Engineering, and Mathematics', to_tsvector('Science, Technology, Engineering, and Mathematics'));
insert into Topics (topic_name, topic_words) values ('English Grammar', to_tsvector('English Grammar'));
insert into Topics (topic_name, topic_words) values ('Games', to_tsvector('Games'));
insert into Topics (topic_name, topic_words) values ('Sports', to_tsvector('Sports'));
insert into Topics (topic_name, topic_words) values ('Lifestyle', to_tsvector('Lifestyle'));
insert into Topics (topic_name, topic_words) values ('Crafts (art)', to_tsvector('Crafts (art)'));
insert into Topics (topic_name, topic_words) values ('World History', to_tsvector('World History'));
insert into Topics (topic_name, topic_words) values ('Law', to_tsvector('Law'));

