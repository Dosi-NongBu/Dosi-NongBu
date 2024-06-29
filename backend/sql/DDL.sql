CREATE TABLE comments (
    bad BIGINT,
    comment_id BIGINT AUTO_INCREMENT,
    created_date TIMESTAMP(6),
    good BIGINT,
    modified_date TIMESTAMP(6),
    post_id BIGINT,
    user_id BIGINT,
    content TEXT NOT NULL,
    PRIMARY KEY (comment_id)
);

CREATE TABLE comments_reactions (
    comment_id BIGINT,
    comment_reaction_id BIGINT AUTO_INCREMENT,
    user_id BIGINT,
    reaction_type VARCHAR(255) CHECK (reaction_type IN ('GOOD','BAD')),
    PRIMARY KEY (comment_reaction_id)
);

CREATE TABLE comments_reports (
    comment_report_id BIGINT AUTO_INCREMENT,
    comment_id BIGINT,
    created_date TIMESTAMP(6),
    modified_date TIMESTAMP(6),
    user_id BIGINT,
    report_type VARCHAR(255) CHECK (report_type IN ('SPAM_AD','INSULT','PORNOGRAPHY','VIOLENCE_THREAT','FALSE_INFO_SCAM','COPYRIGHT_INFRINGEMENT','OTHER')),
    PRIMARY KEY (comment_report_id)
);

CREATE TABLE crops (
    crop_month INTEGER,
    difficulty INTEGER,
    humidity INTEGER,
    max_temperature INTEGER,
    min_temperature INTEGER,
    crop_id BIGINT AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (crop_id)
);

CREATE TABLE crops_images (
    crop_id BIGINT NOT NULL,
    image_url VARCHAR(255)
);

CREATE TABLE crops_informations (
    crop_id BIGINT NOT NULL,
    classification VARCHAR(255),
    origin VARCHAR(255),
    feature TEXT,
    PRIMARY KEY (crop_id)
);

CREATE TABLE crops_managements (
    crop_id BIGINT NOT NULL,
    cultivation TEXT,
    harvest_manage TEXT,
    pest TEXT,
    plating TEXT,
    tip TEXT,
    PRIMARY KEY (crop_id)
);

CREATE TABLE crops_periods (
    period INTEGER,
    crop_id BIGINT,
    crop_period_id BIGINT AUTO_INCREMENT,
    manage VARCHAR(255) CHECK (manage IN ('WATER','VENTILATION','REPOT','PRUNING')),
    PRIMARY KEY (crop_period_id)
);

CREATE TABLE faqs (
    created_date TIMESTAMP(6),
    faq_id BIGINT AUTO_INCREMENT,
    modified_date TIMESTAMP(6),
    faq_type VARCHAR(255) CHECK (faq_type IN ('GENERAL_FAQ','ACCOUNT_FAQ','TECHNICAL_FAQ','BILLING_FAQ')),
    faq_answer TEXT,
    faq_question TEXT,
    PRIMARY KEY (faq_id)
);

CREATE TABLE faqs_images (
    faq_id BIGINT NOT NULL,
    image_url VARCHAR(255)
);

CREATE TABLE inquirys (
    inquiry_status_type TINYINT CHECK (inquiry_status_type BETWEEN 0 AND 2),
    created_date TIMESTAMP(6),
    inquiry_id BIGINT AUTO_INCREMENT,
    modified_date TIMESTAMP(6),
    user_id BIGINT,
    inquiry_type VARCHAR(255) CHECK (inquiry_type IN ('GENERAL_INQUIRY','ACCOUNT_ISSUE','TECHNICAL_SUPPORT','BILLING_ISSUE','FEEDBACK_SUGGESTION')),
    title VARCHAR(255),
    content TEXT,
    inquiry_answer TEXT,
    PRIMARY KEY (inquiry_id)
);

CREATE TABLE inquirys_images (
    inquiry_id BIGINT NOT NULL,
    image_url VARCHAR(255)
);

CREATE TABLE notices (
    created_date TIMESTAMP(6),
    modified_date TIMESTAMP(6),
    notice_id BIGINT AUTO_INCREMENT,
    notice_type VARCHAR(255) CHECK (notice_type IN ('ANNOUNCEMENT','MAINTENANCE_NOTICE','NEW_FEATURE_UPDATE')),
    title VARCHAR(255),
    content TEXT,
    PRIMARY KEY (notice_id)
);

