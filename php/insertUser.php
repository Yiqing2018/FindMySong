<?php
$servername = "127.0.0.1";
$username = "findmysong_admin";
$password = "LoveCS411!";
$my_db = "findmysong_database";
$user_id = $_POST["user_id"]; 
$diaplay_name = $_POST["diaplay_name"]; 
// $user_id = "test_id1";
// $diaplay_name = "test_name1";


$link = mysqli_connect($servername, $username, $password,$my_db);


if (!$link) {
    echo "Error: Unable to connect to MySQL";
    exit;
}


$sql="INSERT into user(user_id,display_name) VALUES ('$user_id','$diaplay_name')";

$result = $link->query($sql);

mysqli_close($link);

?>

