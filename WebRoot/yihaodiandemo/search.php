<html>
<head>
  <title>YiHaoDian - Search Results</title>
</head>

<body>
<h1>YiHaoDian - Search Results</h1>

<?php
$searchtype = $_REQUEST["searchtype"];
$searchterm = $_REQUEST["searchterm"];

echo "\$searchtype = $searchtype<br>\n";
echo "\$searchterm = $searchterm<br>\n";
$searchterm = trim ($searchterm);
echo "\$searchterm = $searchterm<br>\n";

if (!$searchtype) {
  echo "You have not entered search details. Please go back and try agian.<br>\n";
  exit(1);
}

$searchtype = addslashes ($searchtype);
$searchterm = addslashes ($searchterm);

for ($i=0; $i<1; $i++) {
sleep($i);
$db = mysql_connect("instance25306.db.xeround.com:6401", "yihaodian", "eulerats");
if ($db) break;
}

echo "\$i = $i<br>\n";

if ($db) {
  echo "DB connection has been established.<br>\n";
}
if (!$db) {
  echo "ERROR: DB connection could not be established: " . mysql_error() . "<br>\n";
  exit(1);
}

mysql_select_db("yihaodian", $db);
$query = "SELECT * FROM YHDmain WHERE $searchtype LIKE '%$searchterm%'";
echo("\$query = $query\n<br>");
$res = mysql_query($query, $db);

echo("\$res = $res\n<br>");

$nres = mysql_num_rows ($res);

echo("\$nres = $nres\n<br>");

for ($i=0; $i < $nres; $i++)
{
  echo("\$i = $i<br>");
  $row = mysql_fetch_array($res);
  echo("\$row[YHDid] = ".$row["YHDid"]."\n<br>");
  echo("\$row[YHDtitle] = ");
  echo("<a href=\"http://www.yihaodian.com/product/" .$row["YHDid"]. "_1\">" .$row["YHDtitle"]. "</a><br>\n");
  echo("\$row[YHDprice] = ".$row["YHDprice"]."\n<br>");
}

mysql_close($db);
echo "DB connection has been closed.<br>\n";

?>

</body>

</html>