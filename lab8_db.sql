-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema lab8_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema lab8_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `lab8_db` DEFAULT CHARACTER SET utf8 ;
USE `lab8_db` ;

-- -----------------------------------------------------
-- Table `lab8_db`.`monitoreo_climatico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab8_db`.`monitoreo_climatico` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ciudad` VARCHAR(100) NOT NULL,
  `fecha` DATETIME NOT NULL,
  `temp_promedio` DOUBLE NULL,
  `condicion_frecuente` VARCHAR(100) NULL,
  `temp_maxima` DOUBLE NULL,
  `temp_minima` DOUBLE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
