<?php
$servername = "127.0.0.1";
$username = "findmysong_admin";
$password = "LoveCS411!";
$my_db = "findmysong_database";
$user_name = $_POST["username"];
// $user_name="Yiqing";


$link = mysqli_connect($servername, $username, $password,$my_db);


if (!$link) {
    echo "Error: Unable to connect to MySQL";
    exit;
}


$sql="SELECT user2.display_name as name FROM follow,user as user1, user as user2 where user1.user_id=follower_id and  user1.display_name='$user_name' and followed_id=user2.user_id";


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

