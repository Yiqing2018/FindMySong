<?php
$servername = "127.0.0.1";
$username = "findmysong_admin";
$password = "LoveCS411!";
$my_db = "findmysong_database";

$link = mysqli_connect($servername, $username, $password,$my_db);


if (!$link) {
    echo "Error: Unable to connect to MySQL";
    exit;
}


$sql="SELECT album_name as name,album.album_cover_url as url FROM album order by popularity limit 0,5";

$result = $link->query($sql);
$value=array();
if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $value[]=$row;
    }
    echo(json_encode($value));
} else {
    echo "404";
}
mysqli_close($link);

?>