CREATE TABLE notices_images (
    notice_id BIGINT NOT NULL,
    image_url VARCHAR(255)
);

CREATE TABLE posts (
    bad BIGINT,
    created_date TIMESTAMP(6),
    good BIGINT,
    modified_date TIMESTAMP(6),
    post_id BIGINT AUTO_INCREMENT,
    user_id BIGINT,
    post_type VARCHAR(255) CHECK (post_type IN ('DEFAULT','QNA')),
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    PRIMARY KEY (post_id)
);

CREATE TABLE posts_images (
    post_id BIGINT NOT NULL,
    image_url VARCHAR(255)
);

CREATE TABLE posts_reactions (
    post_id BIGINT,
    post_reaction_id BIGINT AUTO_INCREMENT,
    user_id BIGINT,
    reaction_type VARCHAR(255) CHECK (reaction_type IN ('GOOD','BAD')),
    PRIMARY KEY (post_reaction_id)
);

CREATE TABLE posts_reports (
    created_date TIMESTAMP(6),
    modified_date TIMESTAMP(6),
    post_id BIGINT,
    post_report_id BIGINT AUTO_INCREMENT,
    user_id BIGINT,
    report_type VARCHAR(255) CHECK (report_type IN ('SPAM_AD','INSULT','PORNOGRAPHY','VIOLENCE_THREAT','FALSE_INFO_SCAM','COPYRIGHT_INFRINGEMENT','OTHER')),
    PRIMARY KEY (post_report_id)
);

CREATE TABLE users (
    created_date TIMESTAMP(6),
    modified_date TIMESTAMP(6),
    user_id BIGINT AUTO_INCREMENT,
    address VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    nickname VARCHAR(255),
    password VARCHAR(255),
    provider VARCHAR(255) CHECK (provider IN ('DEFAULT','GOOGLE','NAVER')),
    role VARCHAR(255) NOT NULL CHECK (role IN ('ADMIN','USER')),
    profile_image TEXT,
    PRIMARY KEY (user_id)
);

CREATE TABLE users_crops (
    humidity INTEGER,
    max_temperature INTEGER,
    min_temperature INTEGER,
    period INTEGER,
    previous_period INTEGER,
    created_date TIMESTAMP(6),
    crop_id BIGINT,
    modified_date TIMESTAMP(6),
    user_crop_id BIGINT AUTO_INCREMENT,
    user_id BIGINT,
    user_place_id BIGINT,
    name VARCHAR(255) NOT NULL,
    nickname VARCHAR(255),
    PRIMARY KEY (user_crop_id)
);

CREATE TABLE users_crops_alarms (
    is_alarm BOOLEAN,
    period INTEGER,
    user_crop_alarm_id BIGINT AUTO_INCREMENT,
    user_crop_id BIGINT,
    manage VARCHAR(255) CHECK (manage IN ('WATER','VENTILATION','REPOT','PRUNING')),
    PRIMARY KEY (user_crop_alarm_id)
);

CREATE TABLE users_crops_images (
    user_crop_id BIGINT NOT NULL,
    image_url VARCHAR(255)
);

CREATE TABLE users_crops_logs (
    created_date TIMESTAMP(6),
    crop_log_id BIGINT AUTO_INCREMENT,
    modified_date TIMESTAMP(6),
    user_crop_id BIGINT,
    manage VARCHAR(255) CHECK (manage IN ('WATER','VENTILATION','REPOT','PRUNING')),
    PRIMARY KEY (crop_log_id)
);

CREATE TABLE users_places (
    user_id BIGINT,
    user_place_id BIGINT AUTO_INCREMENT,
    direction VARCHAR(255) NOT NULL CHECK (direction IN ('SOUTH','EAST','WEST')),
    light VARCHAR(255) NOT NULL CHECK (light IN ('DIRECT','THROUGH','NONE')),
    name VARCHAR(255) NOT NULL,
    place VARCHAR(255) NOT NULL CHECK (place IN ('VERANDA','TERRACE','INSIDE')),
    quantity VARCHAR(255) NOT NULL CHECK (quantity IN ('LESS','MEDIUM','MANY')),
    PRIMARY KEY (user_place_id)
);

