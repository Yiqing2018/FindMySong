
<?php
$servername = "127.0.0.1";
$username = "findmysong_admin";
$password = "LoveCS411!";
$my_db = "findmysong_database";

$user_name = $_POST["username"]; 
$user_feedback = $_POST["user_feedback"]; 
$user_email=$_POST["user_email"]; 

// $user_name = "yiqing";
// $user_feedback ="test";
// $user_email="test";



$link = mysqli_connect($servername, $username, $password,$my_db);


if (!$link) {
    echo "Error: Unable to connect to MySQL";
    exit;
}


$sql="INSERT into feedback(user_id,user_email,user_feedback)values((SELECT user_id from user where display_name='$user_name'),'$user_email','$user_feedback')";

$result = $link->query($sql);
echo("why can't get access");

mysqli_close($link);

?>

