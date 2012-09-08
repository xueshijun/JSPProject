#
# SQL to create the tables for YiHaoDian
#

/*
DROP TABLE YHDmain;
DROP TABLE YHDpprice;
*/

CREATE TABLE
YHDmain
(
  id INT UNSIGNED NOT NULL AUTO_INCREMENT
, YHDid VARCHAR(16) NOT NULL
, YHDtitle VARCHAR(120) NOT NULL # YHD title
, YHDprice FLOAT(6, 2) NOT NULL # YHD price
, PRIMARY KEY (id)
) ;

CREATE TABLE
YHDpprice
(
  YHDid CHAR(16) NOT NULL
, YHDpprice FLOAT(6, 2) NOT NULL # YHD promotional price
, PRIMARY KEY (YHDid)
) ;

