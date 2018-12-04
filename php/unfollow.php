<?php
$servername = "127.0.0.1";
$username = "findmysong_admin";
$password = "LoveCS411!";
$my_db = "findmysong_database";
//follower_id
$user1 = $_POST["user1"]; 
//followed_id
$user2 = $_POST["user2"]; 

// $user1="Yiqing";
// $user2="Abdu";



$link = mysqli_connect($servername, $username, $password,$my_db);


if (!$link) {
    echo "Error: Unable to connect to MySQL";
    exit;
}


$sql="DELETE FROM follow where followed_id=(SELECT user.user_id from user where user.display_name='$user2') and follower_id=(SELECT user.user_id from user where user.display_name='$user1')";

$result = $link->query($sql);

mysqli_close($link);

?>

