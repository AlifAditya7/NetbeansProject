/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 10.4.24-MariaDB : Database - kemahasiswaan_10121083
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`kemahasiswaan_10121083` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `kemahasiswaan_10121083`;

/*Table structure for table `data_infaq` */

DROP TABLE IF EXISTS `data_infaq`;

CREATE TABLE `data_infaq` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `kencleng` int(50) DEFAULT 0,
  `donatur` int(50) DEFAULT 0,
  `total` int(50) DEFAULT 0,
  `nama_donatur` text DEFAULT NULL,
  `admin_masuk` varchar(20) DEFAULT NULL,
  `admin_ubah` varchar(20) DEFAULT NULL,
  `tanggal` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `data_infaq` */

insert  into `data_infaq`(`no`,`kencleng`,`donatur`,`total`,`nama_donatur`,`admin_masuk`,`admin_ubah`,`tanggal`) values 
(1,200000,5000000,5200000,'-','alif','-','Jul 27, 2022'),
(2,2000000,5000000,7000000,' dinar, nasza ',' alif ','-','Jul 27, 2022'),
(3,300000,4000000,4300000,' hidayat ',' alif ','Alif','Aug 2, 2022');

/*Table structure for table `mahasiswa` */

DROP TABLE IF EXISTS `mahasiswa`;

CREATE TABLE `mahasiswa` (
  `nim` varchar(20) NOT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `ttl` varchar(20) DEFAULT NULL,
  `tgl_lahir` varchar(20) DEFAULT NULL,
  `alamat` text DEFAULT NULL,
  PRIMARY KEY (`nim`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `mahasiswa` */

insert  into `mahasiswa`(`nim`,`nama`,`ttl`,`tgl_lahir`,`alamat`) values 
('10121057',' Muhammad Hidayat Akbar',' Banda Aceh','Jul 20, 2003',' Lambhuk '),
('10121060',' Dinar Nur Aziz',' Ciamis','Mar 18, 2003','Cinunuk'),
('10121082',' Febi Amelia',' Jambi',' Feb 28, 2003',' Jambi '),
('10121083',' Mochammad Alif Aditya Putra Bhakti',' Bandung',' Aug 5, 2003',' Perum. Cijagra Indah No. 20 ');

/*Table structure for table `matakuliah` */

DROP TABLE IF EXISTS `matakuliah`;

CREATE TABLE `matakuliah` (
  `no_mk` varchar(20) NOT NULL,
  `nama_mk` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`no_mk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `matakuliah` */

insert  into `matakuliah`(`no_mk`,`nama_mk`) values 
('M001','Algoritma'),
('M002','Matematika'),
('M003','Statistika'),
('M004','Pemrograman');

/*Table structure for table `nilai_akhir` */

DROP TABLE IF EXISTS `nilai_akhir`;

CREATE TABLE `nilai_akhir` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `kode_mk` varchar(20) DEFAULT NULL,
  `persen_absen` float DEFAULT NULL,
  `persen_tugas` float DEFAULT NULL,
  `persen_uts` float DEFAULT NULL,
  `persen_uas` float DEFAULT NULL,
  `absensi` int(20) DEFAULT NULL,
  `tugas1` int(20) DEFAULT NULL,
  `tugas2` int(20) DEFAULT NULL,
  `tugas3` int(20) DEFAULT NULL,
  `uts` int(20) DEFAULT NULL,
  `uas` int(20) DEFAULT NULL,
  `nilai_absen` int(20) DEFAULT NULL,
  `nilai_tugas` int(20) DEFAULT NULL,
  `nilai_uts` int(20) DEFAULT NULL,
  `nilai_uas` int(20) DEFAULT NULL,
  `nilai_akhir` int(20) DEFAULT NULL,
  `indeks` varchar(3) DEFAULT NULL,
  `keterangan` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`no`),
  KEY `1` (`kode_mk`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `nilai_akhir` */

insert  into `nilai_akhir`(`no`,`kode_mk`,`persen_absen`,`persen_tugas`,`persen_uts`,`persen_uas`,`absensi`,`tugas1`,`tugas2`,`tugas3`,`uts`,`uas`,`nilai_absen`,`nilai_tugas`,`nilai_uts`,`nilai_uas`,`nilai_akhir`,`indeks`,`keterangan`) values 
(1,'M003 ',5,25,30,40,14,67,65,54,78,45,5,15,23,18,61,' C ',' Lulus'),
(2,'M001',5,25,30,40,14,100,57,75,0,64,5,19,0,25,49,'C','Lulus'),
(3,'M003',5,25,30,40,14,100,100,100,100,100,5,25,30,40,100,'A','Lulus'),
(4,'M001',5,25,30,45,14,78,0,45,86,100,5,10,25,45,85,'A','Lulus'),
(5,'M003',5,25,30,40,13,78,67,80,78,90,0,18,23,36,77,'B','Lulus');

/*Table structure for table `nilai_mhs` */

DROP TABLE IF EXISTS `nilai_mhs`;

CREATE TABLE `nilai_mhs` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `nim` varchar(20) DEFAULT NULL,
  `kode_mk` varchar(20) DEFAULT NULL,
  `absensi` int(20) DEFAULT NULL,
  `tugas1` int(20) DEFAULT NULL,
  `tugas2` int(20) DEFAULT NULL,
  `tugas3` int(20) DEFAULT NULL,
  `uts` int(20) DEFAULT NULL,
  `uas` int(20) DEFAULT NULL,
  `nilai_absen` int(20) DEFAULT NULL,
  `nilai_tgs` int(20) DEFAULT NULL,
  `nilai_uts` int(20) DEFAULT NULL,
  `nilai_uas` int(20) DEFAULT NULL,
  `nilai_akhir` int(20) DEFAULT NULL,
  `indeks` char(2) DEFAULT NULL,
  `keterangan` varchar(20) DEFAULT NULL,
  `angkatan` varchar(20) DEFAULT '2021',
  PRIMARY KEY (`no`),
  KEY `2` (`nim`),
  KEY `3` (`kode_mk`),
  CONSTRAINT `nilai_mhs_ibfk_1` FOREIGN KEY (`kode_mk`) REFERENCES `matakuliah` (`no_mk`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `nilai_mhs_ibfk_2` FOREIGN KEY (`nim`) REFERENCES `mahasiswa` (`nim`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `nilai_mhs` */

insert  into `nilai_mhs`(`no`,`nim`,`kode_mk`,`absensi`,`tugas1`,`tugas2`,`tugas3`,`uts`,`uas`,`nilai_absen`,`nilai_tgs`,`nilai_uts`,`nilai_uas`,`nilai_akhir`,`indeks`,`keterangan`,`angkatan`) values 
(1,'10121083','M003',14,100,100,100,100,100,5,25,30,40,100,' A',' Lulus ','2021'),
(2,'10121083','M001',12,100,100,100,100,100,0,25,30,40,95,' A',' Lulus ','2021'),
(3,'10121060','M002',10,69,56,67,76,54,0,16,22,21,59,' C',' Tidak Lulus ','2021');

/*Table structure for table `pengguna` */

DROP TABLE IF EXISTS `pengguna`;

CREATE TABLE `pengguna` (
  `no` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `pass` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `pengguna` */

insert  into `pengguna`(`no`,`username`,`pass`) values 
(1,'admin','admin');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
