SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `oblig` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `oblig` ;
DROP TABLE IF EXISTS `oblig`.`ADMIN` ;
DROP TABLE IF EXISTS `oblig`.`TICKET` ;
DROP TABLE IF EXISTS `oblig`.`PARTICIPANT` ;
DROP TABLE IF EXISTS `oblig`.`EVENT` ;
DROP TABLE IF EXISTS `oblig`.`PERSON` ;
DROP TABLE IF EXISTS `oblig`.`NATIONALITY` ;
DROP TABLE IF EXISTS `oblig`.`TYPE` ;

-- -----------------------------------------------------
-- Table `oblig`.`TYPE`
-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `oblig`.`TYPE` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `TYPE` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oblig`.`EVENT`
-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `oblig`.`EVENT` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `DATE` DATE NOT NULL ,
  `TYPE_ID` INT NOT NULL ,
  `ISMALES` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `fk_EVENT_TYPE1` (`TYPE_ID` ASC) ,
  CONSTRAINT `fk_EVENT_TYPE1`
    FOREIGN KEY (`TYPE_ID` )
    REFERENCES `oblig`.`TYPE` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oblig`.`NATIONALITY`
-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `oblig`.`NATIONALITY` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `NATION` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oblig`.`PERSON`
-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `oblig`.`PERSON` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `NATIONALITY_ID` INT NOT NULL ,
  `FIRSTNAME` VARCHAR(45) NOT NULL ,
  `LASTNAME` VARCHAR(45) NOT NULL ,
  `ISMALE` TINYINT(1) NOT NULL ,
  `ADRESS` VARCHAR(45) NULL ,
  `PHONE` VARCHAR(13) NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `fk_PERSON_NATIONALITY1` (`NATIONALITY_ID` ASC) ,
  CONSTRAINT `fk_PERSON_NATIONALITY1`
    FOREIGN KEY (`NATIONALITY_ID` )
    REFERENCES `oblig`.`NATIONALITY` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oblig`.`TICKET`
-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `oblig`.`TICKET` (
  `EVENT_ID` INT NOT NULL ,
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `PERSON_ID` INT NOT NULL ,
  PRIMARY KEY (`ID`, `EVENT_ID`) ,
  INDEX `fk_TICKET_PERSON1` (`PERSON_ID` ASC) ,
  CONSTRAINT `fk_TICKET_EVENT`
    FOREIGN KEY (`EVENT_ID` )
    REFERENCES `oblig`.`EVENT` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TICKET_PERSON1`
    FOREIGN KEY (`PERSON_ID` )
    REFERENCES `oblig`.`PERSON` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oblig`.`PARTICIPANT`
-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `oblig`.`PARTICIPANT` (
  `EVENT_ID` INT NOT NULL ,
  `PERSON_ID` INT NOT NULL ,
  PRIMARY KEY (`EVENT_ID`, `PERSON_ID`) ,
  INDEX `fk_PARTICIPANT_PERSON1` (`PERSON_ID` ASC) ,
  CONSTRAINT `fk_PARTICIPANT_EVENT1`
    FOREIGN KEY (`EVENT_ID` )
    REFERENCES `oblig`.`EVENT` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PARTICIPANT_PERSON1`
    FOREIGN KEY (`PERSON_ID` )
    REFERENCES `oblig`.`PERSON` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oblig`.`ADMIN`
-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `oblig`.`ADMIN` (
  `PERSON_ID` INT NOT NULL ,
  `PASSWORD` VARCHAR(40) NOT NULL ,
  `EMAIL` VARCHAR(40) NOT NULL ,
  PRIMARY KEY (`PERSON_ID`) ,
  CONSTRAINT `fk_table1_PERSON1`
    FOREIGN KEY (`PERSON_ID` )
    REFERENCES `oblig`.`PERSON` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;







-- -----------------------------------------------------
-- INSERTS
-- -----------------------------------------------------

INSERT INTO oblig.NATIONALITY (NATION) VALUES
("Norge"),
("Danmark"),
("Tyskland"),
("Sverige"),
("Nederland"),
("Finland"),
("Slovenia"),
("Østerrike"),
("Italia"),
("Ljubljana"),
("Polen");

INSERT INTO oblig.TYPE (TYPE) VALUES
("Kombinert"),
("Stafett"),
("Hopp"),
("Fellesstart 50km"),
("Fellesstart 30km");

INSERT INTO oblig.EVENT (TYPE_ID, DATE, ISMALES) VALUES
(1, "2012-03-09", TRUE),
(3, "2012-03-09", FALSE),
(4, "2012-03-10", TRUE),
(2, "2012-03-10", TRUE),
(5, "2012-03-11", FALSE),
(3, "2012-03-11", TRUE);

INSERT INTO oblig.PERSON (FIRSTNAME, LASTNAME, NATIONALITY_ID, ISMALE) VALUES
("Ola", "Nordmann", 1, TRUE),
("Erik", "Ver", 4, TRUE),
("Max", "Ebel", 3, TRUE),
("Gudmund", "Storlien", 1, TRUE),
("Mikko", "Kokslien", 6, TRUE),
("Anders", "Bardal", 1, TRUE),
("Robert", "Kranjec", 7, TRUE),
("Martin", "Koch", 8, TRUE),
("Marcus", "Hellner", 4, TRUE),
("Petter", "Northug", 1, TRUE),
("Maja", "Vtic", 7, FALSE),
("Marit", "Bjørgen", 1, FALSE),
("Arianna", "Follis", 9, FALSE),
("Petra", "Majdic", 10, FALSE),
("Therese", "Johaug", 1, FALSE),
("Justyna", "Kowalczyk", 11, FALSE),
("", "", 1, TRUE);

INSERT INTO oblig.ADMIN (PERSON_ID, PASSWORD, EMAIL) VALUES
(17, "51862b26e314c7019eadd66cd911c3b55fa5c435", "test");

INSERT INTO oblig.TICKET (EVENT_ID, PERSON_ID) VALUES
(1,1),
(2,11),
(3,1),
(4,3),
(5,12),
(6,2);

INSERT INTO oblig.PARTICIPANT (EVENT_ID, PERSON_ID) VALUES
(1,10),
(2,8),
(3,5),
(4,6),
(5,10),
(6,9);