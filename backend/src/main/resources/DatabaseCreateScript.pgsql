CREATE TABLE Addresses (
    Id SERIAL PRIMARY KEY,
    Address1 VARCHAR(255) NOT NULL,
    Address2 VARCHAR(255),
    Postcode VARCHAR(15),
    Town VARCHAR(255),
    Country VARCHAR(255)
);

CREATE TABLE Customers (
    Id SERIAL PRIMARY KEY,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    Title INT,
    Email VARCHAR(255) NOT NULL UNIQUE,
    IsCorporate BOOLEAN NOT NULL,
    AddressId INT REFERENCES Addresses(Id)
);

CREATE TABLE Cars (
    Id SERIAL PRIMARY KEY,
    PlateNo VARCHAR(20) NOT NULL,
    CustomerId INT NOT NULL,
    CONSTRAINT FK_CustomerCar FOREIGN KEY (CustomerId) REFERENCES Customers(Id),
    CONSTRAINT UC_CarCustomer UNIQUE (CustomerId, PlateNo)
);

CREATE TABLE Garages (
    Id SERIAL PRIMARY KEY,
    LocationName VARCHAR(255) NOT NULL UNIQUE,
    AddressId INT NOT NULL,
    CONSTRAINT FK_LocationAddress FOREIGN KEY (AddressId) REFERENCES Addresses(Id)
);

CREATE TABLE Scanner (
    Id SERIAL PRIMARY KEY,
    ScannerType VARCHAR(20) NOT NULL,
    LocationId INT NOT NULL,
    CONSTRAINT FK_ScannerLocation FOREIGN KEY (LocationId) REFERENCES Garages(Id)
);

CREATE TABLE ParkingSpaces (
    Id SERIAL PRIMARY KEY,
    Code VARCHAR(10) NOT NULL UNIQUE,
    LocationFloor VARCHAR(2) NOT NULL,
    LocationId INT NOT NULL,
    CONSTRAINT FK_SpaceLocation FOREIGN KEY (LocationId) REFERENCES Garages(Id)
);

CREATE TABLE SensorDevice (
    Id SERIAL PRIMARY KEY,
    SensorStatus INT NOT NULL,
    LocationId INT NOT NULL,
    ParkingSpaceId INT NOT NULL,
    CONSTRAINT FK_SensorLocation FOREIGN KEY (LocationId) REFERENCES Garages(Id),
    CONSTRAINT FK_SensorSpace FOREIGN KEY (ParkingSpaceId) REFERENCES ParkingSpaces(Id)
);

CREATE TABLE Discounts (
    Id SERIAL PRIMARY KEY,
    DiscountType INT NOT NULL,
    FlatAmount NUMERIC(10,4),
    PercentageAmount INT
);

CREATE TABLE PriceTypes (
    Id SERIAL PRIMARY KEY,
    Price NUMERIC(10,4) NOT NULL,
    TypeName VARCHAR(255) NOT NULL
);

CREATE TABLE Contracts (
    Id SERIAL PRIMARY KEY,
    DateAgreed TIMESTAMP NOT NULL,
    ContractNumber VARCHAR(255) UNIQUE NOT NULL,
    IsRecurrent BOOLEAN,
    CustomerId INT NOT NULL,
    CONSTRAINT FK_ContractCustomer FOREIGN KEY (CustomerId) REFERENCES Customers(Id)
);

CREATE TABLE SpaceReservations (
    Id SERIAL PRIMARY KEY,
    DateFrom DATE,
    DateTo DATE,
    TimeFrom TIME,
    TimeTo TIME,
    ContractId INT NOT NULL,
    PriceTypeId INT NOT NULL,
    SpaceId INT NOT NULL,
    CarId INT REFERENCES Cars(Id),
    CONSTRAINT FK_ContractReservation FOREIGN KEY (ContractId) REFERENCES Contracts(Id),
    CONSTRAINT FK_PriceReservation FOREIGN KEY (PriceTypeId) REFERENCES PriceTypes(Id),
    CONSTRAINT FK_SpaceReservation FOREIGN KEY (SpaceId) REFERENCES ParkingSpaces(Id),
    CONSTRAINT UC_DateTime UNIQUE (SpaceId, DateFrom, DateTo, TimeFrom, TimeTo)
);

CREATE TABLE Invoices (
    Id SERIAL PRIMARY KEY,
    DateCreated TIMESTAMP NOT NULL,
    DatePaid TIMESTAMP,
    InvoiceNumber VARCHAR(255) NOT NULL UNIQUE,
    Total NUMERIC(10,4) NOT NULL,
    IsRefund BOOLEAN,
    DiscountId INT REFERENCES Discounts(Id),
    ContractId INT NOT NULL,
    CONSTRAINT FK_ContractInvoice FOREIGN KEY (ContractId) REFERENCES Contracts(Id)
);

-- TEST DATA SEEDS --
INSERT INTO Addresses (Address1, Postcode, Town, Country)
VALUES ('123 Fake Str', 'AB12CD', 'London', 'United Kingdom'),
       ('Another Str', 'EF34GH', 'London', 'United Kingdom');

INSERT INTO Garages (LocationName, AddressId)
VALUES ('Location 1', (SELECT Id FROM Addresses WHERE Address1 = '123 Fake Str' LIMIT 1));

INSERT INTO ParkingSpaces (Code, LocationFloor, LocationId)
VALUES ('L1_01', 'B1', (SELECT Id FROM Garages LIMIT 1));

INSERT INTO Customers (FirstName, LastName, Email, AddressId)
VALUES ('Alex', 'Castro', 'ac3005g@gre.ac.uk', (SELECT Id FROM Addresses WHERE Address1 = 'Another Str' LIMIT 1));