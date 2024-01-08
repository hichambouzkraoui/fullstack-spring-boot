-- changeset liquibase:1
CREATE TABLE employees (
  id INT PRIMARY KEY AUTO_INCREMENT,
  email_id VARCHAR(255),
  first_name VARCHAR(255),
  last_name VARCHAR(255)
  )