-- MySQL Script generated by MySQL Workbench
-- Tue 11 Jul 2017 10:29:18 AM EDT
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema SuperheroSightings
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `SuperheroSightings` ;

-- -----------------------------------------------------
-- Schema SuperheroSightings
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SuperheroSightings` DEFAULT CHARACTER SET utf8 ;
USE `SuperheroSightings` ;

-- -----------------------------------------------------
-- Table `SuperheroSightings`.`Locations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperheroSightings`.`Locations` ;

CREATE TABLE IF NOT EXISTS `SuperheroSightings`.`Locations` (
  `LocationId` INT NOT NULL AUTO_INCREMENT,
  `Landmark` VARCHAR(35) NOT NULL,
  `Description` TEXT NULL,
  `StreetAddress` VARCHAR(45) NULL,
  `City` VARCHAR(45) NULL,
  `State` VARCHAR(45) NULL,
  `Zip` INT NULL,
  `Latitude` DECIMAL(10,8) NULL,
  `Longitude` VARCHAR(45) NULL,
  PRIMARY KEY (`LocationId`));


-- -----------------------------------------------------
-- Table `SuperheroSightings`.`HeroesAndVillains`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperheroSightings`.`HeroesAndVillains` ;

CREATE TABLE IF NOT EXISTS `SuperheroSightings`.`HeroesAndVillains` (
  `SuperId` INT NOT NULL AUTO_INCREMENT,
  `SuperName` VARCHAR(25) NOT NULL,
  `Alias` VARCHAR(45) NULL,
  `Cover` VARCHAR(45) NULL,
  `isVillain` TINYINT(1) NOT NULL,
  PRIMARY KEY (`SuperId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperheroSightings`.`Organizations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperheroSightings`.`Organizations` ;

CREATE TABLE IF NOT EXISTS `SuperheroSightings`.`Organizations` (
  `OrganizationId` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Description` VARCHAR(100) NULL,
  `LocationId` INT NULL,
  PRIMARY KEY (`OrganizationId`),
  INDEX `fk_OrgLocationId_idx` (`LocationId` ASC),
  CONSTRAINT `fk_OrgLocationId`
    FOREIGN KEY (`LocationId`)
    REFERENCES `SuperheroSightings`.`Locations` (`LocationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `SuperheroSightings`.`OrganizationsSupers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperheroSightings`.`OrganizationsSupers` ;

CREATE TABLE IF NOT EXISTS `SuperheroSightings`.`OrganizationsSupers` (
  `OrganizationId` INT NOT NULL,
  `SuperId` INT NOT NULL,
  PRIMARY KEY (`OrganizationId`, `SuperId`),
  INDEX `fk_OrgId_idx` (`OrganizationId` ASC),
  CONSTRAINT `fk_SupersId`
    FOREIGN KEY (`SuperId`)
    REFERENCES `SuperheroSightings`.`HeroesAndVillains` (`SuperId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrgId`
    FOREIGN KEY (`OrganizationId`)
    REFERENCES `SuperheroSightings`.`Organizations` (`OrganizationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `SuperheroSightings`.`Sightings`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperheroSightings`.`Sightings` ;

CREATE TABLE IF NOT EXISTS `SuperheroSightings`.`Sightings` (
  `SightingId` INT NOT NULL,
  `LocationId` INT NOT NULL,
  `SightingDate` DATE NULL,
  PRIMARY KEY (`SightingId`),
  INDEX `fk_SightingLocationId_idx` (`LocationId` ASC),
  CONSTRAINT `fk_SightingLocationId`
    FOREIGN KEY (`LocationId`)
    REFERENCES `SuperheroSightings`.`Locations` (`LocationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `SuperheroSightings`.`SupersSightings`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperheroSightings`.`SupersSightings` ;

CREATE TABLE IF NOT EXISTS `SuperheroSightings`.`SupersSightings` (
  `SightingId` INT NOT NULL,
  `SuperId` INT NOT NULL,
  PRIMARY KEY (`SightingId`, `SuperId`),
  INDEX `fk_SupersId_idx` (`SuperId` ASC),
  CONSTRAINT `fk_SightingIds`
    FOREIGN KEY (`SightingId`)
    REFERENCES `SuperheroSightings`.`Sightings` (`SightingId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SuperIds`
    FOREIGN KEY (`SuperId`)
    REFERENCES `SuperheroSightings`.`HeroesAndVillains` (`SuperId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `SuperheroSightings`.`Superpowers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperheroSightings`.`Superpowers` ;

CREATE TABLE IF NOT EXISTS `SuperheroSightings`.`Superpowers` (
  `SuperpowerId` INT NOT NULL AUTO_INCREMENT,
  `Superpower` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`SuperpowerId`));


-- -----------------------------------------------------
-- Table `SuperheroSightings`.`SupersSuperpowers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperheroSightings`.`SupersSuperpowers` ;

CREATE TABLE IF NOT EXISTS `SuperheroSightings`.`SupersSuperpowers` (
  `SuperId` INT NOT NULL,
  `SuperpowerId` INT NOT NULL,
  PRIMARY KEY (`SuperId`, `SuperpowerId`),
  INDEX `fk_Superhero_idx` (`SuperpowerId` ASC),
  INDEX `fk_Superpower_idx` (`SuperId` ASC),
  CONSTRAINT `fk_SuperheroId`
    FOREIGN KEY (`SuperId`)
    REFERENCES `SuperheroSightings`.`HeroesAndVillains` (`SuperId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SuperpowerId`
    FOREIGN KEY (`SuperpowerId`)
    REFERENCES `SuperheroSightings`.`Superpowers` (`SuperpowerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ROW_FORMAT = DEFAULT;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