alter table comments add constraint FKh4c7lvsc298whoyd4w9ta25cr foreign key (post_id) references posts;
alter table comments add constraint FK8omq0tc18jd43bu5tjh6jvraq foreign key (user_id) references users;
alter table comments_reactions add constraint FK3bu6n44l5mt06cm499blx4jgs foreign key (comment_id) references comments;
alter table comments_reactions add constraint FKskudkdkcvrtgeknfmlqxqpl4p foreign key (user_id) references users;
alter table comments_reports add constraint FK49ui42721f706mxrukuya1l6i foreign key (comment_id) references comments;
alter table comments_reports add constraint FKtjfafr7cgtgmlwbrujo81oeha foreign key (user_id) references users;
alter table crops_images add constraint FKcagcsp499ujyfi3mslp441mv0 foreign key (crop_id) references crops;
alter table crops_informations add constraint FK98h4u0kcfgj87ki2exjyf58xm foreign key (crop_id) references crops;
alter table crops_managements add constraint FKkrglx3mqhqwd5xl80p9svlh88 foreign key (crop_id) references crops;
alter table crops_periods add constraint FKspx5hvef8fk4692x9lsgcx1d2 foreign key (crop_id) references crops;
alter table faqs_images add constraint FKpfvurtdjapabjnc0obqcyixl4 foreign key (faq_id) references faqs;
alter table inquirys add constraint FK9gfbqxw2gpj2dnofp5eohqbsl foreign key (user_id) references users;
alter table inquirys_images add constraint FKpnn85vwhdfaji33mjhhhm6exy foreign key (inquiry_id) references inquirys;
alter table notices_images add constraint FKnwa0pwvywweumlscifprnliok foreign key (notice_id) references notices;
alter table posts add constraint FK5lidm6cqbc7u4xhqpxm898qme foreign key (user_id) references users;
alter table posts_images add constraint FKr315ebcveolhvos3mj05cqmit foreign key (post_id) references posts;
alter table posts_reactions add constraint FKkdmghpci33leh2l8nmfey7phb foreign key (post_id) references posts;
alter table posts_reactions add constraint FK3wgfqlm4xqh334f0locgaupw8 foreign key (user_id) references users;
alter table posts_reports add constraint FK98kvd0c4b6dggm3wkmlfy9qar foreign key (post_id) references posts;
alter table posts_reports add constraint FKhobyfrhvorv5x5wd43mt4l04h foreign key (user_id) references users;
alter table users_crops add constraint FK4n2512bh5mqeqeg6xksve0rr1 foreign key (crop_id) references crops;
alter table users_crops add constraint FKaddky3av3t9i79nrfr137hgh2 foreign key (user_id) references users;
alter table users_crops add constraint FK45mbpcxjogvex2ulhc1p0dthc foreign key (user_place_id) references users_places;
alter table users_crops_alarms add constraint FKc7xvp6djtxvynjypkll0hc1p2 foreign key (user_crop_id) references users_crops;
alter table users_crops_images add constraint FK6ffuxwty9wran07t3cp4wlpsr foreign key (user_crop_id) references users_crops;
alter table users_crops_logs add constraint FKo6x6hgeli3ojrqxwul9u94i8t foreign key (user_crop_id) references users_crops;
alter table users_places add constraint FKddoqy9fk6pjkehrvgrj823e5 foreign key (user_id) references users;

CREATE TABLE SPRING_SESSION (
	PRIMARY_ID CHAR(36) NOT NULL,
	SESSION_ID CHAR(36) NOT NULL,
	CREATION_TIME BIGINT NOT NULL,
	LAST_ACCESS_TIME BIGINT NOT NULL,
	MAX_INACTIVE_INTERVAL INT NOT NULL,
	EXPIRY_TIME BIGINT NOT NULL,
	PRINCIPAL_NAME VARCHAR(100),
	CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;

CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);

CREATE TABLE SPRING_SESSION_ATTRIBUTES (
	SESSION_PRIMARY_ID CHAR(36) NOT NULL,
	ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
	ATTRIBUTE_BYTES BLOB NOT NULL,
	CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
	CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;