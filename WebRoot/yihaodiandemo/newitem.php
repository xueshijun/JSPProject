<html>
<head>
 <title>YiHaoDian - Item Entry Result</title>
</head>

<body>
<h1>YiHaoDian - Item Entry Result</h1>
</body>

<?php

$YHDid = $_REQUEST["YHDid"];
$YHDtitle = $_REQUEST["YHDtitle"];
$YHDprice = $_REQUEST["YHDprice"];
$YHDpprice = $_REQUEST["YHDpprice"];

?>

<?php

if (!$YHDid || !$YHDtitle || !$YHDprice) {
  echo "Your have not entered all the required details.<br>\n";
  echo "Please go back and try again.<br>\n";
  exit(1);
}

?>

<?php

$YHDid = addslashes($YHDid);
$YHDtitle = addslashes($YHDtitle);
$YHDprice = doubleval($YHDprice);

for ($i=0; $i<1; $i++) {
sleep($i);
$db = mysql_connect("instance25306.db.xeround.com:6401", "yihaodian", "eulerats");
if ($db) break;
}

if ($db) {
  echo "DB connection has been established.<br>\n";
}
if (!$db) {
  echo "ERROR: DB connection could not be established: " . mysql_error() . "<br>\n";
  exit(1);
}

mysql_select_db("yihaodian", $db);
$query = "INSERT INTO YHDmain VALUES (NULL, '$YHDid', '$YHDtitle', '$YHDprice')";
echo("\$query = $query\n<br>");
$res = mysql_query($query, $db);
if ($res) {
  echo mysql_affected_rows () . " item inserted into database.<br>\n";
}
if (!$res) {
  echo "ERROR: " . mysql_error() . "<br>\n";
}

if ($YHDpprice) {
//
$YHDpprice = doubleval($YHDpprice);
$query = "INSERT INTO YHDpprice VALUES ('$YHDid', '$YHDpprice')";
echo("\$query = $query\n<br>");
$res = mysql_query($query, $db);
if ($res) {
  echo mysql_affected_rows () . " item inserted into database.<br>\n";
}
if (!$res) {
  echo "ERROR: " . mysql_error() . "<br>\n";
}
//
} // end of [if]

mysql_close($db);

exit(0);

?>

</html>