<?php
$servername = "127.0.0.1";
$username = "findmysong_admin";
$password = "LoveCS411!";
$my_db = "findmysong_database";
$user1 = $_POST["user1"];
$user2 = $_POST["user2"];

// $user1="Yiqing";
// $user2="John";



$link = mysqli_connect($servername, $username, $password,$my_db);


if (!$link) {
    echo "Error: Unable to connect to MySQL";
    exit;
}


$sql="SELECT  count(distinct t1.artist_id) as count FROM listen as listen1, listen as listen2, user as user1,user as user2, track as t1,track as t2 WHERE user1.display_name='$user1' and user1.user_id=listen1.user_id and user2.display_name='$user2' and user2.user_id=listen2.user_id and listen1.track_id=t1.track_id and listen2.track_id=t2.track_id and t1.artist_id=t2.artist_id";


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

