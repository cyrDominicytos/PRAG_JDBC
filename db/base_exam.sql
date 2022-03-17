-- --------------------------------------------------------
-- Hôte :                        localhost
-- Version du serveur:           5.7.24 - MySQL Community Server (GPL)
-- SE du serveur:                Win64
-- HeidiSQL Version:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Listage de la structure de la table base_exam. personne
DROP TABLE IF EXISTS `personne`;
CREATE TABLE IF NOT EXISTS `personne` (
  `matricule` int(11) NOT NULL,
  `nom` varchar(40) NOT NULL,
  `prenom` varchar(40) NOT NULL,
  `code_service` int(11) NOT NULL,
  PRIMARY KEY (`matricule`),
  KEY `code_service` (`code_service`),
  CONSTRAINT `personne_ibfk_1` FOREIGN KEY (`code_service`) REFERENCES `service` (`code_service`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table base_exam.personne : ~0 rows (environ)
/*!40000 ALTER TABLE `personne` DISABLE KEYS */;
INSERT INTO `personne` (`matricule`, `nom`, `prenom`, `code_service`) VALUES
	(4113, 'IPAKTCHI', 'Nadjoua', 13),
	(4116, 'FERRERE', 'FERRERE', 14),
	(4121, 'SINACO', 'Keveen', 15),
	(4123, 'GRIMA', 'Botan', 15);
/*!40000 ALTER TABLE `personne` ENABLE KEYS */;

-- Listage de la structure de la table base_exam. service
DROP TABLE IF EXISTS `service`;
CREATE TABLE IF NOT EXISTS `service` (
  `code_service` int(11) NOT NULL,
  `libelle_service` varchar(25) NOT NULL,
  PRIMARY KEY (`code_service`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table base_exam.service : ~0 rows (environ)
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` (`code_service`, `libelle_service`) VALUES
	(12, 'Peinture'),
	(13, 'Plomberie'),
	(14, 'Electricité'),
	(15, 'Couverture');
/*!40000 ALTER TABLE `service` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
