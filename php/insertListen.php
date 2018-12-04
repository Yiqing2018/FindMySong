<?php
$servername = "127.0.0.1";
$username = "findmysong_admin";
$password = "LoveCS411!";
$my_db = "findmysong_database";
$user_id = $_POST["user_id"]; 
$track_id = $_POST["track_id"]; 

// $user_id = "test"; 
// $track_id = "test"; 


$link = mysqli_connect($servername, $username, $password,$my_db);


if (!$link) {
    echo "Error: Unable to connect to MySQL";
    exit;
}


$sql="INSERT into listen(user_id,track_id) VALUES ('$user_id','$track_id')";

$result = $link->query($sql);

mysqli_close($link);

?>

