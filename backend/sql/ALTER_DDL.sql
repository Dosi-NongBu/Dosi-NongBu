use dosinongbu

ALTER TABLE crops_informations MODIFY classification TEXT;
ALTER TABLE crops_informations MODIFY origin TEXT;
ALTER TABLE crops_informations MODIFY feature TEXT;

ALTER TABLE crops_managements MODIFY cultivation TEXT;
ALTER TABLE crops_managements MODIFY harvest_manage TEXT;
ALTER TABLE crops_managements MODIFY pest TEXT;
ALTER TABLE crops_managements MODIFY plating TEXT;
ALTER TABLE crops_managements MODIFY tip TEXT;

ALTER TABLE faqs MODIFY faq_answer TEXT;
ALTER TABLE faqs MODIFY faq_question TEXT;

ALTER TABLE inquirys MODIFY content TEXT;
ALTER TABLE inquirys MODIFY inquiry_answer TEXT;

ALTER TABLE notices MODIFY content TEXT;

ALTER TABLE posts MODIFY content TEXT;

SHOW COLUMNS FROM posts;