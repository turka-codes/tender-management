CREATE TABLE roles (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    rolename VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE users (
    id           INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username     VARCHAR(255) NOT NULL,
    company_name VARCHAR(255),
    email        VARCHAR(255) NOT NULL UNIQUE,
    password     VARCHAR(255) NOT NULL,
    role_id      INTEGER,
    CONSTRAINT users_roles_id_fk FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE biddings (
    id                INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    bidding_id        INTEGER NOT NULL UNIQUE,
    project_name      VARCHAR(255) NOT NULL,
    bid_amount        DOUBLE PRECISION NOT NULL,
    years_to_complete DOUBLE PRECISION NOT NULL,
    date_of_bidding   DATE DEFAULT CURRENT_DATE NOT NULL,
    status            VARCHAR(255) DEFAULT 'pending' NOT NULL,
    bidder_id         INTEGER NOT NULL,
    CONSTRAINT biddings_users_id_fk FOREIGN KEY (bidder_id) REFERENCES users(id)
);


INSERT INTO roles (rolename) VALUES ('BIDDER'), ('APPROVER');

INSERT INTO users (username, company_name, email, password, role_id) VALUES
     ('bidder1', 'companyOne', 'bidderemail@gmail.com', 'bidder123$', 1),
     ('bidder2', 'companyTwo', 'bidderemail2@gmail.com', 'bidder789$', 1),
     ('approver', 'defaultCompany', 'approveremail@gmail.com', 'approver123$', 2);
